/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2dana;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Anesa
 */
public class FinishedOrderProperty extends PendingOrderProperty {

    private DoubleProperty price;
    
    public FinishedOrderProperty(String timeStamp, String drink, String drinkSize, String appetizer, String mainCourse, String dessert, String extra, int tableNumber, int id, double price) {
        super(timeStamp, drink, drinkSize, appetizer, mainCourse, dessert, extra, tableNumber, id);
        
        this.price = new SimpleDoubleProperty(price);
    }
    
        public DoubleProperty getPrice() {
        return price;
    }
    
}
