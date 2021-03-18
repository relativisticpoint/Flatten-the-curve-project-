// Draw a Dead Face

import java.awt.*; 
import java.awt.Color;
import javax.swing.*;


public class DeadFace extends Person { 
	//attributes
	private Color status = Color.black;
	
	//constructor
	public DeadFace (){
		super();
	}
	public DeadFace (Vec initPosition, Vec initVelocity){
		super(initPosition, initVelocity);
	}
	public Person changeStatus (){
		Vec newPosition = this.position;
		Vec newVelocity = new Vec(0.0,0.0);
		DeadFace newDeath = new DeadFace (newPosition,newVelocity);
		return newDeath;
	}
	public Person hasRecovered (){
		Vec newPos = this.position;
		Vec newVelo = this.velocity;
		SmileyFace newRecovered = new SmileyFace (newPos,newVelo);
		return newRecovered;
	}
	public void drawFaces (Graphics g){
		g.setColor(status);
		g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
	}
}


