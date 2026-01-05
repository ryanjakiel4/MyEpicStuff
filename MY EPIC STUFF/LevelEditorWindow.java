import java.awt.*;
   import javax.swing.*;
   import javax.swing.JFrame;
   public class LevelEditorWindow extends JFrame
   {
      public LevelEditorWindow(String name)
      {
         super(name);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         Dimension windowSize = new Dimension(1850,1080);
         this.setSize(windowSize);
      }
      public static void main(String[] args)
      {
         LevelEditorWindow window = new LevelEditorWindow("CrazyNess");
         Container contentPane = window.getContentPane();
         contentPane.setLayout(new GridLayout(1,1));
      
         LevelEditor edit = new LevelEditor(window.getSize());
         contentPane.add(edit);
      
         window.setVisible(true);
      }
   }