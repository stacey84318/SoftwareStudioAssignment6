package main.java;

import java.lang.Object;
import processing.event.*;
import processing.event.KeyEvent;
import java.awt.Component;
import java.util.ArrayList;
import java.util.EventObject;

import processing.core.PApplet;
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
	Character current;
	float curX, curY;
	ArrayList<Character> inCircle;

	String episode="starwars-episode-1-interactions.json";

	private final static int width = 1200, height = 650;
	
	
	public void setup() {
		size(width, height);
		characters = new ArrayList<Character>();
		inCircle = new ArrayList<Character>();
		smooth();
		loadData();
		this.addMouseListener(this);
	    this.addMouseMotionListener(this);
		//this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}
	
	public void mousePressed() {
		if(draglock==false){
			for(Character character : characters){
				if(mouseX<character.x+10+30 && mouseX>character.x+10-30 && mouseY<character.y+30 && mouseY>character.y-30){
					current=character;
					draglock=true;
					curX=character.x;
					curY=character.y;
				}
			}
		}
	}

	public void mouseReleased() {
		
		if((mouseX-650)*(mouseX-650)+(mouseY-325)*(mouseY-325)<=275*275){
			if(draglock){
				draglock=false;
				current.inCircle=true;
				inCircle.add(current);
				for(int i=0; i<inCircle.size(); i++){
					inCircle.get(i).x=(float)(Math.cos(Math.toRadians((360/inCircle.size())*i))*275)+650;
					inCircle.get(i).y=(float)(Math.sin(Math.toRadians((360/inCircle.size())*i))*275)+325;
				}
				System.out.println("4---"+inCircle.size());
			}
		}
		else{
			draglock=false;
			current.x=curX;
			current.y=curY;
		}
	}
	
	public void mouseDragged() {
		current.x=mouseX;
		current.y=mouseY;
		current.display();
	}
	 
	public void keyPressed(){
	        if(key=='1') 
	        	episode ="starwars-episode-1-interactions.json";
	        else if(key=='2')  
	        	episode ="starwars-episode-2-interactions.json";
	        else if(key=='3')  
	        	episode ="starwars-episode-3-interactions.json";
	        else if(key=='4')  
	        	episode ="starwars-episode-4-interactions.json";
	        else if(key=='5') 
	        	episode ="starwars-episode-5-interactions.json";
	        else if(key=='6') 
	        	episode ="starwars-episode-6-interactions.json";
	        else if(key=='7')  
	        	episode ="starwars-episode-7-interactions.json";
	        else 
	        	episode ="starwars-episode-1-interactions.json";
	        	
	        setup();
	        
	       }

	public void draw() {
		this.background(255);
		this.ellipse(650, 325, 550, 550);
		for(Character character : characters){
			if(mouseX<character.x+10+30 && mouseX>character.x+10-30 && mouseY<character.y+30 && mouseY>character.y-30){
				character.showName=true;
			}
			else
				character.showName=false;
			character.display(); // let the character handle its own display
			
			
		}
		
	}

	private void loadData(){
		
		this.data = loadJSONObject(path+episode);

		this.nodes = data.getJSONArray("nodes");
		this.links = data.getJSONArray("links");
		System.out.println(nodes.size());
		for(int i = 0 ;i<nodes.size();i++){
			JSONObject N = nodes.getJSONObject(i);
			int value = N.getInt("value");
			String name = N.getString("name");
			String color = N.getString("colour");
			System.out.println(name+","+value+","+color);
			this.characters.add(new Character(this,name,50+(i/10)*70,50+(i%10)*60+((i/10)%2)*30,color));;
		}
	}

}
