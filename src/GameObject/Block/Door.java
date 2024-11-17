package GameObject.Block;

import Media.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;

public class Door extends Block {
	private int x, y, width, height;
	private static Shape doorHitBox;
	private static ArrayList<Door> doors = new ArrayList<Door>();

	private Door(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		doorHitBox = new Rectangle(x, y, width, height);
	}

	public void update() {
		if (doorHitBox != null) {
			doorHitBox.setLocation(x, y);
		}
	}

	public void render(Graphics g) throws SlickException {
		ImageLoader.returnImages("door").draw(x, y, width, height);
		g.setColor(Color.transparent);
		g.draw(doorHitBox);
	}

	public static void createDoor(int x, int y, int width, int height) {
		doors.add(new Door(x, y, width, height));
	}

	public static void clearDoors() {
		doors.clear();
	}

	public static Shape getDoorHitBox() {
		return doorHitBox;
	}
}
