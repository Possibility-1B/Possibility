package Media;

import core.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {	
	private static Image cursor;
	private static int timer = 0;
	private static int fadeInTimer = 0;
	private static int fadeOutTimer = 255;
    private static Image glow;
	
	private static Image door;

	private static Image spike;
	private static Image spike2;	
	
	private static Image welcome;
	private static Image youDied;
	
	private static Image lvl1;
	private static Image lvl2;
	private static Image lvl3;
	private static Image lvl4;
	private static Image lvl5;
	private static Image lvl6;
	private static Image lvl7;
	private static Image lvl8;
	private static Image pressE;
	private static Image E;
	
	private static Image credits;
	private static Image lvlCredits;
	private static Image rate;
	private static Image thanks;
	private static Image thumbsup;

	private static Image gravityUp;
	private static Image gravityDown;
	private static Image gravityRight;
	private static Image gravityLeft;
	
	private static Image AFKLeft;
	private static Image AFKRight;
	private static Image AFKUpsideDownLeft;
	private static Image AFKUpsideDownRight;
	
	private static Image cooldown;
	
	private static Image midgameLogo;
	private static Image midgameLogonoBGScaled;
	
	private static Image openDoor;

	private static Image downGhostBlock;
	private static Image ghostBlock;

	private static Image newIce;
	private static Image iceText2;
	private static Image ghostBlockText;
		
	public static void loadImages() throws SlickException{
		midgameLogo = new Image("res/images/GUI/Midgame1.png");
		midgameLogonoBGScaled = midgameLogo.getScaledCopy(Main.getScreenWidth(), Main.getScreenHeight());
		
		door = new Image("res/images/Puzzle/door.png");
		openDoor = new Image("res/images/Puzzle/openDoor.png");
		
		spike = new Image("res/images/Hazards/spike.png");
		spike2 = spike.getFlippedCopy(false, true);
				
		glow = new Image("res/images/white.png");
		
		lvl1 = new Image("res/images/levelNumbers/One.png");
		lvl2 = new Image("res/images/levelNumbers/Two.png");
		lvl3 = new Image("res/images/levelNumbers/Three.png");
		lvl4 = new Image("res/images/levelNumbers/Four.png");
		lvl5 = new Image("res/images/levelNumbers/Five.png");
		lvl6 = new Image("res/images/levelNumbers/Six.png");
		lvl7 = new Image("res/images/levelNumbers/Seven.png");
		lvl8 = new Image("res/images/levelNumbers/Eight.png");
		pressE = new Image("res/images/levelNumbers/pressE.png");
		E = new Image("res/images/GUI/E.png");
		
		cooldown = new Image("res/images/GUI/readyCooldown.png");
		
		lvlCredits = new Image("res/images/levelNumbers/lvlCredits.png");
		
		credits = new Image("res/images/Credits/Credits.png");
		thanks = new Image("res/images/Credits/thanks.png");
		rate = new Image("res/images/Credits/rate.png");
		thumbsup = new Image("res/images/Credits/thumbsup.png");
		
        welcome = new Image("res/images/GUI/welcome.png");
        youDied = new Image("res/images/GUI/youDied.png");

		AFKLeft = new Image("res/images/Player/AFKLeft.png");
		AFKRight = new Image("res/images/Player/AFKRight.png");
		AFKUpsideDownLeft = new Image("res/images/Player/AFKUpsideDownLeft.png");
		AFKUpsideDownRight = new Image("res/images/Player/AFKUpsideDownRight.png");
		
		gravityUp = new Image("res/images/Arrows/leverUp.png");
		gravityDown = new Image("res/images/Arrows/leverDown.png");
		gravityRight = new Image("res/images/Arrows/leverRight.png");
		gravityLeft = new Image("res/images/Arrows/leverLeft.png");
		
		cursor = new Image("res/images/GUI/cursor.png");
	
		ghostBlock = new Image("res/images/Puzzle/ghostBlock.png");
		downGhostBlock = new Image("res/images/Puzzle/downGhostBlock.png");
		
		newIce = new Image("res/images/Text/newIce.png");
		iceText2 = new Image("res/images/Text/iceText2.png");
		ghostBlockText = new Image("res/images/Text/ghostBlockText.png");
	}
	
	public static Image getCursor(){
		return cursor;
	}

	public static Image returnImages(String s) throws SlickException{
		if(s.equalsIgnoreCase("gravityUp")){
			return gravityUp;
		}
		
		if(s.equalsIgnoreCase("cooldown")){
			return cooldown;
		}
		
		if(s.equalsIgnoreCase("gravityDown")){
			return gravityDown;
		}
		
		if(s.equalsIgnoreCase("gravityRight")){
			return gravityRight;
		}
		
		if(s.equalsIgnoreCase("gravityLeft")){
			return gravityLeft;
		}
		
		if(s.equalsIgnoreCase("AFKLeft")){
			return AFKLeft;
		}
		
		if(s.equalsIgnoreCase("AFKRight")){
			return AFKRight;
		}
		
		if(s.equalsIgnoreCase("AFKUpsideDownLeft")){
			return AFKUpsideDownLeft;
		}
		
		if(s.equalsIgnoreCase("AFKUpsideDownRight")){
			return AFKUpsideDownRight;
		}
		
		if(s.equalsIgnoreCase("glow")){
            return glow;
        }

		if(s.equalsIgnoreCase("door")){
			return door;
		}
		
		if(s.equalsIgnoreCase("spike")){
			return spike;
		}
		
		if(s.equalsIgnoreCase("lvl1")){
			return lvl1;
		}
		
		if(s.equalsIgnoreCase("lvl2")){
			return lvl2;
		}
		
		if(s.equalsIgnoreCase("lvl3")){
			return lvl3;
		}
		
		if(s.equalsIgnoreCase("lvl4")){
			return lvl4;
		}
		
		if(s.equalsIgnoreCase("lvl5")){
			return lvl5;
		}
		
		if(s.equalsIgnoreCase("lvl6")){
			return lvl6;
		}
		
		if(s.equalsIgnoreCase("lvl7")){
			return lvl7;
		}
		
		if(s.equalsIgnoreCase("lvl8")){
			return lvl8;
		}
		
		if(s.equalsIgnoreCase("pressE")){
			return pressE;
		}
		
		if(s.equalsIgnoreCase("E")){
			return E;
		}
		
		if(s.equalsIgnoreCase("lvlCredits")){
			return lvlCredits;
		}
		
		if(s.equalsIgnoreCase("credits")){
			return credits;
		}
		
		if(s.equalsIgnoreCase("rate")){
			return rate;
		}
		
		if(s.equalsIgnoreCase("thanks")){
			return thanks;
		}
		
		if(s.equalsIgnoreCase("thumbsup")){
			return thumbsup;
		}
		
		if(s.equalsIgnoreCase("openDoor")){
			return openDoor;
		}
		
        if(s.equalsIgnoreCase("welcome")){
            return welcome;
        }
        
        if(s.equalsIgnoreCase("youDied")){
        	return youDied;
        }
        
        if(s.equalsIgnoreCase("ghostBlock")){
            return ghostBlock;
        }
        
        if(s.equalsIgnoreCase("downGhostBlock")){
            return downGhostBlock;
        }
        
        if(s.equalsIgnoreCase("ghostBlockText")){
            return ghostBlockText;
        }
        
        if(s.equalsIgnoreCase("spike2")){
        	return spike2;
        }
        
        if(s.equalsIgnoreCase("iceText2")){
        	return iceText2;
        }
        
        if(s.equalsIgnoreCase("newIce")){
        	return newIce;
        }
        
		return null;
	}
	
	public static void getImages(String s){
		if(s.equalsIgnoreCase("logo")){	
			timer++;
			
			if(timer < 255){
				fadeInTimer++;
			}
			
			if(timer > 300){
				fadeOutTimer--;
			}
			
			if(timer <= 255){
				midgameLogonoBGScaled.draw(-1, -1, new Color(255,255,255,fadeInTimer));
			}
			else if(timer > 300){
				midgameLogonoBGScaled.draw(-1, -1, new Color(255,255,255,fadeOutTimer));
			}else{
				midgameLogonoBGScaled.draw(-1, -1);
			}
		}
	}
}
