package org.pikaju.rectangle.level.entity.enemy;

import org.pikaju.rectangle.level.entity.Mob;

public class YellowFountain extends Mob {

	private int timer;

	public YellowFountain(float x, float y) {
		super(x, y, 1, 1);
		color = 0x80ff00;
	}
	
	public void update() {
		timer++;
		if(timer > 60) timer = 0;
		if(timer == 0) {
			RedScum redScum = new RedScum(x, y);
			redScum.dy = -0.9f;
			redScum.timer = 60;
			level.addEntity(redScum);
		}
	}
}
