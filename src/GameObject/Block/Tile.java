package GameObject.Block;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Tile extends Block {
	private String color;
	private Shape tileShape;
	private float x, y, width, height;

	Tile(float xS, float yS, float widthS, float HeightS, String color) {
		x = xS;
		y = yS;
		width = widthS;
		height = HeightS;

		this.color = color;
		tileShape = new Rectangle(x, y, width, height);
	}

	public void update() {
		if (tileShape != null && color.equalsIgnoreCase("white")) {
			tileShape.setLocation(this.x, this.y);
		}
		if (tileShape != null && color.equalsIgnoreCase("black")) {
			tileShape.setLocation(this.x, this.y);
		}
	}

	public void render(Graphics g) {
		if (tileShape != null && color.equalsIgnoreCase("white")) {
			g.setColor(Color.white);
			g.fill(tileShape);
		}
		if (tileShape != null && color.equalsIgnoreCase("black")) {
			g.setColor(Color.black);
			g.fill(tileShape);
		}
	}
}