package project2dana;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
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

    @FXML
    private TextField userNameField, passwordField, sequrityQuestionField, answerField, firstNameField, lastNameField, PhoneField, emailField, addressField, cityField;

    @FXML
    private Button submitButton, returnButton;

    @FXML
    private Label userAdded;

    @FXML
    private CheckBox adminBox;

    @FXML
    private void handleaReturnButtonAction(ActionEvent event) {

        try {

            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene(event, "MainMenu.fxml");
        } catch (Exception ex) {

        }

    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        String userName = null;
        String password = null;
        String eMail = null;
        String phone = null;
        String address = null;
        String city = null;
        String sequrityQuestion = null;
        String answer = null;
        String firstName = null;
        String lastName = null;
        int admin = 0;

        try {
            if (adminBox.isSelected()) {
                admin = 1;
            } else {
                admin = 2;
            }
            userName = userNameField.getText();
            System.out.println(userName);
            password = passwordField.getText();
            eMail = emailField.getText();
            System.out.println(eMail);
            phone = (PhoneField.getText());
            System.out.println(0);
            address = addressField.getText();
            city = cityField.getText();
            sequrityQuestion = sequrityQuestionField.getText();
            answer = answerField.getText();
            firstName = firstNameField.getText();
            lastName = lastNameField.getText();

            db.addUser(userName, password, firstName, lastName, eMail, phone, address, city, sequrityQuestion, answer, admin);

            userNameField.clear();
            passwordField.clear();
            emailField.clear();
            PhoneField.clear();
            addressField.clear();
            cityField.clear();
            sequrityQuestionField.clear();
            answerField.clear();
            firstNameField.clear();
            lastNameField.clear();

            userAdded.setText("User Added");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            userAdded.setText("Error");
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
