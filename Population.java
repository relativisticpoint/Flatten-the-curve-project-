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
		Person newPerson = new Person (new Vec(700*Math.random(),700*Math.random()), new Vec(20.0*Math.random()-10.0,20.0*Math.random()-10.0)); 
		everyone.add (newPerson);
		
		//To generate a population with the desired number of people
		while (everyone.size() < nbOfPeople) {			
			
			newPerson = new Person (new Vec(700*Math.random(),700*Math.random()),				 //If the simulation is 1200x1200 pixels
			                        new Vec(20.0*Math.random()-10.0,20.0*Math.random()-10.0));   //The velocity is in [-10,10]
			
			while (this.coincideWhenInitiate(newPerson)) {
				newPerson = new Person (new Vec(1150*Math.random(),1150*Math.random()),new Vec(20.0*Math.random()-10.0,20.0*Math.random()-10.0));
			}			
			everyone.add (newPerson);
		}
	}
	
	//To create "the moving population"
	public void newWorld() {
		
		if (!everyone.isEmpty()){
			for (Person a : everyone) {
				int count =0;
		
				while (this.movingImpossible(a)) {	
					a.velocity = new Vec(20.0*Math.random()-10.0,20.0*Math.random()-10.0);
					//System.out.println(count);
					count++;
					if (count ==300) {
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
	
	public void changeVelocity () {
		for (Person a : everyone) {
			a.velocity = new Vec(20.0*Math.random()-10.0,20.0*Math.random()-10.0);
		}
	}
}
	
	
	
