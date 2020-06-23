
import java.awt.*;
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
    int creator;
    Point a;
    Point b;
    
    public int getThread(){
        return creator;
    }

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
    
    public Edge(Point a, Point b, String name, String creator) {
        String s[] = creator.split("-");
        this.creator = Integer.parseInt(s[3]);
        this.name = name;
        this.a = a;
        this.b = b;
    }
    
    public String toString(){
        return name + " between "+ a.toString() + " and " + b.toString() + " created by Thread " + creator;
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
}
