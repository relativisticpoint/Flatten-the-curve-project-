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
	public static final double ONE_DAY = 4000.0;
	
	public int nbOfPeople;
	public ArrayList<Person> everyone;
	public LinkedList<Person> infectedPeople = new LinkedList<Person>();
	
	//Constructor	
	public Population(int initNb) {
		nbOfPeople = initNb;
		everyone = new ArrayList<Person>();
		
		Person newPerson = new SmileyFace ();
		
		int[] familyMembersNb = this.generateNbOfMembers();
		int count =0;
		int family =1;
		
		//To generate a population with the desired number of people
		while (everyone.size() < nbOfPeople) {						
			newPerson = new SmileyFace (newPerson.setNewRandomPosition(),newPerson.setNewRandomVelocity(), family); 
			  					
			while (this.coincideWhenInitiate(newPerson)) {
				newPerson = new SmileyFace(newPerson.setNewRandomPosition(), newPerson.setNewRandomVelocity(), family);
			}			
			everyone.add (newPerson);
			
			count++;
			
			if (count >= familyMembersNb[family-1]) {
				family ++;
				count = 0;
			}
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
	
	//Method to distribute randomly people to a family consisting of from 2 to 6 people
	public int[] generateNbOfMembers() {
		int[] familyDistribution = new int[20];
		int sum = 0;
		while (sum >70 || sum <50) {
			for (int i =0; i<15; i++) {
				familyDistribution[i] = (int)(5*Math.random())+2;
				sum += familyDistribution[i];
			}
		}

		int difference = 80 -sum;
		int remainder = difference %5;
		for (int i =15; i<15 + remainder; i++) {
			familyDistribution[i] = (int)(difference/5)+1;
		}
		for (int i =15+remainder; i<20; i++) {
			familyDistribution[i] = (int)(difference/5)+1;
		}
		return familyDistribution;
	}
	
	
	//To change everyone's velocity after some time
	public void newVelocity () {
		for (Person a : everyone) {			 
			if (Math.random() <0.5) {
				a.velocity =  a.setNewRandomVelocity();
			}		
		}
	}
	
	//To update the world in general
	public void updateWorld() {
		this.updateWorldInfection(); 
		this.updateWorldMovement();
		
	}
	
	public void updateWorldLockdown() {
		this.updateWorldInfection(); 
		this.updateWorldMovementLockdown();
		
	}
	
	//To start the infection
	public void startTheInfection(double x) {
		if (x == ONE_DAY) {
			for (int i =0; i< (int)(4*Math.random()+2); i++) {
				int nb = (int)(80.0*Math.random());
				everyone.add(everyone.get(nb).changeStatus());
				everyone.remove(nb);
			}
		}
	}
	
	//To update the world with disease	
	public void updateWorldInfection () {
		for(int i = 0; i <everyone.size(); i++) { 
			Person p = everyone.get(i);
			
			//A healthy person may get infected after 3 days
			if (p.timeToBeInfected >0) {
				if (p.timeToBeInfected == 3*ONE_DAY) {
					p.timeToBeInfected = 0.0;
					if (100.0*Math.random() < p.PERCENTAGE_TO_GET_INFECTED) {
						everyone.add(p.changeStatus());
						everyone.remove(p);
					}
				}else{
					p.timeToBeInfected += 100.0;
				}
			}
			
			//An infected person may recover after 7 days or die after 10 days
			if (p instanceof IllFace) {
				if (p.infectionTime < 7*ONE_DAY) {
					p.infectionTime += 100.0;
				}else if (p.infectionTime == 7*ONE_DAY && 100.0*Math.random() > p.PROBABILITY_TO_DIE) {
					
					everyone.add(p.hasRecovered());
					everyone.remove(p);
					p.infectionTime =0.0;
				}else{
					p.infectionTime += 100.0;
					if (p.infectionTime == 10*ONE_DAY) {
						everyone.add(0,p.changeStatus());
						everyone.remove(p);
						p.infectionTime = 0.0;
					}
				}
				
			}
		}
							
		for (Person a : everyone) {
			if (a instanceof IllFace) {
				for (Person b : everyone) {
					if (b instanceof SmileyFace && b.timeToBeInfected == 0.0 && b.getTooClose(a)) {
						b.timeToBeInfected += 100.0;
					}
				}
			}
		}
	}
	
	//To create "the moving population"
	public void updateWorldMovement () {
		int i =0;
		if (!everyone.isEmpty()){					
			for (Person a : everyone) {	
				while (this.movingImpossible(a)) {	

				
					a.velocity = a.setNewRandomVelocity();
					i++;
				}
					
				
				a.movement();
			}
		}
	}
	
	public void updateWorldMovementLockdown () {
		int i =0;
		if (!everyone.isEmpty()){					
			for (Person a : everyone) {
				if (a.lockdownRespect) {
					if (a.distanceFromHome() >a.HOUSE_RADIUS) { 
						a.velocity = a.goHome();	
					}
					
					while (this.movingImpossible(a) || a.goAwayFromHome()) {	
					
						a.velocity = a.setNewRandomVelocity();
						i++;
					}
				}else{
					while (this.movingImpossible(a)) {	
			
						a.velocity = a.setNewRandomVelocity();
						i++;
					}
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
				if (b.different(p) && !(b instanceof DeadFace)) {
					if (b.position.dist(p.position) < 2*b.RADIUS) {
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
		if (p instanceof DeadFace) {
			return false;
		}
		Person p1 = new SmileyFace (new Vec((p.position).x + (p.velocity).x, (p.position).y + (p.velocity).y), new Vec ((p.velocity).x,(p.velocity).y),1);
		return (p.outWindow() || this.coincideAfterMoving(p1));
	}
	
}
	
	
	
