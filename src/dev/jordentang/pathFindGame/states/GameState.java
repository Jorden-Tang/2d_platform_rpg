package dev.jordentang.pathFindGame.states;

import java.awt.Graphics;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.entities.Player;
import dev.jordentang.pathFindGame.entities.statics.Tree;
import dev.jordentang.pathFindGame.worlds.World;

public class GameState extends State{
	
	private World world;

	
	public GameState(Handler handler){
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);

		
	}
	
	@Override
	public void tick(){
		world.tick();
	}
	
	@Override
	public void render(Graphics g){
		world.render(g);
	}
}
