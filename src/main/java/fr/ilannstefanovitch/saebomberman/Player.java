package fr.ilannstefanovitch.saebomberman;

public class Player {
    private int x, y;
    private final int startX, startY;

    public Player(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.x = startX;
        this.y = startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void resetPosition() {
        this.x = startX;
        this.y = startY;
    }

    public boolean isAt(int x, int y) {
        return this.x == x && this.y == y;
    }
}