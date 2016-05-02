package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name;
	public float x, y, radius;
	private ArrayList<Character> targets  = new ArrayList<Character>();

	public Character(MainApplet parent, String name, float x, float y){

		this.parent = parent;
		this.name=name;
		this.x=x;
		this.y=y;
		
	}

	public void display(){
		parent.text(name, x, y);
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
	
}
