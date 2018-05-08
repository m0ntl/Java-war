package UI;

import javafx.scene.layout.Pane;

public class CountryPane extends Pane {

	private App 	app;
	private String 	countryName;
	
	public CountryPane(App app, String countryName){
		this.app = app;
		this.countryName = countryName;
		
	}
}
