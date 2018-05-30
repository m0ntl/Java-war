package program;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import bl.Missile;
import bl.MissileLauncher;
import bl.WarModel;
/*
 * Main class to run the program and get input from console/GUI
 * 
 * TODO:
 * 	1. Re-factor when a missile is created
 * 	2. Re-factor war object to decide the damage of a missile
 * 
 */
public class Program implements bl.BLConstants{

	static Scanner s = new Scanner (System.in);
	public static WarModel war = new WarModel();
	
	warScheduler ws = new warScheduler(this);

	public static void main(String[] args) {
		readFromConfigFile(war);					
		//chooseOptions(war);
	}

	/* menu */
	public static void chooseOptions( WarModel war ) {
		boolean exit = false;
		int ans;

		System.out.println(MAIN_MENU_STR);
		
		while( !exit ){
			ans = s.nextInt();
			switch( ans ) {
			case ADD_LAUNCHER_DESTRUCTOR:
				 
				break;
			case ADD_MISSILE_DESTRUCTOR:
				 
				break;
			case ADD_LAUNCHER:
				addLauncherMenu(war);
				break;
			case LAUNCH_MISSILE:
				launchMissileMenu(war);
				break;
			case DESTRUCT_MISSILE:
				 
				break;
			case DESTRUCT_LAUNCHER:
				 
				break;
			case SHOW_STATISTICS:
				war.statistics();
				break;
			case EXIT:
				war.exit();
				System.out.println("Bye bye");
				exit = true;
				break;
			}
		}
		s.close();	
	}
	
	private static void addLauncherMenu(WarModel war){
		System.out.println(ENTER_LAUNCHER_ID);
		String id = s.next();
		System.out.println(ENTER_IS_LAUNCHER_HIDDEN);
		int hidden = s.nextInt();
		boolean isHidden = (hidden == YES);
		
		war.addMissileLauncher(id, isHidden);
	}
	
	private static void launchMissileMenu(WarModel war){
		System.out.println(ENTER_LAUNCHER_ID);
		String launcherID = s.next();
		System.out.println(ENTER_MISSILE_ID);
		String missileID= s.next();
		System.out.println(ENTER_POTENTIAL_DAMAGE);
		int potentialDamage = s.nextInt();
		System.out.println(ENTER_DESTINATION);
		String destination = s.next();
		System.out.println(ENTER_FLIGHT_TIME);
		int flyTime = s.nextInt();
		
		war.addLaunch(launcherID, missileID, potentialDamage, destination, flyTime);
	}


	/* configuration file related */
	private static void readFromConfigFile(WarModel war){
		
		// ########################## COMMENTED FROM TESTING ########################## 
//		System.out.println(LOAD_FROM_CONFIG);
//		if ( s.nextInt() != YES )
//			return;
		
		readLaunchers(); //Done
		//readMissileDestructors(); //Done
		//readMissileLauncherDestructors(); //Done
	}
	
	private static void readLaunchers(){
		JSONArray missileLaunchers = (JSONArray) jsonParser.returnSubObject(new String[] {"missileLaunchers", "launcher"});
		Iterator<JSONObject> iterator = missileLaunchers.iterator();
		
		while (iterator.hasNext()) {
			JSONObject launcher = iterator.next();
			String id = (String) launcher.get("id");
			boolean isHidden = ((String) launcher.get("isHidden")) == "true";
			
			war.addMissileLauncher(id, isHidden);
			
			readMissilesToLaunch((JSONObject)launcher, war);
		}
	}
	
	private static void readMissilesToLaunch(JSONObject launcher, WarModel war){	
		Map<String, Integer> missilesList = new LinkedHashMap<>();
		
		if(launcher.get("missile") instanceof JSONArray){
			JSONArray missiles = (JSONArray) launcher.get("missile");
			Iterator<JSONObject> itr = missiles.iterator();
	
			while (itr.hasNext()){
				JSONObject missile = itr.next();
				Missile m = getMissileDetails(missile, war.getMissileLauncherByID((String) launcher.get("id")));
				
				missilesList.put(m.getID(), Integer.parseInt((String) missile.get("launchTime")));
				//war.addMissileToLaunch(launcherId, id, damage, destination, flyTime, launchTime);
			}
		} else {
			JSONObject missile = (JSONObject) launcher.get("missile");
			Missile m = getMissileDetails(missile, war.getMissileLauncherByID((String) launcher.get("id")));
			
			missilesList.put(m.getID(), Integer.parseInt((String) missile.get("launchTime")));
			//war.addMissileToLaunch(launcherId, id, damage, destination, flyTime, launchTime);
		}
		warScheduler.MissileLaunch(missilesList);
	}
	
	private static Missile getMissileDetails(JSONObject missile, MissileLauncher ml) {
		return new Missile(
				(String) missile.get("id"), 
				Integer.parseInt( (String) missile.get("damage") ), 
				(String) missile.get("destination"), 
				Integer.parseInt( (String) missile.get("flyTime") ), 
				ml);
	}
	
	
	private static void readMissileDestructors() {
		JSONArray missileDestructors = (JSONArray)jsonParser.returnSubObject(new String[] {"missileDestructors", "destructor"});
		Iterator<JSONObject> iterator = missileDestructors.iterator();
		
		while (iterator.hasNext()) {
			JSONObject destructor = iterator.next();
			String id = (String)destructor.get("id");
			
			scheduleDestructMissile((JSONObject) destructor, id);
		}
	}
	
	private static void readMissileLauncherDestructors() {
		JSONArray missileLauncherDestructors = (JSONArray)jsonParser.returnSubObject(new String[] {"missileLauncherDestructors", "destructor"});
		Iterator<JSONObject> iterator = missileLauncherDestructors.iterator();
		
		while (iterator.hasNext()) {
			JSONObject mlDestructor = iterator.next();
			String type = (String) mlDestructor.get("type");
			
			scheduleDestructLanucher((JSONObject) mlDestructor);
		}
	}
	
	private static void scheduleDestructLanucher(JSONObject destructorJsonObject){
		//Define map to hold all the destructors to scheduale a launch for
		Map<String, Integer> launcherDestructors = new LinkedHashMap<>();
		
		if(destructorJsonObject.get("destructedLanucher") instanceof JSONArray){
			JSONArray destructedLaunchers = (JSONArray) destructorJsonObject.get("destructedLanucher");
			Iterator<JSONObject> itr = destructedLaunchers.iterator();
			
			while (itr.hasNext()){
				JSONObject target = itr.next();
				launcherDestructors.put((String) target.get("id"), Integer.parseInt((String) target.get("destructTime")));
			}
		} else {
			JSONObject target = (JSONObject) destructorJsonObject.get("destructedLanucher");
			launcherDestructors.put((String) target.get("id"), Integer.parseInt((String) target.get("destructTime")));
		}
		warScheduler.LDLaunch(launcherDestructors);
	}
	
	
	private static void scheduleDestructMissile(JSONObject destructorJsonObject, String destructorID){
		//Define map to hold all the destructors to scheduale a launch for
		Map<String, Integer> missileDestructors = new LinkedHashMap<>();
		
		if(destructorJsonObject.get("destructdMissile") instanceof JSONArray){
			JSONArray missiles = (JSONArray) destructorJsonObject.get("destructdMissile");
			Iterator<JSONObject> itr = missiles.iterator();
			
			while (itr.hasNext()){
				JSONObject missile = itr.next();
				missileDestructors.put((String) missile.get("id"), Integer.parseInt((String) missile.get("destructAfterLaunch")));
				itr.remove(); // avoids a ConcurrentModificationException
			}
		} else {
			JSONObject missile = (JSONObject) destructorJsonObject.get("destructdMissile");
			missileDestructors.put((String) missile.get("id"), Integer.parseInt((String) missile.get("destructAfterLaunch")));
		}
		
		//Call scheduler with all missiles
		warScheduler.MDLaunch(missileDestructors);
	}
	
	public static void destructLauncher(String launcherID) {
		System.out.println("Log: destructing launcher with id: " + launcherID);
		war.destructLauncher(launcherID);
	}
	
	public static void destructMissile(String missileID){
		System.out.println("Log: destructing missile with id: " + missileID);
		war.destructMissile(missileID);
	}
	
	public static void launchMissile(String missileID) {
		System.out.println("Log: launching missile with id: " + missileID);
		war.launchMissile(missileID);
	}
}

