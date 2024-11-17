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
import GameObject.Entity.Hazard.Stationary.Ice;
import GameObject.Entity.Hazard.Stationary.IceManager;
import GameObject.Entity.Hazard.Stationary.Spike;
import GameObject.Entity.Hazard.Stationary.SpikeManager;
import GameObject.Entity.Interactable.Key;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level5 extends Level{
	private static boolean isDoorOpen = false, keyGrabbed = false, isCompleted = false;
	//push blocks 
	public static float pushx = 200, pushy = Main.getScreenHeight()-205;

	public int doorTimer = 0;

	public static void reset(){
		pushx = 200;
		pushy = Main.getScreenHeight()-205;
		isDoorOpen = false;
		keyGrabbed = false;
		Ice.reset();
	}
	
	public void render(Graphics g) throws SlickException{
		ImageLoader.returnImages("ghostBlockText").draw(100,200);
		
		for(Tile t : TileManager.getWhiteTiles()) {
			t.render(g);
		}
		
		g.setColor(Color.black);
		
		for(Tile t : TileManager.getBlackTiles()) {
			t.render(g);
		}
		//door & key
		if(!keyGrabbed){
			AnimationLoader.returnAnimation("key").draw(3200,Main.getScreenHeight()-220, 180, 180);
		}
		
		if(keyGrabbed){
			if(doorTimer < 25){
				AnimationLoader.returnAnimation("door").draw(3500, Main.getScreenHeight()-290, 150, 230);
			}
			
			if(doorTimer >= 25){
				ImageLoader.returnImages("openDoor").draw(3500, Main.getScreenHeight()-290, 150, 230);
			}
		}else{
			ImageLoader.returnImages("door").draw(3500, Main.getScreenHeight()-290, 150, 230);
		}
		
		for(Switch s : SwitchManager.getSwitches()){
			s.render(g);
		}
		
		for(pushBlock pB : pushBlockManager.getPushBlocks()){
			pB.render(g);
		}
		
		g.setColor(new Color(113, 208, 227));
		Cooldown.draw(g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		TileManager.clearTiles();
		Border.clearBorders();
		SpikeManager.clearSpikes();
		SwitchManager.clearSwitches();
		pushBlockManager.clearPushBlocks();
		if(keyGrabbed)
		{
			doorTimer ++;
		}
		generateWorld();
		for(Ice I : IceManager.getIce())
		{
			I.update();
		}
		for(pushBlock pB : pushBlockManager.getPushBlocks())
		{
			pB.update();
		}
		for(Switch s : SwitchManager.getSwitches())
		{
			if(Game.getP().getxPos() > s.getX() && Game.getP().getxPos() < s.getX()+ s.getWidth() 
			&& Game.getP().getyPos() + Game.getP().getHeight() > s.getY()  && Game.getP().getyPos() < s.getY() +s.getHeight()) 
			{
				Game.switchg();
			}
		}
		if(Game.getP().getPlayerShape().intersects(Door.getDoorHitBox()) && isDoorOpen)
		{
			isCompleted = true;
			Game.getP().playerReset();
			Game.setCurLevel(Game.getLevelSelect());
			Game.getP().setxPos(500);
			Game.getP().setyPos(Main.getScreenHeight()/2);
		}
		if(Game.getP().getPlayerShape().intersects(Key.getKeyHitBox()))
		{
			keyGrabbed = true;
			isDoorOpen = true;
		}
		for(Spike s : SpikeManager.getSpikes())
		{
			s.update();
		}
		for(Spike s : SpikeManager.getSpikes())
		{
			if(Game.getP().getPlayerShape().intersects(s.getSpike()))
			{
				Game.getP().die();
			}
		}
	}

	private void generateWorld(){
		//spike
		SpikeManager.createSpike(0,Main.getScreenHeight()-60, Main.getScreenWidth()*3,100, true);
		SpikeManager.createSpike(0,0, Main.getScreenWidth()*3,60, true);
		wallsAroundMapx2();
		//push blocks
		pushBlockManager.createPushBlock(pushx, pushy, 105, 105);
		pushBlockWalls();
		//Floors
		TileManager.createBorder(1700,Main.getScreenHeight()/2 -100,1700 +Main.getScreenWidth()/2-300,Main.getScreenHeight()/2 -100);
		TileManager.createBorder(1700,Main.getScreenHeight()/2 +100,1700 +Main.getScreenWidth()/2-300,Main.getScreenHeight()/2 +100);
		//Walls
		TileManager.createBorder(1700, Main.getScreenHeight(), 1700, Main.getScreenHeight()/2 +100+2);
		TileManager.createBorder(1700,0,1700,Main.getScreenHeight()/2 -100-2);
		TileManager.createBorder(1700+Main.getScreenWidth()/2-300, Main.getScreenHeight(), 1700+Main.getScreenWidth()/2-300, Main.getScreenHeight()/2 +100+2);
		TileManager.createBorder(1700+Main.getScreenWidth()/2-300, 0, 1700+Main.getScreenWidth()/2-300, Main.getScreenHeight()/2 -100-2);
		//Tiles
		TileManager.createTile(1700, 0, Main.getScreenWidth()/2-300, Main.getScreenHeight()/2 -100, "white");
		TileManager.createTile(1700, Main.getScreenHeight()/2 +100, Main.getScreenWidth()/2-300, Main.getScreenHeight(), "white");
		//switches
		SwitchManager.createSwitch(600,Main.getScreenHeight()-200, 100, 100,true);
		SwitchManager.createSwitch(600,100, 100, 100,false);
		
		if(!keyGrabbed){
			Key.createKey(3200,Main.getScreenHeight()-220, 180, 84);
		}
		Door.createDoor(3500, Main.getScreenHeight()-290, 150, 230);
	}

	public static void setPushx(float x){
		Level5.pushx = x;
	}

	public static void setPushy(float y){
		Level5.pushy = y;
	}

	public static boolean getCompleted(){
		return isCompleted;
	}
	
	public static void setCompleted(boolean isCompleted){
		Level5.isCompleted = isCompleted;
	}

	@Override
	public int getWidth(){return 0;}

	@Override
	public int getHeight(){return 0;}
}

