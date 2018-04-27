package bl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import mvc.WarModelEventsListener;

public class War implements BLConstants{
	
	private SideA 							israel;
	private SideB 							otherCountry;
	
	private Timer							timer;
	private int								time;
	
	private Vector<WarModelEventsListener>	allListeners = new Vector<WarModelEventsListener>();
		
	public War() {
	
		israel = new SideA();
		otherCountry = new SideB();
		
		israel.setEnemy(otherCountry);
		otherCountry.setEnemy(israel);
		
		timer = new Timer("Timer");
		timer.schedule(new TimerTask() {
	        public void run() {
	        	doTimerTask();
	        }
	    }, 1000L);
	
	}

	public void doTimerTask(){
		time++;
	}
	
	public void registerListener(WarModelEventsListener listener) {
		allListeners.add(listener);
	}

	private boolean isWarOver(){
		//temp
		return false;
	}
	
	//add
	public void addMissileLauncher(String id) {
		otherCountry.addMissileLauncher(id);
		fireAddMissileLuncherEvent();
	}

	public void addMissileDestructor(String id) {
		israel.addMissileDestructor(id);
		fireAddMissileDestructorEvent();
	}

	public void addLauncherDestructor(String id, int type) {
		israel.addLauncherDestructor(id, type);
		fireAddLauncherDestructorEvent();
	}

	//launch
	public void launchMissile(String idLauncher, String idMissile, int potentialDamage, String destination, int flyTime) {
		int damage = otherCountry.launchMissile(idLauncher, idMissile, potentialDamage, destination, flyTime, time);
		fireLaunchMissileEvent( damage );
	}

	//destruct
	public void destructLauncher(String id) {
		//israel.destructLauncher();
		fireDestructLuncherEvent();
	}

	public void destructMissile() {
		israel.destructMissile();
		fireDestructMissileEvent();
	}

	public void destructLauncherDestructor() {
		otherCountry.destructLauncherDestructor();
		fireDestructLauncherDestructorEvent();
	}

	public void sumUp() {

		int totalDamage = israel.getTotalDamage();
		int launchedMissiles = otherCountry.getLaunchedMissiles();
		int destructedMissiles = otherCountry.getDestructedMissiles();
		int destructedLaunchers = otherCountry.getDestructedLaunchers();
		int missileHits = otherCountry.getMissileHits();
		
		fireSumUpEvent(totalDamage, launchedMissiles, destructedMissiles, destructedLaunchers, missileHits);
	}
	
	public void exit() {
		sumUp();
		fireExitEvent();
	}

	// fire events ( to ui )
	private void fireAddMissileLuncherEvent() {
		for (WarModelEventsListener g : allListeners) {
			g.addMissileLauncherInModel();
		}
	}

	private void fireAddMissileDestructorEvent() {
		for (WarModelEventsListener g : allListeners) {
			g.addMissileDestructorInModel();
		}
	}

	private void fireAddLauncherDestructorEvent() {
		for (WarModelEventsListener g : allListeners) {
			g.addLauncherDestructorInModel();
		}
	}

	private void fireLaunchMissileEvent( int damage ) {
		for (WarModelEventsListener l : allListeners) {
			l.launchMissileInModel( damage );
		}
	}

	private void fireDestructLuncherEvent() {
		for (WarModelEventsListener g : allListeners) {
			g.destructLuncherInModel();
		}
	}

	private void fireDestructMissileEvent() {
		for (WarModelEventsListener g : allListeners) {
			g.destructMissileInModel();
		}
	}

	private void fireDestructLauncherDestructorEvent() {
		for (WarModelEventsListener g : allListeners) {
			g.destructLauncherDestructorInModel();
		}
	}

	private void fireSumUpEvent( int totalDamage, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int missileHits  ) {
		for (WarModelEventsListener l : allListeners) {
			l.sumUpInModel(totalDamage, launchedMissiles, destructedMissiles, destructedLaunchers, missileHits );
		}
	}

	private void fireExitEvent() {
		for (WarModelEventsListener g : allListeners) {
			g.exitInModel();
		}
	}
	
	// private void fireNotificationFailedAdding..(String message) {
	// for (GameModelEventsListener g : allListeners) {
	// g.notifyFailedAdding...InModel(message);
	
}
