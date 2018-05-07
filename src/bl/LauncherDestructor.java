package bl;

import java.util.HashMap;
import java.util.Map;

import bl.BLConstants.LauncherDestructorType;

public abstract class LauncherDestructor extends Destructor {
	BLConstants.LauncherDestructorType type;
	Map<String, Integer> destructMap;
	
	public LauncherDestructor(String id, BLConstants.LauncherDestructorType type) {
		super(id);
		setType(type);
		Map<String, Integer> destructMap = new HashMap<>();
	}
	
	private void setType(LauncherDestructorType type) {
		this.type = type;
	}

	public boolean destructLauncher(String launcherID, int exposedTime) {
		//Randomize how long it will take to destruct
		int destructTime = (int )(Math.random() * BLConstants.MAX_FLY_TIME + BLConstants.MIN_FLY_TIME);
		
		//Add attempt to destruction map
		destructMap.put(launcherID, destructTime);
		
		//Return if destruction was successful
		return destructTime < exposedTime? true : false;
	}
	
	public String toString() { 
		return "Printing Launcher with id: " + super.getID();
	}
}
