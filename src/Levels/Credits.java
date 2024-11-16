package Levels;

import Media.*;
import Menus.*;
import core.*;
import core.Game;
import Levels.*;
import GameObject.*;
import GameObject.Block.*;
import GameObject.Entity.*;
import GameObject.Entity.Hazard.*;
import GameObject.Entity.Hazard.Moving.*;
import GameObject.Entity.Hazard.Stationary.*;
import GameObject.Entity.Interactable.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

import org.newdawn.slick.*;
import java.util.*;

public class Credits extends Level{

	private int id;
	
	private int timer = 0;
	private int scrollTimer = 0;
	
	public Credits(int id)
	{
		this.id = id;
		
	}
	
	public int getID()
	{
		return id;
	}
	
	public void render(Graphics g) throws SlickException 
	{
		g.setColor(new Color(197,197,197));
		ImageLoader.returnImages("Credits").draw(0, -scrollTimer);
		if(timer >= 3240) 
		{
			ImageLoader.returnImages("thanks").draw(150, Main.getScreenHeight()/2 - 300);
			ImageLoader.returnImages("rate").draw(400, Main.getScreenHeight()/2 - 100);
			ImageLoader.returnImages("thumbsup").draw(Main.getScreenWidth()/2 - 40, Main.getScreenHeight()/2 + 100);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
	{
		timer+=2;
		if(timer > 300 && scrollTimer < 3240)
		{
			scrollTimer+=2;
		}
		
		if(timer > 4200)
		{
			Game.setCurLevel(Game.getLevelSelect());
			scrollTimer = 0;
			timer = 0;
		}
	}

	public int getWidth() 
	{
		return Main.getScreenWidth();
	}

	public int getHeight() 
	{
		return Main.getScreenHeight() * 4;
	}

}
