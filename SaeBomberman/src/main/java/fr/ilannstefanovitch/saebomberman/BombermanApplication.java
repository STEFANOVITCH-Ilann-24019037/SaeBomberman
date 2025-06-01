package fr.ilannstefanovitch.saebomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BombermanApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(BombermanApplication.class.getResource("/fr/ilannstefanovitch/saebomberman/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Bomberman Game");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // Focus sur la scène pour les événements clavier
        scene.getRoot().requestFocus();
    }


    public static void main(String[] args) {
        launch();
    }
}