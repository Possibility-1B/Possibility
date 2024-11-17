package GameObject.Entity.Hazard.Stationary;

import Media.*;
import core.*;
import core.Game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Spike{	
	private float x, y, width, height;
	private boolean flipped;
	
	public static boolean fallen = false, fall = false;
	
	protected Shape spikeShape;
		
	public Spike(float x, float y, float width, float height, boolean flipped){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.flipped = flipped;
				
		spikeShape = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public void update(){	
		checkCollisions();
		if(spikeShape != null){
			spikeShape.setLocation(x, y);
		}
	}
	
	public static void reset(){
		fall = fallen = false;
	}

	public void render(Graphics g) throws SlickException{
		if(spikeShape == null){
			return;
		}
		
		if(!flipped){
			ImageLoader.returnImages("spike2").draw(x, y, width, height);
		}else{
			ImageLoader.returnImages("spike").draw(x, y, width, height);
		}
		g.setColor(Color.transparent);
		g.fill(spikeShape);
	}
	
	public void checkCollisions(){
		if(Game.getP().getPlayerShape().intersects(spikeShape)){
			Game.getP().die();
		}
	}
	
	public void dropMovingRight(int spikeX){
		if(Game.getP().getxPos() + 25 > (spikeX + (width/2)) - 50){
			fallen = true;
		}
		
		if(y >= Main.getScreenHeight()){
			fallen = false;
		}
	}
	
	public Shape getSpike(){
		return spikeShape;
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
}