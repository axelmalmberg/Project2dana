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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project2dana.model.FtpDownload;
import project2dana.model.FtpUpload;
import project2dana.model.Order;
import project2dana.model.SceneSwitcher;

/**
 * FXML Controller class
 *
 * /**
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class AddOrdersController implements Initializable {

    FtpDownload ftpDown = null;

    ArrayList<Order> list = new ArrayList<>();
    Order order = null;
    FtpUpload ftp = null;

    @FXML
    private TextField addDrink, addDrinkSize, addAppetizer, addMainCourse, addDessert, addExtra, addTableNumber, addPrice;

    @FXML
    private Button submitButton, returnButton;

    @FXML
    private Label orderAdded, mustHavePrice, mustHaveSize;

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
            if (addDrink.textProperty().get().isEmpty()) {
                order.setDrink("0");
                order.setDrinkSize("0");
            } else {
                order.setDrink(addDrink.getText());

                if (addDrinkSize.textProperty().get().isEmpty()) {
                orderAdded.setText("Drink size!");
                mustHaveSize.setVisible(true);
                return;
                } else {
                    order.setDrinkSize(addDrinkSize.getText());
                }
            }

            if (addAppetizer.textProperty().get().isEmpty()) {
                order.setAppetizer("0");
            } else {
                order.setAppetizer(addAppetizer.getText());
            }
            if (addMainCourse.textProperty().get().isEmpty()) {
                order.setMainCourse("0");
            } else {
                order.setMainCourse(addMainCourse.getText());
            }
            if (addDessert.textProperty().get().isEmpty()) {
                order.setDessert("0");
            } else {
                order.setDessert(addDessert.getText());
            }
            if (addExtra.textProperty().get().isEmpty()) {
                order.setExtra("0");
            } else {
                order.setExtra(addExtra.getText());
            }
            order.setTableNumber(tempNr);
            if (addPrice.textProperty().get().isEmpty()) {
                orderAdded.setText("Price!");
                mustHavePrice.setVisible(true);
                return;
            } else {
                order.setPrice(Double.parseDouble(addPrice.getText()));
            }
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

}
