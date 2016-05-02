package main.java;

import java.util.ArrayList;

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
	private String file = "starwars-episode-1-interactions.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		size(width, height);
		characters = new ArrayList<Character>();	
		smooth();
		loadData();
		this.addMouseMotionListener(this);
	}
	
	public void mousePressed(MouseEvent e) {
		    
	}

	public void mouseReleased(MouseEvent e) {
		   
	}
	
	public void mouseDragged(MouseEvent e) {
	    
	}

	public void draw() {
		this.background(255);
		this.ellipse(650, 325, 550, 550);
		
	}

	private void loadData(){

	}

}
