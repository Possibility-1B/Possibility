package Menus;

import core.*;
import Media.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleLeave extends BasicGameState{
	private int id;
	private StateBasedGame sbg;
	private static Shape leave, yes, no;

	public TitleLeave(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		leave = new Rectangle(Main.getScreenWidth()/2 - 200, Main.getScreenHeight()/2 - 100, 400, 200);
		no = new Rectangle(Main.getScreenWidth()/2 + 50, Main.getScreenHeight()/2, 100, 50);
		yes = new Rectangle(Main.getScreenWidth()/2 - 150, Main.getScreenHeight()/2, 100, 50);
		
		this.sbg = sbg;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		ImageLoader.returnImages("welcome").draw(0, 0);

		g.setColor(Color.black);
		g.fill(leave);
		g.setColor(Color.white);
		g.draw(leave);
		g.drawString("Are you sure you want to quit?", (Main.getScreenWidth()/2) - 135, 
				Main.getScreenHeight()/2 - 65);
		
		if(Mouse.getX() > no.getMinX() && Mouse.getX() < no.getMaxX() && 
				(Main.getScreenHeight() - Mouse.getY()) > no.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < no.getMaxY()){
			g.setColor(new Color(125,125,125,100));
			g.fill(no);
			g.setColor(Color.white);
			g.draw(no);
			g.setColor(Color.white);
			g.drawString("No", (Main.getScreenWidth()/2 + 90), (Main.getScreenHeight()/2 + 15));
		}else{
			g.setColor(Color.gray);
			g.fill(no);
			g.setColor(Color.white);
			g.draw(no);
			g.drawString("No", (Main.getScreenWidth()/2 + 90), (Main.getScreenHeight()/2 + 15));
		}
		
		if(Mouse.getX() > yes.getMinX() && Mouse.getX() < yes.getMaxX() && 
				(Main.getScreenHeight() - Mouse.getY()) > yes.getMinY() && 
				(Main.getScreenHeight()-Mouse.getY()) < yes.getMaxY()){
			g.setColor(new Color(125,125,125,100));
			g.fill(yes);
			g.setColor(Color.white);
			g.draw(yes);
			g.setColor(Color.white);
			g.drawString("Yes", (Main.getScreenWidth()/2 - 115), (Main.getScreenHeight()/2 + 15));
		}else{
			g.setColor(Color.gray);
			g.fill(yes);
			g.setColor(Color.white);
			g.draw(yes);
			g.drawString("Yes", (Main.getScreenWidth()/2 - 115), (Main.getScreenHeight()/2 + 15));
		}
	}

	public void keyPressed(int key, char c){
		if(key == Input.KEY_ESCAPE){
			sbg.enterState(Main.TITLE_ID);
		}
	}
	
	public void mousePressed(int button, int x, int y){
		if(button == 0 && x > yes.getMinX() && x < yes.getMaxX() && y > yes.getMinY() && y < yes.getMaxY()){
			Main.getGameContainer().exit();
	    }  
		if(button == 0 && x > no.getMinX() && x < no.getMaxX() && y > no.getMinY() && y < no.getMaxY()){
			sbg.enterState(Main.TITLE_ID);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {}	
}