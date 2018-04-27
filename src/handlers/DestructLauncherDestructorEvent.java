package handlers;

import UI.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DestructLauncherDestructorEvent implements EventHandler<ActionEvent> {

	private App app;

	public DestructLauncherDestructorEvent(App app) {
		this.app = app;
	}
	
	@Override
	public void handle(ActionEvent arg0) {

	}

}
