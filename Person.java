/*
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public abstract class Person {
	
	//Parameters
	public static final double ONE_DAY= 4000.0;
		
	public static final double RADIUS = 7.0;
	public static final double VELOCITY_MAX = 4.0;   //The velocity is in [-VELOCITY_MAX, VELOCITY_MAX]
	public static final double HOUSE_RADIUS = 60.0;
	public static final double AREA_RADIUS = 40.0;
	
	public static final double INFECT_RADIUS = 14.0;
	public static final double PROBABILITY_TO_DIE = 30.0;		
		
	public int familyNb;
	public Vec address ;
	public Vec position;
	public Vec velocity;
	public double probabilityToGetInfected = 40.0;	
	public double timeToBeInfected =0.0;
	public double infectionTime =0.0;
	public boolean lockdownRespect;
	public boolean socialDistancingRespect;
		
	public boolean wearMask = false;
	

	
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
		socialDistancingRespect = (boolean)(Math.random() <= 0.90);
	}
	
	public Person (Vec initPosition, Vec initVelocity, int nb, Vec initAddress, boolean ldRespect, boolean sdRespect) {
		position = initPosition;
		velocity = initVelocity;
		familyNb = nb;
		address = initAddress;
		lockdownRespect = ldRespect;
		socialDistancingRespect = sdRespect;
	}

	public Vec setNewRandomPosition() {
		return new Vec(800*Math.random()+20,700*Math.random()+50);
	}
	
	public Vec setNewRandomVelocity() {
		return new Vec(2*VELOCITY_MAX*Math.random()-VELOCITY_MAX,2*VELOCITY_MAX*Math.random()-VELOCITY_MAX);
	}
	
	
	public void setVelocity (Vec v) {
		this.velocity = new Vec(v.x,v.y);
	}

	//To move a person
	public void movement() {
		position.add(velocity);
	}
	
	//Method to verify if the person is still in the world window
	public boolean outWindow() {
		return ((boolean)(position.x + velocity.x <20) || (boolean)(position.x + velocity.x >880) 
			|| (boolean)(position.y + velocity.y <50) || (boolean)(position.y + velocity.y >770));
	}
	
	//Method to verify if it is the same person
	public boolean differentFrom(Person p) {
		return !((boolean)(this.position.x == p.position.x) && (boolean)(this.position.y == p.position.y));
	}
	
	
	//Method to check if a person gets too close to an infected person
	public boolean getTooClose (Person p) {
		if (!this.differentFrom(p) || (p instanceof DeadFace)) {
			return false;
		}
		return (boolean)(this.position.dist(p.position) <= RADIUS+INFECT_RADIUS);
	}
	
	public boolean inTheSameArea (Person p) {
		if (!this.differentFrom(p) || (p instanceof DeadFace)) {
			return false;
		}
		return (boolean)(this.position.dist(p.position) <= (AREA_RADIUS-RADIUS));
	}
	
	public double distanceFromHome() {
		return position.dist(address);
	}
	
	public Vec goHome() {
		Vec direction = new Vec (address.x-position.x, address.y-position.y);
		direction.normalize();
		return new Vec (direction.x*VELOCITY_MAX, direction.y*VELOCITY_MAX);
	}
	
	
	public boolean goAwayFromHome() {
		Vec newPosition = new Vec (position.x + velocity.x, position.y + velocity.y);
		return (boolean)((position.dist(address) < HOUSE_RADIUS) && (newPosition.dist(address) > HOUSE_RADIUS));
	}
	
	public void drawFaces (Graphics g){
		g.fillOval ((int)(position.x-RADIUS), (int)(position.y-RADIUS),(int)(2*RADIUS), (int)(2*RADIUS));
	}
	
	//To draw the "houses" during lockdown
	public void drawHouses (Graphics g) {

		g.setColor(Color.white);
		g.drawRect ((int)(address.x-HOUSE_RADIUS), (int)(address.y-HOUSE_RADIUS), (int)(2*HOUSE_RADIUS), (int)(2*HOUSE_RADIUS));
	}
	
	public abstract Person changeStatus ();
	public abstract Person hasRecovered ();
	
}
