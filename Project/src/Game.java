
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.*;
import javax.swing.border.MatteBorder;

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
        
    //other
    private static int n, t, m, z;
    private static ExecutorService e;
    private static int WIDTH = 1000, HEIGHT = 1000;
    private static long startTime;
    
    private static Draw line = new Draw(1000, 1000);
    private static JPanel panel = new JPanel(new GridBagLayout());
    private static JFrame frame = new JFrame();
    private static GridBagConstraints c = new GridBagConstraints();
    
    private static final String[] colors = {"black", "blue", "aqua", "gray", "green", "silver", "fuchsia", "orange", "purple", "red", "yellow"};
    
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
        Game.c.insets = new Insets(4, 4, 4, 4);
        Game.c.gridx = 0;
        Game.c.weightx = 1;
        Game.c.gridy = 0;
        Game.c.anchor = GridBagConstraints.WEST;
        z = 0;
        panel.removeAll();
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
        
        //visualising game
        printEdges();
        printEnd(tc);
        System.out.println("-----");
        for (ThreadController tc1 : tc) {
            System.out.println(tc1.getName() + " created " + tc1.getWin() + " edge(s) and failed " + tc1.getFail() + " time(s).");
        }
        
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < edges.size(); i++) {
                    line.addLine((edges.get(i).getThread()-1),edges.get(i).getA().getX(),edges.get(i).getA().getY(),edges.get(i).getB().getX(),edges.get(i).getB().getY());
                }
                JOptionPane.showMessageDialog(null, line);
            }
        };
        SwingUtilities.invokeLater(r);
    }
    
    private void printEnd(ThreadController tc[]) {
        JLabel pe = new JLabel("<html> <b>Edges created</b>:" + "<br/><br/>");
        for (int i = 0; i < edges.size(); i++) {
            pe.setText(pe.getText() + edges.get(i).toString() + "<br/>");
        }
        pe.setText(pe.getText() + "</html>");
        pe.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        panel.add(pe, c);
        c.gridy++;
        System.out.println("-----");
        JLabel user = new JLabel("<html> <b>Thread status</b>:" + "<br/> <br/>");
        for (int i = 0; i < tc.length; i++) {
            int idx = 0;
            if(!((tc[i].getNum() - 1) > colors.length))
                idx = tc[i].getNum() - 1;
            user.setText(user.getText() + "<font color=" + colors[idx] + "> " + tc[i].getName() + "</font> created " + tc[i].getWin() + " edge(s) and failed " + tc[i].getFail() + " time(s). <br/>");
            System.out.println(tc[i].getName() + " created " + tc[i].getWin() + " edge(s) and failed " + tc[i].getFail() + " time(s).");
        }
        user.setText(user.getText() + "</html>");
        user.setBorder(new MatteBorder(1, 0, 0, 0, Color.BLACK));
        panel.add(user, c);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(panel);
        frame.pack();
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
        System.out.println("-----");
        System.out.println("Generated points: " );
        JLabel pp = new JLabel("<html> <b>Generated points</b>:" + "<br/> <br/>");

        for (int i = 0; i < points.size(); i++) {
            pp.setText(pp.getText() + points.get(i).toString() + "<br/>");
            System.out.println(points.get(i).toString());
        }
        pp.setText(pp.getText() + "</html>");
        panel.add(pp, c);
        c.gridy++;
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
