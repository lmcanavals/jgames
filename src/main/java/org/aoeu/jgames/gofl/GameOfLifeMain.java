package org.aoeu.jgames.gofl;

import java.awt.EventQueue;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class GameOfLifeMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOfLifeGui().start();
            }
        });

    }
}
