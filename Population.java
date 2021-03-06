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
	
	public int nbOfPeople = 100;
	public ArrayList<Person> everyone;
	
	//Constructor	
	public Population() {
		everyone = new ArrayList<Person>();
		while (everyone.size() < nbOfPeople) {
			Person newPerson = new Person (500*Math.random(), 500*Math.random());  //If the simulation is 500x500 pixels
			everyone.add (newPerson);
		}
	}
		
}
	
	
	
