

import javax.swing.*;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.GridLayout;

public class PlayGround extends JFrame implements ActionListener{
	public static final double ONE_DAY= 4000.0;
	private double time = 0.0;
	private double timeStartLockdown =0.0;
	private double lockdownDuration =0.0;
	private Timer monChrono;
	private boolean notPause = true ; 
	
	public double percentageVaccinated = 0.0;
	

	private PlotTheFaces movingObjects;
	private PlotTheGraphs graphs;
	
	private int countToChangeVelocity =0;
	private JTextArea TimeTextArea;
	private JTextArea PeopleInfectedTextField;
	private JTextArea DeathRateTextField;
	private JButton Handwash;
	private JButton Mask;
	private JButton LockDown;
	private JButton Vaccine;
	private JButton SocialDistancing;
	private JButton PlayorPause;
	private JButton Restart;
	
    private JPanel GlobalPanel = new JPanel();
    private ArrayList <Point> points = new ArrayList<Point>();
	
	

		public PlayGround (){
		this.setTitle ("Flatten the Curve"); //setting the title
		this.setSize (1800,1000); //setting the size
		this.setLocation(0,0); //setting the location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //operation on closing
		
		
		//GlobalPanel
		this.setResizable(false); 
		//JPanel GlobalPanel = new JPanel();
		GlobalPanel.setBounds(0,0,1800,800);
		GlobalPanel.setLayout(null);
		GlobalPanel.setBackground(Color.yellow);
		
		
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
		JPanel PeopleInfectedPanel = new JPanel();
		PeopleInfectedPanel.setBounds(930,250,240,120);
		PeopleInfectedPanel.setLayout(null);
		PeopleInfectedPanel.setBackground(Color.green);
		//people injected  label
		JLabel PeopleInfectedLabel = new JLabel();
		PeopleInfectedLabel.setText("People infected");
		PeopleInfectedLabel.setBounds(70,10,230,50);
		PeopleInfectedPanel.add(PeopleInfectedLabel); 
		// people injected text field 
		PeopleInfectedTextField = new JTextArea();
		PeopleInfectedTextField.setBounds(20,50,195,50);
		PeopleInfectedPanel.add(PeopleInfectedTextField);
		
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
		PlayorPause.setBackground(new Color(70,144,10));//setting color of background
		PlayorPause.setForeground(Color.black);//setting color of foreground
		PlayorPause.addActionListener(this);
		GlobalPanel.add(PlayorPause);
				
		//button Restart
		Restart = new JButton ("Restart");//name of button
		Restart.setBounds (930,770,240,50);
		Restart.setBackground(new Color(70,144,10));//setting color of background
		Restart.setForeground(Color.black);//setting color of foreground
		Restart.addActionListener(this);
		GlobalPanel.add(Restart);
		
		//button Mask
		Mask = new JButton ("Mask");//name of button
		Mask.setBounds (750,860,100,60);
		Mask.setBackground(Color.red);//setting color of background
		Mask.setForeground(Color.white);//setting color of foreground
		Mask.addActionListener(this);
		GlobalPanel.add(Mask);
		
		//button Handwash
		Handwash= new JButton ("Handwash");//name of button
		Handwash.setBounds (150,860,100,60);
		Handwash.setBackground(Color.red);//setting color of background
		Handwash.setForeground(Color.white);//setting color of foreground
		Handwash.addActionListener(this);
		GlobalPanel.add(Handwash);
		
		//button LockDown
		LockDown= new JButton ("LockDown");//name of button
		LockDown.setBounds (300,860,100,60);
		LockDown.setBackground(Color.red);//setting color of background
		LockDown.setForeground(Color.white);//setting color of foreground
		LockDown.addActionListener(this);
		GlobalPanel.add(LockDown);
		
		//button Vaccine
		Vaccine= new JButton ("Vaccine");//name of button
		Vaccine.setBounds (450,860,100,60);
		Vaccine.setBackground(Color.red);//setting color of background
		Vaccine.setForeground(Color.white);//setting color of foreground
		Vaccine.addActionListener(this);
		GlobalPanel.add(Vaccine);

		
		//button SocialDistancing
		SocialDistancing= new JButton ("SocialDist");//name of button
		SocialDistancing.setBounds (600,860,100,60);
		SocialDistancing.setBackground(Color.red);//setting color of background
		SocialDistancing.setForeground(Color.white);//setting color of foreground
		SocialDistancing.addActionListener(this);
		
		//Adding buttons and area for moving objects
		GlobalPanel.add(SocialDistancing);
		GlobalPanel.add(Mask);
		GlobalPanel.add(TimePanel);
		GlobalPanel.add(PeopleInfectedPanel);
		GlobalPanel.add(DeathRatePanel);
		GlobalPanel.add(Vaccine);
		
		// Adding zone where the faces are moving
		movingObjects = new PlotTheFaces (80);
		GlobalPanel.add(movingObjects);
		graphs = new PlotTheGraphs ();
		GlobalPanel.add(graphs);
		
		//Display the playground
		this.add(GlobalPanel);
		this.setResizable(true);
		this.setVisible(true);//visibility of the window
		
		monChrono = new Timer (100,this);
		monChrono.start();		
		
	
	}
	
	
	public void actionPerformed (ActionEvent e){
		
		//Lockdown button
		if (e.getSource() == LockDown) {
			movingObjects.faces.activateLockdown = !movingObjects.faces.activateLockdown;
			if (movingObjects.faces.activateLockdown) {
				LockDown.setBackground(new Color (0,255,128));
				LockDown.setForeground(Color.black);
				timeStartLockdown = time;

				while (lockdownDuration < 5.0 || lockdownDuration > 14.0) {
					notPause = false;
					String toSet = JOptionPane.showInputDialog(this,"How long does the lockdown last? (between 5 and 14 days)");
					if (toSet == null) {
						movingObjects.faces.activateLockdown =false;
						LockDown.setBackground(Color.red);
						LockDown.setForeground(Color.white);
						break;
					}
					lockdownDuration = Double.parseDouble(toSet);
					
				}				
				notPause = true;
				
				if (lockdownDuration >= 5.0 && lockdownDuration <= 14.0) {
					JOptionPane.showMessageDialog(this,"Our country goes into new lockdown in " + (int)(lockdownDuration) +" days. Stay at home and save lives!");
				}

			} else {
				LockDown.setBackground(Color.red);
				LockDown.setForeground(Color.white);
				JOptionPane.showMessageDialog(this,"The lockdown is lifted from now");
				lockdownDuration =0.0;
			}
		}		

		
		//Mask button 
		if (e.getSource() == Mask) {
			movingObjects.faces.maskOn = !movingObjects.faces.maskOn;
			movingObjects.faces.toWearMask();
		}
		
		//Handwash button
		if (e.getSource() == Handwash) {
			movingObjects.getHandwash = !movingObjects.getHandwash;
			if (movingObjects.getHandwash) {
					Handwash.setBackground(new Color (0,255,128));
					Handwash.setForeground(Color.black);
			}else {
				Handwash.setBackground(Color.red);
				Handwash.setForeground(Color.white);
			}
		}
		
		//vaccine button
		if (e.getSource() == Vaccine) {
			movingObjects.faces.vaccineOn = !movingObjects.faces.vaccineOn;
			if (movingObjects.faces.vaccineOn ) {
				Vaccine.setBackground(new Color (0,255,128));
				Vaccine.setForeground(Color.black);

				
				while (percentageVaccinated <= 0.0 || percentageVaccinated > 100.0  ) {
					String vactoSet = JOptionPane.showInputDialog(this,"What percentage of the population de you want to vaccinate?");
					if (vactoSet == null) {
						movingObjects.faces.vaccineOn =false;
						LockDown.setBackground(Color.red);
						LockDown.setForeground(Color.white);
						break;
					}
					percentageVaccinated = Double.parseDouble(vactoSet);
				}
				if (percentageVaccinated > 0.0 && percentageVaccinated <= 100.0) {
					JOptionPane.showMessageDialog(this, + (int)(percentageVaccinated) +" % of the population are now vaccinated! ");
					
				}			
			}else {
				LockDown.setBackground(Color.red);
				LockDown.setForeground(Color.white);
				JOptionPane.showMessageDialog(this,"From now on , no one is vaccinated. ");
				percentageVaccinated =0.0;
			}
			
			movingObjects.faces.pctVaccinated = percentageVaccinated;	
			movingObjects.faces.togetvaccinated(); 
		}
		 
		//Play or Pause button 
		if (e.getSource() == PlayorPause) {
			notPause = !notPause;
		}
		//Reset button 
		if (e.getSource() == Restart) {
			time = 0.0;
			
			GlobalPanel.remove(movingObjects);
			movingObjects = new PlotTheFaces (80);
			movingObjects.faces.infectedPeople.clear();
			movingObjects.faces.deadPeople.clear();
			
			LockDown.setBackground(Color.red);
			LockDown.setForeground(Color.white);
			
			//graphs.points.clear();
			GlobalPanel.add(movingObjects);
			graphs.xCoordinate += 50; //make a gap between 2 graphs when clicking on restart
		}
		
		//SocialDistancing button 
		if (e.getSource() == SocialDistancing) {
			if (!movingObjects.faces.activateLockdown) {
				movingObjects.faces.activateSocialDistancing = !movingObjects.faces.activateSocialDistancing;
				
				if (movingObjects.faces.activateSocialDistancing) {
					SocialDistancing.setBackground(new Color (0,255,128));
					SocialDistancing.setForeground(Color.black);
				}else {
					SocialDistancing.setBackground(Color.red);
					SocialDistancing.setForeground(Color.white);
				}
			}
		}
		
		if (e.getSource() == monChrono && notPause ){
			time = time + 100.0;
			
			TimeTextArea.setText((int)(time*24.0/this.ONE_DAY)+"h");
			PeopleInfectedTextField.setText(String.valueOf(movingObjects.faces.infectedPeople.size()));
			DeathRateTextField.setText(String.valueOf(movingObjects.faces.deadPeople.size()));
			
			if (movingObjects.faces.activateLockdown && (time - timeStartLockdown) >= (8000.0 +lockdownDuration*ONE_DAY)) {
				movingObjects.faces.activateLockdown = false;
				LockDown.setBackground(Color.red);
				LockDown.setForeground(Color.white);
				lockdownDuration =0.0;
			}
							
			
			movingObjects.faces.startTheInfection(time);
			
			if (movingObjects.faces.activateLockdown) {
				movingObjects.faces.updateWorldLockdown();
			}else if (movingObjects.faces.activateSocialDistancing) {
				movingObjects.faces.updateWorldSocialDistancing();
			}else{						
				movingObjects.faces.updateWorld();
			}
						
			
			
			
			countToChangeVelocity++;
			
			if (countToChangeVelocity==20) {
				movingObjects.faces.changeVelocity();
				countToChangeVelocity =0;
			}
			
			//Setting parameters to plot the graphs
			graphs.nbInfected = movingObjects.faces.infectedPeople.size();
			graphs.nbDead = movingObjects.faces.deadPeople.size();
			graphs.time = this.time;
			repaint();
				
			//graphs.repaint();
	
			
		}
	}
	
	
}


