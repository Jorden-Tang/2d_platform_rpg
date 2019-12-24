package dev.jordentang.pathFindGame.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.states.State;

public class EntityManager {
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	
	public EntityManager(Handler handler, Player player){
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick();
			if(!e.active){
				//when player dies
				if(e.equals(player)){
					entities.remove(e);
					State.setState(handler.getGame().getMenuState());
				}
				//other stuff dies
				entities.remove(e);
			}
		}
	}
	
	public void render(Graphics g){
		//render every single entity in the list
		for(Entity e : entities){
				e.render(g);
		}
	}

	
	
	public void addEntity(Entity e){
		entities.add(e);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
}
