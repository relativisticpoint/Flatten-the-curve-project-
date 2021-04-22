import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class PlotTheFaces extends JPanel implements MouseListener{
	
	public static final double ONE_DAY= 4000.0;
	
	public Population faces;
	public boolean getHandwash = false;
	public LinkedList <Vec> gelDistributorLocation;
	

	public PlotTheFaces(int initNbOfPeople){
		gelDistributorLocation = new LinkedList <Vec>();
		faces = new Population(initNbOfPeople);
		this.setSize(900,830);
		this.setLocation (0,0);
		this.setLayout (null);
		addMouseListener(this);
	}


	public void paintComponent(Graphics g){
		int count =0;
		
		if (!gelDistributorLocation.isEmpty()) {
			for (Vec v: gelDistributorLocation) {
				Image imageGel = new ImageIcon("gel50.png").getImage();
				g.drawImage(imageGel, (int)(v.x)-25, (int)(v.y)-25, null);
			}	
		}
		
		if (!faces.everyone.isEmpty()){
			for (Person p : faces.everyone){
				for (Vec v: gelDistributorLocation){
					if (p.position.x < (v.x+30) && p.position.x>(v.x-30) && p.position.y <(v.y+30) && p.position.y > (v.y-30)){
						if (!(p instanceof DeadFace)) {
							p.washHands(g);
								
						}
					}
				}		
										
				if (faces.activateSocialDistancing) {				
					p.drawFaces(g, true);  
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
	
	
	public void mouseClicked (MouseEvent e){
		if (getHandwash) {
			Vec aPositionOfGel = new Vec (e.getX(),e.getY());
			if (gelDistributorLocation.size() >=4) {
				gelDistributorLocation.remove(0);
			}
			gelDistributorLocation.add(aPositionOfGel); 
		}	
	}
	
	public void mouseExited (MouseEvent e){}
	public void mouseReleased (MouseEvent e){}
	public void mousePressed (MouseEvent e){}
	public void mouseEntered (MouseEvent e){}
	
	 
}
