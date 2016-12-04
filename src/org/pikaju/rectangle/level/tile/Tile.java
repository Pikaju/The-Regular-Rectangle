package org.pikaju.rectangle.level.tile;

import java.awt.Color;
import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.Mob;
import org.pikaju.rectangle.level.entity.terrain.Platform;

public class Tile {
	
	public static final int SIZE = 32;
	
	private int x;
	private int y;
	public int color;
	
	public Tile(int x, int y, int color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void update() {
	}
	
	public void render(Graphics2D g, int sx, int sy) {
		if(color == 0xffffff) return;
		g.setColor(new Color(color));
		g.fillRect(x * SIZE + sx, y * SIZE + sy, SIZE, SIZE);
	}
	
	public void steppedOn(Mob m) {
		if(color == 0x505050) {
			if(!(m instanceof Platform)) {
				m.remove(this);
			}
		}
	}
	
	public boolean isSolid() {
		return color != 0xffffff && color != 0xf05030;
	}
}
