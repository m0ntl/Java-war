package bl;

import java.util.Vector;

public class MissileLauncher implements Hiddenable{

	private String 				id;
	private static int 			iDJen;
	
	//?
	private boolean				isHidden, isHiddenNow; 
	
	private boolean				isDestructed;
	
	public MissileLauncher(String id){
		if ( id != null )
			this.id = "L"+id;
		else
			this.id = (++iDJen+"");
				
		isDestructed = false;
		isHidden = Math.random() < 0.5;
		isHiddenNow = isHidden;
	}
	
	public synchronized void launchMissile(Missile m){
		
		if ( isHiddenNow )
			emerge();

		m.launch();
		
		if ( isHidden )
			hide();
	}
	
	//???
	public boolean isHiddenNow() {
		return isHidden;
	}
	
	private void hide() {
		isHidden = true;
	}
	
	private void emerge() {
		isHidden = false;
	}
	
	public boolean underAttack(){
		if ( !isHiddenNow )
			isDestructed = true;
		return isDestructed;
	}
	
	public String getId(){
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
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