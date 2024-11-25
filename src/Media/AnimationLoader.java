package Media;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimationLoader {
	
	private static SpriteSheet playerRunningRight;
	private static Animation playerRunningRightAnimation;

	private static SpriteSheet playerRunningLeft;
	private static Animation playerRunningLeftAnimation;

	private static SpriteSheet playerUpsideDownRunningLeft;
	private static Animation playerUpsideDownRunningLeftAnimation;

	private static SpriteSheet playerUpsideDownRunningRight;
	private static Animation playerUpsideDownRunningRightAnimation;

	private static SpriteSheet portal;
	private static Animation portalDoor;

	private static SpriteSheet openPortal;
	private static Animation openedPortal;

	private static SpriteSheet key;
	private static Animation rotateKey;

	private static SpriteSheet door;
	private static Animation openDoor;

	private static SpriteSheet portalCredits;
	private static Animation credits;

	private static SpriteSheet gravityCooldown;
	private static Animation cooldown;

	public static void loadAnimations() throws SlickException {
		playerRunningRight = new SpriteSheet("res/animations/Player/playerRunningRight.png", 140,
				186);
		playerRunningRightAnimation = new Animation(playerRunningRight, 50);

		playerRunningLeft = new SpriteSheet("res/animations/Player/playerRunningLeft.png", 140,
				186);
		playerRunningLeftAnimation = new Animation(playerRunningLeft, 50);

		playerUpsideDownRunningLeft = new SpriteSheet(
				"res/animations/Player/playerUpsideDownRunningLeft.png", 140, 186);
		playerUpsideDownRunningLeftAnimation = new Animation(playerUpsideDownRunningLeft, 50);

		playerUpsideDownRunningRight = new SpriteSheet(
				"res/animations/Player/playerUpsideDownRunningRight.png", 140, 186);
		playerUpsideDownRunningRightAnimation = new Animation(playerUpsideDownRunningRight, 50);

		gravityCooldown = new SpriteSheet("res/animations/Player/cooldown.png", 128, 128);
		cooldown = new Animation(gravityCooldown, 116);

		portalCredits = new SpriteSheet("res/animations/levelSelect/portalCredits.png", 320, 320);
		credits = new Animation(portalCredits, 40);

		portal = new SpriteSheet("res/animations/levelSelect/portal.png", 320, 320);
		portalDoor = new Animation(portal, 40);

		openPortal = new SpriteSheet("res/animations/levelSelect/openPortal.png", 500, 500);
		openedPortal = new Animation(openPortal, 15);

		key = new SpriteSheet("res/animations/Puzzle/key.png", 180, 180);
		rotateKey = new Animation(key, 25);

		door = new SpriteSheet("res/animations/Puzzle/door.png", 600, 600);
		openDoor = new Animation(door, 100);
	}

	public static Animation returnAnimation(String s) throws SlickException {
		if (s.equalsIgnoreCase("playerMovingRight")) {
			return playerRunningRightAnimation;
		}

		if (s.equalsIgnoreCase("credits")) {
			return credits;
		}

		if (s.equalsIgnoreCase("cooldown")) {
			return cooldown;
		}

		if (s.equalsIgnoreCase("playerMovingLeft")) {
			return playerRunningLeftAnimation;
		}

		if (s.equalsIgnoreCase("playerMovingRightUpsideDown")) {
			return playerUpsideDownRunningRightAnimation;
		}

		if (s.equalsIgnoreCase("playerMovingLeftUpsideDown")) {
			return playerUpsideDownRunningLeftAnimation;
		}

		if (s.equalsIgnoreCase("portal")) {
			return portalDoor;
		}

		if (s.equalsIgnoreCase("openedPortal")) {
			return openedPortal;
		}

		if (s.equalsIgnoreCase("key")) {
			return rotateKey;
		}

		if (s.equalsIgnoreCase("door")) {
			return openDoor;
		}

		return null;
	}
}
