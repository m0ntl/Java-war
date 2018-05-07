package bl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import mvc.WarModelEventsListener;

public class WarModel implements IWar, BLConstants {
	
	private SideB 							B;
	
	//?
	private Timer 							timer;
	private int 							time;
	private boolean 						inWar;

	private int 							launchedMissiles;
	private Vector<WarModelEventsListener>	allListeners = new Vector<WarModelEventsListener>();

	
	public WarModel() {
		inWar = true;
		timer = new Timer();
		B = new SideB(); 
		
		launchedMissiles = 0;
	}

	public void registerListener(WarModelEventsListener listener) {
		allListeners.add(listener);
	}
	
	
	/* --- missile-launcher related --- */
	public void addMissileLauncher(String id, boolean isHidden) {
		B.addLauncher(id, isHidden, timer);
		System.out.println("missile launcher #" + id + ", hidden: " + isHidden+ ", was added !");
		
		fireAddMissileLuncherEvent(id, isHidden);	
	}
	
	public void addMissileToLaunch(String launcherId, String id, int damage, String destination, int flyTime, int launchTime) {
		timer.schedule( new TimerTask() {
			@Override
			public void run() {
				WarModel.this.launchMissile(launcherId, damage, destination, flyTime);
			} 
		}, launchTime * ONE_SEC );
	}
	
	public void launchMissile(String launcherId, int potentialDamage, String destination, int flyTime) {
		B.launchMissile(launcherId, potentialDamage, destination, flyTime);
		fireLaunchMissileEvent(launcherId);
		launchedMissiles++;
	}
	
	
	/* --- general --- */
	public void statistics(){
		int destructedLaunchers = B.getDestructedLaunchers();
		int destructedMissiles = B.getDestructedMissiles();
		int launchedMissiles = B.getLaunchedMissiles();
		int hits = B.getHits();
		int totalDamage = B.getTotalDamage();
		
		System.out.println("total damage: " + totalDamage
				+ "\nlaunched missiles: " + launchedMissiles
				+ "\n destructed missiles: " +destructedMissiles
				+ "\n destructed launchers: " +destructedLaunchers 
				+ "\nmissile hits: " + hits);

		fireStatisticsEvent(totalDamage, launchedMissiles, destructedMissiles, destructedLaunchers, hits);
	}
	
	public void endWar(){
		inWar = false;
		B.endWar();
	}
	
	public void exit() {
		statistics();
		endWar();
		fireExitEvent();
	}

	
	/* --- fire to controller --- */
	private void fireAddMissileLuncherEvent(String id, boolean isHidden) {
		for (WarModelEventsListener l : allListeners) 
			l.addMissileLauncherInModel(id, isHidden);
	}
	
	private void fireLaunchMissileEvent(String launcherId) {
		for (WarModelEventsListener l : allListeners) {
			l.launchMissileInModel(launcherId);
		}
	}

	private void fireStatisticsEvent(int totalDamage, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int missileHits){
		for (WarModelEventsListener l : allListeners) 
			l.statisticsInModel(totalDamage, launchedMissiles, destructedMissiles, destructedLaunchers, missileHits );
	}
	
	private void fireExitEvent() {
		for (WarModelEventsListener l : allListeners)
			l.exitInModel();
	}
	
	/* --- getters --- */
	public int getLaunchersNum(){
		return getLaunchersNum();
	}
	
	public int getLaunchedMissiles(){
		return launchedMissiles;
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
//	//add missile destructor
//	public void addMissileDestructor(String id) {
//		A.addMissileDestructor(id);
//		fireAddMissileDestructorEvent(id);
//	}
//
//	private void fireAddMissileDestructorEvent(String id) {
//		for (WarModelEventsListener l : allListeners) {
//			l.addMissileDestructorInModel(id);
//		}
//	}
//	//add launcher destructor
//	public void addLauncherDestructor(String id, String type) {
//		A.addLauncherDestructor(id, type);
//		fireAddLauncherDestructorEvent();
//	}
//	
//	private void fireAddMissileDestructorEvent() {
//		for (WarModelEventsListener l : allListeners) {
//			l.addMissleDestructorInModel();
//		}
//	}
//	
//	public void addLauncherDestructor(String type) {
//		A.addLauncherDestructor(type);
//		fireAddLauncherDestructorEvent();
//	}
//	
//	private void fireAddLauncherDestructorEvent() {
//		for (WarModelEventsListener l : allListeners) {
//			l.addLauncherDestructorInModel();
//		}
//	}
//	
//	//destruct
//	public void destructLauncher(String id) {
//		//israel.destructLauncher();
//		fireDestructLuncherEvent();
//	}
//
//	private void fireDestructLuncherEvent() {
//		for (WarModelEventsListener l : allListeners) {
//			l.destructLuncherInModel();
//		}
//	}
//
//	public void destructMissile() {
//		A.destructMissile();
//		fireDestructMissileEvent();
//	}
//
//	private void fireDestructMissileEvent() {
//		for (WarModelEventsListener l : allListeners) {
//			l.destructMissileInModel();
//		}
////	}
//	
//
//	}

	
	// private void fireNotificationFailedAdding..(String message) {
	// for (GameModelEventsListener g : allListeners) {
	// g.notifyFailedAdding...InModel(message);
	

