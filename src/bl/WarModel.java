package bl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import Logging.WarLogsGenerator;
import mvc.WarModelEventsListener;
import program.warScheduler;

public class WarModel implements IWar, BLConstants {
	
	private SideA 							A;
	private SideB 							B;
	
	private WarLogsGenerator				logsGen = new WarLogsGenerator();
	private Vector<WarModelEventsListener>	allListeners = new Vector<WarModelEventsListener>();
	
	//warScheduler.scheduleMissileLaunch(null);
	warScheduler ws = new warScheduler();
	
	public void setMissileScheduler() {
		warScheduler.scheduleMissileLaunch(null);
	}
	public MissileLauncher getMissileLauncherByID(String id) {
		return B.getLauncherById(id);
	}
	private void addMissile(String id, int potentialDamage, String destination, int flyTime, MissileLauncher ml){
		Missile m = new Missile(id, potentialDamage, destination, flyTime, ml);
	}
	
	public void setDestructorScheduler() {
		warScheduler.scheduleMissileLaunch(null);
	}
	
	public WarModel() {
		B = new SideB(this); 
	}

	public void registerListener(WarModelEventsListener listener) {
		allListeners.add(listener);
	}
	
	public void addMissileLaunch(String missileID) {
		B.launchMissileByID(missileID);
	}
	
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

	public void addMissileDestructor(){
		//A.addDestructor
		//logsGen.addMissileDestructor(MissileDestructor d)
		//fireAddMissileDestructorEvent(id);
	}
	
	public void addLauncherDestructor(){
		//A.addDestructor..
		//logsGen.addLauncherDestructor(MissileDestructor d)
		fireAddLauncherDestructorEvent();
	}
	
	//Change function to only take the launcher id
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
	
	public void onStartOfMissileDestruct(){
		//logsGen.startMissileDestruct();
		fireDestructMissileEvent();
	}

	public void onEndOfMissileDestruct(){
		
	}
	
	public void onStartOfLauncherDestruct(){
		
	}

	public void onEndOfLauncherDestruct(){
		
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

	
	// fire to controller 
	private void fireAddMissileLuncherEvent(String id, boolean isHidden) {
		for (WarModelEventsListener l : allListeners) 
			l.addMissileLauncherInModel(id, isHidden);
	}
	
	public void fireLaunchMissileEvent(String launcherId, String missileID, String destination) {
		for (WarModelEventsListener l : allListeners) {
			l.launchMissileInModel(launcherId); 
		}
	}
	
	private void fireAddMissileDestructorEvent(String id) {
		for (WarModelEventsListener l : allListeners) {
			l.addMissileDestructorInModel(id);
		}
	}
	
	private void fireAddLauncherDestructorEvent() {
	for (WarModelEventsListener l : allListeners) {
		l.addLauncherDestructorInModel();
	}
}
	
	private void fireDestructMissileEvent() {
		for (WarModelEventsListener l : allListeners) {
			l.destructMissileInModel();
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
	
	// getters
	public int getLaunchersNum(){
		return getLaunchersNum();
	}
	
	public boolean destructLauncher(String launcherID) {
		MissileLauncher targetML = B.getLauncherById(launcherID);
		if ( !targetML.isHidden()){
			B.destructLauncher(targetML);
			return true;
		}
		return false;
	}
	
	public boolean destructMissile(String missileID) {
		Missile targetMissile = B.getMissileById(missileID);
		if ((!targetMissile.isDestructed()) && targetMissile.isAlive()) {
			//If target missile thread is alive & isn't destructed.
			//No need to check if missile is done, performed by the missile.
			B.destructMissile(targetMissile);
			return true;
		}
		return false;
	}
}
