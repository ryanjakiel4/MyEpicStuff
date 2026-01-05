import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
public class AwesomeWebWindow extends JApplet
{
   public void init()
   {
      Container contentPane = this.getContentPane();
      
      contentPane.setLayout(new GridLayout(1,1));
      AwesomeWeb edit = new AwesomeWeb(new Dimension(1600, 900));
      contentPane.add(edit);
   }
}