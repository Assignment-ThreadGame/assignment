import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
/**
 * To demonstrates how to draw lines using Graphics2D object.
 *
 */
public class LineDrawer extends JFrame {
 
    public LinesDrawer() {
        super("Lines Drawing");
 
        setSize(480, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    void drawLines(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
 
        g2d.drawLine(120, 50);
 
        g2d.draw(new Line2D.Double(59.2d, 99.8d));
 
        g2d.draw(new Line2D.Float(21.50f, 132.50f));
 
    }
 
    public void paint(Graphics g) {
        super.paint(g);
        drawLines(g);
    }
   
   SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LinesDrawingExample().setVisible(true);
            }
        });
}
