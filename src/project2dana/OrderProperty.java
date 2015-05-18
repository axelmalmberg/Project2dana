package project2dana;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bumblebee
 */
public class OrderProperty {
//    StringProperty drink2;
    
    private StringProperty timeStamp;
    private StringProperty drink;
//    private StringProperty drinkSize;
    private StringProperty appetizer;
    private StringProperty mainCourse;
    private StringProperty dessert;
    private StringProperty extra;
    private IntegerProperty tableNumber;
    private IntegerProperty id;
//    private DoubleProperty price;
    
    public OrderProperty(String timeStamp, String drink, String appetizer, String mainCourse, String dessert,
            String extra, int tableNumber, int id)  {
        this.drink = new SimpleStringProperty(drink);
//        this.drinkSize = new SimpleStringProperty(drinkSize);
        this.appetizer = new SimpleStringProperty (appetizer);
        this.mainCourse = new SimpleStringProperty (mainCourse);
        this.dessert = new SimpleStringProperty(dessert);
        this.extra = new SimpleStringProperty(extra);
        this.tableNumber = new SimpleIntegerProperty(tableNumber);
        this.timeStamp = new SimpleStringProperty(timeStamp);
        this.id = new SimpleIntegerProperty(id);
//        this.price = new SimpleDoubleProperty(price);
        
    }
    
    public void setDrink(String drink) {
        this.drink = new SimpleStringProperty(drink);
    }
//    public void setDrinkSize(String drinkSize) {
//        this.drinkSize = new SimpleStringProperty(drinkSize);
//    }
    public void setAppetizer(String appetizer) {
        this.appetizer = new SimpleStringProperty (appetizer);
    }
    public void setMainCourse(String mainCourse) {
        this.mainCourse = new SimpleStringProperty (mainCourse);
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