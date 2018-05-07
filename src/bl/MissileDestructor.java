package bl;

import java.util.HashMap;
import java.util.Map;

public class MissileDestructor extends Destructor{
	Map<String, Integer> destructMap;
	
	public MissileDestructor(String id) {
		super(id);
		this.destructMap = new HashMap<>();
	}
	
	public boolean destructMissle(String missileID, int flyTime) {
		//Assign random destruct time
		int destructAfterLaunch = (int )(Math.random() * BLConstants.MAX_FLY_TIME + BLConstants.MIN_FLY_TIME);
		
		//Add missile to destruct map
		destructMap.put(missileID, destructAfterLaunch);
		
		//Return if destruction was successful
		return destructAfterLaunch < flyTime ? true : false;
	}
	
	public String toString() { 
		return "Printing missileD with id: " + super.getID() + " with map: " + destructMap;
	}
}
