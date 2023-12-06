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
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.util.StringConverter;

public class DiagramController {

	private DataTableAdapter dataAdapter;
	private String currentDataTableName;
	private Diagrams currentDiagram;
	
	
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
    private AnchorPane rowChooserContainer;

    @FXML
    private Spinner<Integer> rowEnd;

    @FXML
    private Spinner<Integer> rowStart;
    
    @FXML
    private HBox diagramTypeContainer;
    
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
			});
		}
		
		for (Diagrams diagram : Diagrams.values()) {
			Button button = new Button();
			button.setText(diagram.getName());
			button.setPrefWidth(80);
			button.setMaxHeight(Double.MAX_VALUE);
			diagramTypeContainer.getChildren().add(button);
			HBox.setMargin(button, new Insets(10));
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					currentDiagram = Diagrams.getDiagramByName(((Button) e.getSource()).getText());
					drawDiagram(dataAdapter.getDataTable(currentDataTableName));
				}
			});
		}
		
		rowStart.valueProperty().addListener((a, b, c) -> { changeRows(b, c); });
		rowEnd  .valueProperty().addListener((a, b, c) -> { changeRows(b, c); });
		
		currentDiagram = Diagrams.AREA_CHART;
    }
    
    private void onTableNameClick(Object sender) {
    	diagramContainer.getChildren().clear();
    	
    	String tableName = ((Button) sender).getText();
    	DataTable dataTable = dataAdapter.getDataTable(tableName);
    	currentDataTableName = tableName;
    	
    	int countRows = dataTable.getRows().size();
    	rowStart.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, countRows, 1));
    	rowEnd.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, countRows, countRows));
    	
    	List<String> tableColumnsNames = dataTable.getColumnNames();
    	xAxisColumn.setItems(FXCollections.observableArrayList(tableColumnsNames));
    	yAxisColumn.setItems(FXCollections.observableArrayList(tableColumnsNames));
    	
    	if (xAxisColumn.getItems().size() >= 2) {
    		xAxisColumn.getSelectionModel().select(0);
        	yAxisColumn.getSelectionModel().select(1);

        	drawDiagram(dataTable);
        	
    	}
    }
    
    private void drawDiagram(DataTable dataTable) {
		if (currentDiagram == Diagrams.LINE_CHART) {
			loadLineDiagram(dataTable);
			return;
		}
		
		if (currentDiagram == Diagrams.AREA_CHART) {
			loadAreaDiagram(dataTable);
			return;
		}
		
		if (currentDiagram == Diagrams.BAR_CHART) {
			loadBarDiagram(dataTable);
			return;
		}
		
		if (currentDiagram == Diagrams.BUBBLE_CHART) {
			return;
		}
		
		if (currentDiagram == Diagrams.PIE_CHART) {
			return;
		}
	}

	@FXML
    void changeAxisColumn(ActionEvent event) {
    	DataTable dataTable = dataAdapter.getDataTable(currentDataTableName);
    	if (xAxisColumn.getItems().size() >= 2) {
    		if (xAxisColumn.getSelectionModel().getSelectedIndex() != -1 && yAxisColumn.getSelectionModel().getSelectedIndex() != -1)
    			drawDiagram(dataTable);
    	}
    }
    
    @FXML
    void changeColor(ActionEvent event) {
    	if (diagramContainer.getChildren().size() > 0) {
    		Color color = colorChooser.getValue();
        	XYChart chart = (XYChart) diagramContainer.getChildren().get(0);
        	changeChartColor(chart, color);
    	}
    	
    }
    
    private void changeChartColor(XYChart<?, ?> chart, Color color) {
    	currentDiagram.changeChartColor(chart, color);
    }
    
    void changeRows(Integer oldValue, Integer newValue) {
    	if (rowStart.getValue() == null || rowEnd.getValue() == null || rowStart.getValue() > rowEnd.getValue()) {
    		return;
    	} 
    	
    	diagramContainer.getChildren().clear();
    	DataTable dataTable = dataAdapter.getDataTable(currentDataTableName);
    	if (xAxisColumn.getItems().size() >= 2) {
    		if (xAxisColumn.getSelectionModel().getSelectedIndex() != -1 && yAxisColumn.getSelectionModel().getSelectedIndex() != -1)
    			drawDiagram(dataTable);
    	}
    }
    
    public void loadLineDiagram(DataTable dataTable) {
    	XYChart chart = DiagramCreator.createLineDiagram(currentDataTableName, dataTable, dataTable.getColumnNames().indexOf(xAxisColumn.getSelectionModel().getSelectedItem()), dataTable.getColumnNames().indexOf(yAxisColumn.getSelectionModel().getSelectedItem()), rowStart.getValue() - 1, rowEnd.getValue());
    	if (chart == null) {
    		return;
    	}
    	
    	changeChartColor(chart, colorChooser.getValue());
        diagramContainer.setCenter(chart);
    }
    
    public void loadAreaDiagram(DataTable dataTable) {
    	XYChart chart = DiagramCreator.createAreaDiagram(currentDataTableName, dataTable, dataTable.getColumnNames().indexOf(xAxisColumn.getSelectionModel().getSelectedItem()), dataTable.getColumnNames().indexOf(yAxisColumn.getSelectionModel().getSelectedItem()), rowStart.getValue() - 1, rowEnd.getValue());
    	if (chart == null) {
    		return;
    	}
    	
    	changeChartColor(chart, colorChooser.getValue());
        diagramContainer.setCenter(chart);
    }
    
    public void loadBarDiagram(DataTable dataTable) {
    	XYChart chart = DiagramCreator.createBarChart(currentDataTableName, dataTable, dataTable.getColumnNames().indexOf(xAxisColumn.getSelectionModel().getSelectedItem()), dataTable.getColumnNames().indexOf(yAxisColumn.getSelectionModel().getSelectedItem()), rowStart.getValue() - 1, rowEnd.getValue());
    	if (chart == null) {
    		return;
    	}
    	
    	changeChartColor(chart, colorChooser.getValue());
        diagramContainer.setCenter(chart);
    }

}

