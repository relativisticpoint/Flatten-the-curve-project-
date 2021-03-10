/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
	
	public static final double RADIUS = 10.0;
	public Vec position;
	public Vec velocity;
	public Color status = Color.green;
	public boolean wearMask = false;

	
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
		while (position.x + velocity.x <0 || position.x + velocity.x >1500 || position.y + velocity.y <0 || position.y + velocity.y >1500) {
			velocity = new Vec (20.0*Math.random()-10.0, 20.0*Math.random()-10.0);
		}
		position.add(velocity);
	}
}

