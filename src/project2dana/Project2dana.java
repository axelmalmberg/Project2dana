package project2dana;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Dardan Berisha, Anesa Kusmic, Nemanja Lekanovic, Axel Malmberg
 *
 */
public class Project2dana extends Application {
    

    @Override
    public void start(Stage stage) throws Exception {

        System.out.println(System.getProperty("user.dir"));

        stage.initStyle(StageStyle.UNDECORATED);


        Parent root = FXMLLoader.load(getClass().getResource("LogInScene.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        System.out.println("DANA ISG OVER!");
    }

}
