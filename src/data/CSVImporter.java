package data;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVImporter {

	private static DataTable dataTable;
	
	public static void importCSV(String path) {
		dataTable = new DataTable();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            if ((line = reader.readLine()) != null) {
            	String[] headers = line.split(",");
            	dataTable.setColumns(headers);
            }
            
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                dataTable.addRow(fields);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.print(dataTable);
    }
	
	public static DataTable getDataTable() {
		return dataTable;
    }
}
