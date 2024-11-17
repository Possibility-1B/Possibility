package GameObject.Block;

import java.util.ArrayList;
import org.newdawn.slick.geom.Shape;


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