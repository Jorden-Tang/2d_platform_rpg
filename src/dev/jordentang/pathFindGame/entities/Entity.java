package dev.jordentang.pathFindGame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.jordentang.pathFindGame.Handler;

public abstract class  Entity {
	
	//for smooth look calculation
	public static final int DEFAULT_HEALTH = 10;
	protected int health;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean active = true;
	protected float speed;
	protected Handler handler;
	public boolean underAttack = false;
	
	public Entity(Handler handler, float x, float y, int width, int height){
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0,0, width, height);
	}
	
	
	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}
	


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	
	//return the bounding area of entity
	public Rectangle getCollisionBounds(float xOffset, float yOffset){
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public boolean checkEntityCollision(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			//don't check collision against itself
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset)) ){
				return true;
			}
		}
		return false;
	}


	//setting active to false
	//remove entity from rendering list
	public void hurt(int amt){
		underAttack = true;
		health -= amt;
		if(health <= 0){
			active = false;
		}
	}
	
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
}
