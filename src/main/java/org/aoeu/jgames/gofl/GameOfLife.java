/*
 * 01010011 01100001 01101101 01110000 01101100 01100101 
 * 01000011 01101111 01100100 01100101 00100001
 */
package org.aoeu.jgames.gofl;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class GameOfLife {

    boolean[][] cells;
    boolean[][] prevGen;

    int width;
    int height;

    GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new boolean[width][height];
        this.prevGen = new boolean[width][height];
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    void toggleCell(int i, int j) {
        cells[i][j] = !cells[i][j];
    }

    boolean[][] getCells() {
        return cells;
    }

    boolean[][] nextGeneration() {
        boolean[][] temp = cells;
        cells = prevGen;
        prevGen = temp;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                byte n = neighbors(i, j);
                boolean prev = prevGen[i][j];
                boolean val = false;
                if (n == 3 || (prev && n == 2)) {
                    val = true;
                }
                cells[i][j] = val;
            }
        }
        return cells;
    }

    private byte neighbors(int i, int j) {
        byte count = 0;
        int nji = j == 0 ? height - 1 : j - 1;
        int njf = j == height - 1 ? 0 : j + 1;
        for (int p = i - 1; p <= i + 1; p++) {
            int ni = p < 0 ? width - 1 : p == width ? 0 : p;
            count += (prevGen[ni][nji] ? 1 : 0) + (prevGen[ni][njf] ? 1 : 0);
        }
        count += prevGen[i == 0 ? width - 1 : i - 1][j] ? 1 : 0;
        count += prevGen[i == width - 1 ? 0 : i + 1][j] ? 1 : 0;
        return count;
    }
}
