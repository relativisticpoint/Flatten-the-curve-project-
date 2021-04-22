import java.awt.*; 
import java.awt.Color;
import javax.swing.*;


public class DeadFace extends Person { 
	
	public DeadFace (Vec initPosition, Vec initVelocity, int nb, double probToGetInfected, boolean mask, boolean vaccine){
		super(initPosition, initVelocity, nb, probToGetInfected, mask, vaccine);
		this.velocity = new Vec(0.0,0.0);
		this.lockdownRespect = false;
		this.socialDistancingRespect = false;
		this.wearMask = false;
		this.vaccinated = false;
	}	


	public Vec setNewRandomVelocity() {
		return new Vec(0.0,0.0);
	}

	
	public Vec goHome() {
		return new Vec (0.0,0.0);
	}

	
	public double distanceFromHome() {
		return 0.0;
	}

	
	public boolean goAwayFromHome() {
		return false;
	}

	
	public void drawFaces (Graphics g, boolean socialDist){
		super.drawFaces(g,socialDist);
		g.setColor(Color.black);
		g.fillOval ((int)(position.x-RADIUS), (int)(position.y-RADIUS),(int)(2*RADIUS), (int)(2*RADIUS));
	}	
}


