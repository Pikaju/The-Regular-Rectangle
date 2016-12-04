package org.pikaju.rectangle.level.entity.enemy;

public class OrangeBomber extends GreenBomber {

	public OrangeBomber(float x, float y) {
		super(x, y);
		normalColor = 0xf09000;
	}
	
	public void update() {
		super.update();
		timer++;
	}
	
	public void spawnMinion() {
		PurpleScum minion = new PurpleScum(x, y);
		minion.timer = 200;
		level.addEntity(minion);
	}
}
