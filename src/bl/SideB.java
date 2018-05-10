package bl;

import java.util.Optional;
import java.util.Vector;

public class SideB {

	WarModel 						theWar;
	
	private int 					hits;
	private int 					totalDamage;
	private int 					launchedMissiles;
		
	private Vector<Missile>			allMissiles = new Vector<>();
	private Vector<MissileLauncher>	allLaunchers = new Vector<>();

	
	public SideB(WarModel theWar){
		this.theWar = theWar;
		launchedMissiles = 0;
	}

	/* missile-launch */
	public void addLauncher(MissileLauncher l) {
		allLaunchers.add(l);
		Thread launcherThread = new Thread(l);
		launcherThread.start();	
	}
	
	public void addMissileToLaunchQueue(String launcherID, String missileID, int potentialDamage,String destination, int flyTime) {
		
		MissileLauncher launcher = getLauncherById(launcherID);
		if ( launcher == null )
			return;
		
		Missile m = new Missile(missileID, potentialDamage, destination, flyTime, launcher);
		launcher.addMissileToLaunchQueue(m);
		allMissiles.add(m);
		launchedMissiles++;
	}
	
	public synchronized void onStartOfLaunch( String launcherID, String missileID, String  destination){
		theWar.onStartOfLaunch(launcherID, missileID, destination);
	}
	
	public synchronized void onEndOfLaunch(MissileLauncher l, boolean success, String destination, int damage, int flightTime){
		if ( success ){
			hits++;
			totalDamage += damage;
		}
		theWar.onEndOfLaunch(l, success, destination, damage, flightTime);	
	}
	
	public void endWar(){
		for( MissileLauncher l : allLaunchers )
			l.terminate();
	}
	
	
	/* getters */
	private MissileLauncher getLauncherById(String id){
		Optional<MissileLauncher> matchingObject = allLaunchers.stream().
				filter(l -> l.getID().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public Missile getMissileById(String id){
		Optional<Missile> matchingObject = allMissiles.stream().
				filter(m -> m.getID().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public int getLaunchedMissiles(){
		return launchedMissiles;
	}
	
	public int getTotalDamage(){
		return totalDamage;
	}
	
	public int getHits(){
		return hits;
	}
}



