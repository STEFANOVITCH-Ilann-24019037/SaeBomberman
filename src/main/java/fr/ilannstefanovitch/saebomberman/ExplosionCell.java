package fr.ilannstefanovitch.saebomberman;

public class ExplosionCell {
    private int x, y;
    private int duration;
    private static final int DEFAULT_DURATION = 10; // Durée de l'explosion en frames (1 seconde)

    public ExplosionCell(int x, int y) {
        this(x, y, DEFAULT_DURATION);
    }

    public ExplosionCell(int x, int y, int duration) {
        this.x = x;
        this.y = y;
        this.duration = duration;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

    public void update() {
        duration--;
    }

    public boolean isFinished() {
        return duration <= 0;
    }

    public int getDurationRemaining() {
        return duration;
    }
}