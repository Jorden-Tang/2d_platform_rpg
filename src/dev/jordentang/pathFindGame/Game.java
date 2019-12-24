package dev.jordentang.pathFindGame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.jordentang.pathFindGame.display.Display;
import dev.jordentang.pathFindGame.gfx.Assets;
import dev.jordentang.pathFindGame.gfx.GameCamera;
import dev.jordentang.pathFindGame.input.KeyManager;
import dev.jordentang.pathFindGame.input.MouseManager;
import dev.jordentang.pathFindGame.states.GameState;
import dev.jordentang.pathFindGame.states.MenuState;
import dev.jordentang.pathFindGame.states.SettingState;
import dev.jordentang.pathFindGame.states.State;


/*
 * Main Game class
 * able to run on thread by implements Runnable
 */
public class Game implements Runnable {
	//displays to the screen
	private Display display;
	public String title;
	private int width, height;
	private boolean running = false;
	private Thread thread;
	
	//tells computer how to draw objects to screen
	//queue for pre render frames
	private BufferStrategy bs;
	private Graphics g;
	
	
	//states
	private State gameState;
	private State menuState;
	private State settingState;
	
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	
	//gameCamera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	

	
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		this.keyManager = new KeyManager();
		this.mouseManager = new MouseManager();
	}
	
	//runs once to initialize settings for game
	private void init(){
		this.display = new Display(title, width, height);
		display.getFrame().addKeyListener(this.keyManager);
		
		//add to frame and canvas
		//so when program is focused canvas or frame, the other can respond to the 
		//mouse event
		display.getFrame().addMouseListener(this.mouseManager);
		display.getFrame().addMouseMotionListener(this.mouseManager);
		display.getCanvas().addMouseListener(this.mouseManager);
		display.getCanvas().addMouseMotionListener(this.mouseManager);
		
		//initialize all the pictures and animations 		
		Assets.init();
		
		//init handler to gameCamera, state
		handler = new Handler(this);
		gameCamera = new GameCamera(this.handler,0,0);
		gameState = new GameState(this.handler);
		menuState = new MenuState(this.handler);
		settingState = new SettingState(this.handler);
//		State.setState(gameState);
		State.setState(menuState);
	}
	

	//update variables for game
	private void tick(){
		this.keyManager.tick();
		if(State.getState() != null){
			//execute tick of current state
			State.getState().tick();
		}
	}
	
	//renders new graphics to screen after update
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		//first time running canvas
		//create buffer frame queue size of 3 (Max is 3)
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		//graphics can update objects to canvas
		g = bs.getDrawGraphics();
		//clear the screen before next render 
		g.clearRect(0, 0, this.width, this.height);
		
		
		//Start Drawing
		//when there is a game state
		if(State.getState() != null){
			//allowed to render
			State.getState().render(g);
		}
		
		//use buffer frame queue, End Drawing
		bs.show();
		g.dispose();
	}
	
	//called by thread.start()
	//main game logic
	public void run(){
		init();
		//getting maximum execution time for 1 tick for 60fps
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(this.running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			//need to limit how many times update and render functions being called 
			//since faster computer would execute faster
			
			//is the time reached for 1 frame to be rendered
			if(delta >= 1){
			  tick();
			  render();
			  ticks++;
			  delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks  and Frames :" + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		//stop the thread
		stop();
	}
	
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public GameCamera getGameCamera(){
		return this.gameCamera;
	}
	
	public State getGameState() {
		return gameState;
	}
	
	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public State getMenuState() {
		return menuState;
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

	public synchronized void start(){
		//if game is already running dont initialize anything
		if(running){
			return;
		}
		this.running = true;
		//run the game class on the thread (runs separate from main process)
		thread = new Thread(this);
		// will call run method
		thread.start();
	}
	
	public synchronized void stop(){
		if(!this.running){
			return;
		}
		this.running = false;
		try{
			//stop the thread safely and properly
			thread.join();
		}
		catch(InterruptedException e){
			//for debugging purpose
			e.printStackTrace();
		}
	}
;}
