/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2dana.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project2dana.model.DbConnector;
import project2dana.model.SceneSwitcher;

/**
 *
 * @author bumblebee
 */
public class LogInController implements Initializable {

    @FXML
    private TextField userNameField;
    
    @FXML
    private PasswordField passwordField;
   
    @FXML
    private Button signInButton;

    private String username = null;
    private String password = null;
    private DbConnector Db = new DbConnector();

    @FXML
    private void handleButtonAction(ActionEvent event) {

        username = userNameField.getText();
        password = passwordField.getText();
        Db.verifyLogIn(username, password, event);
       
    }
    @FXML
    private void handleForgotPasswordButtonAction(ActionEvent event) {
        SceneSwitcher ss = new SceneSwitcher();
        ss.switchScene(event, "SequrityQuestion.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
