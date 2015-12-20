package com.organthia.tobenamedrpg.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.organthia.tobenamedrpg.encounter.Encounter;
import com.organthia.tobenamedrpg.encounter.ImageToEncounter;
import com.organthia.tobenamedrpg.encounter.RandomEncounter;
import com.organthia.tobenamedrpg.encounter.tile.Tile;
import com.organthia.tobenamedrpg.graphics.Render;
import com.organthia.tobenamedrpg.graphics.RotateImage;
import com.organthia.tobenamedrpg.graphics.SpriteSheet;
import com.organthia.tobenamedrpg.input.InputAction;
import com.organthia.tobenamedrpg.input.InputHandler;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int scale = 3;
	public static int width = 900 /scale; //1920
	public static int height = width / 16 * 9;
	public static String title = "Unnamed";

	private Thread thread;
	private JFrame frame;
	private static JPanel panel;
	private Render render;
	private static InputHandler input;
	private Encounter encounter;
	private boolean running = false;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Main() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		render = new Render(width, height);
		frame = new JFrame();
		panel = (JPanel) frame.getContentPane();
		input = new InputHandler();
		encounter = new ImageToEncounter(SpriteSheet.testSwag.WIDTH, SpriteSheet.testSwag.HEIGHT, SpriteSheet.testSwag);
		//encounter = new RandomEncounter(64, 64);
		
		frame.addFocusListener(input);
		/*
		frame.addKeyListener(input);
		frame.addMouseListener(input);
		frame.addMouseMotionListener(input);
		frame.addMouseWheelListener(input);
		*/
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + " | " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	int x = 0, y = 0;

	public void update() {
		input.update();
		if (input.upArrow) y--;
		if (input.downArrow) y++;
		if (input.leftArrow) x--;
		if (input.rightArrow) x++;
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		render.clear();
		encounter.render(x, y, render);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = render.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.frame.setResizable(false);
		main.frame.setTitle(Main.title);
		main.frame.add(main);
		main.frame.pack();
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.frame.setLocationRelativeTo(null);
		main.frame.setVisible(true);
		
		input.initKeys(panel);
		
		main.start();
	}
}
