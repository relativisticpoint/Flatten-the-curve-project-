import java.util.*;
import java.awt.*;

public class Person {
	
	double positionX;
	double positionY;
	Color status = Color.green;
	boolean wearMask = false;
	
	public Person (double initialX, double initialY) {
		positionX = initialX;
		positionY = initialY;
	}
	
	public void changeStatus (Color newStatus) {
		status = newStatus;
	}

}
