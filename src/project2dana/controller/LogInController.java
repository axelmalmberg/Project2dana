package project2dana.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project2dana.model.DbConnector;
import project2dana.model.SceneSwitcher;

public class LogInController implements Initializable {

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;
        
    @FXML
    private Button exitButton;

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
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
            File f2 = new File("saveId.txt");
            File f = new File("SaveUserInfo.txt");
            File f3 = new File("saveStatus.txt");
            File f4 = new File("OrderList.ser");
            f3.delete();
            f.delete();
            f2.delete();
            f3.delete();
            System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
