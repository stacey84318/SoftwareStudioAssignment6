package main.java;

import java.awt.event.KeyEvent;
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
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private String file1 = "starwars-episode-1-interactions.json";
	private String file2 = "starwars-episode-2-interactions.json";
	private String file3 = "starwars-episode-3-interactions.json";
	private String file4 = "starwars-episode-4-interactions.json";
	private String file5 = "starwars-episode-5-interactions.json";
	private String file6 = "starwars-episode-5-interactions.json";
	private String file7 = "starwars-episode-5-interactions.json";

	int episode=0;
	
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
	 public void keyPressed(KeyEvent e){
	        switch(e.getKeyCode()){
	        case KeyEvent.VK_1: 
	        	episode = 1;
	        	break;
	        case KeyEvent.VK_2: 
	        	episode = 2;
	        	break;
	        case KeyEvent.VK_3: 
	        	episode = 3;
	        	break;
	        case KeyEvent.VK_4: 
	        	episode = 4;
	        	break;
	        case KeyEvent.VK_5: 
	        	episode = 5;
	        	break;
	        case KeyEvent.VK_6: 
	        	episode = 6;
	        	break;
	        case KeyEvent.VK_7: 
	        	episode = 7;
	        	break;
	        default: 
	        	episode = 1;
	        	break;
	        }
	       }

	public void draw() {
		this.background(255);
		this.ellipse(650, 325, 550, 550);
		
	}

	private void loadData(){
		if(episode!=0)
			this.data = loadJSONObject("file"+episode);
		this.nodes = data.getJSONArray("nodes");
		this.links = data.getJSONArray("links");
		for(int i = 0 ;i<nodes.size();i++){
			JSONObject N = nodes.getJSONObject(i);
			int value = N.getInt("value");
			String name = N.getString("name");
			String color = N.getString("colour");
			System.out.println(name+","+value+","+color);
			this.characters.add(new Character(this,name,(i/10)*20,i*10));;
		}

	}

}
