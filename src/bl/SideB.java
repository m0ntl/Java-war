package bl;

import java.util.Optional;
import java.util.Vector;

public class SideB extends Warrior implements BLConstants{

	private int 							launchedMissiles;
	private int 							destructedMissiles;
	private int 							destructedLaunchers;
	private int 							missileHits;
	private int 							destructedDestructors;

	private Vector<MissileLauncher> 		launchers;
	
	public SideB() {
	}

	public void addMissileLauncher(String id) {
		launchers.add(new MissileLauncher(id));
	}

	public int launchMissile(String idLauncher, String idMissile, int potentialDamage, String destination, int flyTime, int launchTime) {
	
		MissileLauncher launcher = getLauncherById( idLauncher );
		if ( launcher == null )
			return 0;
		
		Missile m = new Missile(idMissile, potentialDamage, destination, flyTime ,launchTime);
		launcher.launchMissile(m);
		launchedMissiles++;
		int damage = m.getDamage();
		if ( m.isDestructed() )
			destructedMissiles++;
		
		return damage;
	}

	public void destructLauncherDestructor() {
		 
	}
	
	public boolean launcherUnderAttack(String id){
		MissileLauncher launcher = getLauncherById(id);
		boolean isLauncherDestructed = launcher.underAttack();
		if ( !isLauncherDestructed )
			return false;
		
		destructedLaunchers++;
		launchers.remove(launcher);
		return true;
	}

	public int getLaunchedMissiles() {
		return launchedMissiles;
	}

	public int getDestructedMissiles() {
		return destructedMissiles;
	}

	public int getDestructedLaunchers() {
		return destructedLaunchers;
	}

	public int getMissileHits() {
		return missileHits;
	}
	
	private MissileLauncher getLauncherById(String id){
		Optional<MissileLauncher> matchingObject = launchers.stream().
				filter(l -> l.getId().equals(id)).findFirst();
		return matchingObject.orElse(null);		
	}


}
