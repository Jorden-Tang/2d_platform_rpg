package dev.jordentang.pathFindGame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.gfx.Animation;
import dev.jordentang.pathFindGame.gfx.Assets;
import dev.jordentang.pathFindGame.states.State;

public class Player extends Creature {
	
	
	private Animation walkRight, jump, crouch, idle, punch, kick, falling;
	private Animation walkLeft, jumpLeft, crouchLeft, idleLeft, punchLeft, kickLeft, fallingLeft;
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	private Boolean faceRight = true;
	public Player(Handler handler, float x, float y){
		super(handler ,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		bounds.x = 38;
		bounds.y = 50;
		bounds.width = 25;
		bounds.height = 40;
		this.setHealth(DEFAULT_HEALTH);
		
		//Animations
		walkRight = new Animation(100, Assets.player_right);
		walkLeft = new Animation(100, Assets.player_left);
		
		jump = new Animation(50, Assets.player_jump);
		jumpLeft = new Animation(50, Assets.player_jump_left);
		
		crouch = new Animation(100, Assets.player_crouch);
		crouchLeft = new Animation(100, Assets.player_crouch_left);
		
		idle = new Animation(100, Assets.player_idle);
		idleLeft = new Animation(100, Assets.player_idle_left);
		
		punch = new Animation(50, Assets.player_punch);
		punchLeft = new Animation(50, Assets.player_punch_left);
		
		kick = new Animation(50, Assets.player_kick);
		kickLeft = new Animation(50, Assets.player_kick_left);
		
		falling = new Animation(400, Assets.player_fall);
		fallingLeft = new Animation(400, Assets.player_fall_left);
		
		
	}
	
	@Override
	public void tick(){
		
		idle.tick();
		idleLeft.tick();
		
		walkLeft.tick();
		walkRight.tick();
		
		jump.tick();
		jumpLeft.tick();
		
		crouch.tick();
		crouchLeft.tick();
		
		punch.tick();
		punchLeft.tick();
		
		kick.tick();
		kickLeft.tick();
		
		this.getInput();
		//getting move method from creature
		fall();
		this.move();
		handler.getGameCamera().centerOnEntity(this);
		//Attacks
		checkAttack();
		

	}
	
	private void getInput(){
		//protected value from creature
		xMove = 0;
//		yMove = 0;
		
		if(handler.getKeyManager().up){
			yMove = - ( 2 * speed);
		}
		if(handler.getKeyManager().down){
			yMove = speed;
		}
		
		if(handler.getKeyManager().left){
			faceRight = false;
			xMove = - new Float(new Double(1.4 * speed));
		}
		
		if(handler.getKeyManager().right){
			faceRight = true;
			xMove = new Float(new Double(1.4 * speed));
		}
		
	}
	
	@Override
	public void render(Graphics g){
		//g.drawImage(Assets.player, (int)x, (int)y, width, height, null);
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()),  (int) (1.5 *  new Double (width)), (int) (1.5 *  new Double (height)), null);
		
//		displays the collision box
//		g.setColor(Color.red);
//		g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(faceRight){
			return playerRightAnimationFrame();
		}
		else{
			return playerLeftAnimationFrame();
		}
	}
	
	private BufferedImage playerRightAnimationFrame(){
		
		if (this.moveY()){
			return falling.getCurrentFrame();
		}
		else if(handler.getKeyManager().right){
			faceRight = true;
			return walkRight.getCurrentFrame();
		}
		else if(handler.getKeyManager().down){
			return crouch.getCurrentFrame();
		}
		else if(handler.getKeyManager().up){
			return	jump.getCurrentFrame();
		}
		else if(handler.getKeyManager().punch){
			return punch.getCurrentFrame();
		}
		else if(handler.getKeyManager().kick){
			return kick.getCurrentFrame();
		}
		else{
				return idle.getCurrentFrame();
		}
	}
	
	private BufferedImage playerLeftAnimationFrame(){
		if (this.moveY()){
			return fallingLeft.getCurrentFrame();
		}
		else if(handler.getKeyManager().left){
			faceRight = false;
			return walkLeft.getCurrentFrame();
		}
		else if(handler.getKeyManager().down){
			return crouchLeft.getCurrentFrame();
		}
		else if(handler.getKeyManager().up){
			return	jumpLeft.getCurrentFrame();
		}
		else if(handler.getKeyManager().punch){
			return punchLeft.getCurrentFrame();
		}
		else if(handler.getKeyManager().kick){
			return kickLeft.getCurrentFrame();
		}
		else{
			return idleLeft.getCurrentFrame();
		}
	}

	@Override
	public void die() {
			System.out.println("You LOSE!");
	}
	
	private void checkAttack(){
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		//can attack if attack cool down has been reached
		if(attackTimer < attackCooldown){
			return;
		}
		//reset attackerTimer so next cool down can be use
		
		attackTimer = 0;
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle attackRec = new Rectangle();
		int arSize = 30;
		attackRec.width = arSize;
		attackRec.height = arSize;
		
		if(handler.getKeyManager().punch){
			if(faceRight){
				attackRec.x = cb.x + cb.width;
				attackRec.y = cb.y + cb.height /2 - arSize/2;
			}
			else{
				attackRec.x = cb.x - cb.width;
				attackRec.y = cb.y + cb.height /2 - arSize/2;
			}
		}
		else if(handler.getKeyManager().kick){
			if(faceRight){
				attackRec.x = cb.x + cb.width + 20;
				attackRec.y = cb.y + cb.height /2 - arSize/2;
			}
			else{
				attackRec.x = cb.x - cb.width - 20;
				attackRec.y = cb.y + cb.height /2 - arSize/2;
			}
		}
		else{
			return;
		}
		
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			//don't attack yourself
			if(e.equals(this)){
				continue;
			}
			if(e.getCollisionBounds(0f, 0f).intersects(attackRec) || 
			   attackRec.intersects(e.getCollisionBounds(0f, 0f)) ||
			   e.getCollisionBounds(0f, 0f).contains(attackRec) ||
			   attackRec.contains(e.getCollisionBounds(0f, 0f))	   ){
				 e.hurt(1);
				 //return here for single attack
//				 return;
			}
		}
		//return here for multiple attack
		return;
		
	}
	
	
	

}
