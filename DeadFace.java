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
	
	public DeadFace (Vec initPosition, Vec initVelocity, int nb){
		super(initPosition, initVelocity, nb);
		this.velocity = new Vec(0.0,0.0);
		this.lockdownRespect = false;
		this.socialDistancingRespect = false;
	}	

	public Vec setNewRandomVelocity() {
		return new Vec(0.0,0.0);
	}
	
	public Vec goHome() {
		return new Vec (0.0,0.0);
	}
	
	public double distanceFromHome() {
		return 0.0;
	}
	
	public boolean goAwayFromHome() {
		return false;
	}
	
	public void drawFaces (Graphics g){
		g.setColor(status);
		super.drawFaces(g);
	}
	
	public Person hasRecovered (){
		DeadFace newRecovered = null;
		return newRecovered;
	}
	
	public Person changeStatus(){
		DeadFace newDeath = null;
		return newDeath;
	}
	
}


