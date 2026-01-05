import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class MemoryMiniWindow extends JFrame
{
   public MemoryMiniWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Dimension windowSize = new Dimension(800,800);
      this.setSize(windowSize); 
   }
   public static void main(String[] args)
   {
      MemoryMiniWindow window = new MemoryMiniWindow("MM");
      
      window.setResizable(true);
      
      Container contentPane = window.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
      MemoryMini edit = new MemoryMini(window.getSize());
      contentPane.add(edit);
   
      window.setVisible(true);
   }
 }
