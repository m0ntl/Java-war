package handlers;

import java.util.Optional;

import UI.App;
import UI.UIConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

public class AddLauncherDestructorEvent implements EventHandler<ActionEvent>, UIConstants {

	private App app;
	private int type;
	
	public AddLauncherDestructorEvent(App app, int type) {
		this.app = app;
		this.type = type;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle(ADD_LAUNCHER_DESTRUCTOR);
		dialog.setHeaderText("Enter Launcher-Destructor's id");

		dialog.setContentText(ID);

		Optional<String> idOp = dialog.showAndWait();
		if (idOp.isPresent())
			app.getMainPane().initiateAddLauncherDestructor(idOp.get(), type);
	}

}
