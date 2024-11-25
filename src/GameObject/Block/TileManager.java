package GameObject.Block;

import org.newdawn.slick.geom.*;

import java.util.*;

public class TileManager {
	private static ArrayList<Tile> whiteTiles = new ArrayList<Tile>();
	private static ArrayList<Tile> blackTiles = new ArrayList<Tile>();

	public static void clearTiles() {
		whiteTiles.clear();
		blackTiles.clear();
	}

	public static void createTile(float x, float y, float width, float height, String type) {
		if (type.equalsIgnoreCase("white")) {
			whiteTiles.add(new Tile(x, y, width, height, type));
		}
		if (type.equalsIgnoreCase("black")) {
			blackTiles.add(new Tile(x, y, width, height, type));
		}
	}

	public static void createBorder(float x, float y, float x2, float y2) {
		Border.addBorders(new Line(x, y, x2, y2));
	}

	public static ArrayList<Tile> getWhiteTiles() {
		return whiteTiles;
	}

	public static ArrayList<Tile> getBlackTiles() {
		return blackTiles;
	}
}