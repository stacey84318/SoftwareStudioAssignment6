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
		if(mouseX>=980&&mouseX<=1160&&mouseY>=60&&mouseY<=140){
			releaseDetect=false;


			System.out.println("in add all");

			for(Character character : characters){
				if(character.inCircle==false){
					character.inCircle=true;
					inCircle.add(character);
				}
			}
			for(int i=0; i<inCircle.size(); i++){
				inCircle.get(i).x=(float)(Math.cos(Math.toRadians((360/inCircle.size())*i))*275)+650;
				inCircle.get(i).y=(float)(Math.sin(Math.toRadians((360/inCircle.size())*i))*275)+325;
			}
		}
		else if(mouseX>=980&&mouseX<=1160&&mouseY>=140&&mouseY<=220){
			releaseDetect=false;
			System.out.println("in remove");
			for(int i=0; i<inCircle.size(); i++){
				inCircle.get(i).inCircle=false;
				inCircle.get(i).x=inCircle.get(i).oriX;
				inCircle.get(i).y=inCircle.get(i).oriY;
			}
			inCircle.removeAll(inCircle);
		}
		else{
			//System.out.println("elllse! "+mouseX+","+mouseY);
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

	public void mouseReleased() {

		//System.out.println("----1"+current.inCircle);
		if(releaseDetect){
			if(current!=null&&current.inCircle){
				if(draglock){
					//System.out.println("----2"+current.inCircle);
					if((mouseX-650)*(mouseX-650)+(mouseY-325)*(mouseY-325)>275*275){
						draglock=false;
						current.inCircle=false;
						current.x=current.oriX;
						current.y=current.oriY;
						inCircle.remove(current);
					}
					
					for(int i=0; i<inCircle.size(); i++){
						inCircle.get(i).x=(float)(Math.cos(Math.toRadians((360/inCircle.size())*i))*275)+650;
						inCircle.get(i).y=(float)(Math.sin(Math.toRadians((360/inCircle.size())*i))*275)+325;
					}	
				}
				//System.out.println("----3"+current.inCircle);
			}
			else{
				
				//System.out.println("----4"+current.inCircle);
				
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
	
	public void mouseDragged() {
		if(releaseDetect){
			current.x=mouseX;
			current.y=mouseY;
			current.display();
		}
	}
	 
	public void keyPressed(){
		if(key!=last_key){
	        if(key=='1'){
	        	episode ="starwars-episode-1-interactions.json";
	        	num_episode=1;
	        	System.out.println(key);
	        }
	        else if(key=='2'){  
	        	episode ="starwars-episode-2-interactions.json";
	        	num_episode=2;
	        	System.out.println(key);
	        }
	        else if(key=='3'){  
	        	episode ="starwars-episode-3-interactions.json";
	        	num_episode=3;
	        	System.out.println(key);
	        }
	        else if(key=='4'){ 
	        	episode ="starwars-episode-4-interactions.json";
	        	num_episode=4;
	        	System.out.println(key);
	        }
	        else if(key=='5'){ 
	        	episode ="starwars-episode-5-interactions.json";
	        	num_episode=5;
	        	System.out.println(key);
	        }
	        else if(key=='6'){ 
	        	episode ="starwars-episode-6-interactions.json";
	        	num_episode=6;
	        	System.out.println(key);
	        }
	        else if(key=='7'){  
	        	episode ="starwars-episode-7-interactions.json";
	        	num_episode=7;
	        	System.out.println(key);
	        }
	        else{ 
	        	episode ="starwars-episode-1-interactions.json";
	        	num_episode=1;
	        	System.out.println(key);
	        }
	        last_key=key;
	        setup();
		}
	}

	public void draw() {
		
		PFont f;
		f = createFont("Arial",16,true);
		
		this.background(255);
		this.strokeWeight(1);
		this.stroke(200);
		this.ellipse(650, 325, 550, 550);
		
		textFont(f,30);
		this.fill(100);
		this.text("Star Wars "+num_episode, 575, 30);
		
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
		
		for(Character character : characters){
			this.strokeJoin(ROUND);
			if(mouseX<character.x+10+30 && mouseX>character.x+10-30 && mouseY<character.y+30 && mouseY>character.y-30){
				character.showName=true;
			}
			else
				character.showName=false;
			
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
		for(int i=0;i<links.size();i++){
			JSONObject L = links.getJSONObject(i);
			characters.get(L.getInt("source")).addTarget(characters.get(L.getInt("target")),L.getInt("value") );
		}
	}

}
