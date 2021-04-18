//Create the area where the graphs are evolving
import javax.swing.*;
import java.util.ArrayList; 
import java.util.LinkedList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;


public class PlotTheGraphs extends JPanel {

	//attribute
	public LinkedList <Point> pointsInfected;
	public LinkedList <Point> pointsDead;
	public LinkedList <JLabel> xAxisInfection;
	public LinkedList <JLabel> yAxisInfection;
	
	public int nbInfected = 0;
	public int nbDead = 0;
	public double time;
	public int xCoordinate =50;
	
	//constructor
	public PlotTheGraphs (){
		pointsInfected = new LinkedList <Point>();
		pointsDead = new LinkedList <Point>();
		xAxisInfection = new LinkedList <JLabel>();
		yAxisInfection = new LinkedList <JLabel>();
		this.setSize(600,950);
		this.setLocation (1200,0);
		this.setLayout (null);
	}

	
	public void paintComponent(Graphics g){	
		if (time%4000==0 && time >0){
			if ((xCoordinate/5)%5 == 0) {
				JLabel xCoor = new JLabel();
				xCoor.setText(String.valueOf(xCoordinate/5-10));
				xCoor.setBounds(xCoordinate-5,410,30,30);
				this.add(xCoor);
			}
			
			/*for (int i =0; i<= (int)(nbInfected/10); i++) {
				JLabel yCoor = new JLabel();
				yCoor.setText(String.valueOf(10*i));
				yCoor.setBounds(0,401-i*50,30,30);
				this.add(yCoor);
			}*/
			
			pointsInfected.add(new Point (xCoordinate,this.nbInfected));
			pointsDead.add (new Point (xCoordinate,this.nbDead));
			xCoordinate+=5;
		}
		
		for (Point p:pointsInfected){
			g.setColor(Color.blue);
			g.fillRect((int)p.time,(int)(401-p.value*5),5,p.value*5+1);
			
		}
		for (Point p1:pointsDead){
			g.setColor(Color.black);
			g.fillRect((int)p1.time,(int)(901-p1.value*5),5,p1.value*5+1);
		}
	}
}

