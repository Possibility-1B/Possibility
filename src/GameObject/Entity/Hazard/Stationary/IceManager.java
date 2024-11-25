package GameObject.Entity.Hazard.Stationary;

import java.util.ArrayList;

public class IceManager {
	private static ArrayList<Ice> ice = new ArrayList<Ice>();

	public static void clearIce() {
		ice.clear();
	}

	public static void createIce(float x, float y, float width, float height) {
		ice.add(new Ice(x, y, width, height));
	}

	public static ArrayList<Ice> getIce() {
		return ice;
	}
}