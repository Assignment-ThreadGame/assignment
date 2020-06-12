
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sarahsyazwina
 */
public class Main {
    public static void main(String[] args) {
        //Draw line
//        JFrame testFrame = new JFrame();
//        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        final LinesComponent comp = new LinesComponent();
//        comp.setPreferredSize(new Dimension(320, 200));
//        testFrame.getContentPane().add(comp, BorderLayout.CENTER);
//        JPanel buttonsPanel = new JPanel();
//        JButton newLineButton = new JButton("New Line");
//        JButton clearButton = new JButton("Clear");
//        buttonsPanel.add(newLineButton);
//        buttonsPanel.add(clearButton);
//        testFrame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        
        //Values
        Scanner k = new Scanner(System.in);
        int n = 0, t = 0, m = 0;
        
        //get the thrads, number of points, and time
        //points must be more than 1
        while(n <= 1){
            System.out.println("How many points?");
            n = k.nextInt();
            //n = 20;
        }
        //threads must be less than n
        while(t <= 0 || n < t){
            System.out.println("How many threads?");
            t = k.nextInt();
            //t = 4;
        }
        //seconds must be more than 1
        while(m <= 0){
            System.out.println("How many seconds?");
            m = k.nextInt();
            //m = 90;
        }
        
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
}
