package GameObject.Block;

import Media.*;
import core.Game;
import Levels.*;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

import java.util.*;

public class pushBlock extends Block {
	private float width, height, vY = 0;
	private Shape pushBlockHitBox;
	private ArrayList<Shape> collisionList = new ArrayList<Shape>();
	private static float x, y;
	private static ArrayList<pushBlock> pushBlocks = new ArrayList<pushBlock>();

	pushBlock(float x, float y, float width, float height) {
		pushBlock.x = x;
		pushBlock.y = y;
		this.height = height;
		this.width = width;
		// need to make a border that is on top of the box in the border class
		pushBlockHitBox = new Rectangle(x, y, width, height);
	}

	// called before player collisions but after player update
	public void update() {
		checkCollisionX();
		checkCollisionY();

		if (!Game.getP().getgSwitch()) {
			if (y < 875) {
				vY += 1.5f;
				y += vY;
				if (Game.getCurLevel() == Game.getLevel5()) {
					Level5.setPushy(y);
				}
				if (Game.getCurLevel() == Game.getLevel6()) {
					Level6.setPushy(y);
				}
				if (Game.getCurLevel() == Game.getLevel7()) {
					Level7.setPushy(y);
				}
				if (Game.getCurLevel() == Game.getLevel8()) {
					Level8.setPushy(y);
				}
			}
		} else {
			if (y > 100) {
				vY -= 1.5f;
				y += vY;
				if (Game.getCurLevel() == Game.getLevel5()) {
					Level5.setPushy(y);
				}
				if (Game.getCurLevel() == Game.getLevel6()) {
					Level6.setPushy(y);
				}
				if (Game.getCurLevel() == Game.getLevel7()) {
					Level7.setPushy(y);
				}
				if (Game.getCurLevel() == Game.getLevel8()) {
					Level8.setPushy(y);
				}
			}
		}
		if (pushBlockHitBox.intersects(Game.getP().getPlayerShape())) {
			if (pushBlockHitBox.getCenterX() < Game.getP().getPlayerShape().getCenterX()
					|| pushBlockHitBox.getCenterX() >= Game.getP().getPlayerShape().getCenterX()) {
				x += Game.getP().getxVelocity();
				if (Game.getCurLevel() == Game.getLevel5()) {
					Level5.setPushx(x);
				}
				if (Game.getCurLevel() == Game.getLevel6()) {
					Level6.setPushx(x);
				}
				if (Game.getCurLevel() == Game.getLevel7()) {
					Level7.setPushx(x);
				}
				if (Game.getCurLevel() == Game.getLevel8()) {
					Level8.setPushx(x);
				}
			}
		}
		if (pushBlockHitBox != null) {
			pushBlockHitBox.setLocation(x, y);
		}
	}

	private void checkCollisionX() {
		if (Collision.Collisions(pushBlockHitBox) != null) {
			collisionList.clear();
			collisionList = Collision.Collisions(pushBlockHitBox);
			for (Shape b : collisionList) {
				if (b.getMaxX() == b.getMinX()) {
					if (b.getCenterX() > pushBlockHitBox.getCenterX()) {
						x = b.getCenterX() - width;
						;
					}
					if (b.getCenterX() < pushBlockHitBox.getCenterX()) {
						x = b.getCenterX();
					}
				}
			}
		}
	}

	private void checkCollisionY() {
		if (Collision.Collisions(pushBlockHitBox) == null) {
			return;
		}
		collisionList.clear();
		collisionList = Collision.Collisions(pushBlockHitBox);
		for (Shape b : collisionList) {
			if (b.getMaxY() == b.getMinY()) {
				if (b.getCenterY() < pushBlockHitBox.getCenterY()) {
					if (!Game.getP().getgSwitch()) {
						vY = 0;
					}
					if (Game.getP().getgSwitch()) {
						vY = 0;
					}
					y = b.getCenterY();
				} else if (b.getCenterY() > pushBlockHitBox.getCenterY()) {
					vY = 0;
					y = b.getCenterY() - height;
				}
			}
		}
	}

	// render box image
	public void render(Graphics g) throws SlickException {
		if (pushBlockHitBox != null) {
			ImageLoader.returnImages("ghostBlock").draw(x, y, width, height);
			g.setColor(Color.white);
			g.draw(pushBlockHitBox);
		}
	}

	public Shape getBoxShape() {
		return pushBlockHitBox;
	}

	public static ArrayList<pushBlock> getPushBlocks() {
		return pushBlocks;
	}
}
