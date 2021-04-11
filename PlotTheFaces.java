import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Dimension;


public class PlotTheFaces extends JPanel {
	
	//attributes
	public static final double ONE_DAY= 4000.0;
	
	private double time =0.0;
	private Timer monChrono;
	public Population faces;
	private int countToChangeVelocity =0;
	
	//public boolean activateLockdown = false;
	public boolean activateSocialDistancing = false;
	//public boolean maskOn = false;
	
	//constructor
	public PlotTheFaces(int initNbOfPeople){
		faces = new Population(initNbOfPeople);
		this.setSize(900,830);
		this.setLocation (0,0);
		this.setLayout (null);

	}

	public void paintComponent(Graphics g){
		int count =0;
		g.setColor(Color.blue);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		if (!faces.everyone.isEmpty()){
			for (Person p : faces.everyone){
				
				//draw all the faces
				p.drawFaces(g);  
				
				//draw the houses during lockdown
				if	(faces.activateLockdown) {
					p.drawHouses(g);
				}
											
			}
		}
	}
	
	 
}
