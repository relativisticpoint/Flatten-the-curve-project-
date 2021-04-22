import java.util.*;
import java.awt.*;

public class Population {
	
	public static final int NB_LIMIT = 5;
	
	public int nbVaccinated =0;
	public double percentageVaccinated = 0.0;
	public ArrayList<Person> everyone;
	public LinkedList<Person> infectedPeople = new LinkedList<Person>();
	public LinkedList<Person> deadPeople = new LinkedList<Person>();
	
	public boolean maskOn = false;
	public boolean activateSocialDistancing = false;
	public boolean activateLockdown = false;
	
	
	public Population(int initNb) {
		everyone = new ArrayList<Person>();		
		Person newPerson = new SmileyFace ();		
		int[] familyMembersNb = this.generateNbOfMembers();
		int count =0;
		int family =1;
		
		while (everyone.size() < initNb) {						
			newPerson = new SmileyFace (newPerson.setNewRandomPosition(),newPerson.setNewRandomVelocity(), family, 60.0, false, false); 
			  					
			while (this.coincideWhenInitiate(newPerson)) {
				newPerson = new SmileyFace(newPerson.setNewRandomPosition(), newPerson.setNewRandomVelocity(), family, 60.0, false, false);
			}			
			everyone.add (newPerson);			
			count++;			
			if (count >= familyMembersNb[family-1]) {
				family ++;
				count = 0;
			}
		}		
	}
	

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
	
	
	public void changeVelocity () {
		for (Person a : everyone) {			 
			if (Math.random() <0.5) {
				a.velocity =  a.setNewRandomVelocity();
			}		
		}
	}
	
	
	public void startTheInfection() {
		for (int i =0; i< (int)(4*Math.random()+2); i++) {
			int nb = 100;
			while (nb >=70) {
				nb = (int)(80.0*Math.random());
			}
			everyone.add(everyone.get(nb).getInfected());
			everyone.remove(nb);
			infectedPeople.add(everyone.get(nb).getInfected()); 
		}
		
	}
	
	
	public void updateWorldInfection () {
		for(int i = 0; i <everyone.size(); i++) { 
			Person p = everyone.get(i);
			
			if (p.washHandsTime < 0.5*p.ONE_DAY) {
				p.washHandsTime += 100.0;
			}else{
				if (p.vaccinated) {
					p.probabilityToGetInfected = 5.0;
				}else if (p.wearMask){
					p.probabilityToGetInfected = 15.0;
				}else{
					p.probabilityToGetInfected = 50.0;
				}
			}
			
			
			if (p.timeToBeInfected >0) {
				if (p.timeToBeInfected == 2*p.ONE_DAY) {
					p.timeToBeInfected = 0.0;
					if (100.0*Math.random() < p.probabilityToGetInfected) {
						everyone.add(p.getInfected());
						everyone.remove(p);
						infectedPeople.add(p.getInfected()) ;
					}
				}else{
					p.timeToBeInfected += 100.0;
				}
			}
			
			
			if (p instanceof IllFace) {
				if (p.infectionTime < 5*p.ONE_DAY) {
					p.infectionTime += 100.0;
				}else if (p.infectionTime == 5*p.ONE_DAY && 100.0*Math.random() > p.PROBABILITY_TO_DIE) {
					
					everyone.add(p.hasRecovered());
					everyone.remove(p);
					p.infectionTime =0.0;
					infectedPeople.remove(0) ;
						
				}else{
					p.infectionTime += 100.0;
					if (p.infectionTime == 7*p.ONE_DAY) {
						everyone.add(0,p.die());
						everyone.remove(p);
						p.infectionTime = 0.0;
						infectedPeople.remove(0);
						deadPeople.add(p);
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
	
	
	public void updateWorld() {
		this.updateWorldInfection(); 
		if (activateLockdown) {
			updateWorldMovementLockdown();
		}else if (activateSocialDistancing) {
			updateWorldMovementSocialDistancing();
		}else{
			updateWorldMovement();	
		}	
	}
	
	
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
		
		int count =0;
		boolean move = true;
		while (this.movingImpossible(a)) {				
			a.velocity = a.setNewRandomVelocity();
			count++;
			if (count >200) {
				move =false;
				break;
			}					
		}	
		a.movement(move);
	}
	
	
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
		Person p1 = new SmileyFace (new Vec((p.position).x + (p.velocity).x, (p.position).y + (p.velocity).y), new Vec ((p.velocity).x,(p.velocity).y),1, 0.0, false,false);
		return (p.outWindow() || this.coincideAfterMoving(p1));
	}
	
	
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
	
	
	public void getMovingSocialDistancing (Person a, boolean nbLimit) {
		int count =0;
		while (this.socialDistancingImpossible(a,nbLimit)) {					
			a.velocity = a.setNewRandomVelocity();
			count++;
			if (count > 200) {
				break;
			}					
		}
		a.movement(true);
	}
	

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
			if (p.inTheSameAreaWith(b)) {
				count++;
				if (count == NB_LIMIT+1) {
					return true;
				}
			}
		}		
		return false;
	}
	
	
	public boolean socialDistancingImpossible (Person p, boolean nbLimit) {
		if (p instanceof DeadFace) {
			return false;
		}
		Person p1 = new SmileyFace (new Vec((p.position).x + (p.velocity).x, (p.position).y + (p.velocity).y), new Vec ((p.velocity).x,(p.velocity).y),1, 0.0, false,false);
				
		if (nbLimit) {
			return (p.outWindow() || this.noRespectSocialDistancing(p1) || this.noRespectNbLimit(p1));
		}
		return (p.outWindow() || this.noRespectSocialDistancing(p1));
	}
	
	
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
					
					if (a.distanceFromHome() >(a.HOUSE_RADIUS-a.RADIUS) || a instanceof IllFace) {
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
	
	
	public void toWearMask () {
		for (Person a : everyone) {
			if (maskOn) {
				a.wearMask = true;
				if (a.vaccinated) {
					a.probabilityToGetInfected = 1.0;
				}else{
					a.probabilityToGetInfected = 15.0;
				}
			}else{
				a.wearMask = false;
				if (a.vaccinated) {
					a.probabilityToGetInfected = 5.0;
				}else{
					a.probabilityToGetInfected = 50.0;
				}
			}
		}
	}
		
		
	public void togetvaccinated (double pctVaccinated) {
		for (Person a : everyone) {
			if (a instanceof SmileyFace) {
				a.vaccinated = true ; 
				if (a.wearMask) {
					a.probabilityToGetInfected = 1.0;
				}else{
					a.probabilityToGetInfected = 5.0;
				}
				nbVaccinated++;
				if (nbVaccinated >= (int)(pctVaccinated*(everyone.size()-deadPeople.size())/100.0)) {
					break;
				}
			}
			
		}
		percentageVaccinated = (nbVaccinated*100.0/(everyone.size()-deadPeople.size())); 
	}
}
	
	
	
