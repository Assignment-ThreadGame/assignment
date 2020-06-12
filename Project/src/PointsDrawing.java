/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointsdrawing;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author hannayasmin
 */
public class PointsDrawing extends JFrame {

    /**
     * @param args the command line arguments
     */
    
    public PointsDrawing(){
        
        JPanel panel = new JPanel();
        
        setTitle("Game");
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        panel.setBorder(BorderFactory.createEmptyBorder(1000, 1000, 1000, 1000));
        panel.setLayout(new GridLayout(1000,1000));
        
    }
    
    public void paint(Graphics g){
        g.setColor(Color.BLACK); 
        g.fillOval(100, 100, 10, 10);
        
  /*    super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(20, 20, 40, 40);
        g2.fill(circle);    */
        
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        PointsDrawing pd = new PointsDrawing();
        pd.paint(null);
    }
    
}
