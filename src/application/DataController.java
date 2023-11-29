package application;

import java.io.File;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.random.RandomGeneratorFactory;

import data.CSVImporter;
import data.Column;
import data.DataTable;
import data.DataTableAdapter;
import data.Row;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DataController {

	@FXML
    private TableView<Row> table;
	
	@FXML
    private Button addTable;
	
	@FXML
    private ListView<String> listDataTableNames;

    @FXML
    private GridPane mainGrid;
    
    private DataTableAdapter dataTableAdapter;

    @FXML
    private void initialize() {
    	System.out.println("dataTableAdapter init");
    	dataTableAdapter = DataTableAdapter.getInstance();
    }
    
    @FXML
    void addDataTable(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Выберите файл");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Текстовые файлы (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog((Stage) addTable.getScene().getWindow());

        if (selectedFile != null) {
        	CSVImporter.importCSV(selectedFile.getAbsolutePath());
        	DataTable dataTable = CSVImporter.getDataTable();
        	fillTable(dataTable);
        	Random rand = new Random();
        	String name = "Name example" + rand.nextInt();
        	dataTableAdapter.addDataTable(name.toString(), dataTable);
        	ObservableList<String> names = FXCollections.observableArrayList();
        	for (String name1 : dataTableAdapter.getAllDataTableNames()) {
        		names.add(name1);
        	}
    		listDataTableNames.setItems(names);
    		System.out.println(dataTableAdapter.getAllDataTableNames().size());
        } else {
            System.out.println("Отменено пользователем.");
        }
    	
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
    

}
