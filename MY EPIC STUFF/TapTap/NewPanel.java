import java.awt.*;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;  
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.applet.Applet;
import java.awt.geom.*;
import java.io.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

public class NewPanel extends Canvas
{
private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running;
   public Thread thready;
   public NewPanel(Dimension size)
   {
      this.thready = new Thread(
            new Runnable() {
               public void run()
               {
                  while(running)
                  {
                     DoLogic();
                     Draw();
                     DrawBackBufferToScreen();
                     try
                     {
                        thready.sleep(10);
                     }
                     catch(Exception e)
                     {
                        e.printStackTrace();
                     }
                  }
               }
            });
      this.setPreferredSize(size);
      running =true;
   }

   public void paint(Graphics g)
   {
      if(bufferStrategy == null)
      {
         this.createBufferStrategy(2);
         bufferStrategy = this.getBufferStrategy();
         bufferGraphics = bufferStrategy.getDrawGraphics();
         thready.start();
         
      }
   }
   public void DoLogic()
   {
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      
      
      
      
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      
      
      
      
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      
   }

   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
}