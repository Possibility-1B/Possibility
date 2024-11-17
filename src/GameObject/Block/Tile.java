package GameObject.Block;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;

public class Tile extends Block{
	private float x, y, width, height;
	protected String color;
	protected Shape tileShape;
	private static ArrayList<Tile> BlackTiles = new ArrayList<Tile>();
	private static ArrayList<Tile> WhiteTiles = new ArrayList<Tile>();
	
	public Tile(float xS, float yS, float widthS, float HeightS, String color){
		x = xS;
		y = yS;
		width = widthS;
		height = HeightS;
		
		this.color = color;
		tileShape = new Rectangle(x,y,width,height);
	}
	
	public void update(){
		if(tileShape != null && color.equalsIgnoreCase("white")){
			tileShape.setLocation(this.x, this.y);
		}
		if(tileShape != null && color.equalsIgnoreCase("black")){
			tileShape.setLocation(this.x, this.y);
		}
	}

	public void render(Graphics g){
		if(tileShape != null && color.equalsIgnoreCase("white")){
			g.setColor(Color.white);
			g.fill(tileShape);
		}
		if(tileShape != null && color.equalsIgnoreCase("black")){
			g.setColor(Color.black);
			g.fill(tileShape);
		}
	}

	public static ArrayList<Tile> getBlackTiles(){
		return BlackTiles;
	}
	
	public static ArrayList<Tile> getWhiteTiles(){
		return WhiteTiles;
	}
}