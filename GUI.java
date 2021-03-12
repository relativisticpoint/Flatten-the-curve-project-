

import java.awt.Color;
import java.util.LinkedList;

public class AlgoProject {
	
	public static void main (String[] args) {
		//GlobalPannel
		JPanel GlobalPanel = new JPanel();
		GlobalPanel.setBounds(0,0,1200,1600);
		GlobalPanel.setLayout(null);
		GlobalPanel.setBackground(Color.yellow);
		GlobalPanel.add(TimePanel);
		GlobalPanel.add(PeopleInjectedPanel);
		GlobalPanel.add(DeathRatePanel);
		GlobalPanel.add(PlayGroundPanel);
		add(GlobalPanel);
		
		//Playground panel
		JPanel PlayGroundPanel = new JPanel();
		PlayGroundPanel.setBounds(0,0,900,1200);
		PlayGroundPanel.setLayout(null);
		PlayGroundPanel.setBackground(Color.Blue);
		
		//time panel
		JPanel TimePanel = new JPanel();
		TimePanel.setBounds(930,125,240,150);
		TimePanel.setLayout(null);
		TimePanel.setBackground(Color.green);
		//time label
		JLabel TimeLabel = new JLabel();
		TimeLabel.setText("Time");
		TimeLabel.setBounds(935,130,230,50);
		TimePanel.add(TimeLabel); 
		// time text field 
		JTextField TimeTextField = new JTextField();
		TimeTextField.setBounds(935,140,230,70);
		TimePanel.add(TimeTextField);
		
		//people injected  panel
		JPanel PeopleInjectedPanel = new JPanel();
		PeopleInjectedPanel.setBounds(930,525,240,120);
		PeopleInjectedPanel.setLayout(null);
		PeopleInjectedPanel.setBackground(Color.green);
		//people injected  label
		JLabel PeopleInjectedLabel = new JLabel();
		PeopleInjectedLabel.setText("Time");
		PeopleInjectedLabel.setBounds(935,530,230,50);
		PeopleInjectedPanel.add(PeopleInjectedLabel); 
		// people injected text field 
		JTextField PeopleInjectedTextField = new JTextField();
		PeopleInjectedTextField.setBounds(935,540,230,60);
		PeopleInjectedPanel.add(PeopleInjectedTextField);
		
		//Death Rate panel
		JPanel DeathRatePanel = new JPanel();
		DeathRatePanel.setBounds(930,925,240,120);
		DeathRatePanel.setLayout(null);
		DeathRatePanel.setBackground(Color.green);
		//Death Rate  label
		JLabel DeathRateLabel = new JLabel();
		DeathRateLabel.setText("Time");
		DeathRateLabel.setBounds(935,930,230,50);
		DeathRatePanel.add(DeathRateLabel); 
		// Death Rate text field 
		JTextField DeathRateTextField = new JTextField();
		DeathRateTextField.setBounds(940,65,230,60);
		DeathRatePanel.add(DeathRateTextField);
		
	}
}

