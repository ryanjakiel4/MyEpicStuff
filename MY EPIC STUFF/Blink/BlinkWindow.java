import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class BlinkWindow extends JFrame
{
   public BlinkWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Dimension windowSize = new Dimension(99,176);
      Dimension windowSize = new Dimension(750,1250);
      //Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.setSize(windowSize); 
   }
   public static void main(String[] args)
   {
      BlinkWindow window = new BlinkWindow("Blink");
      
      window.setResizable(false);
      
      Container contentPane = window.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
      Blink edit = new Blink(window.getSize());
      contentPane.add(edit);
   
      window.setVisible(true);
   }
 }
