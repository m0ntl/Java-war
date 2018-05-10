package bl;

public interface IWar {

	void addMissileLauncher(String id, boolean isHidden);
	
	void addLaunch(String launcherID, String missileID, int potentialDamage, String destination, int flyTime);
	void onStartOfLaunch(String launcherID, String missileID, String destination);
	void onEndOfLaunch(MissileLauncher l, boolean success, String destination, int damage, int flightTime);

	void statistics();
	void exit();	
	
}
