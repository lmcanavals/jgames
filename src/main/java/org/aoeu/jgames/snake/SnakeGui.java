package org.aoeu.jgames.snake;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class SnakeGui extends JFrame {

    static final int WORLD_WIDTH = 50;
    static final int WORLD_HEIGHT = 35;
    static final int WORLD_SCALE = 20;
    static final String TITLE = "Un Snake Super Simple";
    int delay = 150;
    Snake snake;
    SnakePanel snakePanel;
    boolean gameOver;
    boolean running;

    void start() {
        setTitle(TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        snake = new Snake(WORLD_WIDTH, WORLD_HEIGHT);
        snakePanel = new SnakePanel(snake, WORLD_SCALE);
        getContentPane().add(snakePanel);
        pack();
        setResizable(false);
        setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P:
                        running = !running;
                        break;
                    case KeyEvent.VK_N:
                        JOptionPane.showMessageDialog(
                                null, "Nuevo juego!",
                                TITLE, JOptionPane.INFORMATION_MESSAGE);
                        newGame();
                        break;
                    case KeyEvent.VK_LEFT:
                        snake.goLeft();
                        break;
                    case KeyEvent.VK_DOWN:
                        snake.goDown();
                        break;
                    case KeyEvent.VK_RIGHT:
                        snake.goRight();
                        break;
                    case KeyEvent.VK_UP:
                        snake.goUp();
                        break;
                }
            }
        });
        JOptionPane.showMessageDialog(
                null,
                "A: Izquierda.\n" +
                        "S: Aabajo.\n" +
                        "D: Derecha.\n" +
                        "W: Arriba.\n" +
                        "N: Nuevo juego.\n" +
                        "P: Pausar/Continuar.",
                TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    void newGame() {
        gameOver = false;
        running = true;
        snake.reset();
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                while (!gameOver) try {
                    while (!running) {
                        wait(10);
                    }
                    gameOver = snake.step();
                    snakePanel.repaint();
                    wait(delay);
                } catch (InterruptedException ignored) {
                }
                if (snake.isDead) {
                    JOptionPane.showMessageDialog(
                            null, "Ouch! suerte para la próxima.",
                            TITLE, JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            null, "Yay! sabía que ganarías.",
                            TITLE, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }).start();
    }
}
