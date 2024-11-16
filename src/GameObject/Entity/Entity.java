package GameObject.Entity;

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

public abstract class Entity extends GameObject
{
	public Entity()
	{
		
	}
	
	public abstract void update() ;

	public abstract void render(Graphics g) throws SlickException;

	public abstract void applyCollision(GameObject o);
}
