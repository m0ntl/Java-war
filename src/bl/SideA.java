package bl;

import java.util.Optional;
import java.util.Vector;

public class SideA {

	WarModel 						theWar;
	
	private int 					missileDestroyed;
	private int 					launchersDestroyed;
		
	private Vector<MissileDestructor>	allMissileDestructors = new Vector<>();
	private Vector<LauncherDestructor>	allLauncherDestructors = new Vector<>();

	
//	public void addMissileDestructorToLaunchQueue(String launcherID, String missileID, int potentialDamage,String destination, int flyTime) {
//		MissileLauncher launcher = getLauncherById(launcherID);
//		if ( launcher == null )
//			return;
//		
//		Missile m = new Missile(missileID, potentialDamage, destination, flyTime, launcher);
//		launcher.addMissileToLaunchQueue(m);
//		allMissiles.add(m);
//		launchedMissiles++;
//	}
	
	public MissileDestructor getMissileDestructorById(String id){
		Optional<MissileDestructor> matchingObject = allMissileDestructors.stream().
				filter(l -> l.getID().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public LauncherDestructor getLauncherDestructorById(String id){
		Optional<LauncherDestructor> matchingObject = allLauncherDestructors.stream().
				filter(l -> l.getID().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}
	
	public SideA(WarModel theWar){
		this.theWar = theWar;
		missileDestroyed = 0;
		launchersDestroyed = 0;
	}
	
	public void endWar(){
		for( MissileDestructor md : allMissileDestructors )
			md.terminate();
		
		for( LauncherDestructor ld : allLauncherDestructors )
			ld.terminate();
	}
	
	/* missile-launch */
	public void addMissileDestructor(MissileDestructor l) {
		allMissileDestructors.add(l);
		Thread launcherThread = new Thread(l);
		launcherThread.start();	
	}
	
	public void addLauncherDestructor(LauncherDestructor l) {
		allLauncherDestructors.add(l);
		//Thread launcherThread = new Thread(l);
		//launcherThread.start();	
	}
	
	public synchronized void onStartOfLaunch( String launcherID, String missileID, String  destination){
		theWar.onStartOfLaunch(launcherID, missileID, destination);
	}

	public void destructLauncher(String launcherID) {
		if (theWar.destructLauncher(launcherID) == true){
			this.launchersDestroyed++;
		}
	}
	public void destructMissile(String targetID) {
		if (theWar.destructMissile(targetID)) {
			this.missileDestroyed++;
		}
	}
}



