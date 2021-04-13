//Create the area where the graphs are evolving
import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;


public class PlotTheGraphs extends JPanel {

	//attribute
	public ArrayList <Point> pointsInfected;
	public ArrayList <Point> pointsDead;
	public int nbInfected;
	public int nbDead;
	public double time;
	public int xCoordinate =0;
	//constructor
	public PlotTheGraphs (){
		pointsInfected = new ArrayList <Point>();
		pointsDead = new ArrayList <Point>();
		this.setSize(600,900);
		this.setLocation (1200,0);
		this.setLayout (null);
	}
	public void paintComponent(Graphics g){
	
		if (time%4000==0){
			pointsInfected.add(new Point (xCoordinate,this.nbInfected));
			pointsDead.add (new Point (xCoordinate,this.nbDead));
			xCoordinate+=3;
		}
		for (Point p:pointsInfected){
			g.setColor(Color.blue);
			g.fillRect((int)p.time,(int)(300-p.value*10),10,p.value*10);
			
		}
		for (Point p1:pointsDead){
			g.setColor(Color.black);
			g.fillRect((int)p1.time,(int)(850-p1.value*10),10,p1.value*10);
		}
	}
}

