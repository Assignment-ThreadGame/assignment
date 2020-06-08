
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;

 
/**
 * To demonstrates how to draw lines using Graphics2D object.
 *
 */
public class LinesDrawing extends JFrame {
 
    public LinesDrawing() {
        setTitle("Line Drawing");
        setSize(960, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }
 
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.drawLine(0, 480, 960, 480);
        g.setColor(Color.BLUE);
        g.drawLine(0, 0, 960, 960);
        g.setColor(Color.GREEN);
        g.drawLine(300, 300, 500, 100);
 
    }
 
 
    public static void main(String[] args) {
      LinesDrawing ld = new LinesDrawing();
    }
}
