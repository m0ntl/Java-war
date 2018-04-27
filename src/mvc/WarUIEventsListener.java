package mvc;

public interface WarUIEventsListener {
	
	void addMissileLauncherFromUI(String id);
	void addMissileDestructorFromUI(String id);
	void addLauncherDestructorFromUI(String id, int type);
	void launchMissileFromUI( String idLauncher, String idMissile, int potentialDamage, String destination, int flyTime );
	void destructLuncherFromUI(String id);
	void destructMissileFromUI();
	void destructLauncherDestructorFromUI();
	void sumUpFromUI();
	void exitFromUI();
}
