package Levels;

import Media.*;
import core.*;
import core.Game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Credits extends Level {
	private int timer = 0, scrollTimer = 0;

	public void render(Graphics g) throws SlickException {
		g.setColor(new Color(197, 197, 197));
		ImageLoader.returnImages("credits").draw(0, -scrollTimer);

		if (timer >= 3240) {
			ImageLoader.returnImages("thanks").draw(150, Main.getScreenHeight() / 2 - 300);
			ImageLoader.returnImages("rate").draw(400, Main.getScreenHeight() / 2 - 100);
			ImageLoader.returnImages("thumbsup").draw(Main.getScreenWidth() / 2 - 40,
					Main.getScreenHeight() / 2 + 100);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer += 2;

		if (timer > 300 && scrollTimer < 3240) {
			scrollTimer += 2;
		}

		if (timer > 4200) {
			Game.setCurLevel(Game.getLevelSelect());
			scrollTimer = 0;
			timer = 0;
		}
	}

	@Override
	public int getWidth() {
		return 0;
	}

	@Override
	public int getHeight() {
		return 0;
	}
}