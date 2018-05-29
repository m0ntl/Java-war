package bl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.TimerTask;
import java.util.Date;
import java.util.Timer;




public class MissileDestructor extends Destructor implements Runnable {
	private boolean inWar;
	Map<String, Integer> destructMap;
	private Queue<Missile>	missilesToLaunch = new LinkedList<>();
	private SideA A;
	
	public MissileDestructor(String id, SideA A) {
		super(id);
		this.destructMap = new HashMap<>();
		inWar = true;
		this.A = A;
	}
	
	public void destructMissle(String missileID, int flyTime) {
		//Assign random destruct time
		//int destructAfterLaunch = (int )(Math.random() * BLConstants.MAX_FLY_TIME + BLConstants.MIN_FLY_TIME);
		
		//Add missile to destruct map
		destructMap.put(missileID, flyTime);
		
		//Return if destruction was successful
		//return destructAfterLaunch < flyTime ? true : false;
		
		A.destructMissile(missileID);
	}
	
	public String toString() { 
		return "Printing missileD with id: " + super.getID() + " with map: " + destructMap;
	}
	
	public void terminate(){
//		inWar = false;
//		synchronized (this) {
//			if ( waitingToDestruct )
//				notify();
//		}
	}
	
	public void run() {
//		while ( inWar ) {
//			if ( !missilesToLaunch.isEmpty() )
//				prepareToLaunch();
//			else {
//				synchronized (MissileLauncher.this) {
//					try {
//						waitingToDestruct = true;
//						wait(); 
//						waitingToDestruct = false;
//					} catch (InterruptedException e) {e.printStackTrace();}
//				}
//			}
//		}
	}
}
