package org.pikaju.rectangle.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

	public static Input i;
	
	public static int BUTTON_LEFT = MouseEvent.BUTTON1;
	public static int BUTTON_RIGHT = MouseEvent.BUTTON2;
	public static int BUTTON_MIDDLE = MouseEvent.BUTTON3;
	
	public static int KEY_UP = KeyEvent.VK_W;
	public static int KEY_DOWN = KeyEvent.VK_S;
	public static int KEY_LEFT = KeyEvent.VK_A;
	public static int KEY_RIGHT = KeyEvent.VK_D;
	
	public static int KEY_QUIT = KeyEvent.VK_ESCAPE;
	
	public static int KEY_UP_A = KeyEvent.VK_UP;
	public static int KEY_DOWN_A = KeyEvent.VK_DOWN;
	public static int KEY_LEFT_A = KeyEvent.VK_LEFT;
	public static int KEY_RIGHT_A = KeyEvent.VK_RIGHT;
	
	private boolean[] keys;
	private boolean[] buttons;
	
	private int x;
	private int y;
	private int lx;
	private int ly;
	private int dx;
	private int dy;

	public Input() {
		keys = new boolean[65536];
		buttons = new boolean[20];
	}
	
	public void init() {
		/*
		try {
			KEY_UP = Save.getDIS().readInt();
			KEY_DOWN = Save.getDIS().readInt();
			KEY_LEFT = Save.getDIS().readInt();
			KEY_RIGHT = Save.getDIS().readInt();
			KEY_QUIT = Save.getDIS().readInt();
		} catch (IOException e) {
			return;
		}*/
	}
	
	public void cleanup() {
		try {
			Save.getDOS().writeInt(KEY_UP);
			Save.getDOS().writeInt(KEY_DOWN);
			Save.getDOS().writeInt(KEY_LEFT);
			Save.getDOS().writeInt(KEY_RIGHT);
			Save.getDOS().writeInt(KEY_QUIT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void refresh() {
		dx = x - lx;
		dy = y - ly;
		lx = x;
		ly = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDX() {
		return dx;
	}
	
	public int getDY() {
		return dy;
	}
	
	public boolean isKeyDown(int key) {
		return keys[key];
	}
	
	public boolean isButtonDown(int button) {
		return buttons[button];
	}
	
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
		
	}

	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	public boolean inRect(int x, int y, int width, int height) {
		return getX() >= x && getY() >= y && getX() < x + width && getY() < y + height;
	}
}
