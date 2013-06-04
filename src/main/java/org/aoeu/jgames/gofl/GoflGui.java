/*
 * 01010011 01100001 01101101 01110000 01101100 01100101 
 * 01000011 01101111 01100100 01100101 00100001
 */
package org.aoeu.jgames.gofl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Martín Canaval Sánchez
 */
public class GoflGui extends JFrame {

    static final int GOFL_WIDTH = 100;
    static final int GOFL_HEIGHT = 70;
    Gofl gofl;
    GoflCanvas goflCanvas;
    boolean paused = true;

    public void ejecutar() {
        setTitle("El juego de la vida");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gofl = new Gofl(GOFL_WIDTH, GOFL_HEIGHT);
        goflCanvas = new GoflCanvas(gofl);
        setVisible(true);
        getContentPane().add(goflCanvas);
        pack();
        setResizable(false);
        goflCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int scale = goflCanvas.getScale();
                gofl.toggleCell(e.getX() / scale, e.getY() / scale);
                goflCanvas.repaint();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 't') {
                    paused = !paused;
                }
            }
        });
        new Thread(new Runnable() {
            public synchronized void run() {
                while (true) {
                    try {
                        while (paused) {
                            wait(10);
                        }
                        wait(250);
                    } catch (InterruptedException ex) {
                    }
                    gofl.nextGeneration();
                    goflCanvas.repaint();
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
