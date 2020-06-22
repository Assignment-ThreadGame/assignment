import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;

import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import java.util.ArrayList;
import java.util.Random;


class Lines extends JComponent {

    ArrayList<Line2D.Double> lines;
    Random random;

    Lines (int width, int height) {
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<Line2D.Double>();
        random = new Random();
    }

    public void addLine() {
        int width = (int)getPreferredSize().getWidth();
        int height = (int)getPreferredSize().getHeight();
        Line2D.Double line = new Line2D.Double(
            random.nextInt(width),
            random.nextInt(height),
            random.nextInt(width),
            random.nextInt(height)
            );
        lines.add(line);
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        Dimension d = getPreferredSize();
        g.setColor(Color.black);
        for (Line2D.Double line : lines) {
            g.drawLine(
                (int)line.getX1(),
                (int)line.getY1(),
                (int)line.getX2(),
                (int)line.getY2()
                );
        }
    }
}