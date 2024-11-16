package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Media.*;
import Menus.*;
import core.*;
import Levels.*;
import Levels.LevelSelector;
import GameObject.*;
import GameObject.Block.*;
import GameObject.Entity.*;
import GameObject.Entity.Hazard.*;
import GameObject.Entity.Hazard.Moving.*;
import GameObject.Entity.Hazard.Stationary.*;
import GameObject.Entity.Interactable.*;
import org.lwjgl.input.*;

public class Main extends StateBasedGame 
{
	public final static int FRAMES_PER_SECOND = 60;
	private static AppGameContainer appgc;
	
	public static final int LOGO_ID = 0;
	public static final int TITLE_ID = 1;
	public static final int CONTROLS_ID = 2;
    public static final int GAME_ID  = 3;

    public static final int DEATH_ID = 5;
    public static final int TITLELEAVE_ID = 6;

    
    private BasicGameState logo;
    private BasicGameState title;
    private BasicGameState controls;
    private BasicGameState game;

    private BasicGameState death;
    private BasicGameState titleLeave;



    
	public Main(String name) 
	{
		super(name);
		
		logo = new Intro(LOGO_ID);
		title = new Title(TITLE_ID);
		controls = new Controls(CONTROLS_ID);
		game = new Game(GAME_ID);

		death = new Death(DEATH_ID);
		titleLeave = new TitleLeave(TITLELEAVE_ID);
	}

	public static int getScreenWidth()
	{
		return appgc.getScreenWidth();
	}
	
	public static int getScreenHeight()
	{
		return appgc.getScreenHeight();
	}
	
	public static GameContainer getGameContainer()
	{
		return appgc;
	}
	
	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(logo);
		addState(title);
		addState(controls);
		addState(game);

		addState(death);
		addState(titleLeave);

	}

	public static void main(String[] args) 
	{
		try 
		{
			appgc = new AppGameContainer(new Main("My First Project"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
			appgc.setTargetFrameRate(FRAMES_PER_SECOND);
			appgc.start();
			appgc.setVSync(true);

		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}

	}
}