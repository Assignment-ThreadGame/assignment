/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class GameMain {
    public static void main(String[]args){
        //Game game1 = new Game(3,2,5);
        int n=2;
        for(int i=0;i<2;i++){
            Thread game = new Thread(new Game(3,2,5));
            game.start();
        }
        
    }
}
