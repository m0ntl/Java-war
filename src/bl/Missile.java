package bl;


public class Missile extends Thread implements BLConstants{

	private String 			id;
	private String			destination;
	
	private int 			flyTime;
	private int 			damage;
	private int 			potentialDamage;
	
	private boolean			isDone;
	private boolean			isDestructed;
	
	private MissileLauncher	launcher;
	
	
	public MissileLauncher getLauncher() {
		return launcher;
	}


	public void setLauncher(MissileLauncher launcher) {
		this.launcher = launcher;
	}


	public Missile(String id, int potentialDamage, String destination,int flyTime, MissileLauncher launcher){
		this.id = id;
		this.potentialDamage = potentialDamage;
		this.destination = destination;
		this.flyTime = flyTime;
		this.launcher = launcher;
		
		damage = 0;
		isDestructed = false;
		isDone = false;
	}

	
	/* missile launch */
	public synchronized void run(){		
		fly();
		
		// wait for launcher to get the launch results
		try {
			wait();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
 	
	public void fly(){		
		try {
			Thread.sleep( flyTime * ONE_SEC );
		} catch (InterruptedException e) {e.printStackTrace();}
		
		if ( ! isDestructed ){
			isDone = true;
			damage = potentialDamage;	
		}
				
		//notify launcher that flight finished
		synchronized (launcher) {
			launcher.notify();
		}
	}
	
	public void destructMissile(){
		if ( !isDone )
			isDestructed = true;
	}
	
	
	/* getters */
	public String getDestination() {
		return destination;
	}
	
	public String getID(){
		return id;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getFlyTime(){
		return flyTime;
	}
	
	public boolean isDestructed() {
		return isDestructed;
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
		Missile other = (Missile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public boolean isDone() {
		return isDone;
	}

}
