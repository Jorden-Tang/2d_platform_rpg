package dev.jordentang.pathFindGame.states;

import java.awt.Graphics;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.gfx.Assets;
import dev.jordentang.pathFindGame.ui.ClickListener;
import dev.jordentang.pathFindGame.ui.UIImageButton;
import dev.jordentang.pathFindGame.ui.UIManager;

public class MenuState extends State{
	private UIManager uiManager;
	public MenuState(final Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		uiManager.addObject(new UIImageButton(300, 250, 180, 90, Assets.start_button, new ClickListener(){
			public void onClick(){
				handler.getGame().setGameState(new GameState(handler));
				State.setState(handler.getGame().getGameState());
			}
		}));
	}
	
	@Override
	public void tick(){
		uiManager.tick();
		
	}
	
	@Override
	public void render(Graphics g){
		uiManager.render(g);
	}
}


