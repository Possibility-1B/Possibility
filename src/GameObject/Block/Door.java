package GameObject.Block;

import org.newdawn.slick.Graphics;

import GameObject.GameObject;

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


public class Door extends Block
{
	private int x;
	private int y;
	private int width;
	private int height;
	
	private static ArrayList<Door> doors = new ArrayList<Door>();
	private static Shape doorHitBox;
	
	public Door(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		doorHitBox = new Rectangle(x,y,width,height);
		
	}
	
	public void update()
	{
		if(doorHitBox != null)
		{
			doorHitBox.setLocation(x, y);
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		ImageLoader.returnImages("door").draw(x,y,width,height);
		g.setColor(Color.transparent);
		g.draw(doorHitBox);
	}
	
	public static void createDoor(int x, int y, int width, int height)
	{
		doors.add(new Door(x,y,width,height));
	}
	
	public static void clearDoors()
	{
		doors.clear();
	}
	
	public void applyCollision(GameObject o) 
	{
		
	}
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Door> getDoors() {
		return doors;
	}

	public void setDoors(ArrayList<Door> doors) {
		Door.doors = doors;
	}

	public static Shape getDoorHitBox() {
		return doorHitBox;
	}

	public void setDoorHitbox(Shape doorHitBox) {
		Door.doorHitBox = doorHitBox;
	}	
}

