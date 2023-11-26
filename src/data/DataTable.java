package data;

import java.util.ArrayList;
import java.util.List;

public class DataTable {

	private List<Column> columns;
	private List<Row> rows;
	
	public DataTable() {
		columns = new ArrayList<>();
		rows = new ArrayList<>();
	}

	public List<Column> getColumns() {
		return columns;
	}
	
	public void setColumns(String... columnNames) {
		for (String name : columnNames) {
			this.addColumn(name);
		}
	}
	
	public List<Row> getRows() {
		return rows;
	}
	
	public void addRow(Row row) {
		rows.add(row);
	}
	
	public void addRow(String... rowItems) {
		rows.add(new Row(rowItems));
	}
	
	public void addColumn(String columnName) {
		this.addColumn(new Column(columnName));
	}
	
	public void addColumn(Column column) {
		columns.add(column);
	}
	
	public void addColumn(int index, Column column) {
		columns.add(index, column);
	}
	
	public void removeColumn(int index) {
		columns.remove(index);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Column col : columns) {
			result.append(col.getName()).append("\t");
		}
		result.append("\n");
		for (Row row : rows) {
			result.append(row.toString()).append("\n");
		}
		result.append("\n");
		return result.toString();
	}
	
}
