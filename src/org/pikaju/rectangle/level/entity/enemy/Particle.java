package org.pikaju.rectangle.level.entity.enemy;

import org.pikaju.rectangle.level.entity.PhysicsMob;

public class Particle extends PhysicsMob {

	private int timer = 0;
	
	public Particle(float x, float y, float width, float height, float dx, float dy, int color, int timer) {
		super(x, y, width, height);
		this.color = color;
		this.dx = dx;
		this.dy = dy;
		this.timer = timer;
		stickToWalls = true;
	}
	
	public void update() {
		if(timer == 0) remove(this);
		if(timer > 0) timer--;
		tick();
	}
}
