package UI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;


//import utils.CloseApplicationUtil;

public class App extends Application implements UIConstants{

	private Stage primaryStage;
	private WarPane mainPane;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, PANE_WIDTH, PANE_HEIGHT);
		//scene.getStylesheets().add(this.getClass().getResource("war.css").toExternalForm());
        primaryStage.setScene(scene);
		this.primaryStage = primaryStage;
		
		primaryStage.setTitle("War !!!");
		//primaryStage.initStyle(StageStyle.UNDECORATED); // hides close, min and max buttons
	
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent e) {
				//e.consume(); // to prevent the application from default closing
				//CloseApplicationUtil.closeApplication(GameApplication.this);
			}
		});
		
		mainPane = new WarPane(this);
		borderPane.setCenter(mainPane);
		
		WarMenu theMenu = new WarMenu(this);
		borderPane.setTop(theMenu);
		
        primaryStage.show();
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public WarPane getMainPane(){
		return mainPane;
	}    
}


