package GameObject.Block;

import java.util.*;

public class SwitchManager{
	protected float x, y, width, height;
	protected static ArrayList<Switch> Switches = new ArrayList<Switch>();

	public static void clearSwitches(){
		Switches.clear();
	}

	public static void createSwitch(float x, float y, float width, float height, boolean up){
		Switches.add(new Switch(x,y,width,height,up));
	}
	
	public static ArrayList<Switch> getSwitches(){
		return Switches;
	}
}
