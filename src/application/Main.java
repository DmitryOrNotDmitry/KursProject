package application;
	
import java.net.URL;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage predStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			//qweqweq
			Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene mainScene = new Scene(root);
			mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			predStage = new Stage();
			Scene picScene = new Scene(FXMLLoader.load(getClass().getResource("PredPic.fxml")), 400, 400);
			this.initPredStage(predStage, picScene);
			
			
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
				predStage.close();
				this.initMainStage(primaryStage, mainScene);
	        }));
			timeline.play();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void initMainStage(Stage stage, Scene scene) {
		stage.setTitle("Main stage");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();		
	}
	
	public void initPredStage(Stage stage, Scene scene) {
		stage.setScene(scene);
		stage.initStyle(StageStyle.UNDECORATED);
		Screen screen = Screen.getPrimary();
        double screenWidth = screen.getBounds().getWidth();
        double screenHeight = screen.getBounds().getHeight();

        stage.setWidth(scene.getWidth());
        stage.setHeight(scene.getHeight());

        double centerX = (screenWidth - stage.getWidth()) / 2;
        double centerY = (screenHeight - stage.getHeight()) / 2;

        stage.setX(centerX);
        stage.setY(centerY);
		stage.show();
	}
}
