package bl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import Logging.WarLogsGenerator;
import mvc.WarModelEventsListener;

public class WarModel implements IWar, BLConstants {
	
	private SideB 							B;

	private WarLogsGenerator				logsGen = new WarLogsGenerator();

	private Vector<WarModelEventsListener>	allListeners = new Vector<WarModelEventsListener>();
	
	public WarModel() {
		B = new SideB(this); 
	}

	public void registerListener(WarModelEventsListener listener) {
		allListeners.add(listener);
	}
	
	/* missile-launcher */
	public void addMissileLauncher(String id, boolean isHidden) {
		MissileLauncher l;
		if ( isHidden )
			l = new HiddenMissileLauncher(id, B);
		else
			l = new MissileLauncher(id, B);
		
		B.addLauncher(l);
		logsGen.addLauncher(l);
		fireAddMissileLuncherEvent(id, isHidden);	
	}

	public void addMissileToLaunch(String launcherId, String id, int damage, String destination, int flyTime, int launchTime) {
		new Timer().schedule( new TimerTask() {
			@Override
			public void run() {
				WarModel.this.addLaunch(launcherId, id, damage, destination, flyTime);
			} 
		}, launchTime * ONE_SEC );
	}
	
	public void addLaunch(String launcherID,String missileID, int potentialDamage, String destination, int flyTime) {
		B.addMissileToLaunchQueue(launcherID, missileID, potentialDamage, destination, flyTime);
	}
	
	public synchronized void onStartOfLaunch(String launcherID, String missileID, String destination){
		logsGen.startLaunch(destination, launcherID);
		fireLaunchMissileEvent(launcherID, missileID, destination);
	}
	
	public synchronized void onEndOfLaunch(MissileLauncher l, boolean success, String destination, int damage, int flightTime){
		logsGen.endLaunch(destination, damage, flightTime, success, l);
		// fire..
	}
	
	/* --- general --- */
	public void statistics(){
		// need to add : destructedLaunchers, destructedMissiles 1
		
		String s = "total damage: " + B.getTotalDamage()
				+ "\nlaunched missiles: " + B.getLaunchedMissiles()
				+ "\nmissile hits: " + B.getHits();

		logsGen.statistics(s);
		fireStatisticsEvent(s);
	}
	
	public void endWar(){
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
	
	public void fireLaunchMissileEvent(String launcherId, String missileID, String destination) {
		for (WarModelEventsListener l : allListeners) {
			l.launchMissileInModel(launcherId); 
		}
	}

	private void fireStatisticsEvent(String s){
		for (WarModelEventsListener l : allListeners) 
			l.statisticsInModel(s);
	}
	
	private void fireExitEvent() {
		for (WarModelEventsListener l : allListeners)
			l.exitInModel();
	}
	
	/* --- getters --- */
	public int getLaunchersNum(){
		return getLaunchersNum();
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
