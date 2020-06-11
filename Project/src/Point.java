
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
    
    public boolean exists(ArrayList<Point> arr){
        try{
            for (int i = 0; i < arr.size(); i++) {
                if(arr.get(i).getX() == x && arr.get(i).getY() == y){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public String toString(){
        return "( " + x + ", " + y + " )";
    }
}
