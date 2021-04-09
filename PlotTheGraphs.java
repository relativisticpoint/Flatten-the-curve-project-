//Create the area where the graphs are evolving
import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;


public class PlotTheGraphs extends JPanel {

	//attribute
	private ArrayList <Point> points;
	public int nbInfected;
	public double time;
	public int xCoordinate =0;
	//constructor
	public PlotTheGraphs (){
		points = new ArrayList <Point>();
		this.setSize(400,300);
		this.setLocation (1200,0);
		this.setLayout (null);
	}
	public void paintComponent(Graphics g){
	
		if (time%4000==0){
			points.add(new Point (xCoordinate,this.nbInfected));
			xCoordinate+=5;
		}
		
		g.setColor(Color.blue);
		
		for (Point p :points){
			g.fillRect((int)p.time,(int)(200-p.value*10),10,p.value*10);
			}
		}
	}

