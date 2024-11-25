package Media;

import core.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;
import java.util.Map;

public class ImageLoader {
	private static Image cursor;
	private static int timer = 0;
	private static int fadeInTimer = 0;
	private static int fadeOutTimer = 255;

	private static Image midgameLogo;
	private static Image midgameLogonoBGScaled;

	private static final Map<String, Image> imageMap = new HashMap<>();

	public static void loadImages() throws SlickException {
		midgameLogo = new Image("res/images/GUI/Midgame1.png");
		midgameLogonoBGScaled = midgameLogo.getScaledCopy(Main.getScreenWidth(),
				Main.getScreenHeight());

		cursor = new Image("res/images/GUI/cursor.png");

		imageMap.put("gravityUp", new Image("res/images/Arrows/leverUp.png"));
		imageMap.put("cooldown", new Image("res/images/GUI/readyCooldown.png"));
		imageMap.put("gravityDown", new Image("res/images/Arrows/leverDown.png"));
		imageMap.put("gravityRight", new Image("res/images/Arrows/leverRight.png"));
		imageMap.put("gravityLeft", new Image("res/images/Arrows/leverLeft.png"));
		imageMap.put("AFKLeft", new Image("res/images/Player/AFKLeft.png"));
		imageMap.put("AFKRight", new Image("res/images/Player/AFKRight.png"));
		imageMap.put("AFKUpsideDownLeft", new Image("res/images/Player/AFKUpsideDownLeft.png"));
		imageMap.put("AFKUpsideDownRight", new Image("res/images/Player/AFKUpsideDownRight.png"));
		imageMap.put("glow", new Image("res/images/white.png"));
		imageMap.put("door", new Image("res/images/Puzzle/door.png"));
		imageMap.put("spike", new Image("res/images/Hazards/spike.png"));
		imageMap.put("lvl1", new Image("res/images/levelNumbers/One.png"));
		imageMap.put("lvl2", new Image("res/images/levelNumbers/Two.png"));
		imageMap.put("lvl3", new Image("res/images/levelNumbers/Three.png"));
		imageMap.put("lvl4", new Image("res/images/levelNumbers/Four.png"));
		imageMap.put("lvl5", new Image("res/images/levelNumbers/Five.png"));
		imageMap.put("lvl6", new Image("res/images/levelNumbers/Six.png"));
		imageMap.put("lvl7", new Image("res/images/levelNumbers/Seven.png"));
		imageMap.put("lvl8", new Image("res/images/levelNumbers/Eight.png"));
		imageMap.put("pressE", new Image("res/images/levelNumbers/pressE.png"));
		imageMap.put("E", new Image("res/images/GUI/E.png"));
		imageMap.put("lvlCredits", new Image("res/images/levelNumbers/lvlCredits.png"));
		imageMap.put("credits", new Image("res/images/Credits/Credits.png"));
		imageMap.put("rate", new Image("res/images/Credits/rate.png"));
		imageMap.put("thanks", new Image("res/images/Credits/thanks.png"));
		imageMap.put("thumbsup", new Image("res/images/Credits/thumbsup.png"));
		imageMap.put("openDoor", new Image("res/images/Puzzle/openDoor.png"));
		imageMap.put("welcome", new Image("res/images/GUI/welcome.png"));
		imageMap.put("youDied", new Image("res/images/GUI/youDied.png"));
		imageMap.put("ghostBlock", new Image("res/images/Puzzle/ghostBlock.png"));
		imageMap.put("downGhostBlock", new Image("res/images/Puzzle/downGhostBlock.png"));
		imageMap.put("ghostBlockText", new Image("res/images/Text/ghostBlockText.png"));
		imageMap.put("spike2", imageMap.get("spike").getFlippedCopy(false, true));
		imageMap.put("iceText2", new Image("res/images/Text/iceText2.png"));
		imageMap.put("newIce", new Image("res/images/Text/newIce.png"));
	}

	public static Image getCursor() {
		return cursor;
	}

	public static Image returnImages(String s) throws SlickException {
		return imageMap.getOrDefault(s, null);
	}

	public static void getLogoImage() {
		timer++;

		if (timer < 255) {
			fadeInTimer++;
		}

		if (timer > 300) {
			fadeOutTimer--;
		}

		if (timer <= 255) {
			midgameLogonoBGScaled.draw(-1, -1, new Color(255, 255, 255, fadeInTimer));
		} else if (timer > 300) {
			midgameLogonoBGScaled.draw(-1, -1, new Color(255, 255, 255, fadeOutTimer));
		} else {
			midgameLogonoBGScaled.draw(-1, -1);
		}
	}
}
