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
            // while the thread has not failed and game is not over
            while(fail < MAX && !isOver() && !stopGame){
                boolean result = g.getBoard().addEdge();
                if(result){
                    win++;
                }
                else{
                    fail++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean isOver(){
        long now = System.nanoTime();
        return (now - g.getStart()) > g.getTimeLimit();
    }
}
