package application;

import data.DataTable;
import data.Row;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class DiagramCreator {

	private DiagramCreator() {
		
	}
	
	public static Chart createLineDiagram(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart, int rowEnd) {
    	if (xColumnIndex == -1 || yColumnIndex == -1) {
    		return null;
    	}
    	
    	NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();

        // Создание графика
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Добавление данных в график
        Series<Number, Number> dataSeries = new Series<>();
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        try {
    	    for (Row row : dataTable.getRows(rowStart, rowEnd)) {
	        	double item1 = Double.parseDouble(row.getItem(xColumnIndex).getValue());
	        	if (min > item1)
	        		min = item1;
	        	if (max < item1)
	        		max = item1;
	        	double item2 = Double.parseDouble(row.getItem(yColumnIndex).getValue());
	        
	        	dataSeries.getData().add(new Data<>(item1, item2));
	        }
		} catch(NumberFormatException e) {
	    	return null;
	    }
        lineChart.getData().add(dataSeries);

        // Настройка внешнего вида графика
        lineChart.setTitle("График таблицы " + tableName);

        xAxis.setLabel(dataTable.getColumns().get(xColumnIndex).getName());
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound((min - Math.abs(max - min) / 5) - 1);
        //xAxis.setLowerBound((int) xAxis.getLowerBound() / 100 * 100);
        
        xAxis.setUpperBound((max + Math.abs(max - min) / 5) + 1);
        //xAxis.setUpperBound((int) xAxis.getUpperBound() / 100 * 100);
        
        xAxis.setTickUnit(Math.abs(max - min) / 10);
        
        yAxis.setLabel(dataTable.getColumns().get(yColumnIndex).getName());

        return lineChart;

    } 
	
	public static Chart createAreaDiagram(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart, int rowEnd) {
	    if (xColumnIndex == -1 || yColumnIndex == -1) {
	        return null;
	    }

	    NumberAxis xAxis = new NumberAxis();
	    NumberAxis yAxis = new NumberAxis();

	    // Создание AreaChart
	    AreaChart<Number, Number> areaChart = new AreaChart<>(xAxis, yAxis);

	    // Добавление данных в график
	    Series<Number, Number> dataSeries = new Series<>();
	    double min = Double.MAX_VALUE;
	    double max = Double.MIN_VALUE;
	    try {
	    	for (Row row : dataTable.getRows(rowStart, rowEnd)) {
	    		double item1 = Double.parseDouble(row.getItem(xColumnIndex).getValue());
	    		if (min > item1)
	    			min = item1;
	    		if (max < item1)
	    			max = item1;
	    		double item2 = Double.parseDouble(row.getItem(yColumnIndex).getValue());
	    		dataSeries.getData().add(new Data<>(item1, item2));
	    	}	    	
	    } catch(NumberFormatException e) {
	    	return null;
	    }

	    // Добавление данных в виде площади
	    areaChart.getData().add(dataSeries);

	    // Настройка внешнего вида графика
	    areaChart.setTitle("График таблицы " + tableName);

	    xAxis.setLabel(dataTable.getColumns().get(xColumnIndex).getName());
	    xAxis.setAutoRanging(false);
	    xAxis.setLowerBound((min - Math.abs(max - min) / 5) - 1);
	    xAxis.setUpperBound((max + Math.abs(max - min) / 5) + 1);
	    xAxis.setTickUnit(Math.abs(max - min) / 10);

	    yAxis.setLabel(dataTable.getColumns().get(yColumnIndex).getName());

	    return areaChart;
	}
	
	public static Chart createBarChart(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart, int rowEnd) {
	    if (xColumnIndex == -1 || yColumnIndex == -1) {
	        return null;
	    }

	    CategoryAxis xAxis = new CategoryAxis();
	    NumberAxis yAxis = new NumberAxis();

	    // Создание BarChart
	    BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

	    // Добавление данных в график
	    XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
	    try {
		    for (Row row : dataTable.getRows(rowStart, rowEnd)) {
		        String item1 = row.getItem(xColumnIndex).getValue();
		        double item2 = Double.parseDouble(row.getItem(yColumnIndex).getValue());
		        dataSeries.getData().add(new XYChart.Data<>(item1, item2));
		    }
		} catch(NumberFormatException e) {
	    	return null;
	    }
	    // Добавление данных в виде столбцов
	    barChart.getData().add(dataSeries);

	    // Настройка внешнего вида графика
	    barChart.setTitle("График таблицы " + tableName);

	    xAxis.setLabel(dataTable.getColumns().get(xColumnIndex).getName());
	    yAxis.setLabel(dataTable.getColumns().get(yColumnIndex).getName());

	    return barChart;
	}
	
	public static XYChart<Number, Number> createScatterChart(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart, int rowEnd) {
	    if (xColumnIndex == -1 || yColumnIndex == -1) {
	        return null;
	    }

	    NumberAxis xAxis = new NumberAxis();
	    NumberAxis yAxis = new NumberAxis();

	    // Создание ScatterChart
	    ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

	    // Добавление данных в график
	    XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
	    double min = Double.MAX_VALUE;
	    double max = Double.MIN_VALUE;
	    try {
			for (Row row : dataTable.getRows(rowStart, rowEnd)) {
		    	double item1 = Double.parseDouble(row.getItem(xColumnIndex).getValue());
		        if (min > item1)
		            min = item1;
		        if (max < item1)
		            max = item1;
		        double item2 = Double.parseDouble(row.getItem(yColumnIndex).getValue());
		        dataSeries.getData().add(new XYChart.Data<>(item1, item2));
		    }
		} catch(NumberFormatException e) {
	    	return null;
	    }
	    // Добавление данных в виде точек
	    scatterChart.getData().add(dataSeries);

	    // Настройка внешнего вида графика
	    scatterChart.setTitle("График таблицы " + tableName);

	    xAxis.setLabel(dataTable.getColumns().get(xColumnIndex).getName());
	    xAxis.setAutoRanging(false);
	    xAxis.setLowerBound((min - Math.abs(max - min) / 5) - 1);
	    xAxis.setUpperBound((max + Math.abs(max - min) / 5) + 1);
	    xAxis.setTickUnit(Math.abs(max - min) / 10);

	    yAxis.setLabel(dataTable.getColumns().get(yColumnIndex).getName());

	    return scatterChart;
	}
	
	public static PieChart createPieChart(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart, int rowEnd) {
	    if (xColumnIndex == -1 || yColumnIndex == -1) {
	        return null;
	    }

	    // Создание PieChart
	    PieChart pieChart = new PieChart();

	    // Добавление данных в график
	    try {
		    for (Row row : dataTable.getRows(rowStart, rowEnd)) {
		        String itemName = row.getItem(xColumnIndex).getValue();
		        double itemValue = Double.parseDouble(row.getItem(yColumnIndex).getValue());
		        pieChart.getData().add(new PieChart.Data(itemName, itemValue));
		    }
		} catch(NumberFormatException e) {
	    	return null;
	    }
	    // Настройка внешнего вида графика
	    pieChart.setTitle("Диаграмма " + tableName);

	    return pieChart;
	}
}
