package main.java;

import java.awt.Font;
import java.util.ArrayList;
import processing.event.*;
import processing.core.PApplet;
import processing.event.MouseEvent;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name;
	public float x, y, radius;
	boolean showName=false;
	private String color;
	private int r,g,b,d;
	private ArrayList<Character> targets  = new ArrayList<Character>();

	public Character(MainApplet parent, String name, float x, float y,String color){

		this.parent = parent;
		this.name=name;
		this.x=x;
		this.y=y;
		this.color = color;
		this.d = Integer.parseInt(color.substring(1, 3),16);
		System.out.println(d);
		this.r = Integer.valueOf(color.substring(3, 5),16);
		System.out.println(r);
		this.g = Integer.valueOf(color.substring(5, 7),16);
		System.out.println(g);
		this.b = Integer.valueOf(color.substring(7, 9),16);
		System.out.println(b);
		
		
	}

	public void display(){
			parent.fill(r,g,b,d);
			parent.stroke(r,g,b,d);
		
		parent.ellipse(x, y, 30, 30);
		parent.fill(0);
		//Font f =new Font(Font.DIALOG_INPUT, Font.ITALIC, 30);
		//parent.setFont(f);
		if(showName){
			//parent.fill(200);
			//parent.rectMode(3);
			//parent.rect(x, y-20, 50, 30);
			//parent.fill(255);
			parent.text(name, x+10, y+5,30);}
		parent.fill(255);
		
		/*int i;
		for(i=0; i<targets.size(); i++)
			parent.line(x, y, targets.get(i).x, targets.get(i).y);
		*/
	}
	


	public void addTarget(Character target){
		targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}

	public void setLocation(int x, int y){
		this.x=x;
		this.y=y;
	}
	
}
