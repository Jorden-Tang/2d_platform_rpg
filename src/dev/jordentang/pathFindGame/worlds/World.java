package dev.jordentang.pathFindGame.worlds;

import java.awt.Graphics;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.entities.EntityManager;
import dev.jordentang.pathFindGame.entities.Player;
import dev.jordentang.pathFindGame.entities.Skeleton;
import dev.jordentang.pathFindGame.entities.statics.Tree;
import dev.jordentang.pathFindGame.gfx.Assets;
import dev.jordentang.pathFindGame.tiles.Tile;
import dev.jordentang.pathFindGame.utils.Utils;

public class World {
	private Handler handler;
	private int width, height;
	private int[][] tiles;
	private int spawnX, spawnY;
	private EntityManager entityManager;
	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 200, 100));
		loadWorld(path);
		entityManager.addEntity(new Tree(handler, 50, 470));
		entityManager.addEntity(new Tree(handler, 100, 470));
		entityManager.addEntity(new Tree(handler, 860, 470));
		entityManager.addEntity(new Tree(handler, 1300, 470));
		entityManager.addEntity(new Tree(handler, 1300, 470));
		entityManager.addEntity(new Tree(handler, 1350, 470));
		entityManager.addEntity(new Tree(handler, 1440, 470));
		entityManager.addEntity(new Tree(handler, 1540, 470));
		entityManager.addEntity(new Tree(handler, 1500, 470));
		entityManager.addEntity(new Tree(handler, 1550, 470));
		
		entityManager.addEntity(new Skeleton(handler, 610, 340));
		entityManager.addEntity(new Skeleton(handler, 670, 340));
		entityManager.addEntity(new Skeleton(handler, 400, 340));
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	private void loadWorld (String path){
		String file = Utils.loadFileAsString(path);
		String [] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		//rendering tiles from 2d array
		tiles = new int [width][height];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
		
	}
	
	public void tick(){
		entityManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = Math.max(0, (int)handler.getGameCamera().getxOffset()/Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width,  (handler.getGameCamera().getxOffset() + handler.getWidth())/ Tile.TILE_WIDTH + 1);
		int yStart = Math.max(0, (int)handler.getGameCamera().getyOffset()/Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(width,  (handler.getGameCamera().getyOffset() + handler.getHeight())/ Tile.TILE_HEIGHT + 1);
		g.drawImage(Assets.background, 0, 0, Tile.TILE_WIDTH * 30 , Tile.TILE_HEIGHT * 19,  null);
		
		
		//Optimizations
		for(int y = yStart; y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				this.getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		//after rendering all tiles for world, render all static entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x , int y){
		if(x < 0 || y < 0 || x >= width || y >= height){
			return Tile.rockTile;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null){
			return Tile.dirtTile;
		}
		return t;
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

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
}
