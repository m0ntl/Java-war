package bl;

public class MissileDestructor extends Destructor{
	
	public MissileDestructor(String id) {
		super(id);
	}
	
	public boolean tryDestruct(Missile m, int destructTimeAfterLaunch){
		return m.isDestructed(destructTimeAfterLaunch); 
	}

}
