package fr.ilannstefanovitch.saebomberman;

public class TerrainMap {
    public static final int EMPTY = 0;
    public static final int INDESTRUCTIBLE_WALL = 1;
    public static final int DESTRUCTIBLE_WALL = 2;

    public static final int GRID_WIDTH = 15;
    public static final int GRID_HEIGHT = 13;

    private int[][] terrain = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,2,0,2,0,2,0,2,0,2,0,0,1},
            {1,0,1,2,1,2,1,2,1,2,1,2,1,0,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,0,1,2,1,2,1,2,1,2,1,2,1,0,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,0,1,2,1,2,1,2,1,2,1,2,1,0,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,0,1,2,1,2,1,2,1,2,1,2,1,0,1},
            {1,2,2,2,2,2,2,2,2,2,2,2,2,2,1},
            {1,0,1,2,1,2,1,2,1,2,1,2,1,0,1},
            {1,0,0,2,0,2,0,2,0,2,0,2,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public int getCellType(int x, int y) {
        if (!isValidPosition(x, y)) {
            return INDESTRUCTIBLE_WALL; // Considérer les positions invalides comme des murs
        }
        return terrain[y][x];
    }

    public void setCellType(int x, int y, int type) {
        if (isValidPosition(x, y)) {
            terrain[y][x] = type;
        }
    }

    public boolean isEmpty(int x, int y) {
        return getCellType(x, y) == EMPTY;
    }

    public boolean isDestructibleWall(int x, int y) {
        return getCellType(x, y) == DESTRUCTIBLE_WALL;
    }

    public boolean isIndestructibleWall(int x, int y) {
        return getCellType(x, y) == INDESTRUCTIBLE_WALL;
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < GRID_WIDTH && y >= 0 && y < GRID_HEIGHT;
    }

    public void destroyWall(int x, int y) {
        if (isDestructibleWall(x, y)) {
            setCellType(x, y, EMPTY);
        }
    }

    public void initializePlayerStartArea() {
        // Libérer la zone de départ du joueur
        setCellType(1, 1, EMPTY);
        setCellType(2, 1, EMPTY);
        setCellType(1, 2, EMPTY);
    }

    public int[][] getTerrainCopy() {
        int[][] copy = new int[GRID_HEIGHT][GRID_WIDTH];
        for (int y = 0; y < GRID_HEIGHT; y++) {
            System.arraycopy(terrain[y], 0, copy[y], 0, GRID_WIDTH);
        }
        return copy;
    }
}