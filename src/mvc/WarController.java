package mvc;

import UI.WarUI;
import bl.War;

public class WarController implements WarModelEventsListener, WarUIEventsListener{

	private War 	war;
	private WarUI 	warUI;
	
	public WarController(War war, WarUI warUI) {
		this.war = war;
		this.warUI = warUI;
		
		war.registerListener(this);
		warUI.registerListener(this);
	}

	
	// UI
	public void addMissileLauncherFromUI(String id) {
		war.addMissileLauncher(id);
	}

	public void addMissileDestructorFromUI(String id) {
		war.addMissileDestructor(id);
	}

	public void addLauncherDestructorFromUI(String id, int type) {
		war.addLauncherDestructor(id, type);
	}

	public void launchMissileFromUI(String idLauncher, String idMissile, int potentialDamage, String destination, int flyTime ) {
		war.launchMissile( idLauncher, idMissile, potentialDamage, destination, flyTime);
	}

	public void destructLuncherFromUI(String id) {
		war.destructLauncher(id);
	}

	public void destructMissileFromUI() {
		war.destructMissile();
	}

	public void destructLauncherDestructorFromUI() {
		war.destructLauncherDestructor();
	}

	public void sumUpFromUI() {
		war.sumUp();
	}

	public void exitFromUI() {
		war.exit();
	}

	
	//model	
	public void addMissileLauncherInModel() {
		warUI.showAddMissileLauncher();
	}

	public void addMissileDestructorInModel() {
		warUI.showAddMissileDestructor();
	}

	public void addLauncherDestructorInModel() {
		warUI.showAddLauncherDestructor();
	}

	public void launchMissileInModel( int damage ) {
		warUI.showLaunchMissile( damage );
	}

	public void destructLuncherInModel() {
		warUI.showDestructLuncher();
	}

	public void destructMissileInModel() {
		warUI.showDestructMissile();		
	}

	public void destructLauncherDestructorInModel() {
		warUI.showDestructLauncherDestructor();
	}

	public void sumUpInModel(int totalDamege, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int hits ) {
		warUI.showSumUp(totalDamege, launchedMissiles, destructedMissiles, destructedLaunchers, hits );
	}

	public void exitInModel() {
		warUI.showExit();
	}

}
