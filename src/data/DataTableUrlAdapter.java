package data;

import java.util.HashMap;
import java.util.Map;

public class DataTableUrlAdapter {

	private static DataTableUrlAdapter dataTableUrlAdapter;
	
	private Map<String, String> dataTableUrls;
	
	private DataTableUrlAdapter() {
		dataTableUrls = new HashMap<>();
	}
	
	public static DataTableUrlAdapter getInstance() {
		if (dataTableUrlAdapter == null) {
			dataTableUrlAdapter = new DataTableUrlAdapter();
		}
		return dataTableUrlAdapter;
	}
	
	public void addDataTableUrl(String name, String url) {
		dataTableUrls.put(name, url);
	}
	
	public void getDataTableUrl(String name) {
		dataTableUrls.get(name);
	}
	
	public void removeUrl(String name) {
		dataTableUrls.remove(name);
	}
	
}
