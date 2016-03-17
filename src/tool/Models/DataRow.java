package tool.Models;

import javafx.beans.property.SimpleStringProperty;

/**
* Author: Phillipa Russell
* Student Number: 0900772r
* Creation: 23/11/2015.
*/
//DataRow object, used by tables
public class DataRow {
    private final SimpleStringProperty name;
    private final SimpleStringProperty value;

    /*DataRow constructor, creates a row in the table
    parameters: n - value for name column, v - value for value column
    returns: null
    */
    public DataRow(String n, String v) {
        this.name = new SimpleStringProperty(n);
        this.value = new SimpleStringProperty(v);
    }

    //getters and setters - some are used in run time DO NOT DELETE
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