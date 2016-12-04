package org.pikaju.rectangle.level.entity.terrain.liquid;

public class Lava extends Liquid {

	public Lava() {
		this(0, 0);
	}
	
	public Lava(float x, float y) {
		super(x, y);
		color = ((int) (Math.random() * 60 + 190) << 16) | ((int) (Math.random() * 60) << 8);
	}
	
	public void update() {
		super.update();
		killPlayerOnIntersect();
	}
}
