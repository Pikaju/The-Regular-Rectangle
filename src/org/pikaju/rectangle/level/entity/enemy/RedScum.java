package org.pikaju.rectangle.level.entity.enemy;

public class RedScum extends Enemy {

	protected float ddx;
	public int timer = -1;
	
	public RedScum(float x, float y) {
		super(x, y, 1, 1);
		ddx = Math.random() > 0.5f ? -0.2f : 0.2f;
		color = 0xff0000;
	}
	
	public void update() {
		if(isOnGround) {
			if(move(ddx, 0)) ddx = -ddx;
		}
		tick();
		killPlayerOnIntersect();
		if(timer > 0) timer--;
		if(timer == 0) remove();
	}
	
	public void remove() {
		super.remove(this);
		spawnParticles(30, 20, 0.1f, 0.2f);
	}
}
