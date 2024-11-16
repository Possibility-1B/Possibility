package GameObject.Entity.Hazard.Stationary;

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

public class Spike
{	
	private float x;
	private float y;
	private float width;
	private float height;
	private boolean flipped;
	
	public static boolean fallen = false;
	
	
	public static boolean fall = false;
	
	protected Shape spikeShape;
	

	private static ArrayList<Spike> spikes = new ArrayList<Spike>();
	
	public Spike(float x, float y, float width, float height, boolean flipped)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.flipped = flipped;
				
		spikeShape = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public void update()
	{	
		checkCollisions();
		if(spikeShape != null)
		{
			spikeShape.setLocation(x, y);
		}
	}
	public static void reset()
	{
		fall = false;
		fallen = false;
	}

	public void render(Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		if(spikeShape != null )
		{
			if(!flipped)
			{
				ImageLoader.returnImages("spike2").draw(x, y, width, height);
			}
			else
			{
				ImageLoader.returnImages("spike").draw(x, y, width, height);
			}
			g.setColor(Color.transparent);
			g.fill(spikeShape);
		}
	}
	
	
	
	public void checkCollisions()
	{
		if(Game.getP().getPlayerShape().intersects(spikeShape))
		{
			Game.getP().die();
		}
	}
	
	
	public void dropMovingRight(int spikeX)
	{
		if(Game.getP().getxPos() + 25 > (spikeX + (width/2)) - 50)
		{
			fallen = true;
		}
		if(y >= Main.getScreenHeight())
		{
			fallen = false;
		}
	}
	public boolean dropMovingLeft(int spikeX)
	{
		if(Game.getP().getxPos() + 25 > (spikeX + (width/2)) - 50)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public Shape getSpike() {
		return spikeShape;
	}

	public void setSpike(Shape spike) {
		this.spikeShape = spike;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public static ArrayList<Spike> getSpikes() {
		return spikes;
	}

	public static void setSpikes(ArrayList<Spike> spikes) {
		Spike.spikes = spikes;
	}
	
	
	
}