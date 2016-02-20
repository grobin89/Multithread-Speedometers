package itc313;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Dashboard extends JPanel {
	private final int INTERVAL = 10; 
	
	private JLabel carName;
	private JLabel speedLabel;
	private Speedometer meter;
	private int speed;
	private int throttleKey;
	private int brakeKey;
	
	private GridBagConstraints gbc = new GridBagConstraints();
	
	public Dashboard(String name,int throttleKeyCode,int brakeKeyCode)
	{
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, false));
		this.setLayout(new GridBagLayout());
		
		this.carName = new JLabel(name);
		this.speed = 0;
		this.speedLabel = new JLabel("KMH " + speed);
		this.meter = new Speedometer(speed);
		this.throttleKey = throttleKeyCode;
		this.brakeKey = brakeKeyCode;
		
		gbc.weightx = 1;
		gbc.weighty = .25;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10,10,10,10);
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(carName,gbc);
		
		gbc.gridy = 1;
		this.add(speedLabel,gbc);
		
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weighty = .5;
		this.add(meter,gbc);
	}
	
	public void accelerate()
	{
		this.setSpeed(this.getSpeed() + this.INTERVAL);
	}
	
	public void brake()
	{
		this.setSpeed(this.getSpeed() - this.INTERVAL);
	}
	
	private void setSpeed(int kmh)
	{
		if(kmh >= meter.MIN_SPEED && kmh <= meter.MAX_SPEED ) this.speed = kmh;
		else if(kmh < meter.MIN_SPEED) this.speed = meter.MIN_SPEED;
		else this.speed = meter.MAX_SPEED;
		
		meter.setSpeed(this.speed);
		speedLabel.setText("KMH " + this.speed);
		this.repaint();
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public int getThrottleKey()
	{
		return this.throttleKey;
	}
	
	public int getBrakeKey()
	{
		return this.brakeKey;
	}
}

	
