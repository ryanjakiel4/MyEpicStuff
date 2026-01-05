//http://stackoverflow.com/questions/12548603/playing-audio-using-javafx-mediaplayer-in-a-normal-java-application
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class WordMiniWindow extends JFrame
{
   public WordMiniWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Dimension windowSize = new Dimension(1800,500);
      //Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.setSize(windowSize);
   }
   public static void main(String[] args)
   {
      WordMiniWindow window = new WordMiniWindow("WM");
      
      window.setResizable(true);
      
      Container contentPane = window.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
      WordMini edit = new WordMini(window.getSize());
      contentPane.add(edit);
   
      window.setVisible(true);
   }
 }