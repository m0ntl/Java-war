package program;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import UI.App;
import bl.BLConstants;
import bl.Missile;
import bl.MissileLauncher;
import bl.WarModel;
import mvc.WarController;

public class Program implements bl.BLConstants{

	static Scanner s = new Scanner (System.in);
	public static WarModel war = new WarModel();
	
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
		
		// ########################## RETURN THIS STATEMENT - COMMENTED FROM TESTING ########################## 
//		System.out.println(LOAD_FROM_CONFIG);
//		if ( s.nextInt() != YES )
//			return;
		
		readLaunchers(); //Done - remember to add to schedule
		readMissileDestructors(); //Done - remember to add to schedule
		readMissileLauncherDestructors(); //Done - remember to add to schedule
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
		if(destructorJsonObject.get("destructedLanucher") instanceof JSONArray){
			JSONArray destructedLaunchers = (JSONArray) destructorJsonObject.get("destructedLanucher");
			Iterator<JSONObject> itr = destructedLaunchers.iterator();
			
			while (itr.hasNext()){
				JSONObject target = itr.next();
				scheduleDestructedLauncher(target);
			}
		} else {
			JSONObject target = (JSONObject) destructorJsonObject.get("destructedLanucher");
			scheduleDestructedLauncher(target);
		}
	}
	
	private static void scheduleDestructedLauncher(JSONObject destructorJsonObject){
		/*
		 * TODO:
		 * Instead of printing, schedule the missile launch!
		 */
//		System.out.println(destructorJsonObject);
//		System.out.println(destructorJsonObject.get("id"));
//		System.out.println(destructorJsonObject.get("destructTime"));
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
	
	private static void scheduleDestructMissile(JSONObject destructorJsonObject, String destructorID){
		if(destructorJsonObject.get("destructdMissile") instanceof JSONArray){
			JSONArray missiles = (JSONArray) destructorJsonObject.get("destructdMissile");
			Iterator<JSONObject> itr = missiles.iterator();
			
			while (itr.hasNext()){
				JSONObject missile = itr.next();
				scheduleMissile(missile);
			}
		} else
			scheduleMissile((JSONObject)destructorJsonObject.get("destructdMissile"));
	}
	
	private static void scheduleMissile(JSONObject missile) {
		/*
		 * TODO:
		 * Instead of printing, schedule the missile launch!
		 */
		//System.out.println(missile.get("id"));
		//System.out.println(missile.get("destructAfterLaunch"));
	}
	
	//private static void readLaunchers(JSONObject theWar, WarModel war){		
	private static void readLaunchers(){
		JSONArray missileLaunchers = (JSONArray) jsonParser.returnSubObject(new String[] {"missileLaunchers", "launcher"});
		Iterator<JSONObject> iterator = missileLaunchers.iterator();
		
		while (iterator.hasNext()) {
			JSONObject launcher = iterator.next();
			String id = (String) launcher.get("id");
			boolean isHidden = ((String) launcher.get("isHidden")) == "true";
			System.out.println(id);
			System.out.println(isHidden);
			
			war.addMissileLauncher(id, isHidden);
			
			readMissilesToLaunch((JSONObject)launcher, id, war);
		}
	}
	private static Missile getMissileDetails(JSONObject missile, MissileLauncher ml) {
		return new Missile(
				(String) missile.get("id"), 
				Integer.parseInt( (String) missile.get("damage") ), 
				(String) missile.get("destination"), 
				Integer.parseInt( (String) missile.get("flyTime") ), 
				ml);
	}
	private static void readMissilesToLaunch(JSONObject launcher, String launcherId, WarModel war){	
		if(launcher.get("missile") instanceof JSONArray){
			JSONArray missiles = (JSONArray) launcher.get("missile");
			Iterator<JSONObject> itr = missiles.iterator();
	
			while (itr.hasNext()){
				JSONObject missile = itr.next();
				Missile m = getMissileDetails(missile, war.getMissileLauncherByID(launcherId));
				System.out.println("Mine");
				System.out.println(m);
				
				// ################ Add missile launch to schedule ################
				//war.addMissileToLaunch(launcherId, id, damage, destination, flyTime, launchTime);
			}
		} else {
			JSONObject missile = (JSONObject) launcher.get("missile");
			Missile m = getMissileDetails(missile, war.getMissileLauncherByID(launcherId));
			
			// ################ Add missile launch to schedule ################
			//war.addMissileToLaunch(launcherId, id, damage, destination, flyTime, launchTime);
		}
	}
	
	public void destructLauncher(String launcherID) {
		war.destructLauncher(launcherID);
	}
	
	public void destructMissile(String missileID){
		war.destructMissile(missileID);
	}
}

