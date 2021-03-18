// Draw a Dead Face

import java.awt.*; 
import java.awt.Color;
import javax.swing.*;


public class DeadFace extends Person { 
	//attributes
	private Color status = Color.black;
	
	//constructor
	public DeadFace (){
		super();
	}
	public DeadFace (Vec initPosition, Vec initVelocity){
		super(initPosition, initVelocity);
		this.velocity = new Vec (0.0,0.0);
	}
	public void drawFaces (Graphics g){
		g.setColor(status);
		g.fillOval ((int)(position.x), (int)(position.y),(int)(2*RADIUS), (int)(2*RADIUS));
	}
}


