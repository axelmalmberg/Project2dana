package project2dana;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    ArrayList<Order> list = new ArrayList<>();
    StringProperty drink, drinksize, appetizer, maincourse, dessert;

    @FXML
    private Button refreshButton, returnButton, finishedOrderButton, deleteOrderButton;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, String> drinkColumn, drinkSizeColumn, appetizerColumn, mainCourseColumn, dessertColumn, extraColumn, dateColumn;

    @FXML
    private TableColumn<Order, Integer> tableColumn, idColumn;
    
    @FXML
    private void handleaddReturnButtonAction(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {

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
                    
                  drink = new SimpleStringProperty(order.getDrink());
                
                  
                
//                drinkColumn.setCellValueFactory(cellData -> cellData.getValue().getDrink().asObject());
//                drinkSizeColumn.setCellValueFactory(cellData -> cellData.getValue().order.getDrinkSize());
//                appetizerColumn.setCellValueFactory(cellData -> order.getAppetizer());
//                mainCourseColumn.setCellValueFactory(cellData -> order.getMainCourse());
//                dessertColumn.setCellValueFactory(cellData -> order.getDessert());
//                extraColumn.setCellValueFactory(cellData -> order.getExtra());
//                tableColumn.setCellValueFactory(cellData -> order.getTableNumber());
//                idColumn.setCellValueFactory(cellData -> order.getId());
//                dateColumn.setCellValueFactory(celldData -> order.getDate());

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
