package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import data.DataTableAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

public class DiagramController {

    @FXML
    private GridPane mainGrid;

    @FXML
    private HBox tableNamesContainer;
    
    @FXML
    private BorderPane diagramContainer;
    
    private DataTableAdapter dataAdapter;
    
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
			tableNamesContainer.setMargin(button, new Insets(10));
		}
		
		NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Создание графика
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Добавление данных в график
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        dataSeries.getData().add(new XYChart.Data<>(1, 25));
        dataSeries.getData().add(new XYChart.Data<>(2, 50));
        dataSeries.getData().add(new XYChart.Data<>(3, 15));
        dataSeries.getData().add(new XYChart.Data<>(4, 30));

        lineChart.getData().add(dataSeries);

        // Настройка внешнего вида графика
        lineChart.setTitle("График с использованием NumberAxis");
        xAxis.setLabel("Дата");
        yAxis.setLabel("Значения");

        // Настройка формата даты на оси X
        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis) {
            @Override
            public String toString(Number object) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
                return sdf.format(new Date(object.longValue()));
            }
        });
        
        diagramContainer.setCenter(lineChart);

    }

}

