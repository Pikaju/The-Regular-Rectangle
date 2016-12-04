package org.pikaju.rectangle.level.entity.enemy;

public class PurpleScum extends RedScum {

	public PurpleScum(float x, float y) {
		super(x, y);
		color = 0xff0080;
	}

	public void update() {
		if(isOnGround) {
			dy = -0.3f;
			dx = ddx * 1;
		}
		super.update();
	}
}
