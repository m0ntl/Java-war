package bl;

import java.util.Optional;
import java.util.Timer;
import java.util.Vector;

public class SideB {

	private Vector<MissileLauncher>	allLaunchers = new Vector<>();
	
	public void addLauncher(String id, boolean isHidden, Timer timer) {
		MissileLauncher l;
		if ( isHidden )
			l = new HiddenMissileLauncher(id, timer);
		else
			l = new MissileLauncher(id, timer);
		
		allLaunchers.add(l);
		Thread launcherThread = new Thread(l);
		launcherThread.start();	
	}
	
	public void launchMissile(String idLauncher, int potentialDamage, String destination, int flyTime) {
		MissileLauncher launcher = getLauncherById(idLauncher);
		if ( launcher == null )
			return;
		
		launcher.addMissileToQueue(potentialDamage, destination, flyTime);
	}

	public void endWar(){
		for( MissileLauncher l : allLaunchers )
			l.terminate();
	}
	
	
	/* --- getters --- */
	private MissileLauncher getLauncherById(String id){
		Optional<MissileLauncher> matchingObject = allLaunchers.stream().
				filter(l -> l.getTheId().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public int getDestructedMissiles(){
		int destructedMissiles = 0;
		for( MissileLauncher l : allLaunchers )
			destructedMissiles += l.getDestructedMissiles();
		return destructedMissiles;
	}
	
	public int getDestructedLaunchers(){
		int destructedLaunchers = 0;
		for( MissileLauncher l : allLaunchers )
			destructedLaunchers += l.getDestructedLaunchers();
		return destructedLaunchers;
	}
	
	public int getLaunchedMissiles(){
		int launchedMissiles = 0;
		for( MissileLauncher l : allLaunchers )
			launchedMissiles += l.getLaunchedMissiles();
		return launchedMissiles;
	}
	
	public int	getTotalDamage(){
		int totalDamage = 0;
		for( MissileLauncher l : allLaunchers )
			totalDamage += l.getTotalDamage();
		return totalDamage;
	}
	
	public int getHits(){
		int hits = 0;
		for( MissileLauncher l : allLaunchers )
			hits += l.getHits();
		return hits;
	}
	
	public int getLaunchersNum(){
		return allLaunchers.size();
	}
}
