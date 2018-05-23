package bl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;
import java.util.Date;
import java.util.Timer;




public class MissileDestructor extends Destructor {
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
