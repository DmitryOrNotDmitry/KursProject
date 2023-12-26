package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import data.DataTableAdapter;
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
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage predStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			loadUrls();
			
			Scene mainScene = ScenesInitializator.getMainScene();
			
			predStage = new Stage();
			Scene predScene = ScenesInitializator.getPredScene();
			this.initPredStage(predStage, predScene);
			
			
			Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
				predStage.close();
				this.initMainStage(primaryStage, mainScene);
	        }));
			timeline.play();
			
			primaryStage.setOnCloseRequest(event -> {
				try {
					handleCloseEvent();
					System.exit(0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadUrls() throws IOException {
		DataTableAdapter dataAdapter = DataTableAdapter.getInstance();
		File saveFile = new File("loadedUrls");
		BufferedReader in = new BufferedReader(new FileReader(saveFile));
		
		if (saveFile.exists()) {
			List<String> lines = new ArrayList<>();
			String line;
			while ((line = in.readLine()) != null) {
                lines.add(line);
            }
			dataAdapter.readObject(lines);
		}
	}

	private void handleCloseEvent() throws IOException {
		DataTableAdapter dataAdapter = DataTableAdapter.getInstance();
		File saveFile = new File("loadedUrls");
		FileWriter out = new FileWriter(saveFile);
			
		if (saveFile.exists()) {
			StringBuilder outBuffer = new StringBuilder();
			
			dataAdapter.writeObject(outBuffer);
			
			out.write(outBuffer.toString());
		}
		out.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initMainStage(Stage stage, Scene scene) {
		stage.getIcons().add(new Image("/images/icon.png"));
		stage.setTitle("DiagramMaker");
		stage.setMaximized(true);
		stage.setScene(scene);
		stage.show();		
	}
	
	public void initPredStage(Stage stage, Scene scene) {
		stage.getIcons().add(new Image("/images/icon.png"));
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
