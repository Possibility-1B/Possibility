package Menus;

import Media.*;
import core.*;
import core.Game;
import Levels.*;
import GameObject.Block.*;
import GameObject.Entity.Hazard.Stationary.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Title extends BasicGameState{
	private int id;
	private StateBasedGame sbg;
	private static Shape newGame, resume, controls, quit;

	public Title(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		newGame = new Rectangle(Main.getScreenWidth()/2 - 100, Main.getScreenHeight()/2 - 100, 200, 75);
		resume = new Rectangle(Main.getScreenWidth()/2 - 100, Main.getScreenHeight()/2 + 25, 200, 75);
		controls = new Rectangle(Main.getScreenWidth()/2 - 100, Main.getScreenHeight()/2 + 150, 200, 75);
		quit = new Rectangle(Main.getScreenWidth()/2 - 100, Main.getScreenHeight()/2 + 275, 200, 75);
		
		this.sbg = sbg;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		ImageLoader.returnImages("welcome").draw(0, 0);

		if(Mouse.getX() > newGame.getMinX() && Mouse.getX() < newGame.getMaxX() && 
				(Main.getScreenHeight() - Mouse.getY()) > newGame.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < newGame.getMaxY()){
			g.setColor(new Color(125,125,125,100));
			g.fill(newGame);
			g.setColor(Color.white);
			g.draw(newGame);
			g.setColor(Color.white);
			g.drawString("New Game", ((Main.getScreenWidth()/2 - 40)), (Main.getScreenHeight()/2 - 75));
		}else{
			g.setColor(Color.gray);
			g.fill(newGame);
			g.setColor(Color.white);
			g.draw(newGame);
			g.drawString("New Game", ((Main.getScreenWidth()/2 - 40)), (Main.getScreenHeight()/2 - 75));
		}
		
		if(Mouse.getX() > resume.getMinX() && Mouse.getX() < resume.getMaxX() && 
				(Main.getScreenHeight() - Mouse.getY()) > resume.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < resume.getMaxY()){
			g.setColor(new Color(125,125,125,100));
			g.fill(resume);
			g.setColor(Color.white);
			g.draw(resume);
			g.setColor(Color.white);
			g.drawString("Resume", ((Main.getScreenWidth()/2 - 30)), (Main.getScreenHeight()/2 + 50));
		}else{
			g.setColor(Color.gray);
			g.fill(resume);
			g.setColor(Color.white);
			g.draw(resume);
			g.drawString("Resume", ((Main.getScreenWidth()/2 - 30)), (Main.getScreenHeight()/2 + 50));
		}
		
		if(Mouse.getX() > controls.getMinX() && Mouse.getX() < controls.getMaxX() && 
				(Main.getScreenHeight() - Mouse.getY()) > controls.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < controls.getMaxY()){
			g.setColor(new Color(125,125,125,100));
			g.fill(controls);
			g.setColor(Color.white);
			g.draw(controls);
			g.setColor(Color.white);
			g.drawString("Controls", ((Main.getScreenWidth()/2 - 40)), (Main.getScreenHeight()/2 + 175));
		}else{
			g.setColor(Color.gray);
			g.fill(controls);
			g.setColor(Color.white);
			g.draw(controls);
			g.drawString("Controls", ((Main.getScreenWidth()/2 - 40)), (Main.getScreenHeight()/2 + 175));
		}
		
		if(Mouse.getX() > quit.getMinX() && Mouse.getX() < quit.getMaxX() && 
				(Main.getScreenHeight() - Mouse.getY()) > quit.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < quit.getMaxY()){
			g.setColor(new Color(125,125,125,100));
			g.fill(quit);
			g.setColor(Color.white);
			g.draw(quit);
			g.setColor(Color.white);
			g.drawString("Quit", ((Main.getScreenWidth()/2 - 25)), (Main.getScreenHeight()/2 + 300));
		}else{
			g.setColor(Color.gray);
			g.fill(quit);
			g.setColor(Color.white);
			g.draw(quit);
			g.drawString("Quit", ((Main.getScreenWidth()/2 - 25)), (Main.getScreenHeight()/2 + 300));
		}
	}
	
	public void keyPressed(int key, char c){
		if(key == Input.KEY_ESCAPE){
			sbg.enterState(Main.TITLELEAVE_ID);
		}
	}
	
	public void mousePressed(int button, int x, int y){
		if(button == 0 && x > newGame.getMinX() && x < newGame.getMaxX() && 
				y > newGame.getMinY() && y < newGame.getMaxY()){
			sbg.enterState(Main.GAME_ID);
			TileManager.clearTiles();
			Border.clearBorders();
			SpikeManager.clearSpikes();
			Level1.setCompleted1(false);
			Level2.setCompleted2(false);
			Game.setCurLevel(Game.getLevelSelect());
			Game.getP().playerReset();
	    }  
		
		if(button == 0 && x > resume.getMinX() && x < resume.getMaxX() && 
				y > resume.getMinY() && y < resume.getMaxY()){
			sbg.enterState(Main.GAME_ID);
	    }  
		
	    if(button == 0 && x > controls.getMinX() && x < controls.getMaxX() &&
	    		y > controls.getMinY() && y < controls.getMaxY()){
	        sbg.enterState(Main.CONTROLS_ID);
	    }  
	    
	    if(button == 0 && x > quit.getMinX() && x < quit.getMaxX() && y > quit.getMinY() 
	    		&& y < quit.getMaxY()){
	        sbg.enterState(Main.TITLELEAVE_ID);
	    }  
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {}	
}