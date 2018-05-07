package bl;

public interface IWar {

	void addMissileLauncher(String id, boolean isHidden);
//	void addMissileDestructor(String id);
//	void addLauncherDestructor();
	void launchMissile(String launcherId, int potentialDamage, String destination, int flyTime );
//	void destructLuncher();
//	void destructMissile();
//	void destructLauncherDestructor();
	void statistics();
	void exit();	
	
}
