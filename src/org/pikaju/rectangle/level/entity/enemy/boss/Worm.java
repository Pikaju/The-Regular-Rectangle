package org.pikaju.rectangle.level.entity.enemy.boss;

import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.Mob;
import org.pikaju.rectangle.level.entity.Player;

public class Worm extends Mob {

	public static final int WORM_LENGTH = 16;
	public static final int WORM_SIZE = 2;
	public static final float WORM_DIST = 0.3f;
	
	private WormPart[] wormParts;
	
	private WormPart last;
	
	private float dx;
	private float dy;
	
	private boolean triggered;
	
	private float life = 128;
	private boolean lastInGround = false;
	
	public Worm(float x, float y) {
		super(x, y + 20, 0, 0);
		noclip = true;
		WormPart attachment = null;
		color = 0x103000;
		wormParts = new WormPart[WORM_LENGTH];
		for(int i = 0; i < wormParts.length; i++) {
			attachment = new WormPart(this.x, this.y, attachment, this);
			if(i == 0) last = attachment;
			wormParts[i] = attachment;
		}
	}
	
	public void update() {
		Player p = level.getPlayer();
		if(p.x > x + 5) {
			triggered = true;
		}
		if(!triggered) return;
		
		if(life < 0) life = 0;
		calculateColor();
		if(level.getTile((int) x, (int) y).color == 0x000080) life -= 5;
		if(life <= 0 && y > 1 && !level.getTile((int) x, (int) y + 1).isSolid()) {
			remove();
		}
		
		boolean inSolid = level.getTile((int) x, (int) y).isSolid();
		if(inSolid != lastInGround) spawnParticles(120, 30, 0.6f, 0.5f, 0x000000, 1.5f);
		lastInGround = inSolid;
		
		boolean inGround = y > level.getTiles()[0].length + 4;
		inGround = level.getTile((int) x, (int) y).isSolid() && y > 1;
		if(inGround) {
			dy -= 0.05f;
			dy += (Math.random() - Math.random()) * 0.05f;
		} else {
			dy += 0.05f;
		}
		if(inGround) {
			dx += (p.x - x) * 0.003f;
			dx += (Math.random() - Math.random()) * 0.05f;
		} else {
			dx += (p.x - x) * 0.001f;
		}
		dx *= 0.99f;
		float max = 3;
		if(dy < -max) dy = -max;
		if(dy > max) dy = max;
		
		move(dx * 0.2f, dy * 0.2f);
		
		last.x = x - last.width / 2.0f;
		last.y = y - last.height / 2.0f;
		for(int i = 0; i < wormParts.length; i++) {
			wormParts[i].level = level;
			wormParts[i].update();
		}
	}

	public void render(Graphics2D g, int sx, int sy) {
		if(!triggered) return;
		super.render(g, sx, sy);
		for(int i = 0; i < wormParts.length; i++) {
			wormParts[i].render(g, sx, sy);
		}
	}
	
	private void calculateColor() {
		color = ((256 - (int) life * 2) << 16) | 0x002000 | ((128 - (int) life) << 0);
	}
	
	public void remove() {
		super.remove(this);
		LevelEnd end = new LevelEnd(x, y);
		level.addEntity(end);
		spawnParticles(-1, 200, 0.4f, 3);
	}
}
