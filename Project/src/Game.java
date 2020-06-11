
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;
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
    
    //arraylists
    private static ArrayList<Point> points = new ArrayList<Point>();
    private static ArrayList<Edge> edges = new ArrayList<Edge>();
    private static ArrayList<Object> shapes = new ArrayList<>();
    
    //other
    private static int n, t, m, z;
    private static ExecutorService e;
    private static int WIDTH = 1000, HEIGHT = 1000;
    private static long startTime;
    private static Board b;
    
    //status
    public enum Status{
        SUCCESS,
        FAIL,
        END
    }
    
    //locks
    private ReentrantLock lock = new ReentrantLock();
    
    public Game(int n, int t, int m, ExecutorService e){
        //initialise game n = points, t = threads, m = seconds
        Game.t = t;
        Game.n = n;
        Game.m = m;
        Game.e = e;
        z = 0;
        //for actions on the board
        //Game.b = new Board();
        //testDraw();
        //generate n random points
        genPoints();
        //start threads
        startThreads();
        //drawBoard();
        //drawPoints();
        //launch threads
        //any thread wins or time = m end program
        //display results
        //draw board with points and edges
    }
    
    private void startThreads(){
        setStart();
        ThreadController tc[] = new ThreadController[t];
        int count = 0;
        while(count < t){
            tc[count] = new ThreadController(this);
            e.submit(tc[count]);
            count++;
        }
        e.shutdown();
        while(!e.isTerminated()){
            
        }
        printEdges();
        
        for (int i = 0; i < tc.length; i++) {
            System.out.println(tc[i].getName() + " created " + tc[i].getWin() + " edge(s) and failed " + tc[i].getFail() + " time(s).");
        }
    }
    
    public Status addEdge(){
        try{
            //lock for other threads
            lock.lock();
            //for every two points only one edge is possible, threfore the number
            //of possible edges is n/2
            int possible = points.size() / 2;
            //if there are still edges to be made
            if(edges.size() < possible ){
                Point a = points.get(new Random().nextInt(points.size()));
                Point b = points.get(new Random().nextInt(points.size()));

                //diff points
                while(a.same(b)){
                    b = points.get(new Random().nextInt(points.size()));
                }

                //create edge between points
                String s = "Edge " + z;
                Edge temp = new Edge(a,b,s,Thread.currentThread().getName());

                if(!temp.exists(edges)){
                    System.out.println("-----\nEdge being created by " + Thread.currentThread().getName()
                            + " between " + a.toString() + " and " + b.toString());
                    edges.add(temp);
                    printEdges();
                    z++;
                    return Status.SUCCESS;
                }
                return Status.FAIL;
            }
            //unlock for other threads
//            return Status.END;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            lock.unlock();
        }
        return Status.END;
    }
    
//    private void testDraw(){
//        JFrame board = new JFrame("Board");
//        board.setSize(new Dimension (WIDTH, HEIGHT));
//        JLabel l = new JLabel();
//        board.getContentPane().add(l);
//        board.getContentPane().addMouseMotionListener(new MyMouseAdapter(l));
//        board.setResizable(false);
//        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        board.setVisible(true);
//    }
//    
//    private class MyMouseAdapter extends MouseAdapter {
//        JLabel j;
//        private MyMouseAdapter(JLabel l){
//            j = l;
//        }
//        @Override
//        public void mouseMoved(MouseEvent e) {
//            // get Point location and turn into a String
//            String location = String.format("[%d, %d]", e.getX(), e.getY());
//
//            // set the label's text with this String
//            j.setText(location);
//        }
//    }
    
    private void genPoints(){
        //generate points
        ArrayList<Point> temp = new ArrayList<Point>();
        while(temp.size() != n){
            Random r = new Random();
            float x = (float) (Math.round((r.nextFloat() * WIDTH) * 100.0) / 100.0);
            float y = (float) (Math.round((r.nextFloat() * WIDTH) * 100.0) / 100.0);
            Point test = new Point(x, y, String.valueOf(temp.size()));
            if(!test.exists(temp)){
                temp.add(test);
            }
        }
        points = temp;
        printPoints();
    }
    
    private void printPoints(){
        System.out.println("-----");
        System.out.println("Generated points: ");
        for (int i = 0; i < points.size(); i++) {
            System.out.println(points.get(i).toString());
        }
    }
    
    private void printEdges(){
        System.out.println("-----");
        System.out.println("Edges: ");
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(edges.get(i).toString());
        }
    }
    
    public long getTimeLimit(){
        return m;
    }
    
    public void setStart(){
        Game.startTime = System.nanoTime();
    }
    
    public long getStart(){
        return startTime;
    }
    
    public Board getBoard(){
        return b;
    }
    
    public ArrayList<Edge> getEdges(){
        return edges;
    }
    
    public ArrayList<Point> getPoints(){
        return points;
    }
    
    private void drawPoints(){
        //draw points on board
    }
    
    private void drawBoard(){
        //draw 1000*1000 panel
        JFrame board = new JFrame("Board");
        Draw panel = new Draw();
        board.setSize(new Dimension (WIDTH, HEIGHT));
        board.getContentPane().add(panel);
        board.setResizable(false);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
    }
    
    class Draw extends JPanel{
        
        @Override
        public void paintComponent(Graphics g){
            System.out.println("paint");
            super.paintComponent(g);
//            for(Object s : shapes){
//                if(s instanceof Edge){
//                    ((Edge) s).draw(g);
//                }
//            }
            drawLine(g);
            
        }
        
        private void drawLine(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            Shape s = new Line2D.Double(90.0, 39.59, 134.0, 789.8);
            g2d.draw(s);
        }
    }
}
