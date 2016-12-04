package org.pikaju.rectangle.level.entity.enemy.boss;

import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.enemy.Enemy;

public class SpiderLeg extends Enemy {

	public float legHeight = 0;
	private float yo;
	
	public SpiderLeg(float x, float y) {
		super(x, y, 1, 4);
		color = 0x000060;
	}
	
	public void update() {
		yo = (float) (Math.sin(Math.toRadians(legHeight % 300 + 60)) * 2.5f);
		if(legHeight < 0) yo = 0;
		if(yo > 0) yo = 0;
		super.update();
		y += yo;
		killPlayerOnIntersect();
		y -= yo;
	}
	
	public void render(Graphics2D g, int sx, int sy) {
		y += yo;
		super.render(g, sx, sy);
		y -= yo;
	}
}
