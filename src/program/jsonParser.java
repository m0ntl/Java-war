package program;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import bl.BLConstants;
import bl.MissileDestructor;

/*
TODO:
	1. Complete returnSubObject method to work with array
	2. Add instead of the chained method calling in getMissileDestructors
	3. Make generic (to work with all objects in the json)
	4. Add file name as global variable
*/

public class jsonParser {
	
	public static Object returnSubObject(String[] objectPath){
		JSONParser parser = new JSONParser();		
		Object o = null;
		try {
			Object obj = parser.parse(new FileReader(BLConstants.CONFIGURATION_FILE));
	        JSONObject jsonObject = (JSONObject) obj;
	        o = (JSONObject) jsonObject.get("war");
	        if(objectPath[0] != "")
				for(String path : objectPath){
					System.out.println("I'm here");
					if(o instanceof JSONObject)
						o = ((JSONObject) o).get(path);
					else if(o instanceof JSONArray)
						//Cannot parse array, return array to caller for parsing
						break;
				}
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }
		
		return o;
	}
	
	public List<MissileDestructor> getMissileDestructors(String fileName){
		List<MissileDestructor> list = new ArrayList<MissileDestructor>();
		
		//Define new parser
		JSONParser parser = new JSONParser();		
		try {
	        Object obj = parser.parse(new FileReader("conf_demo.json"));
	        JSONObject jsonObject = (JSONObject) obj;
	        
	        JSONArray missileDestructors = (JSONArray) returnSubObject(new String[] {"missileDestructors", "destructor"});
	        
	        //iterate over missile destructors
	        Iterator<JSONObject> iterator = missileDestructors.iterator();
	        while (iterator.hasNext()) {
	        	JSONObject missileDistructor =  (JSONObject)iterator.next();
	        	MissileDestructor md = new MissileDestructor((String) missileDistructor.get("id"));

	        	//Define object because we don't know if it's an array or jsonobject yet
	        	Object destructdMissiles;
	        	
	        	if(missileDistructor.get("destructdMissile") instanceof JSONArray){
	        		//If it's an array, iterate over it and create each missile
	        		JSONArray missiles = (JSONArray) missileDistructor.get("destructdMissile");
	        		Iterator<JSONObject> missileIterator = missiles.iterator();
	                while (missileIterator.hasNext()) {
	                	JSONObject missile =  (JSONObject)missileIterator.next();
	                	md.destructMissle((String)missile.get("id"), 3);
	                }
	                
	        		destructdMissiles = (JSONArray) missileDistructor.get("destructdMissile");
	        	} else if(missileDistructor.get("destructdMissile") instanceof JSONObject){
	        		destructdMissiles = (JSONObject) missileDistructor.get("destructdMissile");
	        		md.destructMissle((String)((JSONObject)destructdMissiles).get("id"), 3);
	        	} else {
	        		throw new IllegalArgumentException("Unable to identify incoming JSON object");
	        	}
	        	list.add(md);
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }
		return list;
	}
}
