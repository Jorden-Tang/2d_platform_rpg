package dev.jordentang.pathFindGame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.jordentang.pathFindGame.Handler;
import dev.jordentang.pathFindGame.gfx.Animation;
import dev.jordentang.pathFindGame.gfx.Assets;

public class Skeleton extends Creature {
	
	private Animation walkLeft, walkRight, underAttackRight, underAttackLeft;
	private long lastAttackTimer, attackCooldown = 100, attackTimer = attackCooldown;
	private Boolean faceRight = true;
	
	
	public Skeleton(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		// TODO Auto-generated constructor stub
		this.xMove = 0.5f;
		bounds.x = 18;
		bounds.y = 16;
		bounds.width = 30;
		bounds.height = 45;
		//Animations
		walkRight = new Animation(100, Assets.skele_right);
		walkLeft = new Animation(100, Assets.skele_left);
		
		underAttackRight = new Animation(100, Assets.skele_under_attack_right);
		underAttackLeft = new Animation(100, Assets.skele_under_attack_left);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		walkRight.tick();
		walkLeft.tick();
		fall();
		
		this.move();
		changeWalkDirection();
		checkAttack();
		
		
	}
	

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		if(!underAttack){
			g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width , height, null );
		}
		else{
			g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width , height, null );
			underAttack = false;
		}

//		g.setColor(Color.red);
//		g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//				(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
	public void changeWalkDirection(){
		// if there is right collision
		if(this.moveX()){
			faceRight = false;
			xMove = -0.5f;
		}
		else{
			faceRight = true;
			xMove = 0.5f;
		}
	}
	
	private BufferedImage getCurrentAnimationFrame(){
			if(!underAttack){
				if(faceRight){
					return walkRight.getCurrentFrame();
				}
				else{
					return walkLeft.getCurrentFrame();
				}
			}
			else{
				if(faceRight){
					return underAttackRight.getCurrentFrame();
				}
				else{
					return underAttackLeft.getCurrentFrame();
				}
			}
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
		//attack range is the hit box of monster
		Rectangle attackRec  = getCollisionBounds(0,0);
		
		//hurting anything that is close
		for(Entity e: handler.getWorld().getEntityManager().getEntities()){
			//don't attack yourself
			if(e.getClass().equals(this.getClass())){
				continue;
			}
			if(e.getCollisionBounds(0, 0).intersects(attackRec)){
				e.hurt(1);
				return;
			}
		}	
	}
	
	

}
