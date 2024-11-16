package GameObject.Block;

import org.newdawn.slick.Graphics;

import GameObject.GameObject;

import Media.*;
import Menus.*;
import core.*;
import core.Game;
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


public class Switch extends Block
{
	private float x;
	private float y;
	private float width;
	private float height;
	private boolean up;
	
	private static ArrayList<Switch> Switches = new ArrayList<Switch>();
	private static Shape SwitchHitBox;
	
	public Switch(float x, float y, float width, float height, boolean up)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.up = up;
		SwitchHitBox = new Rectangle(x,y,width,height);
	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(SwitchHitBox != null)
		{
			if(up)
			{
				ImageLoader.returnImages("gravityUp").draw(x,y,width,height);
			}
			else
			{
				ImageLoader.returnImages("gravityDown").draw(x,y,width,height);
			}
			g.setColor(Color.transparent);
			g.draw(SwitchHitBox);	
		}
	}
	
	
	
	public static void clearSwitches()
	{
		Switches.clear();
	}
	
	public void applyCollision(GameObject o) 
	{
		
	}
	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static ArrayList<Switch> getSwitches() {
		return Switches;
	}

	public static void setSwitchs(ArrayList<Switch> switches) {
		Switches = switches;
	}

	public Shape getSwitchHitBox() {
		return SwitchHitBox;
	}

	public static void setSwitchHitBox(Shape switchHitBox) {
		SwitchHitBox = switchHitBox;
	}

	
}
