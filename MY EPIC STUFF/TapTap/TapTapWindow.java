//http://stackoverflow.com/questions/12548603/playing-audio-using-javafx-mediaplayer-in-a-normal-java-application
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class TapTapWindow extends JFrame
{
   public TapTapWindow(String name)
   {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Dimension windowSize = new Dimension(450,800);
      Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.setSize(windowSize);
   }
   public static void main(String[] args)
   {
      TapTapWindow window = new TapTapWindow("Tap Studio for PC");
      
      window.setResizable(false);
      
      Container contentPane = window.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
      TapTap edit = new TapTap(window.getSize());
      contentPane.add(edit);
   
      window.setVisible(true);
   }
 }