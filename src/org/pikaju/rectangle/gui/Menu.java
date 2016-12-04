package org.pikaju.rectangle.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import org.pikaju.rectangle.Game;
import org.pikaju.rectangle.Rectangle;
import org.pikaju.rectangle.level.LevelManager;

public class Menu {

	public static final int MAIN = 0;
	public static final int LEVELSELECTION = 1;
	public static final int SETTINGS = 2;
	
	public int state = MAIN;
	
	//MAIN
	public TextButton play;
	public TextButton quit;
	
	public TextButton back;
	
	//LEVELSELECTION
	public TextButton[] levels;
	
	//SETTINGS
	
	
	public Menu() {
		play = new TextButton("Play", 30, 330, 300, 40);
		quit = new TextButton("Quit", 30, 380, 300, 40);
		
		back = new TextButton("Back", 30, 430, 300, 40);
		
		levels = new TextButton[LevelManager.LEVEL_AMOUNT];
		for(int i = 0; i < levels.length; i++) {
			levels[i] = new TextButton("Level " + (i + 1), 140, 170 + i * 40, 300, 40);
		}
	}
	
	public void update() {
		if(state == MAIN) {
			play.update(); if(play.wasClicked()) state = LEVELSELECTION;
			quit.update(); if(quit.wasClicked()) Rectangle.i.stop();
		} else {
			//back.update(); if(back.wasClicked()) state = MAIN;
		}
		if(state == LEVELSELECTION) {
			for(int i = 0; i < levels.length; i++) {
				int levelData = Rectangle.i.game.levelManager.levelData[i];
				//Rectangle.i.game.levelManager.levelData[i] = 2;
				if(levelData == 0) levels[i].textColor = 0x909090;
				if(levelData == 1) levels[i].textColor = -1;
				if(levelData == 2) levels[i].textColor = 0x00a000;
				levels[i].update();
				if(levels[i].wasClicked()) {
					Rectangle.i.game.state = Game.PLAY;
					Rectangle.i.game.levelManager.currentLevel = i;
					Rectangle.i.game.levelManager.load();
					System.out.println("Loading level " + (i + 1) + "...");
				}
			}
		}
		if (state == SETTINGS) {
			
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Rectangle.i.getWidth(), Rectangle.i.getHeight());
		if(state == MAIN) {
			play.render(g);
			quit.render(g);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", 0, 40));
			g.drawString("The Regular Rectangle", 130, 200);
		} else {
			//back.render(g);
		}
		if(state == LEVELSELECTION) {
			for(int i = 0; i < levels.length; i++) {
				levels[i].render(g);
			}
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", 0, 40));
			g.drawString("Levels", 130, 100);
		}
		if (state == SETTINGS) {
			
		}
	}
}
