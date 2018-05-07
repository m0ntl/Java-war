package mvc;

public interface WarUIEventsListener {
	
	void addMissileLauncherFromUI(String id);
	void addMissileDestructorFromUI(String id);
	void addLauncherDestructorFromUI(String id, String type);
	void launchMissileFromUI( String idLauncher, int potentialDamage, String destination, int flyTime );
	void destructLuncherFromUI(String id);
	void destructMissileFromUI();
	void destructLauncherDestructorFromUI();
	void statisticsFromUI();
	void exitFromUI();
}
