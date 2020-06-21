
import java.util.InputMismatchException;
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
        //points must be more than 1
        do{
            try{
               System.out.println("How many points?");
                n = k.nextInt();
                //n = 11; 
            }catch(InputMismatchException e){
                k.nextLine();
            }
            
        }while(n <= 1);
        //threads must be less than n
        do{
            try{
               System.out.println("How many threads?");
                t = k.nextInt();
                //t = 3;
            }catch(InputMismatchException e){
                k.nextLine();
            }
            
        }while(t <= 0 || n < t);
        //seconds must be more than 1
        do{
            try{
               System.out.println("How many seconds?");
                m = k.nextInt();
                //m = 90;
            }catch(InputMismatchException e){
                k.nextLine();
            }
            
        }while(m <= 0);
        
        //declare executor
        ExecutorService e = Executors.newFixedThreadPool(t);
        
        //declare game
        Game g = new Game(n, t, m, e);
    }
}
