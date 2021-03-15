/*
 * This class is the set of all "Person"
 * This class should be used to create the GUI simulation
 * This is just the basic version to test if it can run
 * To be improved...
 */

import java.util.*;
import java.awt.*;

public class Population {
	
	//Parameters
	
	public int nbOfPeople;
	public ArrayList<Person> everyone;
	public LinkedList<Person> infectedPeople = new LinkedList<Person>();
	
	//Constructor	
	public Population(int initNb) {
		nbOfPeople = initNb;
		everyone = new ArrayList<Person>();
		
		//To make "everyone" not empty
		Person newPerson = new Person ();
		newPerson = new Person (newPerson.setNewRandomPosition(), newPerson.setNewRandomVelocity()); 
		newPerson.changeStatus(Color.red);
		everyone.add (newPerson);
		
		//To generate a population with the desired number of people
		while (everyone.size() < nbOfPeople) {			
			
			newPerson = new Person (newPerson.setNewRandomPosition(),			//If the simulation is 800x800 pixels
			                        newPerson.setNewRandomVelocity());   		//The velocity is in [-10,10]
			
			while (this.coincideWhenInitiate(newPerson)) {
				newPerson = new Person (newPerson.setNewRandomPosition(), newPerson.setNewRandomVelocity());
			}			
			everyone.add (newPerson);
		}
	}
	
	//To verify all people are not "spawn on top of each other"
	public boolean coincideWhenInitiate(Person p) {
		if (!everyone.isEmpty()){
			for (Person b : everyone) {
				if (b.different(p)) {
					if (b.position.dist(p.position) <= 2*b.RADIUS) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//To update the world in general
	public void updateWorld() {
		this.updateWorldInfection(); 
		this.updateWorldMovement();
		
	}
	
	//To spread the disease
	
	public void updateWorldInfection () {
		for (Person a : everyone) {
			if (a.status == Color.red && a.infectionSourceIfRed) {
				for (Person b : everyone) {
					if (b.status == Color.green && b.infectionSourceIfRed && b.getTooClose(a) && b.different(a)) {
						b.infectionSourceIfRed = false;
						if (100.0*Math.random() > b.PERCENTAGE_TO_GET_INFECTED) {
							b.changeStatus(Color.red);
						}
					}
				}
			}
		}

	}
	
	//To create "the moving population"
	public void updateWorldMovement () {
		if (!everyone.isEmpty()){					
			for (Person a : everyone) {
						
				while (this.movingImpossible(a)) {	
					a.changeVelocity();
				}
				
				a.movement();
			}
		}
	}
					
	
	//To verify all people do not "step onto each other" after moving
	public boolean coincideAfterMoving(Person p) {
		int count =0;
		if (!everyone.isEmpty()){
			for (Person b : everyone) {
				if (b.different(p)) {
					if (b.position.dist(p.position) <= 2*b.RADIUS) {
						count++;
						if (count==2) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean movingImpossible (Person p) {
		
		Person p1 = new Person (new Vec((p.position).x + (p.velocity).x, (p.position).y + (p.velocity).y), new Vec ((p.velocity).x,(p.velocity).y));
		return (p.outWindow() || this.coincideAfterMoving(p1));
	}
	
}
	
	
	
