/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public abstract class Person {
	
	//Parameters
	public static final double ONE_DAY= 4000.0;
		
	public static final double RADIUS = 10.0;
	public static final double VELOCITY_MAX = 7.0;   //The velocity is in [-VELOCITY_MAX, VELOCITY_MAX]
	
	public static final double INFECT_RADIUS = 20.0;
	public static final double PERCENTAGE_TO_GET_INFECTED = 50.0;		//smileyface
	public static final double PERCENTAGE_TO_RECOVER = 40.0;		//illface
	public static final double PROBABILITY_TO_DIE = 40.0;		//illface
		
	public Vec position;
	public Vec velocity;
	public double timeToBeInfected =0.0;
	public double infectionTime =0.0;
		
		
	public boolean wearMask = false;
	//public LinkedList <Person> inRadiusPeople;	

	
	//Constructor default
	public Person () {
		super();
	}
	
	//Constructor
	public Person (Vec initPosition, Vec initVelocity) {
		position = initPosition;
		velocity = initVelocity;
	}

	public Vec setNewRandomPosition() {
		return new Vec(1100*Math.random()+10,700*Math.random()+40);
	}
	

	//To move a person
	public void movement() {
		position.add(velocity);
	}
	
	//Method to verify if the person is still in the world window
	public boolean outWindow() {
		return ((boolean)(position.x + velocity.x <10) || (boolean)(position.x + velocity.x >1170) 
			|| (boolean)(position.y + velocity.y <40) || (boolean)(position.y + velocity.y >760));
	}
	
	//Method to verify if it is the same person
	public boolean different(Person p) {
		return !((boolean)(this.position.x == p.position.x) && (boolean)(this.position.y == p.position.y));
	}
	
	
	//Method to check if a person gets too close to an infected person
	public boolean getTooClose (Person p) {
		if (!this.different(p)) {
			return false;
		}
		return (boolean)(this.position.dist(p.position) < RADIUS+INFECT_RADIUS);
	}
	
	public abstract Person changeStatus ();
	public abstract Vec setNewRandomVelocity(); 
	public abstract Person hasRecovered ();
	public abstract void drawFaces (Graphics g);
	
}

