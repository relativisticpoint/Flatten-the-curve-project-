import java.awt.*; 
import java.awt.Color;
import javax.swing.*;

public class IllFace extends Person { 
	
	public IllFace (Vec initPosition, Vec initVelocity, int nb, double probToGetInfected, boolean mask, boolean vaccine){
		super(initPosition, initVelocity, nb, probToGetInfected, mask, vaccine);
	}
	
	
	public void drawFaces (Graphics g, boolean socialDist){
		super.drawFaces(g,socialDist);
		g.setColor(Color.red);
		g.fillOval ((int)(position.x-RADIUS), (int)(position.y-RADIUS),(int)(2*RADIUS), (int)(2*RADIUS));
		g.drawOval ((int)(position.x-INFECT_RADIUS), (int)(position.y-INFECT_RADIUS),(int)(2*INFECT_RADIUS), (int)(2*INFECT_RADIUS));
				
		if (socialDist && this.socialDistancingRespect) {
			g.setColor(Color.green);
			g.drawOval ((int)(position.x-INFECT_RADIUS), (int)(position.y-INFECT_RADIUS),(int)(2*INFECT_RADIUS), (int)(2*INFECT_RADIUS));
		}
		
		if (wearMask) {
			g.setColor(Color.white);
			g.fillRect((int)(position.x-RADIUS) , (int)(position.y-(RADIUS-3)/2),(int)(2*RADIUS), (int)(0.7*RADIUS));
		}
	}
}


