

import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.GridLayout;

public class PlayGround extends JFrame implements ActionListener{
	public static final double ONE_DAY= 4000.0;
	private double time = 0.0;
	private Timer monChrono;

	
	//private Population faces;
	private PlotTheFaces movingObjects;
	private int countToChangeVelocity =0;
	private JTextArea TimeTextArea;
	private JTextArea PeopleInjectedTextField;
	private JTextArea DeathRateTextField;
	private JButton Handwash;
	private JButton Mask;
	private JButton LockDown;
	private JButton Vaccine;
	private JButton NbLimit;
	private JButton PlayorPause;
	private JButton Restart;
	

	
	

	public PlayGround (){
		this.setTitle ("Play Ground"); //setting the title
		this.setSize (1200,1000); //setting the size
		this.setLocation(0,0); //setting the location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //operation on closing
		//GlobalPannel
		JPanel GlobalPanel = new JPanel();
		GlobalPanel.setBounds(0,0,1200,800);
		GlobalPanel.setLayout(null);
		GlobalPanel.setBackground(Color.yellow);
		
		
		/*Playground panel
		JPanel PlayGroundPanel = new JPanel();
		PlayGroundPanel.setBounds(0,0,900,800);
		PlayGroundPanel.setLayout(null);
		PlayGroundPanel.setBackground(Color.blue);*/
		
		//time panel
		JPanel TimePanel = new JPanel();
		TimePanel.setBounds(930,50,240,120);
		TimePanel.setLayout(null);
		TimePanel.setBackground(Color.green);
		//time label
		JLabel TimeLabel = new JLabel();
		TimeLabel.setText("Time");
		TimeLabel.setBounds(100,10,230,50);
		TimePanel.add(TimeLabel); 
		// time text field 
		TimeTextArea = new JTextArea();
		TimeTextArea.setBounds(20,50,195,50);
		TimePanel.add(TimeTextArea);
		
		//people injected  panel
		JPanel PeopleInjectedPanel = new JPanel();
		PeopleInjectedPanel.setBounds(930,250,240,120);
		PeopleInjectedPanel.setLayout(null);
		PeopleInjectedPanel.setBackground(Color.green);
		//people injected  label
		JLabel PeopleInjectedLabel = new JLabel();
		PeopleInjectedLabel.setText("People infected");
		PeopleInjectedLabel.setBounds(70,10,230,50);
		PeopleInjectedPanel.add(PeopleInjectedLabel); 
		// people injected text field 
		PeopleInjectedTextField = new JTextArea();
		PeopleInjectedTextField.setBounds(20,50,195,50);
		PeopleInjectedPanel.add(PeopleInjectedTextField);
		
		//Death Rate panel
		JPanel DeathRatePanel = new JPanel();
		DeathRatePanel.setBounds(930,500,240,120);
		DeathRatePanel.setLayout(null);
		DeathRatePanel.setBackground(Color.green);
		//Death Rate  label
		JLabel DeathRateLabel = new JLabel();
		DeathRateLabel.setText("Death Rate");
		DeathRateLabel.setBounds(80,10,230,50);
		DeathRatePanel.add(DeathRateLabel); 
		// Death Rate text field 
		DeathRateTextField = new JTextArea();
		DeathRateTextField.setBounds(20,50,195,50);
		DeathRatePanel.add(DeathRateTextField);
		
		//button play or pause
		PlayorPause = new JButton ("Play or Pause");//name of button
		PlayorPause.setBounds (930,700,240,50);
		PlayorPause.setBackground(new Color(10,144,10));//setting color of background
		PlayorPause.setForeground(Color.white);//setting color of foreground
		GlobalPanel.add(PlayorPause);
		//.addActionListener(this); //enabling a listener of actions
		
		//button Restart
		Restart = new JButton ("Restart");//name of button
		Restart.setBounds (930,770,240,50);
		Restart.setBackground(new Color(10,144,10));//setting color of background
		Restart.setForeground(Color.white);//setting color of foreground
		GlobalPanel.add(Restart);
		
		//button Mask
		Mask = new JButton ("Mask");//name of button
		Mask.setBounds (750,860,100,60);
		Mask.setBackground(Color.red);//setting color of background
		Mask.setForeground(Color.white);//setting color of foreground
		GlobalPanel.add(Mask);
		
		//button Handwash
		Handwash= new JButton ("Handwash");//name of button
		Handwash.setBounds (150,860,100,60);
		Handwash.setBackground(Color.red);//setting color of background
		Handwash.setForeground(Color.white);//setting color of foreground
		GlobalPanel.add(Handwash);
		
		//button LockDown
		LockDown= new JButton ("LockDown");//name of button
		LockDown.setBounds (300,860,100,60);
		LockDown.setBackground(Color.red);//setting color of background
		LockDown.setForeground(Color.white);//setting color of foreground
		GlobalPanel.add(LockDown);
		
		//button Vaccine
		Vaccine= new JButton ("Vaccine");//name of button
		Vaccine.setBounds (450,860,100,60);
		Vaccine.setBackground(Color.red);//setting color of background
		Vaccine.setForeground(Color.white);//setting color of foreground
		GlobalPanel.add(Vaccine);
		
		//button NbLimit
		NbLimit= new JButton ("NbLimit");//name of button
		NbLimit.setBounds (600,860,100,60);
		NbLimit.setBackground(Color.red);//setting color of background
		NbLimit.setForeground(Color.white);//setting color of foreground
		NbLimit.addActionListener(this);
		
		//Adding buttons and area for moving objects
		GlobalPanel.add(NbLimit);
		GlobalPanel.add(Mask);
		GlobalPanel.add(TimePanel);
		GlobalPanel.add(PeopleInjectedPanel);
		GlobalPanel.add(DeathRatePanel);
		
		// Adding zone where the faces are moving
		movingObjects = new PlotTheFaces (80);
		GlobalPanel.add(movingObjects);
		
		//Display the playground
		this.add(GlobalPanel);
		this.setResizable(true);
		this.setVisible(true);//visibility of the window
		
		monChrono = new Timer (100,this);
		monChrono.start();
		
		LockDown.addActionListener(this);
		Mask.addActionListener(this);
		
		
		
	}
	
	public void actionPerformed (ActionEvent e){
		if (e.getSource() == LockDown) {
			movingObjects.activateLockdown = !movingObjects.activateLockdown;
			if (movingObjects.activateLockdown) {
				JOptionPane.showMessageDialog(this,"Our country goes into new lockdown. Stay at home and save lives!");
				LockDown.setBackground(new Color (0,255,128));
				LockDown.setForeground(Color.black);
			} else {
				JOptionPane.showMessageDialog(this,"The lockdown is lifted from now");
				LockDown.setBackground(Color.red);
				LockDown.setForeground(Color.white);
			}
		}
		
		if (e.getSource() == Mask) {
			movingObjects.maskOn = !movingObjects.maskOn;
		}
		
		if (e.getSource() == NbLimit) {
			if (!movingObjects.activateLockdown) {
				movingObjects.activateSocialDistancing = !movingObjects.activateSocialDistancing;
			}
		}
		
		if (e.getSource()==monChrono){
			time = time + 100.0;
			this.setTitle ("Flatten the curve - Playing time: "+(int)(time*24.0/this.ONE_DAY)+"h");
			
			movingObjects.faces.startTheInfection(time);
			
			if (movingObjects.activateLockdown) {
				movingObjects.faces.updateWorldLockdown();
			}else if (movingObjects.activateSocialDistancing) {
				movingObjects.faces.updateWorldSocialDistancing();
			}else{						
				movingObjects.faces.updateWorld();
			}
						
			movingObjects.faces.toWearMask(movingObjects.maskOn);			
			
			movingObjects.repaint();
			
			countToChangeVelocity++;
			
			if (countToChangeVelocity==20) {
				movingObjects.faces.changeVelocity();
				countToChangeVelocity =0;
			}
	
			
		}
	}

	
}


