package bl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.quartz.JobExecutionException;

public class testProgram {
	
	public testProgram(){
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		// jsonParser jp = new jsonParser();
		//  System.out.println(jp.returnSubObject(new String[] {"missileDestructors", "destructor", "destructdMissile"}));
		// JSONParser parser = new JSONParser();
//		try {
		//warScheduler.scheduleJob();
//		} catch (JobExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		MissileDestructor md1 = new MissileDestructor("1");
		MissileDestructor md2 = new MissileDestructor("2");
		MissileDestructor md3 = new MissileDestructor("3");
		MissileDestructor md4 = new MissileDestructor("4");
		MissileDestructor md5 = new MissileDestructor("5");
		MissileDestructor md6 = new MissileDestructor("6");
		
		List<MissileDestructor> list = new ArrayList<>();
		list.add(md1);
		list.add(md2);
		list.add(md3);
		list.add(md4);
		list.add(md5);
		list.add(md6);
		
		warScheduler ws = new warScheduler();
		ws.scheduleMDLaunch(list);
//		scheduleLaunch(3, new MissileDestructor("1"));
	}
	
	
}
