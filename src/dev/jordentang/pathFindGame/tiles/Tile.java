package dev.jordentang.pathFindGame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//static
	public static Tile [] tiles = new Tile[256];
	public static Tile clearTile = new ClearTile(0);
	public static Tile grassTile =  new GrassTile(1);
	public static Tile dirtTile = new DirtTile(2);
	public static Tile rockTile = new RockTile(3);
	
	
	
	public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;
	protected BufferedImage texture;
	//should never change id of tile
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.id = id;
		this.texture = texture;
		tiles[id] = this;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void tick(){
		
	}
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x,y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
}
	
	
