
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
    public boolean stopGame;
    private int fail;
    private int win;
    private String name;
    
    public ThreadController(Game g) {
        this.g = g;
        this.fail = 0;
        this.win = 0;
        stopGame = false;
    }

    @Override
    public void run() {
        String t = Thread.currentThread().getName();
        String s[] = t.split("-");
        name = "Thread " + s[3];
        System.out.println("-----\nStarting : " + name);
        try{
            //while the thread has not failed > 20 and game is not over
            while(fail < MAX && !isOver() && !stopGame){
                Game.Status result = g.addEdge();
                switch(result){
                    case SUCCESS:
                        win++;
                        break;
                    case FAIL:
                        fail++;
                        break;
                    case END:
                        stopGame = true;
                        break;
                    default:
                }
            }
            System.out.println("-----\nEnd : " + name + "\n" + "Runtime : " + runTime() + " seconds");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public int getFail() {
        return fail;
    }

    public int getWin() {
        return win;
    }
    
    private boolean isOver(){
        long now = System.nanoTime();
        double test = (now - g.getStart()) / 1_000_000_000;
//        System.out.println("Runtime: " + test + " > " + g.getTimeLimit());
        if(test >= g.getTimeLimit()){
            return true;
        }
        return false;
    }
    
    private double runTime(){
        long now = System.nanoTime();
        return (double) (now - g.getStart()) / 1_000_000_000;
    }
}
