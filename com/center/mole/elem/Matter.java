package com.center.mole.elem;

import java.awt.Color;

public class Matter implements Cloneable {
	public Color color;
	public double mass;
	public double posX;
	public double posY;
	public double vecX;
	public double vecY;

	public Matter() {
		color = Color.BLACK;
	}

	public Matter clone() {
		try {
			return (Matter) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public void genVectors(float arg0, float arg1) {
		float max = Math.max(arg0, arg1);
		float min = Math.min(arg0, arg1);

		double speed = (max - min) * Math.random() + min;
		double angle = 2 * Math.PI * Math.random();
        vecX = speed * Math.cos(angle);
        vecY = speed * Math.sin(angle);
	}
}
