package org.pikaju.rectangle.level.entity;

public class PhysicsMob extends Mob {

	public boolean isOnGround = false;
	
	public float dx;
	public float dy;
	
	protected boolean stickToWalls = false;
	
	public PhysicsMob(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void tick() {
		dy += 0.03f;
		if(move(0, dy)) {
			if(dy > 0) isOnGround = true;
			if(stickToWalls && dy < 0) dy -= 0.03f;
			dy = 0;
		}
		if(dy != 0) isOnGround = false;
		if(move(dx, 0) && stickToWalls) dy = -0.03f;
		if(isOnGround) dx = 0;
	}
}
