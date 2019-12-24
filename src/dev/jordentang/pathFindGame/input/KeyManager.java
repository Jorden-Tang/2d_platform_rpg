package dev.jordentang.pathFindGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	//storing status of keys are up or down
	private boolean [] keys;
	public boolean up, down, left, right, punch, kick, punch_left, kick_left;
	
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W] ;
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		punch = keys[KeyEvent.VK_Z];
		kick = keys[KeyEvent.VK_X];
		
	}
	
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	
}
