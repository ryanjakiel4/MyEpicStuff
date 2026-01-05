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

public class ArrowMini extends Canvas implements KeyListener
{
   private ImageObserver observer;
   private int WINDOWWIDTH, WINDOWHEIGHT, realWW, realWH;
   public Thread thready;
   private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running = true;
   
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
   
   private int scale = 10;
   private int xConstant, yConstant;
   
   private int upArrowXLocation, downArrowXLocation, leftArrowXLocation, rightArrowXLocation;
   private int upArrowYLocation, downArrowYLocation, leftArrowYLocation, rightArrowYLocation;
   
   private ArrayList<Arrow> upArrows = new ArrayList<Arrow>();
   private ArrayList<Arrow> downArrows = new ArrayList<Arrow>();
   private ArrayList<Arrow> leftArrows = new ArrayList<Arrow>();
   private ArrayList<Arrow> rightArrows = new ArrayList<Arrow>();
   
   private int notes = 100;
   private int generalCount = 0;
   private int speed;
   private int framesTillPress = 100;
   private int noteSpawnSpeed = 100;
   private int sensitivity;
   public int score = 0;
   
   private boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false;
   private boolean firstGame = true, gameover = false, gameStarted = false;
   
   private boolean hasHeWon = false;
   public boolean isPlaying = false;
   public ArrowMini(Dimension size)
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
      
      if(WINDOWHEIGHT < WINDOWWIDTH)
      {
         xConstant = (WINDOWWIDTH - WINDOWHEIGHT)/2;
         yConstant = 0;
         realWW = WINDOWWIDTH;
         realWH = WINDOWHEIGHT;
         WINDOWWIDTH = WINDOWHEIGHT;
      }
      if(WINDOWWIDTH < WINDOWHEIGHT)
      {
         yConstant = (WINDOWHEIGHT - WINDOWWIDTH)/2;
         xConstant = 0;
         realWW = WINDOWWIDTH;
         realWH = WINDOWHEIGHT;
         WINDOWHEIGHT = WINDOWWIDTH;
      }
         
      instantiateImages();
      
      upArrowXLocation = WINDOWWIDTH/2 - WINDOWWIDTH/(2*scale) + xConstant;
      upArrowYLocation = WINDOWWIDTH/2 - WINDOWWIDTH/scale - WINDOWWIDTH/(2*scale) + yConstant;
      
      downArrowXLocation = WINDOWWIDTH/2 - WINDOWWIDTH/20 + xConstant;
      downArrowYLocation = WINDOWWIDTH/2 + WINDOWWIDTH/20 + yConstant;
      
      leftArrowXLocation = WINDOWWIDTH/2 - WINDOWWIDTH/scale - WINDOWWIDTH/(2*scale) + xConstant;
      leftArrowYLocation = WINDOWWIDTH/2 - WINDOWWIDTH/(2*scale) + yConstant;
      
      rightArrowXLocation = WINDOWWIDTH/2 + WINDOWWIDTH/(2*scale) + xConstant;
      rightArrowYLocation = WINDOWWIDTH/2 - WINDOWWIDTH/(2*scale) + yConstant;
     
      speed = (WINDOWWIDTH/2 - WINDOWWIDTH/scale)/framesTillPress;
      sensitivity = WINDOWWIDTH/30;
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
         if(notes > 0)
         {
            if(generalCount%noteSpawnSpeed == 0)
            {
               double a  = Math.random();
               if(a < .25)
               {
                  upArrows.add(new Arrow("up",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale));
               }
               else if(a<.5)
               {
                  downArrows.add(new Arrow("down",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale));
               }
               else if(a<.75)
               {
                  leftArrows.add(new Arrow("left",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale));
               }
               else
               {
                  rightArrows.add(new Arrow("right",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale));
               }
               notes--;
               if(notes%10 == 0)
               {
                  framesTillPress -= 4;
                  speed = (WINDOWWIDTH/2 - WINDOWWIDTH/scale)/framesTillPress;
                  noteSpawnSpeed -= 7;
               }
            }
         }
         else if(upArrows.size() == 0 && downArrows.size() == 0 && leftArrows.size() == 0 && rightArrows.size() == 0)
         {
            gameover = true;
            gameStarted = false;
         }
         for(int n = 0; n < upArrows.size(); n++)
         {
            if(upArrows.get(n).increment())
            {
               upArrows.remove(n);
               score--;
            //if(upPressed)
            //score++;
            }
         }
         for(int n = 0; n < downArrows.size(); n++)
         {
            if(downArrows.get(n).increment())
            {
               downArrows.remove(n);
               score--;
            //if(downPressed)
            //score++;
            }
         }
         for(int n = 0; n < leftArrows.size(); n++)
         {
            if(leftArrows.get(n).increment())
            {
               leftArrows.remove(n);
               score--;
            //if(leftPressed)
            //score++;
            }
         }
         for(int n = 0; n < rightArrows.size(); n++)
         {
            if(rightArrows.get(n).increment())
            {
               rightArrows.remove(n);
               score--;
            //if(rightPressed)
            //score++;
            }
         }
      }
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      
      bufferGraphics.setColor(Color.BLACK);
      bufferGraphics.fillRect(0,0,realWW,realWH);
      
      for(int n = 0; n < upArrows.size(); n++)
      {
         bufferGraphics.drawImage(upArrowBlank,upArrows.get(n).getX(),upArrows.get(n).getY(),observer);
      }
      for(int n = 0; n < downArrows.size(); n++)
      {
         bufferGraphics.drawImage(downArrowBlank,downArrows.get(n).getX(),downArrows.get(n).getY(),observer);
      }
      for(int n = 0; n < leftArrows.size(); n++)
      {
         bufferGraphics.drawImage(leftArrowBlank,leftArrows.get(n).getX(),leftArrows.get(n).getY(),observer);
      }
      for(int n = 0; n < rightArrows.size(); n++)
      {
         bufferGraphics.drawImage(rightArrowBlank,rightArrows.get(n).getX(),rightArrows.get(n).getY(),observer);
      }
   
      if(upPressed)
         bufferGraphics.drawImage(upArrowPressed, upArrowXLocation, upArrowYLocation, observer);
      else
         bufferGraphics.drawImage(upArrow, upArrowXLocation, upArrowYLocation, observer);
      if(downPressed)
         bufferGraphics.drawImage(downArrowPressed, downArrowXLocation, downArrowYLocation, observer);
      else
         bufferGraphics.drawImage(downArrow, downArrowXLocation, downArrowYLocation, observer);
      if(leftPressed)
         bufferGraphics.drawImage(leftArrowPressed, leftArrowXLocation, leftArrowYLocation, observer);
      else
         bufferGraphics.drawImage(leftArrow, leftArrowXLocation, leftArrowYLocation, observer);
      if(rightPressed)
         bufferGraphics.drawImage(rightArrowPressed, rightArrowXLocation, rightArrowYLocation, observer);
      else
         bufferGraphics.drawImage(rightArrow, rightArrowXLocation, rightArrowYLocation, observer);
         
      bufferGraphics.setColor(Color.WHITE);
      bufferGraphics.setFont(new Font("Sans Serif", Font.PLAIN, realWH/30));
      bufferGraphics.drawString(""+score, WINDOWWIDTH/2 + xConstant - WINDOWWIDTH/60, WINDOWHEIGHT/2 + yConstant);
      if(gameover)
      {
         bufferGraphics.drawString("Gameover", WINDOWWIDTH/4, WINDOWHEIGHT/8);
         bufferGraphics.drawString("Press s to play again, Press q to quit", WINDOWWIDTH/4, WINDOWHEIGHT/4);
      }
      if(!gameover && !gameStarted)
      {
         bufferGraphics.drawString("Score high enough to win a prize!", WINDOWWIDTH/4, WINDOWHEIGHT/8);
         bufferGraphics.drawString("Press s to play, Press q to quit", WINDOWWIDTH/4, WINDOWHEIGHT/4);
      }
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
   }
   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public void checkUpKeys()
   {
      boolean hitOne = false;
      for(int n = 0; n < upArrows.size(); n++)
         if(Math.abs(upArrowYLocation - upArrows.get(n).getY()) < sensitivity)
         {
            score++; 
            hitOne = true;
            upArrows.remove(n);
         }
      if(!hitOne)
         score--;
   }
   public void checkDownKeys()
   {
      boolean hitOne = false;
      for(int n = 0; n < downArrows.size(); n++)
         if(Math.abs(downArrowYLocation - downArrows.get(n).getY()) < sensitivity)
         {
            score++; 
            hitOne = true;
            downArrows.remove(n);
         }
      if(!hitOne)
         score--;
   }
   public void checkLeftKeys()
   {
      boolean hitOne = false;
      for(int n = 0; n < leftArrows.size(); n++)
         if(Math.abs(leftArrowXLocation - leftArrows.get(n).getX()) < sensitivity)
         {
            score++; 
            hitOne = true;
            leftArrows.remove(n);
         }
      if(!hitOne)
         score--;
   }
   public void checkRightKeys()
   {
      boolean hitOne = false;
      for(int n = 0; n < rightArrows.size(); n++)
         if(Math.abs(rightArrowXLocation - rightArrows.get(n).getX()) < sensitivity)
         {
            score++; 
            hitOne = true;
            rightArrows.remove(n);
         }
      if(!hitOne)
         score--;
   }
   public boolean hasWon()
   {
      return hasHeWon;
   }
   public void instantiateImages()
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
         if(WINDOWWIDTH <= WINDOWHEIGHT)
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
      if(keyCode == KeyEvent.VK_Q)
      {
         hasHeWon = true;
      }
      if(keyCode == KeyEvent.VK_UP)
      {
         upPressed = true;
         checkUpKeys();
      }
      if(keyCode == KeyEvent.VK_DOWN)
      {
         downPressed = true;
         checkDownKeys();
      }
      if(keyCode == KeyEvent.VK_LEFT)
      {
         leftPressed = true;
         checkLeftKeys();
      }
      if(keyCode == KeyEvent.VK_RIGHT)
      {
         rightPressed = true;
         checkRightKeys();
      }
   }
   public void keyReleased(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(keyCode == KeyEvent.VK_S)
         if(firstGame || (!gameStarted && gameover))
         {
            gameStarted = true;
            gameover = false;
            firstGame = false;
            framesTillPress = 100;
            notes = 100;
            noteSpawnSpeed = 100;
            score = 0;
            speed = (WINDOWWIDTH/2 - WINDOWWIDTH/scale)/framesTillPress;
         }
      if(keyCode == KeyEvent.VK_UP)
      {
         upPressed = false;
      }
      if(keyCode == KeyEvent.VK_DOWN)
      {
         downPressed = false;
      }
      if(keyCode == KeyEvent.VK_LEFT)
      {
         leftPressed = false;
      }
      if(keyCode == KeyEvent.VK_RIGHT)
      {
         rightPressed = false;
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
         //scaleHorizontal = WINDOWWIDTH*1.0/224;
         //scaleVertical = WINDOWHEIGHT*1.0/126;
         if(WINDOWHEIGHT < WINDOWWIDTH)
         {
            xConstant = (WINDOWWIDTH - WINDOWHEIGHT)/2;
            yConstant = 0;
            realWW = WINDOWWIDTH;
            realWH = WINDOWHEIGHT;
            WINDOWWIDTH = WINDOWHEIGHT;
         }
         if(WINDOWWIDTH < WINDOWHEIGHT)
         {
            yConstant = (WINDOWHEIGHT - WINDOWWIDTH)/2;
            xConstant = 0;
            realWW = WINDOWWIDTH;
            realWH = WINDOWHEIGHT;
            WINDOWHEIGHT = WINDOWWIDTH;
         }
         
         instantiateImages();
      
         upArrowXLocation = WINDOWWIDTH/2 - WINDOWWIDTH/20 + xConstant;
         upArrowYLocation = WINDOWWIDTH/2 - WINDOWWIDTH/10 - WINDOWWIDTH/20 + yConstant;
      
         downArrowXLocation = WINDOWWIDTH/2 - WINDOWWIDTH/20 + xConstant;
         downArrowYLocation = WINDOWWIDTH/2 + WINDOWWIDTH/20 + yConstant;
      
         leftArrowXLocation = WINDOWWIDTH/2 - WINDOWWIDTH/10 - WINDOWWIDTH/20 + xConstant;
         leftArrowYLocation = WINDOWWIDTH/2 - WINDOWWIDTH/20 + yConstant;
      
         rightArrowXLocation = WINDOWWIDTH/2 + WINDOWWIDTH/20 + xConstant;
         rightArrowYLocation = WINDOWWIDTH/2 - WINDOWWIDTH/20 + yConstant;
      
         speed = (WINDOWWIDTH/2 - WINDOWWIDTH/scale)/framesTillPress;
         sensitivity = WINDOWWIDTH/30;
      
         for(int n = 0; n < upArrows.size(); n++)
         {
            upArrows.get(n).recalibrate("up",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale);
         }
         for(int n = 0; n < downArrows.size(); n++)
         {
            downArrows.get(n).recalibrate("down",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale);
         }
         for(int n = 0; n < leftArrows.size(); n++)
         {
            leftArrows.get(n).recalibrate("left",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale);
         }
         for(int n = 0; n < rightArrows.size(); n++)
         {
            rightArrows.get(n).recalibrate("right",speed,WINDOWWIDTH,WINDOWHEIGHT,xConstant,yConstant,scale);
         }
      }
   }
}