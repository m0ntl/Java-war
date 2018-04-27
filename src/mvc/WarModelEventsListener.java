package mvc;

public interface WarModelEventsListener {

	void addMissileLauncherInModel();
	void addMissileDestructorInModel();
	void addLauncherDestructorInModel();
	void launchMissileInModel( int damage );
	void destructLuncherInModel();
	void destructMissileInModel();
	void destructLauncherDestructorInModel();
	void sumUpInModel(int totalDamege, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int hits );
	void exitInModel();	
}
