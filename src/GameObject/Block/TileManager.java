package GameObject.Block;

import Media.*;
import Menus.*;
import core.*;
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

public class TileManager 
{
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected String type;
	
	protected Shape tileShape;
	
	protected static ArrayList<Tile> whiteTiles = new ArrayList<Tile>();
	protected static ArrayList<Tile> blackTiles = new ArrayList<Tile>();

	private ArrayList<Tile> unusedWhiteTiles = new ArrayList<Tile>();
	private ArrayList<Tile> unusedBlackTiles = new ArrayList<Tile>();
	
	public TileManager() 
	{
		whiteTiles = Tile.getWhiteTiles();
		blackTiles = Tile.getBlackTiles();
	}

	public static void clearTiles()
	{
		whiteTiles.clear();
		blackTiles.clear();
	}
	
	public static void createTile(float x, float y, float width, float height, String type)
	{
		if(type.equalsIgnoreCase("white"))
		{
			whiteTiles.add(new Tile(x,y,width,height,type));
		}
		if(type.equalsIgnoreCase("black"))
		{
			blackTiles.add(new Tile(x,y,width,height,type));
		}
	}
	public static void createBorder(float x, float y, float x2, float y2)
	{
		Border.addBorders(new Line(x, y, x2, y2));
	}
	public void render(Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame. 
	}
	

	public void removeTile(Tile p)
	{
		unusedWhiteTiles.add(p);
		unusedBlackTiles.add(p);
	}
	
	public void removeTiles()
	{
		for(Tile p : unusedWhiteTiles)
		{
			whiteTiles.remove(p);
		}
		for(Tile p : unusedBlackTiles)
		{
			blackTiles.remove(p);
		}
	}

	public static ArrayList<Tile> getWhiteTiles() {
		return whiteTiles;
	}

	public static void setWhiteTiles(ArrayList<Tile> whiteTiles) {
		TileManager.whiteTiles = whiteTiles;
	}

	public static ArrayList<Tile> getBlackTiles() {
		return blackTiles;
	}

	public static void setBlackTiles(ArrayList<Tile> blackTiles) {
		TileManager.blackTiles = blackTiles;
	}

	public ArrayList<Tile> getUnusedWhiteTiles() {
		return unusedWhiteTiles;
	}

	public void setUnusedWhiteTiles(ArrayList<Tile> unusedWhiteTiles) {
		this.unusedWhiteTiles = unusedWhiteTiles;
	}

	public ArrayList<Tile> getUnusedBlackTiles() {
		return unusedBlackTiles;
	}

	public void setUnusedBlackTiles(ArrayList<Tile> unusedBlackTiles) {
		this.unusedBlackTiles = unusedBlackTiles;
	}

	
}
