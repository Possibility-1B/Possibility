package GameObject.Block;

import org.newdawn.slick.geom.*;

import java.util.*;
	
public class Collision {
	static ArrayList<Shape> tempList = new ArrayList<Shape>();
	
	public static ArrayList<Shape> Collisions(Shape s){
		tempList.clear();
		for(Shape b : Border.getBorders()){
			if(s.intersects(b)){
				tempList.add(b);
			}
		}
		return tempList;
	}
}