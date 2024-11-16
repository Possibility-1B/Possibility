package GameObject.Block;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

import core.Main;

public class Border {

	private static ArrayList <Shape> Borders = new ArrayList<Shape>();
	
	private static ArrayList <Shape> unusedBorders = new ArrayList<Shape>();

	public static void flipBorders()
	{
		for(int i = 0; i < Borders.size(); i++)
		{
			Borders.get(i).setCenterY(Main.getScreenHeight() - Borders.get(i).getY());
		}
	}
	
	public static ArrayList<Shape> getBorders() {
		return Borders;
	}

	public static void addBorders(Shape newLine) {
		Borders.add(newLine);
	}
	
	public static void clearBorders() 
	{
		Borders.clear();
	}
	
	public static void drawBorders(Graphics g)
	{
		for(Shape s:Borders)
		{
			g.setColor(Color.red);
			g.draw(s);
			
		}
	}
	
	
	
}
