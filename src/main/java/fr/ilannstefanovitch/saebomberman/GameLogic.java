package fr.ilannstefanovitch.saebomberman;

import java.util.*;

public class GameLogic {
    private GameState gameState;
    private Player player;
    private List<Enemy> enemies;
    private List<Bomb> bombs;
    private Set<ExplosionCell> explosions;
    private TerrainMap terrain;

    public GameLogic() {
        this.gameState = new GameState();
        this.terrain = new TerrainMap();
        this.player = new Player(1, 1);
        this.enemies = new ArrayList<>();
        this.bombs = new ArrayList<>();
        this.explosions = new HashSet<>();

        initializeGame();
    }

    private void initializeGame() {
        // Initialiser les ennemis
        enemies.add(new Enemy(13, 1));
        enemies.add(new Enemy(13, 11));
        enemies.add(new Enemy(1, 11));

        // Préparer la zone de départ du joueur
        terrain.initializePlayerStartArea();
    }

    public void update() {
        if (gameState.isGameOver() || gameState.isVictory()) {
            return;
        }

        updateEnemies();
        updateBombs();
        updateExplosions();
        checkCollisions();
        checkVictoryCondition();
    }

    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update(terrain);
        }
    }

    private void updateBombs() {
        Iterator<Bomb> bombIter = bombs.iterator();
        while (bombIter.hasNext()) {
            Bomb bomb = bombIter.next();
            bomb.update();
            if (bomb.hasExploded()) {
                explodeBomb(bomb);
                bombIter.remove();
            }
        }
    }

    private void updateExplosions() {
        Iterator<ExplosionCell> explosionIter = explosions.iterator();
        while (explosionIter.hasNext()) {
            ExplosionCell explosion = explosionIter.next();
            explosion.update();
            if (explosion.isFinished()) {
                explosionIter.remove();
            }
        }
    }

    private void explodeBomb(Bomb bomb) {
        int x = bomb.getX();
        int y = bomb.getY();
        int range = bomb.getRange();

        // Explosion au centre
        explosions.add(new ExplosionCell(x, y));
        destroyAt(x, y);

        // Explosion dans les 4 directions
        int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        for (int[] dir : directions) {
            for (int i = 1; i <= range; i++) {
                int nx = x + dir[0] * i;
                int ny = y + dir[1] * i;

                if (!terrain.isValidPosition(nx, ny)) break;
                if (terrain.isIndestructibleWall(nx, ny)) break;

                explosions.add(new ExplosionCell(nx, ny));
                destroyAt(nx, ny);

                if (terrain.isDestructibleWall(nx, ny)) {
                    terrain.destroyWall(nx, ny);
                    gameState.addScore(10);
                    break;
                }
            }
        }
    }

    private void destroyAt(int x, int y) {
        // Vérifier si le joueur est touché
        if (player.isAt(x, y)) {
            gameState.loseLife();
            if (gameState.isGameOver()) {
                return;
            }
        }

        // Vérifier si des ennemis sont touchés
        enemies.removeIf(enemy -> {
            if (enemy.isAt(x, y)) {
                gameState.addScore(100);
                return true;
            }
            return false;
        });
    }

    private void checkCollisions() {
        // Collision joueur-ennemi
        for (Enemy enemy : enemies) {
            if (player.isAt(enemy.getX(), enemy.getY())) {
                gameState.loseLife();
                if (gameState.isGameOver()) {
                    return;
                }
                // Repositionner le joueur
                player.resetPosition();
                break;
            }
        }
    }

    private void checkVictoryCondition() {
        if (enemies.isEmpty()) {
            gameState.setVictory(true);
        }
    }

    public boolean movePlayer(int deltaX, int deltaY) {
        if (gameState.isGameOver()) return false;

        int newX = player.getX() + deltaX;
        int newY = player.getY() + deltaY;

        if (isValidPlayerMove(newX, newY)) {
            player.setPosition(newX, newY);
            return true;
        }
        return false;
    }

    private boolean isValidPlayerMove(int x, int y) {
        if (!terrain.isValidPosition(x, y)) return false;
        if (!terrain.isEmpty(x, y)) return false;

        // Vérifier s'il y a une bombe à cette position
        for (Bomb bomb : bombs) {
            if (bomb.isAt(x, y)) return false;
        }

        return true;
    }

    public boolean placeBomb() {
        if (gameState.isGameOver()) return false;

        int x = player.getX();
        int y = player.getY();

        // Vérifier s'il y a déjà une bombe à cette position
        for (Bomb bomb : bombs) {
            if (bomb.isAt(x, y)) return false;
        }

        bombs.add(new Bomb(x, y));
        return true;
    }

    // Getters
    public GameState getGameState() { return gameState; }
    public Player getPlayer() { return player; }
    public List<Enemy> getEnemies() { return enemies; }
    public List<Bomb> getBombs() { return bombs; }
    public Set<ExplosionCell> getExplosions() { return explosions; }
    public TerrainMap getTerrain() { return terrain; }
}