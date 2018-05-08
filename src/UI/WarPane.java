package UI;

import java.util.Vector;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import mvc.WarUIEventsListener;

public class WarPane extends GridPane implements WarUI, UIConstants {
	
	private Vector<WarUIEventsListener>	allListeners = new Vector<WarUIEventsListener>();
	private CountryPane 				israel, enemy;
	
	public WarPane(App app) {
				
		this.prefWidthProperty().bind(app.getPrimaryStage().getScene().widthProperty());
		this.prefHeightProperty().bind(app.getPrimaryStage().getScene().heightProperty());
		
		SplitPane countriesSplitter = new SplitPane();
		countriesSplitter.setOrientation(Orientation.HORIZONTAL);

		israel = new CountryPane(app,ISRAEL );
		enemy = new CountryPane(app, OTHER_COUNTRY);

		countriesSplitter.getItems().addAll(israel, enemy);
		countriesSplitter.setDividerPositions(0.5, 0.5);
		
		countriesSplitter.prefWidthProperty().bind(this.widthProperty());
		countriesSplitter.prefHeightProperty().bind(this.heightProperty());
		israel.prefHeightProperty().bind(countriesSplitter.heightProperty());
		israel.prefWidthProperty().bind(countriesSplitter.widthProperty());
		enemy.prefHeightProperty().bind(countriesSplitter.heightProperty());
		enemy.prefWidthProperty().bind(countriesSplitter.widthProperty());
		
		getChildren().add(countriesSplitter);
		
	}

	public void registerListener(WarUIEventsListener listener) {
		allListeners.add(listener);
	}
	
	//show
	public void showAddMissileLauncher() {
	}

	public void showAddMissileDestructor() {
	}

	public void showAddLauncherDestructor() {
	}

	public void showLaunchMissile(int damage) {
		
	}

	public void showDestructLuncher() {
		
	}

	public void showDestructMissile() {
	}

	public void showDestructLauncherDestructor() {
	}

	public void showSumUp(int totalDamege, int launchedMissiles, int destructedMissiles, int destructedLaunchers, int hits ) {
	}

	public void showExit() {
	}

	
	//initiate
	
	public void initiateAddMissileLauncher(String id) {
 
		fireAddMissileLauncherEvent(id);
	}

	public void initiateAddMissileDestructor(String id) {
		
		fireAddMissileDestructorEvent(id);
	}
	
	public void initiateAddLauncherDestructor(String id, int type) {
		fireAddLauncherDestructorEvent(id, type);
	}

	public void initiateLaunchMissile(String idLauncher, String idMissile, int potentialDamage, String destination, int flyTime){
		fireLaunchMissileEvent(idLauncher, idMissile, potentialDamage, destination, flyTime);
	}

	public void initiateDestructLuncher(String id) {
		fireDestructLuncherEvent(id);
	}

	public void initiateDestructMissile() {
		fireDestructMissileEvent();
	}

	public void initiateDestructLauncherDestructor() {
		fireDestructLauncherDestructorEvent();
	}

	public void initiateSumUp() {
		fireSumUpEvent();
	}

	public void initiateExit() {
		fireExitEvent();
	}

	
	//fire events to bl
	
	private void fireAddMissileLauncherEvent(String id) {
		for (WarUIEventsListener l : allListeners)
			l.addMissileLauncherFromUI(id);	
	}
	
	private void fireAddMissileDestructorEvent(String id) {
		for (WarUIEventsListener l : allListeners)
			l.addMissileDestructorFromUI(id);	
	}
	
	private void fireAddLauncherDestructorEvent(String id, int type) {
		for (WarUIEventsListener l : allListeners)
			l.addLauncherDestructorFromUI( id, type);	
	}
	
	private void fireLaunchMissileEvent(String idLauncher, String idMissile, int potentialDamage, String destination, int flyTime) {
		
		for (WarUIEventsListener l : allListeners)
			l.launchMissileFromUI(idLauncher, idMissile, potentialDamage, destination, flyTime);
	}
	
	private void fireDestructLuncherEvent(String id) {
		for (WarUIEventsListener l : allListeners)
			l.destructLuncherFromUI(id);
	}
	
	private void fireDestructMissileEvent() {
		for (WarUIEventsListener l : allListeners)
			l.destructMissileFromUI();
	}
	
	private void fireDestructLauncherDestructorEvent() {
		for (WarUIEventsListener l : allListeners)
			l.destructLauncherDestructorFromUI();	
	}
	
	private void fireSumUpEvent() {
		for (WarUIEventsListener l : allListeners)
			l.sumUpFromUI();
	}
	
	private void fireExitEvent() {
		for (WarUIEventsListener l : allListeners)
			l.exitFromUI();
	}

	
//	@Override
//	public void showFailedAdding...(String msg) {
//		Alert alert = new Alert(AlertType.ERROR);
//		alert.setTitle("Error Dialog");
//		alert.setHeaderText("Look, an Error Dialog");
//		alert.setContentText(msg);
//		alert.showAndWait();
//	}

	
}
