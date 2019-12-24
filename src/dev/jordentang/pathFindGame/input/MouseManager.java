package dev.jordentang.pathFindGame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.jordentang.pathFindGame.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener{
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	public MouseManager(){
		
	}
	
	//Getters
	public boolean isLeftPressed(){
		return leftPressed;
	}
	
	public boolean isRightPressed(){
		return rightPressed;
	}
	
	public int getMouseX(){
		return mouseX;
	}
	
	public int getMouseY(){
		return mouseY;
	}
	
	//Setters
	public void setUIManager(UIManager uiManager){
		this.uiManager = uiManager;
	}
	//Implemented Methods
	
	
	
	public void tick(){
		
	}
	
	public void render(){
		
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(uiManager != null){
			uiManager.onMouseMove(e);
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = true;
		}
		else if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1){
			leftPressed = false;
		}
		else if(e.getButton() == MouseEvent.BUTTON3){
			rightPressed = false;
		}
		
		if(uiManager != null){
			uiManager.onMouseRelease(e);
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

		
		
}
