import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Dimension;


public class PlotTheFaces extends JPanel implements ActionListener {
	
	//attributes
	public static final double ONE_DAY= 4000.0;
	
	private double time =0.0;
	private Timer monChrono;
	private Population faces;
	private int countToChangeVelocity =0;
	
	//constructor
	public PlotTheFaces(int initNbOfPeople){
		faces = new Population(initNbOfPeople);
		this.setSize(900,830);
		this.setLocation (0,0);
		this.setLayout (null);
		monChrono = new Timer (100,this);
		monChrono.start();
	}

	public void paintComponent(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		if (!faces.everyone.isEmpty()){
			for (Person p : faces.everyone){
				
				//draw all the faces
				p.drawFaces(g);  							
			}
		}
	}
	
	public void actionPerformed (ActionEvent e){
		if (e.getSource()==monChrono){
			time = time + 100.0;
			/*this.setTitle ("Flatten the curve - Playing time: "+(int)(time*24.0/this.ONE_DAY)+"h");*/
			
			faces.startTheInfection(time);						
			faces.updateWorld();//error
			
			repaint();
			countToChangeVelocity++;
			
			if (countToChangeVelocity==20) {
				faces.newVelocity();
				countToChangeVelocity =0;
			}
			
		}
	}

	
	 
}
