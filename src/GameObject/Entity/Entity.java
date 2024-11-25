package GameObject.Entity;

import GameObject.*;

import org.newdawn.slick.*;

abstract class Entity extends GameObject {

	public abstract void update();

	public abstract void render(Graphics g) throws SlickException;
}
