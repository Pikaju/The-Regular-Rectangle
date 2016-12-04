package org.pikaju.rectangle.level.entity.terrain.liquid;

import org.pikaju.rectangle.level.entity.Player;

public class SoloLiquidSpawner extends LiquidSpawner {
	
	private int timer = 0;
	
	public SoloLiquidSpawner(float x, float y, Class<? extends Liquid> liquid) {
		super(x, y, liquid);
		life = -1;
	}
	
	public void update() {
		Player p = level.getPlayer();
		if(Math.abs(p.x - x) > 15) return;
		timer++;
		if(timer > 3) remove(this);
		super.update();
	}
}
