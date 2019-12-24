package dev.jordentang.pathFindGame.gfx;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

/*
 * Image, sound, Music
 * 
 */
public class Assets {
	public static BufferedImage player, dirt, grass, stone, tree, tree_attack, clear, background;
	
	public static BufferedImage [] player_idle;
	public static BufferedImage [] player_idle_left;
	
	
	public static BufferedImage [] player_right;
	public static BufferedImage [] player_left;
	
	
	public static BufferedImage [] player_jump;
	public static BufferedImage [] player_jump_left;
	
	public static BufferedImage [] player_crouch;
	public static BufferedImage [] player_crouch_left;
	
	public static BufferedImage [] player_punch;
	public static BufferedImage [] player_punch_left;
	
	public static BufferedImage [] player_kick;
	public static BufferedImage [] player_kick_left;
	
	public static BufferedImage [] player_fall;
	public static BufferedImage [] player_fall_left;
	
	public static BufferedImage[] skele_left;
	public static BufferedImage[] skele_right;
	
	public static BufferedImage[] skele_under_attack_left;
	public static BufferedImage[] skele_under_attack_right;
	
	public static BufferedImage [] start_button;
	
	
	
	private static final int characterWidth = 45, characterHeight = 37;
	private static final int dirtWidth = 16, dirtHeight = 16;
	private static final int stoneWidth = 16, stoneHeight = 16;
	private static final int treeWidth = 64, treeHeight = 96;
	private static final int grassWidth = 16, grassHeight = 16;
	private static final int clearWidth = 16, clearHeight = 16;
	private static final int monsterWidth = 16, monsterHeight = 16;
	
	
	public static void init(){
		//setup for vertical flip
	     AffineTransform tx=AffineTransform.getScaleInstance(-1.0,1.0);  //scaling

	     
		SpriteSheet environmentSheet = new SpriteSheet(ImageLoader.loadImage("/textures/environment.png"));
		SpriteSheet environmentUnderAttackSheet = new SpriteSheet(ImageLoader.loadImage("/textures/environment_under_attack.png"));
		SpriteSheet skeleWalkSheet = new SpriteSheet(ImageLoader.loadImage("/textures/monster_sprites/skeleton.png"));
		SpriteSheet skeleUnderAttackSheet = new SpriteSheet(ImageLoader.loadImage("/textures/monster_sprites/skeleton_under_attack.png"));
		
		player_idle = new BufferedImage[4];
		player_idle_left = new BufferedImage[4];
		for(int i = 0 ; i < player_idle.length; i++){
			BufferedImage idleImage = ImageLoader.loadImage("/textures/character_sprites/adventurer-idle-2-0" + Integer.toString(i) + "-1.3.png");
			player_idle[i] = idleImage.getSubimage(0, 0, characterWidth, characterHeight);	
			player_idle_left[i] = imageHorizontalFlip(idleImage).getSubimage(0, 0, characterWidth, characterHeight);
		}
		
		
		player_right = new BufferedImage[6];
		player_left = new BufferedImage[6];
		
		for(int i = 0 ; i < player_right.length; i++){
			BufferedImage walkImage = ImageLoader.loadImage("/textures/character_sprites/adventurer-walk-0" + Integer.toString(i) + ".png");
			player_right[i] = walkImage.getSubimage(0, 0, characterWidth, characterHeight);
			player_left [i] = imageHorizontalFlip(walkImage).getSubimage(0, 0, characterWidth, characterHeight);
		}

		
		
		player_jump = new BufferedImage[4];
		player_jump_left = new BufferedImage[4];
		for(int i = 0 ; i < player_jump.length; i++){
			BufferedImage jumpImage = ImageLoader.loadImage("/textures/character_sprites/adventurer-jump-0" + Integer.toString(i) + "-1.3.png");
			player_jump[i] = jumpImage.getSubimage(0, 0, characterWidth, characterHeight);
			player_jump_left[i] = imageHorizontalFlip(jumpImage).getSubimage(0, 0, characterWidth, characterHeight);
		}
		
		player_crouch = new BufferedImage[4];
		player_crouch_left = new BufferedImage[4];
		for(int i = 0 ; i < player_crouch.length; i++){
			BufferedImage crouchImage = ImageLoader.loadImage("/textures/character_sprites/adventurer-crouch-0" + Integer.toString(i) + "-1.3.png");
			player_crouch[i] = crouchImage.getSubimage(0, 0, characterWidth, characterHeight);
			player_crouch_left[i] = imageHorizontalFlip(crouchImage).getSubimage(0, 0, characterWidth, characterHeight);
		}
		
		player_punch = new BufferedImage[13];
		player_punch_left = new BufferedImage[13];
		for(int i = 0 ; i < player_punch.length; i++){
			BufferedImage punchImage = ImageLoader.loadImage("/textures/character_sprites/adventurer-punch-" + String.format("%02d", i) + ".png");
			player_punch[i] = punchImage.getSubimage(0, 0, characterWidth, characterHeight);
			player_punch_left[i] = imageHorizontalFlip(punchImage).getSubimage(0, 0, characterWidth, characterHeight);
		}
		
		player_kick = new BufferedImage[8];
		player_kick_left = new BufferedImage[8];
		for(int i = 0 ; i < player_kick.length; i++){
			BufferedImage kickImage = ImageLoader.loadImage("/textures/character_sprites/adventurer-kick-" + String.format("%02d", i) + ".png");
			player_kick[i] = kickImage.getSubimage(0, 0, characterWidth, characterHeight);
			player_kick_left[i] = imageHorizontalFlip(kickImage).getSubimage(0, 0, characterWidth, characterHeight);
		}
		
		player_fall = new BufferedImage[2];
		player_fall_left = new BufferedImage[2];
		for(int i = 0 ;i < player_fall.length; i++){
			BufferedImage fallImage = ImageLoader.loadImage("/textures/character_sprites/adventurer-fall-" + String.format("%02d", i) + "-1.3.png");
			player_fall[i] = fallImage.getSubimage(0, 0, characterWidth, characterHeight);
			player_fall_left[i] = imageHorizontalFlip(fallImage).getSubimage(0, 0, characterWidth, characterHeight);
		}
		
		start_button = new BufferedImage[2];
		for(int i = 0; i < player_fall.length; i++){
			start_button[i] = ImageLoader.loadImage("/textures/menu_sprites/start_" + Integer.toString(i) + ".png");
		}
		
		skele_right = new BufferedImage[4];
		skele_left = new BufferedImage[4];
		for(int i = 0; i < skele_right.length; i++){
			BufferedImage tempWalkImage =  skeleWalkSheet.crop(i * monsterWidth, 0, monsterWidth, monsterHeight);
			skele_right[i] = tempWalkImage;
			skele_left[i] = imageHorizontalFlip(tempWalkImage);
		}
		
		skele_under_attack_left = new BufferedImage[4];
		skele_under_attack_right = new BufferedImage[4];
		
		for(int i = 0; i < skele_under_attack_right.length; i++){
			BufferedImage underAttackImage =  skeleUnderAttackSheet.crop(i * monsterWidth, 0, monsterWidth, monsterHeight);
			skele_under_attack_right[i] = underAttackImage;
			skele_under_attack_left[i] = imageHorizontalFlip(underAttackImage);
		}
		
		
		grass = ImageLoader.loadImage("/textures/environment_sprites/terrain_platform_center.png").getSubimage(0, 0, grassWidth, grassHeight);
		dirt = (ImageLoader.loadImage("/textures/environment_sprites/midground_center.png")).getSubimage(0, 0, dirtWidth, dirtHeight);
		stone = (ImageLoader.loadImage("/textures/environment_sprites/terrain_center.png")).getSubimage(0, 0, stoneWidth, stoneHeight);
		tree = environmentSheet.crop(3 * treeWidth, 0, treeWidth, treeHeight);
		tree_attack = environmentUnderAttackSheet.crop(3 * treeWidth, 0, treeWidth, treeHeight);
		clear = ImageLoader.loadImage("/textures/environment_sprites/transparent.png").getSubimage(0, 0, clearWidth, clearHeight);
		background = ImageLoader.loadImage("/textures/background.png");
		
	}
	
	private static BufferedImage imageHorizontalFlip(BufferedImage img){
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage flipped = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for(int y = 0; y < height; y++){
			for(int x = 0; x <width; x++){
				flipped.setRGB((width - 1) - x, y, img.getRGB(x, y));
			}
		}
		return flipped;
	}
}
