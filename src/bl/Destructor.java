package bl;

import java.util.HashMap;
import java.util.Map;

public abstract class Destructor {
	private String id;
	
	public Destructor(String id){
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
}
