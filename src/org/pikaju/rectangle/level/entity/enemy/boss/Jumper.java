package org.pikaju.rectangle.level.entity.enemy.boss;

import java.awt.Graphics2D;

import org.pikaju.rectangle.level.entity.Mob;
import org.pikaju.rectangle.level.entity.Player;
import org.pikaju.rectangle.level.entity.enemy.Enemy;
import org.pikaju.rectangle.level.entity.enemy.RedScum;

public class Jumper extends Enemy {
	
	private int jumpTimer = 0;
	private boolean triggered;
	private int harmTimer = 0;
	private int life = 3;
	
	private Mob[] minions;
	
	public Jumper(float x, float y) {
		super(x, y, 4, 4);
		color = 0xff00ff;
		minions = new Mob[3];
	}
	
	public void update() {
		Player player = level.getPlayer();
		if(x - player.x < 5) triggered = true;
		if(!triggered) return;
		tick();
		
		if(jumpTimer > 90) jumpTimer = 0;
		if(jumpTimer == 60 && harmTimer == 0) minions[0] = spawnMinion();
		if(jumpTimer == 70 && harmTimer == 0) minions[1] = spawnMinion();
		if(jumpTimer == 80 && harmTimer == 0) minions[2] = spawnMinion();
		if(jumpTimer == 0 && isOnGround) {
			dx = 0.2f;
			dy = -0.7f;
			if(x + width / 2.0f - player.x > 0) dx = -dx;
			jumpTimer = 0;
		}
		jumpTimer++;
		
		if(harmTimer > 0) harmTimer++;
		if(harmTimer > 180) harmTimer = 0;
		
		if(harmTimer == 0) calculatePlayerIntersection(player);
	}

	public void render(Graphics2D g, int sx, int sy) {
		if(harmTimer % 10 < 8) super.render(g, sx, sy);
	}
	
	private Mob spawnMinion() {
		RedScum minion = new RedScum(x + width / 2 - 0.5f, y + height / 2  - 0.5f);
		minion.timer = 80;
		minion.dx = 0.3f;
		minion.dy = -0.3f;
		if(Math.random() > 0.5f) minion.dx = -minion.dx;
		level.addEntity(minion);
		return minion;
	}

	private void calculatePlayerIntersection(Player p) {
		height -= 3;
		if(intersects(p)) {
			harmTimer++;
			for(int i = 0; i < minions.length; i++) {
				if(minions[i] != null) minions[i].remove(this);
			}
			p.dy = -0.5f;
			life--;
			if(life == 0) remove();
		}
		y += 1;
		height += 2;
		killPlayerOnIntersect();
		y -= 1;
		height += 1;
	}
	
	public void remove() {
		super.remove(this);
		spawnParticles(-1, 300, 0.3f, 0.8f);
		LevelEnd end = new LevelEnd(x + width / 2 - 0.5f, y + height / 2  - 0.5f);
		level.addEntity(end);
	}
}
