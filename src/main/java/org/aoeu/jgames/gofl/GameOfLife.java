package org.aoeu.jgames.gofl;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class GameOfLife {

    static final byte ONE = 1;
    static final byte ZERO = 0;

    byte[][] cells;
    byte[][] prevGen;

    int width;
    int height;
    int upperW;
    int upperH;

    GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;
        upperW = width - 1;
        upperH = height - 1;
        cells = new byte[width][height];
        prevGen = new byte[width][height];
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    void toggleCell(int i, int j) {
        cells[i][j] = cells[i][j] == ONE ? ZERO : ONE;
    }

    byte[][] getCells() {
        return cells;
    }

    void nextGeneration() {
        byte[][] temp = cells;
        cells = prevGen;
        prevGen = temp;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                byte n = neighbors(i, j);
                byte prev = prevGen[i][j];
                byte val = ZERO;
                if (n == 3 || (prev == 1 && n == 2)) {
                    val = ONE;
                }
                cells[i][j] = val;
            }
        }
    }

    private byte neighbors(int i, int j) {
        byte count = 0;
        int nji = (j == 0 ? height : j) - 1;
        int njf = j != upperH ? j + 1 : 0;
        for (int p = i - 1; p <= i + 1; p++) {
            int ni = p < 0 ? upperW : p == width ? 0 : p;
            count += prevGen[ni][nji] + prevGen[ni][njf];
        }
        count += prevGen[(i == 0 ? width : i) - 1][j];
        count += prevGen[i != upperW ? i + 1 : 0][j];
        return count;
    }
}
