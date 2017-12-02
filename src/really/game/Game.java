package really.game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.leonmontealegre.util.*;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	private JFrame frame;

	private Thread thread;
	private Image board;
	private Image bTott;
	private Image wTzarra;
	private Image bTzarra;
	private Image wTott;
	private Image bTzaar;
	private Image wTzaar;
	private Image boards;
	private boolean n;
	public float xRecorder;
	public float yRecorder;
	private boolean isRunning;
	private boolean t = false;
	private boolean k=false;
	private Piece dragPiece;
	private ArrayList<Piece> pieces;
	private ArrayList<Point> places;
	private ArrayList<Piece> y;
	private ArrayList<Piece> g;
	private ArrayList<Piece> b;
	private ArrayList<Piece> c;
	private ArrayList<Piece> f;
	private ArrayList<Piece> l;
	private Array[][] Point;
	public Game() {
		frame = new JFrame("Unicycle Posse");
		frame.setSize(900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Input.initiate(this);
		this.setLayout(null);
		this.setFocusable(true);
		this.setVisible(true);
		frame.getContentPane().add(this);

		boards = new ImageIcon("image/board.jpg").getImage();
		bTott = new ImageIcon("image/1.png").getImage();
		wTzarra = new ImageIcon("image/2.png").getImage();
		bTzarra = new ImageIcon("image/3.png").getImage();
		wTott = new ImageIcon("image/4.png").getImage();
		bTzaar = new ImageIcon("image/5.png").getImage();
		wTzaar = new ImageIcon("image/6.png").getImage();
		pieces = new ArrayList<Piece>();
		places = new ArrayList<Point>();
		y = new ArrayList<Piece>();
		g = new ArrayList<Piece>();
	    b = new ArrayList<Piece>();
		c = new ArrayList<Piece>();
		f = new ArrayList<Piece>();
		l = new ArrayList<Piece>();
		for (int l = 1; l <= 15; l++) {
			Piece h = new Piece(wTott, 60, new Vector(50, 800), true, 1);
			pieces.add(h);
			g.add(h);
			Piece j = new Piece(bTott, 60, new Vector(600, 800), false, 1);
			pieces.add(j);
			y.add(j);
		}	
		for (int k = 1; k <= 9; k++) {
			Piece h = new Piece(wTzarra, 60, new Vector(125, 800), true, 1);
			pieces.add(h);
			b.add(h);
			Piece j = new Piece(bTzarra, 60, new Vector(675, 800), false, 1);
			pieces.add(j);
			c.add(j);
		}
		for (int o = 1; o <= 6; o++) {
			Piece h = new Piece(wTzaar, 60, new Vector(200, 800), true, 1);
			pieces.add(h);
			f.add(h);
			Piece j = new Piece(bTzaar, 60, new Vector(750, 800), false, 1);
			pieces.add(j);
			l.add(j);
		}
		// fill the piece array ^

		frame.setUndecorated(true);
		JButton quitButton = new JButton("Quit");
		quitButton.setSize(frame.getWidth() / 15, frame.getHeight() / 50);
		quitButton.setLocation(
				(int) (frame.getWidth() / 2 - quitButton.getWidth() / 2), 5);
		quitButton.addActionListener((e) -> {
			System.exit(0);
		});
		this.add(quitButton);

		setupBoard();
		frame.setVisible(true);
	}

	public void start() {
		this.isRunning = true;
		thread = new Thread(this, "Updater");
		thread.start();
	}

	public void update() {
		
		for (int i = 0; i < pieces.size(); i++) {

			if (Input.getMouseDown(Key.LEFT_MOUSE_BUTTON.keyValue)) {

				if (pieces.get(i).containsPoint(Input.mousePosition.x,
						Input.mousePosition.y)) {

					xRecorder = pieces.get(i).position.x;
					yRecorder = pieces.get(i).position.y;
					t = true;
					dragPiece = pieces.get(i);
				}
			}

			// if mouse down
			// check if the mouse pos is on a tile piece
			// if true set a "dragging" variable to true
		}

		if (t) {
			dragPiece.position = new Vector(Input.mousePosition.x
					- dragPiece.size / 2, Input.mousePosition.y
					- dragPiece.size / 2);
		}

		if (t && Input.getMouseUp(Key.LEFT_MOUSE_BUTTON.keyValue)) {
			n = true;
			for (int i = 0; i < places.size(); i++) {
				if (dragPiece.containsPoint(places.get(i).x, places.get(i).y)) {
					dragPiece.position.x = places.get(i).x - 30;
					dragPiece.position.y = places.get(i).y - 30;
					n = false;
				}

			}
			if (n) {
				dragPiece.position.x = xRecorder;
				dragPiece.position.y = yRecorder;
			}
			dragPiece = null;
			t = false;
		}
		for (int i = 0; i < pieces.size(); i++) {
			if((pieces.get(i).containsPoint(50, 800) || pieces.get(i).containsPoint(175, 800) || pieces.get(i).containsPoint(225, 800) || pieces.get(i).containsPoint(600, 800) || pieces.get(i).containsPoint(675, 800) || pieces.get(i).containsPoint(750, 800)))
			{
				k=true;
			}
		}
		// if dragging is true
		// draw the piece at the current mouse position

		// if dragging is true and mouse is released
		// set dragging to false
	}
	public boolean sureee(Piece r, Piece n)
	{
		if(r.stack<n.stack && r.color!=n.color)
			{
			return false;
			}
		else if(r.color==n.color && r.stack==3)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public int getWidth() {
		return frame.getWidth();
	}

	public int getHeight() {
		return frame.getHeight();
	}

	@Override
	public void run() {
		try {
			while (this.isRunning) {
				this.update();
				Input.update();
				frame.repaint();
				Thread.sleep(1000 / 60);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(boards, 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(bTzaar, 170, 725, 60, 60, null);

		for (int i = 0; i < pieces.size(); i++) {
			pieces.get(i).render(g);
		}
	}

	private void setupBoard() {
		for (int i = 0; i < 5; i++) {
			for (int o = 0; o < 5; o++) {
				if (o != 2 || i != 2) {
					Point piece = new Point(o * 180 + 54 + 35,
							i * 93 + 234 + 31);
					places.add(piece);
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int o = 0; o < 4; o++) {

				Point piece = new Point(o * 180 + 144 + 35, i * 93 + 187 + 31);
				places.add(piece);

			}
		}
		for (int i = 1; i <= 3; i++) {
			for (int o = 0; o < i; o++) {

				Point piece = new Point(o * 180 + 505 + 35 - 90 * i,
						(i - 1) * 46 + 48 + 31);
				places.add(piece);
			}
			for (int o = 3; o >= i; o--) {
				Point piece = new Point(o * 180 + 145 - 90 * i + 35,
						(i - 1) * 46 + 698 + 31);
				places.add(piece);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("hi");
		new Game().start();
	}

}