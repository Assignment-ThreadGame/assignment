
import java.awt.*;
import java.awt.geom.GeneralPath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sarahsyazwina
 */
public class Edge {
    Point a;
    Point b;
    
    public Edge() {
        this.a = null;
        this.b = null;
    }
    
    public Edge(Point a, Point b) {
        this.a = a;
        this.b = b;
    }
    
    public void draw(Graphics g) {
        int xPoints[] = {9, 15, 0, 18, 3};
        int yPoints[] = {0, 18, 6, 6, 18};

        Graphics2D g2d = (Graphics2D) g;

        
    }
}
