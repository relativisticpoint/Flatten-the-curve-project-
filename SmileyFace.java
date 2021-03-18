// Draw a Smiley Face representing a healthy person

import java.awt.*; 
import java.awt.Color;
import javax.swing.*;


public class SmileyFace extends Person{ 
	
	//attributes
	private Color status = Color.green;
	
	//constructor
	public SmileyFace (){
		super();
	}
	
	public SmileyFace (Vec initPosition, Vec initVelocity){
		super(initPosition,initVelocity);
		
	}
	
	//when a healthy person is infected by virus
	public Person changeStatus (){
		Vec newPosition = this.position;
		Vec newVelocity = this.velocity;
		IllFace newPatient = new IllFace (newPosition,newVelocity);
		return newPatient;
	}
	
	public Vec setNewRandomVelocity() {
		return new Vec(2*VELOCITY_MAX*Math.random()-VELOCITY_MAX,2*VELOCITY_MAX*Math.random()-VELOCITY_MAX);
	}
	
	//representation 
	public void drawFaces (Graphics g){
		g.setColor(status);
		g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
	}
	
	public Person hasRecovered (){
		SmileyFace newRecovered = null;
		return newRecovered;
	}
}


