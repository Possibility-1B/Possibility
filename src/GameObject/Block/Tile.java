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

import GameObject.*;

import org.newdawn.slick.*;
import java.util.*;

public class Tile extends Block
{
	private float x;
	private float y;
	private float width;
	private float height;
	
	protected String color;
	
	protected Shape tileShape;
	
	private static ArrayList<Tile> BlackTiles = new ArrayList<Tile>();
	private static ArrayList<Tile> WhiteTiles = new ArrayList<Tile>();
	
	public Tile(float xS, float yS, float widthS, float HeightS, String color)
	{
		x = xS;
		y = yS;
		width = widthS;
		height = HeightS;
		
		this.color = color;
		
		tileShape = new Rectangle(x,y,width,height);
	}
	
	public void update() 
	{
		if(tileShape != null && color.equalsIgnoreCase("white"))
		{
			tileShape.setLocation(this.x, this.y);
		}
		
		if(tileShape != null && color.equalsIgnoreCase("black"))
		{
			tileShape.setLocation(this.x, this.y);
		}
	}

	public void render(Graphics g) 
	{
		if(tileShape != null && color.equalsIgnoreCase("white"))
		{
			g.setColor(Color.white);
			g.fill(tileShape);
		}
		
		if(tileShape != null && color.equalsIgnoreCase("black"))
		{
			g.setColor(Color.black);
			g.fill(tileShape);
		}
	}

	public void applyCollision(GameObject o) 
	{
		if(o instanceof Tile)
		{
			
		}
	}

	public static ArrayList<Tile> getBlackTiles() {
		return BlackTiles;
	}

	public void setBlackTiles(ArrayList<Tile> blackTiles) {
		BlackTiles = blackTiles;
	}

	public static ArrayList<Tile> getWhiteTiles() {
		return WhiteTiles;
	}

	public void setWhiteTiles(ArrayList<Tile> whiteTiles) {
		WhiteTiles = whiteTiles;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public Shape getTileShape() {
		return tileShape;
	}

	public void setTileShape(Shape tileShape) {
		this.tileShape = tileShape;
	}
	
}
