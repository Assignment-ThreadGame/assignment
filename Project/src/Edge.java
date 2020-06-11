
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

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
    String name;
    Point a;
    Point b;

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }
    
    public Edge() {
        this.a = null;
        this.b = null;
    }
    
    public Edge(Point a, Point b, String name) {
        this.name = name;
        this.a = a;
        this.b = b;
    }
    
    public String toString(){
        return name + " between "+ a.toString() + " and " + b.toString();
    }
    
    public boolean exists(ArrayList<Edge> arr){
        try{
            for (int i = 0; i < arr.size(); i++) {
                if(arr.get(i).getA().same(a) || arr.get(i).getB().same(b)){
                    return true;
                }
                if(arr.get(i).getA().same(b) || arr.get(i).getB().same(a)){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public void draw(Graphics g) {
        int xPoints[] = {9, 15, 0, 18, 3};
        int yPoints[] = {0, 18, 6, 6, 18};

        Graphics2D g2d = (Graphics2D) g;

        
    }
}
