/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2dana.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bumblebee
 */
public class SceneSwitcher {

    public void switchScene(ActionEvent event, String sceneName) {
        try {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
