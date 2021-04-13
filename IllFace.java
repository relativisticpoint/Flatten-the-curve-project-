// Draw an IllFace Face representing an infected person

import java.awt.*; 
import java.awt.Color;
import javax.swing.*;


public class IllFace extends Person { 
	
	//constructor
	public IllFace (){
		super();
	}
	
	public IllFace (Vec initPosition, Vec initVelocity, int nb){
		super(initPosition, initVelocity, nb);
	}
	
	//when a patient dies
	public Person changeStatus (){
		Vec newPosition = this.position;
		Vec nilVelo = new Vec (0.0,0.0);
		int newFamilyNb = this.familyNb;
		DeadFace newDeath = new DeadFace (newPosition,nilVelo,newFamilyNb);
		return newDeath;
	}
	
	//when a patient has recovered
	public Person hasRecovered (){
		Vec newPos = this.position;
		Vec newVelo = this.velocity;
		int newFamilyNb = this.familyNb;
		SmileyFace newRecovered = new SmileyFace (newPos,newVelo, newFamilyNb);
		return newRecovered;
	}
	
	
	//representation	
	public void drawFaces (Graphics g, boolean socialDist){
		g.setColor(Color.red);
		super.drawFaces(g,socialDist);
		g.drawOval ((int)(position.x-INFECT_RADIUS), (int)(position.y-INFECT_RADIUS),(int)(2*INFECT_RADIUS), (int)(2*INFECT_RADIUS));
		
		if (socialDist && this.socialDistancingRespect) {
			g.setColor(Color.green);
			g.drawOval ((int)(position.x-INFECT_RADIUS), (int)(position.y-INFECT_RADIUS),(int)(2*INFECT_RADIUS), (int)(2*INFECT_RADIUS));
		}
		
		if (wearMask) {
			g.setColor(Color.white);
			g.fillRect((int)(position.x-RADIUS) , (int)(position.y-(RADIUS-1)/2),(int)(2*RADIUS), (int)(0.7*RADIUS));
		}
	}
}


