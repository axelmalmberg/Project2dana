package project2dana.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order implements Serializable {

    private String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    private String drink;
    private String drinkSize;
    private String appetizer;
    private String mainCourse;
    private String dessert;
    private String extra;
    private int tableNumber;
    private int id;
    private double price;

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }

    public void setAppetizer(String appetizer) {
        this.appetizer = appetizer;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDrink() {
        return drink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkSize() {
        return drinkSize;
    }

    public String getAppetizer() {
        return appetizer;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public String getDessert() {
        return dessert;
    }

    public String getExtra() {
        return extra;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getDate() {
        return timeStamp;
    }

    public int getId() {
        return id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public double getPrice() {
        return price;
    }
}
