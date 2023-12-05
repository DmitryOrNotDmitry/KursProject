package application;

import java.io.File;

import data.CSVImporter;
import data.DataTable;
import data.DataTableAdapter;
import data.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DataController {

	private DataTableAdapter dataTableAdapter;
	
	private String selectedDataTableName;

	@FXML
    private TableView<Row> table;
	
	@FXML
    private Button addTable;
	
	@FXML
    private ListView<String> listDataTableNames;

    @FXML
    private GridPane mainGrid;
    

    @FXML
    private void initialize() {
    	System.out.println("dataTableAdapter init");
    	dataTableAdapter = DataTableAdapter.getInstance();
    	ObservableList<String> names = FXCollections.observableArrayList(dataTableAdapter.getAllDataTableNames());
		if (listDataTableNames != null) {
			listDataTableNames.setItems(names);
		}
    }
    
    @FXML
    void addDataTable(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Выберите файл");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Таблицы данных (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog((Stage) addTable.getScene().getWindow());

        if (selectedFile != null) {
        	
        	DataTable dataTable = CSVImporter.importCSV(selectedFile.getAbsolutePath());
        	dataTable.setFile(selectedFile);
        	fillTable(dataTable);
        	
        	selectedDataTableName = selectedFile.getName();
        	dataTableAdapter.addDataTable(selectedDataTableName, dataTable);
        	
        	ObservableList<String> names = FXCollections.observableArrayList(dataTableAdapter.getAllDataTableNames());
    		listDataTableNames.setItems(names);
        }
    	
    }
    
    public void loadDataTable(String name) {
    	DataTable dataTable = dataTableAdapter.getDataTable(name);
    	fillTable(dataTable);
    }
    
    private void fillTable(DataTable dataTable) {
    	table.getColumns().clear();
    	int len = dataTable.getColumns().size();
    	for (int i = 0; i < len; i++) {
    		final int index = i;
    		TableColumn<Row, String> nameColumn = new TableColumn<>(dataTable.getColumns().get(index).getName());
            nameColumn.setCellValueFactory(cellData -> cellData.getValue().getItem(index));
            table.getColumns().add(nameColumn);
    	}
		ObservableList<Row> data = FXCollections.observableArrayList();
		for (Row row : dataTable.getRows()) {
			data.add(row);
		}
    	
    	table.setItems(data);
    }
    
    @FXML
    void selectDataTable(MouseEvent event) {
    	String newName = listDataTableNames.getSelectionModel().getSelectedItem();
    	if (newName == null || newName.equals(selectedDataTableName)) {
    		return;
    	}
    	loadDataTable(newName);
    }
    

}
