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

public class Level3 extends Level{	
	private static boolean isDoorOpen = false, keyGrabbed = false, isCompleted3 = false;	
	
	private static float icex = Main.getScreenWidth() - 500, icey = 980, iceWidth = 1300,
			iceHeight = Main.getScreenHeight();
	
	private int keyX = getWidth()/2, keyY = getHeight()/2 + 200, doorTimer = 0;

	public static void reset(){
		isDoorOpen = false;
		keyGrabbed = false;
		
		for(Ice I : IceManager.getIce()){
			I.setSlide(false);
		}
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
		
		for(Switch s : SwitchManager.getSwitches()){
			s.render(g);
		}
		
		for(Ice I : IceManager.getIce()){
			I.render(g);
		}
		
		if(!keyGrabbed){			
			AnimationLoader.returnAnimation("key").draw(keyX, keyY, 180, 180);
		}
		
		if(keyGrabbed){
			if(doorTimer < 25){
				AnimationLoader.returnAnimation("door").draw(getWidth() - 250,Main.getScreenHeight()-290, 150, 230);
			}
			
			if(doorTimer >= 25){
				ImageLoader.returnImages("openDoor").draw(getWidth() - 250, (int)(Main.getScreenHeight()-290), 150, 230);
			}
		}else{
			ImageLoader.returnImages("door").draw(getWidth() - 250, (int)(Main.getScreenHeight()-290), 150, 230);
		}
		
		ImageLoader.returnImages("newIce").draw(250,250);
		ImageLoader.returnImages("iceText2").draw(getWidth()/2 - 300, 250);
		Cooldown.draw(g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		TileManager.clearTiles();
		Border.clearBorders();
		SwitchManager.clearSwitches();
		
		if(keyGrabbed){
			doorTimer ++;
		}
		
		generateWorld();
	
		for(pushBlock pB : pushBlockManager.getPushBlocks()){
			pB.update();
		}
		
		for(Ice I : IceManager.getIce()){
			I.update();
		}
		
		for(Switch s : SwitchManager.getSwitches()){
			if(Game.getP().getxPos() > s.getX() && Game.getP().getxPos() < s.getX() + 
					s.getWidth() && Game.getP().getyPos() + Game.getP().getHeight() > 
					s.getY()  && Game.getP().getyPos() < s.getY() +s.getHeight()){
				Game.switchg();
			}
		}
		
		if(Game.getP().getPlayerShape().intersects(Door.getDoorHitBox()) && isDoorOpen){
			isCompleted3 = true;
			Game.setCurLevel(Game.getLevelSelect());
			Game.getP().playerReset();
			
			for(Ice I : IceManager.getIce()){
				I.setSlide(false);
			}
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

	public int getWidth() {
		return Main.getScreenWidth()*2;
	}

	public int getHeight() {
		return Main.getScreenHeight();
	}

	private void generateWorld() {		
		wallsAroundMapx2();
		
		if(!keyGrabbed){
			Key.createKey(keyX, keyY, 180, 84);
		}
		
		Door.createDoor(getWidth() - 250, Main.getScreenHeight()-290, 150, 230);
		//ice
		IceManager.createIce(icex, icey, iceWidth, iceHeight);
		//spike
		SpikeManager.createSpike(Main.getScreenWidth()/2, getHeight()-132, 32, 32, false);
		SpikeManager.createSpike(keyX, getHeight()-164, 64, 64, false);
	}
	
	public static void setCompleted3(boolean b){
		isCompleted3 = b;
	}
}