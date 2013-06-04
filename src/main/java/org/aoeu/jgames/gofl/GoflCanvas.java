/*
 * 01010011 01100001 01101101 01110000 01101100 01100101 
 * 01000011 01101111 01100100 01100101 00100001
 */
package org.aoeu.jgames.gofl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Luis Martín Canaval Sánchez
 */
public class GoflCanvas extends JPanel {

    private Gofl gofl;
    int scale = 10;

    GoflCanvas(Gofl gofl) {
        this.gofl = gofl;
        this.setPreferredSize(new Dimension(
                gofl.getWidth() * scale + 1,
                gofl.getHeight() * scale + 1));
        this.setOpaque(true);
        this.setBackground(Color.darkGray);
    }

    public int getScale() {
        return scale;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean[][] cells = gofl.getCells();
        int width = gofl.getWidth();
        int height = gofl.getHeight();
        g.setColor(Color.gray);
        for (int i = 0; i < getWidth(); i += scale) {
            g.drawLine(i, 0, i, this.getHeight());
        }
        for (int j =0; j < getHeight() + 1; j += scale) {
            g.drawLine(0, j, this.getWidth() + 1, j);
        }
        g.setColor(Color.green);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j]) {
                    g.fillRect(i * scale + 1, j * scale + 1,
                            scale - 1, scale - 1);
                }
            }
        }
    }
}
