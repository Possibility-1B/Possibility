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

public class SwitchManager 
{
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected String type;

	protected Shape tileShape;

	protected static ArrayList<Switch> Switches = new ArrayList<Switch>();

	private ArrayList<Switch> unusedSwitches = new ArrayList<Switch>();

	public SwitchManager() 
	{
		Switches = Switch.getSwitches();
	}

	public static void clearSwitches()
	{
		Switches.clear();
	}

	public static void createSwitch(float x, float y, float width, float height, boolean up)
	{

		Switches.add(new Switch(x,y,width,height,up));

	}
	public static void createBorder(float x, float y, float x2, float y2)
	{
		Border.addBorders(new Line(x, y, x2, y2));
	}
	public void render(Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame. 
	}
	public void removeTiles()
	{
		for(Switch p : unusedSwitches)
		{
			unusedSwitches.remove(p);
		}
		for(Switch p : unusedSwitches)
		{
			unusedSwitches.remove(p);
		}
	}

	public static ArrayList<Switch> getSwitches() {
		return Switches;
	}

	public static void setSwitches(ArrayList<Switch> switches) {
		Switches = switches;
	}

	public ArrayList<Switch> getUnusedSwitches() {
		return unusedSwitches;
	}

	public void setUnusedSwitchs(ArrayList<Switch> unusedSwitches) {
		this.unusedSwitches = unusedSwitches;
	}



}
