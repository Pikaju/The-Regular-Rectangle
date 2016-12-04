package org.pikaju.rectangle.level.entity.terrain.liquid;

import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.Player;
import org.pikaju.rectangle.level.entity.enemy.Enemy;

public class Liquid extends Enemy {

	private static final float SIZE = 0.5f;
	public int timer = -1;
	
	public Liquid(float x, float y) {
		super(x, y, SIZE, SIZE);
		color = ((int) (Math.random() * 50 + 50) << 8) | ((int) (Math.random() * 50 + 200) << 0);
		timer = 120;
	}
	
	public Liquid() {
		this(0, 0);
	}
	
	public void update() {
		if(timer > 0) timer--;
		if(timer == 0) remove(this);
		Player p = level.getPlayer();
		if(Math.abs(p.x - x) > 15) return;
		
		dy += 0.03f;
		dx *= 0.99f;
		if(move(0, dy)) dy = 0;
		if(move(dx, 0)) dx = -dx * 0.5f;
		
		if(y > level.getTiles()[0].length) {
			remove(this);
		}
		for(int i = 0; i < level.getEntities().size(); i++) {
			if(level.getEntities().get(i) instanceof Liquid) {
				handleLiquid(((Liquid) level.getEntities().get(i)));
			}
		}
	}

	protected void handleLiquid(Liquid l) {
		if(l == this) return;
		if(intersects(l)) {
			float dx = (l.x - x);
			float dy = (l.y - y);
			if(dx < 0) dx = -SIZE + Math.abs(dx);
			else dx = SIZE - dx;
			if(dy < 0) dy = -SIZE + Math.abs(dy);
			else dy = SIZE - dy;
			if(dy < 0) l.dy = 0;
			l.dx += (dx * 0.08f);
			l.dy += (dy * 0.08f);
			l.move(dx * 1.0f, dy * 1.0f);
		}
	}
	
	public void render(Graphics2D g, int sx, int sy) {
		x -= SIZE / 2.0f;
		y -= SIZE / 2.0f;
		width += SIZE;
		height += SIZE;
		super.render(g, sx, sy);
		x += SIZE / 2.0f;
		y += SIZE / 2.0f;
		width -= SIZE;
		height -= SIZE;
	}
}
