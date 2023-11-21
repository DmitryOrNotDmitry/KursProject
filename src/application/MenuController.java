package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Button mainMenu;

    @FXML
    private void initialize() {
    	System.out.print(1);
    }
    
    @FXML
    void showMainMenu(ActionEvent event) {
		Scene scene = (Scene) mainMenu.getScene();
		((VBox)scene.getRoot()).getChildren().get(0).setStyle("-fx-background-color: white;");
    }
    

}
