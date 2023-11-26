package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuController {

	 @FXML
    private Button dataTables;

    @FXML
    private Button diagrams;

    @FXML
    private Button help;

    @FXML
    private Button mainMenu;


    @FXML
    private void initialize() {
    	System.out.print("menu");
    }
    
    @FXML
    void showMainMenu(ActionEvent event) {
		Scene currentScene = (Scene) mainMenu.getScene();
		Scene mainScene = ScenesInitializator.getMainScene();
		if (currentScene != mainScene) {
			Stage curStage = (Stage) currentScene.getWindow();
			attachSceneToStage(curStage, mainScene);
		}
    }
    
    @FXML
    void showDataTables(ActionEvent event) {
    	Scene currentScene = (Scene) dataTables.getScene();
		Scene dataScene = ScenesInitializator.getDataScene();
		if (currentScene != dataScene) {
			Stage curStage = (Stage) currentScene.getWindow();
			attachSceneToStage(curStage, dataScene);
		}
    }

    @FXML
    void showDiagrams(ActionEvent event) {
    	Scene currentScene = (Scene) diagrams.getScene();
		Scene diagramScene = ScenesInitializator.getDiagramScene();
		if (currentScene != diagramScene) {
			Stage curStage = (Stage) currentScene.getWindow();
			attachSceneToStage(curStage, diagramScene);
		}
    }

    @FXML
    void showHelp(ActionEvent event) {
    	
    }
    
    public void attachSceneToStage(Stage stage, Scene scene) {
    	double width = stage.getWidth();
		double height = stage.getHeight();
		stage.setScene(scene);
		stage.setWidth(width);
		stage.setHeight(height);
    }
    
}
