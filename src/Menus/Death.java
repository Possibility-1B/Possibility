package Menus;

import Media.*;
import core.*;
import core.Game;
import Levels.*;
import GameObject.Block.*;
import GameObject.Entity.Hazard.Stationary.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Death extends BasicGameState{
	private int id;
	private StateBasedGame sbg;
	private Shape levelSelect;
	private Shape retry;

	public Death(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.sbg = sbg;
		retry = new Rectangle((Main.getScreenWidth() / 2) - 130, 
				Main.getScreenHeight()/2 + 205, 250, 50);
		levelSelect = new Rectangle((Main.getScreenWidth() / 2) - 130, 
				Main.getScreenHeight()/2 + 315, 250, 50);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		ImageLoader.returnImages("youDied").draw(0, 0);;
		
		if(Mouse.getX() > retry.getMinX() && Mouse.getX() < retry.getMaxX() && 
				(Main.getScreenHeight() - Mouse.getY()) > retry.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < retry.getMaxY()){
			g.setColor(new Color(0,0,0,100));
			g.fill(retry);
			g.setColor(Color.white);
			g.draw(retry);
			g.setColor(Color.white);
			g.drawString("Retry Level", (Main.getScreenWidth() / 2) - 55, 
					(Main.getScreenHeight()/2) + 220);
		}else{
			g.setColor(Color.black);
			g.fill(retry);
			g.setColor(Color.white);
			g.draw(retry);
			g.setColor(Color.white);
			g.drawString("Retry Level", (Main.getScreenWidth() / 2) - 55, 
					(Main.getScreenHeight()/2) + 220);
		}		
		
		if(Mouse.getX() > levelSelect.getMinX() && Mouse.getX() < levelSelect.getMaxX() 
				&& (Main.getScreenHeight() - Mouse.getY()) > levelSelect.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < levelSelect.getMaxY()){
			g.setColor(new Color(0,0,0,100));
			g.fill(levelSelect);
			g.setColor(Color.white);
			g.draw(levelSelect);
			g.setColor(Color.white);
			g.drawString("Back to Main Menu", (Main.getScreenWidth() / 2) - 85, 
					(Main.getScreenHeight()/2) + 330);
		}else{
			g.setColor(Color.black);
			g.fill(levelSelect);
			g.setColor(Color.white);
			g.draw(levelSelect);
			g.setColor(Color.white);
			g.drawString("Back to Main Menu", (Main.getScreenWidth() / 2) - 85, 
					(Main.getScreenHeight()/2) + 330);
		}		
	}

	public void mousePressed(int button, int x, int y){
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() 
	    		&& y < retry.getMaxY()  && Game.getCurLevel() == Game.getLevel1()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(false);
			Level2.setCompleted2(false);
			Game.setCurLevel(Game.getLevel1());
			Game.getP().playerReset();
	    }  
	    
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() 
	    		&& y < retry.getMaxY()  && Game.getCurLevel() == Game.getLevel2()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(true);
			Level2.setCompleted2(false);
			Game.setCurLevel(Game.getLevel2());
			Game.getP().playerReset();
	    }  
	    
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() && 
	    		y < retry.getMaxY()  && Game.getCurLevel() == Game.getLevel3()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(true);
			Level2.setCompleted2(true);
			Level3.setCompleted3(false);
			Game.setCurLevel(Game.getLevel3());
			Game.getP().playerReset();
	    }
	    
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() && 
	    		y < retry.getMaxY()  && Game.getCurLevel() == Game.getLevel4()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(true);
			Level2.setCompleted2(true);
			Level3.setCompleted3(true);
			Level4.setCompleted4(false);

			Game.setCurLevel(Game.getLevel4());
			Game.getP().playerReset();
	    }
	    
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() && 
	    		y < retry.getMaxY() && Game.getCurLevel() == Game.getLevel5()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(true);
			Level2.setCompleted2(true);
			Level3.setCompleted3(true);
			Level4.setCompleted4(true);
			Level5.setCompleted5(false);

			Game.setCurLevel(Game.getLevel5());
			Game.getP().playerReset();
	    }
	    
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() && 
	    		y < retry.getMaxY() && Game.getCurLevel() == Game.getLevel6()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(true);
			Level2.setCompleted2(true);
			Level3.setCompleted3(true);
			Level4.setCompleted4(true);
			Level5.setCompleted5(true);
			Level6.setCompleted6(false);

			Game.setCurLevel(Game.getLevel6());
			Game.getP().playerReset();
	    }
	    
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() && 
	    		y < retry.getMaxY()  && Game.getCurLevel() == Game.getLevel7()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(true);
			Level2.setCompleted2(true);
			Level3.setCompleted3(true);
			Level4.setCompleted4(true);
			Level5.setCompleted5(true);
			Level6.setCompleted6(true);
			Level7.setCompleted7(false);

			Game.setCurLevel(Game.getLevel7());
			Game.getP().playerReset();
	    }
	    
	    if(button == 0 && x > retry.getMinX() && x < retry.getMaxX() && y > retry.getMinY() && 
	    		y < retry.getMaxY()  && Game.getCurLevel() == Game.getLevel8()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(true);
			Level2.setCompleted2(true);
			Level3.setCompleted3(true);
			Level4.setCompleted4(true);
			Level5.setCompleted5(true);
			Level6.setCompleted6(true);
			Level7.setCompleted7(true);
			Level8.setCompleted8(false);

			Game.setCurLevel(Game.getLevel8());
			Game.getP().playerReset();
	    }
	    
	    if(button == 0 && x > levelSelect.getMinX() && x < levelSelect.getMaxX() && 
	    		y > levelSelect.getMinY() && y < levelSelect.getMaxY()){
			sbg.enterState(Main.TITLE_ID);
			Game.setCurLevel(Game.getLevelSelect());
			Game.getP().playerReset();
	    }  
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {}
}