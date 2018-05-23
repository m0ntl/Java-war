package bl;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import UI.App;
import mvc.WarController;

public class Program implements BLConstants{

	static Scanner s = new Scanner (System.in);
	public static WarModel war = new WarModel();
	
	public static void main(String[] args) {
		
		readFromConfigFile(war);	
						
		chooseOptions(war);
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
		boolean isHidden = false;
		if ( hidden == YES )
			isHidden = true;
		
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
		
		System.out.println(LOAD_FROM_CONFIG);
		if ( s.nextInt() != YES )
			return;
		
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(CONFIGURATION_FILE));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONObject theWar = (JSONObject) jsonObject.get("war");
			
			readLaunchers(theWar, war);
		} catch (IOException | ParseException e) {e.printStackTrace();}
	}

	private static void readLaunchers(JSONObject theWar, WarModel war){		
		JSONObject missileLaunchers = (JSONObject) theWar.get("missileLaunchers");
		JSONArray launchers = (JSONArray) missileLaunchers.get("launcher");
		Iterator<JSONObject> iterator = launchers.iterator();
		
		while (iterator.hasNext()) {
			JSONObject launcher = iterator.next();
			String id = (String) launcher.get("id");
			String hidden = (String) launcher.get("isHidden");
			boolean isHidden = false;
			if ( hidden.equals("true") )
				isHidden = true;
			war.addMissileLauncher(id, isHidden);
			
			readMissilesToLaunch(launcher, id, war);
		}
}

	private static void readMissilesToLaunch(JSONObject launcher, String launcherId, WarModel war){	
		JSONArray missiles = (JSONArray) launcher.get("missile");
		Iterator<JSONObject> itr = missiles.iterator();
	
		while (itr.hasNext()){
			JSONObject missile = itr.next();
			String id = (String) missile.get("id");
			String destination = (String) missile.get("destination");
			int launchTime = Integer.parseInt( (String) missile.get("launchTime") );
			int flyTime = Integer.parseInt( (String) missile.get("flyTime") );
			int damage = Integer.parseInt( (String) missile.get("damage") );
		
			//war.addMissileToLaunch(launcherId, id, damage, destination, flyTime, launchTime);
		}
	}
}

//private void readMissileDestructors(JSONObject theWar){
//
//	JSONObject missileDestructors = (JSONObject) theWar.get("missileDestructors");
//	JSONArray destructors = (JSONArray) missileDestructors.get("destructor");
//	Iterator<JSONObject> iterator = destructors.iterator();
//	while (iterator.hasNext()) {
//		JSONObject destructor = iterator.next();
//		String id = (String) destructor.get("id");
//		
//		// .. do stuff with the data 
//		
//		readMissilesToDestruct(destructor, id);
//	}
//}
//	
//private void readMissilesToDestruct(JSONObject destructor, String destructorId){
//	JSONArray destructdMissile = (JSONArray) destructor.get("destructdMissile");
//	Iterator<JSONObject> itr = destructdMissile.iterator();
//	while (itr.hasNext()){
//		JSONObject missile = itr.next();
//		String id = (String) missile.get("id");
//		int destructAfterLaunch = (int) missile.get("destructAfterLaunch");
//		
//		// .. do stuff with the data 
//	}
//}
//
//private void readLauncherDestructors( JSONObject theWar ){
//
//	JSONObject missileLauncherDestructors = (JSONObject) theWar.get("missileLauncherDestructors");
//	JSONArray destructors = (JSONArray) missileLauncherDestructors.get("destructor");
//	Iterator<JSONObject> iterator = destructors.iterator();
//	while (iterator.hasNext()) {
//		JSONObject destructor = iterator.next();
//		String type = (String) destructor.get("type");
//		
//		// .. do stuff with the data 
//		
//		readLaunchersToDestruct(destructor);
//	}
//}
//
//private void readLaunchersToDestruct(JSONObject destructor){
//	JSONArray destructedLanucher = (JSONArray) destructor.get("destructedLanucher");
//	Iterator<JSONObject> itr = destructedLanucher.iterator();
//	while (itr.hasNext()){
//		JSONObject launcher = itr.next();
//		String id = (String) launcher.get("id");
//		int destructTime = (int) launcher.get("destructTime");
//		
//		// .. do stuff with the data 
//	}
//}

