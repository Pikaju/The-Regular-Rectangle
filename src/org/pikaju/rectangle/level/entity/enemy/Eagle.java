package org.pikaju.rectangle.level.entity.enemy;


public class Eagle extends Enemy {

	private float ddx;
	private float ddy;
	private float startingHeight = 0;
	private int timer = 0;
	
	public Eagle(float x, float y) {
		super(x, y, 1, 1);
		startingHeight = y;
		color = 0x008000;
		ddx = Math.random() > 0.5 ? 0.3f : -0.3f;
		
	}
	
	public void update() {
		if(timer == 0 && ddy == 0 && move(ddx, 0)) ddx = -ddx;
		if(move(0, ddy)) ddy = -ddy;
		if(y <= startingHeight) ddy = 0;
		if(Math.random() > 0.98f && ddy == 0 && timer == 0) {
			timer++;
		}
		if(timer > 0) timer++;
		if(timer > 10) {
			ddy = 0.6f;
			timer = 0;
		}
		killPlayerOnIntersect();
	}
}
