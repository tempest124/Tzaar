package really.game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.leonmontealegre.input.Input;
import com.leonmontealegre.input.Key;


public class GameOld extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	
	private Thread thread;
	
	private int xPosition, yPosition;
	private int width = 50, height = 50;
	public GameOld() {
		frame = new JFrame("stuff"); 
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.cyan);
		
		Input.setup(this);
		this.setFocusable(true);
		this.setVisible(true);
		frame.getContentPane().add(this);
		
		frame.setVisible(true);
	}
	
	public void start() {
		thread = new Thread(this, "Thingstuff");
		thread.start();
	}
	
	public void update() {
		if(Math.max(xPosition, width+xPosition)<frame.getWidth())
		if (Input.getKey(Key.D)) {
			xPosition++;
		}
		if(Math.min(xPosition, width+xPosition)>0)
		if (Input.getKey(Key.A)) {
			xPosition--;
		}
		if(Math.min(yPosition, height+yPosition)>0)
		if (Input.getKey(Key.W)) {
			yPosition--;
		}
		if(Math.max(yPosition, height+yPosition)<frame.getHeight())
		if (Input.getKey(Key.S)) {
			yPosition++;
		}
		
		if (Input.getKey(Key.Q)) {
			width+=2;
			xPosition--;
		}
		
		
		if (Input.getKey(Key.E)) {
			height+=2;
			yPosition--;
		}
		if (Input.getKey(Key.SHIFT)) {
			width-=2;
			xPosition++;
		}
		if (Input.getKey(Key.C)) {
			height-=2;
			yPosition++;
		}
		
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				this.update();
				frame.repaint();
				Thread.sleep(1000 / 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		
		//draws cube thing
		g.setColor(Color.black);
		g.drawRect(xPosition, yPosition, Math.abs(width), Math.abs(height));
	}
	
	public static void main(String[] args) {
		new GameOld().start();
	}
	
}
