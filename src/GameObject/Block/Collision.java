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

public class Collision {
	
	static ArrayList<Shape> tempList = new ArrayList<Shape>();

	public Collision()
	{
		
	}
	
	public static ArrayList<Shape> Collisions(Shape s)
	{
		tempList.clear();
		for(Shape b : Border.getBorders())
		{
			if(s.intersects(b))
			{
				tempList.add(b);
			}
		}
		return tempList;
	}

	public static ArrayList<Shape> getTempList() {
		return tempList;
	}

	public static void setTempList(ArrayList<Shape> tempList) {
		Collision.tempList = tempList;
	}
	
}
