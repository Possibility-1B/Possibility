package GameObject.Block;

import java.util.*;

public class pushBlockManager {
	private static ArrayList<pushBlock> pushBlocks = new ArrayList<pushBlock>();

	public static void clearPushBlocks() {
		pushBlocks.clear();
	}

	public static void createPushBlock(float x, float y, float width, float height) {
		pushBlocks.add(new pushBlock(x, y, width, height));
	}

	public static ArrayList<pushBlock> getPushBlocks() {
		return pushBlocks;
	}
}