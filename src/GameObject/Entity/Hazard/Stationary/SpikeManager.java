package GameObject.Entity.Hazard.Stationary;

import java.util.*;

public class SpikeManager {
	private static ArrayList<Spike> spikes = new ArrayList<Spike>();

	public static void clearSpikes() {
		spikes.clear();
	}

	public static void createSpike(float x, float y, float width, float height, boolean flipped) {
		spikes.add(new Spike(x, y, width, height, flipped));
	}

	public static ArrayList<Spike> getSpikes() {
		return spikes;
	}
}