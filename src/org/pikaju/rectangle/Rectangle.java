package org.pikaju.rectangle;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import org.pikaju.rectangle.io.Input;

public class Rectangle extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static Rectangle i;
	
	private Thread thread;
	private boolean running;
	
	public int ups;
	public int fps;
	
	public Game game;
	
	public Rectangle() {
		setPreferredSize(new Dimension(640, 480));
	}
	
	public static void main(String[] args) {
		i = new Rectangle();
		i.start();
	}
	
	public void init() {
		JFrame frame = new JFrame("The Regular Rectangle");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Rectangle.i.stop();
			}
		});
		Input.i = new Input();
		addKeyListener(Input.i);
		addMouseListener(Input.i);
		addMouseMotionListener(Input.i);
		
		game = new Game();
		game.init();
		
		frame.setVisible(true);
	}
	
	public void cleanup() {
		game.cleanup();
	}
	
	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		init();
		thread = new Thread(this, "The Regular Rectangle");
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		cleanup();
		System.exit(0);
	}
	
	public void run() {
		long currentTime = 0;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		
		int updates = 0;
		int frames = 0;
		
		while(running) {
			currentTime = System.nanoTime();
			timer += currentTime - lastTime;
			delta += (currentTime - lastTime) / ns;
			lastTime = currentTime;
			
			boolean shouldRender = false;
			
			while(delta >= 1) {
				update();
				updates++;
				delta--;
				shouldRender = true;
			}
			
			if(shouldRender) {
				render();
				frames++;
			}
			
			if(timer >= 1000000000) {
				timer -= 1000000000;
				ups = updates;
				fps = frames;
				updates = 0;
				frames = 0;
			}
		}
	}

	public void update() {
		game.update();
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		Graphics2D g = (Graphics2D) bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		game.render(g);
		
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
		bs.show();
	}
}
