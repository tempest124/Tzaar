package com.leonmontealegre.util;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import really.game.Game;

/**
 * The Input class. This class handles all input.
 * @author Leon Montealegre
 * @version 0.01
 */
public final class Input {
	
	/**
	 * Array of all the keys that have been pressed for the first time.
	 */
	private static boolean[] firstPressedKeys;
	
	/**
	 * Array of all the keys that are being held.
	 */
	private static boolean[] pressedKeys;
	
	/**
	 * Array of all the keys that have been released.
	 */
	private static boolean[] releasedKeys;
	
	/**
	 * Array of all the mouse buttons that have been pressed for the first time.
	 */
	private static boolean[] mouseFirstPressed;
	
	/**
	 * Array of all the mouse buttons that are being held.
	 */
	private static boolean[] mousePressed;
	
	/**
	 * Array of all the mouse buttons that have been released.
	 */
	private static boolean[] mouseReleased;
	
	/**
	 * The current position of the mouse.
	 */
	public static Vector mousePosition;
	
	/**
	 * Initiates and starts this class.
	 * @param game The game to listen for input from.
	 */
	public static void initiate(Game game) {
		firstPressedKeys = new boolean[255];
		pressedKeys = new boolean[255];
		releasedKeys = new boolean[255];
		mouseFirstPressed = new boolean[3];
		mousePressed = new boolean[3];
		mouseReleased = new boolean[3];
		new Input(game);
	}
	
	
	/**
	 * Creates a new Input listener.
	 * @param game The game to assign all the listeners to.
	 */
	private Input(Game game) {
		KeyListener keyListener = new MyKeyListener();
		MouseListener mouseListener = new MyMouseListener();
		MouseMotionListener mouseMotion = new MyMouseMotionListener();
		game.addKeyListener(keyListener);
		game.addMouseListener(mouseListener);
		game.addMouseMotionListener(mouseMotion);
	}
	
	/**
	 * Gets if a key was pressed for the first time after being released.
	 * @param key The key to check for.
	 * @return If the key was pressed for the first time after being released.
	 */
	public static boolean getKeyDown(Key key) {
		if (firstPressedKeys[key.keyValue]) {
			firstPressedKeys[key.keyValue] = false;
			return true;
		}
		return false;
	}
	
	public static void update()
	{
		
	
	}
	/**
	 * Gets if a key is being held.
	 * @param key The key to check for.
	 * @return If the key is being held.
	 */
	public static boolean getKey(Key key) {
		return pressedKeys[key.keyValue];
	}
	
	/**
	 * Gets if a key was released.
	 * @param key The key to check for.
	 * @return If they key was released.
	 */
	public static boolean getKeyUp(Key key) {
		if (releasedKeys[key.keyValue]) {
			releasedKeys[key.keyValue] = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets if a mouse button was pressed for the first time.
	 * @param button The mouse button to check for.
	 * @return If the mouse button was pressed for the first time.
	 */
	public static boolean getMouseDown(int button) {
		if (mouseFirstPressed[button]) {
			mouseFirstPressed[button] = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Gets if a mouse button is being held.
	 * @param button The mouse button to check for.
	 * @return If the mouse button is being held.
	 */
	public static boolean getMouse(int button) {
		return mousePressed[button];
	}
	
	/**
	 * Gets if a mouse button was released.
	 * @param button The mouse button to check for.
	 * @return If the mouse button was released.
	 */
	public static boolean getMouseUp(int button) {
		if (mouseReleased[button]) {
			mouseReleased[button] = false;
			return true;
		}
		return false;
	}
	
	/**
	 * Listens for Key Input.
	 * @author Leon Montealegre
	 * @version 0.01
	 */
	private class MyKeyListener implements KeyListener {
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyPressed(KeyEvent e) {
			this.updateKeys(e.getKeyCode(), true);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			this.updateKeys(e.getKeyCode(), false);
		}
		
		/**
		 * Updates the keys appropriately.
		 * @param code The key code pressed or released.
		 * @param pressed If the key was pressed or released.
		 */
		private void updateKeys(int code, boolean pressed) {
			if (code < 255) {
				if (!pressedKeys[code])
					firstPressedKeys[code] = pressed;
				pressedKeys[code] = pressed;
				releasedKeys[code] = !pressed;
			}
		}
		
	}
	
	/**
	 * Listens for Mouse Input.
	 * @author Leon Montealegre
	 * @version 0.01
	 */
	private class MyMouseListener extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent e) {}
		
		@Override
		public void mouseExited(MouseEvent e) {}
		
		@Override
		public void mouseEntered(MouseEvent e) {}
		
		@Override
		public void mousePressed(MouseEvent e) {
			this.updateMouse(e.getButton(), true);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			this.updateMouse(e.getButton(), false);
		}
		
		/**
		 * Updates the mouse buttons appropriately.
		 * @param button The mouse button pressed or released.
		 * @param pressed If the mouse button was pressed or released.
		 */
		private void updateMouse(int button, boolean pressed) {
			if (button == 2) button = 3;
			else if (button == 3) button = 2;
			if (button > 0) {
				if (!mousePressed[button-1])
					mouseFirstPressed[button-1] = pressed;
				mousePressed[button-1] = pressed;
				mouseReleased[button-1] = !pressed;
			}
		}
		
	}
	
	/**
	 * Listens for Mouse Movement.
	 * @author Leon Montealegre
	 * @version 0.01
	 */
	private class MyMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			Input.mousePosition = new Vector(e.getX(), e.getY());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			Input.mousePosition = new Vector(e.getX(), e.getY());
		}
		
	}
	
}
