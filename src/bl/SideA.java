package bl;

import java.util.Vector;


public class SideA extends Warrior implements BLConstants{ // Israel - can only destruct missiles and launchers
	
	private int 							totalDamage;
	
	private Vector<MissileDestructor> 		missileDestructors = new Vector<>();
	private Vector<LauncherDestructor> 		launcherDestructors = new Vector<>();
		
	public SideA() {
		
	}
	
	public void addMissileDestructor(String id) {
		missileDestructors.add( new MissileDestructor(id) );
	}

	public void addLauncherDestructor(String id, int type) {
		switch( type ){
		case PLANE:
			launcherDestructors.add(new Plane(id));
			break;
		case SHIP:
			launcherDestructors.add(new Ship(id));
			break;
		}
	}

	public void setDamage(int damage) {

	}

	public void destructMissile() {
		
	}

	public void destructLauncher(){
		//((SideB)enemy).launcherUnderAttack(id);
	}
	
	public void missileLaunched(){
		
	}
	
	public int getTotalDamage() {
		return totalDamage;
	}
	
}
