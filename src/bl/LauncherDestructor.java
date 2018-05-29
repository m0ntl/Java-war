package bl;

import java.util.HashMap;
import java.util.Map;

import bl.BLConstants.LauncherDestructorType;

public abstract class LauncherDestructor extends Destructor implements Runnable {
	private boolean inWar;
	BLConstants.LauncherDestructorType type;
	Map<String, Integer> destructMap;
	private SideA A;
	
	public LauncherDestructor(String id, BLConstants.LauncherDestructorType type, SideA A) {
		super(id);
		setType(type);
		Map<String, Integer> destructMap = new HashMap<>();
		this.A = A;
		inWar = true;
	}
	
	private void setType(LauncherDestructorType type) {
		this.type = type;
	}

	public void destructLauncher(String launcherID, String destructTime) {
		//Randomize how long it will take to destruct
		//int destructTime = (int )(Math.random() * BLConstants.MAX_FLY_TIME + BLConstants.MIN_FLY_TIME);
		
		//Add attempt to destruction map
		destructMap.put(launcherID, Integer.valueOf(destructTime));
		
		//Return if destruction was successful
		//return destructTime < exposedTime? true : false;
		A.destructLauncher(launcherID);
	}
	
	public String toString() { 
		return "Printing Launcher with id: " + super.getID();
	}
	
	public void terminate(){
//		inWar = false;
//		synchronized (this) {
//			if ( waitingForMissile )
//				notify();
//		}
	}
	
	@Override
	public void run() {
//		while ( inWar ) {
//			if ( !missilesToLaunch.isEmpty() )
//				prepareToLaunch();
//			else {
//				synchronized (MissileLauncher.this) {
//					try {
//						waitingForMissile = true;
//						wait(); 
//						waitingForMissile = false;
//					} catch (InterruptedException e) {e.printStackTrace();}
//				}
//			}
//		}
	}
	
}
