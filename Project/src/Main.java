
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sarahsyazwina
 */
public class Main implements ActionListener {
    
    private static JLabel user, upoint, uthread, useconds;
    private static JTextField point, thread, seconds;
    private static JButton play;
    
    
    public static void main(String[] args) {
        
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(2000, 2000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        
        panel.setLayout(null);
        
        user = new JLabel("Enter the number of");
        user.setBounds(10, 20, 200, 40);
        panel.add(user);
       
        
        //get the thrads, number of points, and time
        //points must be more than 1
        
            upoint = new JLabel("Points:");
            upoint.setBounds(10, 50, 200, 40);
            panel.add(upoint);
       
            point = new JTextField(20);
            point.setBounds(140, 60, 165, 25);
            panel.add(point);
            
            String text = point.getText();
            int n = Integer.parseInt(text);
        
        //threads must be less than n
       
            uthread = new JLabel("Threads:");
            uthread.setBounds(10, 80, 200, 40);
            panel.add(uthread);
       
            thread = new JTextField(20);
            thread.setBounds(140, 90, 165, 25);
            panel.add(thread);
            
            String text2 = thread.getText();
            int t = Integer.parseInt(text);
        
        //seconds must be more than 1
        
            useconds = new JLabel("Seconds");
            useconds.setBounds(10, 110, 200, 40);
            panel.add(useconds);
       
            seconds = new JTextField(20);
            seconds.setBounds(140, 120, 165, 25);
            panel.add(seconds);
            
            String text3 = seconds.getText();
            int m = Integer.parseInt(text);
        
        frame.setVisible(true);
        
        
        //button
        play = new JButton("Start Game");
        play.setBounds(10, 150, 150, 25);
        play.addActionListener(new Main());
        panel.add(play);
        
        //declare executor
        ExecutorService e = Executors.newFixedThreadPool(t);
        
        
        //declare game
        Game g = new Game(n, t, m, e);
        
//        //start threads
//        ThreadController tc[] = new ThreadController[t];
//        int count = 0;
//        while(count < t){
//            tc[count] = new ThreadController(g);
//            e.submit(tc[count]);
//            count++;
//        }
//        //set game start time now
//        g.setStart();
//        
//        e.shutdownNow();
//        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
    }
}
