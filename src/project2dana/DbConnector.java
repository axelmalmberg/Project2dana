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

    
    
    
    
//    THIS IS ALL UNDER CONSTRUCTION, DONT REMOVE ISNT FINISHED
    
    public void addUser(String usrn, String psw, String fName, String lName, String mail, int phn, String adrs, String cty, String quest, String ans) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // String URL = "jdbc:mysql://194.47.47.18:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
            System.out.println("a");
            String URL = "jdbc:mysql://127.0.0.1:3306/dana?user=root&password=root";
            System.out.println("b");
            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            Statement st2 = c.createStatement();
            Statement st3 = c.createStatement();
            Statement st4 = c.createStatement();
            Statement st5 = c.createStatement();
            System.out.println("1");
            String tmp = String.format("insert into employee (firstname, lastname, email, phone, address, city) values ('%s', '%s', '%s', %d, '%s', '%s')",fName, lName, mail, phn, adrs, cty, quest, ans);
              st.executeUpdate(tmp);
            System.out.println("2");
            ResultSet rs = st2.executeQuery("SELECT idemployee FROM employee ORDER BY idemployee DESC LIMIT 1");
            int resultat = 0;
            if(rs.next()) {
                resultat = rs.getInt(1);
            }
            String tmp2 = String.format("insert into login (username, password, securityQuestion, securityAnswer, employee_idemployee) values ('%s', '%s', '%s', '%s', %d)", usrn, psw, quest, ans, resultat);
            st3.executeUpdate(tmp2);
//                    + " values (" + usrn + ", " + psw + " , " + quest + ", " + ans + ", " + resultat + ");");
            String tmp3 = String.format("CREATE USER '%s'@'localhost' IDENTIFIED BY '%s';", usrn, psw);
            String tmp4 = String.format("GRANT ALL PRIVILEGES ON * . * TO '%s'@'localhost'", usrn);
           
            st4.executeUpdate(tmp3);
            st5.executeUpdate(tmp4);
           
        }catch (Exception ex) {
           
        }
    }
    

//    THIS IS ALL UNDER CONSTRUCTION, DONT REMOVE ISNT FINISHED
    public void verifyLogIn(String usr, String psw, ActionEvent event) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // String URL = "jdbc:mysql://194.47.47.18:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
            String URL = "jdbc:mysql://127.0.0.1:3306/dana?user=" + usr + "&password=" + psw;
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
    }
}


