package project2dana;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
    
    ArrayList<Order> list = new ArrayList<>();
    Order order = null;
    FtpUpload ftp = null;
    
    @FXML
    private TextField addDrink, addDrinkSize, addAppetizer, addMainCourse, addDessert, addExtra, addTableNumber, addPrice;
    
    @FXML
    private Button submitButton, returnButton;
    
    @FXML
    private Label orderAdded;
    
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
            System.out.println(ex.getMessage());
        }
        
    }
    
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        BufferedReader read = null;
        int tempNr = 0;
        
        try {
            order = new Order();
            
            read = new BufferedReader(new FileReader("saveId.txt"));
            String tmpStr = null;
            tmpStr = read.readLine();
            int id = Integer.parseInt(tmpStr);
            
            read.close();
            
            tempNr = Integer.parseInt(addTableNumber.getText());
            order.setDrink(addDrink.getText());
            order.setDrinkSize(addDrinkSize.getText());
            order.setAppetizer(addAppetizer.getText());
            order.setMainCourse(addMainCourse.getText());
            order.setDessert(addDessert.getText());
            order.setExtra(addExtra.getText());
            order.setTableNumber(tempNr);
            order.setPrice(Double.parseDouble(addPrice.getText()));
            order.setId(id);
            list.add(order);
            saveList();
            addToFTP();
            
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
    
    public void addToFTP(){
        ftp = new FtpUpload();
        ftp.startFTP("OrderList.ser");
    }
    
}
