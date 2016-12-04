package org.pikaju.rectangle;

import java.awt.Graphics2D;

import org.pikaju.rectangle.gui.Menu;
import org.pikaju.rectangle.io.Input;
import org.pikaju.rectangle.io.Save;
import org.pikaju.rectangle.level.LevelManager;

public class Game {

	public static final int MENU = 0;
	public static final int PLAY = 1;
	
	public int state = 0;
	
	public LevelManager levelManager;
	public Menu menu;
	
	public Game() {
	}
	
	public void init() {
		levelManager = new LevelManager();
		
		Save.read();
		if(Save.getDIS() != null) {
			levelManager.init();
			Input.i.init();
		}
		Save.close();
		menu = new Menu();
	}
	
	public void cleanup() {
		Save.write();
		levelManager.cleanup();
		Input.i.cleanup();
		Save.close();
	}
	
	public void update() {
		if(Input.i.isKeyDown(Input.KEY_QUIT)) state = MENU;
		if(state == MENU) menu.update();
		if(state == PLAY) levelManager.update();
	}
	
	public void render(Graphics2D g) {
		if(state == MENU) menu.render(g);
		if(state == PLAY) levelManager.render(g);
	}
}
