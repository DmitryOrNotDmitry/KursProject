package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

	@FXML
    private Button leftMenuInteract;

    @FXML
    private GridPane mainGrid;

    boolean isLeftMenuHidden = false;
    
    @FXML
    void interactLeftMenu(ActionEvent event) {
    	if (isLeftMenuHidden) {
    		System.out.println("open");
    		isLeftMenuHidden = false;
            VBox leftMenuVBox = (VBox) mainGrid.getChildren().get(0);
            leftMenuVBox.setMaxWidth(100);
            //leftMenuVBox.setMinWidth(100);
            animateLeftMenu();
            leftMenuVBox.setVisible(true);
            leftMenuVBox.setManaged(true);
        } else {
        	System.out.println("close");
        	isLeftMenuHidden = true;
            VBox leftMenuVBox = (VBox) mainGrid.getChildren().get(0);
            //leftMenuVBox.setMaxWidth(0);
            leftMenuVBox.setMinWidth(0);
            animateLeftMenu();
            leftMenuVBox.setVisible(false);
            leftMenuVBox.setManaged(false);
        }
    }
    
	@FXML
    private void initialize() {
    	System.out.print("main");
    }
	
	private void animateLeftMenu() {
		ColumnConstraints column = mainGrid.getColumnConstraints().get(0);
        
        // Создание анимации
        Timeline timeline = new Timeline();

        // Анимация для maxWidth
        KeyValue keyValueMaxWidth = isLeftMenuHidden ? new KeyValue(column.maxWidthProperty(), 0) : new KeyValue(column.maxWidthProperty(), 100);
        KeyFrame keyFrameMaxWidth = new KeyFrame(Duration.seconds(0.25), keyValueMaxWidth);
        timeline.getKeyFrames().add(keyFrameMaxWidth);
        
        timeline.play();
	}
	
}
