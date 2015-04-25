package project2dana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class MainMenuController implements Initializable {

    @FXML
    private Button addOrdersButton, viewOrdersButton, searchSalesButton, addUserButton, exitButton;

    
    @FXML
    private void handleaddOrderButtonAction(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddOrders.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
        } catch (Exception ex) {
            
        }
        
    }
    
    @FXML
    private void handleviewOrderButtonAction(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewOrders.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
        } catch (Exception ex) {
            
        }
        
    }
    
    @FXML
    private void handleaSearchSalesButtonAction(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewSales.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
        } catch (Exception ex) {
            
        }
        
    }
    
    
    @FXML
    private void handleaddUserButtonAction(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
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
        // TODO
    }
    
    public void exitButton() {
        System.exit(0);
    }

}
