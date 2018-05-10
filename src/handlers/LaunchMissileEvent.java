package handlers;

import java.util.Optional;

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
import UI.UIConstants;

public class LaunchMissileEvent implements EventHandler<ActionEvent>, UIConstants {

//	private App app;
//	private String launcherId;
//	
//	public LaunchMissileEvent(App app, String launcherId) {
//		this.app = app;
//		this.launcherId = launcherId;
//	}
//	
	@Override
	public void handle(ActionEvent arg0) {
		
//		Dialog<String> dialog = new Dialog<>();
//		dialog.setTitle(LAUNCH_MISSILE);
//		dialog.setHeaderText("Enter launch information");
//
//		LaunchMissilePane grid = new LaunchMissilePane();
//		dialog.getDialogPane().setContent(grid);
//		
//		ButtonType launchButtonType = new ButtonType(LAUNCH_MISSILE, ButtonData.OK_DONE);
//		dialog.getDialogPane().getButtonTypes().addAll(launchButtonType);
//
//		Optional<String> data = dialog.showAndWait();
//		
//		if (data.isPresent()){
//			//String id = launcher.getId();
//			app.getMainPane().initiateLaunchMissile(launcherId, grid.getPotentialDamage(), grid.getDestination(),
//													grid.getFlyTime());
//		}
	}
}
