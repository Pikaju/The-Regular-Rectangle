package org.pikaju.rectangle.level.entity.enemy;

import org.pikaju.rectangle.level.entity.PhysicsMob;
import org.pikaju.rectangle.level.entity.Player;

public class Enemy extends PhysicsMob {

	public Enemy(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void killPlayerOnIntersect() {
		Player p = level.getPlayer();
		if(intersects(p)) {
			p.remove(this);
		}
	}
}
