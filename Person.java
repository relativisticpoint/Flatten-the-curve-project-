/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
	
	
	public Vec velocity;
	public Vec position;
	public Color status = Color.green;
	public boolean wearMask = false;

	
	// Constructor
	public Person (Vec Position, Vec Velocity) {
		position = Position;
		velocity = Velocity;
	}
		
	// To change the status of a person
	public void changeStatus (Color newStatus) {
		status = newStatus;
	}
	//to move 
	public void movement(){
		position.add(velocity);
	}
	

}

