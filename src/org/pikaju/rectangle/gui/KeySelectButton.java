package org.pikaju.rectangle.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import org.pikaju.rectangle.io.Input;

public class KeySelectButton {
	
	private String text;
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int fontsize = 24;
	
	private boolean wasClicked;
	private boolean hovering;
	
	public int textColor = -1;
	
	public KeySelectButton(String text, int x, int y, int width, int height) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		hovering = Input.i.inRect(x, y, width, height);
		if(textColor == 0x909090) hovering = false;
		if(hovering) {
			if(Input.i.isButtonDown(Input.BUTTON_LEFT)) {
			}
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		if(hovering) {
			g.setColor(new Color(0x00a0ff));
			g.fillRect(x, y, width, height);
			g.setColor(Color.WHITE);
		}
		g.setFont(new Font("Arial", 0, fontsize));
		if(textColor != -1) g.setColor(new Color(textColor));
		g.drawString(text, x + 10, y + fontsize);
	}
	
	public boolean wasClicked() {
		boolean b = wasClicked;
		wasClicked = false;
		return b;
	}
}
