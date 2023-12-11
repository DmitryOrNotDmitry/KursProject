package application;

import data.DataTable;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.paint.Color;

public enum Diagrams {

	LINE_CHART("LineChart") {
		@Override
		public void changeChartColor(Chart chart, Color color) {
			String rgbColor = Utils.getHexColorString(color);
	    	String style_stroke = "-fx-stroke:" + rgbColor + ";";
	    	for (XYChart.Series<?, ?> series : ((XYChart<?, ?>) chart).getData()) {
	    		Node line = series.getNode().lookup(".chart-series-line");
	    		if (line != null) {
	    			line.setStyle(style_stroke);
	    		}
	    	}
		}

		@Override
		public Chart create(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart,
				int rowEnd) {
			return DiagramCreator.createLineDiagram(tableName, dataTable, xColumnIndex, yColumnIndex, rowStart, rowEnd);
		}

		@Override
		public Settings[] getSettings() {
			Settings[] settings = {Settings.X_AXIS, Settings.Y_AXIS, Settings.COLOR, Settings.ROWS};
			return settings;
		}
	},
	AREA_CHART("AreaChart") {
		@Override
		public void changeChartColor(Chart chart, Color color) {
			String rgbStrokeColor = Utils.getHexColorString(color);
			String rgbFillColor = Utils.getHexColorString(new Color(color.getRed(), color.getGreen(), color.getBlue(), 0.3));
	    	String style_stroke = "-fx-stroke:" + rgbStrokeColor + ";";
	    	String style_fill = "-fx-fill:" + rgbFillColor + ";";
	    	for (XYChart.Series<?, ?> series : ((XYChart<?, ?>) chart).getData()) {
	    		Node line = series.getNode().lookup(".chart-series-area-line");
	    		Node area = series.getNode().lookup(".chart-series-area-fill");
    			if (line != null) {
    				line.setStyle(style_stroke);
    			}
    			if (area != null) {
    				area.setStyle(style_fill);
    			}
	    	}
		}

		@Override
		public Chart create(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart,
				int rowEnd) {
			return DiagramCreator.createAreaDiagram(tableName, dataTable, xColumnIndex, yColumnIndex, rowStart, rowEnd);
		}

		@Override
		public Settings[] getSettings() {
			Settings[] settings = {Settings.X_AXIS, Settings.Y_AXIS, Settings.COLOR, Settings.ROWS};
			return settings;
		}
	},
	BAR_CHART("BarChart") {
		@Override
		public void changeChartColor(Chart chart, Color color) {
			String rgbColor = Utils.getHexColorString(color);
	    	String style_fill = "-fx-bar-fill:" + rgbColor + ";";
	    	for (XYChart.Series<?, ?> series : ((XYChart<?, ?>) chart).getData()) {
	    		for (Data<?, ?> data : series.getData()) {
		            data.getNode().setStyle(style_fill);
		        }
	    	}
		}

		@Override
		public Chart create(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart,
				int rowEnd) {
			return DiagramCreator.createBarChart(tableName, dataTable, xColumnIndex, yColumnIndex, rowStart, rowEnd);
		}

		@Override
		public Settings[] getSettings() {
			Settings[] settings = {Settings.X_AXIS, Settings.Y_AXIS, Settings.COLOR, Settings.ROWS};
			return settings;
		}
	},
	SCATTER_CHART("ScatterChart") {
		@Override
		public void changeChartColor(Chart chart, Color color) {
	    	String rgbColor = Utils.getHexColorString(color);
	    	String style_stroke = "-fx-background-color:" + rgbColor + ";";
	    	for (XYChart.Series<?, ?> series : ((XYChart<?, ?>) chart).getData()) {
	    		for (Data<?, ?> data : series.getData()) {
		            data.getNode().setStyle(style_stroke);
		        }
	    	}
		}

		@Override
		public Chart create(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart,
				int rowEnd) {
			return DiagramCreator.createScatterChart(tableName, dataTable, xColumnIndex, yColumnIndex, rowStart, rowEnd);
		}

		@Override
		public Settings[] getSettings() {
			Settings[] settings = {Settings.X_AXIS, Settings.Y_AXIS, Settings.COLOR, Settings.ROWS};
			return settings;
		}
	},
	PIE_CHART("PieChart") {
		@Override
		public void changeChartColor(Chart chart, Color color) {
			
		}

		@Override
		public Chart create(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart,
				int rowEnd) {
			return DiagramCreator.createPieChart(tableName, dataTable, xColumnIndex, yColumnIndex, rowStart, rowEnd);
		}

		@Override
		public Settings[] getSettings() {
			Settings[] settings = {Settings.X_AXIS, Settings.Y_AXIS, Settings.ROWS};
			return settings;
		}
	};
	
	Diagrams(String name) {
		this.setName(name);
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Diagrams getDiagramByName(String name) {
		for (Diagrams diagram : Diagrams.values()) {
			if (diagram.getName().equals(name)) {
				return diagram;
			}
		}
		return LINE_CHART;
	}
	
	public abstract void changeChartColor(Chart chart, Color color);
	public abstract Chart create(String tableName, DataTable dataTable, int xColumnIndex, int yColumnIndex, int rowStart, int rowEnd);
	public abstract Settings[] getSettings();
}
