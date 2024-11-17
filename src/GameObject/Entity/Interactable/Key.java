package GameObject.Entity.Interactable;

import Media.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;

public class Key {
	private int x, y, width, height;
	
	private static ArrayList<Key> keys = new ArrayList<Key>();
	private static Shape keyHitBox;
	
	public Key(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		keyHitBox = new Rectangle(x,y,width,height);
	}
	
	public void update(){
		if(keyHitBox != null){
			keyHitBox.setLocation(x, y);
		}
	}
	
	public void render(Graphics g) throws SlickException{
		ImageLoader.returnImages("key").draw(x,y,width,height);
		g.setColor(Color.transparent);
		g.draw(keyHitBox);
	}

	public static void createKey(int x, int y, int width, int height){
		keys.add(new Key(x,y,width,height));
	}

	public static Shape getKeyHitBox() {
		return keyHitBox;
	}
}
