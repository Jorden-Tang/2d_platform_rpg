package dev.jordentang.pathFindGame.tiles;

import dev.jordentang.pathFindGame.gfx.Assets;

public class GrassTile extends Tile{
	
	@Override
	public boolean isSolid(){
		return true;
	}
	public GrassTile(int id){
		super(Assets.grass, id);
	}
}
