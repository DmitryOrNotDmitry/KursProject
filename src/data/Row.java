package data;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;

public class Row {

	private List<SimpleStringProperty> items;

	public Row() {
		items = new ArrayList<>();
	}
	
	public Row(String... items) {
		this.items = new ArrayList<>();
		this.setItems(items);
	}
	
	public List<SimpleStringProperty> getItems() {
		return items;
	}
	
	public SimpleStringProperty getItem(int index) {
		return items.get(index);
	}

	public void setItems(List<SimpleStringProperty> items) {
		this.items = items;
	}
	
	public void setItems(String... items) {
		for (String item : items) {
			this.items.add(new SimpleStringProperty(item));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (SimpleStringProperty item: items) {
			result.append(item).append("\t");
		}
		result.append("\n");
		return result.toString();
	}
	
}
