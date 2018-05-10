package bl;

import java.util.LinkedList;
import java.util.Queue;

public class MissileLauncher implements Runnable, BLConstants{

	private String 			id;		
	
	private SideB 			side;

	private boolean			inWar;
	private boolean			isAlive;
	private boolean			waitingForMissile;
		
	private Queue<Missile>	missilesToLaunch = new LinkedList<>();
			
	
	public MissileLauncher(String id, SideB side){
		this.id = id;
		this.side = side;
		
		waitingForMissile = false;
		inWar = true;
		isAlive = true;
	}
		
	public synchronized void addMissileToLaunchQueue(Missile m){
		missilesToLaunch.add( m );

		synchronized (this) {
			if ( waitingForMissile )
				notify();
		}
	}

	
	/* missile-launch */
	public void run() {
		while ( inWar ) {
			if ( !missilesToLaunch.isEmpty() )
				prepareToLaunch();
			else {
				synchronized (MissileLauncher.this) {
					try {
						waitingForMissile = true;
						wait(); 
						waitingForMissile = false;
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		}
	}
	
	private synchronized void prepareToLaunch(){
		Missile m = missilesToLaunch.poll();
		if (m == null) 
			return;
		
		side.onStartOfLaunch(id, m.getID(), m.getDestination());
		launchMissile(m);
		updateResults(m);				
	}

	public void launchMissile(Missile m) {		
		m.start();
		try {
			//wait until missile finishes flying
			wait(); 	
		} catch (InterruptedException e) { e.printStackTrace(); }
 	}

	public void updateResults(Missile m){
	
		boolean success = !m.isDestructed();
		String destination = m.getDestination();
		int damage = m.getDamage();
		int flightTime = m.getFlyTime();
		
		//notify missile that launcher finished getting the information
		synchronized (m) {
			m.notify();
		}
		
		side.onEndOfLaunch(this, success, destination, damage, flightTime);
	}

	
	/* stop thread */
	public void destructLauncher(){
		isAlive = false;
		terminate();
	}
		
	public void terminate(){
		inWar = false;
		synchronized (this) {
			if ( waitingForMissile )
				notify();
		}
	}

	
	/* getters */
	public String getID(){
		return id;
	}

	public boolean isAlive(){
		return isAlive;
	}
	
	public boolean isHidden(){
		return false;
	}

	
	/* hashCode & equals */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MissileLauncher other = (MissileLauncher) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}