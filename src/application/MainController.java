package application;

import data.DataTableAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MainController {


	@FXML
    private Button addTableButton;
    
    @FXML
    private GridPane mainGrid;

    @FXML
    private ListView<String> dataList;
    
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
