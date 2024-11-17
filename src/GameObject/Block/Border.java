package GameObject.Block;

import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class Border {
	private static ArrayList <Shape> Borders = new ArrayList<Shape>();
	
	public static ArrayList<Shape> getBorders(){
		return Borders;
	}

	public static void addBorders(Shape newLine){
		Borders.add(newLine);
	}
	
	public static void clearBorders(){
		Borders.clear();
	}
}