package GameObject.Entity.Interactable;

import org.newdawn.slick.geom.*;

import java.util.*;

public class Key {
	private static Shape keyHitBox;
	private static ArrayList<Key> keys = new ArrayList<Key>();

	private Key(int x, int y, int width, int height) {
		keyHitBox = new Rectangle(x, y, width, height);
	}

	public static void createKey(int x, int y, int width, int height) {
		keys.add(new Key(x, y, width, height));
	}

	public static Shape getKeyHitBox() {
		return keyHitBox;
	}
}