package project2dana.model;

import project2dana.controller.FinishedOrderProperty;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class DbConnector {

    FinishedOrderProperty op = null;
    private static final int ADMIN = 1;
    private String URL;
    ObservableList<FinishedOrderProperty> ObList = FXCollections.observableArrayList();

    public void addSale(String date, int tableNr, double price, int employeeId, String appetizer, String dessert, String drink, String extras, String mainCourse, String drinkSize) {
        try {
            connect();
            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            String tmpPrice = Double.toString(price);
            String tmp = String.format("insert into sales (dateString, tableNumber, totalPrice, employee_idemployee, appetizer, dessert, drink, extras, mainMeal, drinkSize) values ('%s', %d, '%s', %d, '%s', '%s', '%s', '%s', '%s', '%s')", date, tableNr, tmpPrice, employeeId, appetizer, dessert, drink, extras, mainCourse, drinkSize);
            st.executeUpdate(tmp);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void addUser(String userName, String password, String firstName, String lastName, String mail, String phone, String adress, String city, String quest, String ans, int admin) {
        try {
            connect();
            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            Statement st2 = c.createStatement();
            Statement st3 = c.createStatement();
            Statement st4 = c.createStatement();
            Statement st5 = c.createStatement();
            String tmp = String.format("insert into employee (firstname, lastname, email, phone, address, city) values ('%s', '%s', '%s', '%s', '%s', '%s')", firstName, lastName, mail, phone, adress, city, quest, ans);
            st.executeUpdate(tmp);
            ResultSet rs = st2.executeQuery("SELECT idemployee FROM employee ORDER BY idemployee DESC LIMIT 1");
            int resultat = 0;
            if (rs.next()) {
                resultat = rs.getInt(1);
            }
            String tmp2 = String.format("insert into login (username, password, securityQuestion, securityAnswer, employee_idemployee, status) values ('%s', '%s', '%s', '%s', %d, %d)", userName, password, quest, ans, resultat, admin);
            st3.executeUpdate(tmp2);

            if (admin == ADMIN) {
                String tmp3 = String.format("CREATE USER '%s'@'' IDENTIFIED BY '%s';", userName, password);
                String tmp4 = String.format("GRANT ALL PRIVILEGES ON * . * TO '%s'@'' WITH GRANT OPTION", userName);

                st4.executeUpdate(tmp3);
                st5.executeUpdate(tmp4);
            } else {
                String tmp3 = String.format("CREATE USER '%s'@'' IDENTIFIED BY '%s';", userName, password);
                String tmp4 = String.format("GRANT INSERT, SELECT ON * . * TO '%s'@''", userName);

                st4.executeUpdate(tmp3);
                st5.executeUpdate(tmp4);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void verifyLogIn(String userName, String password, ActionEvent event) {
        FileWriter save = null;
        FileWriter saveId = null;
        FileWriter saveStatus = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL2 = "jdbc:mysql://192.168.1.77:3306/dana?user=" + userName + "&password=" + password;
            Connection c = DriverManager.getConnection(URL2);
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM login");
            while (rs.next()) {
                String tmpstr1 = rs.getString("username");
                String tmpstr2 = rs.getString("password");
                if (tmpstr1.equals(userName) && tmpstr2.equals(password)) {
                    System.out.println("Login success!");

                    int id = rs.getInt("employee_idemployee");
                    int status = rs.getInt("status");
                    String status2 = Integer.toString(status);

                    String id2 = Integer.toString(id);

                    saveId = new FileWriter("saveId.txt", true);
                    saveId.write(id2);

                    save = new FileWriter("SaveUserInfo.txt", true);
                    save.write(userName + ":" + password);

                    saveStatus = new FileWriter("saveStatus.txt", true);
                    saveStatus.write(status2);
                    saveStatus.close();

                    SceneSwitcher ss = new SceneSwitcher();
                    ss.switchScene(event, "MainMenu.fxml");

                    break;

                } else {
                    System.out.println("Login denied!");
                }

            }
            c.close();
            save.close();
            saveId.close();

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
                String tmpUrl = "jdbc:mysql://192.168.1.77:3306/dana?user=" + tmpArray[0] + "&password=" + tmpArray[1];

                Class.forName("com.mysql.jdbc.Driver").newInstance();

                URL = tmpUrl;
            } else {
                System.out.println("Couldn't connect to database!");
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

    public ObservableList<FinishedOrderProperty> getSales() {

        try {
            connect();
            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM sales");

            while (rs.next()) {

                int idSales = rs.getInt("idsales");
                String date = rs.getString("dateString");
                int tableNr = rs.getInt("tableNumber");
                double price = rs.getDouble("totalPrice");
                int employeeId = rs.getInt("employee_idemployee");
                String appetizer = rs.getString("appetizer");
                String dessert = rs.getString("dessert");
                String drink = rs.getString("drink");
                String extras = rs.getString("extras");
                String mainCourse = rs.getString("mainMeal");
                String drinkSize = rs.getString("drinkSize");
                op = new FinishedOrderProperty(date, drink, drinkSize, appetizer, mainCourse, dessert, extras, tableNr, employeeId, price, idSales);

                ObList.add(op);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return ObList;
    }

    public void deleteSale(int salesId) {
        try {
            connect();
            Connection c = DriverManager.getConnection(URL);
            Statement st = c.createStatement();
            String tmpStr = String.format("DELETE FROM sales WHERE idsales = %d", salesId);
            st.executeUpdate(tmpStr);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Boolean checkExistingUser(String userName) {
        Boolean exists = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL2 = "jdbc:mysql://192.168.1.77:3306/dana?user=root&password=root";
            Connection c = DriverManager.getConnection(URL2);
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM login");
            while (rs.next()) {
                String tmpstr1 = rs.getString("username");
                if (tmpstr1.equals(userName)) {
                    exists = true;
                    break;
                } else {

                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exists;
    }

    public String getSecurityQuestion(String userName) {
        String question = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL2 = "jdbc:mysql://192.168.1.77:3306/dana?user=root&password=root";
            Connection c = DriverManager.getConnection(URL2);
            Statement st = c.createStatement();
            String tmpStr = String.format("SELECT securityQuestion FROM login WHERE username = '%s' ", userName);
            ResultSet rs = st.executeQuery(tmpStr);
            while (rs.next()) {
                question = rs.getString("securityQuestion");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return question;
    }

    public int getIdLogin(String userName) {
        int tmpid = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL2 = "jdbc:mysql://192.168.1.77:3306/dana?user=root&password=root";
            Connection c = DriverManager.getConnection(URL2);
            Statement st = c.createStatement();
            String tmpStr = String.format("SELECT idlogin FROM login WHERE username = '%s'", userName);
            ResultSet rs = st.executeQuery(tmpStr);
            while (rs.next()) {
                tmpid = rs.getInt("idlogin");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tmpid;
    }

    public Boolean verifyQuestion(String userName, String question, String answer) {
        Boolean correct = false;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL2 = "jdbc:mysql://192.168.1.77:3306/dana?user=root&password=root";
            Connection c = DriverManager.getConnection(URL2);
            Statement st = c.createStatement();
            String tmpStr = String.format("SELECT securityQuestion, securityAnswer FROM login WHERE username = '%s'", userName);
            ResultSet rs = st.executeQuery(tmpStr);
            while (rs.next()) {
                String tmpQuestion = rs.getString("securityQuestion");
                String tmpAnswer = rs.getString("securityAnswer");
                if (tmpAnswer.equals(answer) && tmpQuestion.equals(question)) {
                    correct = true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return correct;
    }

    public void updatePassword(String userName, String password, int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL2 = "jdbc:mysql://192.168.1.77:3306/dana?user=root&password=root";
            Connection c = DriverManager.getConnection(URL2);
            Statement st = c.createStatement();
            Statement st2 = c.createStatement();
            String tmpStr = String.format("UPDATE login SET password = '%s' WHERE idlogin = %d", password, id);
            String tmpStr2 = String.format("SET PASSWORD FOR '%s'@'' = PASSWORD('%s')", userName, password);
            st.executeUpdate(tmpStr);
            st.executeUpdate(tmpStr2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
