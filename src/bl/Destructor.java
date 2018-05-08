package bl;

public abstract class Destructor {
	private String id;
	
	public Destructor(String id){
		this.id = id;
	}
	
	public String getID() {
		return this.id;
	}
}
