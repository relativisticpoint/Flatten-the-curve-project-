import javax.swing.*;
import java.util.ArrayList; 
import java.util.LinkedList; 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Graphics;


public class PlotTheGraphs extends JPanel {
	
	public LinkedList <Point> pointsInfected;
	public LinkedList <Point> pointsDead;	
	public int nbInfected = 0;
	public int nbDead = 0;
	public double time;
	public int xCoordinate =100;
	

	public PlotTheGraphs (){
		pointsInfected = new LinkedList <Point>();
		pointsDead = new LinkedList <Point>();
		this.setSize(600,950);
		this.setLocation (1200,0);
		this.setLayout (null);		
		
	}

	
	public void paintComponent(Graphics g){	
		if (time%4000==0 && time >0){
			
			if ((xCoordinate/5)%5 == 0) {
				JLabel xInfectionCoor = new JLabel();
				xInfectionCoor.setText(String.valueOf(xCoordinate/5-19));
				xInfectionCoor.setBounds(xCoordinate,405,30,30);
				this.add(xInfectionCoor);
			}
			
			for (int i =0; i<= (int)(nbInfected/10); i++) {
				JLabel yInfectionCoor = new JLabel();
				yInfectionCoor.setText(String.valueOf(10*i));
				yInfectionCoor.setBounds(70,390-i*50,20,20);
				this.add(yInfectionCoor);
			}
			
			if ((xCoordinate/5)%5 == 0) {
				JLabel xDeathCoor = new JLabel();
				xDeathCoor.setText(String.valueOf(xCoordinate/5-19));
				xDeathCoor.setBounds(xCoordinate,855,30,30);
				this.add(xDeathCoor);
			}
			
			for (int i =0; i<= (int)(nbDead/10); i++) {
				JLabel yDeathCoor = new JLabel();
				yDeathCoor.setText(String.valueOf(10*i));
				yDeathCoor.setBounds(70,840-i*50,20,20);
				this.add(yDeathCoor);
			}
			
			pointsInfected.add(new Point (xCoordinate,this.nbInfected));
			pointsDead.add (new Point (xCoordinate,this.nbDead));
			xCoordinate+=5;
		}
		
		for (Point p:pointsInfected){
			g.setColor(Color.red);
			g.fillRect((int)p.time,(int)(401-p.value*5),5,p.value*5+1);
			
		}
		
		for (Point p1:pointsDead){
			g.setColor(Color.black);
			g.fillRect((int)p1.time,(int)(851-p1.value*5),5,p1.value*5+1);
		}
	}
}

