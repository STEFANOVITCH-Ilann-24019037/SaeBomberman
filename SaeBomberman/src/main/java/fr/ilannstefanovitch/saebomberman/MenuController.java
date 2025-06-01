package fr.ilannstefanovitch.saebomberman;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Platform;

public class MenuController {
    @FXML private Button playButton;
    @FXML private Button rulesButton;
    @FXML private Button quitButton;

    @FXML
    private void startGame() {
        try {
            // Charger la scène de jeu
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bomberman.fxml"));
            Scene gameScene = new Scene(fxmlLoader.load(), 800, 600);

            // Récupérer la fenêtre actuelle
            Stage stage = (Stage) playButton.getScene().getWindow();
            stage.setScene(gameScene);
            stage.setTitle("Bomberman - En jeu");

            // Focus pour les contrôles clavier
            gameScene.getRoot().requestFocus();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de charger le jeu : " + e.getMessage());
        }
    }

    @FXML
    private void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Règles du jeu");
        alert.setHeaderText("Comment jouer à Bomberman");
        alert.setContentText(
                "DÉPLACEMENT :\n" +
                        "• Utilisez ZQSD ou les flèches directionnelles pour bouger\n\n" +
                        "BOMBES :\n" +
                        "• Appuyez sur ESPACE pour poser une bombe\n" +
                        "• Les bombes explosent après 3 secondes\n" +
                        "• L'explosion détruit les murs marrons et élimine les ennemis\n\n" +
                        "OBJECTIF :\n" +
                        "• Détruisez tous les ennemis rouges\n" +
                        "• Évitez les explosions et les ennemis\n" +
                        "• Gagnez des points en détruisant des murs et des ennemis\n\n" +
                        "ATTENTION :\n" +
                        "• Vous avez 3 vies\n" +
                        "• Ne restez pas près de vos bombes !"
        );
        alert.showAndWait();
    }

    @FXML
    private void quitGame() {
        Platform.exit();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}