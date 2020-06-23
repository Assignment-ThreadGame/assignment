
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

    private  JLabel number = new JLabel("Start a game with...");
    private  JLabel upoint = new JLabel("Points:"); 
    private  JLabel uthread = new JLabel("Threads:");
    private  JLabel useconds = new JLabel("Seconds:");
    
    private  JTextField point = new JTextField(); 
    private  JTextField thread = new JTextField(); 
    private  JTextField seconds = new JTextField(); 
    
    private  JButton play = new JButton("Start Game");
    private  JButton exit = new JButton("Exit");
    
    private JFrame frame1 = new JFrame();
    private JFrame frame2 = new JFrame();
    private JFrame frame3 = new JFrame();
    private JFrame frame4 = new JFrame();
    private JFrame frame5 = new JFrame();
    private JFrame frame6 = new JFrame();

  public MainPanel(){
        
        setTitle("Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

    component();
    operation();   
    
  }

  private void component(){
    
    //button
    play.setBounds(110, 280, 130, 25);
    exit.setBounds(260, 280, 130, 25);
    add(play);
    add(exit);
    
    
    //label
    number.setBounds(170, 100, 200, 40);
    upoint.setBounds(140, 150, 200, 40);
    uthread.setBounds(140, 180, 200, 40);
    useconds.setBounds(140, 210, 200, 40);
    
    add(useconds);
    add(uthread);
    add(number);
    add(upoint);
    
    
    //textfield
    point.setBounds(210, 160, 165, 25);
    thread.setBounds(210, 190, 165, 25);
    seconds.setBounds(210, 220, 165, 25);
    
    add(seconds);
    add(thread);
    add(point);
  }

  private void operation(){

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
       System.exit(0);
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
      
        if(n>=1 && n>t && m>0){
        
            //declare executor
            ExecutorService e = Executors.newFixedThreadPool(t);
      
            //declare game
            Game g = new Game(n, t, m, e);
        }   
        else if (n>=1 && n>t && m<0) {
            
            frame1.setVisible(true);
            frame1.setSize(330, 100);
            frame1.setLocationRelativeTo(null);
        
            JLabel error1 = new JLabel("Error!\nNumber of second must be more than one.");
            frame1.add(error1);
        }
        else if(n<1 && n>t && m>0){
        
            frame2.setVisible(true);
            frame2.setSize(350, 100);
            frame2.setLocationRelativeTo(null);
        
            JLabel error2 = new JLabel("Error!\nNumber of point must be more than one.");
            frame2.add(error2);
        }
        else if(n>1 && n<t && m>0){
            
            frame3.setVisible(true);
            frame3.setSize(500, 100);
            frame3.setLocationRelativeTo(null);
        
            JLabel error3 = new JLabel("Error!\nNumber of point must be more than the number of thread."); //done
            frame3.add(error3);
        }
        else if (n<1 && n>t && m<1){
            
            frame4.setVisible(true);
            frame4.setSize(500, 100);
            frame4.setLocationRelativeTo(null);
        
            JLabel error4 = new JLabel("Error!\nNumber of point and second must be more than one");
            frame4.add(error4);
        }
        else if (n>1 && n<t && m<1){
           
            frame5.setVisible(true);
            frame5.setSize(500, 100);
            frame5.setLocationRelativeTo(null);
        
            JLabel error5 = new JLabel("Error!\nNumber of point must be more than the number of thread and number of second must be more than one");
            frame4.add(error5);
        }
        else {
            
            frame6.setVisible(true);
            frame6.setSize(750, 100);
            frame6.setLocationRelativeTo(null);
            
            JLabel error6 = new JLabel("Error!\nNumber of point and second must be more than one & number of thread must be less than the number of point.");
            frame6.add(error6);
        }

    }catch(Exception e){
        System.out.println(e);
        JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}


