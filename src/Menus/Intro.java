package Menus;

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


public class Intro extends BasicGameState
{
	private int id;
	private StateBasedGame sbg;
	int timer;

	public Intro(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		ImageLoader.loadImages();
		AnimationLoader.loadAnimations();
		SoundLoader.loadSounds();
		Main.getGameContainer().setMouseCursor(ImageLoader.getCursor(), 1, 1);
		

		this.sbg = sbg;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		timer++;
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		ImageLoader.getImages("logo");
		if(timer > 550)
		{
			sbg.enterState(Main.TITLE_ID);
		}
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		Game.setCurLevel(Game.getLevelSelect());
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}
	
	public void keyPressed(int key, char c)
	{
		sbg.enterState(Main.TITLE_ID);
		
	}
	
	public void mousePressed(int button, int x, int y)
	{
		sbg.enterState(Main.TITLE_ID);
	}
	
}
