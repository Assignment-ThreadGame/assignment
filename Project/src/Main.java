
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        Scanner k = new Scanner(System.in);
        int n = 0, t = 0, m = 0;
        
        //get the thrads, number of points, and time
        while(n <= 0){
            System.out.println("How many points?");
            //n = k.nextInt();
            n = 4;
        }
        while(t <= 0 || n < t){
            System.out.println("How many threads?");
            //t = k.nextInt();
            t = 2;
        }
        while(m <= 0){
            System.out.println("How many seconds?");
            //m = k.nextInt();
            m = 90;
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
