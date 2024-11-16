package GameObject;

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

public abstract class GameObject {
	
	public GameObject()
	{
		
	}
	
	abstract public void update();
	abstract public void render(Graphics g) throws SlickException;
	abstract public void applyCollision(GameObject o);
	public boolean isCollision(Shape a, Shape b)
	{
		if(a.intersects(b))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
