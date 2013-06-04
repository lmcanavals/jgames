package org.aoeu.jgames.snake;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class SnakeGui extends JFrame {

    void start() {
        setTitle("Un Snake Super Simple");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SnakePanel snakePanel = new SnakePanel();
        getContentPane().add(snakePanel);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
