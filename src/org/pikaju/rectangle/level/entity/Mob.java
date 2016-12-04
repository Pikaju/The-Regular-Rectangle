package org.pikaju.rectangle.level.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.enemy.Particle;
import org.pikaju.rectangle.level.tile.Tile;

public class Mob extends Entity {

	public float x;
	public float y;
	public float width;
	public float height;
	public int color;
	protected boolean noclip = false;
	
	public Mob(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void render(Graphics2D g, int sx, int sy) {
		g.setColor(new Color(color));
		g.fillRect((int) (x * Tile.SIZE + sx), (int) (y * Tile.SIZE + sy), (int) (width * Tile.SIZE + 1), (int) (height * Tile.SIZE + 1));
	}
	
	public boolean move(float dx, float dy) {
		float movement = 0.0125f;
		boolean intersects = false;
		for(float xx = 0; xx < Math.abs(dx); xx += movement) {
			x += dx < 0 ? -movement : movement;
			if(collision()) {
				x -= dx < 0 ? -movement : movement;
				intersects = true;
				break;
			}
		}
		for(float yy = 0; yy < Math.abs(dy); yy += movement) {
			y += dy < 0 ? -movement : movement;
			if(collision()) {
				y -= dy < 0 ? -movement : movement;
				intersects = true;
				break;
			}
		}
		return intersects;
	}
	
	public boolean collision() {
		if(noclip) return false;
		float xa = 0.999f;
		float ya = 0.999f;
		if(width < 1) xa = width - 0.01f;
		if(height < 1) ya = height - 0.01f;
		for(float xx = x; xx < x + width; xx += xa) {
			for(float yy = y; yy < y + height; yy += ya) {
				level.getTile((int) (xx), (int) (yy)).steppedOn(this);
				if(level.getTile((int) (xx), (int) (yy)).isSolid()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean intersects(Entity e) {
		if(!(e instanceof Mob)) return false;
		Mob m = (Mob) e;
		return intersects(m.x, m.y, m.width, m.height);
	}
	
	public boolean intersects(float x, float y, float width, float height) {
		return x < this.x + this.width && y < this.y + this.height && x + width > this.x && y + height > this.y;
	}
	
	public void spawnParticles(int timer, int amount, float size, float speed) {
		spawnParticles(timer, amount, size, speed, color, 0);
	}
	
	public void spawnParticles(int timer, int amount, float size, float speed, int color, float spread) {
		spawnParticles(timer, amount, size, speed, color, spread, x, y);
	}
	
	public void spawnParticles(int timer, int amount, float size, float speed, int color, float spread, float x, float y) {
		for(int i = 0; i < amount; i++) {
			float dx = (float) (Math.random() - Math.random()) * speed;
			float dy = (float) (Math.random() - Math.random()) * speed;
			float xo = (float) (spread * (Math.random() - Math.random()));
			float yo = (float) (spread * (Math.random() - Math.random()));
			Particle p = new Particle(x + width * 0.5f - size / 2.0f + xo, y + height * 0.5f - size / 2.0f + yo, size, size, dx, dy, color, timer);
			level.addEntity(p);
		}
	}
}
