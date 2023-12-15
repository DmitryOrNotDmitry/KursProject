package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ScenesInitializator {

	private ScenesInitializator() {
		
	}
	
	private static Scene mainScene = null;
	private static Scene dataScene = null;
	private static Scene diagramScene = null;
	private static Scene infoScene = null;
	private static Scene predScene = null;
	
	private static MainController mainController;
	private static DataController dataController;
	private static DiagramController diagramController;
	
	public static Scene getPredScene() {
		Parent root;
		if (predScene == null) {
			try {
				root = FXMLLoader.load(ScenesInitializator.class.getResource("PredPic.fxml"));
				predScene = new Scene(root, 400, 400);
			} catch (IOException e) {
				e.printStackTrace();
			}				
		}
		
		return predScene;
	}
	
	public static Scene getMainScene() {
		if (mainScene == null) {
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(ScenesInitializator.class.getResource("MainMenu.fxml"));
				root = loader.load();
				mainScene = new Scene(root);
				mainScene.getStylesheets().add(ScenesInitializator.class.getResource("application.css").toExternalForm());
				mainController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return mainScene;
	}
	
	public static Scene getDataScene() {
		if (dataScene == null) {
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(ScenesInitializator.class.getResource("DataTables.fxml"));
				root = loader.load();
				dataScene = new Scene(root);
				dataScene.getStylesheets().add(ScenesInitializator.class.getResource("application.css").toExternalForm());
				dataController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return dataScene;
	}
	
	public static Scene getDiagramScene() {
		if (diagramScene == null) {
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(ScenesInitializator.class.getResource("Diagrams.fxml"));
				root = loader.load();
				diagramScene = new Scene(root);
				diagramScene.getStylesheets().add(ScenesInitializator.class.getResource("application.css").toExternalForm());
				diagramController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return diagramScene;
	}
	
	public static Scene getInfoScene() {
		if (infoScene == null) {
			Parent root;
			try {
				FXMLLoader loader = new FXMLLoader(ScenesInitializator.class.getResource("HelpPage.fxml"));
				root = loader.load();
				infoScene = new Scene(root);
				infoScene.getStylesheets().add(ScenesInitializator.class.getResource("application.css").toExternalForm());
				//infoController = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return infoScene;
	}

	public static DataController getDataController() {
		if (dataController == null) {
			ScenesInitializator.getDataScene();
		}
		
		return dataController;
	}
	
	public static MainController getMainController() {
		if (mainController == null) {
			ScenesInitializator.getMainScene();
		}
		
		return mainController;
	}
	
	public static DiagramController getDiagramController() {
		if (diagramController == null) {
			ScenesInitializator.getDiagramScene();
		}
		
		return diagramController;
	}
	
}
