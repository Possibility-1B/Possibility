package GameObject;

import org.newdawn.slick.*;

public abstract class GameObject {

	abstract public void update();

	abstract public void render(Graphics g) throws SlickException;
}
