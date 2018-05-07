package bl;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class HiddenMissileLauncher extends MissileLauncher implements IHidden{

	private boolean		isHiddenNow; 
	private boolean		isLaunching; 

	
	public HiddenMissileLauncher(String id, Timer timer) {
		super(id, timer);
		isHiddenNow = true;
		isLaunching = false;
	}
	
	public synchronized void launchMissile(Missile m) {
		isLaunching = true;
		if( isHiddenNow )
			emerge();	
		
		super.launchMissile(m);
		
		isLaunching = false; 
		hide();
	}
	
	public synchronized void hide(){
		
		//takes X time to hide
		//if after X time launcher not launching missile -> hide
		new Timer().schedule(new TimerTask() {
			public void run() {
				if ( !isLaunching )
					isHiddenNow = true;
			}
		}, new Random().nextInt(MAX_TIME) );	
	}
	
	public void emerge(){
		isHiddenNow = false;
	}

	public void destructLauncher(){
		if ( !isHiddenNow )
			super.destructLauncher();
	}
}