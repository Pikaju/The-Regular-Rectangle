package org.pikaju.rectangle.level.entity.enemy.boss;

import org.pikaju.rectangle.level.entity.PhysicsMob;

public class LevelEnd extends PhysicsMob {

	public LevelEnd(float x, float y) {
		super(x, y, 1, 1);
	}
	
	public void update() {
		color = (int) (Math.random() * 0xffffff);
		tick();
		if(intersects(level.getPlayer())) {
			level.getLevelManager().nextLevel();
		}
	}
}
