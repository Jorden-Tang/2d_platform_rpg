package dev.jordentang.pathFindGame;
import dev.jordentang.pathFindGame.display.Display;

public class Launcher {

	public static void main(String [] args){
		Game newGame  = new Game("Simple Platform", 800, 600);
		newGame.start();
	}
	
}
