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

		midgameLogo = makeImage("images/GUI/Midgame1.png");
		midgameLogonoBGScaled = midgameLogo.getScaledCopy(Main.getScreenWidth(),
				Main.getScreenHeight());

		cursor = makeImage("images/GUI/cursor.png");

		imageMap.put("gravityUp", makeImage("images/Arrows/leverUp.png"));
		imageMap.put("cooldown", makeImage("images/GUI/readyCooldown.png"));
		imageMap.put("gravityDown", makeImage("images/Arrows/leverDown.png"));
		imageMap.put("gravityRight", makeImage("images/Arrows/leverRight.png"));
		imageMap.put("gravityLeft", makeImage("images/Arrows/leverLeft.png"));
		imageMap.put("AFKLeft", makeImage("images/Player/AFKLeft.png"));
		imageMap.put("AFKRight", makeImage("images/Player/AFKRight.png"));
		imageMap.put("AFKUpsideDownLeft", makeImage("images/Player/AFKUpsideDownLeft.png"));
		imageMap.put("AFKUpsideDownRight", makeImage("images/Player/AFKUpsideDownRight.png"));
		imageMap.put("glow", makeImage("images/white.png"));
		imageMap.put("door", makeImage("images/Puzzle/door.png"));
		imageMap.put("spike", makeImage("images/Hazards/spike.png"));
		imageMap.put("lvl1", makeImage("images/levelNumbers/One.png"));
		imageMap.put("lvl2", makeImage("images/levelNumbers/Two.png"));
		imageMap.put("lvl3", makeImage("images/levelNumbers/Three.png"));
		imageMap.put("lvl4", makeImage("images/levelNumbers/Four.png"));
		imageMap.put("lvl5", makeImage("images/levelNumbers/Five.png"));
		imageMap.put("lvl6", makeImage("images/levelNumbers/Six.png"));
		imageMap.put("lvl7", makeImage("images/levelNumbers/Seven.png"));
		imageMap.put("lvl8", makeImage("images/levelNumbers/Eight.png"));
		imageMap.put("pressE", makeImage("images/levelNumbers/pressE.png"));
		imageMap.put("E", makeImage("images/GUI/E.png"));
		imageMap.put("lvlCredits", makeImage("images/levelNumbers/lvlCredits.png"));
		imageMap.put("credits", makeImage("images/Credits/Credits.png"));
		imageMap.put("rate", makeImage("images/Credits/rate.png"));
		imageMap.put("thanks", makeImage("images/Credits/thanks.png"));
		imageMap.put("thumbsup", makeImage("images/Credits/thumbsup.png"));
		imageMap.put("openDoor", makeImage("images/Puzzle/openDoor.png"));
		imageMap.put("welcome", makeImage("images/GUI/welcome.png"));
		imageMap.put("youDied", makeImage("images/GUI/youDied.png"));
		imageMap.put("ghostBlock", makeImage("images/Puzzle/ghostBlock.png"));
		imageMap.put("downGhostBlock", makeImage("images/Puzzle/downGhostBlock.png"));
		imageMap.put("ghostBlockText", makeImage("images/Text/ghostBlockText.png"));
		imageMap.put("spike2", imageMap.get("spike").getFlippedCopy(false, true));
		imageMap.put("iceText2", makeImage("images/Text/iceText2.png"));
		imageMap.put("newIce", makeImage("images/Text/newIce.png"));
	}

	public static Image getCursor() {
		return cursor;
	}

	private static Image makeImage(String path) throws SlickException {
		return new Image(ImageLoader.class.getClassLoader().getResourceAsStream(path), path, false);
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
