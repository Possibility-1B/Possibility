package Menus;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import Media.ImageLoader;
import Media.SoundLoader;
import core.Main;

public class Controls extends BasicGameState
{
	private int id;
	private StateBasedGame sbg;
	private Shape box;
	private Shape mainMenu;

	public Controls(int id) 
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
		this.sbg = sbg;
		box = new Rectangle((Main.getScreenWidth() / 2) - 150, Main.getScreenHeight()/2 - 250, 300, 500);
		mainMenu = new Rectangle(1, Main.getScreenHeight() - 51, 100, 50);

	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.	
		ImageLoader.returnImages("welcome").draw(0, 0);

		g.setColor(Color.black);
		g.fill(box);
		g.setColor(Color.white);
		g.draw(box);
		g.drawString("Controls:", (Main.getScreenWidth() / 2) - 45, (Main.getScreenHeight()/2) - 225);
		g.drawLine(Main.getScreenWidth()/2 - 53, Main.getScreenHeight()/2 - 205, Main.getScreenWidth()/2 + 40, Main.getScreenHeight()/2 - 205);
		
		g.drawString("Left: A", (Main.getScreenWidth() / 2) - 115-20, (Main.getScreenHeight()/2) - 175);
		g.drawString("Right: D", (Main.getScreenWidth() / 2) - 115-20, (Main.getScreenHeight()/2) - 150);
		g.drawString("Jump: W or SPACE", (Main.getScreenWidth() / 2) - 115-20, (Main.getScreenHeight()/2) - 125);
		g.drawString("Enter Level: E", (Main.getScreenWidth() / 2) - 115-20, (Main.getScreenHeight()/2) - 100);
		g.drawString("Pause: ESC", (Main.getScreenWidth() / 2) - 115-20, (Main.getScreenHeight()/2) - 75);
				
		
		if(Mouse.getX() > mainMenu.getMinX() && Mouse.getX() < mainMenu.getMaxX() && (Main.getScreenHeight() - Mouse.getY()) > mainMenu.getMinY() && (Main.getScreenHeight()-Mouse.getY()) < mainMenu.getMaxY())
		{
			g.setColor(new Color(0,0,0,100));
			g.fill(mainMenu);
			g.setColor(Color.white);
			g.draw(mainMenu);
			g.setColor(Color.white);
			g.drawString("Back", 30, Main.getScreenHeight() - 35);
		}
		else
		{
			g.setColor(Color.black);
			g.fill(mainMenu);
			g.setColor(Color.white);
			g.draw(mainMenu);
			g.setColor(Color.white);
			g.drawString("Back", 30, Main.getScreenHeight() - 35);
		}		
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
		// This code happens every time the user presses a key
		if(key == Input.KEY_ESCAPE)
		{
			sbg.enterState(Main.TITLE_ID);
		}
	}
	
	public void keyReleased(int key, char c)
	{
		
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	    if(button == 0 && x > mainMenu.getMinX() && x < mainMenu.getMaxX() && y > mainMenu.getMinY() && y < mainMenu.getMaxY())
	    {
	        sbg.enterState(Main.TITLE_ID);
	    }  
	}
	
}