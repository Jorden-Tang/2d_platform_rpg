package dev.jordentang.pathFindGame.display;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display {
	// for game window (frame)
	private JFrame frame;
	//for game graphics (painting)
	private Canvas canvas;
	
	private String title;
	private int width, height;
	
	public Display(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		createDisplay();
	}
	
	private void createDisplay(){
		//initialize game window with title
		frame = new JFrame(title);
		//setting width and height for game window
		frame.setSize(width, height);
		//link closing window (x) to closing JFrame
		//so game will close after window close
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//screen cannot be resized
		frame.setResizable(false);
		//window appear in the center of screen
		frame.setLocationRelativeTo(null);
		//make JFrame visible( default is hidden)
		frame.setVisible(true);
		
		//initialize canvas and fix the width and height
		this.canvas = new Canvas();
		this.canvas.setPreferredSize(new Dimension(this.width, this.height));
		this.canvas.setMaximumSize(new Dimension(this.width, this.height));
		this.canvas.setMinimumSize(new Dimension(this.width, this.height));
		
		//JFrame is the only thing can be focused 
		this.canvas.setFocusable(false);
		frame.add(this.canvas);
		frame.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame(){
		return this.frame;
	}

	
	
	
	
	
	
	
	
}
