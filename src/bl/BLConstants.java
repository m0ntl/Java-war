package bl;

public interface BLConstants {

	public static final int		ADD_LAUNCHER_DESTRUCTOR = 1;
	public static final int 	ADD_MISSILE_DESTRUCTOR = 2;
	public static final int 	ADD_LAUNCHER = 3;
	public static final int 	LAUNCH_MISSILE = 4;
	public static final int 	DESTRUCT_MISSILE = 5;
	public static final int 	DESTRUCT_LAUNCHER = 6;
	public static final int 	SHOW_STATISTICS = 7;
	public static final int 	EXIT = 0;
	public static final int 	ONE_SEC = 1000;
	public static final int 	MAX_TIME = ONE_SEC * 30;	
	public static final int 	YES = 1;

	public static final String	YES_NO = "YES --- 1\n"
										+"NO ---- 2\n";
	public static final String LOAD_FROM_CONFIG = "Do you want to read from config file?\n" + YES_NO;
	public static final String 	MAIN_MENU_STR = "Welcome to THE WAR !!!\n"
											+ "Please choose one of the above:\n"
											+ "Add launcher destractor ------ 1\n"
											+ "Add missile destractor ------- 2\n"
											+ "Add launcher ----------------- 3\n"
											+ "Launch missile --------------- 4\n"
											+ "Destruct a launcher ---------- 5\n"
											+ "Destruct a missile ----------- 6\n"												+ "Show statistics -------------- 7\n"
											+ "Exit ------------------------- 0\n";
	
	public static final String	ENTER_POTENTIAL_DAMAGE = "Enter potential damage:\n";
	public static final String	ENTER_DESTINATION = "Enter destination:\n";
	public static final String	ENTER_FLIGHT_TIME = "Enter flight time:\n";
	public static final String	ENTER_LAUNCHER_ID = "Enter launcher's id:\n";
	public static final String	ENTER_IS_LAUNCHER_HIDDEN = "is launcher hidden?\n" + YES_NO;
}
