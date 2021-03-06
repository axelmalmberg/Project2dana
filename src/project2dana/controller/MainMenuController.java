package project2dana.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import project2dana.model.SceneSwitcher;

public class MainMenuController implements Initializable {

    @FXML
    private Button addOrdersButton, viewOrdersButton, searchSalesButton, addUserButton, exitButton, addFoodButton;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button scr = (Button) event.getSource();
        String tmpFxmlStr = null;
        if (scr == addOrdersButton || scr == viewOrdersButton || scr == searchSalesButton || scr == addUserButton || scr == addFoodButton) {
            if (scr == addOrdersButton) {
                tmpFxmlStr = "AddOrders.fxml";
            } else if (scr == viewOrdersButton) {
                tmpFxmlStr = "ViewOrders.fxml";
            } else if (scr == searchSalesButton) {
                tmpFxmlStr = "ViewSales.fxml";
            } else if (scr == addUserButton) {
                tmpFxmlStr = "AddUser.fxml";
            }
            else if (scr == addFoodButton) {
                tmpFxmlStr = "AddFood.fxml";
            }
            try {
                SceneSwitcher ss = new SceneSwitcher();
                ss.switchScene(event, tmpFxmlStr);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } else {
            File f2 = new File("saveId.txt");
            File f = new File("SaveUserInfo.txt");
            File f3 = new File("saveStatus.txt");
            File f4 = new File("OrderList.ser");
            f3.delete();
            f.delete();
            f2.delete();
            f3.delete();
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BufferedReader read = null;
        try {
            read = new BufferedReader(new FileReader("saveStatus.txt"));
            String tmpStr = null;
            tmpStr = read.readLine();
            int adminStatus = Integer.parseInt(tmpStr);
            if (adminStatus == 2) {
                addUserButton.setVisible(false);
                searchSalesButton.setVisible(false);
            }
            read.close();

        } catch (IOException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
