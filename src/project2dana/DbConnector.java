/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2dana;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
public class DbConnector  {

    private String URL;

//    THIS IS ALL UNDER CONSTRUCTION, DONT REMOVE ISNT FINISHED
    public void addUser(String usrn, String psw, String fName, String lName, String mail, int phn, String adrs, String cty, String quest, String ans) {
        try {
            connect();
            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            Statement st2 = c.createStatement();
            Statement st3 = c.createStatement();
            Statement st4 = c.createStatement();
            Statement st5 = c.createStatement();
            System.out.println("1");
            String tmp = String.format("insert into employee (firstname, lastname, email, phone, address, city) values ('%s', '%s', '%s', %d, '%s', '%s')", fName, lName, mail, phn, adrs, cty, quest, ans);
            st.executeUpdate(tmp);
            System.out.println("2");
            ResultSet rs = st2.executeQuery("SELECT idemployee FROM employee ORDER BY idemployee DESC LIMIT 1");
            int resultat = 0;
            if (rs.next()) {
                resultat = rs.getInt(1);
            }
            String tmp2 = String.format("insert into login (username, password, securityQuestion, securityAnswer, employee_idemployee) values ('%s', '%s', '%s', '%s', %d)", usrn, psw, quest, ans, resultat);
            st3.executeUpdate(tmp2);
//                    + " values (" + usrn + ", " + psw + " , " + quest + ", " + ans + ", " + resultat + ");");
            String tmp3 = String.format("CREATE USER '%s'@'localhost' IDENTIFIED BY '%s';", usrn, psw);
            String tmp4 = String.format("GRANT ALL PRIVILEGES ON * . * TO '%s'@'localhost' WITH GRANT OPTION", usrn);

            st4.executeUpdate(tmp3);
            st5.executeUpdate(tmp4);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

//    THIS IS ALL UNDER CONSTRUCTION, DONT REMOVE ISNT FINISHED
    public void verifyLogIn(String usr, String psw, ActionEvent event) {
        FileWriter save = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL2 = "jdbc:mysql://127.0.0.1:3306/dana?user=" + usr + "&password=" + psw;
            Connection c = DriverManager.getConnection(URL2);
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

                    save = new FileWriter("SaveUserInfo.txt", true);
                    save.write(usr + ":" + psw);

                    break;

                } else {
                    System.out.println("login denied");
                }
//                System.out.println("Customer Name: " + name + " \nand customer number " + nbr + "\n\n");
            }
            c.close();
            save.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void connect() {
        File f = new File("SaveUserInfo.txt");
        BufferedReader read = null;
        try {
            if (f.exists()) {
                read = new BufferedReader(new FileReader("SaveUserInfo.txt"));
                String tmpStr = null;
                tmpStr = read.readLine();
                String[] tmpArray = tmpStr.split(":");
                String tmpUrl = "jdbc:mysql://127.0.0.1:3306/dana?user=" + tmpArray[0] + "&password=" + tmpArray[1];
                
                System.out.println(tmpArray[0] + tmpArray[1]);
                
            
        
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // String URL = "jdbc:mysql://194.47.47.18:3306/YOUR_DATABASE_NAME?user=YOUR_USER_NAME&password=YOUR_PASSWORD";
            URL = tmpUrl;
            } else {
                System.out.println("error");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                read.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
