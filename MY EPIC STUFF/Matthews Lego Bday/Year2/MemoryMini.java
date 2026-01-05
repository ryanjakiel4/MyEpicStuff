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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MemoryMini extends Canvas implements KeyListener
{
   private ImageObserver observer;
   private int WINDOWWIDTH, WINDOWHEIGHT, realWW, realWH;
   public Thread thready;
   private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running = true;
   
   private Color[] colors = new Color[]{
   new Color(75,15,102), // dark purple
   new Color(150,30,202), // purple
   new Color(21,6,155), // dark blue
   new Color(61,40,247),// blue
   new Color (0,255,255),// cyan
   new Color(20,124,12), //dark green
   new Color(37,234,21), //green
   new Color(255,255,0), // yellow
   new Color(255, 128, 0), //orange
   new Color(164,0,0), // dark red
   new Color(255,0,0), //red
   new Color(255,0,128), // magenta
   new Color(127,127,127)}; // white
   
   private Image upArrow;
   private Image downArrow;
   private Image leftArrow;
   private Image rightArrow;
   private Image upArrowBlank;
   private Image downArrowBlank;
   private Image leftArrowBlank;
   private Image rightArrowBlank;
   private Image upArrowPressed;
   private Image downArrowPressed;
   private Image leftArrowPressed;
   private Image rightArrowPressed;

   private int generalCount = 0;
   private int xConstant, yConstant;
   private int timerBarHeight;
   
   public int arrows = 4;
   private int currentArrows = 0;
   private ArrayList<String> arrowTypes = new ArrayList<String>();
   private boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false;
   private boolean displayingArrows = true, typingArrows = false;
   private boolean gameover = false, gameStarted = false;
   private int oldLength = 1;
   private int timeRemaining = 100, totalTime = 100;
   private Color bufferColor = Color.BLUE;
   private boolean hasHeWon = false;
   public boolean isPlaying = true;
   public MemoryMini(Dimension size)
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
                        generalCount++;
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
      this.addComponentListener(new ResizeListener());
      addKeyListener(this);
        
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      realWW = WINDOWWIDTH;
      realWH = WINDOWHEIGHT;
      timerBarHeight = WINDOWHEIGHT/20;
      if(WINDOWHEIGHT < WINDOWWIDTH)
      {
         xConstant = (WINDOWWIDTH - WINDOWHEIGHT)/2;
         yConstant = 0;
         WINDOWWIDTH = WINDOWHEIGHT;
      }
      else
      {
         yConstant = (WINDOWHEIGHT - WINDOWWIDTH)/2;
         xConstant = 0;
         WINDOWHEIGHT = WINDOWWIDTH;
      }
      //yConstant += timerBarHeight;
      //instantiateImages();
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
      if(gameStarted)
      {
         if(displayingArrows)
         {
            if(generalCount%100 == 0)
            {
               if(currentArrows < arrows)
               {
                  double a = Math.random();
                  if(a < .25)
                     arrowTypes.add("up");
                  else if(a < .5)
                     arrowTypes.add("down");
                  else if(a < .75)
                     arrowTypes.add("left");
                  else
                     arrowTypes.add("right");
                  currentArrows++;
               }
               else
               {
                  displayingArrows = false;
                  typingArrows = true;
                  currentArrows = 0;
                  totalTime = (int)(arrows*100);
                  timeRemaining = (int)(arrows*100);
                  double z = Math.random();
                  if(z < .25)
                     bufferColor = Color.BLUE;
                  else if(z < .5)
                     bufferColor = Color.GREEN;
                  else if(z < .75)
                     bufferColor = Color.RED;
                  else
                     bufferColor = Color.YELLOW;
               }
            }
         }
         else if(typingArrows)
         {
            if(currentArrows < arrows)
            {
               timeRemaining--;
               if(upPressed)
               {
                  if(arrowTypes.get(currentArrows).equals("up"))
                  {
                     currentArrows++;
                  }
                  else
                  {
                     gameover = true;
                  }
                  upPressed = false;
               }
               else if(downPressed)
               {
                  if(arrowTypes.get(currentArrows).equals("down"))
                  {
                     currentArrows++;
                  }
                  else
                  {
                     gameover = true;
                  }
                  downPressed = false;
               }
               else if(leftPressed)
               {
                  if(arrowTypes.get(currentArrows).equals("left"))
                  {
                     currentArrows++;
                  }
                  else
                  {
                     gameover = true;
                  }
                  leftPressed = false;
               }
               else if(rightPressed)
               {
                  if(arrowTypes.get(currentArrows).equals("right"))
                  {
                     currentArrows++;
                  }
                  else
                  {
                     gameover = true;
                  }
                  rightPressed = false;
               }
            }
            else
            {
               displayingArrows = true;
               typingArrows = false;
               arrows+=2;
               currentArrows = 0;
            }
         }
      }
      // if(gameover)
//       {
//       if(arrows-2 >= 16)
//       {
//       hasHeWon = true;
//       }
//       }
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      
      bufferGraphics.setColor(Color.BLACK);
      bufferGraphics.fillRect(0,0,realWW, realWH);
      if(gameStarted && !gameover)
      {
         if(typingArrows)
         {
            if(1.0*timeRemaining/totalTime < .15)
               bufferGraphics.setColor(colors[(int)(Math.random()*colors.length)]);
            else
               bufferGraphics.setColor(bufferColor);
            if(timeRemaining == 0)
               gameover = true;
            else
            {
               bufferGraphics.fillRect(0,0,(int)(realWW*(1.0*timeRemaining/totalTime)),timerBarHeight);
            }
         }
         drawArrows(currentArrows);
      }
      else if(gameover)
      {
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.setFont(new Font("Sans Serif", Font.PLAIN, 30));
         bufferGraphics.drawString("Gameover", WINDOWWIDTH/8, WINDOWHEIGHT/8);
         if(arrows > 4)
            bufferGraphics.drawString("You memorized " + (arrows-2) + " arrows", WINDOWWIDTH/8, WINDOWHEIGHT/4);
         else
            bufferGraphics.drawString("You memorized no arrows", WINDOWWIDTH/8, WINDOWHEIGHT/4);
         bufferGraphics.drawString("Press s to play again, press q to quit at anytime.", WINDOWWIDTH/8, WINDOWHEIGHT*3/8);
      }
      else if(!gameover && !gameStarted)
      {
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.setFont(new Font("Sans Serif", Font.PLAIN, 30));
         bufferGraphics.drawString("Memorize the arrows", WINDOWWIDTH/4, WINDOWHEIGHT/8);
         bufferGraphics.drawString("Score 16 to win a prize!", WINDOWWIDTH/4, WINDOWHEIGHT/4);
         bufferGraphics.drawString("Press s to play, press q to quit at anytime.", WINDOWWIDTH/4, WINDOWHEIGHT*3/8);
      }
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
   }
   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public void drawArrows(int numArrows)
   {
      int[] rowsCols = getLengthAndOverflowForGrid(arrows);
      int length = rowsCols[0];
      int overflow = rowsCols[1];
      if(oldLength != length)
         instantiateImages(length);
      oldLength = length;
      for(int r = 0; r < numArrows/length + 1; r++)
      {
         for(int c = 0; c < length; c++)
         {
            if(numArrows > r*length + c)
            {
               String type = arrowTypes.get(r*length + c);
               if(type.equals("up"))   
                  bufferGraphics.drawImage(upArrow, c*(WINDOWWIDTH/length) + xConstant, r*(WINDOWHEIGHT/length) + yConstant, observer);
               else if(type.equals("down"))   
                  bufferGraphics.drawImage(downArrow, c*(WINDOWWIDTH/length) + xConstant, r*(WINDOWHEIGHT/length) + yConstant, observer);
               else if(type.equals("left"))   
                  bufferGraphics.drawImage(leftArrow, c*(WINDOWWIDTH/length) + xConstant, r*(WINDOWHEIGHT/length) + yConstant, observer);
               else if(type.equals("right"))   
                  bufferGraphics.drawImage(rightArrow, c*(WINDOWWIDTH/length) + xConstant, r*(WINDOWHEIGHT/length) + yConstant, observer);
            }
         }
      }
      
   }
   public int[] getLengthAndOverflowForGrid(int boxes)
   {
      int upperLimit = 500;
      for(int n = 1; n < upperLimit; n++)
         if(boxes <= n*n)
            return new int[]{n,n*n-boxes};
      return new int[]{1,0};
   }
   public boolean hasWon()
   {
   return hasHeWon;
   }
   public void instantiateImages(int scale)
   {
      try {
         if(WINDOWHEIGHT < WINDOWWIDTH)
         {
            upArrow = ImageIO.read(new File("arrowUp.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            downArrow = ImageIO.read(new File("arrowDown.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            leftArrow = ImageIO.read(new File("arrowLeft.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            rightArrow = ImageIO.read(new File("arrowRight.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            upArrowBlank = ImageIO.read(new File("arrowUpBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            downArrowBlank = ImageIO.read(new File("arrowDownBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            leftArrowBlank = ImageIO.read(new File("arrowLeftBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            rightArrowBlank = ImageIO.read(new File("arrowRightBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            upArrowPressed = ImageIO.read(new File("arrowUpPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            downArrowPressed = ImageIO.read(new File("arrowDownPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            leftArrowPressed = ImageIO.read(new File("arrowLeftPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            rightArrowPressed = ImageIO.read(new File("arrowRightPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
         }
         else
         {
            upArrow = ImageIO.read(new File("arrowUp.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            downArrow = ImageIO.read(new File("arrowDown.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            leftArrow = ImageIO.read(new File("arrowLeft.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            rightArrow = ImageIO.read(new File("arrowRight.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            upArrowBlank = ImageIO.read(new File("arrowUpBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            downArrowBlank = ImageIO.read(new File("arrowDownBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            leftArrowBlank = ImageIO.read(new File("arrowLeftBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            rightArrowBlank = ImageIO.read(new File("arrowRightBlank.png")).getScaledInstance(WINDOWHEIGHT/scale,WINDOWHEIGHT/scale,Image.SCALE_SMOOTH);
            upArrowPressed = ImageIO.read(new File("arrowUpPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            downArrowPressed = ImageIO.read(new File("arrowDownPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            leftArrowPressed = ImageIO.read(new File("arrowLeftPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
            rightArrowPressed = ImageIO.read(new File("arrowRightPressed.png")).getScaledInstance(WINDOWWIDTH/scale,WINDOWWIDTH/scale,Image.SCALE_SMOOTH);
         }
      } 
      catch (IOException e) {
      }
   }
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
   }
   public void keyReleased(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(keyCode == KeyEvent.VK_Q)
      {
      hasHeWon = true;
      }
      if(keyCode == KeyEvent.VK_S)
      {
         if(!gameStarted)
         {
            gameStarted = true;
         }
         if(gameover)
         {
            gameover = false;
            gameStarted = true;
            displayingArrows = true;
            typingArrows = false;
            currentArrows = 0;
            arrows = 4;
            arrowTypes = new ArrayList<String>();
            oldLength = 0;
            timeRemaining = 100;
            totalTime = 100;
         }
      }
      if(typingArrows)
      {
         if(keyCode == KeyEvent.VK_UP)
         {
            upPressed = true;
         }
         if(keyCode == KeyEvent.VK_DOWN)
         {
            downPressed = true;
         }
         if(keyCode == KeyEvent.VK_LEFT)
         {
            leftPressed = true;
         }
         if(keyCode == KeyEvent.VK_RIGHT)
         {
            rightPressed = true;
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
         //this.setPreferredSize(size);
         WINDOWWIDTH = size.width;
         WINDOWHEIGHT = size.height;
         realWW = WINDOWWIDTH;
         realWH = WINDOWHEIGHT;
         timerBarHeight = WINDOWHEIGHT/20;
         if(WINDOWHEIGHT < WINDOWWIDTH)
         {
            xConstant = (WINDOWWIDTH - WINDOWHEIGHT)/2;
            yConstant = 0;
            WINDOWWIDTH = WINDOWHEIGHT;
         }
         else
         {
            yConstant = (WINDOWHEIGHT - WINDOWWIDTH)/2;
            xConstant = 0;
            WINDOWHEIGHT = WINDOWWIDTH;
         }
         //yConstant += timerBarHeight;
         instantiateImages(oldLength);
      }
   }
}