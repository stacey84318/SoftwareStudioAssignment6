package main.java;

import java.lang.Object;
import processing.event.*;
import processing.event.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.EventObject;

import processing.core.PApplet;
import processing.core.PFont;
import processing.data.JSONArray;
import processing.data.JSONObject;
import processing.event.MouseEvent;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	
	//set the variable 
	private String path = "main/resources/";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	boolean draglock=false;
	int last_key='0';
	Character current;
	ArrayList<Character> inCircle;
	boolean releaseDetect=false;
	String episode="starwars-episode-1-interactions.json";
	int num_episode=1;
	private final static int width = 1200, height = 650;
	
	
	public void setup() {
		
		//設定 建立兩個Character的array 一個放全部一個放在circle裡的
		size(width, height);
		characters = new ArrayList<Character>();
		inCircle = new ArrayList<Character>();
		smooth();
		loadData();
		this.addMouseListener(this);
	    this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}
	
	public void mousePressed() {
	 
		//設置滑鼠事件，點到某固定區塊發生固定事件，第一個為加入全部的點
		if(mouseX>=980&&mouseX<=1160&&mouseY>=60&&mouseY<=140){
			releaseDetect=false;
			for(Character character : characters){
			//若該點尚未在circle裡，要加入
				if(character.inCircle==false){
					character.inCircle=true;
					inCircle.add(character);
				}
			}
			//設置各個點的位置
			for(int i=0; i<inCircle.size(); i++){
				inCircle.get(i).x=(float)(Math.cos(Math.toRadians((360/inCircle.size())*i))*275)+650;
				inCircle.get(i).y=(float)(Math.sin(Math.toRadians((360/inCircle.size())*i))*275)+325;
			}
		}
		//如果點到clear
		else if(mouseX>=980&&mouseX<=1160&&mouseY>=140&&mouseY<=220){
			releaseDetect=false;
			//把點放回原處
			for(int i=0; i<inCircle.size(); i++){
				inCircle.get(i).inCircle=false;
				inCircle.get(i).x=inCircle.get(i).oriX;
				inCircle.get(i).y=inCircle.get(i).oriY;
			}
			//從圈內的array 移除所有點
			inCircle.removeAll(inCircle);
		}
		//其他情況：按到點
		else{
			releaseDetect=true;
			if(draglock==false){
				for(Character character : characters){
					if(mouseX<character.x+10+30 && mouseX>character.x+10-30 && mouseY<character.y+30 && mouseY>character.y-30){
						current=character;
						draglock=true;
					}
				}
			}
		}
	}

	//放開滑鼠的事件
	public void mouseReleased() {
		if(releaseDetect){
			if(current!=null&&current.inCircle){
				if(draglock){
					
					//如果不在圈內放開，放回原點
					if((mouseX-650)*(mouseX-650)+(mouseY-325)*(mouseY-325)>275*275){
						draglock=false;
						current.inCircle=false;
						current.x=current.oriX;
						current.y=current.oriY;
						inCircle.remove(current);
					}
					
					//如果在圈內放開，就將點安排進circle
					for(int i=0; i<inCircle.size(); i++){
						inCircle.get(i).x=(float)(Math.cos(Math.toRadians((360/inCircle.size())*i))*275)+650;
						inCircle.get(i).y=(float)(Math.sin(Math.toRadians((360/inCircle.size())*i))*275)+325;
					}	
				}
			}
			//如果點本來在圈外
			else{
				if((mouseX-650)*(mouseX-650)+(mouseY-325)*(mouseY-325)<=275*275){
					if(draglock){
						draglock=false;
						current.inCircle=true;
						inCircle.add(current);
						for(int i=0; i<inCircle.size(); i++){
							inCircle.get(i).x=(float)(Math.cos(Math.toRadians((360/inCircle.size())*i))*275)+650;
							inCircle.get(i).y=(float)(Math.sin(Math.toRadians((360/inCircle.size())*i))*275)+325;
						}
						//System.out.println("4---"+inCircle.size());
					}
				}
		
				else{
					draglock=false;
					current.x=current.oriX;
					current.y=current.oriY;
				}
			}
			releaseDetect=false;
		}
	}
	
	//拖曳點
	public void mouseDragged() {
		if(releaseDetect){
			current.x=mouseX;
			current.y=mouseY;
			current.display();
		}
	}
	
	//鍵盤事件，改變episode
	public void keyPressed(){
		if(key!=last_key){
	        if(key=='1'){
	        	episode ="starwars-episode-1-interactions.json";
	        	num_episode=1;
	        }
	        else if(key=='2'){  
	        	episode ="starwars-episode-2-interactions.json";
	        	num_episode=2;
	        }
	        else if(key=='3'){  
	        	episode ="starwars-episode-3-interactions.json";
	        	num_episode=3;
	        }
	        else if(key=='4'){ 
	        	episode ="starwars-episode-4-interactions.json";
	        	num_episode=4;
	        }
	        else if(key=='5'){ 
	        	episode ="starwars-episode-5-interactions.json";
	        	num_episode=5;
	        }
	        else if(key=='6'){ 
	        	episode ="starwars-episode-6-interactions.json";
	        	num_episode=6;
	        }
	        else if(key=='7'){  
	        	episode ="starwars-episode-7-interactions.json";
	        	num_episode=7;
	        }
	        else{ 
	        	episode ="starwars-episode-1-interactions.json";
	        	num_episode=1;
	        }
	        last_key=key;
	        setup();
		}
	}

	//畫上該有的東西，依照集數畫上點
	public void draw() {
		//設定字型
		PFont f;
		f = createFont("Arial",16,true);
		
		//設定背景跟大圓
		this.background(255);
		this.strokeWeight(1);
		this.stroke(200);
		this.ellipse(650, 325, 550, 550);
	
		//放上文字
		textFont(f,30);
		this.fill(100);
		this.text("Star Wars "+num_episode, 575, 30);
		
		//放上add all 跟 clear的按鈕
		this.strokeJoin(ROUND);
		this.fill(200);
		this.stroke(200);
		this.strokeWeight(20);
		this.rect(1000, 80, 140, 40);
		this.rect(1000, 160, 140, 40);	
		
		textFont(f,20);
		
		this.fill(255);
		this.text("ADD ALL", 1025, 110);
		
		this.fill(255);
		this.text("CLEAR", 1030, 190);
		
		//設每個點的位置
		for(Character character : characters){
			this.strokeJoin(ROUND);
			if(mouseX<character.x+10+30 && mouseX>character.x+10-30 && mouseY<character.y+30 && mouseY>character.y-30){
				character.showName=true;
			}
			else
				character.showName=false;
			//設link的位置 起始點及終點
			ArrayList<Character> targets = character.getTargets();
			for(Character target:targets){
				this.strokeWeight(1+target.value/10);
				this.stroke(0);
				if(character.inCircle && target.inCircle){
					this.noFill();
					this.bezier(character.x,character.y,(650+character.x)/2,(325+character.y)/2,(650+target.x)/2,(325+target.y)/2, target.x, target.y);	
				}	
			}
			this.stroke(105,105,105,200);
			this.textFont(f, 13);
			character.display(); // let the character handle its own display
		}
	}

	private void loadData(){
		//讀入檔案並設置他的起始位置
		this.data = loadJSONObject(path+episode);
		this.nodes = data.getJSONArray("nodes");
		this.links = data.getJSONArray("links");
		for(int i = 0 ;i<nodes.size();i++){
			JSONObject N = nodes.getJSONObject(i);
			int value = N.getInt("value");
			String name = N.getString("name");
			String color = N.getString("colour");
			this.characters.add(new Character(this,name,50+(i/10)*70,50+(i%10)*60+((i/10)%2)*30,color,value));
		}
		//讀入線並設置起始位置
		for(int i=0;i<links.size();i++){
			JSONObject L = links.getJSONObject(i);
			characters.get(L.getInt("source")).addTarget(characters.get(L.getInt("target")),L.getInt("value") );
		}
	}
}
