package dev.jordentang.pathFindGame.tiles;

import dev.jordentang.pathFindGame.gfx.Assets;

public class RockTile extends Tile{
	
	public RockTile(int id){
		super(Assets.stone, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}
	
	
	
}
