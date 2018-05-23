package bl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * This class receives a map of objects with the properties and times
 * and schedules launches at the correct time.
 */

public class warScheduler {
	
	static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	final static List<MissileDestructor> launchList = new ArrayList<MissileDestructor>();

	//Map for holding MissileDestructor items to schedule
	final static Map<String, Integer> missiles = new LinkedHashMap<>();
	final static Map<String, Integer> destructors = new LinkedHashMap<>();
	
	public warScheduler(){
	}
	
	public static void scheduleMissileLaunch(Map<String, Integer> list) {
		//Sort the list by launch time
		list = sortByValue(list);
		
		//Copy list to static map for scheduling
		Iterator it = list.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        missiles.put((String)pair.getKey(), (Integer)pair.getValue());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
	    for (Map.Entry<String, Integer> entry : missiles.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
			scheduleLaunch((Integer)entry.getValue());
		}
	}
	
	private static void scheduleLaunch(int time) {
		ScheduledFuture<Object> scheduledFuture = scheduledExecutorService.schedule(new Callable<Object>() {
	        public Object call() throws Exception {
	        	//Print first element for debugging
	        	System.out.print("Time to launch: ");
	            System.out.println(missiles.entrySet().iterator().next());
	            //remove first element
	            missiles.remove(missiles.entrySet().iterator().next().getKey());
	            return "Complete";
	        }
	    },
		time,
		TimeUnit.SECONDS);
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
