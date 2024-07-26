package org.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static GestionPharmacie gestionPharmacie = new GestionPharmacie();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showMainPage(primaryStage);
    }

    public static void showMainPage(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/org/example/demo1/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setGestionPharmacie(gestionPharmacie);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


}
