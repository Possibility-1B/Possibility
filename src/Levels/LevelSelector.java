package Levels;

import Media.*;
import core.*;
import core.Game;
import GameObject.Block.*;
import GameObject.Entity.Hazard.Stationary.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.*;

public class LevelSelector extends Level{	
	private static Shape lvl1, lvl2, lvl3, lvl4, lvl5, lvl6, lvl7, lvl8, credits;
	
	private int start = Main.getScreenWidth()/8 + 300, after = 300;

	public LevelSelector(){ 
		lvl1 = new Rectangle(start, getHeight()/2, 150, 200);
		lvl2 = new Rectangle(start + after, getHeight()/2, 150, 200);
		lvl3 = new Rectangle(start + (2*after), getHeight()/2, 150,200);
		lvl4 = new Rectangle(start + (3*after), getHeight()/2, 150,200);
		lvl5 = new Rectangle(start + (4*after), getHeight()/2, 150,200);
		lvl6 = new Rectangle(start + (5*after), getHeight()/2, 150,200);
		lvl7 = new Rectangle(start + (6*after), getHeight()/2, 150,200);
		lvl8 = new Rectangle(start + (7*after), getHeight()/2, 150,200);
		credits = new Rectangle(start + (8*after), getHeight()/2, 150,200);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Ice.slide = false;
		TileManager.clearTiles();
		Border.clearBorders();		
		Door.clearDoors();
		Game.getP().setgSwitch(false);
		
		if(Game.getP().getxPos() < Main.getScreenWidth()/8){
            Game.getP().setxPos(Main.getScreenWidth()/8);
        }
		
		if(Game.getP().getyPos() > ((getHeight()/2) + 200) || Game.getP().getyPos()
				< ((getHeight()/2) - 200)){
			Game.getP().setyPos(Main.getScreenHeight()/2);
        }
		generateWorld();
	}
	
	public void render(Graphics g) throws SlickException{
		g.setColor(Color.white);
		
		for(Tile t : TileManager.getWhiteTiles()) {
			t.render(g);
		}
		
		g.setColor(Color.black);
		
		for(Tile t : TileManager.getBlackTiles()) {
			t.render(g);
		}
		
		renderPortals();
		
		ImageLoader.returnImages("lvl1").draw(lvl1.getCenterX() - 12, getHeight()/2 - 50 - 80, 22, 102);	
		ImageLoader.returnImages("lvl2").draw(lvl2.getCenterX() - 12, getHeight()/2 - 50 - 80, 28, 100);	
		ImageLoader.returnImages("lvl3").draw(lvl3.getCenterX() - 12, getHeight()/2 - 50 - 80, 28, 100);
		ImageLoader.returnImages("lvl4").draw(lvl4.getCenterX() - 12, getHeight()/2 - 50 - 80, 31, 100);
		ImageLoader.returnImages("lvl5").draw(lvl5.getCenterX() - 12, getHeight()/2 - 50 - 80, 28, 100);
		ImageLoader.returnImages("lvl6").draw(lvl6.getCenterX() - 12, getHeight()/2 - 50 - 80, 28, 102);
		ImageLoader.returnImages("lvl7").draw(lvl7.getCenterX() - 12, getHeight()/2 - 50 - 80, 27, 99);
		ImageLoader.returnImages("lvl8").draw(lvl8.getCenterX() - 12, getHeight()/2 - 50 - 80, 27, 101);
		ImageLoader.returnImages("lvlCredits").draw(credits.getCenterX() - 100, getHeight()/2 - 105);
		
		ImageLoader.returnImages("glow").draw(-75, -75, getWidth() + 175, (getHeight()/2) - 200 + 120);
        ImageLoader.returnImages("glow").draw(-300, -75, 600,Main.getScreenHeight() + 50);
        ImageLoader.returnImages("glow").draw(-75, 675, getWidth() + 175, Main.getScreenHeight() + 50);
        ImageLoader.returnImages("glow").draw(-75, 675, getWidth() + 175, Main.getScreenHeight() + 50);
        ImageLoader.returnImages("glow").draw(Main.getScreenWidth() + 1320, 0, getWidth() + 175, Main.getScreenHeight() + 50);
        ImageLoader.returnImages("glow").draw(Main.getScreenWidth() + 1320, 0, getWidth() + 175, Main.getScreenHeight() + 50);
        
		ImageLoader.returnImages("pressE").draw(300,Main.getScreenHeight()/2 - 250, 300, 29);
	}

	public void generateWorld(){
		//top
		TileManager.createTile(0, 0, getWidth(), (getHeight()/2) - 200, "white");
		TileManager.createBorder(0, (getHeight()/2) - 200, getWidth(), (getHeight()/2) - 200);
		//bottom
		TileManager.createTile(0, (getHeight()/2) + 200, getWidth(), (getHeight()/2) + 200, "white");
		TileManager.createBorder(0, (getHeight()/2) + 200, getWidth(), (getHeight()/2) + 200);
		//left
		TileManager.createTile(0, 0, (Main.getScreenWidth()/8), getHeight(), "white");
		TileManager.createBorder((Main.getScreenWidth()/8), 0, (Main.getScreenWidth()/8), getHeight());
		//right
		TileManager.createTile(getWidth()-(Main.getScreenWidth()/4), 0, getWidth(), getHeight(), "white");		
		TileManager.createBorder(getWidth()-(Main.getScreenWidth()/4), 0, getWidth()-(Main.getScreenWidth()/4), getHeight());
		
		Door.createDoor((Main.getScreenWidth()/4 + 400), getHeight()/2, 100, 100);
	}

	private void renderPortals() throws SlickException{
		if(Game.getP().getPlayerShape().intersects(lvl1)){
			Level1.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl1.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl1.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl1.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(lvl2)){
			Level2.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl2.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl2.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl2.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(lvl3)){
			Level3.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl3.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl3.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl3.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(lvl4)){
			Level4.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl4.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl4.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl4.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(lvl5)){
			Level5.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl5.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl5.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl5.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(lvl6)){
			Level6.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl6.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl6.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl6.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(lvl7)){
			Level7.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl7.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl7.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl7.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(lvl8)){
			Level8.reset();
			AnimationLoader.returnAnimation("openedPortal").draw(lvl8.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(lvl8.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("portal").draw(lvl8.getX(),  getHeight()/2 , 150, 200);
		}
		
		if(Game.getP().getPlayerShape().intersects(credits)){
			AnimationLoader.returnAnimation("openedPortal").draw(credits.getX(),  getHeight()/2 , 150, 200);
			ImageLoader.returnImages("E").draw(credits.getX() + 44, lvl1.getY() - 60, 60, 72 );
		}else{
			AnimationLoader.returnAnimation("credits").draw(credits.getX(),  getHeight()/2 , 150, 200);
		}
	}
	
	public void keyPressed(int key, char c){
		if((key == Input.KEY_E) && Game.getP().getPlayerShape().intersects(lvl1)){
			Game.setCurLevel(Game.getLevel1());
		}
	}
	
	public void mousePressed(int button, int x, int y){
		if((button == Input.MOUSE_LEFT_BUTTON) && Game.getP().getPlayerShape().intersects(lvl1)){
			Game.setCurLevel(Game.getLevel1());
		}
	}

	public int getWidth(){
		return Main.getScreenWidth()*2;
	}

	public int getHeight(){
		return Main.getScreenHeight();
	}

	public static Shape getLvl1(){
		return lvl1;
	}

	public static Shape getLvl2() {
		return lvl2;
	}

	public static Shape getLvl3() {
		return lvl3;
	}

	public static Shape getLvl4() {
		return lvl4;
	}

	public static Shape getLvl5() {
		return lvl5;
	}
	
	public static Shape getLvl6() {
		return lvl6;
	}

	public static Shape getLvl7() {
		return lvl7;
	}

	public static Shape getLvl8() {
		return lvl8;
	}

	public static Shape getCredits() {
		return credits;
	}	
}