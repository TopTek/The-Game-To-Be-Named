package com.organthia.tobenamedrpg.input;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class InputAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	private InputHandler input;
	private int keyPressed;
	private boolean pressed;
	
	public InputAction(int keyPressed, InputHandler input, boolean pressed) {
		this.keyPressed = keyPressed;
		this.input = input;
		this.pressed = pressed;
	}

	public void actionPerformed(ActionEvent e) {
		if(!pressed){
			input.key[keyPressed]=true;
		}else{
			input.key[keyPressed]=false;
		}
	}
}
