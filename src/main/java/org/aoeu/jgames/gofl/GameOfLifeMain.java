/*
 * 01010011 01100001 01101101 01110000 01101100 01100101 
 * 01000011 01101111 01100100 01100101 00100001
 */
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
