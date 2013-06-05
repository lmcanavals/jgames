package org.aoeu.jgames.snake;

import javax.swing.*;
import java.awt.*;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class SnakePanel extends JPanel {

    int scale = 10;
    Snake snake;

    public SnakePanel(Snake snake, int scale) {
        this.snake = snake;
        this.scale = scale;
        setPreferredSize(new Dimension(
                snake.getWorldWidth() * scale + 1,
                snake.getWorldHeight() * scale + 1));
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        g.setColor(Color.gray);
        for (int i = 0; i < panelWidth; i += scale) {
            g.drawLine(i, 0, i, panelHeight);
        }
        for (int j = 0; j < panelHeight; j += scale) {
            g.drawLine(0, j, panelWidth, j);
        }
        if (snake.isDead()) {
            g.setColor(Color.red);
            g.drawString("Game Over :(", scale, scale * 2);
        } else {
            g.setColor(Color.orange);
            int bodyLength = snake.getBodyLength();
            int side = scale - 1;
            for (int i = 0; i < bodyLength; i++) {
                g.fillRect(snake.getX(i) * scale + 1, snake.getY(i) * scale + 1,
                        side, side);
            }
        }
    }
}
