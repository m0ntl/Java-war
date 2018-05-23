package Logging;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import bl.LauncherDestructor;
import bl.MissileDestructor;
import bl.MissileLauncher;


public class WarLogsGenerator{

	private static Logger			logger;
	private static ConsoleHandler	theConsoleHandler;

	public WarLogsGenerator(){
		logger = Logger.getLogger("warLogger");
		logger.setUseParentHandlers(false);
		theConsoleHandler = new ConsoleHandler();		
		theConsoleHandler.setFormatter(new WarFormatter()); 
		logger.addHandler(theConsoleHandler);
	}
	
	public void startLaunch(String destination, String launcherID){
		logger.info("launcher #" + launcherID + " just launched a missile to " + destination);
	}
	
	public void endLaunch(String destination, int damage, int flightTime, boolean success, MissileLauncher l){
		// need to add time of launch + time of landing !!!
		String msg = "";
		if ( success )
			msg += "Missile hit " + destination + " after " + flightTime + " seconds! \ndemage is " +  damage;
		else
			msg += "Missile got destructed after " + flightTime + " seconds";
		
		logger.log(Level.INFO, msg, l);
	}
	
	public void startMissileDestruct(){
		//logger.info("launcher #" + launcherID + " just launched a missile to " + destination);
	}
	
	public void afterMissileDestruct(MissileDestructor d, String launcherID , boolean success){
		String msg = "Destructor tried to destruct launcher #" + launcherID;
		if ( success )
			msg += ", and succceeded!";
		else
			msg += ", and filed";
		
		logger.log(Level.INFO, msg, d);
	}
	
	public void afterLauncherDestruct(MissileDestructor d, String missileID , boolean success, int damage){
		String msg = "Destructor tried to destruct missile #" + missileID;
		if ( success )
			msg += ", and succceeded!";
		else
			msg += ", and filed.\n damage caused by the missile is " + damage;
		logger.log(Level.INFO, msg, d);
	}
	
	public void statistics(String statistics){
		logger.info(statistics);
	}
	
	public void addLauncher( MissileLauncher l ){
		logger.info("missile launcher #" + l.getID() + ", hidden: " + l.isHidden()+ ", was added !");
		addHandler(l, l.getClass().getSimpleName() , l.getID());	
	}
	
	public void addMissileDestructor(MissileDestructor d){
		addHandler(d, d.getClass().getSimpleName() , d.getID());	

	}
	
	public void addLauncherDestructor(LauncherDestructor d){
		addHandler(d, d.getClass().getSimpleName(), d.getID());	
	}
	
	private void addHandler(Object obj, String className, String id){
		FileHandler launcherHandler;
		try {
			launcherHandler = new FileHandler(className +"_" + id + ".txt");
			launcherHandler.setFilter(new ObjectFilter(obj));
			launcherHandler.setFormatter(new WarFormatter()); 
			logger.addHandler(launcherHandler);
		
		} catch (SecurityException e) { e.printStackTrace(); 
		} catch (IOException e) { e.printStackTrace(); }
	}

}
