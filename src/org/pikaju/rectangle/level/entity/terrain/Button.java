package org.pikaju.rectangle.level.entity.terrain;

import org.pikaju.rectangle.level.entity.Mob;
import org.pikaju.rectangle.level.entity.PhysicsMob;

public class Button extends Mob {

	private boolean pushed;
	private int id;
	
	public Button(float x, float y, int id) {
		super(x, y, 1, 1);
		this.id = id;
		color = 0x800080;
	}
	
	public void update() {
		pushed = false;
		for(int i = 0; i < level.getEntities().size(); i++) {
			if(!(level.getEntities().get(i) instanceof PhysicsMob)) continue;
			while(intersects(level.getEntities().get(i))) {
				pushed = true;
				((PhysicsMob) level.getEntities().get(i)).move(0, -0.00125f);
				((PhysicsMob) level.getEntities().get(i)).dy = 0.0f;
				((PhysicsMob) level.getEntities().get(i)).isOnGround = true;
			}
		}
		if(pushed) {
			for(int i = 0; i < level.getEntities().size(); i++) {
				if(level.getEntities().get(i) instanceof Door) {
					((Door) level.getEntities().get(i)).check(id);
				}
			}
		}
	}
}
