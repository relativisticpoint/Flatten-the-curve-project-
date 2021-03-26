/*
 * This is just the basic version
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public abstract class Person {
	
	//Parameters
	public static final double ONE_DAY= 4000.0;
		
	public static final double RADIUS = 7.0;
	public static final double VELOCITY_MAX = 5.0;   //The velocity is in [-VELOCITY_MAX, VELOCITY_MAX]
	public static final double HOUSE_RADIUS = 50.0;
	
	public static final double INFECT_RADIUS = 14.0;
	public static final double PERCENTAGE_TO_GET_INFECTED = 50.0;		//smileyface
	public static final double PROBABILITY_TO_DIE = 40.0;		//illface
		
	public int familyNb;
	public Vec address ;
	public Vec position;
	public Vec velocity;
	public double timeToBeInfected =0.0;
	public double infectionTime =0.0;
	public boolean lockdownRespect;
		
		
	public boolean wearMask = false;
	//public LinkedList <Person> inRadiusPeople;	

	
	//Constructor default
	public Person () {
		super();
	}
	
	//Constructor
	public Person (Vec initPosition, Vec initVelocity, int nb) {
		position = initPosition;
		velocity = initVelocity;
		familyNb = nb;
		address = new Vec(90+((familyNb+4)%5)*180,100+(int)((familyNb-1)/5)*200);
		lockdownRespect = (boolean)(Math.random() <= 0.90);
	}

	public Vec setNewRandomPosition() {
		return new Vec(800*Math.random()+10,700*Math.random()+40);
	}
	

	//To move a person
	public void movement() {
		position.add(velocity);
	}
	
	//Method to verify if the person is still in the world window
	public boolean outWindow() {
		return ((boolean)(position.x + velocity.x <10) || (boolean)(position.x + velocity.x >870) 
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
	
	
	public Vec setNewRandomVelocity() {
		return new Vec(2*VELOCITY_MAX*Math.random()-VELOCITY_MAX,2*VELOCITY_MAX*Math.random()-VELOCITY_MAX);
	}
	
	public Vec goHome() {
		Vec direction = new Vec (address.x-position.x, address.y-position.y);
		direction.normalize();
		return new Vec (direction.x*VELOCITY_MAX, direction.y*VELOCITY_MAX);
	}
	
	public double distanceFromHome() {
		return position.dist(address);
	}
	
	
	public boolean goAwayFromHome() {
		Vec newPosition = new Vec (position.x + velocity.x, position.y + velocity.y);
		return (boolean)((position.dist(address) < HOUSE_RADIUS) && (newPosition.dist(address) > HOUSE_RADIUS));
	}
	
	public void drawFaces (Graphics g){
		g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
	}
	
	public abstract Person changeStatus ();
	public abstract Person hasRecovered ();
	
}

