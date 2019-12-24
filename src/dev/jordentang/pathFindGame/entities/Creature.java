package dev.jordentang.pathFindGame.entities;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.tiles.Tile;

public abstract class Creature extends Entity{
	
	protected double dx, dy;
	protected double gravity;
	//maximum dropping speed
	protected double maxDy;
	protected boolean falling = true;
	protected boolean canJump = true; 
	
	
	
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		gravity = 0.5;
		maxDy = 5;
		
	}
	
	
	//changing x,y position
	public void move(){
		if(!checkEntityCollision(xMove, 0f))
			moveX();
		if(!checkEntityCollision(0f, yMove)){
			moveY();
			gravity = 0.5;
		}
		else{
			gravity = 0;
		}
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	
	//return true if there is right collision
	//return false if there is left collision
	
	public boolean moveX(){
		//moving right
		if(xMove > 0){
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
			!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
				x += xMove;
			}
			//getting perfect right collision
			else{
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
				return true;
			}
		}
		//moving left
		else if(xMove < 0){
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
			!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
				x += xMove;
				return  true;
			}
			//getting perfect left collision
			else{
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
				return false;
			}
		}
		return false;
	}
	// vertical collision check and moveY
	//return true if falling
	//return false if not falling
	public boolean moveY(){
		//up
		if(yMove < 0){
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			
			//no upward collision
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
				!collisionWithTile((int) (x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty)){
				y += yMove;
			}
			//with collision
			else{
//				falling = false;
//				canJump = true;
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
		}
		//down
		else if(yMove > 0){
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			//no downward collision
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
				!collisionWithTile((int) (x + bounds.x + bounds.width)/Tile.TILE_WIDTH, ty)){
				y += yMove;
				return true;
			}
			// with downward collision
			else{
				yMove = 0;
				canJump = false;
				falling = true;
			}
		}
		return false;
	}
	
	protected boolean collisionWithTile(int x, int y){
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	protected void fall(){
		if(falling){
			yMove += gravity;
		}
	}
	
	
}
