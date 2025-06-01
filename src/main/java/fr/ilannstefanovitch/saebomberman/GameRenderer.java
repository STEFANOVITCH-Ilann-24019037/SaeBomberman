
package fr.ilannstefanovitch.saebomberman;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameRenderer {
    private static final int CELL_SIZE = 40;
    private final GridPane gameGrid;

    public GameRenderer(GridPane gameGrid) {
        this.gameGrid = gameGrid;
    }

    public void render(GameLogic gameLogic) {
        gameGrid.getChildren().clear();

        renderTerrain(gameLogic.getTerrain());
        renderExplosions(gameLogic);
        renderBombs(gameLogic);
        renderEnemies(gameLogic);
        renderPlayer(gameLogic.getPlayer());
    }

    private void renderTerrain(TerrainMap terrain) {
        for (int y = 0; y < TerrainMap.GRID_HEIGHT; y++) {
            for (int x = 0; x < TerrainMap.GRID_WIDTH; x++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);

                switch (terrain.getCellType(x, y)) {
                    case TerrainMap.EMPTY -> cell.setFill(Color.LIGHTGREEN);
                    case TerrainMap.INDESTRUCTIBLE_WALL -> cell.setFill(Color.DARKGRAY);
                    case TerrainMap.DESTRUCTIBLE_WALL -> cell.setFill(Color.BROWN);
                }

                cell.setStroke(Color.BLACK);
                cell.setStrokeWidth(0.5);
                gameGrid.add(cell, x, y);
            }
        }
    }

    private void renderExplosions(GameLogic gameLogic) {
        for (ExplosionCell explosion : gameLogic.getExplosions()) {
            Rectangle explosionRect = new Rectangle(CELL_SIZE, CELL_SIZE, Color.YELLOW);
            explosionRect.setStroke(Color.ORANGE);
            explosionRect.setStrokeWidth(2);
            gameGrid.add(explosionRect, explosion.getX(), explosion.getY());
        }
    }

    private void renderBombs(GameLogic gameLogic) {
        for (Bomb bomb : gameLogic.getBombs()) {
            Circle bombCircle = new Circle(CELL_SIZE / 3, Color.BLACK);
            gameGrid.add(bombCircle, bomb.getX(), bomb.getY());
        }
    }

    private void renderEnemies(GameLogic gameLogic) {
        for (Enemy enemy : gameLogic.getEnemies()) {
            Circle enemyCircle = new Circle(CELL_SIZE / 3, Color.RED);
            gameGrid.add(enemyCircle, enemy.getX(), enemy.getY());
        }
    }

    private void renderPlayer(Player player) {
        Circle playerCircle = new Circle(CELL_SIZE / 3, Color.BLUE);
        gameGrid.add(playerCircle, player.getX(), player.getY());
    }
}