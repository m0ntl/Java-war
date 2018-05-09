package bl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

 

public class testProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jsonParser jp = new jsonParser();
		System.out.println(jp.returnSubObject("configFile.json", new String[] {"missileDestructors", "destructor", "destructdMissile"}));
	}

}
