/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
		
	public static final double RADIUS = 10.0;
	public static final double INFECT_RADIUS = 30.0;
	public static final double VELOCITY_MAX = 10.0;
	public Vec position;
	public Vec velocity;
	public Color status = Color.green;
	public boolean wearMask = false;


	
	//Constructor default
	public Person () {
		super();
	}
	
	//Constructor
	public Person (Vec initPosition, Vec initVelocity) {
		position = initPosition;
		velocity = initVelocity;
	}
		
	//To change the status of a person
	public void changeStatus (Color newStatus) {
		status = newStatus;
	}
	
	//To move a person
	public void movement() {
		position.add(velocity);
	}
	
	//Method to verify if the person is still in the world window
	public boolean outWindow() {
		return ((boolean)(position.x + velocity.x <10) || (boolean)(position.x + velocity.x >1170) 
			|| (boolean)(position.y + velocity.y <40) || (boolean)(position.y + velocity.y >770));
	}
	
	//Method to verify if it is the same person
	public boolean different(Person p) {
		return !((boolean)(this.position.x == p.position.x) && (boolean)(this.position.y == p.position.y));
	}
	
	public void changeVelocity() {
		velocity = this.setNewRandomVelocity();
	}
	
	public Vec setNewRandomVelocity() {
		return new Vec(2*VELOCITY_MAX*Math.random()-VELOCITY_MAX,2*VELOCITY_MAX*Math.random()-VELOCITY_MAX);
	}
	
	public Vec setNewRandomPosition() {
		return new Vec(1100*Math.random(),700*Math.random());
	}
	
	public void drawFaces (Graphics g) {
		g.setColor (status);
		if (status == Color.green) {
			g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
		}
		if (status == Color.red) {
			g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
			g.drawOval ((int)(position.x), (int)(position.y),(int)(2*(RADIUS+INFECT_RADIUS)), (int)(2*(RADIUS+INFECT_RADIUS)));
		}			
	}
	
	
}

