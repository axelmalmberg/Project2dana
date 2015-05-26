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
import javafx.scene.control.TextField;
import project2dana.model.DbConnector;
import project2dana.model.SceneSwitcher;

/**
 * FXML Controller class
 *
 * @author bumblebee
 */
public class SequrityQuestionController implements Initializable {
    SceneSwitcher ss = new SceneSwitcher();
    DbConnector db = new DbConnector();
    private String username;
    private String question;
    private int idlogin;
    
    @FXML
    private TextField usernameField;
    @FXML
    private TextField securityQuestionField;
    @FXML
    private TextField answerField;
    @FXML
    private TextField passwordField;
    
    @FXML
    private Button firstNextButton;
    @FXML
    private Button secondNextButton;
    @FXML
    private Button finishButton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        securityQuestionField.setVisible(false);
        answerField.setVisible(false);
        passwordField.setVisible(false);
        secondNextButton.setVisible(false);
        finishButton.setVisible(false);
    }    
    
    @FXML
    private void handleFirstNextButtonAction(ActionEvent event) {
        try {
        String tmpUsr = usernameField.getText();
        
        if (db.checkExistingUser(tmpUsr) == true) {
            username = tmpUsr;
            firstNextButton.setVisible(false);
            secondNextButton.setVisible(true);
            securityQuestionField.setVisible(true);
            answerField.setVisible(true);
            
            idlogin = db.getIdLogin(username);
            question = db.getSecurityQuestion(username);
            securityQuestionField.setText(question);
            
            
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    private void handleSecondNextButtonAction(ActionEvent event) {
       try {
        String tmpAnswer = answerField.getText();
        
        if (db.verifyQuestion(username, question, tmpAnswer) == true) {
            secondNextButton.setVisible(false);
            passwordField.setVisible(true);
            finishButton.setVisible(true);
        }
       } catch (Exception ex) {
           ex.printStackTrace();
       }
    }
    
    @FXML
    private void handleFinishButtonAction(ActionEvent event) {
        try {
        String tmpPassword = passwordField.getText();
        db.updatePassword(username, tmpPassword, idlogin);
        ss.switchScene(event, "LogInScene.fxml");
        } catch (Exception ex) {
            
        }
    }
    @FXML void handleReturnButtonAction(ActionEvent event) {
        ss.switchScene(event, "LogInScene.fxml");
    }
    
    
    
}
