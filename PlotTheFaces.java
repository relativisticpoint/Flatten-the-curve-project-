/* Moving objects - Faces
 */
import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;

	
public class PlotTheFaces extends JFrame implements ActionListener {
	//attributes
	private int time =0;
	private Timer monChrono;
	private ArrayList <Person> faces;
	//constructor
	public PlotTheFaces(){
		faces = new ArrayList <Person>();
		this.setTitle ("Flatten the Curve");
		this.setLayout (null);
		this.setSize (600,600);
		this.setLocation (700,200);
		this.setResizable(true);
		this.setVisible (true);
		monChrono = new Timer (500,this);
	}
	public void addFaces (Person p){
		faces.add(p);
		repaint(); //update the visualization after modifying
	}
	public void paint(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		boolean emptyCheck = faces.isEmpty();
		if (emptyCheck==false){
			for (Person p : faces){
				p.drawFaces(g);  //draw all the faces
			}
		}
	}
	public void actionPerformed (ActionEvent e){
		if (e.getSource()==monChrono){
			time = time + 1000;
			this.setTitle ("Flatten the curve - Playing time: "+time/1000+"s");
			if (faces!=null) {
				for (Person p : faces){
					p.movement();
				}
			repaint();
			}
		}
	}

	public void lancement () {
		monChrono.start();
		
	}
	 
}
	
	


