package application;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import data.DataTable;
import data.DataTableAdapter;
import data.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

public class DiagramController {

	private DataTableAdapter dataAdapter;
	private String currentDataTableName;
	
	
    @FXML
    private GridPane mainGrid;

    @FXML
    private HBox tableNamesContainer;
    
    @FXML
    private BorderPane diagramContainer;
    
    @FXML
    private HBox diagramSettingsContainer;
    
    @FXML
    private ComboBox<String> xAxisColumn;

    @FXML
    private AnchorPane xAxisColumnContainer;

    @FXML
    private ComboBox<String> yAxisColumn;

    @FXML
    private AnchorPane yAxisColumnContainer;
    
    @FXML
    private ColorPicker colorChooser;

    @FXML
    private AnchorPane colorChooserContainer;
    
    @FXML
    private void initialize() {
    	dataAdapter = DataTableAdapter.getInstance();
    	ObservableList<String> names = FXCollections.observableArrayList(dataAdapter.getAllDataTableNames());
		for (String name : names) {
			Button button = new Button();
			button.setText(name);
			button.setPrefWidth(80);
			button.setMaxHeight(Double.MAX_VALUE);
			tableNamesContainer.getChildren().add(button);
			HBox.setMargin(button, new Insets(10));
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					onTableNameClick(e.getSource());
				}
			});;
		}
		
		
		//String tableName = "qwe.csv";
		//DataTable dataTable = dataAdapter.getDataTable(tableName);
		
		//createLineDiagram(tableName, dataTable, dataTable.getColumnNames().indexOf("Год"), dataTable.getColumnNames().indexOf("ВВП"));
    }
    
    private void createLineDiagram(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex) {
    	NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Создание графика
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Добавление данных в график
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Row row : dataTable.getRows()) {
        	int item1 = Integer.parseInt(row.getItem(xColumnIndex).getValue());
        	if (min > item1)
        		min = item1;
        	if (max < item1)
        		max = item1;
        	int item2 = Integer.parseInt(row.getItem(yColumnIndex).getValue());
        	dataSeries.getData().add(new XYChart.Data<>(item1, item2));
        }

        lineChart.getData().add(dataSeries);

        // Настройка внешнего вида графика
        lineChart.setTitle("График таблицы " + tableName);

        xAxis.setLabel(dataTable.getColumns().get(xColumnIndex).getName());
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(min - Math.abs(max - min) / 5);
        xAxis.setUpperBound(max + Math.abs(max - min) / 5);
        
        yAxis.setLabel(dataTable.getColumns().get(yColumnIndex).getName());

        //lineChart.setStyle("-fx-stroke: #0000ff");
        diagramContainer.setCenter(lineChart);

    }
    
    private void onTableNameClick(Object sender) {
    	diagramContainer.getChildren().clear();
    	
    	String tableName = ((Button) sender).getText();
    	DataTable dataTable = dataAdapter.getDataTable(tableName);
    	currentDataTableName = tableName;
    	
    	List<String> tableColumnsNames = dataTable.getColumnNames();
    	xAxisColumn.setItems(FXCollections.observableArrayList(tableColumnsNames));
    	yAxisColumn.setItems(FXCollections.observableArrayList(tableColumnsNames));
    	
    	if (xAxisColumn.getItems().size() >= 2) {
    		xAxisColumn.getSelectionModel().select(0);
        	yAxisColumn.getSelectionModel().select(1);

        	createLineDiagram(tableName, dataTable, dataTable.getColumnNames().indexOf(xAxisColumn.getSelectionModel().getSelectedItem()), dataTable.getColumnNames().indexOf(yAxisColumn.getSelectionModel().getSelectedItem()));
    	}
    	
//    	xAxisColumnContainer.setMaxWidth(0);
//    	xAxisColumnContainer.setMinWidth(0);
//    	xAxisColumnContainer.setVisible(false);
    }
    
    @FXML
    void changeAxisColumn(ActionEvent event) {
    	DataTable dataTable = dataAdapter.getDataTable(currentDataTableName);
    	if (xAxisColumn.getItems().size() >= 2) {
    		if (xAxisColumn.getSelectionModel().getSelectedIndex() != -1 && yAxisColumn.getSelectionModel().getSelectedIndex() != -1)
        	createLineDiagram(currentDataTableName, dataTable, dataTable.getColumnNames().indexOf(xAxisColumn.getSelectionModel().getSelectedItem()), dataTable.getColumnNames().indexOf(yAxisColumn.getSelectionModel().getSelectedItem()));
    	}
    }
    
    @FXML
    void changeColor(ActionEvent event) {
    	colorChooser.getValue();
    }

}

