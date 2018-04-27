package UI;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LaunchMissilePane extends GridPane implements UIConstants{

	private TextField	idLauncher;
	private TextField	idMissile;
	private TextField	potentialDamage;
	private TextField	destination;
	private TextField	flyTime;	
	
	public LaunchMissilePane(){
		initGrid();
	}
	
	private void initGrid(){

		this.setHgap(10);
		this.setVgap(10);
		this.setPadding(new Insets(20, 150, 10, 10));

		idLauncher = new TextField();
		idMissile = new TextField();
		potentialDamage = new TextField();
		destination = new TextField();
		flyTime = new TextField();

		this.add(new Label(LAUNCHER_ID), 0, 0);
		this.add(idLauncher, 1, 0);
		this.add(new Label(MISSILE_ID), 0, 1);
		this.add(idMissile, 1, 1);
		this.add(new Label(POTENTIAL_DAMAGE), 0, 2);
		this.add(potentialDamage, 1, 2);
		this.add(new Label(DESTINATION), 0, 3);
		this.add(destination, 1, 3);
		this.add(new Label(FLY_TIME), 0, 4);
		this.add(flyTime, 1, 4);
	}
	
	public int getFlyTime(){
		return Integer.parseInt(flyTime.getText());
	}
	
	public int getPotentialDamage(){
		return Integer.parseInt(potentialDamage.getText());
	}
	
	public String getIdLauncher(){
		return idLauncher.getText();
	}
	
	public String getIdMissile(){
		return idMissile.getText();
	}
	
	public String getDestination(){
		return destination.getText();
	}
}
