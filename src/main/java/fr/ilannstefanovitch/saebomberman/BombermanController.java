package fr.ilannstefanovitch.saebomberman;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class BombermanController implements Initializable {
    @FXML private GridPane gameGrid;
    @FXML private Label scoreLabel;

    private GameLogic gameLogic;
    private GameRenderer gameRenderer;
    private Timeline gameLoop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeGame();
        setupGameLoop();
        render();
    }

    private void initializeGame() {
        gameLogic = new GameLogic();
        gameRenderer = new GameRenderer(gameGrid);
    }

    private void setupGameLoop() {
        gameLoop = new Timeline(new KeyFrame(Duration.millis(100), e -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    private void updateGame() {
        gameLogic.update();
        render();
        updateUI();
        checkGameEnd();
    }

    private void render() {
        gameRenderer.render(gameLogic);
    }

    private void updateUI() {
        GameState gameState = gameLogic.getGameState();
        scoreLabel.setText("Score: " + gameState.getScore() + " | Vies: " + gameState.getLives());
    }

    private void checkGameEnd() {
        GameState gameState = gameLogic.getGameState();

        if (gameState.isVictory()) {
            gameLoop.stop();
            scoreLabel.setText("VICTOIRE ! Score final: " + gameState.getScore() + " - Appuyez sur ECHAP pour le menu");
        } else if (gameState.isGameOver()) {
            gameLoop.stop();
            scoreLabel.setText("GAME OVER - Score: " + gameState.getScore() + " - Appuyez sur ECHAP pour le menu");
        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (gameLogic.getGameState().isGameOver() && !gameLogic.getGameState().isVictory()) {
            if (event.getCode().toString().equals("ESCAPE")) {
                returnToMenu();
            }
            return;
        }

        switch (event.getCode()) {
            case UP, Z -> gameLogic.movePlayer(0, -1);
            case DOWN, S -> gameLogic.movePlayer(0, 1);
            case LEFT, Q -> gameLogic.movePlayer(-1, 0);
            case RIGHT, D -> gameLogic.movePlayer(1, 0);
            case SPACE -> gameLogic.placeBomb();
            case ESCAPE -> returnToMenu();
        }
    }

    private void returnToMenu() {
        try {
            if (gameLoop != null) {
                gameLoop.stop();
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Scene menuScene = new Scene(fxmlLoader.load(), 800, 600);

            Stage stage = (Stage) gameGrid.getScene().getWindow();
            stage.setScene(menuScene);
            stage.setTitle("Bomberman - Menu Principal");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}