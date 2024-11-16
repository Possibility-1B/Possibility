package GameObject.Entity;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

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

public class Player extends Entity
{
	
	//Movement (Up = jump)
	private static boolean movingUp = false;
	private static  boolean movingDown = false;
	private static boolean movingLeft = false;
	private static boolean movingRight = false;

	//Direction
	private boolean facingRight = false;
	private boolean facingLeft = false;
	



	protected double gravity = 0.2;
	protected static double vY;
	private static float xVelocity;
	
	public static int width	= 50;
	protected static int height = 100;
	
	public static float xPos;
	public static float yPos; 
	
	//keys  p = press, r = released
	private static boolean pD;
	private static boolean rD;
	
	private static boolean pA;
	private boolean rA;
	
	public static boolean gSwitch; 
	

	private float dx;
	
	private static int CUR_HEALTH = 100;
	private static Shape playerShape;
	
	
	ArrayList<Shape> collisionList = new ArrayList<Shape>();
	
	//Resolution
	public int screenX = Main.getScreenWidth();
	public int screenY = Main.getScreenHeight();
	public float health;


	public Player(float xPos, float yPos) throws SlickException 
	{
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		screenX = (int)size.getWidth();
		screenY = (int)size.getHeight();
		health = 100;
		Player.xPos = xPos;
		Player.yPos = yPos;
		pD = false;
		rD = false;
		
		pA = false;  
		pA = false;
		playerShape = new Rectangle(xPos, yPos, width, height);
		gSwitch = false;
	}


	public void update()
	{
		checkCollisionY();
		checkCollisionX();
		movement();
		playerShape.setLocation(xPos, yPos);
	}
	

	public void playerReset()
    {
		
		if(Game.getCurLevel() != Game.getLevelSelect())
        {
            xPos = 100;
            yPos = 200;
         

        }
		else if(Game.getCurLevel() == Game.getLevel4())
		{
			xPos = 100;
			yPos = 200;
		}
        else
        {
            xPos = 400;
            yPos = Main.getScreenHeight()/2;
        }

        CUR_HEALTH = 100;
        movingRight = false;
        movingLeft = false;
        movingUp = false;
        movingDown = false;
        xVelocity = 0;
        vY = 0;
        pD = false;
        rD = false;
        pA = false;
        pD = false;
        gSwitch = false;
    }
	
	private void checkCollisionX()
	{
		if (Collision.Collisions(playerShape) != null)
		{
			collisionList.clear();
			collisionList = Collision.Collisions(playerShape);
			for(Shape b : collisionList)
			{
				if (b.getMaxX() == b.getMinX())
				{
					if (b.getCenterX() > playerShape.getCenterX())
					{
						xVelocity = 0;
						xPos = b.getCenterX() - width - 1;
						pD = false;
						rD = false;
						pA = false;
						pD = false;
					}
					if (b.getCenterX() < playerShape.getCenterX())
					{
						xVelocity = 0;
						xPos = b.getCenterX() + 1;
						pD = false;
						rD = false;
						pA = false;
						pD = false;
					}
				}
			}
		}
	}

	
	private void checkCollisionY()
	{
		if (Collision.Collisions(playerShape) != null)
		{
			collisionList.clear();
			collisionList = Collision.Collisions(playerShape);
			for(Shape b : collisionList)
			{
				if (b.getMaxY() == b.getMinY())
				{
					if(b.getCenterY() < playerShape.getCenterY())
					{
						if(!gSwitch)
						{
							vY = 0;
						}
						if(gSwitch)
						{
							vY = 0;
						}
						yPos = b.getCenterY() + 1f;
					}	
					else if(b.getCenterY() > playerShape.getCenterY())
					{
						vY = 0;
						yPos = b.getCenterY() - height;
					}
				}
			}
		}
	}
	
	public void movement()
	{
		
		xPos += xVelocity;
			
		if(pD && pA && !Ice.isSlide())
		{
			xVelocity = 0;
		}
		
		else if(pD && !rD && !Ice.isSlide())
		{
			xVelocity = 7;
		}
		else if(pA && !rA && !Ice.isSlide()) 
		{
			xVelocity = -7;
		}
		else if(!pD && rD && xVelocity > 0)
		{
			if(Ice.isSlide())
			{
				Helper.i = 0;
			}
			else
			{
			Helper.i = 1;
			dx = Helper.i;
			}
			xVelocity += - Helper.i * .8f;
			
			if(xVelocity <= 0)
			{
				xVelocity = 0;
			}
		}
		else if(!pA && rA && xVelocity < 0)
		{
			if(Ice.slide)
			{
				Helper.i = 0;
			}
			else
			{
			Helper.i = 1;
			dx = Helper.i;
			}
			xVelocity += Helper.i * .8f;
			if(xVelocity >= 0)
			{
				xVelocity = 0;
			}
		}
		
		//jumping
		if(movingUp)
		{
			collisionList.clear();
			collisionList = Collision.Collisions(playerShape);
			for(Shape b : collisionList)
			{
				if(vY == 0 && playerShape.intersects(b))
				{
					if(!gSwitch)
					{
						vY = -25;
					}
					
					if(gSwitch)
					{
						vY = 25;
					}
					
					if( xVelocity < 0)
					{
						Helper.i = 1;
						dx = Helper.i;
						xVelocity += Helper.i * .8f;
						if(xVelocity >= 0)
						{
							xVelocity = 0;
						}
					}
					if(  xVelocity > 0)
					{
						Helper.i = 1;
						dx = Helper.i;
						xVelocity += - Helper.i * .8f;
						if(xVelocity <= 0)
						{
							xVelocity =0;
						}
					}
					
				}
			}
		}
		
		// if the player is above its default height, add gravity
		if(yPos > Main.getScreenHeight() - height)
		{
			vY = 0;
			yPos += vY;
		}
		else
		{
			if(gSwitch)
			{
				vY -= 1.51;
			}
			if(!gSwitch)
			{
				vY += 1.51;
			}

			yPos += vY;
		}
	}

	public void flip() 
    {
        if(yPos < Main.getScreenHeight()/2)
        {
            yPos = -1*yPos + Main.getScreenHeight()/2 + height;
        }
        else 
        {
            yPos = -1*yPos+ Main.getScreenHeight()/2 + height;
        }
    }
	
	public void render(Graphics g) throws SlickException
	{
		if(facingRight && !facingLeft && movingRight && !movingLeft && !gSwitch)
		{
			AnimationLoader.returnAnimation("playerMovingRight").draw(xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && !movingRight && movingLeft && !gSwitch)
		{
			AnimationLoader.returnAnimation("playerMovingLeft").draw(xPos, yPos, width, height);
		}
		else if(facingRight && !facingLeft && !gSwitch && !movingRight && !movingLeft)
		{
			ImageLoader.returnImages("AFKRight").draw(xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && !gSwitch && !movingRight && !movingLeft)
		{
			ImageLoader.returnImages("AFKLeft").draw(xPos, yPos, width, height);
		}
		else if((!facingRight && !facingLeft && !gSwitch && !movingRight && !movingLeft) || (facingRight && facingLeft && !gSwitch && !movingRight && !movingLeft))
		{
			ImageLoader.returnImages("AFKRight").draw(xPos, yPos, width, height);
		}
		
		
		if(facingRight && !facingLeft && movingRight && !movingLeft && gSwitch)
		{
			AnimationLoader.returnAnimation("playerMovingRightUpsideDown").draw(xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && !movingRight && movingLeft && gSwitch)
		{
			AnimationLoader.returnAnimation("playerMovingLeftUpsideDown").draw(xPos, yPos, width, height);
		}
		else if(facingRight && !facingLeft && gSwitch && !movingRight && !movingLeft)
		{
			ImageLoader.returnImages("AFKUpsideDownRight").draw(xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && gSwitch && !movingRight && !movingLeft)
		{
			ImageLoader.returnImages("AFKUpsideDownLeft").draw(xPos, yPos, width, height);
		}
		else if((!facingRight && !facingLeft && gSwitch && !movingRight && !movingLeft) || (facingRight && facingLeft && gSwitch && !movingRight && !movingLeft))
		{
			ImageLoader.returnImages("AFKUpsideDownRight").draw(xPos, yPos, width, height);
		}		
	}
	
	public void applyCollision(GameObject o)
	{

	}
	public void die()
	{
		playerReset();
		CUR_HEALTH = 0;
	}
	public void keyPressed(int key, char c)
	{
		// This code happens every time the user presses a key
		if(key == Input.KEY_SPACE || key == Input.KEY_W || key == Input.KEY_UP)
		{
			setMovingUp(true);
		}

		if(key == Input.KEY_D || key == Input.KEY_RIGHT)
		{
			setFacingLeft(false);
			setMovingLeft(false);
			setFacingRight(true);
			setMovingRight(true);
			pD = true;
			rD = false;
		}
		if(key == Input.KEY_A || key == Input.KEY_LEFT)
		{
			setFacingRight(false);
			setMovingRight(false);
			setFacingLeft(true);
			setMovingLeft(true);
			pA = true;
			rA = false;
		}
	}

	public void keyReleased(int key, char c)
	{
		if(key == Input.KEY_SPACE || key == Input.KEY_W || key == Input.KEY_UP)
		{
			setMovingUp(false);
		}
		if(key == Input.KEY_D || key == Input.KEY_RIGHT)
		{
			setMovingRight(false);
			pD = false;
			rD = true;
		}
		if(key == Input.KEY_A || key == Input.KEY_LEFT)
		{
			setMovingLeft(false);
			pA = false;
			rA = true;
		}
	}

	public float getxPos() {
		return xPos;
	}
	
	public void setxPos(float xPos) {
		Player.xPos = xPos;
	}
	
	public float getyPos() {
		return yPos;
	}
	
	public void setyPos(float f) {
		yPos = f;
	}
	
	public boolean isMovingUp() {
		return movingUp;
	}
	
	public void setMovingUp(boolean movingUp) {
		Player.movingUp = movingUp;
	}
	
	public  boolean isMovingDown() {
		return movingDown;
	}
	
	public  void setMovingDown(boolean movingDown) {
		Player.movingDown = movingDown;
	}
	
	public boolean isMovingLeft() {
		return movingLeft;
	}
	
	public void setMovingLeft(boolean movingLeft) {
		Player.movingLeft = movingLeft;
	}
	
	public boolean isMovingRight() {
		return movingRight;
	}
	
	public void setMovingRight(boolean movingRight) {
		Player.movingRight = movingRight;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		Player.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		Player.height = height;
	}
	
	public Shape getPlayerShape() {
		return playerShape;
	}
	
	public void setPlayerShape(Shape player) {
		Player.playerShape = player;
	}
	
	public double getvY() {
		return vY;
	}
	
	public void setvY(double vY) {
		Player.vY = vY;
	}
	
	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public boolean isFacingLeft() {
		return facingLeft;
	}

	public void setFacingLeft(boolean facingLeft) {
		this.facingLeft = facingLeft;
	}


	public int getCUR_HEALTH() {
		return CUR_HEALTH;
	}


	public void setCUR_HEALTH(int CUR_HEALTH) {
		Player.CUR_HEALTH = CUR_HEALTH;
	}


	public float getxVelocity() {
		return xVelocity;
	}


	public boolean getgSwitch() {
		return gSwitch;
	}


	public void setgSwitch(boolean gSwitch) {
		Player.gSwitch = gSwitch;
	}
	
	
}
	