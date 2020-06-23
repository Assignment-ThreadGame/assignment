
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public class Main {
    
    public static void main(String[] args) {
        
        MainPanel mp = new MainPanel();
        mp.setVisible(true);
        
    }    
    
}
class MainPanel extends JFrame {

    private  JLabel number = new JLabel("Enter the number of");
    private  JLabel upoint = new JLabel("Points:"); 
    private  JLabel uthread = new JLabel("Threads:");
    private  JLabel useconds = new JLabel("Seconds:");
    
    private  JTextField point = new JTextField(); 
    private  JTextField thread = new JTextField(); 
    private  JTextField seconds = new JTextField(); 
    
    private  JButton play = new JButton("Start Game");
    private  JButton exit = new JButton("Exit");

  public MainPanel(){
        
        setTitle("Game");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

    component();
    operation();   
    
  }

  private void component(){
    
    //button
    play.setBounds(350, 500, 150, 25);
    exit.setBounds(500, 500, 150, 25);
    add(play);
    add(exit);
    
    
    //label
    number.setBounds(400, 310, 200, 40);
    upoint.setBounds(400, 350, 200, 40);
    uthread.setBounds(400, 380, 200, 40);
    useconds.setBounds(400, 410, 200, 40);
    
    add(useconds);
    add(uthread);
    add(number);
    add(upoint);
    
    
    //textfield
    point.setBounds(460, 360, 165, 25);
    thread.setBounds(460, 390, 165, 25);
    seconds.setBounds(460, 420, 165, 25);
    
    add(seconds);
    add(thread);
    add(point);
  }

  private void operation(){

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
       System.exit(1);
      }
    });

    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        exitclick(e);
      }
    });

    play.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         playclick(e);
      }
    });
  }
  
  private void exitclick(ActionEvent evt){
    System.exit(0);
  }
  
  private void playclick(ActionEvent evt){
    Integer n,t,m;
    try{
      n = Integer.parseInt(point.getText());
      t = Integer.parseInt(thread.getText());
      m = Integer.parseInt(seconds.getText());
      
      if(n>=1){
        if (n>t){
            //declare executor
            ExecutorService e = Executors.newFixedThreadPool(t);
      
            //declare game
            Game g = new Game(n, t, m, e);
            
        }
        else {
        
        JFrame frame1 = new JFrame();
        frame1.setVisible(true);
        frame1.setSize(420, 100);
        frame1.setLocationRelativeTo(null);
        
        JLabel error1 = new JLabel("Error!\nNumber of threads must be less than the number of point.");
        
        frame1.add(error1);
        }
        
      }
      else {
        
            JFrame frame2 = new JFrame();
            frame2.setVisible(true);
            frame2.setSize(350, 100);
            frame2.setLocationRelativeTo(null);
            
            JLabel error2 = new JLabel("Error!\nNumber of points must be more than one.");
        
            frame2.add(error2);
        }

    }catch(Exception e){
      System.out.println(e);
      JOptionPane.showMessageDialog(null, 
          e.toString(),
          "Error", 
          JOptionPane.ERROR_MESSAGE);
    }
  }
}


