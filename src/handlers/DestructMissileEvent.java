package handlers;

import UI.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DestructMissileEvent implements EventHandler<ActionEvent> {

	private App app;

	public DestructMissileEvent(App app) {
		this.app = app;
	}
	
	@Override
	public void handle(ActionEvent arg0) {

	}

}
