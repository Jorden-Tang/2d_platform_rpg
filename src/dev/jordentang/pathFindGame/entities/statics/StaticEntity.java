package dev.jordentang.pathFindGame.entities.statics;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.entities.Entity;

public abstract class StaticEntity extends Entity{
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}
	
}
