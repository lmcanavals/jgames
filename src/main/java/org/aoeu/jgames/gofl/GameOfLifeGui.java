/*
 * 01010011 01100001 01101101 01110000 01101100 01100101 
 * 01000011 01101111 01100100 01100101 00100001
 */
package org.aoeu.jgames.gofl;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class GameOfLifeGui extends JFrame {

    static final int WORLD_WIDTH = 100;
    static final int WORLD_HEIGHT = 70;
    static final int WORLD_SCALE = 10;

    int delay = 250;
    GameOfLife gol;
    GameOfLifePanel golCanvas;
    boolean running;
    boolean forever;

    void start() {
        setTitle("El juego de la vida");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gol = new GameOfLife(WORLD_WIDTH, WORLD_HEIGHT);
        golCanvas = new GameOfLifePanel(gol, WORLD_SCALE);
        setVisible(true);
        getContentPane().add(golCanvas);
        pack();
        setResizable(false);
        golCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gol.toggleCell(e.getX() / WORLD_SCALE, e.getY() / WORLD_SCALE);
                golCanvas.repaint();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 't') {
                    running = !running;
                }
            }
        });
        new Thread(new Runnable() {
            public synchronized void run() {
                forever = true;
                while (forever) {
                    try {
                        while (!running) {
                            wait(10);
                        }
                        wait(delay);
                    } catch (InterruptedException ignored) {
                    }
                    gol.nextGeneration();
                    golCanvas.repaint();
                }
            }
        }).start();
        JOptionPane.showMessageDialog(
                this,
                "Pon unas células y pulsa 't' para iniciar o pausar.",
                "El juego de la vida de Conway",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
