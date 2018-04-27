package handlers;

import java.util.Optional;

import UI.App;
import UI.UIConstants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.stage.StageStyle;

public class AddMissileDestructorEvent implements EventHandler<ActionEvent> , UIConstants{

	private App app;

	public AddMissileDestructorEvent(App app) {
		this.app = app;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle(ADD_MISSILE_DESTRUCTOR);
		dialog.setHeaderText("Enter Missile-Destructor's id");
		dialog.setContentText(ID);

		Optional<String> idOp = dialog.showAndWait();
		if (idOp.isPresent())
			app.getMainPane().initiateAddMissileDestructor(idOp.get());
			
	}

}
