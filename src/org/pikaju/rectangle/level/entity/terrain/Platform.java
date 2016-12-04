package org.pikaju.rectangle.level.entity.terrain;

import org.pikaju.rectangle.level.entity.Entity;
import org.pikaju.rectangle.level.entity.Mob;
import org.pikaju.rectangle.level.entity.PhysicsMob;

public class Platform extends Mob {

	private float ddx;
	
	public Platform(float x, float y, float width, float height) {
		super(x, y, width, height);
		ddx = 0.2f;
		color = 0x21007f;
	}
	
	public void update() {
		for(int i = 0; i < level.getEntities().size(); i++) {
			Entity e = level.getEntities().get(i);
			if(e instanceof PhysicsMob) {
				handleMob((PhysicsMob) e);
			}
		}
		
		if(move(ddx, 0)) ddx = -ddx;		
	}

	public void handleMob(PhysicsMob m) {
		if(intersects(m)) {			
			m.move(ddx, 0);
		}
		if(intersects(m) && m.dy < 0) {
			m.dy = 0;
			return;
		}
		if(m.y + m.height / 2 > y) return;
		if(intersects(m)) {			
			m.dy = 0;
			m.isOnGround = true;
		}
		while(intersects(m)) {
			m.y -= 0.0125f;
		}
	}
}