/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
	
	double positionX;
	double positionY;
	Color status = Color.green;
	boolean wearMask = false;
	
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
