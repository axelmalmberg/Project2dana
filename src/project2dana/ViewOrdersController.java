/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project2dana;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author optimus prime
 */
public class ViewOrdersController implements Initializable {
    
    @FXML
    private Button refreshButton, returnButton, finishedOrderButton, deleteOrderButton;
    
    @FXML
    private TableView orderTable;
    
    @FXML
    private TableColumn drinkColumn, drinkSizeColumn, appetizerColumn, mainCourseColumn, dessertColumn, extraColumn, tableColumn, idColumn, dateColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
