package UI;

import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mvc.WarController;

public class App extends Application {

	private static WarUI warUI = new War();
	
	@Override
	public void start(final Stage stage) throws Exception {
		
		FXMLLoader f = new FXMLLoader();
		Parent fxmlRoot = (Parent) f.load(new FileInputStream(new File("fxmlMenu.fxml")));
		
		Scene scene = new Scene(fxmlRoot);
		stage.setScene(scene);
		stage.show();
		
		new WarController( warUI );
	}
	
	public static WarUI getWar(){
		return warUI;
	}
}
