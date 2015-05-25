/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2dana.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Anesa
 */
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
