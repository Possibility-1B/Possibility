package Levels;

import Media.AnimationLoader;
import Media.ImageLoader;
import core.Cooldown;
import core.Game;
import core.Main;
import GameObject.Block.Border;
import GameObject.Block.Door;
import GameObject.Block.Switch;
import GameObject.Block.SwitchManager;
import GameObject.Block.Tile;
import GameObject.Block.TileManager;
import GameObject.Block.pushBlock;
import GameObject.Block.pushBlockManager;
import GameObject.Entity.Hazard.Stationary.Ice;
import GameObject.Entity.Hazard.Stationary.IceManager;
import GameObject.Entity.Hazard.Stationary.Spike;
import GameObject.Entity.Hazard.Stationary.SpikeManager;
import GameObject.Entity.Interactable.Key;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level4 extends Level {
	private int doorTimer = 0;
	private int keyX = getWidth() / 2 - 135, keyY = getHeight() - 400;
	private float pX1 = 450, pY1 = 850;
	private float pX2 = 1000, pY2 = 775;
	private float pX3 = 1550, pY3 = 700;
	private float pX4 = 2100, pY4 = 625;
	private float pX5 = pX1 + 2 * 1080 + 400, pY5 = getHeight() - pY1;
	private float pX6 = pX2 + 2 * 1080 + 420, pY6 = getHeight() - pY2;
	private float pX7 = pX3 + 2 * 1080 + 420, pY7 = getHeight() - pY3;
	private float pX8 = pX4 + 2 * 1080 + 420, pY8 = getHeight() - pY4;
	private float iceX8 = pX8, iceY8 = pY8;
	private float pWidth = 450, pHeight = 50;
	private static boolean isDoorOpen = false, keyGrabbed = false, isCompleted = false;

	public static void reset() {
		isDoorOpen = false;
		keyGrabbed = false;

		for (Ice I : IceManager.getIce()) {
			I.setSlide(false);
		}
	}

	public void render(Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.setColor(Color.black);

		for (Spike s : SpikeManager.getSpikes()) {
			s.render(g);
		}

		for (Tile t : TileManager.getWhiteTiles()) {
			t.render(g);
		}

		for (Tile t : TileManager.getBlackTiles()) {
			t.render(g);
		}

		for (Switch s : SwitchManager.getSwitches()) {
			s.render(g);
		}

		for (Ice I : IceManager.getIce()) {
			I.render(g);
		}

		if (!keyGrabbed) {
			AnimationLoader.returnAnimation("key").draw(keyX, keyY, 180, 180);
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
		Cooldown.draw(g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		TileManager.clearTiles();
		Border.clearBorders();
		SwitchManager.clearSwitches();
		SpikeManager.clearSpikes();
		IceManager.clearIce();

		for (Ice I : IceManager.getIce()) {
			I.update();
		}

		if (keyGrabbed) {
			doorTimer++;
		}

		generateWorld();

		for (pushBlock pB : pushBlockManager.getPushBlocks()) {
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
			Game.getP().playerReset();
			Game.setCurLevel(Game.getLevelSelect());
			Game.getP().setxPos(500);
			Game.getP().setyPos(Main.getScreenHeight() / 2);
		}

		if (Game.getP().getPlayerShape().intersects(Key.getKeyHitBox())) {
			keyGrabbed = true;
			isDoorOpen = true;
		}

		for (Spike s : SpikeManager.getSpikes()) {
			s.update();
		}

		for (Spike s : SpikeManager.getSpikes()) {
			if (Game.getP().getPlayerShape().intersects(s.getSpike())) {
				Game.getP().die();
			}
		}
	}

	public int getWidth() {
		return Main.getScreenWidth() * 3;
	}

	public int getHeight() {
		return Main.getScreenHeight();
	}

	private void generateWorld() {
		// Floors
		TileManager.createBorder(0, 980, Main.getScreenWidth() * 3, 980);
		TileManager.createBorder(0, 100, Main.getScreenWidth() * 3, 100);
		// Walls
		TileManager.createBorder(100, 0, 100, Main.getScreenHeight());
		TileManager.createBorder(5660, 0, 5660, Main.getScreenHeight());
		wallsAroundMap();
		// Tiles
		TileManager.createTile(0, 980, Main.getScreenWidth() * 3, 100, "white");
		TileManager.createTile(0, 0, Main.getScreenWidth() * 3, 100, "white");
		TileManager.createTile(0, 100, 100, Main.getScreenHeight(), "white");
		TileManager.createTile(5660, 100, 5660, Main.getScreenHeight(), "white");
		// platforms
		TileManager.createTile(pX1, pY1, pWidth, pHeight, "white");
		TileManager.createTile(pX2, pY2, pWidth, pHeight, "white");
		TileManager.createTile(pX3, pY3, pWidth, pHeight, "white");
		TileManager.createTile(pX4, pY4, pWidth, pHeight, "white");
		TileManager.createTile(pX5, pY5, pWidth, pHeight, "white");
		TileManager.createTile(pX6, pY6, pWidth, pHeight, "white");
		TileManager.createTile(pX7, pY7, pWidth, pHeight, "white");
		TileManager.createTile(pX8, pY8, pWidth, pHeight, "white");
		// borders on platforms
		TileManager.createBorder(pX1, pY1, pX1 + pWidth, pY1);
		TileManager.createBorder(pX2, pY2, pX2 + pWidth, pY2);
		TileManager.createBorder(pX3, pY3, pX3 + pWidth, pY3);
		TileManager.createBorder(pX4, pY4, pX4 + pWidth, pY4);
		TileManager.createBorder(pX1, pY1 + pHeight, pX1 + pWidth, pY1 + pHeight);
		TileManager.createBorder(pX2, pY2 + pHeight, pX2 + pWidth, pY2 + pHeight);
		TileManager.createBorder(pX3, pY3 + pHeight, pX3 + pWidth, pY3 + pHeight);
		TileManager.createBorder(pX4, pY4 + pHeight, pX4 + pWidth, pY4 + pHeight);
		TileManager.createBorder(pX1, pY1 + 3, pX1, pY1 + pHeight - 3);
		TileManager.createBorder(pX2, pY2 + 3, pX2, pY2 + pHeight - 3);
		TileManager.createBorder(pX3, pY3 + 3, pX3, pY3 + pHeight - 3);
		TileManager.createBorder(pX4, pY4 + 3, pX4, pY4 + pHeight - 3);
		TileManager.createBorder(pX1 + pWidth, pY1 + 3, pX1 + pWidth, pY1 + pHeight - 3);
		TileManager.createBorder(pX2 + pWidth, pY2 + 3, pX2 + pWidth, pY2 + pHeight - 3);
		TileManager.createBorder(pX3 + pWidth, pY3 + 3, pX3 + pWidth, pY3 + pHeight - 3);
		TileManager.createBorder(pX4 + pWidth, pY4 + 3, pX4 + pWidth, pY4 + pHeight - 3);
		TileManager.createBorder(pX5, pY5, pX5 + pWidth, pY5);
		TileManager.createBorder(pX6, pY6, pX6 + pWidth, pY6);
		TileManager.createBorder(pX7, pY7, pX7 + pWidth, pY7);
		TileManager.createBorder(pX8, pY8, pX8 + pWidth, pY8);
		TileManager.createBorder(pX5, pY5 + pHeight, pX5 + pWidth, pY5 + pHeight);
		TileManager.createBorder(pX6, pY6 + pHeight, pX6 + pWidth, pY6 + pHeight);
		TileManager.createBorder(pX7, pY7 + pHeight, pX7 + pWidth, pY7 + pHeight);
		TileManager.createBorder(pX8, pY8 + pHeight, pX8 + pWidth, pY8 + pHeight);
		TileManager.createBorder(pX5, pY5 + 3, pX5, pY5 + pHeight - 3);
		TileManager.createBorder(pX6, pY6 + 3, pX6, pY6 + pHeight - 3);
		TileManager.createBorder(pX7, pY7 + 3, pX7, pY7 + pHeight - 3);
		TileManager.createBorder(pX8, pY8 + 3, pX8, pY8 + pHeight - 3);
		TileManager.createBorder(pX5 + pWidth, pY5 + 3, pX5 + pWidth, pY5 + pHeight - 3);
		TileManager.createBorder(pX6 + pWidth, pY6 + 3, pX6 + pWidth, pY6 + pHeight - 3);
		TileManager.createBorder(pX7 + pWidth, pY7 + 3, pX7 + pWidth, pY7 + pHeight - 3);
		TileManager.createBorder(pX8 + pWidth, pY8 + 3, pX8 + pWidth, pY8 + pHeight - 3);
		// iceS
		IceManager.createIce(iceX8, iceY8 + pHeight / 2, pWidth, pHeight / 2);
		// other switches
		SwitchManager.createSwitch(pX8 + pWidth + 125, pY8 + 50, 100, 100, false);
		// middle (switches, borders, tiles, key)
		SwitchManager.createSwitch(getWidth() / 2 - 100, getHeight() - 250, 100, 100, true);

		if (!keyGrabbed) {
			Key.createKey(keyX, keyY, 180, 84);
		}

		TileManager.createTile(getWidth() / 2 - 180, getHeight() / 2, 40, getHeight(), "white");
		TileManager.createTile(getWidth() / 2 + 50, getHeight() / 2, 40, getHeight(), "white");
		TileManager.createBorder(getWidth() / 2 - 180, getHeight() / 2, getWidth() / 2 - 140,
				getHeight() / 2);
		TileManager.createBorder(getWidth() / 2 + 50, getHeight() / 2, getWidth() / 2 + 90,
				getHeight() / 2);
		TileManager.createBorder(getWidth() / 2 - 180, getHeight() / 2 + 30, getWidth() / 2 - 180,
				getHeight());
		TileManager.createBorder(getWidth() / 2 - 140, getHeight() / 2 + 30, getWidth() / 2 - 140,
				getHeight());
		TileManager.createBorder(getWidth() / 2 + 50, getHeight() / 2 + 30, getWidth() / 2 + 50,
				getHeight());
		TileManager.createBorder(getWidth() / 2 + 90, getHeight() / 2 + 30, getWidth() / 2 + 90,
				getHeight());
		// spikes (bottom & top)
		for (int i = 400; i < getWidth() / 2 - 180; i += 32) {
			SpikeManager.createSpike(i, getHeight() - 132, 32, 32, false);
		}

		for (int i = 100; i < getWidth() / 2 - 180; i += 32) {
			SpikeManager.createSpike(i, 100, 32, 32, true);
		}

		for (int i = getWidth() / 2 + 50; i < getWidth() - 200; i += 32) {
			SpikeManager.createSpike(i, getHeight() - 132, 32, 32, false);
		}

		for (int i = getWidth() / 2 + 80; i < getWidth(); i += 32) {
			SpikeManager.createSpike(i, 100, 32, 32, true);
		}
		// door
		Door.createDoor(5510, Main.getScreenHeight() - 290, 150, 230);
	}

	public static boolean getCompleted() {
		return isCompleted;
	}

	public static void setCompleted(boolean isCompleted) {
		Level4.isCompleted = isCompleted;
	}
}