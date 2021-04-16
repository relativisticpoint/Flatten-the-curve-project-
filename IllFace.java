// Draw an IllFace Face representing an infected person

import java.awt.*; 
import java.awt.Color;
import javax.swing.*;


public class IllFace extends Person { 
	
	//constructor
	public IllFace (){
		super();
	}
	
	public IllFace (Vec initPosition, Vec initVelocity, int nb, double probToGetInfected, boolean mask, boolean vaccine){
		super(initPosition, initVelocity, nb, probToGetInfected, mask, vaccine);
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


