package UI;

import handlers.AddLauncherDestructorEvent;
import handlers.AddMissileDestructorEvent;
import handlers.AddMissileLauncherEvent;
import handlers.DestructLauncherEvent;
import handlers.LaunchMissileEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
//import utils.CloseApplicationUtil;

public class WarMenu extends MenuBar implements UIConstants {

	private App app;

	public WarMenu(App app) {
		this.app = app;

		initFileMenu();
		initAddMenu();
		initLaunchMenu();
		initDestructMenu();
	}
	
	//file menu
	private void initFileMenu(){
		
		Menu fileMenu = new Menu(FILE);
		initSumUpMenuItem( fileMenu);
		initExitMenuItem(fileMenu);
		this.getMenus().add(fileMenu);
	}
	
	private void initSumUpMenuItem(Menu fileMenu){
		MenuItem sumUp = new MenuItem(SUM_UP);
		
		sumUp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		fileMenu.getItems().add(sumUp);
	}
	
	private void initExitMenuItem(Menu fileMenu){
		MenuItem exit = new MenuItem(EXIT);
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		
		fileMenu.getItems().add(exit);
	}

	//add menu
	private void initAddMenu(){
		
		Menu addMenu = new Menu(ADD);

		initAddMissileLauncherMenuItem(addMenu);
		initAddMissileDestructorMenuItem(addMenu);
		initAddLauncherDestructorMenu(addMenu);
		
		this.getMenus().add(addMenu);
	}
	
	private void initAddLauncherDestructorMenu(Menu addMenu){
		
		Menu addLauncherDestructor = new Menu(ADD_LAUNCHER_DESTRUCTOR);
		
		MenuItem addShip = new MenuItem(ADD_SHIP);
		addShip.setOnAction( new AddLauncherDestructorEvent(app, SHIP) );
		addLauncherDestructor.getItems().add(addShip);

		
		MenuItem addPlane = new MenuItem(ADD_PLANE);
		addPlane.setOnAction( new AddLauncherDestructorEvent(app, PLANE) );
		addLauncherDestructor.getItems().add(addPlane);
		
		addMenu.getItems().add(addLauncherDestructor);
	}
	
	private void initAddMissileLauncherMenuItem(Menu addMenu){
		MenuItem addMissileLauncher = new MenuItem(ADD_MISSILE_LAUNCHER);
		addMissileLauncher.setOnAction( new AddMissileLauncherEvent(app) );
		addMenu.getItems().add(addMissileLauncher);
	}
	
	private void initAddMissileDestructorMenuItem(Menu addMenu){
		MenuItem addMissileDestructor = new MenuItem(ADD_MISSILE_DESTRUCTOR);		
		addMissileDestructor.setOnAction( new AddMissileDestructorEvent(app) );
		addMenu.getItems().add(addMissileDestructor);
	}
	
	
	//launch menu
	private void initLaunchMenu(){
		
		Menu launchMenu = new Menu(LAUNCH);
		
		initLaunchMissileMenuItem(launchMenu);
		
		this.getMenus().add(launchMenu);
	}
	
	private void initLaunchMissileMenuItem(Menu launchMenu){
		MenuItem launchMissile = new MenuItem(LAUNCH_MISSILE);
		launchMissile.setOnAction( new LaunchMissileEvent(app) );
		
		launchMenu.getItems().add(launchMissile);
	}

	//destruct menu
	private void initDestructMenu(){

		Menu destructhMenu = new Menu(DESTRUCT);
		
		initDestructMissileMenuItem(destructhMenu);
		initDestructLauncherMenuItem(destructhMenu);
		initDestructLauncherDestructorMenuItem(destructhMenu);

		this.getMenus().add(destructhMenu);
	}
	
	private void initDestructLauncherMenuItem(Menu destructhMenu){
		MenuItem destructLauncher = new MenuItem(DESTRUCT_LAUNCHER);
		destructLauncher.setOnAction( new DestructLauncherEvent(app) );
		destructhMenu.getItems().add(destructLauncher);
	}

	private void initDestructMissileMenuItem(Menu destructhMenu){
		MenuItem destructMissile = new MenuItem(DESTRUCT_MISSILE);
		destructMissile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		destructhMenu.getItems().add(destructMissile);
	}
	
	private void initDestructLauncherDestructorMenuItem(Menu destructhMenu){
		MenuItem destructLauncherDestructor = new MenuItem(DESTRUCT_LAUNCHER_DESTRUCTOR);
		destructLauncherDestructor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
			}
		});
		destructhMenu.getItems().add(destructLauncherDestructor);
	}
}
