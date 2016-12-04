package org.pikaju.rectangle.level.entity.enemy.boss;

import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.Player;
import org.pikaju.rectangle.level.entity.enemy.Enemy;

public class Spider extends Enemy {

	private SpiderLeg[] legs;
	
	private float speed = 0.0f;
	private boolean lastStanding = false;
	private boolean lastDir = false;
	
	private int weakCounter = -1;
	private int damageCounter = 0;
	private int life = 3;
	
	public Spider(float x, float y) {
		super(x, y, 8, 3);
		color = 0x000060;
		legs = new SpiderLeg[3];
		for(int i = 0; i < legs.length; i++) {
			legs[i] = new SpiderLeg(x + i * width / 2.0f - 0.5f, y);
		}
		lastStanding = true;
	}
	
	public void update() {
		Player p = level.getPlayer();
		if(weakCounter == -1) {
			if(x - p.x < 7) {
				weakCounter = 0;
				y -= 2;
			}
		}
		
		if(move(speed, 0)) {
			dy = -Math.abs(speed) * 2.4f;
			dx = -speed * 1.2f;
			speed = 0;
			for(int i = 0; i < legs.length; i++) {
				legs[i].legHeight = -300;
			}
			lastStanding = true;
			weakCounter = 1;
			spawnParticles(100, 30, 0.4f, 0.6f, 0x000000, 0.0f, (float) (x + Math.random() * width - width / 2), y + height + 1 - 0.01f);
		}
		if(speed == 0) lastStanding = true;
		if(weakCounter == 0) speed += (p.x + p.width / 2 - (x + width / 2)) * 0.0002f;
		
		if(speed > 0 && lastDir == false) lastStanding = true;
		if(speed <= 0 && lastDir == true) lastStanding = true;
		if(speed > 0) lastDir = true;
		else lastDir = false;
		
		if(lastStanding) {
			for(int i = 0; i < legs.length; i++) {
				legs[i].legHeight = speed < 0 ? -i * 100.0f : -(legs.length - i - 1) * 100.0f;
			}
			lastStanding = false;
		}
		if(Math.random() < Math.abs(speed)) {
			spawnParticles(30, 2, 0.2f, 0.3f, 0x000000, 0.0f, (float) (x + Math.random() * width - width / 2), y + height + 1 - 0.01f);
		}
		
		if(weakCounter > 0) weakCounter++;
		if(weakCounter > 180) {
			y -= 2;
			weakCounter = 0;
		}
		
		height += 3;
		if(weakCounter != 0) height -= 2;
		tick();
		if(weakCounter != 0) height += 2;
		height -= 3;
		for(int i = 0; i < legs.length; i++) {
			legs[i].level = level;
			legs[i].x = (x + i * width / 2.0f - 0.5f);
			legs[i].y = (y + 2);
			if(weakCounter > 0) legs[i].y -= 1.5f;
			legs[i].update();
			legs[i].legHeight += Math.abs(speed) * 30.0f;
		}
		
		if(weakCounter == 0) {
			killPlayerOnIntersect();
		}
		if(intersects(p) && weakCounter > 0 && damageCounter == 0) {
			p.dy = -0.8f;
			damageCounter = 1;
			life--;
		}
		if(life == 0) remove();
		if(damageCounter > 0) damageCounter++;
		if(damageCounter > 120) damageCounter = 0;
	}
	
	public void render(Graphics2D g, int sx, int sy) {
		if(damageCounter % 10 > 7) return;
		super.render(g, sx, sy);
		for(int i = 0; i < legs.length; i++) {
			legs[i].render(g, sx, sy);
		}
	}
	
	public void remove() {
		super.remove(this);
		spawnParticles(-1, 100, 0.3f, 1.0f);
		LevelEnd end = new LevelEnd(x + width / 2, y + height / 2);
		level.addEntity(end);
	}
}
