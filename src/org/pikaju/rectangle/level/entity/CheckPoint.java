package org.pikaju.rectangle.level.entity;

public class CheckPoint extends Mob {
	
	public int l = 0;
	
	public CheckPoint(float x, float y) {
		super(x, y, 1, 1);
		color = 0x00a0ff;
	}
	
	public void update() {
		if(intersects(level.getPlayer())) {
			if(level.getLevelManager().checkPoints[level.getLevelManager().currentLevel] != this) {
				spawnParticles(60, 50, 0.1f, 0.3f, color, 0.5f);
			}
			level.setCheckPoint(this);
		}
	}
}
