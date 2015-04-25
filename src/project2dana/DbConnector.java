/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2dana;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bumblebee
 */
public class DbConnector {
    
    
    
    
    public void verifyLogIn(String usr, String psw, ActionEvent event) {
        try {
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
                if (tmpstr1.equals(usr) && tmpstr2.equals(psw)) {
                    System.out.println("det funkar");
                    
                    
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    
                    break;
                    
                    
                } else {
                    System.out.println("login denied");
                }
//                System.out.println("Customer Name: " + name + " \nand customer number " + nbr + "\n\n");
            }
            c.close();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
        }
        } catch (Exception ex) {
            
        }
    }
    
    
    
}
 