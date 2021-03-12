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
	
	//Constructor	
	public Population(int initNb) {
		nbOfPeople = initNb;
		everyone = new ArrayList<Person>();
		
		//To make "everyone" not empty
		Person newPerson = new Person ();
		newPerson = new Person (newPerson.setNewRandomPosition(), newPerson.setNewRandomVelocity()); 
		everyone.add (newPerson);
		
		//To generate a population with the desired number of people
		while (everyone.size() < nbOfPeople) {			
			
			newPerson = new Person (newPerson.setNewRandomPosition(),				 //If the simulation is 800x800 pixels
			                        newPerson.setNewRandomVelocity());   			//The velocity is in [-10,10]
			
			while (this.coincideWhenInitiate(newPerson)) {
				newPerson = new Person (newPerson.setNewRandomPosition(), newPerson.setNewRandomVelocity());
			}			
			everyone.add (newPerson);
		}
	}
	
	//To create "the moving population"
	public void updateWorld() {
		
		if (!everyone.isEmpty()){
			for (Person a : everyone) {
				int count =0;
		
				while (this.movingImpossible(a)) {	
					a.changeVelocity();
					count++;
					if (count ==500) {
						break;
					}
				}
				a.movement();
			}
		}
	}
	
	//To verify all people are not "spawn on to each other"
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
		if (!everyone.isEmpty()) {
			if (p.outWindow()) {
				return true;
			}
		}
		
		Person p1 = new Person (new Vec((p.position).x + (p.velocity).x, (p.position).y + (p.velocity).y), new Vec ((p.velocity).x,(p.velocity).y));
		return this.coincideAfterMoving(p1);
	}
	
}
	
	
	
