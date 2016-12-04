package org.pikaju.rectangle.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.pikaju.rectangle.level.entity.CheckPoint;
import org.pikaju.rectangle.level.entity.Entity;
import org.pikaju.rectangle.level.entity.Player;
import org.pikaju.rectangle.level.entity.enemy.Eagle;
import org.pikaju.rectangle.level.entity.enemy.GreenBomber;
import org.pikaju.rectangle.level.entity.enemy.OrangeBomber;
import org.pikaju.rectangle.level.entity.enemy.PurpleScum;
import org.pikaju.rectangle.level.entity.enemy.RedScum;
import org.pikaju.rectangle.level.entity.enemy.YellowFountain;
import org.pikaju.rectangle.level.entity.enemy.boss.Jumper;
import org.pikaju.rectangle.level.entity.enemy.boss.Spider;
import org.pikaju.rectangle.level.entity.enemy.boss.Worm;
import org.pikaju.rectangle.level.entity.terrain.Button;
import org.pikaju.rectangle.level.entity.terrain.Door;
import org.pikaju.rectangle.level.entity.terrain.Platform;
import org.pikaju.rectangle.level.entity.terrain.liquid.Lava;
import org.pikaju.rectangle.level.entity.terrain.liquid.LiquidSpawner;
import org.pikaju.rectangle.level.entity.terrain.liquid.SoloLiquidSpawner;
import org.pikaju.rectangle.level.entity.terrain.liquid.Water;
import org.pikaju.rectangle.level.tile.Tile;

public class LevelLoader {

	public static Level load(String path, LevelManager levelManager) {
		Level level = new Level(levelManager);		
		try {
			BufferedImage image = ImageIO.read(LevelLoader.class.getResource(path));
			Tile[][] tiles = new Tile[image.getWidth()][image.getHeight()];
			for(int x = image.getWidth() - 1; x >= 0; x--) {
				for(int y = 0; y < image.getHeight(); y++) {
					int color = image.getRGB(x, y) & 0xffffff;
					Entity e = createEntity(x, y, color);
					if(e == null) {
						tiles[x][y] = new Tile(x, y, color);
					} else {
						tiles[x][y] = new Tile(x, y, 0xffffff);
						level.addEntity(e);
					}
				}
			}
			
			level.setTiles(tiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return level;
	}

	private static Entity createEntity(int x, int y, int color) {
		if(color == 0x0000ff) return new Player(x, y);
		if(color == 0x00a0ff) return new CheckPoint(x, y);
		if(color == 0xff0000) return new RedScum(x, y);
		if(color == 0xff0080) return new PurpleScum(x, y);
		if(color == 0x00ff00) return new GreenBomber(x, y);
		if(color == 0xf09000) return new OrangeBomber(x, y);
		if(color == 0x80ff00) return new YellowFountain(x, y);
		if(color == 0xff00ff) return new Jumper(x, y);
		if(color == 0x103000) return new Worm(x, y);
		if(color == 0x000060) return new Spider(x, y);
		if(color == 0x008000) return new Eagle(x, y);
		if(color == 0x21007f) return new Platform(x, y, 2, 1);
		if(color == 0x00ffff) return new LiquidSpawner(x, y, Water.class);
		if(color == 0xffa000) return new LiquidSpawner(x, y, Lava.class);
		if(color == 0xff4000) return new SoloLiquidSpawner(x, y, Lava.class);
		if(color == 0x0040ff) return new SoloLiquidSpawner(x, y, Water.class);
		if((color & 0xff00ff) == 0xc00050) return new Door(x, y, ((color & 0x00ff00) >> 8));
		if((color & 0xff00ff) == 0x800080) return new Button(x, y, ((color & 0x00ff00) >> 8));
		return null;
	}
}
