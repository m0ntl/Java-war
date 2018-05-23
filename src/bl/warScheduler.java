package bl;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
<<<<<<< HEAD
	public static void scheduleJob() {
//		// Say Hello to the World and display the date/time
//	    SchedulerFactory sf = new StdSchedulerFactory();
//	    Scheduler sched = sf.getScheduler();
//	    
//	    Date runTime = evenMinuteDate(new Date());
//	    
//	    JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();
//
//	    // Trigger the job to run on the next round minute
//	    Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
//
//	    // Tell quartz to schedule the job using our trigger
//	    sched.scheduleJob(job, trigger);
//	    
//	    sched.start();
//	    
//	    try {
//	        // wait 65 seconds to show job
//	        Thread.sleep(65L * 1000L);
//	        // executing...
//	    } catch (Exception e) {
//	        //
//	    }
//	    
//	    sched.shutdown(true);
=======
	
	static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
	final static List<MissileDestructor> launchList = new ArrayList<MissileDestructor>();
	
	public warScheduler(){
		
	}
	
	public void scheduleMDLaunch(List<MissileDestructor> list) {
		for(MissileDestructor md : list) {
			//scheduleLaunch(Integer.parseInt( md.getID()), md);
			launchList.add(md);
			//scheduleLaunch();
		}
		for(MissileDestructor md : launchList) {
			//scheduleLaunch(Integer.parseInt( md.getID()), md);
			//launchList.add(md);
			scheduleLaunch(Integer.parseInt(md.getID()));
		}
>>>>>>> f58822c391353ddd54bea414818fd5fe3937f9b0
	}
	
	private static void scheduleLaunch(int ttl) {
		ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new Callable() {
	        public Object call() throws Exception {
	            System.out.println("Executed!");
	            System.out.println(launchList.get(0));
	            launchList.remove(0);
	            return "Complete";
	        }
	    },
		ttl+1,
		TimeUnit.SECONDS);
		//System.out.println(list.size());
		//list.remove(0);
	}	
}
