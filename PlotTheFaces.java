import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Dimension;


public class PlotTheFaces extends JPanel implements MouseListener{
	
	//attributes
	public static final double ONE_DAY= 4000.0;
	
	private double time =0.0;
	private Timer monChrono;
	public Population faces;
	private int countToChangeVelocity =0;
	private ArrayList <Vec> gelDistributorLocation = new ArrayList <Vec>(); 
	public static final double RADIUS = 7.0;
	
	//public boolean activateLockdown = false;
	//public boolean activateSocialDistancing = false;
	//public boolean maskOn = false;
	//public boolean vaccineOn = false ; 
	
	//constructor
	public PlotTheFaces(int initNbOfPeople){
		faces = new Population(initNbOfPeople);
		this.setSize(900,830);
		this.setLocation (0,0);
		this.setLayout (null);
		addMouseListener(this);

	}
//override
	public void mouseClicked (MouseEvent e){
		Vec aPositionOfGel = new Vec (e.getX(),e.getY());
		gelDistributorLocation.add(aPositionOfGel); 
		JLabel imageGel = new JLabel (new ImageIcon ("gel50.png"));
		imageGel.setBounds ((int)(e.getX()),(int)(e.getY()), 50, 50);
		this.add(imageGel);	
			
	}
	//override
	public void mouseExited (MouseEvent e){
	}
	//override
	public void mouseReleased (MouseEvent e){
	}
	//override
	public void mousePressed (MouseEvent e){
	}
	//override
	public void mouseEntered (MouseEvent e){
	}

	public void paintComponent(Graphics g){
		int count =0;
		g.setColor(Color.blue);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		if (!faces.everyone.isEmpty()){
			for (Person p : faces.everyone){
				for (Vec v: gelDistributorLocation){
					if (p.position.x < (v.x+30) && p.position.x>(v.x-30) && p.position.y <(v.y+30) && p.position.y > (v.y-30)){
						if (!(p instanceof DeadFace)) p.washHands(g);
					}
				}								
				if (faces.activateSocialDistancing) {
					//draw all the faces				
					p.drawFaces(g, true);  
				
					//draw the houses during lockdown
					if	(faces.activateLockdown) {
						p.drawHouses(g);
					}
				}else{		
					p.drawFaces(g, false); 
					if	(faces.activateLockdown) {
						p.drawHouses(g);
					}
				}
			}
		}
	}
	
	 
}
