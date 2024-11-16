package Levels;

import Media.*;
import Menus.*;
import core.*;
import core.Game;
import Levels.*;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

import GameObject.*;
import GameObject.Block.*;
import GameObject.Entity.*;
import GameObject.Entity.Hazard.*;
import GameObject.Entity.Hazard.Moving.*;
import GameObject.Entity.Hazard.Stationary.*;
import GameObject.Entity.Interactable.*;

import org.newdawn.slick.*;
import java.util.*;

public class Level2 extends Level {
	private int id;

	final private static float h = Main.getScreenHeight();
	final private static float w = Main.getScreenWidth();

	private static boolean isDoorOpen = false;
	private static boolean keyGrabbed = false;

	//moving platforms
	public int platformx = Main.getScreenWidth()/2;
	public int platformy = Main.getScreenHeight()/2;

	public int platformx1 = Main.getScreenWidth();
	public int platformy1 = Main.getScreenHeight()/2 - 50;


	public static int change = 0;

	public static boolean isCompleted2 = false;	

	public int doorTimer = 0;
	public int num1 = 5;
	public int num2 = 10;

	public Level2(int id)
	{
		this.id = id;



	} 

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{

	}
	public static void reset()
	{
		isDoorOpen = false;
		keyGrabbed = false;
	}

	public void platformMove()
	{
		change = (int)(Math.random()* 20);

		platformx += num1;
		if(platformx + 200 > Main.getScreenWidth() || platformx < 100)
		{
			num1 = num1 * (-1);
		}


		platformx1 += num2;
		if(platformx1 + 200 > Main.getScreenWidth()*2 || platformx1 < Main.getScreenWidth())
		{
			num2 = num2 * (-1);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		platformMove();
		TileManager.clearTiles();
		Border.clearBorders();
		SpikeManager.clearSpikes();
		SwitchManager.clearSwitches();
		IceManager.clearIce();
		pushBlockManager.clearPushBlocks();

		if(keyGrabbed)
		{
			doorTimer++;
		}
		generateWorld();


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
			isCompleted2 = true;
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


	public int getID() 
	{
		return id;
	}

	public void render(Graphics g) throws SlickException
	{
		//	
		g.setColor(Color.white);
		for(Tile t : TileManager.getWhiteTiles()) 
		{
			t.render(g);
		}
		g.setColor(Color.black);
		for(Tile t : TileManager.getBlackTiles()) 
		{
			t.render(g);
		}
		for(Spike s : SpikeManager.getSpikes())
		{
			s.render(g);
		}
		for(Switch s : SwitchManager.getSwitches())
		{
			s.render(g);
		}
		//door & key
		if(!keyGrabbed)
		{

			AnimationLoader.returnAnimation("key").draw(platformx1,platformy1 - 90, 90, 90);


		}




		if(keyGrabbed)
		{

			if(doorTimer < 25)
			{
				AnimationLoader.returnAnimation("door").draw(platformx + 75, platformy - 190, 150, 230);
			}
			if(doorTimer >= 25)
			{
				ImageLoader.returnImages("openDoor").draw(platformx + 75, platformy - 190, 150, 230);
			}
		}
		else
		{
			ImageLoader.returnImages("door").draw(platformx + 40, platformy - 190, 150, 230);
		}
		Cooldown.draw(g);

	}

	public int getWidth() 
	{
		return Main.getScreenWidth()*3;
	}

	public int getHeight() 
	{
		return Main.getScreenHeight();
	}

	public static boolean isCompleted2() {
		return isCompleted2;
	}

	public static void setCompleted2(boolean isCompleted2) {
		Level2.isCompleted2 = isCompleted2;
	}

	private void generateWorld() {
		wallsAroundMap();

		//Floors
		TileManager.createBorder(0, 980, getWidth(), 980);
		TileManager.createBorder(0,  100, getWidth(), 100);


		//moving platforms
		TileManager.createTile(platformx, platformy, 200, 30, "white");
		TileManager.createBorder(platformx,  platformy + 30, platformx + 200, platformy + 30);
		TileManager.createBorder(platformx,  platformy, platformx + 200, platformy);

		TileManager.createTile(platformx1, platformy1, 200, 100, "white");
		TileManager.createBorder(platformx1, platformy1, platformx1+200, platformy1);
		TileManager.createBorder(platformx1, platformy1 + 100, platformx1 + 200, platformy1 + 100);
		TileManager.createBorder(platformx1, platformy1 + 5, platformx1, platformy1 + 100 + 5);
		TileManager.createBorder(platformx1 + 200, platformy1 - 5, platformx1 + 200, platformy1 + 100 - 5);


		//Tiles
		TileManager.createTile(0, 980, getWidth(), 100, "white");
		TileManager.createTile(0, 0, getWidth(), 100, "white");

		//switches
		SwitchManager.createSwitch(1520,Main.getScreenHeight()-200, 100, 100, true);
		SwitchManager.createSwitch(1520,100, 100, 100, false);
		SwitchManager.createSwitch(3240,Main.getScreenHeight()-200, 100, 100, true);
		SwitchManager.createSwitch(3240,100, 100, 100, false);

		//Walls
		TileManager.createBorder(100, 0, 100,Main.getScreenHeight());
		TileManager.createBorder(5660, 0, 5660, Main.getScreenHeight());
		TileManager.createTile(0, 100, 100,Main.getScreenHeight(), "white");
		TileManager.createTile(5660, 100, 5660,Main.getScreenHeight(), "white");

		//door & key
		if(!keyGrabbed)
		{
			Key.createKey(platformx1,platformy1 - 90, 90, 90);
		}
		Door.createDoor(platformx + 75, platformy - 190, 150, 230);

	}

}