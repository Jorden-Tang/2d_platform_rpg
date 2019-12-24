package dev.jordentang.pathFindGame.gfx;

import dev.jordentang.pathFindGame.Game;
import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.entities.Entity;
import dev.jordentang.pathFindGame.tiles.Tile;

public class GameCamera {
	private float xOffset, yOffset;
	private Handler handler;
	
	public GameCamera(Handler handler, float xoffset, float yoffset){
		this.handler = handler;
		this.xOffset = xoffset;
		this.yOffset = yoffset;
	}
	
	public void move(float xAmount, float yAmount){
		xOffset += xAmount;
		yOffset += yAmount;
		checkBlankSpace();
	}

	public float getxOffset() {
		return xOffset;
	}
	
	public void centerOnEntity(Entity e){
		xOffset = e.getX() - handler.getWidth()/ 2 + e.getWidth() /2 ;
		yOffset = e.getY() - new Float(new Double(handler.getHeight())/1.45);
		checkBlankSpace();
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace(){
		if(this.xOffset <= 0){
			xOffset = 0;
		}
		else if(this.xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()){
			xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
		}
		if(this.yOffset <= 0){
			yOffset = 0;
		}
		else if(this.yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()){
			yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
		}
		
	}
	
	
}
