package main.java;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;
//import processing.event.MouseEvent;

import java.awt.*;

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

	int count=0;

	String episode="starwars-episode-1-interactions.json";

	
	private final static int width = 1200, height = 650;
	
	
	public void setup() {
		size(width, height);
		characters = new ArrayList<Character>();	
		smooth();
		loadData();
		this.addMouseListener(this);
	    this.addMouseMotionListener(this);
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getX()>=375&&e.getX()<=925&&e.getY()>=50&&e.getY()<=600){
			count++;
			setDot((Character)e.getSource(),e.getX(),e.getY());
		}
		   
	}
	
	public void mouseDragged(MouseEvent e) {
		((Character)e.getSource()).setLocation(e.getX(),e.getY()); 
	}

	
	public void setDot(Character role, int x, int y){
		
		role.setLocation(x, y);
	}
	 
	public void keyPressed(KeyEvent e){
	        switch(e.getKeyCode()){
	        case KeyEvent.VK_1: 
	        	episode = "starwars-episode-1-interactions.json";
	        	break;
	        case KeyEvent.VK_2: 
	        	episode = "starwars-episode-2-interactions.json";
	        	break;
	        case KeyEvent.VK_3: 
	        	episode = "starwars-episode-3-interactions.json";
	        	break;
	        case KeyEvent.VK_4: 
	        	episode = "starwars-episode-4-interactions.json";
	        	break;
	        case KeyEvent.VK_5: 
	        	episode = "starwars-episode-5-interactions.json";
	        	break;
	        case KeyEvent.VK_6: 
	        	episode = "starwars-episode-6-interactions.json";
	        	break;
	        case KeyEvent.VK_7: 
	        	episode = "starwars-episode-7-interactions.json";
	        	break;
	        default: 
	        	episode = "starwars-episode-1-interactions.json";
	        	break;
	        }
	       }

	public void draw() {
		this.background(200);
		this.ellipse(650, 325, 550, 550);
		for(Character character : characters){
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

			this.characters.add(new Character(this,name,50+(i/10)*50,50+(i%10)*50));;
		}


	}

}
