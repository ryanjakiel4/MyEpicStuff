import java.applet.*;
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

public class TapTapAudioClip extends Canvas
{
   private  int WINDOWWIDTH, WINDOWHEIGHT;
   private  ArrayList<Integer> keys = new ArrayList<Integer>();
   private  Graphics bufferGraphics = null;
   private  BufferStrategy bufferStrategy = null;
   private  boolean running;
   public  Thread thread;
   private  String songName = "";
   private  boolean choosingSong = true, loadingSong = false, editingSong = false, playingSong = false, startedSong;
   private  boolean choosingRails = false, choosingKeys = false;
   private  boolean upperCase = false;
   private boolean transition = true;
   private AudioClip sound;
   private  String workingDir = System.getProperty("user.dir");
   private  File directory = new File(workingDir);
   private  ArrayList<String> txtFiles = new ArrayList<String>();
   private  int txtDisplayPlace = 0;
   private  int FONTSIZE = 30;
   private  String numberOfRails = "";
   private  int rails;
   private  ArrayList<String> railKeys = new ArrayList<String>();
   private  int scorebarHeight = 150,keysbarHeight = 100,dividerWidth = 20;
   public TapTapAudioClip(Dimension size)
   {
      this.setPreferredSize(size);
      addKeyListener(this);
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      this.thread = new Thread(this);
      running =true;
      getTxtFiles();
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
         
         thread.currentThread();
         try
         {
            thread.sleep(10);
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      }
   }
   public  void DoLogic()
   {
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      if(choosingSong)
      {
      //add selection from list of txt files w/curser, and as you nagivate through with the arrow keys
      //changes pages when you reach the top and bottom and press enter to select
      //can select option of typing in a new file name if it does not exist
      }
      else if(loadingSong)
      {
         sound = getAudioClip(getDocumentBase(), getParameter("sound")); 
         if(txtFiles.contains(songName))
            playingSong = true;
         else
            editingSong = true;
         loadingSong = false;
         choosingRails = true;
      }
      else if(choosingRails || choosingKeys)
      {
         if(!choosingRails)
         {
            rails = Integer.parseInt(numberOfRails);
         }  
      }
      else if(transition)
      {
      AudioPlayer.player.start(as);
      transition = false;
      }
      else if(editingSong)
      {
      sound.play();
      }
      else if(playingSong)
      {
      sound.play();
      }
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public  void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
      bufferGraphics.setColor(Color.BLACK);
      bufferGraphics.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
      bufferGraphics.setColor(Color.WHITE);
      bufferGraphics.setFont(new Font("SansSeriff",Font.PLAIN,30));
      if(choosingSong)
      {
         bufferGraphics.drawString("Song File Name:",0,WINDOWHEIGHT/4-30);
         bufferGraphics.drawString(songName, 0, WINDOWHEIGHT/4);
         if(txtFiles.size() > txtDisplayPlace)
         {
            for(int n = 0; n < (3*WINDOWHEIGHT/4)/FONTSIZE; n++)
            {
               //bufferGraphics.drawString(txtFiles.get(n+txtDisplayPlace),WINDOWWIDTH/2,WINDOWHEIGHT/4 + FONTSIZE*n);
            }
         }
      }
      else if(!startedSong)
      {
         bufferGraphics.drawString("Number of Rails?  "+numberOfRails,0,WINDOWHEIGHT/8);
         if(choosingKeys)
         {
            bufferGraphics.drawString("Set Keys:",0,WINDOWHEIGHT/4);
            for(int n = rails; n > 0; n--)
            {
               bufferGraphics.drawString(""+(rails-n+1),WINDOWWIDTH-(n*(WINDOWWIDTH/rails))+ (WINDOWWIDTH/(2*rails)),WINDOWHEIGHT/4+FONTSIZE);
            }
            if(railKeys.size() > 0)
            {
               for(int n = 0; n < railKeys.size(); n++)
               {
                  bufferGraphics.drawString(railKeys.get(n),(n*(WINDOWWIDTH/rails))+ (WINDOWWIDTH/(2*rails)),WINDOWHEIGHT/4+FONTSIZE*2);
               }
            }
         }
      }
      else if(editingSong && startedSong)
      {
         bufferGraphics.fillRect(0,scorebarHeight, dividerWidth, WINDOWHEIGHT - keysbarHeight);
         for(int n = 0; n < rails; n++)
         {
            bufferGraphics.fillRect(n*(WINDOWWIDTH/rails) - dividerWidth,scorebarHeight, n*(WINDOWWIDTH/rails), WINDOWHEIGHT - keysbarHeight);
         }
      }
      else if(playingSong && startedSong)
      {
         bufferGraphics.fillRect(0,scorebarHeight, dividerWidth, WINDOWHEIGHT - keysbarHeight);
         for(int n = 0; n < rails; n++)
         {
            bufferGraphics.fillRect(n*(WINDOWWIDTH/rails) - dividerWidth,scorebarHeight, n*(WINDOWWIDTH/rails), WINDOWHEIGHT - keysbarHeight);
         }
      }
            
            
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      
   }

   public  void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public  void getTxtFiles()
   {
      for(File f : directory.listFiles())
      {
         if(f.isFile() && f.getName().endsWith(".txt"))
            txtFiles.add(f.getName());
      }
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
         else if(keyCode == KeyEvent.VK_ENTER)
         {
            choosingSong = false;
            loadingSong = true;
         }
         else if(keyCode == KeyEvent.VK_BACK_SPACE && songName.length() >= 1)
         {
            songName = songName.substring(0,songName.length()-1);
         }   
         // else if(keyCode == KeyEvent.VK_UP)
         // {
            // if(txtDisplayPlace > 0)
               // txtDisplayPlace -= (3*WINDOWHEIGHT/4)/FONTSIZE;
         // }
         // else if(keyCode == KeyEvent.VK_DOWN)
         // {
            // if(txtFiles.size() > txtDisplayPlace)
               // txtDisplayPlace += (3*WINDOWHEIGHT/4)/FONTSIZE;
         // }
         else
         {
            char c = (char)keyCode;
            if(upperCase)
            {
               songName += Character.toString(c);
            }
            else
            {
               songName += Character.toString(c).toLowerCase();
            }
         }
      }
      else if(!startedSong)
      {
         if(choosingRails)
         {
            if(keyCode == KeyEvent.VK_ENTER)
            {
               choosingRails = false;
               choosingKeys  = true;
               System.out.println("Rails Chosen.");
            }
            else if(keyCode == KeyEvent.VK_BACK_SPACE)
            {
               if(numberOfRails.length() <= 0)
               {
                  loadingSong = false;
                  choosingSong = true;
                  choosingRails = false;
               }
               else
               {
                  numberOfRails = numberOfRails.substring(0,numberOfRails.length()-1);
               }
            }
            else
            {
               String s = Character.toString((char)keyCode);
               numberOfRails += s;
            }
         }
         else if(choosingKeys)
         {
            if(keyCode == KeyEvent.VK_ENTER)
            {
               choosingKeys = false;
               startedSong = true;
               System.out.println("Keys Chosen.");
            }
            else if(keyCode == KeyEvent.VK_BACK_SPACE)
            {
               if(railKeys.size() <= 0)
               {
                  choosingKeys = false;
                  choosingRails = true;
               }
               else
               {
                  railKeys.remove(railKeys.size()-1);
               }
            }
            else
            {
               String s = Character.toString((char)keyCode);
               if(!railKeys.contains(s))
                  railKeys.add(s);
            }
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