package GameObject.Block;

import GameObject.*;

import org.newdawn.slick.*;

abstract class Block extends GameObject {

	public abstract void update();

	public abstract void render(Graphics g) throws SlickException;
}
