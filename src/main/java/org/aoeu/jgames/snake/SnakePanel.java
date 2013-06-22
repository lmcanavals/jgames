package org.aoeu.jgames.snake;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class SnakePanel extends JPanel {

    private static Color BG_COLOR = new Color(0x19231A);
    private static Color GRID_COLOR = new Color(0x111812);
    private static Color SNAKE_COLOR = new Color(0x5FAB5B);
    private static Color HEAD_COLOR = new Color(0x9DC33C);
    private static Color APPLE_COLOR = new Color(0xff6480);

    int scale = 10;
    Snake snake;

    public SnakePanel(Snake snake, int scale) {
        this.snake = snake;
        this.scale = scale;
        setPreferredSize(new Dimension(
                snake.getWorldWidth() * scale + 1,
                snake.getWorldHeight() * scale + 1));
        setOpaque(true);
        setBackground(BG_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        g.setColor(GRID_COLOR);
        for (int i = 0; i < panelWidth; i += scale) {
            g.drawLine(i, 0, i, panelHeight);
        }
        for (int j = 0; j < panelHeight; j += scale) {
            g.drawLine(0, j, panelWidth, j);
        }
        if (!snake.isDead() && snake.getBodyLength() > 0) {
            g.setColor(SNAKE_COLOR);
            int xi, yi;
            for (int i = 0; i < snake.getBodyLength() - 1; i++) {
                xi = snake.getX(i) * scale;
                yi = snake.getY(i) * scale;
                g.fillOval(xi, yi, scale, scale);
            }
            g.setColor(HEAD_COLOR);
            xi = snake.getX(snake.getBodyLength() - 1) * scale;
            yi = snake.getY(snake.getBodyLength() - 1) * scale;
            g.fillOval(xi, yi, scale, scale);
            g.setColor(APPLE_COLOR);
            xi = snake.getAppleX() * scale;
            yi = snake.getAppleY() * scale;
            g.fillOval(xi, yi, scale, scale);
        }
    }
}
