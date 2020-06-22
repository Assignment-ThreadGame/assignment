import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        
        setTitle("Games");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

    component();
    operation();   
  }

  private void component(){
    
    //button
    play.setBounds(10, 170, 150, 25);
    exit.setBounds(200, 170, 150, 25);
    add(play);
    add(exit);
    
    
    //label
    number.setBounds(10, 20, 200, 40);
    upoint.setBounds(10, 50, 200, 40);
    uthread.setBounds(10, 80, 200, 40);
    useconds.setBounds(10, 110, 200, 40);
    
    add(useconds);
    add(uthread);
    add(number);
    add(upoint);
    
    
    //textfield
    point.setBounds(140, 60, 165, 25);
    thread.setBounds(140, 90, 165, 25);
    seconds.setBounds(140, 120, 165, 25);
    
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
      
      //declare executor
      ExecutorService e = Executors.newFixedThreadPool(t);
      
      //declare game
      Game g = new Game(n, t, m, e);

    }catch(Exception e){
      System.out.println(e);
      JOptionPane.showMessageDialog(null, 
          e.toString(),
          "Error", 
          JOptionPane.ERROR_MESSAGE);
    }
  }
}