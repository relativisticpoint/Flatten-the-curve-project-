import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.util.ArrayList; 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.GridLayout;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class PlayGround extends JFrame implements ActionListener, WindowListener{
	public static final double ONE_DAY= 4000.0;
	private double time = 0.0;
	private double timeStartLockdown =0.0;
	private double lockdownDuration =0.0;
	private Timer monChrono;
	private boolean notPause = true;
	private boolean startInfection = false; 
	
	private PlotTheFaces movingObjects;
	private PlotTheGraphs graphs;
	
	private int countToChangeVelocity =0;
	private JTextField TimeTextField;
	private JTextField PeopleInfectedTextField;
	private JTextField DeathRateTextField;
	private JTextField VaccineTextField;
	private JButton Handwash;
	private JButton Mask;
	private JButton LockDown;
	private JButton Vaccine;
	private JButton SocialDistancing;
	private JButton PlayorPause;
	private JButton Restart;
	private JButton StartInfection;
	private Border border;
	private Font font1;
	private Font font2;
	private JLabel Background;
	
    private JPanel GlobalPanel = new JPanel();
		

	public PlayGround (){
		this.setTitle ("Flatten the Curve"); //setting the title
		this.setSize (1800,1000); //setting the size
		this.setLocation(0,0); //setting the location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //operation on closing
		
		border = BorderFactory.createLineBorder(Color.black,1);
		font1 = new Font("TimesNewRoman",Font.BOLD, 14);
		font2 = new Font("TimesNewRoman",Font.PLAIN, 16);
		
		//GlobalPanel
		this.setResizable(false); 
		GlobalPanel.setBounds(0,0,1800,800);
		GlobalPanel.setLayout(null);
		GlobalPanel.setBackground(Color.yellow);
		GlobalPanel.setBorder(border);
				
		JLabel graphTitle = new JLabel("<html> <div style='text-align: center'> The evolution of the pandemic <br/> as a function of time </div></html>",SwingConstants.CENTER);
		graphTitle.setBounds(1200,40,500,50);
		graphTitle.setFont(new Font("Arial", Font.BOLD, 24));
		GlobalPanel.add(graphTitle); 
		
		//time panel
		JPanel TimePanel = new JPanel();
		TimePanel.setBounds(930,50,240,120);
		TimePanel.setLayout(null);
		TimePanel.setBackground(Color.green);
		TimePanel.setBorder(border);
		//time label
		JLabel TimeLabel = new JLabel();
		TimeLabel.setText("Time");
		TimeLabel.setBounds(100,10,230,50);
		TimeLabel.setFont(font1);
		TimePanel.add(TimeLabel); 
		// time text field 
		TimeTextField = new JTextField();
		TimeTextField.setBounds(20,50,195,50);
		TimeTextField.setFont(font2);
		TimeTextField.setHorizontalAlignment(JTextField.CENTER);
		TimePanel.add(TimeTextField);		
		
		//people infected  panel
		JPanel PeopleInfectedPanel = new JPanel();
		PeopleInfectedPanel.setBounds(930,200,240,120);
		PeopleInfectedPanel.setLayout(null);
		PeopleInfectedPanel.setBackground(Color.green);
		PeopleInfectedPanel.setBorder(border);
		//people infected  label
		JLabel PeopleInfectedLabel = new JLabel("Total infection cases");
		PeopleInfectedLabel.setBounds(40,10,230,50);
		PeopleInfectedLabel.setFont(font1);
		PeopleInfectedPanel.add(PeopleInfectedLabel); 
		// people infected text field 
		PeopleInfectedTextField = new JTextField();
		PeopleInfectedTextField.setBounds(20,50,195,50);
		PeopleInfectedTextField.setBorder(border);
		PeopleInfectedTextField.setFont(font2);
		PeopleInfectedTextField.setHorizontalAlignment(JTextField.CENTER);
		PeopleInfectedPanel.add(PeopleInfectedTextField);
		
		//Death Rate panel
		JPanel DeathRatePanel = new JPanel();
		DeathRatePanel.setBounds(930,350,240,120);
		DeathRatePanel.setLayout(null);
		DeathRatePanel.setBackground(Color.green);
		DeathRatePanel.setBorder(border);
		//Death Rate  label
		JLabel DeathRateLabel = new JLabel("Total death cases");
		DeathRateLabel.setBounds(50,10,230,50);
		DeathRateLabel.setFont(font1);
		DeathRatePanel.add(DeathRateLabel); 
		// Death Rate text field 
		DeathRateTextField = new JTextField();
		DeathRateTextField.setBounds(20,50,195,50);
		DeathRateTextField.setBorder(border);
		DeathRateTextField.setFont(font2);
		DeathRateTextField.setHorizontalAlignment(JTextField.CENTER);
		DeathRatePanel.add(DeathRateTextField);
		
		//Vaccination Pct panel
		JPanel VaccinePctPanel = new JPanel();
		VaccinePctPanel.setBounds(930,500,240,120);
		VaccinePctPanel.setLayout(null);
		VaccinePctPanel.setBackground(Color.green);
		VaccinePctPanel.setBorder(border);
		//VaccineationPct label
		JLabel VaccinePctLabel = new JLabel("Vaccination Percentage (%)");
		VaccinePctLabel.setBounds(20,10,230,50);
		VaccinePctLabel.setFont(font1);
		VaccinePctPanel.add(VaccinePctLabel); 
		//VaccinationPct text field 
		VaccineTextField = new JTextField();
		VaccineTextField.setBounds(20,50,195,50);
		VaccineTextField.setBorder(border);
		VaccineTextField.setFont(font2);
		VaccineTextField.setHorizontalAlignment(JTextField.CENTER);
		VaccinePctPanel.add(VaccineTextField);
		
		//button play or pause
		PlayorPause = new JButton ("Play or Pause");//name of button
		PlayorPause.setBounds (930,730,240,50);
		PlayorPause.setBackground(new Color(70,144,10));//setting color of background
		PlayorPause.setForeground(Color.black);//setting color of foreground
		PlayorPause.setBorder(border);
		PlayorPause.addActionListener(this);
		GlobalPanel.add(PlayorPause);
		
		StartInfection = new JButton ("StartTheInfection");//name of button
		StartInfection.setBounds (930,800,240,50);
		StartInfection.setBackground(new Color(70,144,10));//setting color of background
		StartInfection.setForeground(Color.black);//setting color of foreground
		StartInfection.setBorder(border);
		StartInfection.addActionListener(this);
		GlobalPanel.add(StartInfection);
				
		//button Restart
		Restart = new JButton ("Restart");//name of button
		Restart.setBounds (930,870,240,50);
		Restart.setBackground(new Color(70,144,10));//setting color of background
		Restart.setForeground(Color.black);//setting color of foreground
		Restart.setBorder(border);
		Restart.addActionListener(this);
		GlobalPanel.add(Restart);
		
		//button Mask
		Mask = new JButton ("Mask");//name of button
		Mask.setBounds (750,860,100,60);
		Mask.setBackground(Color.red);//setting color of background
		Mask.setForeground(Color.white);//setting color of foreground
		Mask.setBorder(border);
		Mask.addActionListener(this);
		GlobalPanel.add(Mask);
		
		//button Handwash
		Handwash= new JButton ("Handwash");//name of button
		Handwash.setBounds (150,860,100,60);
		Handwash.setBackground(Color.red);//setting color of background
		Handwash.setForeground(Color.white);//setting color of foreground
		Handwash.setBorder(border);
		Handwash.addActionListener(this);
		GlobalPanel.add(Handwash);
		
		//button LockDown
		LockDown= new JButton ("LockDown");//name of button
		LockDown.setBounds (300,860,100,60);
		LockDown.setBackground(Color.red);//setting color of background
		LockDown.setForeground(Color.white);//setting color of foreground
		LockDown.setBorder(border);
		LockDown.addActionListener(this);
		GlobalPanel.add(LockDown);
		
		//button Vaccine
		Vaccine= new JButton ("Vaccine");//name of button
		Vaccine.setBounds (450,860,100,60);
		Vaccine.setBackground(Color.red);//setting color of background
		Vaccine.setForeground(Color.white);//setting color of foreground
		Vaccine.setBorder(border);
		Vaccine.addActionListener(this);
		GlobalPanel.add(Vaccine);

		
		//button SocialDistancing
		SocialDistancing= new JButton ("SocialDist");//name of button
		SocialDistancing.setBounds (600,860,100,60);
		SocialDistancing.setBackground(Color.red);//setting color of background
		SocialDistancing.setForeground(Color.white);//setting color of foreground
		SocialDistancing.setBorder(border);
		SocialDistancing.addActionListener(this);
		GlobalPanel.add(SocialDistancing);
		
		//Adding buttons and area for moving objects
		
		GlobalPanel.add(TimePanel);
		GlobalPanel.add(PeopleInfectedPanel);
		GlobalPanel.add(DeathRatePanel);
		GlobalPanel.add(VaccinePctPanel);
		
		// Adding zone where the faces are moving
		movingObjects = new PlotTheFaces (80);
		GlobalPanel.add(movingObjects);
		
		graphs = new PlotTheGraphs ();
		GlobalPanel.add(graphs);
		
		Background = new JLabel(new ImageIcon("Background.png"));
		Background.setBounds(0,0,900,830);
		Background.setBorder(border);
		GlobalPanel.add(Background);
		
		//Display the playground
		this.add(GlobalPanel);
		this.setResizable(true);
		this.setVisible(true);//visibility of the window
		addWindowListener(this);
		
		monChrono = new Timer (100,this);	
		monChrono.start();
	
	}
	
	public void playSound(String soundName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}
	
	public void actionPerformed (ActionEvent e){
		
		//SocialDistancing button 
		if (e.getSource() == SocialDistancing) {
			if (startInfection) {
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
		}
		
		//Lockdown button
		if (e.getSource() == LockDown) {
			if (startInfection) {
				if (lockdownDuration ==0.0) {
					while (lockdownDuration < 5.0 || lockdownDuration > 14.0) {
						notPause = false;
						String toSet = JOptionPane.showInputDialog(this,"How long does the lockdown last? (between 5 and 14 days)");
					
						if (toSet == null) {
							movingObjects.faces.activateLockdown =false;
							LockDown.setBackground(Color.red);
							LockDown.setForeground(Color.white);
							break;
						}
					
						if (toSet.isEmpty()) {
							continue;
						}
						lockdownDuration = Double.parseDouble(toSet);
					
					}				
					notPause = true;
				
					if (lockdownDuration >= 5.0 && lockdownDuration <= 14.0) {
						playSound("Cloche.wav");
						
						movingObjects.faces.activateLockdown = true;
						LockDown.setBackground(new Color (0,255,128));
						LockDown.setForeground(Color.black);
						timeStartLockdown = time;
					
						movingObjects.faces.activateSocialDistancing = true;
						SocialDistancing.setBackground(new Color (0,255,128));
						SocialDistancing.setForeground(Color.black);
					
						JOptionPane.showMessageDialog(this,"Our country goes into new lockdown in " + (int)(lockdownDuration) +" days. Stay at home and save lives!");
					}

				}else{
					movingObjects.faces.activateLockdown = false;
					LockDown.setBackground(Color.red);
					LockDown.setForeground(Color.white);
					JOptionPane.showMessageDialog(this,"The lockdown is lifted from now");
					lockdownDuration =0.0;
				}
			}		
		}
		
		//Mask button 
		if (e.getSource() == Mask) {
			movingObjects.faces.maskOn = !movingObjects.faces.maskOn;
			movingObjects.faces.toWearMask();
			if (movingObjects.faces.maskOn) {
				Mask.setBackground(new Color (0,255,128));
				Mask.setForeground(Color.black);
			}else{
				Mask.setBackground(Color.red);
				Mask.setForeground(Color.white);
			}
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
			if (startInfection) {
				double pctToVaccinate = movingObjects.faces.percentageVaccinated;
				String vactoSet = null;
				while (pctToVaccinate <= movingObjects.faces.percentageVaccinated || pctToVaccinate > 100.0  ) {
					vactoSet = JOptionPane.showInputDialog(this,"What percentage of the population de you want to vaccinate? (From "+ (int)(movingObjects.faces.percentageVaccinated+1.0) +"% to 100%)");			
					if (vactoSet == null) {
						break;
					}
				
					if (vactoSet.isEmpty()) {
						continue;
					}
					pctToVaccinate = Double.parseDouble(vactoSet);
				}
				movingObjects.faces.percentageVaccinated = pctToVaccinate;
				if (pctToVaccinate >0.0 && pctToVaccinate <= 100.0 && vactoSet != null) {					
					Vaccine.setBackground(new Color (0,255,128));
					Vaccine.setForeground(Color.black);
				
					movingObjects.faces.togetvaccinated(movingObjects.faces.percentageVaccinated); 
					JOptionPane.showMessageDialog(this, + (int)(movingObjects.faces.percentageVaccinated) +" % of the population are now vaccinated! ");				
				}
			}
					
		}
		 
		//Play or Pause button 
		if (e.getSource() == PlayorPause) {
			notPause = !notPause;
		}
		
		//Start Infection
		if (e.getSource() == StartInfection) {
			if (!startInfection) {
				startInfection = true;
				
				JLabel day1 = new JLabel("Days");
				day1.setBounds(75,430,30,30);
				graphs.add(day1);
				
				JLabel day2 = new JLabel("Days");			
				day2.setBounds(75,880,30,30);
				graphs.add(day2);
				
				JLabel nbCasesInfection = new JLabel("<html>Total <br/> cases </html>");
				nbCasesInfection.setBounds(0,370,60,50);
				graphs.add(nbCasesInfection);
				
				JLabel nbCasesDeath = new JLabel("<html>Total <br/> death </html>");
				nbCasesDeath.setBounds(0,820,60,50);
				graphs.add(nbCasesDeath);
				
				movingObjects.gelDistributorLocation.clear();
				movingObjects.faces.startTheInfection();
				
				StartInfection.setBackground(new Color (0,255,128));
				StartInfection.setForeground(Color.black);
					
			}
		}
		
		//Reset button 
		if (e.getSource() == Restart) {
			time = 0.0;
			movingObjects.faces.percentageVaccinated = 0.0;
			lockdownDuration = 0.0;
			startInfection = false;
			
			GlobalPanel.remove(movingObjects);
			movingObjects = new PlotTheFaces (80);
			GlobalPanel.add(movingObjects);
			
			GlobalPanel.remove(graphs);
			graphs = new PlotTheGraphs();
			GlobalPanel.add(graphs);
			
			StartInfection.setBackground(new Color(70,144,10));
			StartInfection.setForeground(Color.black);
			
			Handwash.setBackground(Color.red);
			Handwash.setForeground(Color.white);
			
			LockDown.setBackground(Color.red);
			LockDown.setForeground(Color.white);
			
			Vaccine.setBackground(Color.red);
			Vaccine.setForeground(Color.white);
			
			SocialDistancing.setBackground(Color.red);
			SocialDistancing.setForeground(Color.white);
			
			Mask.setBackground(Color.red);
			Mask.setForeground(Color.white);
			
			//graphs.points.clear();
			//graphs.xCoordinate += 50; //make a gap between 2 graphs when clicking on restart
			
			GlobalPanel.remove(Background);
			GlobalPanel.add(Background);
			

		}
				
		if (e.getSource() == monChrono && notPause ){
			if (startInfection) {
				time = time + 100.0;
			}
			movingObjects.faces.percentageVaccinated = (movingObjects.faces.nbVaccinated*100.0/(movingObjects.faces.everyone.size()-movingObjects.faces.deadPeople.size()));
					
			TimeTextField.setText((int)(time*24.0/this.ONE_DAY)+"h");
			PeopleInfectedTextField.setText(String.valueOf((int)(movingObjects.faces.infectedPeople.size()))+ " (" +String.valueOf((int)((movingObjects.faces.infectedPeople.size()/(movingObjects.faces.everyone.size()-movingObjects.faces.deadPeople.size()))*100.0))+"%)");
			DeathRateTextField.setText(String.valueOf((int)(movingObjects.faces.deadPeople.size()))+ " (" +String.valueOf((int)((movingObjects.faces.deadPeople.size()/80.0)*100.0))+ "%)");
			VaccineTextField.setText(String.valueOf((int)(movingObjects.faces.percentageVaccinated)));
			
			if (movingObjects.faces.activateLockdown && (time - timeStartLockdown) >= (8000.0 +lockdownDuration*ONE_DAY)) {
				movingObjects.faces.activateLockdown = false;
				LockDown.setBackground(Color.red);
				LockDown.setForeground(Color.white);
				lockdownDuration =0.0;
			}									
								
			movingObjects.faces.updateWorld();				
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
			
		}
	}	

	//override
	public void windowIconified(WindowEvent e) {
        monChrono.stop();
    }
    
     public void windowDeiconified(WindowEvent e) {
        monChrono.start();
    }
    
    public void windowClosed(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {}

}


