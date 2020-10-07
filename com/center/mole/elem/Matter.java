package com.center.mole.elem;

import java.awt.Color;
import java.awt.Graphics2D;

public class Matter {
	public Color color;
	public double mass;
	public double posX;
	public double posY;
	public double vecX;
	public double vecY;

	public Matter() {
		color = Color.BLACK;
	}

	public void render(Graphics2D graphics) {
		
	}

	public void genVectors() {
		double speed = 10 + 40 * Math.random();
        double angle = 2 * Math.PI * Math.random();
        vecX = speed * Math.cos(angle);
        vecY = speed * Math.sin(angle);
	}
}
