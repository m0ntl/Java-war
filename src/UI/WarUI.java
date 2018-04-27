package UI;

import mvc.WarUIEventsListener;

public interface WarUI {
	
	void showAddMissileLauncher ();
	void showAddMissileDestructor();
	void showAddLauncherDestructor();
	void showLaunchMissile(int damage);
	void showDestructLuncher();
	void showDestructMissile();
	void showDestructLauncherDestructor();
	void showSumUp(int totalDamege, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int hits );//(String tribeName, int newNum);
	void showExit();
	
	void initiateAddMissileLauncher(String id);
	void initiateAddMissileDestructor(String id);
	void initiateAddLauncherDestructor(String id, int type) ;
	void initiateLaunchMissile(String idLauncher, String idMissile, int potentialDamage, String destination, int flyTime);
	void initiateDestructLuncher(String id);
	void initiateDestructMissile();
	void initiateDestructLauncherDestructor();
	void initiateSumUp();
	void initiateExit();

	void registerListener(WarUIEventsListener listener);
	
	//void showFailedAddingSurvivorToTribe(String fromTribeName);


}
