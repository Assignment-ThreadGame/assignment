/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author hannayasmin
 */

class Draw extends JComponent {

    ArrayList<Lines> lines;
    Random random;
    
    //colors for threads
    Color[] colors = {Color.black, Color.blue, Color.cyan, Color.darkGray, Color.green, Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red, Color.yellow};
    int idx = 0;

    Draw (int width, int height) {
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<Lines>();
        random = new Random();
    }

    public void addLine(int thread, float ax, float ay, float bx, float by) {
        idx = thread;
        Line2D.Double line = new Line2D.Double(
            ax,
            ay,
            bx,
            by
            );
        Color temp = colors[0];
        if(!(idx > colors.length))
            temp = colors[idx];
        Lines lines = new Lines(line, temp);
        this.lines.add(lines);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        Dimension d = getPreferredSize();
        for (Lines line : lines) {
            g2d.setColor(line.color);
            g2d.draw(line.line);
        }
    }
}
