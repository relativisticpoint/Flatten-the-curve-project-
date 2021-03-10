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
			Person newPerson = new Person (new Vec(1450*Math.random(),1450*Math.random()),
			                               new Vec(20.0*Math.random()-10.0,20.0*Math.random()-10.0));  //If the simulation is 1500x1500 pixels
			everyone.add (newPerson);
		}
	}
	
	//To create "the moving population"
	public void newWorld() {
		for (Person a : everyone) {
			a.movement();
		}
	}
		
}
	
	
	
