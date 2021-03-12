import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;


public class TestGUI extends JFrame implements ActionListener {
	//attributes
	private int time =0;
	private Timer monChrono;
	private Population faces;
	//constructor
	public TestGUI(){
		faces = new Population(10);
		this.setTitle ("Flatten the Curve");
		this.setLayout (null);
		this.setSize (900,900);
		this.setLocation (200,200);
		this.setResizable(true);
		this.setVisible (true);
		monChrono = new Timer (100,this);
		monChrono.start();
	}
	/*public void addFaces (Person p){
		faces.add(p);
		repaint(); //update the visualization after modifying
	}*/
	public void paint(Graphics g){
		g.setColor(Color.blue);
		g.fillRect(0,0,this.getWidth(),this.getHeight());
		if (!faces.everyone.isEmpty()){
			for (Person p : faces.everyone){
				p.drawFaces(g);  //draw all the faces
			}
		}
	}
	public void actionPerformed (ActionEvent e){
		if (e.getSource()==monChrono){
			time = time + 1000;
			this.setTitle ("Flatten the curve - Playing time: "+time/1000+"s");
			faces.newWorld();
			repaint();
			
		}
	}

	public void lancement () {
		monChrono.start();
		
	}
	 
}
