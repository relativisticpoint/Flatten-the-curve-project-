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
		while (everyone.size() < nbOfPeople) {
			Person newPerson = new Person (new Vec(1150*Math.random(),1150*Math.random()),				//If the simulation is 1200x1200 pixels
			                               new Vec(20.0*Math.random()-10.0,20.0*Math.random()-10.0));   //The velocity is in [-10,10]
			everyone.add (newPerson);
		}
	}
	
	//To create "the moving population"
	public void newWorld() {
		if (!everyone.isEmpty()){
			for (Person a : everyone) {
				a.movement();
			}
		}
	}
	
	//To verify all people do not "step onto each other"
	public boolean notCoincide(Person p) {
		if (!everyone.isEmpty()){
			for (Person b : everyone) {
				if (b.different(p)) {
					if (b.position.dist(p.position) <= 2*b.RADIUS) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
	
	
	
