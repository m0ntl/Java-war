package bl;

public class Missile {

	private String 	id;
	private String 	destination;
	private int 	potentialDamage;
	private int 	damage;
	private int 	flyTime;
	private int 	launchTime;
	private boolean	isDestructed;
	
	public Missile(String id, int potentialDamage, String destination, int flyTime, int launchTime){
		this.id = id;
		this.potentialDamage = potentialDamage;
		this.destination = destination;
		this.flyTime = flyTime;
		this.launchTime = launchTime;
		this.isDestructed = false;
		this.damage = 0;
	}
	
	public String getId(){
		return id;
	}
	
	public void launch(){
		//...
		
		try {
			wait(flyTime);
		} catch (InterruptedException e) { System.out.println( e.getStackTrace() ); }
		
		if ( !isDestructed )
			damage = potentialDamage;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public boolean isDestructed(){
		return isDestructed;
	}
	
	public boolean isDestructed(int time){
		if ( launchTime+flyTime <= time )
			isDestructed = true;
		return isDestructed;
	}

}
