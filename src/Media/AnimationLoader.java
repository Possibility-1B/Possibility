package Media;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimationLoader {
	private static final Map<String, Animation> animationMap = new HashMap<>();

	public static void loadAnimations() throws SlickException {
		animationMap.put("playerMovingRight", new Animation(
				new SpriteSheet("res/animations/Player/playerRunningRight.png", 140, 186), 50));
		animationMap.put("credits", new Animation(
				new SpriteSheet("res/animations/levelSelect/portalCredits.png", 320, 320), 40));
		animationMap.put("cooldown", new Animation(
				new SpriteSheet("res/animations/Player/cooldown.png", 128, 128), 116));
		animationMap.put("playerMovingLeft", new Animation(
				new SpriteSheet("res/animations/Player/playerRunningLeft.png", 140, 186), 50));
		animationMap.put("playerMovingRightUpsideDown", new Animation(
				new SpriteSheet("res/animations/Player/playerUpsideDownRunningRight.png", 140, 186),
				50));
		animationMap.put("playerMovingLeftUpsideDown", new Animation(
				new SpriteSheet("res/animations/Player/playerUpsideDownRunningLeft.png", 140, 186),
				50));
		animationMap.put("portal", new Animation(
				new SpriteSheet("res/animations/levelSelect/portal.png", 320, 320), 40));
		animationMap.put("openedPortal", new Animation(
				new SpriteSheet("res/animations/levelSelect/openPortal.png", 500, 500), 15));
		animationMap.put("key",
				new Animation(new SpriteSheet("res/animations/Puzzle/key.png", 180, 180), 25));
		animationMap.put("door",
				new Animation(new SpriteSheet("res/animations/Puzzle/door.png", 600, 600), 100));
	}

	public static Animation returnAnimation(String key) throws SlickException {
		return animationMap.getOrDefault(key, null);
	}
}
