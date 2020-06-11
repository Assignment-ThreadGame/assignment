
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sarahsyazwina
 */
public class ThreadController implements Runnable{
    
    public static Game g;
    public final int MAX = 20;
    public boolean stopGame = false;
    private int fail;
    private int win;

    public ThreadController(Game g) {
        ThreadController.g = g;
    }

    @Override
    public void run() {
        String t = Thread.currentThread().getName();
        try{
            //while the thread has not failed > 20 and game is not over
            while(fail < MAX && !isOver() && !stopGame){
                boolean result = g.getBoard().addEdge();
                if(result){
                    win++;
                }
                else{
                    fail++;
                }
            }
            Thread.sleep(1000);
            System.out.println("-----\nEnd thread : " + t + "\n" + "Runtime : " + runTime() + " seconds");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private boolean isOver(){
        long now = System.nanoTime();
        return (now - g.getStart()) > g.getTimeLimit();
    }
    
    private double runTime(){
        long now = System.nanoTime();
        return (double) (now - g.getStart()) / 1_000_000_000;
    }
}
