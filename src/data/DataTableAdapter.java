package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableAdapter {

	private static DataTableAdapter dataTableAdapter;
	
	Map<String, DataTable> dataTables;
	
	private DataTableAdapter() {
		dataTables = new HashMap<>();
	}
	
	public static DataTableAdapter getInstance() {
		if (dataTableAdapter == null) {
			dataTableAdapter = new DataTableAdapter();
		}
		return dataTableAdapter;
	}
	
	public void addDataTable(String name, DataTable dataTable) {
		this.dataTables.put(name, dataTable);
	}
	
	public DataTable getDataTable(String name) {
		return this.dataTables.get(name);
	}
	
	public List<String> getAllDataTableNames(){
		List<String> result = new ArrayList<>();
		for (String key : this.dataTables.keySet()) {
			result.add(key);
		}
		return result;
	}
	
}
