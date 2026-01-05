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

public class NewGame extends Canvas
implements KeyListener, Runnable
{
   private boolean startGame = false;
   private int WINDOWHEIGHT = 512, WINDOWWIDTH = 512;
   private ArrayList<Integer> keys = new ArrayList<Integer>();
   private static Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running;
   private Thread thread;
   private int level = 1; // Level 1 height = 1772, Level 2 height = 2348
   private int[] levelHeights = new int[]{1772, 2348}; 
   private int backgroundImagePlace= 0;
   private double changingPicCount = 1, changingPicStep = .25;
   private String direction = "e";
   private int heroX = 20, heroY = 200;
   private int oldHeroY;
   private int heroSpeed = 3;
   private ImageObserver observer;
   private boolean heroCanJump = true;
   private boolean heroJump = false;
   private int jumpCount = 0;
   private int[] heroJumpList = new int[]{-8,-8,-8,-8,-8,-7,-7,-7,-7,-7,-6,-6,-6,-6,-6,-5,-5,-5,-5,-5,-4,-4,-4,-4,-4,-3,-3,-3,-3,-3,-2,-2,-2,-2,-2,-1,-1,-1,-1,-1};
   private double gravity = 0;
   private double gravityStep = .2;
   private boolean onSurface = false;
   private ArrayList<int[]> surfaces = new ArrayList<int[]>(); // int[] is [?,?,?] where the first is the Y of the platform, then the next two are the left and right X endpoints
   private int platformSeparation = 70;
   private int minimumPlatformWidth = 100;
   private int platformThickness = 10;
   private int platformSpeed = 1;
   private BufferedImage[] heroImageRight = new BufferedImage[10];
   private BufferedImage[] heroImageLeft = new BufferedImage[10];
   private BufferedImage[] background = new BufferedImage[2];
   public NewGame(Dimension size)
   {
      this.setPreferredSize(size);
      addKeyListener(this);
      instantiateImages();
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
      instantiateNewPlatforms();
      if(!startGame)
      {
         heroY = 430; // hero on ground
      }
      
      movePlatforms();
      
      if(startGame)
      {
         backgroundImagePlace += platformSpeed;
         oldHeroY = heroY;
         if(heroJump)
         {
            onSurface = false;
            if(jumpCount < heroJumpList.length)
            {
               heroY += heroJumpList[jumpCount];
               jumpCount++;
            }
            else
            {
               heroJump = false;
               jumpCount = 0;
            }
         }
         if(!onSurface)
         {
            heroY += (int)gravity;
            gravity += gravityStep;
            onSurface = isHeroOnSurface();
         }
         if(onSurface)
         {
            gravity = 0;
         }
         
      }
      if(keys.contains(new Integer(KeyEvent.VK_LEFT)))
      {
         heroX -= heroSpeed;
         direction = "w";
         changingPicCount+=changingPicStep;
      }
      else if(keys.contains(new Integer(KeyEvent.VK_RIGHT)))
      {
         heroX += heroSpeed;
         direction = "e";
         changingPicCount+=changingPicStep;
      }
      else
      {
         changingPicCount = 10;
      }
      
      
      
   
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      
      try
      {
         bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
         try {
            background = ImageIO.read(new File("Level" + level + ".jpg"));
         } 
         catch (IOException e) {
         }
         if(direction == "e")
         {
            try {
               heroImage = ImageIO.read(new File("moveRight" + (int)(changingPicCount)%11 + ".jpg"));
            } 
            catch (IOException e) {
            }
         }
         else if(direction == "w")
         {
            try {
               heroImage = ImageIO.read(new File("moveLeft" + (int)(changingPicCount)%11 + ".jpg"));
            } 
            catch (IOException e) {
            } 
         }
         bufferGraphics.drawImage(background, 0, (background.getHeight() - backgroundImagePlace)*-1, observer);
         bufferGraphics.drawImage(heroImage, heroX, heroY, observer);
         drawPlatforms(bufferGraphics);
         Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         bufferGraphics.dispose();
      }
   
   }
   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public void instantiateNewPlatforms()
   {
      for(int n = 0; n < surfaces.size(); n++)
      {
         if(surfaces.get(n)[0] > 500)
         {
            surfaces.remove(n);
         }
      }
      while(surfaces.size() < WINDOWHEIGHT/platformSeparation)
      {
         if(surfaces.size() == 0)
         {
            int leftEnd = (int)(Math.random()*(WINDOWWIDTH - minimumPlatformWidth));
            int rightEnd =  leftEnd + minimumPlatformWidth + (int)(Math.random()*(WINDOWWIDTH - (leftEnd + minimumPlatformWidth)));
            
            surfaces.add(new int[]{0,leftEnd,rightEnd});
         }
         else
         {
            int previousPlatformHeight = surfaces.get(surfaces.size() - 1)[0];
            int platformY = previousPlatformHeight - platformSeparation;
            int leftEnd = (int)(Math.random()*(WINDOWWIDTH - minimumPlatformWidth));
            int rightEnd =  leftEnd + minimumPlatformWidth + (int)(Math.random()*(WINDOWWIDTH - (leftEnd + minimumPlatformWidth)));
            
            surfaces.add(new int[]{platformY, leftEnd, rightEnd});
         }
      }
   }
   public void drawPlatforms(Graphics g)
   {
      g.setColor(Color.BLACK);
      for(int n = 0; n < surfaces.size(); n++)
      {
         int platformX = surfaces.get(n)[1];
         int platformY = surfaces.get(n)[0];
         int platformLength = surfaces.get(n)[2] - platformX;
         g.fillRect(platformX, platformY, platformLength, platformThickness);
      }
   }
   public void movePlatforms()
   {
      for(int n = 0; n < surfaces.size(); n++)
      {
         int platformY = surfaces.get(n)[0];
         int platformX = surfaces.get(n)[1];
         int platformX2 = surfaces.get(n)[2];
         surfaces.set(n,new int[]{platformY+platformSpeed, platformX, platformX2});
      }
   }
   public boolean isHeroOnSurface()
   {
      int actualHeroX = heroX + 10; //half the length of the pic
      int actualHeroY = heroY + 20; //the length of the pic
      int actualOldHeroY = oldHeroY + 20;
      for(int n = 0; n < surfaces.size(); n++)
      {
         int platformY = surfaces.get(n)[0];
         int platformX = surfaces.get(n)[1];
         int platformX2 = surfaces.get(n)[2];
         if(actualHeroX > platformX && actualHeroX < platformX2)
         {
            if(actualHeroY >= platformY && actualOldHeroY <= platformY)
            {
               System.out.println("3");
               heroY = platformY-20; //height of hero
               return true;
            }
         }
      }
      return false;
   }
   public void instantiateImages()
   {
      try {
         heroImageLeft[1] = ImageIO.read(new File("moveLeft1.jpg"));
         heroImageLeft[2] = ImageIO.read(new File("moveLeft2.jpg"));
         heroImageLeft[3] = ImageIO.read(new File("moveLeft3.jpg"));
         heroImageLeft[4] = ImageIO.read(new File("moveLeft4.jpg"));
         heroImageLeft[5] = ImageIO.read(new File("moveLeft5.jpg"));
         heroImageLeft[6] = ImageIO.read(new File("moveLeft6.jpg"));
         heroImageLeft[7] = ImageIO.read(new File("moveLeft7.jpg"));
         heroImageLeft[8] = ImageIO.read(new File("moveLeft8.jpg"));
         heroImageLeft[9] = ImageIO.read(new File("moveLeft9.jpg"));
         heroImageLeft[0] = ImageIO.read(new File("moveLeft0.jpg"));
         heroImageRight[1] = ImageIO.read(new File("moveRight1.jpg"));
         heroImageRight[2] = ImageIO.read(new File("moveRight2.jpg"));
         heroImageRight[3] = ImageIO.read(new File("moveRight3.jpg"));
         heroImageRight[4] = ImageIO.read(new File("moveRight4.jpg"));
         heroImageRight[5] = ImageIO.read(new File("moveRight5.jpg"));
         heroImageRight[6] = ImageIO.read(new File("moveRight6.jpg"));
         heroImageRight[7] = ImageIO.read(new File("moveRight7.jpg"));
         heroImageRight[8] = ImageIO.read(new File("moveRight8.jpg"));
         heroImageRight[9] = ImageIO.read(new File("moveRight9.jpg"));
         heroImageRight[0] = ImageIO.read(new File("moveRight0.jpg"));
         background[0] = ImageIO.read(new File("Level1.jpg"));
         background[1] = ImageIO.read(new File("Level2.jpg"));
               
      } 
      catch (IOException e) {
      }
   }
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(keyCode == KeyEvent.VK_UP)
      {
         if(heroCanJump)
         {
            heroJump = true;
            onSurface = false;
            heroCanJump = false;
            startGame = true;
         }
      }
      else if(!keys.contains(keyCode))
         keys.add(new Integer(keyCode));
   }
   public void keyReleased(KeyEvent e)
   {
      keys.remove(new Integer(e.getKeyCode()));
      if(e.getKeyCode() == KeyEvent.VK_UP)
      {
         heroJump = false;
         jumpCount = 0;
         heroCanJump = true;
      }
   }
   public void keyTyped(KeyEvent e)
   {
   }
}