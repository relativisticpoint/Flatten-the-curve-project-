/*
 * This class is the set of all "Person"
 * To be improved...
 */

import java.util.*;
import java.awt.*;

public class Population {
	
	//Parameters
	public static final double ONE_DAY = 4000.0;
	public static final int NB_LIMIT = 4;
	public static final double AREA_RADIUS = 40;
	
	public int nbOfPeople;
	public ArrayList<Person> everyone;
	public LinkedList<Person> infectedPeople = new LinkedList<Person>();
	public LinkedList<Person> deadPeople = new LinkedList<Person>();
	
	public boolean maskOn = false;
	public boolean activateSocialDistancing = false;
	public boolean activateLockdown = false;
	
	
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
	
	
	//To verify all people are not "spawn on top of each other"
	public boolean coincideWhenInitiate(Person p) {
		if (!everyone.isEmpty()){
			for (Person b : everyone) {
				if (b.differentFrom(p)) {
					if (b.position.dist(p.position) <= 2*b.RADIUS) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	//To change everyone's velocity after some time
	public void changeVelocity () {
		for (Person a : everyone) {			 
			if (Math.random() <0.5) {
				a.velocity =  a.setNewRandomVelocity();
			}		
		}
	}
	
	
	//To start the infection
	public void startTheInfection(double x) {
		if (x == ONE_DAY) {
			for (int i =0; i< (int)(4*Math.random()+2); i++) {
				int nb = 100;
				while (nb >=70) {
					nb = (int)(80.0*Math.random());
				}
				everyone.add(everyone.get(nb).changeStatus());
				everyone.remove(nb);
				infectedPeople.add(everyone.get(nb).changeStatus()); 
			}
		}
	}
	
	//To update the world with disease	
	public void updateWorldInfection () {
		for(int i = 0; i <everyone.size(); i++) { 
			Person p = everyone.get(i);
			
			//A healthy person may get infected after 2 days
			if (p.timeToBeInfected >0) {
				if (p.timeToBeInfected == 2*ONE_DAY) {
					p.timeToBeInfected = 0.0;
					if (100.0*Math.random() < p.probabilityToGetInfected) {
						everyone.add(p.changeStatus());
						everyone.remove(p);
						infectedPeople.add(p.changeStatus()) ;
					}
				}else{
					p.timeToBeInfected += 100.0;
				}
			}
			
			//An infected person may recover after 5 days or die after 7 days
			if (p instanceof IllFace) {
				if (p.infectionTime < 5*ONE_DAY) {
					p.infectionTime += 100.0;
				}else if (p.infectionTime == 7*ONE_DAY && 100.0*Math.random() > p.PROBABILITY_TO_DIE) {
					
					everyone.add(p.hasRecovered());
					everyone.remove(p);
					p.infectionTime =0.0;
					infectedPeople.remove(0) ;
					
				}else{
					p.infectionTime += 100.0;
					if (p.infectionTime == 7*ONE_DAY) {
						everyone.add(0,p.changeStatus());
						everyone.remove(p);
						p.infectionTime = 0.0;
						infectedPeople.remove(0);
						deadPeople.add(p);
					}
				}
				
			}
		}
		
		//A healthy person may get infected if getting close to an infected person					
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
	
	
	//To update the world in general
	public void updateWorld() {
		this.updateWorldInfection(); 
		this.updateWorldMovement();		
	}
	
	
	//To create "the moving population"
	public void updateWorldMovement () {		
		if (!everyone.isEmpty()){					
			for (Person a : everyone) {								
				getMoving(a);
			}
		}
	}
	
	
	public void getMoving (Person a) {
		if (maskOn && !activateLockdown) {
			a.wearMask = true;
		}
		
		while (this.movingImpossible(a)) {				
			a.velocity = a.setNewRandomVelocity();					
		}
	
		a.movement();
	}
	
	
	//To verify all people do not "step onto each other" after moving
	public boolean coincideAfterMoving(Person p) {
		int count =0;
		if (!everyone.isEmpty()){
			for (Person b : everyone) {
				if (b.differentFrom(p) && !(b instanceof DeadFace)) {
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
	
	
	//To update the world while social distancing is on
	public void updateWorldSocialDistancing() {
		this.updateWorldInfection(); 
		this.updateWorldMovementSocialDistancing();
	}
	
	
	//To create a "moving population" while social distancing is on
	public void updateWorldMovementSocialDistancing () {
		if (!everyone.isEmpty()){					
			for (Person a : everyone) {	
				if (a.socialDistancingRespect) {
					getMovingSocialDistancing(a,true);				
				}else{
					getMoving(a);
				}
				
			}
		}
	}
	
	public void getMovingSocialDistancing (Person a, boolean bo) {
		int count =0;
		while (this.socialDistancingImpossible(a,bo)) {					
			a.velocity = a.setNewRandomVelocity();
			count++;
			if (count > 200) {
				break;
			}					
		}
		a.movement();
	}
	
	//To verify that people are not too close to each other
	public boolean noRespectSocialDistancing (Person p) {
		int count =0;
		if (!everyone.isEmpty()){
			for (Person b : everyone) {
				
				if (p.getTooClose(b)) {
					count++;
					if (count==2) {
						return true;
					}
				}
				
				
			}
			
		}
		return false;
	}
	
	public boolean noRespectNbLimit (Person p) {
		
		int count =0;
		for (Person b : everyone) {
			if (p.position.dist(b.position) < (p.AREA_RADIUS-p.RADIUS) && !(b instanceof DeadFace)) {
				count++;
				if (count ==6) {
					return true;
				}
			}
		}
			
		
		return false;
	}
	
	public boolean socialDistancingImpossible (Person p, boolean bo) {
		if (p instanceof DeadFace) {
			return false;
		}
		Person p1 = new SmileyFace (new Vec((p.position).x + (p.velocity).x, (p.position).y + (p.velocity).y), new Vec ((p.velocity).x,(p.velocity).y),1);
		
		if (bo) {
			return (p.outWindow() || this.noRespectSocialDistancing(p1) || this.noRespectNbLimit(p1));
		}
		return (p.outWindow() || this.noRespectSocialDistancing(p1));
	}
	
	
	//To update world while lockdown is on
	public void updateWorldLockdown() {
		this.updateWorldInfection(); 
		this.updateWorldMovementLockdown();		
	}
	
	
	//To create a "moving population" during the lockdown
	public void updateWorldMovementLockdown () {	
		if (!everyone.isEmpty()){					
			for (Person a : everyone) {
				if (a.lockdownRespect) {
					if (a.distanceFromHome() >(a.HOUSE_RADIUS - a.RADIUS)) { 					
						a.velocity = a.goHome();																			
					}
					
					if (a.distanceFromHome() <= (a.HOUSE_RADIUS - a.RADIUS)) {
						a.wearMask = false;
					}
					
					if (a.socialDistancingRespect && a.distanceFromHome() >(a.HOUSE_RADIUS-a.RADIUS)) {
						getMovingSocialDistancing(a,false);
					}else{
						getMoving(a);
					}
				}else{
					getMoving(a);
				}				
			}
		}
	}
	
	
	//To provide the people with masks
	public void toWearMask () {
		for (Person a : everyone) {
			if (maskOn) {
				a.wearMask = true;  //Give masks for all people
				a.probabilityToGetInfected = 15.0;
			}else{
				a.wearMask = false;   //Take off the mask
				a.probabilityToGetInfected = 40.0;
			}
		}
	}
		
		
}
	
	
	
