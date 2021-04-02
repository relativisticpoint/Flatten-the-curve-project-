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
	
	public SmileyFace (Vec initPosition, Vec initVelocity, int nb){
		super(initPosition,initVelocity, nb);
		
	}
	
	//when a healthy person is infected by virus
	public Person changeStatus (){
		Vec newPosition = this.position;
		Vec newVelocity = this.velocity;
		int nbFamily = this.familyNb;
		IllFace newPatient = new IllFace (newPosition,newVelocity, nbFamily);
		return newPatient;
	}
	
	
	//representation 
	public void drawFaces (Graphics g){
		g.setColor(status);
		super.drawFaces(g);
		if (wearMask) {
			g.setColor(Color.white);
			g.fillRect((int)(position.x-RADIUS) , (int)(position.y-(RADIUS-1)/2),(int)(2*RADIUS), (int)(0.7*RADIUS));
		}
	}
	
	public Person hasRecovered (){
		SmileyFace newRecovered = null;
		return newRecovered;
	}
}


