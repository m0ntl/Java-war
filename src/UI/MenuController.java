package UI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mvc.WarController;

public class MenuController implements Initializable, UIConstants{
  @FXML
  private BorderPane		mainPane;  
  private TextInputDialog	dialog = new TextInputDialog("");
  
  
  //add 
  @FXML
  private void handleAddLauncherAction(final ActionEvent event){
	  dialog.setTitle(STR_ADD_MISSILE_LAUNCHER);
	  dialog.setHeaderText("Enter Missile-Launcher's id");
	  dialog.setContentText(ID);
	  boolean isHidden = new Random().nextBoolean();
	  
	  Optional<String> idOp = dialog.showAndWait();
	  if (idOp.isPresent())
		  App.getWar().initiateAddMissileLauncher( idOp.get() , isHidden);
  }
  
 @FXML
  private void handleAddMissileDestructorAction(final ActionEvent event){
		dialog.setTitle(STR_ADD_MISSILE_DESTRUCTOR);
		dialog.setHeaderText("Enter Missile-Destructor's id");
		dialog.setContentText(ID);

		Optional<String> idOp = dialog.showAndWait();
		if (idOp.isPresent())
			 App.getWar().initiateAddMissileDestructor(idOp.get());
  }
 
 @FXML
 private void handleAddLauncherDestructorAction(final ActionEvent event){
		
 }
 
 
 @FXML
 private void handleLaunchMissileAction(final ActionEvent event){
     
//	 dialog.initStyle(StageStyle.UTILITY);
//	 dialog.setTitle(LAUNCH_MISSILE);
//	 dialog.setHeaderText("Enter launch information");
//	 Parent fxmlRoot = null;
//	 
//	 try {
//		 fxmlRoot = (Parent) new FXMLLoader().load(new FileInputStream(new File("addLauncher.fxml")));
//		 dialog.getDialogPane().setContent(fxmlRoot);
//		 } catch (IOException e) {e.printStackTrace();}
//	 Optional<String> data = dialog.showAndWait();	
//		if (data.isPresent()){
//			//String id = launcher.getId();
//			 App.getWar().initiateLaunchMissile( id, grid.getPotentialDamage(), grid.getDestination(),
//													grid.getFlyTime());
//		}
 
 }
 
 @FXML
 private void handleDestructMissileAction(final ActionEvent event){
	  
 }
 
 @FXML
 private void handleDestructLauncherAction(final ActionEvent event){
		
 }
 
 @FXML
 private void handleStatisticsAction(final ActionEvent event){
	 App.getWar().initiateStatistics();
 }
 
 @FXML
 private void handleExitAction(final ActionEvent event){
	  
 }
 
 @Override
 public void initialize(java.net.URL arg0, ResourceBundle arg1) {
	 mainPane.setFocusTraversable(true);
 }   
}