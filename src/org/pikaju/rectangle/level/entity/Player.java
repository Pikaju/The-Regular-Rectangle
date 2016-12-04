package org.pikaju.rectangle.level.entity;

import java.awt.Graphics2D;

import org.pikaju.rectangle.io.Input;

public class Player extends PhysicsMob {

	private int deadTimer = 0;
	
	public Player(float x, float y) {
		super(x, y, 1, 1);
		color = 0x0000ff;
	}
	
	public void update() {
		if(deadTimer != 0) {
			deadTimer++;
			if(deadTimer > 80) level.respawn();
			return;
		}
		dx = 0;
		if(Input.i.isKeyDown(Input.KEY_LEFT)) dx -= 0.2f;
		if(Input.i.isKeyDown(Input.KEY_RIGHT)) dx += 0.2f;
		if(Input.i.isKeyDown(Input.KEY_UP) && isOnGround) dy -= 0.5f;
		tick();
		if(y >= level.getTiles()[0].length - 1) {
			deadTimer++;
		}
	}
	public void render(Graphics2D g, int sx, int sy) {
		if(deadTimer == 0) super.render(g, sx, sy);
	}
	
	public void remove(Object by) {
		if(deadTimer != 0) return;
		System.out.println("Killed by " + by);
		deadTimer = 1;
		spawnParticles(100, 100, 0.2f, 0.5f);
	}
}
