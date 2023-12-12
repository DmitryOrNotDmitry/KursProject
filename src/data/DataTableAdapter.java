package data;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTableAdapter {

	private static DataTableAdapter dataTableAdapter;
	
	private Map<String, DataTable> dataTables;
	
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
	
	public void removeDataTable(String name) {
		this.dataTables.remove(name);
	}
	
	public void changeDataTableName(String oldName, String newName) {
		DataTable dataTable = this.getDataTable(oldName);
		this.removeDataTable(oldName);
		this.addDataTable(newName, dataTable);
	}
	
	public DataTable getDataTable(String name) {
		DataTable dataTable = this.dataTables.get(name);
		if (dataTable == null) {
			return null;
		}
		
		if (!dataTable.isLoaded()) {
			CSVImporter.importCSV(dataTable);
		}
		return dataTable;
	}
	
	public List<String> getAllDataTableNames(){
		List<String> result = new ArrayList<>();
		for (String key : this.dataTables.keySet()) {
			result.add(key);
		}
		return result;
	}
	
	public void writeObject(StringBuilder outBuffer) {
        for (String name : this.getAllDataTableNames()) {
        	outBuffer.append(name).append(";")
        		.append(this.getDataTable(name).getFile().getAbsolutePath()).append("\n");
        }
    }

	public void readObject(List<String> lines) {
        for (String line : lines) {
            DataTable dataTable = new DataTable();
            String[] fields = line.split(";");
            File file = new File(fields[1]);
            dataTable.setFile(file);
            this.addDataTable(fields[0], dataTable);
        }
    }
    
}
