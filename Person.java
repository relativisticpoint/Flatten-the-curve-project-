/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
		
	public static final int RADIUS = 10;
	public static final double INFECT_RADIUS = 40.0;
	public Vec position;
	public Vec velocity;
	public Color status = Color.green;
	public boolean wearMask = false;
	private APoint centre; //center of the point
	

	
	// Constructor
	public Person (Vec initPosition, Vec initVelocity) {
		position = initPosition;
		velocity = initVelocity;
	}
		
	// To change the status of a person
	public void changeStatus (Color newStatus) {
		status = newStatus;
	}
	
	// To move a person
	public void movement() {
		position.add(velocity);
	}
	
	// Method to verify if the person is still in the world window
	public boolean outWindow() {
		return ((boolean)(position.x + velocity.x <0) || (boolean)(position.x + velocity.x >1180) 
			|| (boolean)(position.y + velocity.y <0) || (boolean)(position.y + velocity.y >1180));
	}
	
	// Method to verify if it is the same person
	public boolean different(Person p) {
		return !((boolean)(this.position.x == p.position.x) && (boolean)(this.position.y == p.position.y));
	}
	
	public void drawFaces (Graphics g){
		g.setColor(status);
		int x = (int) centre.x;
		int y = (int) centre.y;
		g.fillOval (x,y,2*this.RADIUS, 2*this.RADIUS);
	}
	
}

