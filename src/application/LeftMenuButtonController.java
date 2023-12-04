package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LeftMenuButtonController {

    @FXML
    private Button leftMenuInteract;

    boolean isLeftMenuHidden = false;
    
    @FXML
    void interactLeftMenu(ActionEvent event) {
    	GridPane mainGrid = (GridPane) leftMenuInteract.getParent().getParent().getParent();
    	VBox leftMenuVBox = (VBox) mainGrid.getChildren().get(0);
    	if (isLeftMenuHidden) {
    		System.out.println("open");
    		isLeftMenuHidden = false;
            leftMenuVBox.setMaxWidth(100);
            animateLeftMenu(mainGrid);
            leftMenuVBox.setVisible(true);
            leftMenuVBox.setManaged(true);
        } else {
        	System.out.println("close");
        	isLeftMenuHidden = true;
        	leftMenuVBox.setMinWidth(0);
            animateLeftMenu(mainGrid);
            leftMenuVBox.setVisible(false);
            leftMenuVBox.setManaged(false);
        }
    }
    
	@FXML
    private void initialize() {
    	System.out.println("leftMenuInteract");
    }
	
	private void animateLeftMenu(GridPane mainGrid) {
		ColumnConstraints column = mainGrid.getColumnConstraints().get(0);
        
        
        Timeline timeline = new Timeline();

        KeyValue keyValueMaxWidth = isLeftMenuHidden ? new KeyValue(column.maxWidthProperty(), 0) : new KeyValue(column.maxWidthProperty(), 100);
        KeyValue keyValueMinWidth = isLeftMenuHidden ? new KeyValue(column.minWidthProperty(), 0) : new KeyValue(column.minWidthProperty(), 100);
        KeyFrame keyFrames = new KeyFrame(Duration.seconds(0.25), keyValueMaxWidth, keyValueMinWidth);
        timeline.getKeyFrames().add(keyFrames);
        
        timeline.play();
	}


}
