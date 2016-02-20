package itc313;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private GridBagConstraints gbc = new GridBagConstraints();
	private Insets left = new Insets(10,10,10,5);
	private Insets middle = new Insets(10,5,10,5);
	private Insets right = new Insets(10,5,10,10);
	
	private Set<Integer> keys = Collections.synchronizedSet(new HashSet<Integer>());
	
	private Dashboard car1 = new Dashboard("Grom",KeyEvent.VK_Q,KeyEvent.VK_A);
	private Dashboard car2 = new Dashboard("Gladis",KeyEvent.VK_W,KeyEvent.VK_S);
	private Dashboard car3 = new Dashboard("Carl",KeyEvent.VK_E,KeyEvent.VK_D);
	private Dashboard car4 = new Dashboard("Brum",KeyEvent.VK_R,KeyEvent.VK_F);
	
	private Thread thread1 = new Thread(new Updater(car1));
	private Thread thread2 = new Thread(new Updater(car2));
	private Thread thread3 = new Thread(new Updater(car3));
	private Thread thread4 = new Thread(new Updater(car4));

	
	public MainFrame()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		
		gbc.weighty = 1;
		gbc.weightx = 0.25;
		gbc.fill = GridBagConstraints.BOTH;
		
		gbc.gridx = 0;
		gbc.insets = left;
		this.add(car1,gbc);
		
		gbc.gridx = 1;
		gbc.insets = middle;
		this.add(car2,gbc);
		
		gbc.gridx = 2;
		this.add(car3,gbc);
		
		gbc.gridx = 3;
		gbc.insets = right;
		this.add(car4,gbc);
		
		
		this.thread1.start();
		this.thread2.start();
		this.thread3.start();
		this.thread4.start();
		this.addListeners();
	}
	
	private void addListeners()
	{
		this.addKeyListener(new KeyRegister());
	}
	
	class KeyRegister implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e)
		{
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_Q:
					keys.add(e.getKeyCode());
					break;
					
				case KeyEvent.VK_W:
					keys.add(e.getKeyCode());
					break;
					
				case KeyEvent.VK_E:
					keys.add(e.getKeyCode());
					break;
					
				case KeyEvent.VK_R:
					keys.add(e.getKeyCode());
					break;
					
				case KeyEvent.VK_A:
					keys.add(e.getKeyCode());
					break;
					
				case KeyEvent.VK_S:
					keys.add(e.getKeyCode());
					break;
					
				case KeyEvent.VK_D:
					keys.add(e.getKeyCode());
					break;
					
				case KeyEvent.VK_F:
					keys.add(e.getKeyCode());
					break;
			
			}
		}

		@Override
		public void keyReleased(KeyEvent e){}

		@Override
		public void keyTyped(KeyEvent e){}
		
	}
	
	class Updater implements Runnable
	{
		private Dashboard dash;

		public Updater(Dashboard d)
		{
			this.dash = d;
		}
		
		@Override
		public void run() {
			while(true)
			{
				synchronized(keys)
				{
					if(keys.contains(dash.getThrottleKey())) 
					{
						dash.accelerate();
						keys.remove(dash.getThrottleKey());
					}
					if(keys.contains(dash.getBrakeKey()))
					{
						dash.brake();
						keys.remove(dash.getBrakeKey());
					}
				}
			}
		}
		
	}
	
	
}
