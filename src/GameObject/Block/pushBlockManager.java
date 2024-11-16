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

public class pushBlockManager 
{
	protected float x;
	protected float y;
	protected float width;
	protected float height;
	protected String type;
	
	protected Shape tileShape;
	
	protected static ArrayList<pushBlock> pushBlocks = new ArrayList<pushBlock>();

	private ArrayList<pushBlock> unusedPushBlocks = new ArrayList<pushBlock>();
	
	public pushBlockManager() 
	{
		pushBlocks = pushBlock.getPushBlocks();
	}

	public static void clearTiles()
	{
		pushBlocks.clear();
	}
	
	public static void clearPushBlocks()
	{
		pushBlocks.clear();
	}
	
	public static void createPushBlock(float x, float y, float width, float height)
	{
		pushBlocks.add(new pushBlock(x,y,width,height));
	}
	public void render(Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame. 
	}
	

	public void removePushBlock(pushBlock pB)
	{
		unusedPushBlocks.add(pB);
	}
	
	public void removeTiles()
	{
		for(pushBlock pB : unusedPushBlocks)
		{
			pushBlocks.remove(pB);
		}

	}

	public static ArrayList<pushBlock> getPushBlocks() {
		return pushBlocks;
	}

	public static void setPushBlocks(ArrayList<pushBlock> pushBlocks) {
		pushBlockManager.pushBlocks = pushBlocks;
	}

	public ArrayList<pushBlock> getUnusedPushBlocks() {
		return unusedPushBlocks;
	}

	public void setUnusedPushBlocks(ArrayList<pushBlock> unusedPushBlocks) {
		this.unusedPushBlocks = unusedPushBlocks;
	}

	
	
}
