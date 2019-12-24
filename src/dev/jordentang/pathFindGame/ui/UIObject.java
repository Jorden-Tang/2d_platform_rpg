package dev.jordentang.pathFindGame.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected float x,y;
	protected int width ,height;
	protected Rectangle bounds;
	protected boolean hovering = false;
	
	public UIObject(float x, float y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int)x , (int)y, width, height);
	}

	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	//heck if mouse in on this ui object while move
	public void onMouseMove(MouseEvent e){
		if(bounds.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}
	
	
	//check if mouse in on this ui object while release
	public void onMouseRelease(MouseEvent e){
		//if hovering and released on this UI object
		// consider it to be a click
		if(hovering){
			onClick();
		}
	}
}
