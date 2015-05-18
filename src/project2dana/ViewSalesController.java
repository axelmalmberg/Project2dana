package project2dana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ViewSalesController implements Initializable {

    FinishedOrderProperty op = null;
    ObservableList<FinishedOrderProperty> ObList = FXCollections.observableArrayList();
    
    @FXML
    private Button returnButton, searchButton;

    @FXML
    private TableView<FinishedOrderProperty> orderTable;

    @FXML
    private TableColumn<FinishedOrderProperty, String> drinkColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, String> drinkSizeColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, String> appetizerColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, String> mainCourseColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, String> dessertColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, Integer> tableColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, Integer> idColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, String> dateColumn;
    
    @FXML
    private TableColumn<FinishedOrderProperty, Double> priceColumn;
    
    
    @FXML
    private TableColumn<FinishedOrderProperty, String> extraColumn;
    @FXML
    private void handleaddReturnButtonAction(ActionEvent event) {
        try {
            SceneSwitcher ss = new SceneSwitcher();
            ss.switchScene(event, "MainMenu.fxml");
        } catch (Exception ex) {

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    public void showTable() {
        try {
            
            drinkColumn.setCellValueFactory(cellData -> cellData.getValue().getDrink());
            drinkSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getDrinkSize());
            appetizerColumn.setCellValueFactory(cellData -> cellData.getValue().getAppetizer());
            mainCourseColumn.setCellValueFactory(cellData -> cellData.getValue().getMainCourse());
            dessertColumn.setCellValueFactory(cellData -> cellData.getValue().getDessert());
            extraColumn.setCellValueFactory(cellData -> cellData.getValue().getExtra());
            tableColumn.setCellValueFactory(cellData -> cellData.getValue().getTableNumber().asObject());
            idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
            dateColumn.setCellValueFactory(celldData -> celldData.getValue().getDate());
            priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
            orderTable.setItems(ObList);
            
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
