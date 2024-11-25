package Levels;

import Media.*;
import core.*;
import core.Game;
import GameObject.Block.*;
import GameObject.Entity.Hazard.Stationary.*;
import GameObject.Entity.Interactable.*;

import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class Level2 extends Level {
	private int doorTimer = 0, num1 = 5, num2 = 10;
	private int platformx = Main.getScreenWidth() / 2, platformy = Main.getScreenHeight() / 2;
	private int platformx1 = Main.getScreenWidth(), platformy1 = Main.getScreenHeight() / 2 - 50;
	private static boolean isDoorOpen = false, keyGrabbed = false, isCompleted = false;

	public static void reset() {
		isDoorOpen = false;
		keyGrabbed = false;
	}

	private void platformMove() {
		platformx += num1;

		if (platformx + 200 > Main.getScreenWidth() || platformx < 100) {
			num1 = num1 * (-1);
		}

		platformx1 += num2;

		if (platformx1 + 200 > Main.getScreenWidth() * 2 || platformx1 < Main.getScreenWidth()) {
			num2 = num2 * (-1);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		platformMove();
		TileManager.clearTiles();
		Border.clearBorders();
		SpikeManager.clearSpikes();
		SwitchManager.clearSwitches();
		IceManager.clearIce();
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
			AnimationLoader.returnAnimation("key").draw(platformx1, platformy1 - 90, 90, 90);
		}

		if (keyGrabbed) {
			if (doorTimer < 25) {
				AnimationLoader.returnAnimation("door").draw(platformx + 75, platformy - 190, 150,
						230);
			}

			if (doorTimer >= 25) {
				ImageLoader.returnImages("openDoor").draw(platformx + 75, platformy - 190, 150,
						230);
			}
		} else {
			ImageLoader.returnImages("door").draw(platformx + 40, platformy - 190, 150, 230);
		}

		Cooldown.draw(g);
	}

	public int getWidth() {
		return Main.getScreenWidth() * 3;
	}

	public static boolean getCompleted() {
		return isCompleted;
	}

	public static void setCompleted(boolean isCompleted) {
		Level2.isCompleted = isCompleted;
	}

	private void generateWorld() {
		wallsAroundMap();
		// Floors
		TileManager.createBorder(0, 980, getWidth(), 980);
		TileManager.createBorder(0, 100, getWidth(), 100);
		// moving platforms
		TileManager.createTile(platformx, platformy, 200, 30, "white");
		TileManager.createBorder(platformx, platformy + 30, platformx + 200, platformy + 30);
		TileManager.createBorder(platformx, platformy, platformx + 200, platformy);

		TileManager.createTile(platformx1, platformy1, 200, 100, "white");
		TileManager.createBorder(platformx1, platformy1, platformx1 + 200, platformy1);
		TileManager.createBorder(platformx1, platformy1 + 100, platformx1 + 200, platformy1 + 100);
		TileManager.createBorder(platformx1, platformy1 + 5, platformx1, platformy1 + 100 + 5);
		TileManager.createBorder(platformx1 + 200, platformy1 - 5, platformx1 + 200,
				platformy1 + 100 - 5);
		// Tiles
		TileManager.createTile(0, 980, getWidth(), 100, "white");
		TileManager.createTile(0, 0, getWidth(), 100, "white");
		// switches
		SwitchManager.createSwitch(1520, Main.getScreenHeight() - 200, 100, 100, true);
		SwitchManager.createSwitch(1520, 100, 100, 100, false);
		SwitchManager.createSwitch(3240, Main.getScreenHeight() - 200, 100, 100, true);
		SwitchManager.createSwitch(3240, 100, 100, 100, false);
		// Walls
		TileManager.createBorder(100, 0, 100, Main.getScreenHeight());
		TileManager.createBorder(5660, 0, 5660, Main.getScreenHeight());
		TileManager.createTile(0, 100, 100, Main.getScreenHeight(), "white");
		TileManager.createTile(5660, 100, 5660, Main.getScreenHeight(), "white");

		if (!keyGrabbed) {
			Key.createKey(platformx1, platformy1 - 90, 90, 90);
		}
		Door.createDoor(platformx + 75, platformy - 190, 150, 230);
	}

	@Override
	public int getHeight() {
		return 0;
	}
}