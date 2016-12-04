package org.pikaju.rectangle;

import java.applet.Applet;

public class RectangleApplet extends Applet {
	private static final long serialVersionUID = 1L;

	public void start() {
		Rectangle.main(new String[0]);
	}
	
	public void stop() {
		if (Rectangle.i == null) return;
		Rectangle.i.stop();
	}
}
