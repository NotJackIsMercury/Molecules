package com.center.mole.display;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import com.center.mole.elem.Matter;
import com.center.mole.event.AWTListener;
import com.center.util.array.ArrayBuilder;

@SuppressWarnings("serial")
public class Display extends Canvas implements AWTListener {
	public static final Display Display = new Display();

	public final ArrayBuilder<Matter> matters;

	private BufferStrategy bufferStrategy;
	private Graphics2D graphics;
	private long lastNanoTime;
	private int markX;
	private int markY;
	private int viewX;
	private int viewY;

	private Display() {
		super();

		matters = new ArrayBuilder<>(1000);

		addMouseListener(this);
		addMouseMotionListener(this);

		Matter matter = new Matter();
		while (Math.random() > 0.1) {
			matter.posX = 35;
			matter.posY = 35;
			matter.genVectors();
			matters.add(matter);
		}

		setBackground(Color.WHITE);
	}

	public void render() {
		bufferStrategy = getBufferStrategy();

		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}

		graphics = (Graphics2D) bufferStrategy.getDrawGraphics();

		graphics.clearRect(0, 0, getWidth(), getHeight());

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (int i = 0; i < matters.length(); i++) {
			if (matters.get(i) != null && viewX - 3 < matters.get(i).posX && matters.get(i).posX < getWidth() + viewX + 3 && viewY - 3 < matters.get(i).posY && matters.get(i).posY < getHeight() + viewY + 4) {
				graphics.setColor(matters.get(i).color);
				graphics.fillOval((int) matters.get(i).posX - 3 - viewX, (int) matters.get(i).posY - 3 - viewY, 7, 7); 
			}
		}

		bufferStrategy.show();
		graphics.dispose();
	}

	public void update() {
		double elapsedNanoTime = (System.nanoTime() - lastNanoTime) /  1_000_000_000.0;

		for (int i = 0; i < matters.length(); i++) {
			if (matters.get(i) == null) continue;
			matters.get(i).posX += matters.get(i).vecX * elapsedNanoTime;
			matters.get(i).posY += matters.get(i).vecY * elapsedNanoTime;
		}

		lastNanoTime = System.nanoTime();
	}

	public void mouseDragged(MouseEvent me) {
		switch (me.getModifiers()) {
		case MouseEvent.NOBUTTON:
			break;
		case MouseEvent.BUTTON1:
			break;
		case MouseEvent.BUTTON2:
			break;
		case MouseEvent.BUTTON3:
			break;
		case MouseEvent.BUTTON1_MASK:
			Matter matter = new Matter();
			matter.genVectors();
			matter.posX = viewX + me.getX();
			matter.posY = viewY + me.getY();
			matters.add(matter);
			break;
		case MouseEvent.BUTTON2_MASK:
			break;
		case MouseEvent.BUTTON3_MASK:
			System.out.println(viewX + ", " + viewY + ": " + markX + ", " + markY);
			viewX += markX - me.getX();
			viewY += markY - me.getY();
			markX = me.getX();
			markY = me.getY();
			break;
		case MouseEvent.BUTTON1_DOWN_MASK:
			break;
		case MouseEvent.BUTTON2_DOWN_MASK:
			break;
		case MouseEvent.BUTTON3_DOWN_MASK:
			break;
		}
	}

	public void mousePressed(MouseEvent me) {
		switch (me.getButton()) {
		case MouseEvent.NOBUTTON:
			break;
		case MouseEvent.BUTTON1:
			Matter matter = new Matter();
			matter.genVectors();
			matter.posX = me.getX();
			matter.posY = me.getY();
			matters.add(matter);
			break;
		case MouseEvent.BUTTON2:
			break;
		case MouseEvent.BUTTON3:
			System.out.println(viewX + ", " + viewY + ": " + markX + ", " + markY);
			markX = me.getX();
			markY = me.getY();
			break;
		case MouseEvent.BUTTON1_MASK:
			break;
		case MouseEvent.BUTTON2_MASK:
			break;
		case MouseEvent.BUTTON3_MASK:
			break;
		case MouseEvent.BUTTON1_DOWN_MASK:
			break;
		case MouseEvent.BUTTON2_DOWN_MASK:
			break;
		case MouseEvent.BUTTON3_DOWN_MASK:
			break;
		}
	}
}
