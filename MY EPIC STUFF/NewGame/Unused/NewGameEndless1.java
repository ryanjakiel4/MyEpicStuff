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

public class NewGameEndless extends Canvas
implements KeyListener, Runnable
{
   private int speedOfPlay = 9;
   private boolean startGame = false;
   private int WINDOWHEIGHT = 559, WINDOWWIDTH = 530;
   private ArrayList<Integer> keys = new ArrayList<Integer>();
   private static Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running;
   private Thread thread;
   private int level = 1;
   private int levelDuration = 1500;
   private int backgroundImagePlace= 0;
   private double changingPicCount = 1, changingPicStep = .25;
   private String direction = "e";
   private int heroX = 20, heroY = 200;
   private int oldHeroY;
   private int heroSpeed = 4;
   private ImageObserver observer;
   private boolean heroCanJump = true, canJump = true;
   private boolean heroJump = false;
   private int jumpCount = 0;
   private int[] heroJumpList = new int[]{-9,-9,-9,-8,-8,-8,-8,-8,-7,-7,-7,-7,-7,-6,-6,-6,-6,-6,-5,-5,-5,-5,-5,-4,-4,-4,-4,-4,-4,-3,-3,-3,-3,-3,-3,-3};
   private double gravity = 0;
   private double gravityStep = .2;
   private boolean onSurface = false;
   private ArrayList<int[]> surfaces = new ArrayList<int[]>(); // int[] is [?,?,?,?] where the first is the Y of the platform, then the next two are the left and right X endpoints, and the last is whether the hero touched the platform or not
   private int platformSeparation = 70;
   private int minimumPlatformWidth = 125;
   private int platformThickness = 10;
   private int[] platformSpeeds = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
   private int platformSpeed = 1;
   private boolean movingPlatforms = true;
   private BufferedImage[] heroImageRight = new BufferedImage[11];
   private BufferedImage[] heroImageLeft = new BufferedImage[11];
   private BufferedImage[] swordLeft = new BufferedImage[8];
   private BufferedImage[] swordRight = new BufferedImage[8];
   private BufferedImage sword;
   private BufferedImage beginningBackground;
   private BufferedImage endlessBackground, endlessBackground1, endlessBackground2;
   private BufferedImage thoughtBubble;
   private BufferedImage[] hearts = new BufferedImage[4];
   private BufferedImage[] rupeePics = new BufferedImage[3];
   private BufferedImage[] orkaRight = new BufferedImage[4];
   private BufferedImage[] orkaLeft = new BufferedImage[4];
   private BufferedImage orkaMissle;
   private BufferedImage[] linkDead = new BufferedImage[13];
   private BufferedImage arrowRight, arrowLeft;
   private BufferedImage arrows;
   private BufferedImage gameOver;
   private BufferedImage pressZ;
   private BufferedImage quiver;
   private BufferedImage[] shootArrowRight = new BufferedImage[16];
   private BufferedImage[] shootArrowLeft = new BufferedImage[16];
   private BufferedImage starBackground;
   private BufferedImage heart;
   private BufferedImage plus5,plus10,plus20;
   private BufferedImage arrowUp;
   private ArrayList<Rupee> rupees = new ArrayList<Rupee>();
   private int backgroundCount = 0;
   private boolean gameover = false;
   private int score = 0;
   private int oldHeroOnSurfaceY = 600;
   private int displayTextCount = 0;
   private String[][] message = new String[][]{{"You found the Sword! Press Z", "to use it and slash any likeArray","that you find in your way."},{"Obtained bow and arrows! Press","X to kill likeArray from a distance!","Be careful, you only have a","limited amount of arrows."}};
   private boolean swingingSword = false;
   private int swingingSwordCount = 0;
   private int generalCount = 0;
   private boolean drawFirst = true;
   private boolean displayLevelUp = false;
   private int displayLevelCount = 0;
   private ArrayList<LikeLike> likeArray = new ArrayList<LikeLike>();
   private ArrayList<Ork> orkArray = new ArrayList<Ork>();
   private int enemySpeed = 1;
   private int indexOfCollision = -1;
   private int enemyCollisionCount = 0;
   private int heartsCount = 3;
   private int beginningCount = 0;
   private boolean introduction = false;
   private int kolor;
   private int linkDeadCount = 0;
   private boolean hasSword = false;
   private int swordX;
   private int swordY;
   private int arrowX,arrowY;
   private boolean swordAppeared = false;
   private int quiverX, quiverY, numberOfArrows=0, shootingArrowCount=0;
   private boolean hasQuiver = false, quiverAppeared = false, shootingArrow = false, arrowShot = false;
   private String arrowDir = "";
   private int arrowSpeed = 5;
   private int[] backgroundColor = new int[]{121,202,249};
   private boolean displayStars = false;
   private int platformColor = 0;
   private boolean starsBool = true;
   private int starsBegin;
   private boolean firstStar = false;
   private boolean displayingText = false;
   private int tempRupeeValue;
   private boolean displayRupeeValue = false;
   private int displayRupeeValueCount = 0;
   private  boolean newHighScore = false;
   private int groundArrowX, groundArrowY, heartX, heartY;
   private boolean heartAppeared = false, arrowAppeared = false;
   public NewGameEndless(Dimension size)
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
            Thread.sleep(speedOfPlay);
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
      if(!displayingText)
      {
         if(introduction)
         {
            if(heroX > 522)
            {
               heroX = -10;
            }
            if(heroX < -10)
            {
               heroX = 522;
            }
            if(gameover)
            {
               if(!displayStars)
               {
                  if(backgroundColor[0] < 121)
                     backgroundColor[0]+=gravity/22;
                  if(backgroundColor[1] < 202)
                     backgroundColor[1]+=gravity/18;
                  if(backgroundColor[2] < 249)
                     backgroundColor[2]+=gravity/14;
               }
               if(backgroundImagePlace > 0)
               {
               
                  backgroundImagePlace -= gravity;
                  heroY = 430;
                  gravity += gravityStep;
                  platformSpeed = (int)(gravity*-1);
                  movePlatforms(platformSpeed);
                  likeArray = new ArrayList<LikeLike>();
                  orkArray = new ArrayList<Ork>();
                  swordAppeared = false;
                  arrowAppeared = false;
                  heartAppeared = false;
                  quiverAppeared = false;
               }
               else
               {
                  backgroundColor[0] = 121;
                  backgroundColor[1] = 202;
                  backgroundColor[2] = 249;
                  backgroundImagePlace = 0;
                  heroY = 430;
               }
            }
            else
            {
               instantiateNewPlatforms();
               if(generalCount % 2 == 0)
               {
               platformSpeed = platformSpeeds[(generalCount%platformSpeeds.length)/2];
               }
               else
               {
               platformSpeed = platformSpeeds[platformSpeeds.length - ((generalCount%platformSpeeds.length)/2) - 1];
               }
               if(!startGame)
               {
                  heroY = 433; // hero on ground
               }
               if(movingPlatforms && !gameover)
               {
                  movePlatforms(platformSpeed);
               }
               if(startGame)
               {
                  backgroundImagePlace += platformSpeed;
                  heroY += platformSpeed;
                  if(backgroundImagePlace > (level*levelDuration))
                  {
                     level++;
                     platformSpeeds[level%platformSpeeds.length]++;
                     displayLevelUp = true;
                  }
                  //System.out.println(backgroundColor[2]);
                  if(generalCount % 35 == 0 && backgroundImagePlace > 10)
                  {
                     if(!gameover)
                     {
                     
                        if(backgroundColor[2] > 0)
                        {
                           if(backgroundColor[0] > 0)
                              backgroundColor[0]--;
                           if(backgroundColor[1] > 0)
                              backgroundColor[1]--;
                           if(backgroundColor[2] > 0)
                              backgroundColor[2]--;
                        }
                        else
                        {
                           if((backgroundImagePlace+endlessBackground1.getHeight())%(endlessBackground1.getHeight() + WINDOWHEIGHT) == endlessBackground1.getHeight() || (backgroundImagePlace)%(endlessBackground1.getHeight() + WINDOWHEIGHT) == endlessBackground1.getHeight())
                           {
                              displayStars =  true;
                              if(starsBool)
                              {
                                 starsBegin = backgroundImagePlace;
                                 starsBool = false;
                              }
                           }
                           backgroundColor = new int[]{0,0,0};
                        }
                        if(platformColor < 255)
                           platformColor++;
                     }
                  }
                  // if(displayStars)
                  // {
                     // for(int n = 0; n < WINDOWWIDTH; n++)
                     // {
                        // if(Math.random() > .999)
                        // {
                           // stars.add(new Star(n,0,(int)(Math.random()*7)+1));
                        // }
                     // }
                     // for(int n = 0; n < stars.size(); n++)
                     // {
                        // stars.get(n).setY(platformSpeed);
                     // }
                  // }
                  oldHeroY = heroY;
                  for(int n = 0 ;n < likeArray.size(); n++)
                  {
                     if(likeArray.get(n).getY() > 450)
                     {
                        if(n == indexOfCollision)
                        {
                           indexOfCollision = -1;
                        }
                        likeArray.remove(n);
                        break;
                     }
                     boolean tempBool = likeArray.get(n).makeMove(heroX, heroY, platformSpeed,24);
                     if(tempBool)
                     {
                        indexOfCollision = n;
                     }
                  }
                  for(int n = 0; n < orkArray.size(); n++)
                  {
                     if(orkArray.get(n).getY() > 450)
                     {
                        orkArray.remove(n);
                        break;
                     }
                     int tempInt = orkArray.get(n).makeMove(heroX, heroY, platformSpeed,heroImageLeft[(int)changingPicCount % 11].getWidth(),swingingSword);
                     if(tempInt == 1)
                     {
                        heartsCount--;
                        orkArray.remove(n);
                     }
                     if(tempInt == 2)
                     {
                        if(Math.random()*level > .9)
                        {
                           tempRupeeValue = 20;
                        }
                        if(Math.random()*level > .7)
                        {
                           tempRupeeValue = 10;
                        }
                        else
                        {
                           tempRupeeValue = 5;
                        }
                        score += tempRupeeValue;
                        displayRupeeValue = true;
                        orkArray.remove(n);
                     }
                     if(tempInt == 3)
                     {
                        heartsCount--;
                     }
                     
                  }
                  if(indexOfCollision == -1)
                  {
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
                     if(onSurface)
                     {
                        gravity = 0;
                        if(canJump)
                        {
                           heroCanJump = true;
                        }
                        heroJump = false;
                     }
                     // for(int n = 0; n < rupees.size(); n++)
                  //                      {
                  //                         rupees.get(n).setY(rupees.get(n).getY() + platformSpeed);
                  //                         if(Math.abs(heroY - rupees.get(n).getY()) < 15 && Math.abs(heroX + 10 - rupees.get(n).getX() - 5) < 15)
                  //                         {
                  //                            score += rupees.get(n).getValue();
                  //                            rupees.remove(rupees.get(n));
                  //                         }
                  //                      }
                     if(heartsCount <= 0)
                     {
                        gameover = true;
                     }
                  }
                  if(swordAppeared)
                  {
                     if(heroX >= swordX && heroX <= (swordX + sword.getWidth()) && Math.abs(heroY-swordY) < 15)
                     {
                        hasSword = true;
                        swordAppeared = false;
                        displayingText = true;
                     }
                     else
                     {
                        displayingText = false;
                     }
                     swordY += platformSpeed;
                     if(swordY > 450)
                     {
                        swordAppeared = false;
                     }
                  }
                  if(quiverAppeared)
                  {
                     if(heroX >= quiverX && heroX <= (quiverX + quiver.getWidth()) && Math.abs(heroY-quiverY) < 15)
                     {
                        hasQuiver = true;
                        quiverAppeared = false;
                        displayingText = true;
                        numberOfArrows = 10;
                     }
                     else
                     {
                        displayingText = false;
                     }
                     quiverY += platformSpeed;
                     if(quiverY > 450)
                     {
                        quiverAppeared = false;
                     }
                  }
                  if(heartAppeared)
                  {
                     if(heroX >= heartX && heroX <= (heartX + heart.getWidth()) && Math.abs(heroY-heartY) < 20)
                     {
                        heartAppeared = false;
                        heartsCount++;
                     }
                     heartY += platformSpeed;
                  }
                  if(arrowAppeared)
                  {
                     if(heroX >= groundArrowX && heroX <= (groundArrowX + arrows.getWidth()) && Math.abs(heroY-groundArrowY) < 20)
                     {
                        arrowAppeared = false;
                        if(numberOfArrows <=5)
                           numberOfArrows += 5;
                        else
                           numberOfArrows = 10;
                     }
                     groundArrowY += platformSpeed;
                  }
                  if(heroY > 440)
                  {
                     gameover = true;
                  }
               }
            
            }
            if(indexOfCollision == -1)
            {
               if(!(gameover && backgroundImagePlace == 0))
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
               if(swingingSword && hasSword)
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
               if(shootingArrow && hasQuiver)
               {
                  if(shootingArrowCount >= 16)
                  {
                     shootingArrow = false;
                     arrowShot = true;
                     arrowY = heroY + 10;
                     if(direction.equals("e"))
                     {
                        arrowX = heroX + 21; //width of final arrow shooting position
                        arrowDir = "e";
                     }
                     else
                     {
                        arrowX = heroX - arrowLeft.getWidth();
                        arrowDir = "w";
                     }
                     
                  }
                  if(generalCount % 3 == 0)
                  {
                     shootingArrowCount++;
                  }
               }
               else
               {
                  shootingArrowCount = 0;
               }
               if(arrowShot)
               {
                  arrowY += platformSpeed;
                  if(arrowDir.equals("e"))
                  {
                     for(int n = 0; n < likeArray.size(); n++)
                     {
                        if(Math.abs(likeArray.get(n).getX() - (arrowX + arrowRight.getWidth())) <= arrowSpeed && Math.abs(likeArray.get(n).getY() - arrowY) < 50)
                        {
                           likeArray.remove(n);
                           if(Math.random()*level > .9)
                           {
                              tempRupeeValue = 20;
                           }
                           if(Math.random()*level > .7)
                           {
                              tempRupeeValue = 10;
                           }
                           else
                           {
                              tempRupeeValue = 5;
                           }
                           score += tempRupeeValue;
                           displayRupeeValue = true;
                           arrowShot = false;
                        }
                     }
                     for(int n = 0; n < orkArray.size(); n++)
                     {
                        if(Math.abs(orkArray.get(n).getX() - (arrowX + arrowRight.getWidth())) <= arrowSpeed && Math.abs(orkArray.get(n).getY() - arrowY) < 15)
                        {
                           orkArray.remove(n);
                           if(Math.random()*level > .9)
                           {
                              tempRupeeValue = 20;
                           }
                           if(Math.random()*level > .7)
                           {
                              tempRupeeValue = 10;
                           }
                           else
                           {
                              tempRupeeValue = 5;
                           }
                           score += tempRupeeValue;
                           displayRupeeValue = true;
                           arrowShot = false;
                        }
                     }
                     arrowX += arrowSpeed;
                  }
                  else
                  {
                     for(int n = 0; n < likeArray.size(); n++)
                     {
                        if(Math.abs((likeArray.get(n).getX() + 20) - arrowX) <= arrowSpeed && Math.abs(likeArray.get(n).getY() - arrowY) < 15)
                        {
                           likeArray.remove(n);
                           if(Math.random()*level > .9)
                           {
                              tempRupeeValue = 20;
                           }
                           if(Math.random()*level > .7)
                           {
                              tempRupeeValue = 10;
                           }
                           else
                           {
                              tempRupeeValue = 5;
                           }
                           score += tempRupeeValue;
                           displayRupeeValue = true;
                           arrowShot = false;
                        }
                     }
                     for(int n = 0; n < orkArray.size(); n++)
                     {
                        if(Math.abs((orkArray.get(n).getX() + 20) - arrowX) <= arrowSpeed && Math.abs(orkArray.get(n).getY() - arrowY) < 15)
                        {
                           orkArray.remove(n);
                           if(Math.random()*level > .9)
                           {
                              tempRupeeValue = 20;
                           }
                           if(Math.random()*level > .7)
                           {
                              tempRupeeValue = 10;
                           }
                           else
                           {
                              tempRupeeValue = 5;
                           }
                           score += tempRupeeValue;
                           displayRupeeValue = true;
                           arrowShot = false;
                        }
                     }
                     arrowX -= arrowSpeed;
                  }
               }
            }
            else
            {
               if(swingingSword && hasSword)
               {
                  //rupees.add(new Rupee(likeArray.get(indexOfCollision).getX(), likeArray.get(indexOfCollision).getY()- 35, level));
                  likeArray.remove(indexOfCollision);
                  indexOfCollision = -1;
                  if(Math.random()*level > .9)
                  {
                     tempRupeeValue = 20;
                  }
                  if(Math.random()*level > .7)
                  {
                     tempRupeeValue = 10;
                  }
                  else
                  {
                     tempRupeeValue = 5;
                  }
                  score += tempRupeeValue;
                  displayRupeeValue = true;
               }
               else
               {
                  enemyCollisionCount++;
                  if(enemyCollisionCount > 50)
                  {
                     enemyCollisionCount = 0;
                     likeArray.remove(indexOfCollision);
                     indexOfCollision = -1;
                     heartsCount--;
                  }
               }
            }
            generalCount ++;
         }
      }
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
      if(!introduction)
      { 
         bufferGraphics.setColor(Color.BLACK);
         bufferGraphics.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
         introduction = playIntro(beginningCount);
         beginningCount++;
      }
      else
      {
         try
         {
            bufferGraphics.setColor(new Color(backgroundColor[0],backgroundColor[1],backgroundColor[2]));
            bufferGraphics.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
            //if(!displayStars)
            //{
            if(gameover && (backgroundImagePlace <= starsBegin))
            {
               endlessBackground1 = endlessBackground;
               endlessBackground2 = endlessBackground;
               displayStars = false;
            }
            if(displayStars && ((backgroundImagePlace%(endlessBackground1.getHeight() + WINDOWHEIGHT) - endlessBackground1.getHeight()) >= WINDOWHEIGHT-platformSpeed))
               endlessBackground1 = starBackground;
            if(displayStars && (((backgroundImagePlace+endlessBackground2.getHeight())%(endlessBackground2.getHeight() + WINDOWHEIGHT) - endlessBackground2.getHeight()) >= WINDOWHEIGHT-platformSpeed))
               endlessBackground2 = starBackground;
               
            bufferGraphics.drawImage(endlessBackground1,0,backgroundImagePlace%(endlessBackground1.getHeight() + WINDOWHEIGHT) - endlessBackground1.getHeight(),observer);
            bufferGraphics.drawImage(endlessBackground2,0,(backgroundImagePlace+endlessBackground2.getHeight())%(endlessBackground2.getHeight() + WINDOWHEIGHT) - endlessBackground2.getHeight(),observer);
            //}
            // else
            // {
            //    // bufferGraphics.setColor(Color.YELLOW);
            //    // for(int n = 0; n < stars.size(); n++)
            //    // {
            //       // stars.get(n).drawStar(bufferGraphics);
            //    // }
               // bufferGraphics.drawImage(starBackground,0,(backgroundImagePlace+starBackground.getHeight())%(starBackground.getHeight() + WINDOWHEIGHT) - starBackground.getHeight(),observer);
               // if(firstStar || (backgroundImagePlace+starBackground.getHeight())%(starBackground.getHeight() + WINDOWHEIGHT) == 0)
               // {
                  // bufferGraphics.drawImage(starBackground,0,backgroundImagePlace%(starBackground.getHeight() + WINDOWHEIGHT) - starBackground.getHeight(),observer);
                  // firstStar = true;
               // }
               // else
                  // bufferGraphics.drawImage(endlessBackground1,0,backgroundImagePlace%(endlessBackground1.getHeight() + WINDOWHEIGHT) - endlessBackground1.getHeight(),observer);
            // 
            // }
            if(backgroundImagePlace < beginningBackground.getHeight())
            {
               bufferGraphics.drawImage(beginningBackground, 0, backgroundImagePlace, observer);
            }
            if(displayLevelUp)
            {
               bufferGraphics.setColor(Color.RED);
               bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, 50)); 
               bufferGraphics.drawString("Level " + level, 350, 505);
               displayLevelCount++;
               if(displayLevelCount > 500)
               {
                  displayLevelUp = false;
                  displayLevelCount = 0;
               }
            }
            drawPlatforms(bufferGraphics);
            if(displayingText)
            {
               if(hasQuiver)
               {
                  displayText(1,bufferGraphics);
               }
               else if(hasSword)
               {
                  displayText(0,bufferGraphics);
               }
            }
            if(swordAppeared)
            {
               bufferGraphics.drawImage(sword,swordX,swordY,observer);
            }
            if(quiverAppeared)
            {
               bufferGraphics.drawImage(quiver,quiverX,quiverY,observer);
            }
            if(heartAppeared)
            {
               //System.out.println("heart" + backgroundColor[2]);
               bufferGraphics.drawImage(heart,heartX,heartY,observer);
            }
            if(arrowAppeared)
            {
               //System.out.println("arrow" + backgroundColor[2]);
               bufferGraphics.drawImage(arrows, groundArrowX, groundArrowY,observer);
            }
            for(int n = 0; n < likeArray.size(); n++)
            {
               likeArray.get(n).drawMove(bufferGraphics, (n == indexOfCollision), observer);
            }
            for(int n = 0; n < orkArray.size(); n++)
            {
               orkArray.get(n).drawMove(bufferGraphics, observer);
            }
            // for(int n = 0; n < rupees.size(); n++)
            // {
               // bufferGraphics.drawImage(rupeePics[rupees.get(n).getKind()], rupees.get(n).getX(), rupees.get(n).getY(), observer);
            // }
            if(displayRupeeValue)
            {
               if(displayRupeeValueCount < 90)
               {
                  if(tempRupeeValue == 20)
                     bufferGraphics.drawImage(plus20, heroX, heroY-plus20.getHeight(),observer);
                  else if(tempRupeeValue == 10)
                     bufferGraphics.drawImage(plus10, heroX, heroY-plus20.getHeight(),observer);
                  else
                     bufferGraphics.drawImage(plus5, heroX, heroY-plus20.getHeight(),observer);
                  displayRupeeValueCount++;
               }
               else
               {
                  displayRupeeValue = false;
                  tempRupeeValue = 0;
                  displayRupeeValueCount = 0;
               }
            }
            if(indexOfCollision == -1 && !(gameover && backgroundImagePlace == 0))
            {
               if(direction.equals("e"))
               {
                  if(swingingSword)
                  {
                     bufferGraphics.drawImage(swordRight[(int)swingingSwordCount % 8], heroX, heroY, observer);
                  }
                  else if(shootingArrow)
                  {
                     bufferGraphics.drawImage(shootArrowRight[(int)shootingArrowCount % 16], heroX-(shootArrowRight[(int)shootingArrowCount % 16].getWidth() - 20), heroY, observer);
                  }
                  else
                  {
                     bufferGraphics.drawImage(heroImageRight[(int)changingPicCount % 11], heroX, heroY, observer);
                  }
               }
               if(direction.equals("w"))
               {
                  if(swingingSword)
                  {
                     bufferGraphics.drawImage(swordLeft[(int)swingingSwordCount % 8], heroX-(swordLeft[(int)swingingSwordCount % 8].getWidth() - 20), heroY, observer);
                  }
                  else if(shootingArrow)
                  {
                     bufferGraphics.drawImage(shootArrowLeft[(int)shootingArrowCount % 16], heroX-(shootArrowLeft[(int)shootingArrowCount % 16].getWidth() - 20), heroY, observer);
                  }
                  else
                  {
                     bufferGraphics.drawImage(heroImageLeft[(int)changingPicCount % 11], heroX, heroY, observer);
                  }
               }
            }
            if(arrowShot)
            {
               if(arrowDir.equals("e"))
               {
                  bufferGraphics.drawImage(arrowRight, arrowX, arrowY, observer);
               }
               else
               {
                  bufferGraphics.drawImage(arrowLeft, arrowX, arrowY, observer);
               }
            }
            if(gameover)
            {
               if(backgroundImagePlace == 0)
               {
                  // bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 80)); 
                  // bufferGraphics.drawString("Game Over", 25, 60);
                  // bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, 40)); 
                  // bufferGraphics.drawString("(Press z to try again)", 30, 100);
                  // bufferGraphics.drawImage(thoughtBubble, heroX + 20, heroY - 100, observer);
                  bufferGraphics.drawImage(gameOver, 6,10,observer);
                  bufferGraphics.drawImage(pressZ, 100,100,observer);
                  bufferGraphics.drawImage(linkDead[linkDeadCount/6],heroX,heroY,observer);
                  Scanner sc = new Scanner(new File("highscore.txt"));
                  String high = sc.nextLine();
                  sc.close();
                  if(Integer.parseInt(high) < score)
                  {
                     high = ""+score;
                     newHighScore = true;
                  }
                  PrintWriter pw = new PrintWriter(new FileWriter("highscore.txt"));
                  pw.println(high);
                  pw.close();
                  bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 40)); 
                  bufferGraphics.setColor(Color.RED);
                  if(newHighScore)
                  {
                     bufferGraphics.drawString("New Highscore!", 400,480);
                  }
                  bufferGraphics.drawString("Highscore: "+high, 250 ,505);
                  
                  
                  
                  if(linkDeadCount < 72)
                     linkDeadCount++;
               }
            }
           
            
            bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 40)); 
            if(!gameover)
               bufferGraphics.setColor(new Color(platformColor, platformColor,platformColor));
            else
               bufferGraphics.setColor(Color.BLACK);
            bufferGraphics.drawString("Score:" + score, 5, 505);
            if(hasQuiver)
            {
               bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, 20));
               if(numberOfArrows == 10)
                  bufferGraphics.drawString("'s:MAX " + numberOfArrows,450,17);
               else 
                  bufferGraphics.drawString("'s: " + numberOfArrows,450,17);
               bufferGraphics.drawImage(arrowUp,440,2, observer);
            }
            bufferGraphics.drawImage(hearts[heartsCount], 0,0,observer);
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
         if(startGame)
         {
            if(surfaces.get(n)[0] > 450)
            {
               surfaces.remove(n);
            }
         }
         else
         {
            if(surfaces.get(n)[0] > 415)
            {
               surfaces.remove(n);
            }
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
            if(backgroundImagePlace > 0)
            {
               if((rightEnd - leftEnd) > 300 && hasSword && Math.random() > .7)
               {
                  likeArray.add(new LikeLike((int)(leftEnd + Math.random()*(rightEnd-leftEnd)), 0, enemySpeed, leftEnd, rightEnd));
               }
            }
            if(backgroundImagePlace > 0)
            {
               if((rightEnd - leftEnd) > 300 && hasQuiver && Math.random() > .7)
               {
                  orkArray.add(new Ork((int)(leftEnd + Math.random()*(rightEnd-leftEnd)), 0, 6, leftEnd, rightEnd));
               }
            }
            if(level >= 2 && !hasSword && !swordAppeared && Math.random() > .9)
            {
               swordX = (int)(Math.random()*(rightEnd - sword.getWidth() - leftEnd)) + leftEnd;
               swordY = platformY - sword.getHeight();
               swordAppeared = true;
            }
            if(level >= 4 && !hasQuiver && !quiverAppeared && Math.random() > .9 && hasSword)
            {
               quiverX = (int)(Math.random()*(rightEnd - quiver.getWidth() - leftEnd)) + leftEnd;
               quiverY = platformY - quiver.getHeight();
               quiverAppeared = true;
            }
            if(level >= 2 && hasSword && !heartAppeared && Math.random() > .9 && heartsCount < 3)
            {
               heartX = (int)(Math.random()*(rightEnd - heart.getWidth() - leftEnd)) + leftEnd;
               heartY = platformY - heart.getHeight();
               heartAppeared = true;
            }
            if(level >= 4 && hasQuiver && !arrowAppeared && Math.random() > .9)
            {
               groundArrowX = (int)(Math.random()*(rightEnd - arrows.getWidth() - leftEnd)) + leftEnd;
               groundArrowY = platformY - arrows.getHeight();
               arrowAppeared = true;
            }
            surfaces.add(new int[]{platformY, leftEnd, rightEnd,0});
         }
      }
   }
   public void drawPlatforms(Graphics g)
   {
      g.setColor(new Color(platformColor,platformColor,platformColor));
      for(int n = 0; n < surfaces.size(); n++)
      {
         int platformX = surfaces.get(n)[1];
         int platformY = surfaces.get(n)[0];
         int platformLength = surfaces.get(n)[2] - platformX;
         g.fillRect(platformX, platformY, platformLength, platformThickness);
      }
   }
   public void movePlatforms(int tempPlatformSpeed)
   {
      for(int n = 0; n < surfaces.size(); n++)
      {
         int platformY = surfaces.get(n)[0];
         int platformX = surfaces.get(n)[1];
         int platformX2 = surfaces.get(n)[2];
         int touched = surfaces.get(n)[3];
         surfaces.set(n,new int[]{platformY+tempPlatformSpeed, platformX, platformX2, touched});
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
                  score += 1;
                  surfaces.set(n, new int[]{platformY, platformX, platformX2, 1});
               }
               return true;
            }
         }
      }
      return false;
   }
   // public void displayText(Graphics bufferGraphics)
   // {
      // if(message[level-1].length > displayTextCount + 1)
      // {
         // bufferGraphics.setColor(Color.BLACK);
         // bufferGraphics.fillRect(50,250,412,100);
         // bufferGraphics.setColor(Color.WHITE);
         // bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 25));
         // bufferGraphics.drawString(message[level-1][displayTextCount], 60,275);
         // bufferGraphics.drawString(message[level-1][displayTextCount+1], 60,325);
      // }
   // }
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
         beginningBackground = ImageIO.read(new File("beginningBackground1.png"));
         endlessBackground = ImageIO.read(new File("endlessBackground1.png"));
         endlessBackground1 = ImageIO.read(new File("endlessBackground1.png"));
         endlessBackground2 = ImageIO.read(new File("endlessBackground1.png"));
         thoughtBubble = ImageIO.read(new File("thoughtBubble.png"));
         hearts[0] = ImageIO.read(new File("0Hearts.png"));
         hearts[1] = ImageIO.read(new File("1Hearts.png"));
         hearts[2] = ImageIO.read(new File("2Hearts.png"));
         hearts[3] = ImageIO.read(new File("3Hearts.png"));
         rupeePics[0] = ImageIO.read(new File("rupee5.png"));
         rupeePics[1] = ImageIO.read(new File("rupee10.png"));
         rupeePics[2] = ImageIO.read(new File("rupee20.png"));
         orkaRight[0] = ImageIO.read(new File("oRight1.png"));
         orkaRight[1] = ImageIO.read(new File("oRight2.png"));
         orkaRight[2] = ImageIO.read(new File("oRight3.png"));
         orkaRight[3] = ImageIO.read(new File("oRight4.png"));
         orkaLeft[0] = ImageIO.read(new File("oLeft1.png"));
         orkaLeft[1] = ImageIO.read(new File("oLeft2.png"));
         orkaLeft[2] = ImageIO.read(new File("oLeft3.png"));
         orkaLeft[3] = ImageIO.read(new File("oLeft4.png"));
         orkaMissle = ImageIO.read(new File("oMissle.png"));
         linkDead[0] = ImageIO.read(new File("linkDead1.png"));
         linkDead[1] = ImageIO.read(new File("linkDead2.png"));
         linkDead[2] = ImageIO.read(new File("linkDead3.png"));
         linkDead[3] = ImageIO.read(new File("linkDead4.png"));
         linkDead[4] = ImageIO.read(new File("linkDead5.png"));
         linkDead[5] = ImageIO.read(new File("linkDead6.png"));
         linkDead[6] = ImageIO.read(new File("linkDead7.png"));
         linkDead[7] = ImageIO.read(new File("linkDead8.png"));
         linkDead[8] = ImageIO.read(new File("linkDead9.png"));
         linkDead[9] = ImageIO.read(new File("linkDead10.png"));
         linkDead[10] = ImageIO.read(new File("linkDead11.png"));
         linkDead[11] = ImageIO.read(new File("linkDead12.png"));
         linkDead[12] = ImageIO.read(new File("linkDead13.png"));
         arrowRight = ImageIO.read(new File("arrowRight.png"));
         arrowLeft = ImageIO.read(new File("arrowLeft.png"));
         gameOver = ImageIO.read(new File("gameover.png"));
         pressZ = ImageIO.read(new File("pressZ.png"));
         quiver = ImageIO.read(new File("quiver.png"));
         shootArrowRight[1] = ImageIO.read(new File("shootArrowRight1.png"));
         shootArrowRight[2] = ImageIO.read(new File("shootArrowRight2.png"));
         shootArrowRight[3] = ImageIO.read(new File("shootArrowRight3.png"));
         shootArrowRight[4] = ImageIO.read(new File("shootArrowRight4.png"));
         shootArrowRight[5] = ImageIO.read(new File("shootArrowRight5.png"));
         shootArrowRight[6] = ImageIO.read(new File("shootArrowRight6.png"));
         shootArrowRight[7] = ImageIO.read(new File("shootArrowRight7.png"));
         shootArrowRight[8] = ImageIO.read(new File("shootArrowRight8.png"));
         shootArrowRight[9] = ImageIO.read(new File("shootArrowRight9.png"));
         shootArrowRight[10] = ImageIO.read(new File("shootArrowRight10.png"));
         shootArrowRight[11] = ImageIO.read(new File("shootArrowRight11.png"));
         shootArrowRight[12] = ImageIO.read(new File("shootArrowRight12.png"));
         shootArrowRight[13] = ImageIO.read(new File("shootArrowRight13.png"));
         shootArrowRight[14] = ImageIO.read(new File("shootArrowRight14.png"));
         shootArrowRight[15] = ImageIO.read(new File("shootArrowRight15.png"));
         shootArrowRight[0] = ImageIO.read(new File("shootArrowRight0.png"));
         shootArrowLeft[1] = ImageIO.read(new File("shootArrowLeft1.png"));
         shootArrowLeft[2] = ImageIO.read(new File("shootArrowLeft2.png"));
         shootArrowLeft[3] = ImageIO.read(new File("shootArrowLeft3.png"));
         shootArrowLeft[4] = ImageIO.read(new File("shootArrowLeft4.png"));
         shootArrowLeft[5] = ImageIO.read(new File("shootArrowLeft5.png"));
         shootArrowLeft[6] = ImageIO.read(new File("shootArrowLeft6.png"));
         shootArrowLeft[7] = ImageIO.read(new File("shootArrowLeft7.png"));
         shootArrowLeft[8] = ImageIO.read(new File("shootArrowLeft8.png"));
         shootArrowLeft[9] = ImageIO.read(new File("shootArrowLeft9.png"));
         shootArrowLeft[10] = ImageIO.read(new File("shootArrowLeft10.png"));
         shootArrowLeft[11] = ImageIO.read(new File("shootArrowLeft11.png"));
         shootArrowLeft[12] = ImageIO.read(new File("shootArrowLeft12.png"));
         shootArrowLeft[13] = ImageIO.read(new File("shootArrowLeft13.png"));
         shootArrowLeft[14] = ImageIO.read(new File("shootArrowLeft14.png"));
         shootArrowLeft[15] = ImageIO.read(new File("shootArrowLeft15.png"));
         shootArrowLeft[0] = ImageIO.read(new File("shootArrowLeft0.png"));
         starBackground = ImageIO.read(new File("starBackground.png")); 
         heart = ImageIO.read(new File("heart.png"));
         arrows = ImageIO.read(new File("arrows.png"));
         plus5 = ImageIO.read(new File("plus5Rupee.png"));
         plus10 = ImageIO.read(new File("plus10Rupee.png"));
         plus20 = ImageIO.read(new File("plus20Rupee.png"));
         arrowUp = ImageIO.read(new File("arrowUp.png"));
      } 
      catch (IOException e) {
      }
   }
   public boolean playIntro(int count)
   {
      bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, 30)); 
      if(count < 255)
      {
         if(count%255 < 128)
            kolor = 255;
         else
            kolor = 255 - 2*((count-128)%255);
         bufferGraphics.setColor(new Color(kolor,kolor,kolor));
         bufferGraphics.drawString("Are You Ready?", 6, 200);
      }
      else if(count < 255*2)
      {
         if(count%255 < 128)
            kolor = 255;
         else
            kolor = 255 - 2*((count-128)%255);
         bufferGraphics.setColor(new Color(kolor,kolor,kolor));     
         bufferGraphics.drawString("Hand On The Arrow Keys", 6, 200);
      }
      else if(count < 255*3)
      {
         if(count%255 < 128)
            kolor = 255;
         else
            kolor = 255 - 2*((count-128)%255);
         bufferGraphics.setColor(new Color(kolor,kolor,kolor));         
         bufferGraphics.drawString("Don't Panic", 6, 200);
      }
      if(count < 255*3)
      {
         return false;
      }
      return true;
   }
   public void displayText(int w, Graphics bufferGraphics)
   {
      if(message[w].length > displayTextCount + 1)
      {
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.fillRect(50,350,412,100);
         bufferGraphics.setColor(Color.BLACK);
         bufferGraphics.setFont(new Font("SansSerif", Font.BOLD, 25));
         bufferGraphics.drawString(message[w][displayTextCount], 60,375);
         bufferGraphics.drawString(message[w][displayTextCount+1], 60,425);
      }
      else
      {
         displayingText = false;
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
            canJump = false;
            startGame = true;
            swingingSword = false;
         }
      }
      if(keyCode == KeyEvent.VK_X)
      {
         if(startGame)
         {
            if(indexOfCollision == -1 && hasQuiver && numberOfArrows > 0 && (!shootingArrow || !arrowShot))
            {
               shootingArrow = true;
               numberOfArrows--;
            }
         }
      }
      if(keyCode == KeyEvent.VK_Z)
      {
         if(gameover)
         {
            if(backgroundImagePlace == 0)
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
               heartsCount = 3;
               rupees = new ArrayList<Rupee>();
               linkDeadCount = 0;
               level = 1;
               displayStars = false;
               backgroundColor = new int[]{121,202,249};
               platformColor = 0;
               hasSword = false;
               hasQuiver = false;
               numberOfArrows = 0;
               newHighScore = false;
               platformSpeeds = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
                              }
         }
         else if(displayingText)
         {
            displayTextCount++;
         }
         else if(startGame)
         {
            if(indexOfCollision == -1  && hasSword)
            {
               swingingSword = true;
            }
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
         canJump = true;
      }
   }
   public void keyTyped(KeyEvent e)
   {
   }
}