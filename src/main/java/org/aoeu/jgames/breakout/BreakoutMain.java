package org.aoeu.jgames.breakout;

import java.awt.EventQueue;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class BreakoutMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BreakoutGui().go();
            }
        });
    }
}
