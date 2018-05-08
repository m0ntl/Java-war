package bl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MissileLauncher implements Runnable, BLConstants{

	private String 			id;		
	
	private boolean			inWar;
	private boolean			waitingForMissile;

	//statistics
	private int 			hits;
	private int 			totalDamage;
	private int 			launchedMissiles;
	private int 			destructedMissiles;
	private int 			destructedLaunchers;
		
	private Queue<Missile>	missilesToLaunch = new LinkedList<>();
	
	//temp
	private Timer 			timer;
	private boolean			isAlive;
	private static Logger 	launcherLogger;

	
	public MissileLauncher(String id, Timer timer){
		this.id = id;
		this.timer = timer;
		
		waitingForMissile = false;
		inWar = true;
		isAlive = true;
		initLoggers();
	}

	private void initLoggers(){
		//temp
		try {
			launcherLogger = Logger.getLogger("launcher Logger");
			FileHandler launcherHandler = new FileHandler("launcher_" + id + ".txt");
			launcherLogger.addHandler(launcherHandler);

		} catch (SecurityException | IOException e) {e.printStackTrace();}
	}
		
	public synchronized void addMissileToQueue(int potentialDamage, String destination, int flyTime){
		Missile m = new Missile(potentialDamage, destination, flyTime, this);
		missilesToLaunch.add(m);
 		
		synchronized (MissileLauncher.this) {
			if ( waitingForMissile )
				notify();
		}
	}

	
	/* --- launch missile --- */
	public void run() {
		while ( inWar ) {
			if ( !missilesToLaunch.isEmpty() )
				prepareToLaunch();
			else {
				synchronized (MissileLauncher.this) {
					try {
						waitingForMissile = true;
						wait(); 
						waitingForMissile = false;
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
		System.out.println("launcher #" +id+ " has finished..");
	}
	
	private synchronized void prepareToLaunch(){
		Missile m = missilesToLaunch.peek();
		if (m == null) 
			return;
		
		launchMissile(m);
		updateResults(m);				
		missilesToLaunch.poll();
		System.out.println("launcher #"+id+" finished with missile #"+m.getTheId()+" !!!!!!!");

	}

	public void launchMissile(Missile m) {
		System.out.println("launcher #"+id+" starts the launch of missile #"+m.getTheId());
		m.start();
		try {
			//wait until missile finishes flying
			wait(); 	
		} catch (InterruptedException e) { e.printStackTrace(); }
 	}

	
	/* --- results of a launch --- */
	public void updateResults(Missile m){
		launchedMissiles++;

		String logMsg = "missile #" + m.getTheId() + " finished! \ndestination was " + m.getDestination();

		if ( m.isDestructed() )
			logMsg += addMiss();
		else
			logMsg += addHit(m.getDamage());
		
		synchronized (m) {
			m.notify();
		}
		//launcherLogger.log(Level.INFO, logMsg);
	}
	
	private String addHit(int damage){
		hits++;
		totalDamage +=damage;
		return "\nmissile hit the target..! \ndamage is " + damage;
	}
	
	private String addMiss(){
		destructedMissiles++;
		return "\nmissile got destructed..\n";
	}

	
	/* --- stop thread --- */
	public void destructLauncher(){
		isAlive = false;
		terminate();
	}
		
	public void terminate(){
		inWar = false;
		synchronized (this) {
			if ( waitingForMissile )
				notify();
		}
	}

	
	/* --- getters --- */
	public String getTheId(){
		return id;
	}
	
	public int getDestructedMissiles() {
		return destructedMissiles;
	}
	
	public int getDestructedLaunchers() {
		return destructedLaunchers;
	}

	public int getLaunchedMissiles() {
		return launchedMissiles;
	}

	public int getTotalDamage() {
		return totalDamage;
	}

	public int getHits() {
		return hits;
	}
	
	
	/* --- hashCode & equals --- */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MissileLauncher other = (MissileLauncher) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}