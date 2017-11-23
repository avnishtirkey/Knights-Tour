import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;




public class ButtonGridEg extends JPanel {
   private static final int ROWS = 8;
   public static final String APP_TITLE="Knight's Tour";
   private static final int COLS = ROWS;
   private static final int GAP = 1;
   private static JButton[][] buttonGrid = new JButton[ROWS][COLS];
   private static List<int[]> res = new ArrayList<>();
   private static final JPanel P1=new JPanel();
   private static final JPanel P2=new JPanel();
   
   
   private static int i=0;
   
   
   
   public ButtonGridEg() {
      setLayout(new GridLayout(ROWS, COLS, GAP, GAP));
           
      setPreferredSize(new Dimension(480, 480));
      setForeground(Color.BLACK);
      ActionListener buttonListener;
       buttonListener = new ActionListener() {
           
           @Override
           public void actionPerformed(ActionEvent evt) {
               JButton selectedBtn = (JButton) evt.getSource();
               int row,col;
               
               for (row = 0; row < buttonGrid.length; row++) {
                   for (col = 0; col < buttonGrid[row].length; col++) {
                       if (buttonGrid[row][col] == selectedBtn) {
                                                     
                           res=KnightsTour.KT(row,col);
                           break;
                       }
                   }
               }
                  Timer timer= new Timer();
                  TimerTask task=new TimerTask(){
       public void run(){
                 int t=res.get(i)[0];
                 int r=res.get(i)[2];
                 int c=res.get(i)[1];
                 
                 buttonGrid[r][c].setText(Integer.toString(t));
                 buttonGrid[r][c].setBackground(Color.green);
                 i++;
                 if(i==64)
                     cancel();
       }
   };
                 timer.scheduleAtFixedRate(task, 1000, 1000);    
           }
     
       };
      for (int row = 0; row < buttonGrid.length; row++) {
         for (int col = 0; col < buttonGrid[row].length; col++) {
            String text = String.format("");
            
            buttonGrid[row][col] = new JButton(text);
            buttonGrid[row][col].addActionListener(buttonListener);
            add(buttonGrid[row][col]);
         }
      }
   }
   
   private static void createAndShowGui() {
      ButtonGridEg mainPanel = new ButtonGridEg();
      int val=1;
           for (int r = 0; r < buttonGrid.length; r++) {
                   for (int c = 0; c < buttonGrid[r].length; c++) {      
           buttonGrid[r][c].setText("");
                 
                 if(val==1){
                 buttonGrid[r][c].setBackground(Color.white);val*=-1;}
                 else{
                 buttonGrid[r][c].setBackground(Color.black);val*=-1;}
                 
                     
       }val*=-1;}
      JFrame frame = new JFrame();
      JButton Reset=new JButton("Reset");
      Reset.addActionListener(new ActionListener(){
       public void actionPerformed(ActionEvent e){  
        
           i=0;
           int val=1;
           for (int r = 0; r < buttonGrid.length; r++) {
                   for (int c = 0; c < buttonGrid[r].length; c++) {      
           buttonGrid[r][c].setText("");
                 
                 if(val==1){
                 buttonGrid[r][c].setBackground(Color.white);val*=-1;}
                 else{
                 buttonGrid[r][c].setBackground(Color.black);val*=-1;}
                 
                     
       }val*=-1;}
       
           res.clear();
       
           
       }
   });
      P1.add(mainPanel);
      P2.add(Reset);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(P1,BorderLayout.CENTER);
      frame.getContentPane().add(P2,BorderLayout.PAGE_END);
      frame.setTitle(APP_TITLE);
      frame.setResizable(false);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

        public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }
             
}