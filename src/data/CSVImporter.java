package data;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVImporter {

	private static DataTable dataTable;
	
	public static DataTable importCSV(String path) {
		dataTable = new DataTable();
		dataTable.setFile(new File(path));
		importCSV(dataTable);
        return dataTable;
    }
	
	public static void importCSV(DataTable dataTable) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(dataTable.getFile().getAbsolutePath()))) {
            String line;
            if ((line = reader.readLine()) != null && !line.isEmpty()) {
            	line = line.strip().trim();
            	String[] headers = line.split(",");
            	dataTable.setColumns(headers);
            }
            
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
            	line = line.strip().trim();
            	String[] fields = line.split(",");
                dataTable.addRow(fields);
            }
        } catch (Exception e) {
        	System.out.println("File not exist - " + dataTable.getFile().getAbsolutePath());
        	//e.printStackTrace();
        }
    }
}
