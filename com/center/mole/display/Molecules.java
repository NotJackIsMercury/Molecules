package com.center.mole.display;

import static com.center.mole.display.Display.Display;

import javax.swing.JFrame;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Molecules extends JFrame {
	public static Molecules Molecules;

	public static void main(String[] args) throws Exception {
		Molecules = new Molecules();

		while (true) {
			Display.update();
			Display.render();
		}
	}

	private Molecules() throws Exception {
		super("Molecules");

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		setSize(800, 640);

		setLocationRelativeTo(null);

		getContentPane().add(Display);

		setVisible(true);

		setDefaultCloseOperation(3);
	}
}
