package org.aoeu.jgames.gofl;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class GameOfLifePanel extends JPanel {

    GameOfLife gol;
    int scale;

    GameOfLifePanel(GameOfLife gol, int scale) {
        this.gol = gol;
        this.scale = scale;
        this.setPreferredSize(new Dimension(
                gol.getWidth() * scale + 1,
                gol.getHeight() * scale + 1));
        this.setOpaque(true);
        this.setBackground(Color.darkGray);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        byte[][] cells = gol.getCells();
        int worldWidth = gol.getWidth();
        int worldHeight = gol.getHeight();
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        g.setColor(Color.gray);
        for (int i = 0; i < panelWidth; i += scale) {
            g.drawLine(i, 0, i, panelHeight);
        }
        for (int j = 0; j < panelHeight; j += scale) {
            g.drawLine(0, j, panelWidth, j);
        }
        g.setColor(Color.green);
        final int side = scale - 1;
        for (int i = 0; i < worldWidth; i++) {
            for (int j = 0; j < worldHeight; j++) {
                if (cells[i][j] == 1) {
                    g.fillRect(i * scale + 1, j * scale + 1, side, side);
                }
            }
        }
    }
}
