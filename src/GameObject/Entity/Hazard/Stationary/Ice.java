package GameObject.Entity.Hazard.Stationary;

import core.*;
import core.Game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Ice {
	public static boolean slide = false;

	private float icex, icey, width, height;
	private Shape iceShape;

	Ice(float x, float y, float width, float height) {
		this.icex = x;
		this.icey = y;
		this.width = width;
		this.height = height;
		iceShape = new Rectangle(this.icex, this.icey, this.width, this.height);
	}

	public static void reset() {
		for (Ice I : IceManager.getIce()) {
			I.setSlide(false);
		}
	}

	public void update() {
		slide();
		movement();
	}

	public void render(Graphics g) throws SlickException {
		if (iceShape != null) {
			g.setColor(new Color(113, 208, 227));
			g.fill(iceShape);
		}
	}

	private void movement() {
		if (slide) {
			Helper.i = 0;
		}
	}

	private void slide() {
		if ((Game.getP().getxPos() >= icex && Game.getP().getxPos() <= (icex + width))
				&& (Game.getP().getyPos() > icey - Game.getP().getHeight() - 1)) {
			slide = true;
		} else if ((Game.getP().getxPos() >= icex && Game.getP().getxPos() <= (icex + width))
				&& (Game.getP().getyPos() < icey + Game.getP().getHeight() + 1)) {
			slide = true;
		} else {
			slide = false;
		}
	}

	public static boolean isSlide() {
		return slide;
	}

	public void setSlide(boolean slide) {
		Ice.slide = slide;
	}
}
