package org.aoeu.jgames.snake;

import java.awt.EventQueue;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class SnakeMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeGui().start();
            }
        });
    }
}
