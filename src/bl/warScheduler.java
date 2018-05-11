package bl;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class warScheduler {
	public static void scheduleJob() {
		// Say Hello to the World and display the date/time
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    
	    Date runTime = evenMinuteDate(new Date());
	    
	    JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();

	    // Trigger the job to run on the next round minute
	    Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

	    // Tell quartz to schedule the job using our trigger
	    sched.scheduleJob(job, trigger);
	    
	    sched.start();
	    
	    try {
	        // wait 65 seconds to show job
	        Thread.sleep(65L * 1000L);
	        // executing...
	    } catch (Exception e) {
	        //
	    }
	    
	    sched.shutdown(true);
	}
}
