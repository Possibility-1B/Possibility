package core;

import Levels.*;
import GameObject.Entity.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import java.util.Timer;
import java.util.TimerTask;

public class Game extends BasicGameState {
	static int timer = 91;
	private static Player p;
	private static TimerTask task;

	private int id;
	private static int gravSwitcher;
	private static Level levelSelect, level1, level2, level3, level4, level5, level6, level7,
			level8, credits, curLevel = null;
	private StateBasedGame sbg;

	Game(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.sbg = sbg;

		gc.setShowFPS(true);
		if (curLevel != levelSelect) {
			p = new Player(100, 200);
		} else {
			p = new Player(400, Main.getScreenHeight() / 2);
		}

		levelSelect = new LevelSelector();
		level1 = new Level1();
		level2 = new Level2();
		level3 = new Level3();
		level4 = new Level4();
		level5 = new Level5();
		level6 = new Level6();
		level7 = new Level7();
		level8 = new Level8();
		credits = new Credits();

		Timer timer = new Timer();

		task = new Helper();
		timer.schedule(task, 200, 100);
	}

	// updates game's logic every frame. NO DRAWING.
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		timer++;
		curLevel.update(gc, sbg, delta);

		if (p.getCUR_HEALTH() == 0) {
			p.playerReset();

			if (curLevel == level1) {
				p.playerReset();
				Level1.reset();
			}
			if (curLevel == level2) {
				p.playerReset();
				Level2.reset();
			}
			if (curLevel == level3) {
				p.playerReset();
				Level3.reset();
			}
			if (curLevel == level4) {
				p.playerReset();
				Level4.reset();
			}
			if (curLevel == level5) {
				p.playerReset();
				Level5.reset();
			}
			if (curLevel == level6) {
				p.playerReset();
				Level6.reset();
			}
			if (curLevel == level7) {
				p.playerReset();
				Level7.reset();
			}
			if (curLevel == level8) {
				p.playerReset();
				Level8.reset();
			}
			sbg.enterState(Main.DEATH_ID);
		}
		p.update();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (curLevel != credits) {
			if (curLevel == levelSelect || curLevel == level3 || curLevel == level5) {
				if (p.getxPos() >= Main.getScreenWidth() * 2 - Main.getScreenWidth() / 2 - 100) {
					g.translate(-Main.getScreenWidth() * 2 + Main.getScreenWidth(), 0);
				} else if (p.getxPos() >= Main.getScreenWidth() / 2 - 100) {
					g.translate(-p.getxPos() + Main.getScreenWidth() / 2 - 100, 0);
				}
			} else {
				if (p.getxPos() >= Main.getScreenWidth() * 3 - Main.getScreenWidth() / 2 - 100) {
					g.translate(-Main.getScreenWidth() * 3 + Main.getScreenWidth(), 0);
				} else if (p.getxPos() >= Main.getScreenWidth() / 2 - 100) {
					g.translate(-p.getxPos() + Main.getScreenWidth() / 2 - 100, 0);
				}
			}
		}
		curLevel.render(g);
		p.render(g);
	}

	public void keyPressed(int key, char c) {
		p.keyPressed(key, c);
		if (key == Input.KEY_E) {
			checkLevelSelect();
		}

		if (key == Input.KEY_ESCAPE) {
			sbg.enterState(Main.TITLE_ID);
		}
	}

	public static void switchg() {
		if (timer > 90) {
			gravSwitcher++;
			timer = 0;
			if (gravSwitcher % 2 == 0) {
				p.setgSwitch(false);
			} else {
				p.setgSwitch(true);
			}
		}
	}

	private void checkLevelSelect() {
		if (curLevel == levelSelect) {
			if (p.getxPos() > LevelSelector.getLvl1().getMinX()
					&& p.getxPos() < LevelSelector.getLvl1().getMaxX()) {
				curLevel = level1;
				p.playerReset();
			}
			if (Level1.getCompleted() && p.getxPos() > LevelSelector.getLvl2().getMinX()
					&& p.getxPos() < LevelSelector.getLvl2().getMaxX()) {
				curLevel = level2;
				p.playerReset();
			}
			if (Level2.getCompleted() && p.getxPos() > LevelSelector.getLvl3().getMinX()
					&& p.getxPos() < LevelSelector.getLvl3().getMaxX()) {
				curLevel = level3;
				p.playerReset();
			}
			if (Level3.getCompleted() && p.getxPos() > LevelSelector.getLvl4().getMinX()
					&& p.getxPos() < LevelSelector.getLvl4().getMaxX()) {
				curLevel = level4;
				p.playerReset();
			}
			if (Level4.getCompleted() && p.getxPos() > LevelSelector.getLvl5().getMinX()
					&& p.getxPos() < LevelSelector.getLvl5().getMaxX()) {
				p.playerReset();
				curLevel = level5;
			}
			if (Level5.getCompleted() && p.getxPos() > LevelSelector.getLvl6().getMinX()
					&& p.getxPos() < LevelSelector.getLvl6().getMaxX()) {
				curLevel = level6;
				p.playerReset();
			}
			if (Level6.getCompleted() && p.getxPos() > LevelSelector.getLvl7().getMinX()
					&& p.getxPos() < LevelSelector.getLvl7().getMaxX()) {
				curLevel = level7;
				p.playerReset();
			}
			if (Level7.getCompleted() && p.getxPos() > LevelSelector.getLvl8().getMinX()
					&& p.getxPos() < LevelSelector.getLvl8().getMaxX()) {
				curLevel = level8;
				p.playerReset();
			}
			if (p.getxPos() > LevelSelector.getCredits().getMinX()
					&& p.getxPos() < LevelSelector.getCredits().getMaxX()) {
				curLevel = credits;
			}
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		p.keyReleased(key, c);
	}

	public static Level getLevel1() {
		return level1;
	}

	public static Level getCurLevel() {
		return curLevel;
	}

	public static void setCurLevel(Level curLevel) {
		Game.curLevel = curLevel;
	}

	public static Level getLevelSelect() {
		return levelSelect;
	}

	public static Player getP() {
		return p;
	}

	public static Level getLevel2() {
		return level2;
	}

	public static Level getLevel3() {
		return level3;
	}

	public static Level getLevel4() {
		return level4;
	}

	public static Level getLevel5() {
		return level5;
	}

	public static Level getLevel6() {
		return level6;
	}

	public static Level getLevel7() {
		return level7;
	}

	public static Level getLevel8() {
		return level8;
	}
}
