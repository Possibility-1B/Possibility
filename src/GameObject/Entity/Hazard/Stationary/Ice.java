package GameObject.Entity.Hazard.Stationary;

import java.util.ArrayList;

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

public class Ice {


	private float icex;
	private float icey;
	private float width;
	private float height;
	
	public static boolean slide = false;
	protected Shape iceShape;
	
	private static ArrayList<Ice> ices = new ArrayList<Ice>();
	
	public Ice(float x, float y, float width, float height)
	{
		this.icex = x;
		this.icey = y;
		this.width = width;
		this.height = height;
		iceShape = new Rectangle(this.icex,this.icey, this.width, this.height);
	}
	public static void reset()
	{
		for(Ice I : IceManager.getIce())
		{
			I.setSlide(false);
		}
	}
	
	public void update()
	{
		slide();
		movement();
	}
	
	public void render(Graphics g) throws SlickException
	{
		if(iceShape != null)
		{
			g.setColor(new Color(113, 208, 227));
			g.fill(iceShape);
		}
	}
	
	public void movement()
	{
		
		if(slide)
		{
			Helper.i = 0;
		}
	}
	public void slide() 
	{
		if((Game.getP().getxPos() >= icex && Game.getP().getxPos() <= (icex + width)) 
			&& (Game.getP().getyPos() > icey - Game.getP().getHeight() -1))
		{
			slide = true;
		}
		else if((Game.getP().getxPos() >= icex && Game.getP().getxPos() <= (icex + width)) 
			&& (Game.getP().getyPos() < icey + Game.getP().getHeight()+1))
		{
			slide = true;
		}
		else
		{
			slide = false;
		}
	}

	public Shape getIce() {
		return iceShape;
	}
	
	public static ArrayList<Ice> getIces() {
		return ices;
	}
	public static boolean isSlide() {
		return slide;
	}
	public void setSlide(boolean slide) {
		Ice.slide = slide;
	}
	
	
}
