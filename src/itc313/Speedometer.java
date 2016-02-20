package itc313;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Speedometer extends JPanel {
	
	public final int MAX_SPEED = 220;
	public final int MIN_SPEED = 0;
	private final int SIZE = 120;
	private final int RADIUS = SIZE/2;
	
	private int speed = 0;
	
	public Speedometer(int speed)
	{
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, false));
		this.setSpeed(speed);
		this.setMinimumSize(new Dimension(SIZE,SIZE));
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int[] origin = { this.getWidth()/2 , this.getHeight()/2 };
		double[] maxPoint = getPointOnCircumference(MAX_SPEED,RADIUS,origin);
		
		g.setColor(Color.RED);
		g.fillArc( origin[0]-RADIUS , origin[1]-RADIUS , SIZE , SIZE , MIN_SPEED , speed );
		g.setColor(Color.BLACK);
		g.drawArc( origin[0]-RADIUS , origin[1]-RADIUS , SIZE, SIZE , MIN_SPEED , MAX_SPEED );
		g.drawLine(origin[0], origin[1], origin[0] + RADIUS, origin[1]);
		g.drawLine(origin[0], origin[1], (int)maxPoint[0], (int)maxPoint[1]);
		g.drawString("0", origin[0] + RADIUS + 5 , origin[1] );
		g.drawString("220", (int)maxPoint[0] - 35 , (int)maxPoint[1] );
	}
	
	public void setSpeed(int kmh)
	{
		if(kmh >= MIN_SPEED && kmh <= MAX_SPEED ) this.speed = kmh;
		else if(kmh < MIN_SPEED) this.speed = MIN_SPEED;
		else this.speed = MAX_SPEED;
		this.repaint();
	}
	
	private double[] getPointOnCircumference(int angle, int radius, int origin[])
	{
		double[] point = { origin[0] + radius * Math.cos(Math.toRadians(angle)) , origin[1] + radius * Math.sin(-Math.toRadians(angle)) };
		return point;
	}
	
}
