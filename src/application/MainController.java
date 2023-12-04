package application;

import data.DataTableAdapter;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {


	@FXML
    private Button addTableButton;

    @FXML
    private Button addTableButton1;
    
    @FXML
    private GridPane mainGrid;

    @FXML
    private ListView<String> dataList;

    @FXML
    private ListView<String> diagramList;
    
    private DataTableAdapter dataAdapter;
    
    
	@FXML
    private void initialize() {
    	dataAdapter = DataTableAdapter.getInstance();
    	ObservableList<String> names = FXCollections.observableArrayList(dataAdapter.getAllDataTableNames());
		dataList.setItems(names);
    }
	

	@FXML
    void redirectToDataTable(MouseEvent event) {
		MenuController.showData(addTableButton);
		ScenesInitializator.getDataController().loadDataTable(dataList.getSelectionModel().getSelectedItem());
		//dataController.loadDataTble(dataList.getSelectionModel().getSelectedItem());
    }

    @FXML
    void redirectToDiagram(MouseEvent event) {

    }
	
}
