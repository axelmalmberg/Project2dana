package project2dana.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import project2dana.model.DbConnector;
import project2dana.model.FtpDownload;
import project2dana.model.FtpUpload;
import project2dana.model.Order;
import project2dana.model.SceneSwitcher;

public class AddOrdersController implements Initializable {

    FtpDownload ftpDown = null;

    ObservableList<FoodProperty> FoodList = FXCollections.observableArrayList();
    DbConnector db = new DbConnector();

    ArrayList<Order> list = new ArrayList<>();
    Order order = null;
    FtpUpload ftp = null;

    @FXML
    private TextField addDrink, addDrinkSize, addAppetizer, addMainCourse, addDessert, addExtra, addTableNumber, addPrice;

    @FXML
    private Button submitButton, returnButton;

    @FXML
    private Label orderAdded, mustHavePrice, mustHaveSize, totalPrice;

    @FXML
    private ComboBox comboDrink;
    @FXML
    private ComboBox<String> comboAppetizer;
    @FXML
    private ComboBox<String> comboMainMeal;
    @FXML
    private ComboBox<String> comboDessert;
    @FXML
    private ComboBox<String> comboExtras;

    @FXML
    private ComboBox comboDrinkSize = new ComboBox();

    @FXML
    private void handleaddReturnButtonAction(ActionEvent event) {
        try {
            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene(event, "MainMenu.fxml");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        BufferedReader read = null;
        int tempNr = 0;
        double Totalprice;

        try {
            File f = new File("OrderList.ser");
            order = new Order();

            read = new BufferedReader(new FileReader("saveId.txt"));
            String tmpStr = null;
            tmpStr = read.readLine();
            int id = Integer.parseInt(tmpStr);

            read.close();

            if (addTableNumber.textProperty().get().isEmpty()) {
                tempNr = 0;
            } else {
                tempNr = Integer.parseInt(addTableNumber.getText());
            }

            FoodList = db.getFood();

            order.setDrink((String) comboDrink.getValue());  
            order.setAppetizer((String) comboAppetizer.getValue());
            order.setMainCourse((String) comboMainMeal.getValue());
            order.setDessert((String) comboDessert.getValue());
            order.setExtra((String) comboExtras.getValue());
            order.setTableNumber(tempNr);
            order.setPrice(Double.parseDouble(addPrice.getText()));
            order.setId(id);

            list.add(order);
            saveList();
            addToFTP();

            if (f.exists()) {
                f.delete();
            }

            orderAdded.setText("Order Added");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            orderAdded.setText("Check Fields");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readCombo();
        downloadFtp();
        File f = new File("OrderList.ser");

        if (!f.exists()) {
            return;
        }
        try {
            FileInputStream fileIn = new FileInputStream("OrderList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Order>) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void saveList() {
        try {
            FileOutputStream fileOut = new FileOutputStream("OrderList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void addToFTP() {
        ftp = new FtpUpload();
        ftp.startFTP("OrderList.ser");
    }

    public void downloadFtp() {

        File f = new File("OrderList.ser");
        if (f.exists()) {
            f.delete();
        }
        ftpDown = new FtpDownload();
        ftpDown.startFTP();

    }

    public void readCombo() {

        readDrinkSize();

        try {
            FoodList = db.getFood();
            for (int i = 0; i < FoodList.size(); i++) {

                comboDrink.getItems().add(FoodList.get(i).getDrink());
                comboAppetizer.getItems().add(FoodList.get(i).getAppetizer());
                comboMainMeal.getItems().add(FoodList.get(i).getMainMeal());
                comboDessert.getItems().add(FoodList.get(i).getDessert());
                comboExtras.getItems().add(FoodList.get(i).getExtras());

            }

        } catch (Exception ex) {
            System.out.println("Funka ej");
        }

    }

    public void readDrinkSize() {
        comboDrinkSize.getItems().addAll("25cl", "33cl", "50cl", "1.5L");
    }
}
