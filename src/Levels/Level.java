package Levels;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.state.StateBasedGame;

import GameObject.Block.Border;
import GameObject.Block.TileManager;
import GameObject.Block.pushBlock;
import GameObject.Block.pushBlockManager;

import core.Main;

public abstract class Level{
	public abstract void render(Graphics g) throws SlickException;
	
	public abstract void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;
	
	//Walls around whole map/screen
	public static void wallsAroundMap(){
		//left wall
		TileManager.createBorder(1, 0, 1, Main.getScreenHeight()); 
		//top
		TileManager.createBorder(1, 0, Main.getScreenWidth()*3 , 0); 
		//right wall
		TileManager.createBorder(Main.getScreenWidth()*3, 0, Main.getScreenWidth()*3, 
				Main.getScreenHeight());
		//bottom
		TileManager.createBorder(1, Main.getScreenHeight()-1 , Main.getScreenWidth()*3,
				Main.getScreenHeight()-1);
		
		//floors and inner walls
		TileManager.createBorder(0, 980, Main.getScreenWidth()*3, 980);
		TileManager.createBorder(0,  100, Main.getScreenWidth()*3, 100);
		TileManager.createTile(0, 980, Main.getScreenWidth()*3, 100, "white");
		TileManager.createTile(0, 0, Main.getScreenWidth()*3, 100, "white");
				
		TileManager.createBorder(100, 0, 100,Main.getScreenHeight());
		TileManager.createBorder(Main.getScreenWidth()*3-100, 0, Main.getScreenWidth()*3-100,
				Main.getScreenHeight());
		TileManager.createTile(0, 100, 100,Main.getScreenHeight(), "white");
		TileManager.createTile(Main.getScreenWidth()*3-100, 100, Main.getScreenWidth()*3-100,
				Main.getScreenHeight(), "white");
	}
	
	//Walls around whole map/screen	
	public static void wallsAroundMapx2(){
		//left wall
		TileManager.createBorder(1, 0, 1, Main.getScreenHeight()); 
		//top
		TileManager.createBorder(1, 0, Main.getScreenWidth()*2 , 0); 
		//right wall
		TileManager.createBorder(Main.getScreenWidth()*2, 0, Main.getScreenWidth()*2,
				Main.getScreenHeight()); 
		//bottom
		TileManager.createBorder(1, Main.getScreenHeight()-1 , Main.getScreenWidth()*2,
				Main.getScreenHeight()-1); 
	
		//floors & inner walls
		TileManager.createBorder(0, 980, Main.getScreenWidth()*2, 980);
		TileManager.createBorder(0,  100, Main.getScreenWidth()*2, 100);
		TileManager.createTile(0, 980, Main.getScreenWidth()*2, 100, "white");
		TileManager.createTile(0, 0, Main.getScreenWidth()*2, 100, "white");
		
		TileManager.createBorder(100, 0, 100,Main.getScreenHeight());
		TileManager.createBorder(Main.getScreenWidth()*2-100, 0, Main.getScreenWidth()*2-100,
				Main.getScreenHeight());
		TileManager.createTile(0, 100, 100,Main.getScreenHeight(), "white");
		TileManager.createTile(Main.getScreenWidth()*2-100, 100, Main.getScreenWidth()*2-100,
				Main.getScreenHeight(), "white");
	}
	
	public void pushBlockWalls(){
		for(pushBlock pB : pushBlockManager.getPushBlocks()){
			//left
			Border.addBorders(new Line(pB.getBoxShape().getMinX(), pB.getBoxShape().getMinY() + 2,
					pB.getBoxShape().getMinX(), pB.getBoxShape().getMaxY() - 2));
			//right
			Border.addBorders(new Line(pB.getBoxShape().getMaxX(), pB.getBoxShape().getMinY() + 2, 
					pB.getBoxShape().getMaxX(), pB.getBoxShape().getMaxY() - 2));
			//top
			Border.addBorders(new Line(pB.getBoxShape().getMinX(), pB.getBoxShape().getMinY(),
					pB.getBoxShape().getMaxX(), pB.getBoxShape().getMinY()));
			//bottom
			Border.addBorders(new Line(pB.getBoxShape().getMinX(), pB.getBoxShape().getMaxY(), 
					pB.getBoxShape().getMaxX(), pB.getBoxShape().getMaxY()));
		}
	}
	
	public abstract int getWidth();

	public abstract int getHeight();
}
