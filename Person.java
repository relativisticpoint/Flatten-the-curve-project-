/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
	
	public double positionX;
	public double positionY;
	public double velocity;
	public Color status = Color.green;
	public boolean wearMask = false;

	
	// Constructor
	public Person (double initialX, double initialY) {
		positionX = initialX;
		positionY = initialY;
	}
		
	// To change the status of a person
	public void changeStatus (Color newStatus) {
		status = newStatus;
	}

}
