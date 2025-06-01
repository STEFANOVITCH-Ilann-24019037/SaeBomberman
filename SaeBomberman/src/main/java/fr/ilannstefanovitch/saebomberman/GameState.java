package fr.ilannstefanovitch.saebomberman;

public class GameState {
    private int score = 0;
    private int lives = 3;
    private boolean gameOver = false;
    private boolean victory = false;

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public void addScore(int points) {
        score += points;
    }

    public void loseLife() {
        lives--;
        if (lives <= 0) {
            gameOver = true;
        }
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public void reset() {
        score = 0;
        lives = 3;
        gameOver = false;
        victory = false;
    }
}