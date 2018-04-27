package handlers;

import java.util.Optional;

import UI.App;
import UI.LaunchMissilePane;
import UI.UIConstants;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LaunchMissileEvent implements EventHandler<ActionEvent>, UIConstants {

	private App app;

	private TextField idLauncher = new TextField();
	private TextField idMissile = new TextField();
	private TextField potentialDamage = new TextField();
	private TextField destination = new TextField();
	private TextField flyTime = new TextField();
	
	
	public LaunchMissileEvent(App app) {
		this.app = app;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(LAUNCH_MISSILE);
		dialog.setHeaderText("Enter launch information");

		LaunchMissilePane grid = new LaunchMissilePane();
		dialog.getDialogPane().setContent(grid);
		
		ButtonType launchButtonType = new ButtonType(LAUNCH_MISSILE, ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(launchButtonType);

		// Request focus on the id field by default.
		Platform.runLater(() -> idLauncher.requestFocus());

		Optional<String> data = dialog.showAndWait();
		
		if (data.isPresent()){
			app.getMainPane().initiateLaunchMissile(grid.getIdLauncher(), grid.getIdMissile(), 
													grid.getPotentialDamage(), grid.getDestination(),
													grid.getFlyTime());
		}
	}
}
