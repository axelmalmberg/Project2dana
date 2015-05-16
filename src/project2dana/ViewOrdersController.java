package project2dana;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class ViewOrdersController implements Initializable {

    DbConnector db = new DbConnector();
    OrderProperty op = null;
    ObservableList<OrderProperty> ObList = FXCollections.observableArrayList();
    ArrayList<Order> list = new ArrayList<>();
    StringProperty drink, drinksize, appetizer, maincourse, dessert;

    FtpDownload ftp = null;

    @FXML
    private TableView<OrderProperty> orderTable;

    @FXML
    private TableColumn<OrderProperty, String> drinkColumn;
    @FXML
    private TableColumn<OrderProperty, String> drinkSizeColumn;
    @FXML
    private TableColumn<OrderProperty, String> appetizerColumn;
    @FXML
    private TableColumn<OrderProperty, String> mainCourseColumn;
    @FXML
    private TableColumn<OrderProperty, String> dessertColumn;
    @FXML
    private TableColumn<OrderProperty, String> extraColumn;
    @FXML
    private TableColumn<OrderProperty, String> dateColumn;

    @FXML
    private TableColumn<OrderProperty, Integer> tableColumn;

    @FXML
    private TableColumn<OrderProperty, Integer> idColumn;

    @FXML
    private void handleaddReturnButtonAction(ActionEvent event) {
        try {

            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene(event, "MainMenu.fxml");
        } catch (Exception ex) {

        }

    }

    @FXML
    private void handleFinishedButtonAction(ActionEvent event) {
        try {
            ObservableList<OrderProperty> orderSelected, allOrders;
            allOrders = orderTable.getItems();
            orderSelected = orderTable.getSelectionModel().getSelectedItems();
            Order order = list.get(orderTable.getSelectionModel().getSelectedIndex());
            db.addSale(order.getDate(), order.getTableNumber(), order.getPrice(), order.getId(), order.getAppetizer(), order.getDessert(), order.getDrink(), order.getExtra(), order.getMainCourse(), order.getDrinkSize());
            list.remove(orderTable.getSelectionModel().getSelectedIndex());
            orderSelected.forEach(allOrders::remove);

            FileOutputStream fileOut = new FileOutputStream("OrderList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void handleDeleteButtonAction(ActionEvent event) {
        ObservableList<OrderProperty> orderSelected, allOrders;
        allOrders = orderTable.getItems();
        orderSelected = orderTable.getSelectionModel().getSelectedItems();
        list.remove(orderTable.getSelectionModel().getSelectedIndex());
        orderSelected.forEach(allOrders::remove);

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("hej");
        showTable();
    }

    public void showTable() {

        try {
            convert();

            drinkColumn.setCellValueFactory(cellData -> cellData.getValue().getDrink());
            drinkSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getDrinkSize());
            appetizerColumn.setCellValueFactory(cellData -> cellData.getValue().getAppetizer());
            mainCourseColumn.setCellValueFactory(cellData -> cellData.getValue().getMainCourse());
            dessertColumn.setCellValueFactory(cellData -> cellData.getValue().getDessert());
            extraColumn.setCellValueFactory(cellData -> cellData.getValue().getExtra());
            tableColumn.setCellValueFactory(cellData -> cellData.getValue().getTableNumber().asObject());
            idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
            dateColumn.setCellValueFactory(celldData -> celldData.getValue().getDate());
            orderTable.setItems(ObList);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void convert() {

        downloadFtp();
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        File f = new File("OrderList.ser");

        if (!f.exists()) {
            return;
        }
        try {
            System.out.println("1");
            FileInputStream fileIn = new FileInputStream("OrderList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Order>) in.readObject();
            System.out.println("2");
            in.close();
            fileIn.close();

            for (int i = 0; i < list.size(); i++) {
                Order order = list.get(i);
                op = new OrderProperty(order.getTimeStamp(), order.getDrink(), order.getDrinkSize(), order.getAppetizer(), order.getMainCourse(),
                        order.getDessert(), order.getExtra(), order.getTableNumber(), order.getId(), order.getPrice());
                ObList.add(op);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void downloadFtp() {

        File f = new File("OrderList.ser");
        if (f.exists()) {
            f.delete();
        }
        ftp = new FtpDownload();
        ftp.startFTP();

    }

}
