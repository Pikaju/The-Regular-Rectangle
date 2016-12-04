package org.pikaju.rectangle.level.entity.terrain;

import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.enemy.Enemy;
import org.pikaju.rectangle.level.entity.terrain.liquid.LiquidSpawner;

public class Door extends Enemy {

	private int id;
	private int open = 0;
	
	public Door(float x, float y, int id) {
		super(x, y, 1, 1);
		this.id = id;
		color = 0xc00050;
	}
	
	public void update() {
		if(open > 0) {
			open--;
			return;
		}
		for(int i = 0; i < level.getEntities().size(); i++) {
			if(level.getEntities().get(i) instanceof Door) continue;
			if(level.getEntities().get(i) instanceof LiquidSpawner) continue;
			if(intersects(level.getEntities().get(i))) {
				level.getEntities().get(i).remove(this);
			}
		}
	}
	
	public void render(Graphics2D g, int sx, int sy) {
		if(open == 0) super.render(g, sx, sy);
	}
	
	public void check(int id) {
		if(id == this.id) open = 10;
	}
}
