/*
 * To be improved...
*/

import java.util.*;
import java.awt.*;

public class Person {
	
	//Parameters
	public static final double ONE_DAY= 4000.0;
		
	public static final double RADIUS = 7.0;
	public static final double VELOCITY_MAX = 4.0;   //The velocity is in [-VELOCITY_MAX, VELOCITY_MAX]
	public static final double HOUSE_RADIUS = 60.0;
	public static final double AREA_RADIUS = 40.0;
	
	public static final double INFECT_RADIUS = 16.0;
	public static final double PROBABILITY_TO_DIE = 20.0;		
		
	public int familyNb;
	public Vec address ;
	public Vec position;
	public Vec velocity;
	public double probabilityToGetInfected;	  //The initial probability should be 0.5 from the construction of the population
	
	public double timeToBeInfected =0.0;
	public double infectionTime =0.0;
	public double washHandsTime = 0.0;
	
	public boolean lockdownRespect;
	public boolean socialDistancingRespect;
		
	public boolean wearMask;
	public boolean vaccinated; 
	
	//Constructor default
	public Person () {
		super();
	}
	
	//Constructor
	public Person (Vec initPosition, Vec initVelocity, int nb, double probToGetInfected, boolean mask, boolean vaccine) {
		position = initPosition;
		velocity = initVelocity;
		familyNb = nb;
		address = new Vec(90+((familyNb+4)%5)*180,100+(int)((familyNb-1)/5)*200);
		probabilityToGetInfected = probToGetInfected;
		wearMask = mask;
		vaccinated = vaccine;
		
		lockdownRespect = (boolean)(Math.random() <= 0.90);
		socialDistancingRespect = (boolean)(Math.random() <= 0.60);
	}
	
	public Person (Vec initPosition, Vec initVelocity, int nb, Vec initAddress, boolean ldRespect, boolean sdRespect) {
		position = initPosition;
		velocity = initVelocity;
		familyNb = nb;
		address = initAddress;
		
	}

	public Vec setNewRandomPosition() {
		return new Vec(800*Math.random()+20,700*Math.random()+50);
	}
	
	public Vec setNewRandomVelocity() {
		return new Vec(2*VELOCITY_MAX*Math.random()-VELOCITY_MAX,2*VELOCITY_MAX*Math.random()-VELOCITY_MAX);
	}

	//To move a person
	public void movement(boolean boo) {
		if (boo) {
			position.add(velocity);
		}
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
	
	public SmileyFace hasRecovered() {
		return new SmileyFace (this.position, this.velocity, this.familyNb, this.probabilityToGetInfected, this.wearMask, this.vaccinated);
	}
	
	public IllFace getInfected() {
		return new IllFace (this.position, this.velocity, this.familyNb, this.probabilityToGetInfected, this.wearMask, this.vaccinated);
	}
	
	public DeadFace die() {
		return new DeadFace (this.position, this.velocity, this.familyNb, this.probabilityToGetInfected, this.wearMask, this.vaccinated);
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
	
	public void drawFaces (Graphics g, boolean socialDist){
		g.setColor(Color.black);
		g.drawOval ((int)(position.x-RADIUS), (int)(position.y-RADIUS),(int)(2*RADIUS), (int)(2*RADIUS));
	}
	
	//To draw the "houses" during lockdown
	public void drawHouses (Graphics g) {
		Vec rooftop = new Vec (address.x, address.y-HOUSE_RADIUS - 30.0);
		
		g.setColor(Color.black);
		g.drawRect ((int)(address.x-HOUSE_RADIUS), (int)(address.y-HOUSE_RADIUS), (int)(2*HOUSE_RADIUS), (int)(2*HOUSE_RADIUS));
		g.drawLine ((int)(address.x-HOUSE_RADIUS), (int)(address.y-HOUSE_RADIUS), (int)(rooftop.x), (int)(rooftop.y));
		g.drawLine ((int)(rooftop.x), (int)(rooftop.y), (int)(address.x+HOUSE_RADIUS), (int)(address.y-HOUSE_RADIUS));
		
	}
	
	//To draw the bubbles when a person washes her/his hands
	public void washHands (Graphics g){
		washHandsTime =0.0;
		if (washHandsTime < 0.5*ONE_DAY) {
			g.setColor(Color.white);
			g.fillOval ((int)(position.x+20), (int)(position.y+20),2,2);
			g.fillOval ((int)(position.x+20), (int)(position.y),5,5);
			g.fillOval ((int) (position.x-15), (int)(position.y)-15,3,3);
			g.fillOval ((int) (position.x), (int)(position.y)-15,4,4);
			g.fillOval ((int) (position.x-15), (int)(position.y)+15,5,5);
			g.fillOval ((int) (position.x+20), (int)(position.y)-15,5,5);
		}
		if (this.vaccinated) {
			probabilityToGetInfected = 1.0;
		}else if (this.wearMask) {
			probabilityToGetInfected = 10.0;
		}else{
			probabilityToGetInfected = 40.0;
		}
	}
	
	
}
