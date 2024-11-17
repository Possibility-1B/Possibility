package core;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Menus.*;

public class Main extends StateBasedGame {
	public final static int FRAMES_PER_SECOND = 60;
	public static final int LOGO_ID = 0, TITLE_ID = 1, CONTROLS_ID = 2, GAME_ID  = 3,
			DEATH_ID = 5, TITLELEAVE_ID = 6;
	private static AppGameContainer appgc;
	
    private BasicGameState logo, title, controls, game, death, titleLeave;
    
  public Main(String name){
		super(name);
	
		logo = new Intro(LOGO_ID);
		title = new Title(TITLE_ID);
		controls = new Controls(CONTROLS_ID);
		game = new Game(GAME_ID);
		death = new Death(DEATH_ID);
		titleLeave = new TitleLeave(TITLELEAVE_ID);
	}

	public static int getScreenWidth(){
		return appgc.getScreenWidth();
	}
	
	public static int getScreenHeight(){
		return appgc.getScreenHeight();
	}
	
	public static GameContainer getGameContainer(){
		return appgc;
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		addState(logo);
		addState(title);
		addState(controls);
		addState(game);
		addState(death);
		addState(titleLeave);
	}

	public static void main(String[] args){
		try{
			appgc = new AppGameContainer(new Main("My First Project"));
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
			appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), false);
			appgc.setTargetFrameRate(FRAMES_PER_SECOND);
			appgc.start();
			appgc.setVSync(true);
		}catch (SlickException e){
			e.printStackTrace();
		}
	}
}