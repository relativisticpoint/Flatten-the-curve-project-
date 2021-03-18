// Draw an IllFace Face representing an infected person

import java.awt.*; 
import java.awt.Color;
import javax.swing.*;


public class IllFace extends Person { 
	//attributes
	private Color status = Color.red;
	
	private int INFECT_RADIUS = 20;
	
	//constructor
	public IllFace (){
		super();
	}
	
	public IllFace (Vec initPosition, Vec initVelocity){
		super(initPosition, initVelocity);
	}
	
	//when a patient dies
	public Person changeStatus (){
		Vec newPosition = this.position;
		Vec nilVelo = new Vec (0.0,0.0);
		DeadFace newDeath = new DeadFace (newPosition,nilVelo);
		return newDeath;
	}
	
	//when a patient has recovered
	public Person hasRecovered (){
		Vec newPos = this.position;
		Vec newVelo = this.velocity;
		SmileyFace newRecovered = new SmileyFace (newPos,newVelo);
		return newRecovered;
	}
	
	public Vec setNewRandomVelocity() {
		return new Vec(2*VELOCITY_MAX*Math.random()-VELOCITY_MAX,2*VELOCITY_MAX*Math.random()-VELOCITY_MAX);
	}
	
	//representation	
	public void drawFaces (Graphics g){
		g.setColor(status);
		g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
		//g.drawOval ((int)(position.x-INFECT_RADIUS), (int)(position.y-INFECT_RADIUS),(int)(2*(RADIUS+INFECT_RADIUS)), (int)(2*(RADIUS+INFECT_RADIUS)));
	}
}


