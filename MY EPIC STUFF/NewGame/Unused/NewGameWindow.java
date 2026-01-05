import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class NewGameWindow extends JFrame
{
   public NewGameWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Dimension windowSize = new Dimension(530,559);
      this.setSize(windowSize);
   }
   public static void main(String[] args)
   {
      NewGameWindow window = new NewGameWindow("Yeah. Go Ahead. Jump. I Dare You.");
      Container contentPane = window.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
   
      NewGameEndless3 edit = new NewGameEndless3(window.getSize());
      contentPane.add(edit);
   
      window.setVisible(true);
   }
 }
 //awkwardly makes background image place -360 at the eginning...