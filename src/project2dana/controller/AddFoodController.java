package project2dana.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import project2dana.model.DbConnector;
import project2dana.model.SceneSwitcher;

/**
 * FXML Controller class
 *
 * @author dardaiin
 */
public class AddFoodController implements Initializable {
    
    private DbConnector db = new DbConnector();
    
    @FXML
    private TextField mealField, categoryField, priceField;
    
    @FXML
    private Button submitButton, returnButton;
    
    @FXML
    ComboBox comboCategory = new ComboBox();

    @FXML
    private void handleReturnButtonAction(ActionEvent event) {

        try {

            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene(event, "MainMenu.fxml");
        } catch (Exception ex) {

        }

    }
    
     @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        String mealName;
        String categoryName;
        String price;

        try {

            mealName = mealField.getText();
            categoryName = (String) comboCategory.getValue();
            price = priceField.getText();


            db.addFood(mealName, categoryName, price);

            mealField.clear();
            priceField.clear();


            System.out.println("Food added");
            

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            
        }

    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readComboCategory();
    }    
        public void readComboCategory() {
        comboCategory.getItems().addAll("Drink","Appetizer","Main course","Dessert","Extras");
    }
}
