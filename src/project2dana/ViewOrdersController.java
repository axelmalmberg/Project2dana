package project2dana;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class ViewOrdersController implements Initializable {

    DbConnector db = new DbConnector();
    PendingOrderProperty op = null;
    ObservableList<PendingOrderProperty> ObList = FXCollections.observableArrayList();
    ArrayList<Order> list = new ArrayList<>();
    StringProperty drink, drinksize, appetizer, maincourse, dessert;

    FtpDownload ftp = null;
    FtpUpload ftpUp = null;

    @FXML
    private TableView<PendingOrderProperty> orderTable;

    @FXML
    private TableColumn<PendingOrderProperty, String> drinkColumn;
    @FXML
    private TableColumn<PendingOrderProperty, String> drinkSizeColumn;
    @FXML
    private TableColumn<PendingOrderProperty, String> appetizerColumn;
    @FXML
    private TableColumn<PendingOrderProperty, String> mainCourseColumn;
    @FXML
    private TableColumn<PendingOrderProperty, String> dessertColumn;
    @FXML
    private TableColumn<PendingOrderProperty, String> extraColumn;
    @FXML
    private TableColumn<PendingOrderProperty, String> dateColumn;

    @FXML
    private TableColumn<PendingOrderProperty, Integer> tableColumn;

    @FXML
    private TableColumn<PendingOrderProperty, Integer> idColumn;
    
    @FXML
    private Button finishedOrderButton;
   

    @FXML
    private void handleRefreshButtonAction(ActionEvent event){
        ObList.clear();
        list.clear();
        downloadFtp();
        showTable();
    }
    
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
        Button bt = (Button)event.getSource();
        
        try {
            ObservableList<PendingOrderProperty> orderSelected, allOrders;
            allOrders = orderTable.getItems();
            orderSelected = orderTable.getSelectionModel().getSelectedItems();
        if(bt == finishedOrderButton){    
            Order order = list.get(orderTable.getSelectionModel().getSelectedIndex());
            db.addSale(order.getDate(), order.getTableNumber(), order.getPrice(), order.getId(), order.getAppetizer(), order.getDessert(), order.getDrink(), order.getExtra(), order.getMainCourse(), order.getDrinkSize());
        } 
            list.remove(orderTable.getSelectionModel().getSelectedIndex());
            orderSelected.forEach(allOrders::remove);

            FileOutputStream fileOut = new FileOutputStream("OrderList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            addToFTP();
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
        System.out.println("Downloading file from server");
      
        File f = new File("OrderList.ser");

        if (!f.exists()) {
            return;
        }
        try {
            System.out.println("Reading file from server");
            FileInputStream fileIn = new FileInputStream("OrderList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Order>) in.readObject();
            System.out.println("Success!");
            in.close();
            fileIn.close();

            for (int i = 0; i < list.size(); i++) {
                Order order = list.get(i);
                op = new PendingOrderProperty(order.getTimeStamp(), order.getDrink(), order.getDrinkSize(), order.getAppetizer(), order.getMainCourse(),
                        order.getDessert(), order.getExtra(), order.getTableNumber(), order.getId());
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
    
    public void addToFTP() {
        ftpUp = new FtpUpload();
        ftpUp.startFTP("OrderList.ser");
    }

}
