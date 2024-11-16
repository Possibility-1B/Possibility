package GameObject.Entity.Hazard.Stationary;

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

public class SpikeManager
{	
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	protected static Shape spikeShape;
	
	protected static ArrayList<Spike> spikes = new ArrayList<Spike>();
	private static ArrayList<Spike> unusedSpikes= new ArrayList<Spike>();
	
	public SpikeManager() 
	{
		spikes = Spike.getSpikes();
	}

	public static void clearSpikes()
	{
		spikes.clear();
	}
	
	public static void createSpike(float x, float y, float width, float height, boolean flipped)
	{
		spikes.add(new Spike(x,y,width,height,flipped));
	}
	
	public static void render(Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame. 
	}
	

	public static void removeSpike(Spike s)
	{
		unusedSpikes.add(s);
	}
	
	public static void removeSpikes()
	{
		for(Spike s : unusedSpikes)
		{
			spikes.remove(s);
		}
	}

	public static ArrayList<Spike> getSpikes() {
		return spikes;
	}

	public static void setSpikes(ArrayList<Spike> spikes) {
		SpikeManager.spikes = spikes;
	}
	
	
	
}