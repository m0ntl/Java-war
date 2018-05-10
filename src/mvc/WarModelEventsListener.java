package mvc;

public interface WarModelEventsListener {

	void addMissileLauncherInModel(String id, boolean isHidden);
	void addMissileDestructorInModel(String id);
	void addLauncherDestructorInModel();
	void launchMissileInModel(String launcherId);
	void destructLuncherInModel();
	void destructMissileInModel();
	void destructLauncherDestructorInModel();
	void statisticsInModel( String s );
	void exitInModel();	
}
