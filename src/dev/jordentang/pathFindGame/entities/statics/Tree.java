package dev.jordentang.pathFindGame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.gfx.Assets;
import dev.jordentang.pathFindGame.tiles.Tile;

public class Tree extends StaticEntity{
	
	public Tree(Handler handler, float x, float y){
		super(handler, x ,y, Tile.TILE_WIDTH * 3, Tile.TILE_HEIGHT * 6);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(!underAttack){
			g.drawImage(Assets.tree, (int)(x - handler.getGameCamera().getxOffset()) , (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		}
		else{
			g.drawImage(Assets.tree_attack, (int)(x - handler.getGameCamera().getxOffset()) , (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
			underAttack = false;
		}
	}
	
	//what should happen after a tree died
	@Override
	public void die() {
		// TODO Auto-generated method stub	
	}
	
}
