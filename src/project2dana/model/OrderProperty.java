package project2dana.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class OrderProperty {

    private StringProperty timeStamp;
    private StringProperty drink;

    private StringProperty appetizer;
    private StringProperty mainCourse;
    private StringProperty dessert;
    private StringProperty extra;
    private IntegerProperty tableNumber;
    private IntegerProperty id;

    public OrderProperty(String timeStamp, String drink, String appetizer, String mainCourse, String dessert,
            String extra, int tableNumber, int id) {
        this.drink = new SimpleStringProperty(drink);

        this.appetizer = new SimpleStringProperty(appetizer);
        this.mainCourse = new SimpleStringProperty(mainCourse);
        this.dessert = new SimpleStringProperty(dessert);
        this.extra = new SimpleStringProperty(extra);
        this.tableNumber = new SimpleIntegerProperty(tableNumber);
        this.timeStamp = new SimpleStringProperty(timeStamp);
        this.id = new SimpleIntegerProperty(id);

    }

    public void setDrink(String drink) {
        this.drink = new SimpleStringProperty(drink);
    }

    public void setAppetizer(String appetizer) {
        this.appetizer = new SimpleStringProperty(appetizer);
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = new SimpleStringProperty(mainCourse);
    }

    public void setDessert(String dessert) {
        this.dessert = new SimpleStringProperty(dessert);
    }

    public void setExtra(String extra) {
        this.extra = new SimpleStringProperty(extra);
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = new SimpleIntegerProperty(tableNumber);
    }

    public StringProperty getDrink() {
        return drink;
    }

    public StringProperty getAppetizer() {
        return appetizer;
    }

    public StringProperty getMainCourse() {
        return mainCourse;
    }

    public StringProperty getDessert() {
        return dessert;
    }

    public StringProperty getExtra() {
        return extra;
    }

    public IntegerProperty getTableNumber() {
        return tableNumber;
    }

    public StringProperty getDate() {
        return timeStamp;
    }

    public IntegerProperty getId() {
        return id;
    }
}
