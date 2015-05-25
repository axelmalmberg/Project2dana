/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2dana.controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import project2dana.model.PendingOrderProperty;

/**
 *
 * @author Anesa
 */
public class FinishedOrderProperty extends PendingOrderProperty {

    private DoubleProperty price;
    private IntegerProperty salesId;
    
    public FinishedOrderProperty(String timeStamp, String drink, String drinkSize, String appetizer, String mainCourse,
            String dessert, String extra, int tableNumber, int id, double price, int salesId) {
        super(timeStamp, drink, drinkSize, appetizer, mainCourse, dessert, extra, tableNumber, id);
        this.salesId = new SimpleIntegerProperty(salesId);
        this.price = new SimpleDoubleProperty(price);
    }
    
        public DoubleProperty getPrice() {
        return price;
    }
        
        public IntegerProperty getSalesId() {
            return salesId;
        }
    
}
