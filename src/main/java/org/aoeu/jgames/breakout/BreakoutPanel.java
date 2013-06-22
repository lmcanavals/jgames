package org.aoeu.jgames.breakout;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class BreakoutPanel extends JPanel {

    Breakout breakout;

    public BreakoutPanel(Breakout breakout) {
        this.breakout = breakout;
        setPreferredSize(new Dimension(breakout.width, breakout.height));
        setBackground(Color.orange);
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Pintando pelotita de color rojo.
        g.setColor(Color.red);
        g.fillOval(breakout.sphereLeft, breakout.sphereTop,
                breakout.sphereDiam, breakout.sphereDiam);
        // Dibujando borde negro de la pelotita.
        g.setColor(Color.black);
        g.drawOval(breakout.sphereLeft, breakout.sphereTop,
                breakout.sphereDiam, breakout.sphereDiam);

        // Pintando la barrita de color venrde.
        g.setColor(Color.green);
        g.fillRect(breakout.barLeft, breakout.barTop,
                breakout.barWidth, breakout.barHeight);
        // Dibujando berde de la barrita de color negro.
        g.setColor(Color.black);
        g.drawRect(breakout.barLeft, breakout.barTop,
                breakout.barWidth, breakout.barHeight);
    }
}
