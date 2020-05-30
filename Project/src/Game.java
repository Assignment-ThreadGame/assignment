
import java.awt.*;
import java.awt.geom.Line2D;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sarahsyazwina
 */
public class Game extends JPanel{
    private static int n, t, m;
    private static int WIDTH = 1000, HEIGHT = 1000;
    Point p[];
    
    public Game(int n, int t, int m){
        //initialise game n = points, t = threads, m = seconds
        Game.t = t;
        Game.n = n;
        Game.m = m;
        p = new Point[n];
        //genPoints();
        drawBoard();
        //drawPoints();
        //launch threads
        //any thread wins or time = m end program
        //display results
    }
    
    private void genPoints(){
        //generate points
        Point temp[] = new Point[Game.n];
        int count = 0;
        while(count < n){
            
            count++;
        }
    }
    
    private void drawPoints(){
        //draw points on board
    }
    
    private void drawBoard(){
        //draw 1000*1000 panel
        JFrame board = new JFrame("Board");
        JPanel panel = new JPanel();
        board.setSize(new Dimension (WIDTH, HEIGHT));
        panel.setPreferredSize(new Dimension (WIDTH, HEIGHT));
        board.add(panel);
        board.setResizable(false);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
    }
    
    private void drawLine(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.drawLine(90, 0, 134, 789);
    }
    
    @Override
    public void paintComponent(Graphics g){
        System.out.println("paint");
        super.paintComponent(g);
        drawLine(g);
    }
}
