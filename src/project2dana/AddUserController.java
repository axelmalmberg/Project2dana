package project2dana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class AddUserController implements Initializable {
    private DbConnector db = new DbConnector();
    private String userName = null;
    private String password = null;
    private String eMail = null;
    private int phone = 0;
    private String address = null;
    private String city = null;
    private String sequrityQuestion = null;
    private String answer = null;
    private String fname = null;
    private String lname = null;

    @FXML
    private TextField userNameField, passwordField, sequrityQuestionField, answerField, firstNameField, lastNameField, phoneField, emailField, addressField, cityField;

    @FXML
    private Button submitButton, returnButton;
    
    @FXML
    private void handleaReturnButtonAction(ActionEvent event) {
        
        
        try {
            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
        } catch (Exception ex) {
            
        }
        
    }
    @FXML
        private void handleSubmitButtonAction(ActionEvent event) {
            
        try {
        userName = userNameField.getText();
        password = passwordField.getText();
        eMail = emailField.getText();
        phone = Integer.parseInt(phoneField.getText());
        address = addressField.getText();
        city = cityField.getText();
        sequrityQuestion = sequrityQuestionField.getId();
        answer = answerField.getText();
        fname = firstNameField.getText();
        lname = lastNameField.getText();
        
        db.addUser(userName, password, fname, lname, eMail, phone, address, city, sequrityQuestion, answer);
        
        } catch (Exception ex) {
            
        }
        
        
        
        
            
            
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
