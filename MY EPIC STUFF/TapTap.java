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

public class TapTap extends Canvas
implements KeyListener, Runnable
{
   private int WINDOWWIDTH, WINDOWHEIGHT;
   private ArrayList<Integer> keys = new ArrayList<Integer>();
   private static Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running;
   private Thread thread;
   private String songName = "";
   private boolean choosingSong = true, loadingSong = false, editingSong = false, playingSong = false;
   private boolean upperCase = false;
	
   public TapTap(Dimension size)
   {
      this.setPreferredSize(size);
      addKeyListener(this);
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      this.thread = new Thread(this);
      running =true;
   }

   public void paint(Graphics g)
   {
      if(bufferStrategy == null)
      {
         this.createBufferStrategy(2);
         bufferStrategy = this.getBufferStrategy();
         bufferGraphics = bufferStrategy.getDrawGraphics();
         this.thread.start();
      }
   }
   public void run()
   {
      while(running)
      {
         DoLogic();
         Draw();
         DrawBackBufferToScreen();
         
         Thread.currentThread();
         try
         {
            Thread.sleep(10);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
   }
   public void DoLogic()
   {
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      if(choosingSong)
      {
      }
      else if(loadingSong)
      {
      Music song = new Music(songName);
      }
      else if(editingSong)
      {
      }
      else if(playingSong)
      {
      }
         
         
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
      if(choosingSong)
      {
         bufferGraphics.setColor(Color.BLACK);
         bufferGraphics.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.setFont(new Font("SansSeriff",Font.PLAIN,30));
         bufferGraphics.drawString("Song File Name:",0,WINDOWHEIGHT/2-30);
         bufferGraphics.drawString(songName, 0, WINDOWHEIGHT/2);
      }
            
            
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      
   }

   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(choosingSong)
      {
         if(keyCode == KeyEvent.VK_SHIFT)
         {
            upperCase = true;
         }
         if(keyCode == KeyEvent.VK_ENTER)
         {
         choosingSong = false;
         loadingSong = true;
         }
         char c = (char)keyCode;
         if(upperCase)
         {
            songName += Character.toString(c);
         }
         else
         {
            songName += Character.toString(c).toLowerCase();
         }
         if(keyCode == KeyEvent.VK_BACK_SPACE && songName.length() >= 2)
         {
         songName = songName.substring(0,songName.length()-2);
         }   
      }
      // else
      // {
         // if(!keys.contains(keyCode))
            // keys.add(new Integer(keyCode));
      // }
   }
   public void keyReleased(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(keyCode == KeyEvent.VK_SHIFT)
      {
         upperCase = false;
      }
      //keys.remove(new Integer(e.getKeyCode()));
   }
   public void keyTyped(KeyEvent e)
   {
   }
}