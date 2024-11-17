package Levels;

import Media.*;
import core.*;
import core.Game;
import GameObject.Block.*;
import GameObject.Entity.Hazard.Stationary.Spike;
import GameObject.Entity.Hazard.Stationary.SpikeManager;
import GameObject.Entity.Interactable.*;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class Level1 extends Level {
	private int doorTimer = 0;
	private static boolean isDoorOpen = false, keyGrabbed = false, isCompleted = false;
	private static int spikey1 = Main.getScreenHeight() - 150, spikey2 = 100, spikex1 = 950,
			spikex2 = 800;

	public static void reset() {
		spikey1 = Main.getScreenHeight() - 150;
		spikey2 = 100;
		keyGrabbed = false;
		isDoorOpen = false;

		Spike.reset();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		SpikeManager.createSpike(spikex1, spikey1, 50, 50, false);
		SpikeManager.createSpike(spikex2, spikey2, 50, 50, true);

		TileManager.clearTiles();
		Border.clearBorders();
		SpikeManager.clearSpikes();
		SwitchManager.clearSwitches();
		pushBlockManager.clearPushBlocks();

		if (keyGrabbed) {
			doorTimer++;
		}

		generateWorld();

		for (pushBlock pB : pushBlock.getPushBlocks()) {
			pB.update();
		}

		for (Switch s : SwitchManager.getSwitches()) {
			if (Game.getP().getxPos() > s.getX() && Game.getP().getxPos() < s.getX() + s.getWidth()
					&& Game.getP().getyPos() + Game.getP().getHeight() > s.getY()
					&& Game.getP().getyPos() < s.getY() + s.getHeight()) {
				Game.switchg();
			}
		}

		if (Game.getP().getPlayerShape().intersects(Door.getDoorHitBox()) && isDoorOpen) {
			isCompleted = true;
			Game.getP().setxPos(500);
			Game.getP().setyPos(Main.getScreenHeight() / 2);
			Game.getP().playerReset();
			Game.setCurLevel(Game.getLevelSelect());
		}

		if (Game.getP().getPlayerShape().intersects(Key.getKeyHitBox())) {
			keyGrabbed = true;
			isDoorOpen = true;
		}

		for (Spike s : SpikeManager.getSpikes()) {
			s.update();
			s.dropMovingRight(spikex2);
		}

		for (Spike s : SpikeManager.getSpikes()) {
			if (Game.getP().getxPos() > s.getX() && Game.getP().getxPos() < s.getX() + s.getWidth()
					&& Game.getP().getyPos() + Game.getP().getHeight() > s.getY()
					&& Game.getP().getyPos() < s.getY() + s.getHeight()) {
				Game.getP().die();
			}
		}

		if (Spike.fallen) {
			spikey2 += 25;
		}
	}

	public void render(Graphics g) throws SlickException {
		g.setColor(Color.white);

		for (Tile t : TileManager.getWhiteTiles()) {
			t.render(g);
		}

		g.setColor(Color.black);

		for (Tile t : TileManager.getBlackTiles()) {
			t.render(g);
		}

		for (Spike s : SpikeManager.getSpikes()) {
			s.render(g);
		}

		for (Switch s : SwitchManager.getSwitches()) {
			s.render(g);
		}

		if (!keyGrabbed) {
			AnimationLoader.returnAnimation("key").draw(5480, 100, 180, 180);
		}

		if (keyGrabbed) {
			if (doorTimer < 25) {
				AnimationLoader.returnAnimation("door").draw(5510, Main.getScreenHeight() - 290,
						150, 230);
			}

			if (doorTimer >= 25) {
				ImageLoader.returnImages("openDoor").draw((int) (5510),
						(int) (Main.getScreenHeight() - 290), 150, 230);
			}
		} else {
			ImageLoader.returnImages("door").draw((int) (5510),
					(int) (Main.getScreenHeight() - 290), 150, 230);
		}

		g.setColor(Color.white);
		Cooldown.draw(g);
	}

	public int getWidth() {
		return Main.getScreenWidth() * 3;
	}

	public static boolean getCompleted() {
		return isCompleted;
	}

	public static void setCompleted(boolean isCompleted) {
		Level1.isCompleted = isCompleted;
	}

	private void generateWorld() {
		wallsAroundMap();
		// Floors
		TileManager.createBorder(0, 980, getWidth(), 980);
		TileManager.createBorder(0, 100, getWidth(), 100);
		TileManager.createBorder(1500, Main.getScreenHeight() - 650, 1920,
				Main.getScreenHeight() - 650);
		TileManager.createBorder(3420, 650, 3840, 650);
		// Walls
		TileManager.createBorder(100, 0, 100, Main.getScreenHeight());
		TileManager.createBorder(5660, 0, 5660, Main.getScreenHeight());
		TileManager.createBorder(1500, Main.getScreenHeight() - 630, 1500, Main.getScreenHeight());
		TileManager.createBorder(1920, Main.getScreenHeight() - 630, 1920, Main.getScreenHeight());
		TileManager.createBorder(3420, 650, 3420, 0);
		TileManager.createBorder(3840, 650, 3840, 0);
		// Tiles
		TileManager.createTile(0, 980, getWidth(), 100, "white");
		TileManager.createTile(0, 0, getWidth(), 100, "white");
		TileManager.createTile(0, 100, 100, Main.getScreenHeight(), "white");
		TileManager.createTile(1500, Main.getScreenHeight() - 650, 420, Main.getScreenHeight(),
				"white");
		TileManager.createTile(3420, 0, 420, 650, "white");
		TileManager.createTile(5660, 100, 5660, Main.getScreenHeight(), "white");
		// switches
		SwitchManager.createSwitch(3300, 100, 100, 100, false);
		SwitchManager.createSwitch(1400, Main.getScreenHeight() - 200, 100, 100, true);
		SwitchManager.createSwitch(5200, Main.getScreenHeight() - 200, 100, 100, true);
		SwitchManager.createSwitch(5200, 100, 100, 100, false);

		// spike
		SpikeManager.createSpike(spikex1, spikey1, 50, 50, false);
		SpikeManager.createSpike(spikex2, spikey2, 50, 50, true);

		if (!keyGrabbed) {
			Key.createKey(5480, 100, 180, 84);
		}

		Door.createDoor(5510, Main.getScreenHeight() - 290, 150, 230);
	}

	@Override
	public int getHeight() {
		return 0;
	}
}