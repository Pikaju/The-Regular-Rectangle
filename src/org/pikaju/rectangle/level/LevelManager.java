package org.pikaju.rectangle.level;

import java.awt.Graphics2D;
import java.io.IOException;

import org.pikaju.rectangle.Game;
import org.pikaju.rectangle.Rectangle;
import org.pikaju.rectangle.io.Save;
import org.pikaju.rectangle.level.entity.CheckPoint;

public class LevelManager {

	public static final int LEVEL_AMOUNT = 3;
	private Level level;
	public int[] levelData;
	public int currentLevel;
	public CheckPoint[] checkPoints;

	public LevelManager() {
		levelData = new int[LEVEL_AMOUNT];
		for(int i = 0; i < levelData.length; i++) {
			if(i == 0) levelData[i] = 1;
		}
		checkPoints = new CheckPoint[levelData.length];
	}
	
	public void update() {
		level.update();
	}
	
	public void render(Graphics2D g) {
		level.render(g);
	}

	public void load() {
		load("/levels/level" + currentLevel + ".png");
		if(checkPoints[currentLevel] == null) return;
		level.getPlayer().x = checkPoints[currentLevel].x;
		level.getPlayer().y = checkPoints[currentLevel].y;
	}
	
	public void reload() {
		load();
	}

	private void load(String fileName) {
		level = LevelLoader.load(fileName, this);
	}

	public void nextLevel() {
		levelData[currentLevel] = 2;
		if(levelData[currentLevel + 1] == 0) levelData[currentLevel + 1] = 1;
		Rectangle.i.game.state = Game.MENU;
	}
	
	public void init() {
		try {
			int levelDataAmount = Save.getDIS().readInt();
			for(int i = 0; i < levelDataAmount && i < levelData.length; i++) {
				levelData[i] = Save.getDIS().readInt();
				levelData[i] = 2;
			}
			checkPoints = new CheckPoint[levelData.length];
			if(Save.getDIS().readBoolean()) {
				for(int i = 0; i < checkPoints.length; i++) {
					checkPoints[i] = new CheckPoint(Save.getDIS().readFloat(), Save.getDIS().readFloat());
					checkPoints[i].l = i;
					if(checkPoints[i].y == -1) checkPoints[i] = null;
					checkPoints[i] = null;
				}
			}
		} catch (IOException e) {
			return;
		}
	}
	
	public void cleanup() {
		try {
			Save.getDOS().writeInt(levelData.length);
			for(int i = 0; i < levelData.length; i++) {
				Save.getDOS().writeInt(levelData[i]);
			}
			Save.getDOS().writeBoolean(false);
			for(int i = 0; i < checkPoints.length; i++) {
				if(checkPoints[i] == null) {
					Save.getDOS().writeFloat(-1);
					Save.getDOS().writeFloat(-1);
					continue;
				}
				Save.getDOS().writeFloat(checkPoints[i].x);
				Save.getDOS().writeFloat(checkPoints[i].y);
			}
		} catch (IOException e) {
			return;
		}
	}
}