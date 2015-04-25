package project2dana;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * /**
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class AddOrdersController implements Initializable {

    @FXML
    private TextField addDrink, addDrinkSize, addAppetizer, addMainCourse, addDessert, addExtra, addTableNumber;

    @FXML
    private Button submitButton, returnButton;
    
//    ArrayList<> orders = new ArrayList<>();
    

    
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
    
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
