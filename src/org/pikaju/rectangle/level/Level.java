package org.pikaju.rectangle.level;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import org.pikaju.rectangle.Rectangle;
import org.pikaju.rectangle.level.entity.CheckPoint;
import org.pikaju.rectangle.level.entity.Entity;
import org.pikaju.rectangle.level.entity.Player;
import org.pikaju.rectangle.level.tile.Tile;

public class Level {

	private Tile[][] tiles;
	private float sx;
	private float sy;
	
	private ArrayList<Entity> entities;
	
	private LevelManager levelManager;
	
	public Level(LevelManager levelManager) {
		this.levelManager = levelManager;
		entities = new ArrayList<Entity>();
	}
	
	public void update() {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
			if(entities.get(i).removed) {
				entities.remove(i);
			}
		}
		
		Player p = getPlayer();
		sx = p.x * Tile.SIZE - Rectangle.i.getWidth() / 2 + p.width * Tile.SIZE / 2;
		sy = p.y * Tile.SIZE - Rectangle.i.getHeight() / 5 * 3 + p.height * Tile.SIZE / 2;
	}

	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Rectangle.i.getWidth(), Rectangle.i.getHeight());

		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g, (int) -sx, (int) -sy);
		}
		
		int x0 = (int) (sx / Tile.SIZE - 1);
		int y0 = (int) (sy / Tile.SIZE - 1);
		int x1 = (int) (sx / Tile.SIZE + Rectangle.i.getWidth() / Tile.SIZE + 2);
		int y1 = (int) (sy / Tile.SIZE + Rectangle.i.getHeight() / Tile.SIZE + 2);
		
		for(int x = x0; x < x1; x++) {
			for(int y = y0; y < y1; y++) {
				getTile(x, y).render(g, (int) -sx, (int) -sy);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if(x < 0 || y < 0 || x >= tiles.length || y >= tiles[0].length) {
			Tile t = new Tile(x, y, 0x000000);
			if(y == tiles[0].length && !getTile(x, y - 1).isSolid()) t = new Tile(x, y, 0xf05030);
			return t;
		}
		return tiles[x][y];
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public void addEntity(Entity e) {
		e.level = this;
		entities.add(e);
	}
	
	public Player getPlayer() {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i) instanceof Player) {
				return (Player) entities.get(i);
			}
		}
		return null;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}

	public void setCheckPoint(CheckPoint checkPoint) {
		levelManager.checkPoints[levelManager.currentLevel] = checkPoint;
		checkPoint.l = levelManager.currentLevel;
	}

	public void respawn() {
		levelManager.reload();
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
