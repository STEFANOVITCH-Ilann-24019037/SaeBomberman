package fr.ilannstefanovitch.saebomberman;

import java.util.*;

public class Enemy {
    private int x, y;
    private Random random = new Random();
    private int moveCounter = 0;
    private static final int MOVE_DELAY = 5;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void update(TerrainMap terrain) {
        moveCounter++;
        if (moveCounter % MOVE_DELAY != 0) return;

        Integer[] directions = {0, 1, 2, 3}; // haut, bas, gauche, droite
        List<Integer> directionsList = Arrays.asList(directions);
        Collections.shuffle(directionsList);

        for (int dir : directionsList) {
            int newX = x, newY = y;

            switch (dir) {
                case 0 -> newY--; // haut
                case 1 -> newY++; // bas
                case 2 -> newX--; // gauche
                case 3 -> newX++; // droite
            }

            if (terrain.isValidPosition(newX, newY) && terrain.isEmpty(newX, newY)) {
                x = newX;
                y = newY;
                break;
            }
        }
    }
}