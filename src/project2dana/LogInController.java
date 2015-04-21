
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package project2dana;

import java.net.URL;
import java.sql.*;
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
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class LogInController implements Initializable {

    @FXML
    private TextField userNameField, passwordField;

    @FXML
    private Button signInButton;

    
    private String username = null;
    private String password = null;


    @FXML
    private void handleButtonAction(ActionEvent event) {

        username = userNameField.getText();
        password = passwordField.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // String URL = "jdbc:mysql://194.47.47.18:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
            String URL = "jdbc:mysql://127.0.0.1:3306/dana?user=root&password=root";
            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM login");
            while (rs.next()) {
                String tmpstr1 = rs.getString("username");
                String tmpstr2 = rs.getString("password");
                if (tmpstr1.equals(username) && tmpstr2.equals(password)) {
                    System.out.println("det funkar");
                    
                    
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } else {
                    System.out.println("FUUUUUUUUUUUCK YOUUU bruuuh");
                }
//                System.out.println("Customer Name: " + name + " \nand customer number " + nbr + "\n\n");
            }
            c.close();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
