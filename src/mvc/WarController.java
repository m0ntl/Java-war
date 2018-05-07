package mvc;

import bl.WarModel;
import ui.WarUI;

public class WarController implements WarModelEventsListener, WarUIEventsListener{

	private WarModel 	war;
	private WarUI 		warUI;
	
	public WarController( WarModel war ) {
		this.war = war;
		war.registerListener(this);
		
//		warUI = new WarUI();		
//		warUI.registerListener(this);
	}
	
	
	/* --- UI --- */
	public void addMissileLauncherFromUI(String id) {
		///war.addMissileLauncher(id);
	}

	public void addMissileDestructorFromUI(String id) {
		//war.addMissileDestructor(id);
	}

	public void addLauncherDestructorFromUI(String id, String type) {
		//war.addLauncherDestructor(id, type);
	}

	public void launchMissileFromUI(String idLauncher,  int potentialDamage, String destination, int flyTime ) {
		//war.launchMissile( idLauncher, potentialDamage, destination, flyTime);
	}

	public void destructLuncherFromUI(String id) {
		//war.destructLauncher(id);
	}

	public void destructMissileFromUI() {
		//war.destructMissile();
	}

	public void destructLauncherDestructorFromUI() {
		//war.destructLauncherDestructor();
	}

	public void statisticsFromUI() {
		//war.statistics();
	}

	public void exitFromUI() {
		//war.exit();
	}

	
	/* --- model --- */
	public void addMissileLauncherInModel(String id, boolean isHidden) {
		//warUI.showAddMissileLauncher(id);
	}

	public void addMissileDestructorInModel(String id) {
		//warUI.showAddMissileDestructor(id);
	}

	public void addLauncherDestructorInModel() {
		//warUI.showAddLauncherDestructor();
	}

	public void launchMissileInModel(String launcherId) {
		//warUI.showLaunchMissile( damage );
	}

	public void destructLuncherInModel() {
		//warUI.showDestructLuncher();
	}

	public void destructMissileInModel() {
		//warUI.showDestructMissile();		
	}

	public void destructLauncherDestructorInModel() {
		//warUI.showDestructLauncherDestructor();
	}

	public void statisticsInModel(int totalDamage, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int hits ) {
		//warUI.showStatistics(totalDamage, launchedMissiles, destructedMissiles, destructedLaunchers, hits );
	}

	public void exitInModel() {
		//warUI.showExit();
	}

}
