import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class AwesomeWindow extends JFrame
{
   public AwesomeWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Dimension windowSize = new Dimension(1000,500);
      Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.setSize(windowSize); 
   }
   public static void main(String[] args)
   {
      AwesomeWindow window = new AwesomeWindow("BM Tron... Sort of");
      
      window.setResizable(false);
      
      Container contentPane = window.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
      AwesomeBetter edit = new AwesomeBetter(window.getSize());
      contentPane.add(edit);
   
      window.setVisible(true);
   }
 }