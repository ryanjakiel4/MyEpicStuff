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
   private int transitionCount = 0;
   private double changingPicCount = 1, changingPicStep = .25;
   private String direction = "e";
   private int heroX = 20, heroY = 200;
   private int oldHeroY;
   private int heroSpeed = 3;
   private ImageObserver observer;
   private boolean heroCanJump = true;
   private boolean heroJump = false;
   private int jumpCount = 0;
   private int[] heroJumpList = new int[]{-9,-9,-9,-8,-8,-8,-8,-8,-7,-7,-7,-7,-7,-6,-6,-6,-6,-6,-5,-5,-5,-5,-5,-4,-4,-4,-4,-4,-4,-3,-3,-3,-3,-3,-3,-3};
   private double gravity = 0;
   private double gravityStep = .2;
   private boolean onSurface = false;
   private ArrayList<int[]> surfaces = new ArrayList<int[]>(); // int[] is [?,?,?,?] where the first is the Y of the platform, then the next two are the left and right X endpoints, and the last is whether the hero touched the platform or not
   private int platformSeparation = 70;
   private int minimumPlatformWidth = 100;
   private int platformThickness = 10;
   private int platformSpeed = 1;
   private boolean movingPlatforms = true;
   private BufferedImage[] heroImageRight = new BufferedImage[11];
   private BufferedImage[] heroImageLeft = new BufferedImage[11];
   private BufferedImage[] background = new BufferedImage[2];
   private BufferedImage[] swordLeft = new BufferedImage[8];
   private BufferedImage[] swordRight = new BufferedImage[8];
   private BufferedImage sword;
   private BufferedImage transitionBackground;
   private boolean gameover = false;
   private boolean levelComplete = false;
   private int score = 0;
   private int oldHeroOnSurfaceY = 600;
   private int displayTextCount = 0;
   private boolean transitionComplete = false;
   private String[][] levelMessage = new String[][]{{"Obtained Sword! Press Z to use", "it and slash any enemies that","you find in your way. They", "might even leave a little", "something behind!"},{"Obtained bow and arrows! Press","X to kill enemies from a distance!","Useful for all those projectile","shooting enemies."}};
   private boolean swingingSword = false;
   private int swingingSwordCount = 0;
   private int generalCount = 0;
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
      if(gameover)
      {
         if(backgroundImagePlace > 0)
         {
            backgroundImagePlace -= gravity;
            heroY = 430;
            gravity += gravityStep;
            platformSpeed = (int)(gravity*-1);
            movePlatforms();
         }
         else
         {
            backgroundImagePlace = 0;
            heroY = 430;   
         }
         
      }
      else if(levelComplete)
      {
         startGame = false;
         gameover = false;
         gravity = 0;
         surfaces = new ArrayList<int[]>();
         movingPlatforms = true;
         heroCanJump = true;
         heroJump = false;
         platformSpeed = 1;
         backgroundImagePlace = 0;
      }
      else
      {
         instantiateNewPlatforms();
         if(heroX > 490)
         {
            heroX = -10;
         }
         if(heroX < -10)
         {
            heroX = 490;
         }
         if(!startGame)
         {
            heroY = 433; // hero on ground
         }
         if(movingPlatforms && !gameover)
         {
            movePlatforms();
         }
         else if(heroY < -10)
         {
            System.out.println("YOU WIN");
            levelComplete = true;
         }
         if(startGame)
         {
            if((background[level-1].getHeight() - WINDOWHEIGHT - backgroundImagePlace) < 0)
            {
               movingPlatforms = false;
            }
            else
            {
               backgroundImagePlace += platformSpeed;
               heroY += platformSpeed;
            }
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
               //heroCanJump = false;
            }
            onSurface = isHeroOnSurface();
            System.out.println(onSurface);
            if(onSurface)
            {
               gravity = 0;
               heroCanJump = true;
               heroJump = false;
            }
            if(heroY > 450)
            {
               gameover = true;
            }
         }
      }
      if(!levelComplete)
      {
         if(keys.contains(new Integer(KeyEvent.VK_LEFT)))
         {
            heroX -= heroSpeed;
            direction = "w";
            changingPicCount+=changingPicStep;
            swingingSword = false;
         }
         else if(keys.contains(new Integer(KeyEvent.VK_RIGHT)))
         {
            heroX += heroSpeed;
            direction = "e";
            changingPicCount+=changingPicStep;
            swingingSword = false;
         }
         else
         {
            changingPicCount = 10;
         }
      }
      if(swingingSword)
      {
         if(swingingSwordCount >= 8)
         {
            swingingSword = false;
         }
         if(generalCount % 3 == 0)
         {
            swingingSwordCount++;
         }
      }
      else
      {
         swingingSwordCount = 0;
      }
      generalCount ++;
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      
      try
      {
         bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
         bufferGraphics.drawImage(background[level-1], 0, (background[level-1].getHeight() - WINDOWHEIGHT - backgroundImagePlace)*-1, observer);
         drawPlatforms(bufferGraphics);
         if(transitionComplete)
         {
            transitionToNextLevel(bufferGraphics);
         }
         else if(levelComplete)
         {
            transitionBackgrounds(bufferGraphics);
         }
         else
         {
            if(direction == "e")
            {
               if(swingingSword)
               {
                  bufferGraphics.drawImage(swordRight[(int)swingingSwordCount % 8], heroX, heroY, observer);
               }
               else
               {
                  bufferGraphics.drawImage(heroImageRight[(int)changingPicCount % 11], heroX, heroY, observer);
               }
            }
            if(direction == "w")
            {
               if(swingingSword)
               {
                  bufferGraphics.drawImage(swordLeft[(int)swingingSwordCount % 8], heroX-(swordLeft[(int)swingingSwordCount % 8].getWidth() - 20), heroY, observer);
               }
               else
               {
                  bufferGraphics.drawImage(heroImageLeft[(int)changingPicCount % 11], heroX, heroY, observer);
               }
            }
         }
         if(gameover)
         {
            bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 80)); 
            bufferGraphics.drawString("Game Over", 25, 60);
            bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, 40)); 
            bufferGraphics.drawString("(Press z to try again)", 30, 100);
         }
         bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 40)); 
         bufferGraphics.setColor(Color.BLACK);
         bufferGraphics.drawString("Score:" + score, 5, 430);
         
         Graphics2D bufferGraphics2D = (Graphics2D)(bufferGraphics);
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
            
            surfaces.add(new int[]{0,leftEnd,rightEnd,0});
         }
         else
         {
            int previousPlatformHeight = surfaces.get(surfaces.size() - 1)[0];
            int platformY = previousPlatformHeight - platformSeparation;
            int leftEnd = (int)(Math.random()*(WINDOWWIDTH - minimumPlatformWidth));
            int rightEnd =  leftEnd + minimumPlatformWidth + (int)(Math.random()*(WINDOWWIDTH - (leftEnd + minimumPlatformWidth)));
            
            surfaces.add(new int[]{platformY, leftEnd, rightEnd,0});
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
         int touched = surfaces.get(n)[3];
         surfaces.set(n,new int[]{platformY+platformSpeed, platformX, platformX2, touched});
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
               heroY = platformY-22; //height of hero
               if(surfaces.get(n)[3] == 0)
               {
                  score += level;
                  surfaces.set(n, new int[]{platformY, platformX, platformX2, 1});
               }
               return true;
            }
         }
      }
      return false;
   }
   public void transitionBackgrounds(Graphics bufferGraphics)
   {
      bufferGraphics.drawImage(transitionBackground, 0, WINDOWHEIGHT - transitionCount, observer);
      if(transitionCount >= 512)
      {
         drawLevelCompleteScreen(bufferGraphics);
         transitionCount = 512;
      }
      else
      {
         transitionCount += 5;
      }
      
   }
   public void drawLevelCompleteScreen(Graphics bufferGraphics)
   {
      bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 50));
      bufferGraphics.drawString("Level " + level + " Complete", 25, 60);
      displayText(bufferGraphics);
   }
   public void displayText(Graphics bufferGraphics)
   {
      if(levelMessage[level-1].length > displayTextCount + 1)
      {
         bufferGraphics.setColor(Color.BLACK);
         bufferGraphics.fillRect(50,250,412,100);
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 25));
         bufferGraphics.drawString(levelMessage[level-1][displayTextCount], 60,275);
         bufferGraphics.drawString(levelMessage[level-1][displayTextCount+1], 60,325);
      }
      else
      {
         transitionComplete = true;
         level++;
      }
   }
   public void transitionToNextLevel(Graphics bufferGraphics)
   {
      bufferGraphics.drawImage(transitionBackground, 0, WINDOWHEIGHT - transitionCount, observer);
      transitionCount += 5;
      if(transitionCount > WINDOWHEIGHT*2)
      {
         transitionCount = 0;
         transitionComplete = false;
         levelComplete = false;
      }
   }
   public void instantiateImages()
   {
      try {
         heroImageLeft[1] = ImageIO.read(new File("moveLeft1.png"));
         heroImageLeft[2] = ImageIO.read(new File("moveLeft2.png"));
         heroImageLeft[3] = ImageIO.read(new File("moveLeft3.png"));
         heroImageLeft[4] = ImageIO.read(new File("moveLeft4.png"));
         heroImageLeft[5] = ImageIO.read(new File("moveLeft5.png"));
         heroImageLeft[6] = ImageIO.read(new File("moveLeft6.png"));
         heroImageLeft[7] = ImageIO.read(new File("moveLeft7.png"));
         heroImageLeft[8] = ImageIO.read(new File("moveLeft8.png"));
         heroImageLeft[9] = ImageIO.read(new File("moveLeft9.png"));
         heroImageLeft[0] = ImageIO.read(new File("moveLeft0.png"));
         heroImageLeft[10] = ImageIO.read(new File("moveLeft10.png"));
         heroImageRight[1] = ImageIO.read(new File("moveRight1.png"));
         heroImageRight[2] = ImageIO.read(new File("moveRight2.png"));
         heroImageRight[3] = ImageIO.read(new File("moveRight3.png"));
         heroImageRight[4] = ImageIO.read(new File("moveRight4.png"));
         heroImageRight[5] = ImageIO.read(new File("moveRight5.png"));
         heroImageRight[6] = ImageIO.read(new File("moveRight6.png"));
         heroImageRight[7] = ImageIO.read(new File("moveRight7.png"));
         heroImageRight[8] = ImageIO.read(new File("moveRight8.png"));
         heroImageRight[9] = ImageIO.read(new File("moveRight9.png"));
         heroImageRight[0] = ImageIO.read(new File("moveRight0.png"));
         heroImageRight[10] = ImageIO.read(new File("moveRight10.png"));
         swordLeft[0] = ImageIO.read(new File("swordLeft0.png"));
         swordLeft[1] = ImageIO.read(new File("swordLeft1.png"));
         swordLeft[2] = ImageIO.read(new File("swordLeft2.png"));
         swordLeft[3] = ImageIO.read(new File("swordLeft3.png"));
         swordLeft[4] = ImageIO.read(new File("swordLeft4.png"));
         swordLeft[5] = ImageIO.read(new File("swordLeft5.png"));
         swordLeft[6] = ImageIO.read(new File("swordLeft6.png"));
         swordLeft[7] = ImageIO.read(new File("swordLeft7.png"));
         swordRight[0] = ImageIO.read(new File("swordRight0.png"));
         swordRight[1] = ImageIO.read(new File("swordRight1.png"));
         swordRight[2] = ImageIO.read(new File("swordRight2.png"));
         swordRight[3] = ImageIO.read(new File("swordRight3.png"));
         swordRight[4] = ImageIO.read(new File("swordRight4.png"));
         swordRight[5] = ImageIO.read(new File("swordRight5.png"));
         swordRight[6] = ImageIO.read(new File("swordRight6.png"));
         swordRight[7] = ImageIO.read(new File("swordRight7.png"));
         sword = ImageIO.read(new File("sword.png"));
         background[0] = ImageIO.read(new File("Level1.jpg"));
         background[1] = ImageIO.read(new File("Level2.jpg"));
         transitionBackground = ImageIO.read(new File("transitionBackground.png"));
               
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
            swingingSword = false;
         }
      }
      if(keyCode == KeyEvent.VK_Z)
      {
         if(gameover)
         {
            startGame = false;
            gameover = false;
            gravity = 0;
            surfaces = new ArrayList<int[]>();
            movingPlatforms = true;
            heroCanJump = true;
            heroJump = false;
            platformSpeed = 1;
            score = 0;
         }
         else if(levelComplete)
         {
            displayTextCount++;
         }
         else if(startGame)
         {
            swingingSword = true;
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
      }
   }
   public void keyTyped(KeyEvent e)
   {
   }
   
   
}