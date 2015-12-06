package tool.Models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 23/11/2015.
 */
public class DataRow {
    private final SimpleStringProperty name;
    private final SimpleStringProperty value;

    public DataRow(String n, String v) {
        this.name = new SimpleStringProperty(n);
        this.value = new SimpleStringProperty(v);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }
}
