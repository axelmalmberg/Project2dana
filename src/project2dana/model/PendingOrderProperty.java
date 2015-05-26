package project2dana.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PendingOrderProperty extends OrderProperty {

    private StringProperty drinkSize;

    public PendingOrderProperty(String timeStamp, String drink, String drinkSize, String appetizer, String mainCourse, String dessert, String extra, int tableNumber, int id) {
        super(timeStamp, drink, appetizer, mainCourse, dessert, extra, tableNumber, id);
        this.drinkSize = new SimpleStringProperty(drinkSize);
    }

    public StringProperty getDrinkSize() {
        return drinkSize;
    }

}
