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
	public Person hasRecovered (){
		Vec newPos = this.position;
		Vec newVelo = this.velocity;
		SmileyFace newRecovered = new SmileyFace (newPos,newVelo);
		return newRecovered;
	}
	//representation 
	public void drawFaces (Graphics g){
		g.setColor(status);
		g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
	}
}


