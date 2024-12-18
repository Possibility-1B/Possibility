package Menus;

import Media.*;
import core.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Intro extends BasicGameState {
	private int id, timer;
	private StateBasedGame sbg;

	public Intro(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		ImageLoader.loadImages();
		AnimationLoader.loadAnimations();
		Main.getGameContainer().setMouseCursor(ImageLoader.getCursor(), 1, 1);

		this.sbg = sbg;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer++;
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		ImageLoader.getLogoImage();

		if (timer > 550) {
			sbg.enterState(Main.TITLE_ID);
		}
	}

	public void keyPressed(int key, char c) {
		sbg.enterState(Main.TITLE_ID);
	}

	public void mousePressed(int button, int x, int y) {
		sbg.enterState(Main.TITLE_ID);
	}
}