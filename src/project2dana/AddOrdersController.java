/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2dana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author optimus prime
 */
public class AddOrdersController implements Initializable {
    
    @FXML
    private TextField addDrink, addDrinkSize, addAppetizer, addMainCourse, addDessert, addExtra, addTableNumber;
    
    @FXML
    private Button submitButton, returnButton;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
