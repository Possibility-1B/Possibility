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
import GameObject.Entity.Hazard.Stationary.Spike;
import GameObject.Entity.Hazard.Stationary.SpikeManager;
import GameObject.Entity.Interactable.Key;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level7 extends Level {
	private int doorTimer = 0;
	private static boolean isDoorOpen = false, keyGrabbed = false, isCompleted = false;
	private static float pushx = 200, pushy = Main.getScreenHeight() - 205; // push block

	public static void reset() {
		isDoorOpen = false;
		keyGrabbed = false;
		pushx = 200;
		pushy = Main.getScreenHeight() - 205;
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

		if (!keyGrabbed) {
			AnimationLoader.returnAnimation("key").draw(Main.getScreenWidth() * 2 + 100, 300, 180,
					180);
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

		for (Switch s : SwitchManager.getSwitches()) {
			s.render(g);
		}

		for (pushBlock pB : pushBlockManager.getPushBlocks()) {
			pB.render(g);
		}
		Cooldown.draw(g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		TileManager.clearTiles();
		Border.clearBorders();
		SpikeManager.clearSpikes();
		SwitchManager.clearSwitches();
		pushBlockManager.clearPushBlocks();

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

	private void generateWorld() {
		wallsAroundMap();
		pushBlockManager.createPushBlock(pushx, pushy, 105, 105);
		pushBlockWalls();
		// Tiles
		TileManager.createTile(0, 0, Main.getScreenWidth() * 3, 100, "white");
		// switches
		SwitchManager.createSwitch(Main.getScreenWidth() + 100, Main.getScreenHeight() - 200, 100,
				100, true);
		SwitchManager.createSwitch(Main.getScreenWidth() + 100, 100, 100, 100, false);
		SwitchManager.createSwitch(Main.getScreenWidth() * 2 + 500, Main.getScreenHeight() - 200,
				100, 100, true);
		SwitchManager.createSwitch(Main.getScreenWidth() * 2 + 500, 100, 100, 100, false);
		SwitchManager.createSwitch(400, Main.getScreenHeight() - 200, 100, 100, true);
		SwitchManager.createSwitch(400, 100, 100, 100, false);

		for (int i = 800; i < 1920; i += 110) {
			SpikeManager.createSpike(i, Main.getScreenHeight() - 210, 110, 110, false);
			SpikeManager.createSpike(i, 100, 110, 110, true);
		}

		for (int i = 2420; i < 4150; i += 110) {
			SpikeManager.createSpike(i, Main.getScreenHeight() - 210, 110, 110, false);
			SpikeManager.createSpike(i, 100, 110, 110, true);
		}

		if (!keyGrabbed) {
			Key.createKey(Main.getScreenWidth() * 2 + 100, 300, 180, 84);
		}
		Door.createDoor(5510, Main.getScreenHeight() - 290, 150, 230);
	}

	public static void setPushx(float x) {
		Level7.pushx = x;
	}

	public static void setPushy(float y) {
		Level7.pushy = y;
	}

	public static boolean getCompleted() {
		return isCompleted;
	}

	public static void setCompleted(boolean isCompleted7) {
		Level7.isCompleted = isCompleted7;
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