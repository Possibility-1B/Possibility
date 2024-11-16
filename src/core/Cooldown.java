package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import Media.AnimationLoader;
import Media.ImageLoader;

public class Cooldown {

	public Cooldown()
	{
		
	}
	
	public static void draw(Graphics g) throws SlickException
	{

		if(Game.getP().getxPos() <= Main.getScreenWidth()/2)
		{
			if(Game.timer <= 90)
			{
			AnimationLoader.returnAnimation("cooldown").draw(100, Main.getScreenHeight() - 90, 75,75);
			}
			else
			{
			AnimationLoader.returnAnimation("cooldown").restart();
			ImageLoader.returnImages("cooldown").draw(100,Main.getScreenHeight() - 90, 75, 75);
			}
		}
		else if(Game.getP().getxPos() >= 2*Main.getScreenWidth() + Main.getScreenWidth()/2 && Game.getCurLevel() != Game.getLevel3() && Game.getCurLevel() != Game.getLevel5())
		{
			if(Game.timer <= 90)
			{
			
			AnimationLoader.returnAnimation("cooldown").draw(2*Main.getScreenWidth() + 100, Main.getScreenHeight() - 90, 75,75);
			}
			else
			{
			ImageLoader.returnImages("cooldown").draw(2*Main.getScreenWidth() + 100, Main.getScreenHeight() - 90, 75,75);
			AnimationLoader.returnAnimation("cooldown").restart();
			}
		}
		else if(Game.getP().getxPos() >= Main.getScreenWidth() + Main.getScreenWidth()/2 && (Game.getCurLevel() == Game.getLevel3() || Game.getCurLevel() == Game.getLevel5()))
		{
			if(Game.timer <= 90)
			{
			
			AnimationLoader.returnAnimation("cooldown").draw(Main.getScreenWidth() + 100, Main.getScreenHeight() - 90, 75,75);
			}
			else
			{
			ImageLoader.returnImages("cooldown").draw(Main.getScreenWidth() + 100, Main.getScreenHeight() - 90, 75,75);
			AnimationLoader.returnAnimation("cooldown").restart();
			}
		}
		else
		{
			
			if(Game.timer <= 90)
			{
			
			AnimationLoader.returnAnimation("cooldown").draw(Game.getP().getxPos() - Main.getScreenWidth()/2 + 110, Main.getScreenHeight() - 90, 75,75);
			}
			else
			{
				AnimationLoader.returnAnimation("cooldown").restart();
				ImageLoader.returnImages("cooldown").draw(Game.getP().getxPos() - Main.getScreenWidth()/2 + 110, Main.getScreenHeight() - 90, 75,75);
			}
		}
	}
	
	
	
}
