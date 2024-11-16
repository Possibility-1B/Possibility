package core;

import Media.*;
import Menus.*;
import core.*;
import javafx.scene.Camera;
import Levels.*;
import GameObject.*;
import GameObject.Block.*;
import GameObject.Entity.*;
import GameObject.Entity.Hazard.*;
import GameObject.Entity.Hazard.Moving.*;
import GameObject.Entity.Hazard.Stationary.*;
import GameObject.Entity.Interactable.*;

import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

import GameObject.*;

import org.newdawn.slick.*;
import java.util.*;

public class Game extends BasicGameState 
{	
	private int id;
	public static Player p;
	public static TimerTask task;
	private static int gravSwitcher;
	ArrayList<GameObject> gameObjects;
	private static Level levelSelect;
	private static Level level1;
	private static Level level2;
	private static Level level3;
	private static Level level4;
	private static Level level5;
	private static Level level6;
	private static Level level7;
	private static Level level8;
	private static Level credits;

	private StateBasedGame sbg;
	public static Level curLevel = null;
	public static int timer = 91;

	public Game(int id) 
	{
		this.id = id;
	}

	public int getID() 
	{
		return id;		
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		this.sbg = sbg;

		gc.setShowFPS(true);
		if(curLevel != levelSelect)
		{
			p = new Player(100, 200);
		}
		else
		{
			p = new Player(400, Main.getScreenHeight()/2);
		}

		levelSelect = new LevelSelector(id);
		level1 = new Level1(id);
		level2 = new Level2(id);
		level3 = new Level3(id);
		level4 = new Level4(id);
		level5 = new Level5(id);
		level6 = new Level6(id);
		level7 = new Level7(id);
		level8 = new Level8(id);
		credits = new Credits(id);

		Timer timer = new Timer();

		task = new Helper();
		timer.schedule(task, 200, 100);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{		
		timer++;
		curLevel.update(gc, sbg, delta);

	
	     if(p.getCUR_HEALTH() == 0)
	        {
	            p.playerReset();
	            if(curLevel == level1)
	            {
	            	p.playerReset();
	            	Level1.reset();
	            }
	            if(curLevel == level2)
	            {
	            	p.playerReset();
	            	Level2.reset();
	            }
	            if(curLevel == level3)
	            {
	            	p.playerReset();
	            	Level3.reset();
	            }
	            if(curLevel == level4)
	            {
	            	p.playerReset();
	            	Level4.reset();
	            }
	            if(curLevel == level5)
	            {
	            	p.playerReset();
	            	Level5.reset();
	            }
	            if(curLevel == level6)
	            {
	            	p.playerReset();
	            	Level6.reset();
	            }
	            if(curLevel == level7)
	            {
	            	p.playerReset();
	            	Level7.reset();
	            }
	            if(curLevel == level8)
	            {
	            	p.playerReset();
	            	Level8.reset();
	            }	            
	            sbg.enterState(Main.DEATH_ID);
	        }
	 	p.update();
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		if(curLevel == credits)
		{
			
		}
		else if(curLevel == levelSelect || curLevel == level3 || curLevel == level5)
		{
			if(p.getxPos() >= Main.getScreenWidth()*2 -Main.getScreenWidth()/2-100)
			{
				g.translate( -Main.getScreenWidth()*2 +Main.getScreenWidth(), 0);
			}
			else if(p.getxPos() >= Main.getScreenWidth()/2-100 )
			{
				g.translate(-p.getxPos() + Main.getScreenWidth()/2-100, 0);
			}	
		}
		else
		{
			if(p.getxPos() >= Main.getScreenWidth()*3 -Main.getScreenWidth()/2-100)
			{
				g.translate( -Main.getScreenWidth()*3 +Main.getScreenWidth(), 0);
			}
			else if(p.getxPos() >= Main.getScreenWidth()/2-100 )
			{
				g.translate(-p.getxPos() + Main.getScreenWidth()/2-100, 0);
			}	
		}

		curLevel.render(g);
		p.render(g);	
	}

	public int getLevel()
	{
		return 0;
	}

	public int setLevel(int lvl)
	{
		return 0;
	}

	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		p.keyPressed(key, c);
		if(key == Input.KEY_E)
		{
			checkLevelSelect();
			
		}
		
		if(key == Input.KEY_ESCAPE)
		{
			sbg.enterState(Main.TITLE_ID);
		}
	}
	public static void switchg() 
	{
		if(timer > 90) 
		{
			gravSwitcher++;
			timer = 0;
			if(gravSwitcher % 2 == 0)
			{
				p.setgSwitch(false);

			}
			else
			{
				p.setgSwitch(true);
			}
		}
	}
	private void checkLevelSelect() {
		if(curLevel == levelSelect)		
		{
			if(p.getxPos() > LevelSelector.getLvl1().getMinX() &&  p.getxPos() < LevelSelector.getLvl1().getMaxX()) 
			{
				curLevel = level1;
				p.playerReset();
				
			}
			if(p.getxPos() > LevelSelector.getLvl2().getMinX() &&  p.getxPos() < LevelSelector.getLvl2().getMaxX()) 
			{
				curLevel = level2;
				p.playerReset();
			}	
			if(p.getxPos() > LevelSelector.getLvl3().getMinX() &&  p.getxPos() < LevelSelector.getLvl3().getMaxX()) 
			{
				curLevel = level3;
				p.playerReset();
			}
			
			if(p.getxPos() > LevelSelector.getLvl4().getMinX() &&  p.getxPos() < LevelSelector.getLvl4().getMaxX()) 
			{
			
				curLevel = level4;
				p.playerReset();
				
			}
			if(p.getxPos() > LevelSelector.getLvl5().getMinX() &&  p.getxPos() < LevelSelector.getLvl5().getMaxX()) 
			{
				p.playerReset();
				curLevel = level5;
			}
			if(p.getxPos() > LevelSelector.getLvl6().getMinX() &&  p.getxPos() < LevelSelector.getLvl6().getMaxX()) 
			{
				curLevel = level6;
				p.playerReset();
			}
			if(p.getxPos() > LevelSelector.getLvl7().getMinX() &&  p.getxPos() < LevelSelector.getLvl7().getMaxX()) 
			{
				curLevel = level7;
				p.playerReset();
			}
			if(p.getxPos() > LevelSelector.getLvl8().getMinX() &&  p.getxPos() < LevelSelector.getLvl8().getMaxX()) 
			{
				curLevel = level8;
				p.playerReset();
			}
			if(p.getxPos() > LevelSelector.getCredits().getMinX() &&  p.getxPos() < LevelSelector.getCredits().getMaxX()) 
			{
				curLevel = credits;
			}
			
		}
		
		 
	}

	public void keyReleased(int key, char c)
	{
		p.keyReleased(key, c);
	}

	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}

	public static Level getLevel1() {
		return level1;
	}

	public void setLevel1(Level level1) {
		Game.level1 = level1;
	}

	public static Level getCurLevel() {
		return curLevel;
	}

	public static void setCurLevel(Level curLevel) {
		Game.curLevel = curLevel;
	}

	public static Level getLevelSelect() {
		return levelSelect;
	}

	public static void setLevelSelect(Level levelSelect) {
		Game.levelSelect = levelSelect;
	}

	public static Player getP() {
		return p;
	}

	public static void setP(Player p) {
		Game.p = p;
	}

	public static Level getLevel2() {
		return level2;
	}

	public static void setLevel2(Level level2) {
		Game.level2 = level2;
	}

	public static Level getLevel3() {
		return level3;
	}

	public static void setLevel3(Level level3) {
		Game.level3 = level3;
	}

	public static Level getLevel4() {
		return level4;
	}

	public static void setLevel4(Level level4) {
		Game.level4 = level4;
	}

	public static Level getLevel5() {
		return level5;
	}

	public static void setLevel5(Level level5) {
		Game.level5 = level5;
	}

	public static Level getLevel6() {
		return level6;
	}

	public static void setLevel6(Level level6) {
		Game.level6 = level6;
	}

	public static Level getLevel7() {
		return level7;
	}

	public static void setLevel7(Level level7) {
		Game.level7 = level7;
	}

	public static Level getLevel8() {
		return level8;
	}

	public static void setLevel8(Level level8) {
		Game.level8 = level8;
	}

	public static Level getCredits() {
		return credits;
	}

	public static void setCredits(Level credits) {
		Game.credits = credits;
	}

}
