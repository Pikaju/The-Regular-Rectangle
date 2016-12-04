package org.pikaju.rectangle.level.entity.enemy.boss;

import org.pikaju.rectangle.level.entity.enemy.Enemy;

public class WormPart extends Enemy {
	
	private WormPart attachment;
	private Worm worm;
	
	private float dirtTimer = 0;
	
	public WormPart(float x, float y, WormPart attachment, Worm worm) {
		super(x, y, Worm.WORM_SIZE, Worm.WORM_SIZE);
		this.worm = worm;
		this.attachment = attachment;
	}
	
	public void update() {
		if(attachment != null) {			
			x += (attachment.x - x) * Worm.WORM_DIST;
			y += (attachment.y - y) * Worm.WORM_DIST;
		}
		int dirtDuration = 60;
		if(level.getTile((int) (x + width / 2), (int) (y + height / 2)).isSolid()) dirtTimer = dirtDuration;
		if(dirtTimer > 0) dirtTimer -= 0.7f;
		
		if(Math.random() < dirtTimer / (float) dirtDuration - 0.3f) spawnParticles(70, 1, 0.2f, 0.0f, 0x000000, 2);
		color = worm.color;
		killPlayerOnIntersect();
	}
}
