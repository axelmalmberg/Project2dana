/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2dana;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author bumblebee
 */
public class Order implements Serializable {
    
    StringProperty drink2;
    
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    String drink;
    String drinkSize;
    String appetizer;
    String mainCourse;
    String dessert;
    String extra;
    int tableNumber;
    int id = 1;
    
    public void setDrink(String drink) {
        this.drink = drink;
        this.drink2 = new SimpleStringProperty(drink);
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
    public StringProperty getDrinkProperty() {
        return drink2;
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
}