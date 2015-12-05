package com.organthia.tobenamedrpg.input;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class InputHandler implements MouseListener, MouseMotionListener, MouseWheelListener, FocusListener {

	public boolean[] key = new boolean[68836];
	public boolean upArrow, downArrow, leftArrow, rightArrow;

	static KeyStroke upTrue = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true);
	static KeyStroke upFalse = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false);

static KeyStroke rightTrue = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true);
	static KeyStroke rightFalse = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false);
	
	static KeyStroke leftTrue = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true);
	static KeyStroke leftFalse = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false);
	
	static KeyStroke downTrue = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true);
	static KeyStroke downFalse = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false);

	public void update() {
		upArrow = key[KeyEvent.VK_UP];
		rightArrow = key[KeyEvent.VK_RIGHT];
		leftArrow = key[KeyEvent.VK_LEFT];
		downArrow = key[KeyEvent.VK_DOWN];
	}

	public void initKeys(JPanel panel) {
		// This needs to be done for all keys, but is much better than a key listener
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upTrue, "up-pressed");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(upFalse, "up-released");
		panel.getActionMap().put("up-pressed", new InputAction(KeyEvent.VK_UP, this, true));
		panel.getActionMap().put("up-released", new InputAction(KeyEvent.VK_UP, this, false));
	
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightTrue, "right-pressed");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(rightFalse, "right-released");
		panel.getActionMap().put("right-pressed", new InputAction(KeyEvent.VK_RIGHT, this, true));
		panel.getActionMap().put("right-released", new InputAction(KeyEvent.VK_RIGHT, this, false));
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftTrue, "left-pressed");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(leftFalse, "left-released");
		panel.getActionMap().put("left-pressed", new InputAction(KeyEvent.VK_LEFT, this, true));
		panel.getActionMap().put("left-released", new InputAction(KeyEvent.VK_LEFT, this, false));
		
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downTrue, "down-pressed");
		panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(downFalse, "down-released");
		panel.getActionMap().put("down-pressed", new InputAction(KeyEvent.VK_DOWN, this, true));
		panel.getActionMap().put("down-released", new InputAction(KeyEvent.VK_DOWN, this, false));
	}

	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void focusGained(FocusEvent e) {

	}

	public void focusLost(FocusEvent e) {
		for (int i = 0; i < key.length; i++) {
			key[i] = false;
		}

	}
}
