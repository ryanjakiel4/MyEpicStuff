import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class BlinkWindow extends JFrame
{
   public BlinkWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Dimension windowSize = new Dimension(99+3,176+27); // add 10 to the y component to make up for toolbar size
      
      Dimension windowSize = new Dimension(1500,1000);
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
