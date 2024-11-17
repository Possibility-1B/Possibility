package Levels;

import Media.AnimationLoader;
import Media.ImageLoader;
import core.Cooldown;
import core.Game;
import core.Main;
import GameObject.Block.Border;
import GameObject.Block.Door;
import GameObject.Block.Switch;
import GameObject.Block.SwitchManager;
import GameObject.Block.Tile;
import GameObject.Block.TileManager;
import GameObject.Block.pushBlock;
import GameObject.Block.pushBlockManager;
import GameObject.Entity.Hazard.Stationary.Spike;
import GameObject.Entity.Hazard.Stationary.SpikeManager;
import GameObject.Entity.Interactable.Key;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level8 extends Level{
	private static boolean isDoorOpen = false, keyGrabbed = false;
	//push blocks 
	public static float pushx = 3600, pushy = Main.getScreenHeight() - 210;
	
	private static float platformX, platformV, elevatorY, elevatorVy, elevatorX, elevatorVx;
	
	private static boolean isCompleted = false;	
	
	public int doorTimer = 0;
	
	public Level8(){
		platformX = 1900;
	} 
	
	public static void reset(){
		isDoorOpen = false;
		keyGrabbed = false;
		platformX = 1900;
		platformV = 5;
		elevatorY = Main.getScreenHeight()-120;
		elevatorVy = 5;
		elevatorX = 3000;
		elevatorVx = 5;
		pushx = 3600;
		pushy = Main.getScreenHeight() - 210;
	}
	
	public void render(Graphics g) throws SlickException{
		g.setColor(Color.white);
		
		for(Tile t : TileManager.getWhiteTiles()) {
			t.render(g);
		}
		
		g.setColor(Color.black);
		
		for(Tile t : TileManager.getBlackTiles()) {
			t.render(g);
		}
		
		for(Spike s : SpikeManager.getSpikes()){
			s.render(g);
		}
		
		if(!keyGrabbed){
			AnimationLoader.returnAnimation("key").draw(3800 ,Main.getScreenHeight() - 794, 180,180);
		}
		
		if(keyGrabbed){
			if(doorTimer < 25){
				AnimationLoader.returnAnimation("door").draw(3680, Main.getScreenHeight() -330, 150, 230);
			}
			
			if(doorTimer >= 25){
				ImageLoader.returnImages("openDoor").draw(3680, Main.getScreenHeight() -330, 150, 230);
			}
		}else{
			ImageLoader.returnImages("door").draw(3680, Main.getScreenHeight() -330, 150, 230);
		}
		
		for(Switch s: SwitchManager.getSwitches()){
			s.render(g);
		}
		
		for(pushBlock pB : pushBlockManager.getPushBlocks()){
			pB.render(g);
		}
		Cooldown.draw(g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		TileManager.clearTiles();
		Border.clearBorders();
		SwitchManager.clearSwitches();
		SpikeManager.clearSpikes();
		pushBlockManager.clearPushBlocks();
		
		if(Game.getP().getPlayerShape().getCenterX() >= elevatorX && 
				Game.getP().getPlayerShape().getCenterX() <= elevatorX + 100 && 
				Game.getP().getPlayerShape().getCenterY() <= elevatorY){
			if(elevatorX <= 3400 && elevatorY > 300){
				elevatorY -= elevatorVy;
			}
			
			if(elevatorY <= 300 && elevatorX < 3400){
				elevatorVy = 0;
				elevatorX += elevatorVx;
			}
		}
		
		platformX += platformV;
	
		if(platformX >= 2300){
			platformV = platformV * (-1); 
		}
		else if(platformX <= 1900){
			platformV = platformV * (-1);
		}
		
		if(keyGrabbed){
			doorTimer ++;
		}
		
		generateWorld();
		
		for(pushBlock pB : pushBlockManager.getPushBlocks()){
			pB.update();
		}
	
		for(Switch s : SwitchManager.getSwitches()){
				if(Game.getP().getxPos() > s.getX() && Game.getP().getxPos() < s.getX() + 
						s.getWidth() && Game.getP().getyPos() + Game.getP().getHeight() >
						s.getY()  && Game.getP().getyPos() < s.getY() +s.getHeight()){
					Game.switchg();
				}
			}
		
		if(Game.getP().getPlayerShape().intersects(Door.getDoorHitBox()) && isDoorOpen){
			isCompleted = true;
			Game.getP().playerReset();
			Game.setCurLevel(Game.getLevelSelect());
			Game.getP().setxPos(150);
			Game.getP().setyPos(Main.getScreenHeight()/2);
		}
		
		if(Game.getP().getPlayerShape().intersects(Key.getKeyHitBox())){
			keyGrabbed = true;
			isDoorOpen = true;
		}
		
		for(Spike s : SpikeManager.getSpikes()){
			s.update();
		}
		
		for(Spike s : SpikeManager.getSpikes()){
			if(Game.getP().getPlayerShape().intersects(s.getSpike())){
				Game.getP().die();
			}
		}
	}

	private void generateWorld() {
		//Tiles
		TileManager.createTile(0, 500, 200, Main.getScreenHeight(), "white");
		TileManager.createTile(200, 700, 200, Main.getScreenHeight(), "white");
		TileManager.createTile(400, 900, 400, Main.getScreenHeight(), "white");
		TileManager.createTile(1000, 200, 50, 200, "white");
		TileManager.createTile(1200, 300, 50, 200, "white");
		TileManager.createTile(1000, 700, 250, 50, "white");
		TileManager.createTile(1400, 400, 500, 50, "white");
		TileManager.createTile(platformX, 400, 50, 25, "white");
		TileManager.createTile(2350, 400, 50, Main.getScreenHeight(), "white");
		TileManager.createTile(elevatorX, elevatorY, 100, 50, "white");
		TileManager.createTile(elevatorX - 25, elevatorY - 100, 25, 150, "white");
		TileManager.createTile(3400, 300, 100, Main.getScreenHeight(), "white");
		//105 x 105
		TileManager.createTile(3600, Main.getScreenHeight() - 710, 125, 500, "white");
		TileManager.createTile(3800, Main.getScreenHeight() - 710, 125, 500, "white");
		TileManager.createBorder(3800,Main.getScreenHeight()-710,3925, Main.getScreenHeight() - 710);
		TileManager.createBorder(3800,Main.getScreenHeight()-210,3925, Main.getScreenHeight() - 210);
		TileManager.createBorder(3800,Main.getScreenHeight()-708,3800, Main.getScreenHeight() - 210);
		TileManager.createBorder(3925,Main.getScreenHeight()-708,3925, Main.getScreenHeight() - 210);
		TileManager.createBorder(3600,Main.getScreenHeight() - 710,3725,Main.getScreenHeight() - 710);
		TileManager.createBorder(3600,Main.getScreenHeight() - 210,3725,Main.getScreenHeight() - 210);
		TileManager.createBorder(3600,Main.getScreenHeight() - 210,3600,Main.getScreenHeight() - 708);
		TileManager.createBorder(3725,Main.getScreenHeight() - 210,3725,Main.getScreenHeight() - 708);
		TileManager.createBorder(3400,302,3500,300);
		TileManager.createBorder(3400,302,3400,Main.getScreenHeight());
		TileManager.createBorder(3500,302, 3500, Main.getScreenHeight());
		TileManager.createBorder(elevatorX, elevatorY, elevatorX + 100, elevatorY);
		TileManager.createBorder(elevatorX, elevatorY + 50, elevatorX + 100, elevatorY + 50);
		TileManager.createBorder(elevatorX, elevatorY + 2, elevatorX, elevatorY + 50);
		TileManager.createBorder(elevatorX + 100, elevatorY + 2, elevatorX + 100, elevatorY + 50);
		TileManager.createBorder(elevatorX - 25 , elevatorY - 100, elevatorX, elevatorY - 100);
		TileManager.createBorder(elevatorX -25 , elevatorY - 98, elevatorX - 25, elevatorY + 50);
		TileManager.createBorder(elevatorX, elevatorY - 98, elevatorX, elevatorY + 50);
		TileManager.createBorder(2350,400, 2400, 400);
		TileManager.createBorder(2350,402, 2350, Main.getScreenHeight());
		TileManager.createBorder(2400,402, 2400, Main.getScreenHeight());
		TileManager.createBorder(200,502, 200, Main.getScreenHeight());
		TileManager.createBorder(0,500, 200,500);
		TileManager.createBorder(400,702, 400,Main.getScreenHeight());
		TileManager.createBorder(200, 700, 400, 700);
		TileManager.createBorder(400, 900, 800, 900);
		TileManager.createBorder(800, 902, 800, Main.getScreenHeight());
		TileManager.createBorder(1000, 200, 1050, 200);
		TileManager.createBorder(1000, 400, 1050, 400);
		TileManager.createBorder(1000, 200, 1000, 400);
		TileManager.createBorder(1050, 200, 1050, 400);
		TileManager.createBorder(1200,300,1250,300);
		TileManager.createBorder(1200, 500, 1250, 500);
		TileManager.createBorder(1200, 300, 1200, 500);
		TileManager.createBorder(1250, 300, 1250, 500);
		TileManager.createBorder(1000, 700, 1250, 700);
		TileManager.createBorder(1000, 750, 1250, 750);
		TileManager.createBorder(1000, 700, 1000, 750);
		TileManager.createBorder(1250, 700, 1250, 750);
		TileManager.createBorder(1400, 400, 1900, 400);
		TileManager.createBorder(1400, 402, 1400, 450);
		TileManager.createBorder(1900, 402, 1900, 450);
		TileManager.createBorder(1400, 450, 1900, 450);
		TileManager.createBorder(platformX, 400, platformX + 50, 400);
		TileManager.createBorder(platformX, 425, platformX + 50, 425);
		TileManager.createBorder(platformX, 402, platformX, 425);
		TileManager.createBorder(platformX + 50, 402, platformX + 50, 425);
		SpikeManager.createSpike(3600, 100, 25,300, true);
		SpikeManager.createSpike(600, 100, 100,100, true);
		SpikeManager.createSpike(1400, 450, 50,50, true);
		SpikeManager.createSpike(1450, 450, 50,50, true);
		SpikeManager.createSpike(1500, 450, 100,100, true);
		SpikeManager.createSpike(1600, 450, 50,50, true);
		SpikeManager.createSpike(1650, 450, 50,50, true);
		SpikeManager.createSpike(1950, 100, 50,50, true);
		SpikeManager.createSpike(1000, Main.getScreenHeight() -125 - 280, 25,25, false);
		SpikeManager.createSpike(1030, Main.getScreenHeight() -130 - 280, 30,30, false);
		SpikeManager.createSpike(1065, Main.getScreenHeight() -135 - 280, 35,35, false);
		SpikeManager.createSpike(1105, Main.getScreenHeight() -140 - 280, 40,40, false);
		SpikeManager.createSpike(1150, Main.getScreenHeight() -145 - 280, 45,45, false);
		SpikeManager.createSpike(platformX + 40, 425, 10,10, false);
		SpikeManager.createSpike(3200, 400, 50, Main.getScreenHeight() - 450, false);
		SwitchManager.createSwitch(600 ,800, 100, 100, true);
		SwitchManager.createSwitch(1350 ,Main.getScreenHeight()-200, 100, 100, true);
		SwitchManager.createSwitch(1050 ,100, 100, 100, false);
		SwitchManager.createSwitch(1600 ,100, 100, 100, false);
		SwitchManager.createSwitch(4000 ,Main.getScreenHeight() - 200, 100, 100, true);
		SwitchManager.createSwitch(3637 , 100, 100, 100, false);
		SwitchManager.createSwitch(5200,Main.getScreenHeight()-200, 100, 100, true);
		SwitchManager.createSwitch(5200,100, 100, 100, false);
		wallsAroundMap();
		//push blocks
		pushBlockManager.createPushBlock(pushx, pushy, 105, 105);
		pushBlockWalls();
		
		if(!keyGrabbed){
			Key.createKey(3800 ,Main.getScreenHeight() - 794, 180, 84);
		}
		Door.createDoor(3680, Main.getScreenHeight() -330, 150, 230);
	}
	
	public static boolean getCompleted(){
		return isCompleted;
	}

	public static void setCompleted(boolean isCompleted) {
		Level8.isCompleted = isCompleted;
	}

	public static void setPushx(float x) {
		Level8.pushx = x;
	}

	public static void setPushy(float y) {
		Level8.pushy = y;
	}

	@Override
	public int getWidth(){return 0;}

	@Override
	public int getHeight(){return 0;}
}