package dev.jordentang.pathFindGame;

import dev.jordentang.pathFindGame.gfx.GameCamera;
import dev.jordentang.pathFindGame.input.KeyManager;
import dev.jordentang.pathFindGame.input.MouseManager;
import dev.jordentang.pathFindGame.worlds.World;

public class Handler {
	private Game game;
	private World world;
	public Handler(Game game){
		this.game = game;
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	public int getWidth(){
		return game.getWidth();
	}
	public int getHeight(){
		return game.getHeight();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
}
