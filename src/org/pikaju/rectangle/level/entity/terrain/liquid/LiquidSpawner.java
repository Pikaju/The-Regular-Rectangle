package org.pikaju.rectangle.level.entity.terrain.liquid;

import org.pikaju.rectangle.level.entity.Mob;
import org.pikaju.rectangle.level.entity.Player;

public class LiquidSpawner extends Mob {

	private Class<? extends Liquid> liquid;
	protected int life = -1;
	
	public LiquidSpawner(float x, float y, Class<? extends Liquid> liquid) {
		super(x, y, 1, 1);
		color = 0x00ffff;
		this.liquid = liquid;
		life = 60;
	}
	
	public void update() {
		Player p = level.getPlayer();
		if(Math.abs(p.x - x) > 15) return;
		for(int i = 0; i < 1; i++) {
			float xo = ((float) (Math.random() - Math.random())) * 0.1f;
			float yo = ((float) (Math.random() - Math.random())) * 0.1f;
			Liquid l = null;
			try {
				l = liquid.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			l.x = x + width / 2 + xo;
			l.y = y + height / 2 + yo;
			l.timer = life;
			l.dy = (float) ((-Math.random()) * 0.2f);
			l.dx = (float) ((Math.random() - Math.random()) * 0.1f);
			level.addEntity(l);
		}
	}
}
