package org.pikaju.rectangle.level.entity.terrain.liquid;

public class Water extends Liquid {

	public Water() {
		super();
	}

	public Water(float x, float y) {
		super(x, y);
	}
	
	protected void handleLiquid(Liquid l) {
		if(l != this && !removed && intersects(l)) {
			if(l instanceof Lava) {
				l.remove(this);
				remove(l);
			}
		}
		super.handleLiquid(l);
	}
}
