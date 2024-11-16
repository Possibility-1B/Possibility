package GameObject.Block;

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

public class pushBlock extends Block
{
	private static float x;
	private static float y;
	private float width;
	private float height;
	private float vY = 0;
	protected Shape pushBlockHitBox;
	protected static ArrayList<pushBlock> pushBlocks = new ArrayList<pushBlock>();
	ArrayList<Shape> collisionList = new ArrayList<Shape>();

	public pushBlock(float x, float y, float width, float height)
	{
		pushBlock.x = x;
		pushBlock.y = y;
		this.height = height;
		this.width = width;
		//you will need to make a border that is on top of this box in the border class
		pushBlockHitBox = new Rectangle(x, y, width, height);
	}

	//this has to be called before player collisions but after player update
	public void update()
	{
		checkCollisionX();
		checkCollisionY();
		if(!Game.getP().getgSwitch())
		{
			if(y < 875 ) 
			{
				vY += 1.5f;
				y += vY;
				if(Game.getCurLevel() == Game.getLevel5())
				{
					Level5.setPushy(y);
				}
				if(Game.getCurLevel() == Game.getLevel6())
				{
					Level6.setPushy(y);
				}
				if(Game.getCurLevel() == Game.getLevel7())
				{
					Level7.setPushy(y);
				}
				if(Game.getCurLevel() == Game.getLevel8())
				{
					Level8.setPushy(y);
				}
			}
		}
		else 
		{
			if( y > 100) 
			{
				vY -= 1.5f;
				y += vY;
				if(Game.getCurLevel() == Game.getLevel5())
				{
					Level5.setPushy(y);
				}
				if(Game.getCurLevel() == Game.getLevel6())
				{
					Level6.setPushy(y);
				}
				if(Game.getCurLevel() == Game.getLevel7())
				{
					Level7.setPushy(y);
				}
				if(Game.getCurLevel() == Game.getLevel8())
				{
					Level8.setPushy(y);
				}
			}
		}
		if(pushBlockHitBox.intersects(Game.getP().getPlayerShape()))
		{
			if(pushBlockHitBox.getCenterX() < Game.getP().getPlayerShape().getCenterX() || pushBlockHitBox.getCenterX() >= Game.getP().getPlayerShape().getCenterX())
			{
				System.out.println(x);
				x += Game.getP().getxVelocity();
				if(Game.getCurLevel() == Game.getLevel5())
				{
					Level5.setPushx(x);
				}
				if(Game.getCurLevel() == Game.getLevel6())
				{
					Level6.setPushx(x);
				}
				if(Game.getCurLevel() == Game.getLevel7())
				{
					Level7.setPushx(x);
				}
				if(Game.getCurLevel() == Game.getLevel8())
				{
					Level8.setPushx(x);
				}
			}
		}
		else 
		{
		}
		if(pushBlockHitBox != null)
		{
			pushBlockHitBox.setLocation(x, y);
		}
	}

	private void checkCollisionX()
	{
		if (Collision.Collisions(pushBlockHitBox) != null)
		{
			collisionList.clear();
			collisionList = Collision.Collisions(pushBlockHitBox);
			for(Shape b : collisionList)
			{
				if (b.getMaxX() == b.getMinX())
				{
					if (b.getCenterX() > pushBlockHitBox.getCenterX())
					{
						x = b.getCenterX() - width;;
					}
					if (b.getCenterX() < pushBlockHitBox.getCenterX())
					{
						x = b.getCenterX();
					}
				}
			}
		}
	}


	private void checkCollisionY()
	{
		if (Collision.Collisions(pushBlockHitBox) != null)
		{
			collisionList.clear();
			collisionList = Collision.Collisions(pushBlockHitBox);
			for(Shape b : collisionList)
			{
				if (b.getMaxY() == b.getMinY())
				{
					if(b.getCenterY() < pushBlockHitBox.getCenterY())
					{
						if(!Game.getP().getgSwitch())
						{
							vY = 0;
						}
						if(Game.getP().getgSwitch())
						{
							vY = 0;
						}
						y = b.getCenterY();
					}	
					else if(b.getCenterY() > pushBlockHitBox.getCenterY())
					{
						vY = 0;
						y = b.getCenterY() - height;
					}
				}
			}
		}
	}

	public void render(Graphics g) throws SlickException
	{
		//render your box image here
		if(pushBlockHitBox != null)
		{
			ImageLoader.returnImages("ghostBlock").draw(x,y,width,height);
			g.setColor(Color.white);
			g.draw(pushBlockHitBox);
		}
	}

	public void applyCollision(GameObject o) 
	{

	}
	public static float getX() {
		return x;
	}

	public void setX(float x) {
		pushBlock.x = x;
	}

	public static float getY() {
		return y;
	}

	public void setY(float y) {
		pushBlock.y = y;
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

	public float getvY() {
		return vY;
	}

	public void setvY(float vY) {
		this.vY = vY;
	}

	public Shape getBoxShape() {
		return pushBlockHitBox;
	}

	public void setBoxShape(Shape pushBlockHitBox) {
		this.pushBlockHitBox = pushBlockHitBox;
	}

	public static ArrayList<pushBlock> getPushBlocks() {
		return pushBlocks;
	}

	public static void setPushBlocks(ArrayList<pushBlock> pushBlocks) {
		pushBlock.pushBlocks = pushBlocks;
	}

}
