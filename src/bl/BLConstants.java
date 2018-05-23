package bl;

public interface BLConstants {
	
	
	public static final int 	MAX_FLY_TIME = 20;
	public static final int 	MIN_FLY_TIME = 1;
	
	public static final int 	ONE_SEC = 1000;
	public static final int 	MAX_TIME = ONE_SEC * 30;	

	public enum LauncherDestructorType {
	    PLANE,
	    SHIP;
	}
	
	public static final int 	YES = 1;

	public static final String	YES_NO = "YES --- 1\n"
			+"NO ---- 2\n";
	public static final String LOAD_FROM_CONFIG = "Do you want to read from config file?\n" + YES_NO;

	public static final String 	CONFIGURATION_FILE = "configFile.json";

}
