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
	private int countToChangeVelocity =0;
	private Timer monChrono;
	private boolean notPause = true;
	private boolean startInfection = false; 
	
	private PlotTheFaces movingObjects;
	private PlotTheGraphs graphs;
	
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
		this.setTitle ("Flatten the Curve");
		this.setSize (1800,1000);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setResizable(false); 
		
		border = BorderFactory.createLineBorder(Color.black,1);
		font1 = new Font("TimesNewRoman",Font.BOLD, 14);
		font2 = new Font("TimesNewRoman",Font.PLAIN, 16);
		
		GlobalPanel.setBounds(0,0,1800,800);
		GlobalPanel.setLayout(null);
		GlobalPanel.setBackground(new Color(234,228,203));
		GlobalPanel.setBorder(border);
			
		JPanel Bar = new JPanel();
		Bar.setBounds(1210,20,5,900);
		Bar.setLayout(null);
		Bar.setBackground(new Color(62,60,60));
		Bar.setBorder(border);
		GlobalPanel.add(Bar);
			
		JPanel TimePanel = new JPanel();
		TimePanel.setBounds(930,50,240,120);
		TimePanel.setLayout(null);
		TimePanel.setBackground(new Color(62,60,60));
		TimePanel.setBorder(border);

		JLabel TimeLabel = new JLabel();
		TimeLabel.setText("Time");
		TimeLabel.setBounds(100,10,230,50);
		TimeLabel.setForeground(Color.white);
		TimeLabel.setFont(font1);
		TimePanel.add(TimeLabel); 

		TimeTextField = new JTextField();
		TimeTextField.setBounds(20,50,195,50);
		TimeTextField.setFont(font2);
		TimeTextField.setHorizontalAlignment(JTextField.CENTER);
		TimePanel.add(TimeTextField);	
		GlobalPanel.add(TimePanel);	
		

		JPanel PeopleInfectedPanel = new JPanel();
		PeopleInfectedPanel.setBounds(930,200,240,120);
		PeopleInfectedPanel.setLayout(null);
		PeopleInfectedPanel.setBackground(new Color(62,60,60));
		PeopleInfectedPanel.setBorder(border);

		JLabel PeopleInfectedLabel = new JLabel("Total infection cases");
		PeopleInfectedLabel.setBounds(40,10,230,50);
		PeopleInfectedLabel.setForeground(Color.white);
		PeopleInfectedLabel.setFont(font1);
		PeopleInfectedPanel.add(PeopleInfectedLabel); 

		PeopleInfectedTextField = new JTextField();
		PeopleInfectedTextField.setBounds(20,50,195,50);
		PeopleInfectedTextField.setBorder(border);
		PeopleInfectedTextField.setFont(font2);
		PeopleInfectedTextField.setHorizontalAlignment(JTextField.CENTER);
		PeopleInfectedPanel.add(PeopleInfectedTextField);
		GlobalPanel.add(PeopleInfectedPanel);
		

		JPanel DeathRatePanel = new JPanel();
		DeathRatePanel.setBounds(930,350,240,120);
		DeathRatePanel.setLayout(null);
		DeathRatePanel.setBackground(new Color(62,60,60));
		DeathRatePanel.setBorder(border);

		JLabel DeathRateLabel = new JLabel("Total death cases");
		DeathRateLabel.setBounds(50,10,230,50);
		DeathRateLabel.setForeground(Color.white);
		DeathRateLabel.setFont(font1);
		DeathRatePanel.add(DeathRateLabel); 

		DeathRateTextField = new JTextField();
		DeathRateTextField.setBounds(20,50,195,50);
		DeathRateTextField.setBorder(border);
		DeathRateTextField.setFont(font2);
		DeathRateTextField.setHorizontalAlignment(JTextField.CENTER);
		DeathRatePanel.add(DeathRateTextField);
		GlobalPanel.add(DeathRatePanel);
			
		
		JPanel VaccinePctPanel = new JPanel();
		VaccinePctPanel.setBounds(930,500,240,120);
		VaccinePctPanel.setLayout(null);
		VaccinePctPanel.setBackground(new Color(62,60,60));
		VaccinePctPanel.setBorder(border);
		
		JLabel VaccinePctLabel = new JLabel("Vaccination Percentage (%)");
		VaccinePctLabel.setBounds(20,10,230,50);
		VaccinePctLabel.setForeground(Color.white);
		VaccinePctLabel.setFont(font1);
		VaccinePctPanel.add(VaccinePctLabel); 
		
		VaccineTextField = new JTextField();
		VaccineTextField.setBounds(20,50,195,50);
		VaccineTextField.setBorder(border);
		VaccineTextField.setFont(font2);
		VaccineTextField.setHorizontalAlignment(JTextField.CENTER);
		VaccinePctPanel.add(VaccineTextField);
		GlobalPanel.add(VaccinePctPanel);
		
		
		PlayorPause = new JButton ("Play or Pause");
		PlayorPause.setBounds (930,730,240,50);
		PlayorPause.setBackground(new Color(62,60,60));
		PlayorPause.setForeground(Color.white);
		PlayorPause.setBorder(border);
		PlayorPause.addActionListener(this);
		GlobalPanel.add(PlayorPause);
		
		
		StartInfection = new JButton ("StartTheInfection");
		StartInfection.setBounds (930,800,240,50);
		StartInfection.setBackground(new Color(62,60,60));
		StartInfection.setForeground(Color.white);
		StartInfection.setBorder(border);
		StartInfection.addActionListener(this);
		GlobalPanel.add(StartInfection);
				
		
		Restart = new JButton ("Restart");
		Restart.setBounds (930,870,240,50);
		Restart.setBackground(new Color(62,60,60));
		Restart.setForeground(Color.white);
		Restart.setBorder(border);
		Restart.addActionListener(this);
		GlobalPanel.add(Restart);
		
		
		Mask = new JButton ("Mask");
		Mask.setBounds (700,860,100,60);
		Mask.setBackground(Color.red);
		Mask.setForeground(Color.white);
		Mask.setBorder(border);
		Mask.addActionListener(this);
		GlobalPanel.add(Mask);
		
		
		Handwash= new JButton ("Handwash");
		Handwash.setBounds (100,860,100,60);
		Handwash.setBackground(Color.red);
		Handwash.setForeground(Color.white);
		Handwash.setBorder(border);
		Handwash.addActionListener(this);
		GlobalPanel.add(Handwash);
		

		LockDown= new JButton ("LockDown");
		LockDown.setBounds (250,860,100,60);
		LockDown.setBackground(Color.red);
		LockDown.setForeground(Color.white);
		LockDown.setBorder(border);
		LockDown.addActionListener(this);
		GlobalPanel.add(LockDown);
		

		Vaccine= new JButton ("Vaccine");
		Vaccine.setBounds (400,860,100,60);
		Vaccine.setBackground(Color.red);
		Vaccine.setForeground(Color.white);
		Vaccine.setBorder(border);
		Vaccine.addActionListener(this);
		GlobalPanel.add(Vaccine);

		
		SocialDistancing= new JButton ("SocialDist");
		SocialDistancing.setBounds (550,860,100,60);
		SocialDistancing.setBackground(Color.red);
		SocialDistancing.setForeground(Color.white);
		SocialDistancing.setBorder(border);
		SocialDistancing.addActionListener(this);
		GlobalPanel.add(SocialDistancing);
			
		
		movingObjects = new PlotTheFaces (80);
		GlobalPanel.add(movingObjects);
		
		graphs = new PlotTheGraphs ();
		GlobalPanel.add(graphs);
		
		JLabel graphTitle = new JLabel("<html> <div style='text-align: center'> The evolution of the pandemic <br/> as a function of time </div></html>",SwingConstants.CENTER);
		graphTitle.setBounds(1200,40,500,50);
		graphTitle.setFont(new Font("Arial", Font.BOLD, 24));
		GlobalPanel.add(graphTitle); 
		
		Background = new JLabel(new ImageIcon("Background.png"));
		Background.setBounds(0,0,900,830);
		Background.setBorder(border);
		GlobalPanel.add(Background);
		
		
		this.add(GlobalPanel);
		this.setVisible(true);
		addWindowListener(this);
		
		monChrono = new Timer (100,this);	
		monChrono.start();	
	}
	
	public void playSoundFile(String soundName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception ex) {
			System.out.println("Error with playing this sound file.");
			ex.printStackTrace();
		}
	}
	
	public void actionPerformed (ActionEvent e){
		
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
						playSoundFile("Cloche.wav");
						
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
		
	
		if (e.getSource() == Vaccine) {
			if (startInfection) {
				double pctToVaccinate = movingObjects.faces.percentageVaccinated;
				String vactoSet = null;
				while (pctToVaccinate <= movingObjects.faces.percentageVaccinated || pctToVaccinate > 100.0) {
					notPause = false;
					vactoSet = JOptionPane.showInputDialog(this,"What percentage of the population de you want to vaccinate? (From "+ (int)(movingObjects.faces.percentageVaccinated+1.0) +"% to 100%)");			
					if (vactoSet == null) {
						break;
					}
				
					if (vactoSet.isEmpty()) {
						continue;
					}
					pctToVaccinate = Double.parseDouble(vactoSet);
				}
				notPause = true;
				movingObjects.faces.percentageVaccinated = pctToVaccinate;
				if (pctToVaccinate >0.0 && pctToVaccinate <= 100.0 && vactoSet != null) {					
					Vaccine.setBackground(new Color (0,255,128));
					Vaccine.setForeground(Color.black);
				
					movingObjects.faces.togetvaccinated(movingObjects.faces.percentageVaccinated); 
					JOptionPane.showMessageDialog(this, + (int)(movingObjects.faces.percentageVaccinated) +" % of the population are now vaccinated! ");				
				}
			}
					
		}
		
		
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
		 
		
		if (e.getSource() == PlayorPause) {
			notPause = !notPause;
		}
		
		
		if (e.getSource() == StartInfection) {
			if (!startInfection) {
				startInfection = true;
				
				JLabel day1 = new JLabel("Days");
				day1.setBounds(100,430,30,30);
				graphs.add(day1);
				
				JLabel day2 = new JLabel("Days");			
				day2.setBounds(100,880,30,30);
				graphs.add(day2);
				
				JLabel nbCasesInfection = new JLabel("<html>Total <br/> cases </html>");
				nbCasesInfection.setBounds(25,370,60,50);
				graphs.add(nbCasesInfection);
				
				JLabel nbCasesDeath = new JLabel("<html>Total <br/> death </html>");
				nbCasesDeath.setBounds(25,820,60,50);
				graphs.add(nbCasesDeath);
				
				movingObjects.gelDistributorLocation.clear();
				movingObjects.faces.startTheInfection();
				
				StartInfection.setBackground(new Color (0,255,128));
				StartInfection.setForeground(Color.black);
					
			}
		}
		
		
		if (e.getSource() == Restart) {
			time = 0.0;
			movingObjects.faces.percentageVaccinated = 0.0;
			lockdownDuration = 0.0;
			startInfection = false;
			notPause = true;
			
			GlobalPanel.remove(movingObjects);
			movingObjects = new PlotTheFaces (80);
			GlobalPanel.add(movingObjects);
			
			GlobalPanel.remove(graphs);
			graphs = new PlotTheGraphs();
			GlobalPanel.add(graphs);
			
			StartInfection.setBackground(new Color(62,60,60));
			StartInfection.setForeground(Color.white);
			
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
			
			GlobalPanel.remove(Background);
			GlobalPanel.add(Background);			
		}
		
						
		if (e.getSource() == monChrono && notPause ){
			if (startInfection) {
				time = time + 100.0;
			}
			movingObjects.faces.percentageVaccinated = (movingObjects.faces.nbVaccinated*100.0/(movingObjects.faces.everyone.size()-movingObjects.faces.deadPeople.size()));
					
			TimeTextField.setText((int)(time*24.0/this.ONE_DAY)+"h");
			PeopleInfectedTextField.setText(String.valueOf(movingObjects.faces.infectedPeople.size())+ " (" +String.valueOf((int)((movingObjects.faces.infectedPeople.size()/(80.0-movingObjects.faces.deadPeople.size()))*100.0))+"%)");
			DeathRateTextField.setText(String.valueOf(movingObjects.faces.deadPeople.size())+ " (" +String.valueOf((int)((movingObjects.faces.deadPeople.size()/80.0)*100.0))+ "%)");
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
			
			graphs.nbInfected = movingObjects.faces.infectedPeople.size();
			graphs.nbDead = movingObjects.faces.deadPeople.size();
			graphs.time = this.time;
			repaint();
			
		}
	}	


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


