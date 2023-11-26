package application;

import java.io.File;
import java.util.Observable;

import data.CSVImporter;
import data.Column;
import data.DataTable;
import data.Row;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private GridPane mainGrid;

    @FXML
    private void initialize() {
    	
    }
    
    @FXML
    void addDataTable(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Выберите файл");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Текстовые файлы (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog((Stage) addTable.getScene().getWindow());

        if (selectedFile != null) {
        	table.getColumns().clear();
        	CSVImporter.importCSV(selectedFile.getAbsolutePath());
        	DataTable dataTable = CSVImporter.getDataTable();
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
        	
        } else {
            System.out.println("Отменено пользователем.");
        }
    	
    }
    

}
