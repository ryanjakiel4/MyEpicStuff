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
import javax.imageio.ImageIO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WordMini extends Canvas implements KeyListener
{
   private int WINDOWWIDTH, WINDOWHEIGHT;
   public Thread thready;
   private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running = true;
   private int firstLine = 70, secondLine = 180, secondSubLine = 235, thirdLine = 290, leftMargin = 20, FONTSIZE = 50;
   
   private int startTime;
   private int gameTimeAllowed;
   private int stringLength = 1;
   private int howMuchIsTyped;
   private boolean startGame = false, gameFinished = false;
   private boolean typedInputMatches = true;
   private String possibleChars = "1234567890qwertyuiopasdfghjklzxcvbnm";
   private String typedInput = "";
   private String goalString = "";
   public int stringsTyped = 0;
   private boolean hasHeWon = false;
   public boolean isPlaying = false;
   
   public WordMini(Dimension size)
   {
      this.thready = new Thread(
            new Runnable() {
               public void run()
               {
                  while(running)
                  {
                  if(isPlaying)
                  {
                     DoLogic();
                     Draw();
                     DrawBackBufferToScreen();
                     Thread.currentThread();
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
               }
            });
      this.setPreferredSize(size);
   
      addKeyListener(this);
      this.addComponentListener(new ResizeListener()); 
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      FONTSIZE = WINDOWHEIGHT/30;
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
      
      if(startGame)
      {
         if(typedInputMatches)
         {
            typedInputMatches = false;
            goalString = "";
            typedInput = "";
            for(int n = 0; n < stringLength; n++)
            {
               int temp = (int)(Math.random()*possibleChars.length());
               goalString += possibleChars.substring(temp,temp+1);
            }
            stringLength++;
            gameTimeAllowed+=(int)(stringLength/2);
            stringsTyped ++;
         }
         else
         {
            if(typedInput.equals(goalString))
               typedInputMatches = true;
            howMuchIsTyped = howMuchIsRight();
              
            if(gameTimeAllowed < ((int)(System.currentTimeMillis()/1000)) - startTime || howMuchIsTyped == -1)
            {
               gameFinished = true;
               startGame = false;
            }
         }  
      }
      // else if(gameFinished)
//       {
//          if(stringsTyped >= 18)
//          {
//             hasHeWon  = true;
//          }
//       }
      
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.setColor(Color.BLACK);
      bufferGraphics.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);   
      bufferGraphics.setColor(Color.WHITE);
      bufferGraphics.setFont(new Font("Sans Serif",Font.PLAIN,FONTSIZE));
      if(!startGame && !gameFinished)
      {
         bufferGraphics.drawString("Press the letters displayed as fast as you can!", leftMargin, firstLine);
         bufferGraphics.drawString("Score high enough to win a prize!", leftMargin, secondLine);
         bufferGraphics.drawString("Press S to start game, Press Q to quit",leftMargin,thirdLine);
      }
      else if(startGame)
      {
         bufferGraphics.drawString("Timer: " + (gameTimeAllowed - ((int)(System.currentTimeMillis()/1000)-startTime)), leftMargin, firstLine);
         bufferGraphics.drawString(goalString, leftMargin,secondLine);
         bufferGraphics.setColor(Color.GREEN);
         bufferGraphics.drawString(typedInput, leftMargin,secondLine);
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.drawString("Strings Typed: "+stringsTyped, leftMargin, thirdLine);
      }
      else if(gameFinished)
      {
         bufferGraphics.drawString("Gameover. Press S to replay, Press Q to quit", leftMargin, firstLine);
         bufferGraphics.drawString(goalString,leftMargin,secondLine);
         if(!typedInput.equals(""))
         {
            bufferGraphics.setColor(Color.RED);
            bufferGraphics.drawString(typedInput.substring(0,typedInput.length()),leftMargin,secondSubLine);
            bufferGraphics.setColor(Color.GREEN);
            bufferGraphics.drawString(typedInput.substring(0,typedInput.length()-1),leftMargin,secondSubLine);
         }
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.drawString("Strings Typed: "+stringsTyped, leftMargin, thirdLine);
      }
      
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
   }
   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   
   public int howMuchIsRight()
   {
      if(typedInput.equals(""))
         return 0;
      for(int n = 0; n < typedInput.length(); n++)
      {
         if(!typedInput.substring(n,n+1).equals(goalString.substring(n,n+1)))
            return -1;
      }
      return typedInput.length()-1;
   }
   public boolean hasWon()
   {
      return hasHeWon;
   }
   public void keyPressed(KeyEvent e)
   {
      
   }
   public void keyReleased(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      String letter = Character.toString((char)keyCode).toLowerCase();
      if(letter.equals("q") && !startGame)
      {
      hasHeWon = true;
      }
      if(!startGame && !gameFinished)
      {
         if(letter.equals("s"))
         {
            startTime = (int)(System.currentTimeMillis()/1000);
            gameTimeAllowed = 10;
            stringsTyped = 0;
            stringLength = 1;
            startGame = true;
         }
      }
      else if(startGame)
      {
         if(possibleChars.indexOf(letter) != -1)
            typedInput += letter;
      }
      else if(gameFinished)
      {
         if(letter.equals("s"))
         {
            gameFinished = false;
            typedInputMatches = true;
            
         }
            
      }
   }
   public void keyTyped(KeyEvent e)
   {
   }
   class ResizeListener implements ComponentListener
   {
      public void componentHidden(ComponentEvent e){}
      public void componentMoved(ComponentEvent e){}
      public void componentShown(ComponentEvent e){}
      public void componentResized(ComponentEvent e)
      {
         Dimension size = e.getComponent().getBounds().getSize();
      
         WINDOWWIDTH = size.width;
         WINDOWHEIGHT = size.height;
         if(stringsTyped != 0 && WINDOWHEIGHT/20 > WINDOWWIDTH/stringsTyped)
            FONTSIZE = WINDOWWIDTH/stringsTyped;
         else
            FONTSIZE = WINDOWHEIGHT/30;
      }
   }
}