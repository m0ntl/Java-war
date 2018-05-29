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
}
