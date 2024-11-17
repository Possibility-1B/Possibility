package Media;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class AnimationLoader {
	private static final Map<String, Animation> animationMap = new HashMap<>();

	public static void loadAnimations() throws SlickException {
		animationMap.put("playerMovingRight",
				makeAnimation("animations/Player/playerRunningRight.png", 140, 186, 50));
		animationMap.put("credits",
				makeAnimation("animations/levelSelect/portalCredits.png", 320, 320, 40));
		animationMap.put("cooldown",
				makeAnimation("animations/Player/cooldown.png", 128, 128, 116));
		animationMap.put("playerMovingLeft",
				makeAnimation("animations/Player/playerRunningLeft.png", 140, 186, 50));
		animationMap.put("playerMovingRightUpsideDown", makeAnimation(
				"animations/Player/playerUpsideDownRunningRight.png", 140, 186, 50));
		animationMap.put("playerMovingLeftUpsideDown", makeAnimation(
				"animations/Player/playerUpsideDownRunningLeft.png", 140, 186, 50));
		animationMap.put("portal",
				makeAnimation("animations/levelSelect/portal.png", 320, 320, 40));
		animationMap.put("openedPortal",
				makeAnimation("animations/levelSelect/openPortal.png", 500, 500, 15));
		animationMap.put("key", makeAnimation("animations/Puzzle/key.png", 180, 180, 25));
		animationMap.put("door", makeAnimation("animations/Puzzle/door.png", 600, 600, 100));
	}

	private static Animation makeAnimation(String path, int tw, int th, int d) throws SlickException {
		SpriteSheet ss = new SpriteSheet(path,AnimationLoader.class.getClassLoader().getResourceAsStream(path), tw, th);
		return new Animation(ss,d);
	}

	public static Animation returnAnimation(String key) throws SlickException {
		return animationMap.getOrDefault(key, null);
	}
}
