package GameObject.Entity;

import Media.*;
import core.*;
import core.Game;
import GameObject.Block.*;
import GameObject.Entity.Hazard.Stationary.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;

public class Player extends Entity{
	//Movement (Up = jump) | p = press
	private static boolean movingUp = false, movingLeft = false,
			movingRight = false, pD, rD, pA, gSwitch;
	
	//Direction | r = release
	private boolean facingRight = false, facingLeft = false, rA;

	private static int CUR_HEALTH = 100;
	private static Shape playerShape;
	private static float xVelocity;

	//Resolution
	public int screenX = Main.getScreenWidth();
	public int screenY = Main.getScreenHeight();
	
	public static int width	= 50;
	public static float xPos,yPos, health;

	protected double gravity = 0.2;
	protected static double vY;
	
	protected static int height = 100;			
	
	ArrayList<Shape> collisionList = new ArrayList<Shape>();

	public Player(float xPos, float yPos) throws SlickException {
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		screenX = (int)size.getWidth();
		screenY = (int)size.getHeight();
		health = 100;
		Player.xPos = xPos;
		Player.yPos = yPos;
		
		pD = rD = pA = false;  
		playerShape = new Rectangle(xPos, yPos, width, height);
		gSwitch = false;
	}

	public void update(){
		checkCollisionY();
		checkCollisionX();
		movement();
		playerShape.setLocation(xPos, yPos);
	}
	
	public void playerReset(){
		if(Game.getCurLevel() != Game.getLevelSelect()){
            xPos = 100;
            yPos = 200;
        }
		else if(Game.getCurLevel() == Game.getLevel4()){
			xPos = 100;
			yPos = 200;
		}else{
            xPos = 400;
            yPos = Main.getScreenHeight()/2;
        }
        CUR_HEALTH = 100;
        movingRight = movingLeft = movingUp =  gSwitch = false;
        pD = rD = pA = pD = false;
        xVelocity = 0;
        vY = 0;
    }
	
	private void checkCollisionX(){
		if(Collision.Collisions(playerShape) == null){
			return;
		}
		collisionList.clear();
		collisionList = Collision.Collisions(playerShape);
		for(Shape b : collisionList){
			if(b.getMaxX() == b.getMinX()){
				if (b.getCenterX() > playerShape.getCenterX()){
					xVelocity = 0;
					xPos = b.getCenterX() - width - 1;
					pD = rD = pA = pD = false;
				}
				
				if(b.getCenterX() < playerShape.getCenterX()){
					xVelocity = 0;
					xPos = b.getCenterX() + 1;
					pD = rD = pA = pD = false;
				}
			}
		}
	}

	private void checkCollisionY(){
		if(Collision.Collisions(playerShape) == null){
			return;
		}
		collisionList.clear();
		collisionList = Collision.Collisions(playerShape);
		for(Shape b : collisionList){
			if(b.getMaxY() == b.getMinY()){
				if(b.getCenterY() < playerShape.getCenterY()){
					vY = 0;			
					yPos = b.getCenterY() + 1f;
				}	
				else if(b.getCenterY() > playerShape.getCenterY()){
					vY = 0;
					yPos = b.getCenterY() - height;
				}
			}
		}
	}
	
	public void movement(){
		xPos += xVelocity;
		if(pD && pA && !Ice.isSlide()){
			xVelocity = 0;
		}
		else if(pD && !rD && !Ice.isSlide()){
			xVelocity = 7;
		}
		else if(pA && !rA && !Ice.isSlide()) {
			xVelocity = -7;
		}
		else if(!pD && rD && xVelocity > 0){
			if(Ice.isSlide()){
				Helper.i = 0;
			}else{
				Helper.i = 1;
			}
			
			xVelocity += - Helper.i * .8f;
	
			if(xVelocity <= 0){
				xVelocity = 0;
			}
		}
		else if(!pA && rA && xVelocity < 0){
			if(Ice.slide){
				Helper.i = 0;
			}else{
				Helper.i = 1;
			}
			
			xVelocity += Helper.i * .8f;
			
			if(xVelocity >= 0){
				xVelocity = 0;
			}
		}
		if(movingUp){ //jumping
			collisionList.clear();
			collisionList = Collision.Collisions(playerShape);
			for(Shape b : collisionList){
				if(vY == 0 && playerShape.intersects(b)){
					if(!gSwitch){
						vY = -25;
					}else{
						vY = 25;
					}
					
					if(xVelocity < 0){
						Helper.i = 1;
						xVelocity += Helper.i * .8f;
						
						if(xVelocity >= 0){
							xVelocity = 0;
						}
					}
					
					if(xVelocity > 0){
						Helper.i = 1;
						xVelocity += - Helper.i * .8f;
						
						if(xVelocity <= 0){
							xVelocity =0;
						}
					}			
				}
			}
		}
		// if the player is above its default height, add gravity
		if(yPos > Main.getScreenHeight() - height){
			vY = 0;
			yPos += vY;
		}else{
			if(gSwitch){
				vY -= 1.51;
			}else{
				vY += 1.51;
			}
			yPos += vY;
		}
	}
	
	public void render(Graphics g) throws SlickException{
		if(facingRight && !facingLeft && movingRight && !movingLeft && !gSwitch){
			AnimationLoader.returnAnimation("playerMovingRight").draw(xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && !movingRight && movingLeft && !gSwitch){
			AnimationLoader.returnAnimation("playerMovingLeft").draw(xPos, yPos, width, height);
		}
		else if(facingRight && !facingLeft && !gSwitch && !movingRight && !movingLeft){
			ImageLoader.returnImages("AFKRight").draw(xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && !gSwitch && !movingRight && !movingLeft){
			ImageLoader.returnImages("AFKLeft").draw(xPos, yPos, width, height);
		}
		else if((!facingRight && !facingLeft && !gSwitch && !movingRight && !movingLeft) 
				|| (facingRight && facingLeft && !gSwitch && !movingRight && !movingLeft)){
			ImageLoader.returnImages("AFKRight").draw(xPos, yPos, width, height);
		}

		if(facingRight && !facingLeft && movingRight && !movingLeft && gSwitch){
			AnimationLoader.returnAnimation("playerMovingRightUpsideDown").draw(
					xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && !movingRight && movingLeft && gSwitch){
			AnimationLoader.returnAnimation("playerMovingLeftUpsideDown").draw(
					xPos, yPos, width, height);
		}
		else if(facingRight && !facingLeft && gSwitch && !movingRight && !movingLeft){
			ImageLoader.returnImages("AFKUpsideDownRight").draw(xPos, yPos, width, height);
		}
		else if(!facingRight && facingLeft && gSwitch && !movingRight && !movingLeft){
			ImageLoader.returnImages("AFKUpsideDownLeft").draw(xPos, yPos, width, height);
		}
		else if((!facingRight && !facingLeft && gSwitch && !movingRight && !movingLeft) 
				|| (facingRight && facingLeft && gSwitch && !movingRight && !movingLeft)){
			ImageLoader.returnImages("AFKUpsideDownRight").draw(xPos, yPos, width, height);
		}		
	}
	
	public void die(){
		playerReset();
		CUR_HEALTH = 0;
	}
	
	public void keyPressed(int key, char c){
		//code happens every time the user presses a key
		if(key == Input.KEY_SPACE || key == Input.KEY_W || key == Input.KEY_UP){
			setMovingUp(true);
		}
		
		if(key == Input.KEY_D || key == Input.KEY_RIGHT){
			setFacingLeft(false);
			setMovingLeft(false);
			setFacingRight(true);
			setMovingRight(true);
			pD = true;
			rD = false;
		}
		
		if(key == Input.KEY_A || key == Input.KEY_LEFT){
			setFacingRight(false);
			setMovingRight(false);
			setFacingLeft(true);
			setMovingLeft(true);
			pA = true;
			rA = false;
		}
	}

	public void keyReleased(int key, char c){
		if(key == Input.KEY_SPACE || key == Input.KEY_W || key == Input.KEY_UP){
			setMovingUp(false);
		}
		
		if(key == Input.KEY_D || key == Input.KEY_RIGHT){
			setMovingRight(false);
			pD = false;
			rD = true;
		}
		
		if(key == Input.KEY_A || key == Input.KEY_LEFT){
			setMovingLeft(false);
			pA = false;
			rA = true;
		}
	}

	public float getxPos(){
		return xPos;
	}
	
	public void setxPos(float xPos){
		Player.xPos = xPos;
	}
	
	public float getyPos(){
		return yPos;
	}
	
	public void setyPos(float yPos){
		Player.yPos = yPos;
	}
	
	public void setMovingUp(boolean movingUp){
		Player.movingUp = movingUp;
	}
	
	public void setMovingLeft(boolean movingLeft){
		Player.movingLeft = movingLeft;
	}
	
	public void setMovingRight(boolean movingRight){
		Player.movingRight = movingRight;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Shape getPlayerShape(){
		return playerShape;
	}

	public void setFacingRight(boolean facingRight){
		this.facingRight = facingRight;
	}

	public void setFacingLeft(boolean facingLeft){
		this.facingLeft = facingLeft;
	}

	public int getCUR_HEALTH(){
		return CUR_HEALTH;
	}

	public float getxVelocity(){
		return xVelocity;
	}

	public boolean getgSwitch(){
		return gSwitch;
	}

	public void setgSwitch(boolean gSwitch) {
		Player.gSwitch = gSwitch;
	}
}
	