package dev.jordentang.pathFindGame.states;

import java.awt.Graphics;

import dev.jordentang.pathFindGame.Game;
import dev.jordentang.pathFindGame.Handler;

public abstract class State {
	
	//Code for State Managers
	private static State currentState = null;
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	
	
	//CLASS
	protected Handler handler;
	
	protected Game game;
	public State(Handler handler){
		this.handler = handler;
	}
	public abstract void tick();
	//state is able to draw to the screen directly
	public abstract void render(Graphics g);
	
}
