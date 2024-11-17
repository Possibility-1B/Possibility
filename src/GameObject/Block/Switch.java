package GameObject.Block;

import Media.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;


public class Switch extends Block{
	private float x, y, width, height;
	private boolean up;
	private static ArrayList<Switch> Switches = new ArrayList<Switch>();
	private static Shape SwitchHitBox;
	
	public Switch(float x, float y, float width, float height, boolean up){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.up = up;
		SwitchHitBox = new Rectangle(x,y,width,height);
	}
	
	public void render(Graphics g) throws SlickException{
		if(SwitchHitBox == null) {
			return;
		}
		if(up){
			ImageLoader.returnImages("gravityUp").draw(x,y,width,height);
		}else{
			ImageLoader.returnImages("gravityDown").draw(x,y,width,height);
		}
		g.setColor(Color.transparent);
		g.draw(SwitchHitBox);	
	}

	public float getX(){
		return x;
	}

	public float getY(){
		return y;
	}

	public float getWidth(){
		return width;
	}

	public float getHeight(){
		return height;
	}

	public static ArrayList<Switch> getSwitches(){
		return Switches;
	}

	@Override
	public void update(){}	
}
