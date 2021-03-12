/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
		
	public static final double RADIUS = 10.0;
	public static final double INFECT_RADIUS = 40.0;
	public Vec position;
	public Vec velocity;
	public Color status = Color.green;
	public boolean wearMask = false;
	//private APoint centre; //center of the point

	
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
		return ((boolean)(position.x + velocity.x <0) || (boolean)(position.x + velocity.x >650) 
			|| (boolean)(position.y + velocity.y <0) || (boolean)(position.y + velocity.y >650));
	}
	
	// Method to verify if it is the same person
	public boolean different(Person p) {
		return !((boolean)(this.position.x == p.position.x) && (boolean)(this.position.y == p.position.y));
	}
	
	public void drawFaces (Graphics g) {
		g.setColor (status);
		g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
	}
	
}

