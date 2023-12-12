package application;

import data.DataTable;
import data.DataTableAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    
    private DataTableAdapter dataTableAdapter;
    
    
	@FXML
    private void initialize() {
		dataTableAdapter = DataTableAdapter.getInstance();
    	this.fillDataList();
    }
	

	@FXML
    void redirectToDataTable(MouseEvent event) {
		MenuController.showData(addTableButton);
		ScenesInitializator.getDataController().loadDataTable(dataList.getSelectionModel().getSelectedItem());
    }

	@FXML
    void addDataTableInMainMenu(ActionEvent event) {
		ScenesInitializator.getDataController().chooseFileAndLoadDataTable();
    	
		this.fillDataList();
    }
	
	public void fillDataList() {
		ObservableList<String> names = FXCollections.observableArrayList(dataTableAdapter.getAllDataTableNames());
    	dataList.setItems(names);
	}
	
}
