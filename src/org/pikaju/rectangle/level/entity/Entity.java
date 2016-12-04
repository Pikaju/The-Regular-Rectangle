package org.pikaju.rectangle.level.entity;

import java.awt.Graphics2D;

import org.pikaju.rectangle.level.Level;

public class Entity {

	public Level level;
	public boolean removed;
	
	public void update() {
		
	}
	
	public void render(Graphics2D g, int sx, int sy) {
		
	}
	
	public void remove(Object by) {
		removed = true;
	}
}
