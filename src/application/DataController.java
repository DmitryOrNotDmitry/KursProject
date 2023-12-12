package application;

import java.io.File;

import data.CSVImporter;
import data.DataTable;
import data.DataTableAdapter;
import data.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldListCell;
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
			listDataTableNames.setCellFactory(TextFieldListCell.forListView());
			listDataTableNames.setOnEditCommit(event -> {
	            int index = event.getIndex();
	            String newValue = event.getNewValue();
	            String oldValue = names.get(index);
	            dataTableAdapter.changeDataTableName(oldValue, newValue);
	            names.set(index, newValue);
	            
	            ScenesInitializator.getMainController().fillDataList();
	            ScenesInitializator.getDiagramController().fillDataTableNames();
	        });
		}
    }
    
    @FXML
    void addDataTable(ActionEvent event) {
    	DataTable dataTable = chooseFileAndLoadDataTable();
    	
    	fillTable(dataTable);
    	
    	selectedDataTableName = dataTable.getFile().getName();
    	dataTableAdapter.addDataTable(selectedDataTableName, dataTable);
    	
    	ObservableList<String> names = FXCollections.observableArrayList(dataTableAdapter.getAllDataTableNames());
		listDataTableNames.setItems(names);
    	
    }
    
    public DataTable chooseFileAndLoadDataTable() {
    	FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Выберите файл");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Таблицы данных (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog((Stage) addTable.getScene().getWindow());

        if (selectedFile != null) {
        	
        	DataTable dataTable = CSVImporter.importCSV(selectedFile.getAbsolutePath());
        	dataTable.setFile(selectedFile);
        	dataTableAdapter.addDataTable(selectedFile.getName(), dataTable);
        	
        	return dataTable;
        }
        
        return null;
    }
    
    public void loadDataTable(String name) {
    	DataTable dataTable = dataTableAdapter.getDataTable(name);
		fillTable(dataTable);
    }
    
    private void fillTable(DataTable dataTable) {
    	if (dataTable == null) {
    		return;
    	}
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
    	int clickCount = event.getClickCount();
    	
    	if (clickCount == 1) {
    		String newName = listDataTableNames.getSelectionModel().getSelectedItem();
    		if (newName == null || newName.equals(selectedDataTableName)) {
    			return;
    		}
    		loadDataTable(newName); 
    		return;
    	}
    	
    	if (clickCount == 2) {
    		
    		return;
    	}
    	
    }
    

}
