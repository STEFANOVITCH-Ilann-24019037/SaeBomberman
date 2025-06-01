package fr.ilannstefanovitch.saebomberman;

public class Bomb {
    private int x, y;
    private int timer;
    private int range;
    private static final int DEFAULT_TIMER = 30; // 3 secondes à 10 FPS
    private static final int DEFAULT_RANGE = 2;

    public Bomb(int x, int y) {
        this(x, y, DEFAULT_RANGE, DEFAULT_TIMER);
    }

    public Bomb(int x, int y, int range, int timer) {
        this.x = x;
        this.y = y;
        this.range = range;
        this.timer = timer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRange() {
        return range;
    }

    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }

    public void update() {
        timer--;
    }

    public boolean hasExploded() {
        return timer <= 0;
    }

    public int getTimeRemaining() {
        return timer;
    }
}