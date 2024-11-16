package GameObject.Entity.Interactable;

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


public class Key 
{
	private int x;
	private int y;
	private int width;
	private int height;
	
	private static ArrayList<Key> keys = new ArrayList<Key>();
	private static Shape keyHitBox;
	
	public Key(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		keyHitBox = new Rectangle(x,y,width,height);
	}
	
	public void update()
	{
		if(keyHitBox != null)
		{
			keyHitBox.setLocation(x, y);
		}
	}
	
	public void render(Graphics g) throws SlickException
	{
		ImageLoader.returnImages("key").draw(x,y,width,height);
		g.setColor(Color.transparent);
		g.draw(keyHitBox);
	}
	
	public static void clearKeys()
	{
		keys.clear();
	}

	public static void createKey(int x, int y, int width, int height)
	{
		keys.add(new Key(x,y,width,height));
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

	public ArrayList<Key> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<Key> keys) {
		Key.keys = keys;
	}

	public static Shape getKeyHitBox() {
		return keyHitBox;
	}

	public void setKeyHitBox(Shape keyHitBox) {
		Key.keyHitBox = keyHitBox;
	}
	
	
}
