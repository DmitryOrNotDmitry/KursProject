package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScenesInitializator {

	private ScenesInitializator() {
		
	}
	
	private static Scene mainScene = null;
	private static Scene dataScene = null;
	private static Scene diagramScene = null;
	private static Scene infoScene = null;
	
	
	public static Scene getMainScene() {
		if (mainScene == null) {
			Parent root;
			try {
				root = FXMLLoader.load(ScenesInitializator.class.getResource("MainMenu.fxml"));
				mainScene = new Scene(root);
				mainScene.getStylesheets().add(ScenesInitializator.class.getResource("application.css").toExternalForm());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return mainScene;
	}
	
	public static Scene getDataScene() {
		if (dataScene == null) {
			
		}
		
		return dataScene;
	}
	
	public static Scene getDiagramScene() {
		if (diagramScene == null) {
			
		}
		
		return diagramScene;
	}
	
	public static Scene getInfoScene() {
		if (infoScene == null) {
			
		}
		
		return infoScene;
	}
	
}
