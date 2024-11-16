package GameObject.Entity.Hazard.Stationary;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public class IceManager {
	
	protected float x;
	protected float x2;
	
	protected static Shape iceShape;
	
	protected static ArrayList<Ice> ice = new ArrayList<Ice>();
	private static ArrayList<Ice> unusedIce= new ArrayList<Ice>();
	
	public IceManager() 
	{
		ice = Ice.getIces();
	}
	public static void clearIce()
	{
		ice.clear();
	}
	public static void createIce(float x, float y, float width, float height)
	{
		ice.add(new Ice(x,y,width,height));
	}
	public static void render(Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame. 
	}
	public static void removeIce(Ice I)
	{
		unusedIce.add(I);
	}
	public static ArrayList<Ice> getIce() {
		return ice;
	}
	public static void setIce(ArrayList<Ice> ice) {
		IceManager.ice = ice;
	}
	
}
