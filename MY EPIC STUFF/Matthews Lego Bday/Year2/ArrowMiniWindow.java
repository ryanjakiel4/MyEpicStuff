import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class ArrowMiniWindow extends JFrame
{
   public ArrowMiniWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Dimension windowSize = new Dimension(800,800);
      this.setSize(windowSize); 
   }
   public static void main(String[] args)
   {
      ArrowMiniWindow window = new ArrowMiniWindow("AM");
      
      window.setResizable(true);
      
      Container contentPane = window.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
      ArrowMini edit = new ArrowMini(window.getSize());
      contentPane.add(edit);
   
      window.setVisible(true);
   }
 }
