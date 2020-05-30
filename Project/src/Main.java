
import java.util.Scanner;

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
            m = 60;
        }
        Game g = new Game(n, t, m);
    }
}
