/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sarahsyazwina
 */
public class Point {
    Edge edge;
    float x;
    float y;
    
    public Point(float x, float y){
        this.edge = null;
        this.x = x;
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public String toString(){
        return "( " + x + ", " + y + " )";
    }
}
