package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {

	@FXML
    private Button leftMenuInteract;

    @FXML
    private GridPane mainGrid;

    boolean isLeftMenuHidden = false;
    
    @FXML
    void interactLeftMenu(ActionEvent event) {
    	if (isLeftMenuHidden) {
    		isLeftMenuHidden = false;
    		mainGrid.getColumnConstraints().get(0).setPercentWidth(100);
    	}
    	else {
    		isLeftMenuHidden = true;
    		mainGrid.getColumnConstraints().get(0).setPercentWidth(0);
    	}
    }
    
	@FXML
    private void initialize() {
    	System.out.print("main");
    }
	
}
