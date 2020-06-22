
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private static ArrayList<Line> lines  = new ArrayList<>();
    
    //other
    private static int n, t, m, z;
    private static ExecutorService e;
    private static int WIDTH = 1000, HEIGHT = 1000;
    private static long startTime;
    
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
         
    
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
        //generate n random points
        genPoints();
        //start threads
        startThreads();
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
        printEnd(tc);
//        System.out.println("-----");
//        for (int i = 0; i < tc.length; i++) {
//            System.out.println(tc[i].getName() + " created " + tc[i].getWin() + " edge(s) and failed " + tc[i].getFail() + " time(s).");
//        }
    }
    
    private void printEnd(ThreadController tc[] ){
        
//        System.out.println("-----");

            frame.setSize(450, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.add(panel);
          
            for (int i = 0; i < tc.length; i++) {
                JLabel user = new JLabel(tc[i].getName() + " created " + tc[i].getWin() + " edge(s) and failed " + tc[i].getFail() + " time(s).");
                user.setBounds(100, 300, 200, 40);
                panel.add(user);
            //  System.out.println(tc[i].getName() + " created " + tc[i].getWin() + " edge(s) and failed " + tc[i].getFail() + " time(s).");
        }
    }
    
    public Status addEdge(){
        try{
            System.out.println("-----\n" + Thread.currentThread().getName() + " attempting...");
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
                System.out.println("-----\n" + Thread.currentThread().getName() + " failure, edge attempt between : " + a.toString() + " and " + b.toString());
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
        System.out.println(Thread.currentThread().getName() + " found no more possible edges...");
        return Status.END;
    }
    
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
    //  System.out.println("-----");
    //  System.out.println("Generated points: " );
        JLabel pp = new JLabel("Generated points:" + System.lineSeparator() + " ");
        pp.setBounds(0, 0, 200, 40);
        panel.add(pp);
        
        for (int i = 0; i < points.size(); i++) {
            JLabel user = new JLabel(points.get(i).toString());
            panel.add(user);
        //  System.out.println(points.get(i).toString());
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
    
    public ArrayList<Edge> getEdges(){
        return edges;
    }
    
    public ArrayList<Point> getPoints(){
        return points;
    }
}
