package org.pikaju.rectangle.level.entity.enemy;

import org.pikaju.rectangle.level.entity.Mob;

public class GreenBomber extends Mob {

	private float ddx;
	protected int timer = 0;
	protected int normalColor;
	
	public GreenBomber(float x, float y) {
		super(x, y, 1, 1);
		ddx = 0.3f;
		normalColor = 0x00ff00;
	}
	
	public void update() {
		if(move(ddx, 0)) ddx = -ddx;
		if(timer > 120) timer = 0;
		if(timer > 90) color = 0xff0000;
		else color = normalColor;
		if(timer == 0) {
			spawnMinion();
		}
		timer++;
	}
	
	public void spawnMinion() {
		RedScum redScum = new RedScum(x, y);
		redScum.timer = 200;
		level.addEntity(redScum);
	}
}
