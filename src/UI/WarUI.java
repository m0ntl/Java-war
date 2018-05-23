package UI;

import mvc.WarUIEventsListener;

public interface WarUI extends UIConstants{
	
	void showAddMissileLauncher (String id);
	void showAddMissileDestructor(String id);
	void showAddLauncherDestructor();
	void showLaunchMissile(int damage);
	void showDestructLuncher();
	void showDestructMissile();
	void showDestructLauncherDestructor();
	void showStatistics(int totalDamage, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int hits );//(String tribeName, int newNum);
	void showExit();
	
	void initiateAddMissileLauncher(String id, boolean isHidden);
	void initiateAddMissileDestructor(String id);
	void initiateAddLauncherDestructor(String id, String type) ;
	void initiateLaunchMissile(String idLauncher, int potentialDamage, String destination, int flyTime);
	void initiateDestructLuncher(String id);
	void initiateDestructMissile();
	void initiateDestructLauncherDestructor();
	void initiateStatistics();
	void initiateExit();

	void registerListener(WarUIEventsListener listener);
	
	//void showFailedAdding..
}
