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

public class Blink extends Canvas
{
   private ImageObserver observer;
   private int toolbarHeight = 21;
   private int mapHeight = 99;
   private int buttonAreaHeight = 56;

private double scaleHorizontal;
private double scaleVertical;

   private int hp = 10;
   private int exp = 0;
   private int mp = 1;
   private int g = 0;
/*
Map Screen
----------------   Toolbar - 21 pixels
|              |
|              |   Map - 99 pixels
|              |
|              |
|              |
|              |
|              |
|              |
|              |
|              |
|              | 
|              |   Buttons - 56 pixels
|    U         |
|           I  |
|  L   R       |
|           C  |
|    D         |
----------------
*/
   private int WINDOWWIDTH, WINDOWHEIGHT;
   public Thread thready;
   private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running = true;
   
   private Image[] alphabet = new Image[26];
   private Image[] numbers = new Image[10];
   private Image[] worldMaps = new Image[4];
   
   private int mouseX;
   private int mouseY;
   private boolean mouseClicked = false;
   
   private boolean inWorld1 = true, inWorld2, inWorld3, inWorld4;
   private boolean inTown1, inTown2, inTown3, inTown4;
   private boolean inDungeon1, inDungeon2, inDungeon3, inDungeon4;
   private boolean inMap = true, inCombat, inMerchant, inBlacksmith;
   
   private boolean areaChecked = false;
   private int areaTextCount = 0;
   
   private boolean inventoryOpened = false;
   
   private int blinkX = 2;
   private int blinkY = 3;
   
   private String buttonPressed;
   
   public Blink(Dimension size)
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
            });
      this.setPreferredSize(size);
   
      addMouseListener(
            new MouseAdapter() { 
               public void mousePressed(MouseEvent me) { 
                  mouseX = MouseInfo.getPointerInfo().getLocation().x; 
                  mouseY = MouseInfo.getPointerInfo().getLocation().y; 
                  mouseClicked = true;
               }});
        
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      scaleHorizontal = WINDOWWIDTH/99.;
      scaleVertical = WINDOWHEIGHT/176.;
      instantiateImages();
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
      
      if(mouseClicked)
      {
         if(inMap)
         {
            if(mouseY >= WINDOWHEIGHT*(120/176)) // check if mouse click is inside the button area
            {
               buttonPressed = whichButtonWasClickedInMap();
               if(areaChecked)
               {
                  if(buttonPressed.equals("Text"))
                     areaTextCount++;
               }
               else
               {
                  if(buttonPressed.equals("Up"))
                     blinkY--;
                  if(buttonPressed.equals("Left"))
                     blinkX--;
                  if(buttonPressed.equals("Right"))
                     blinkX++;
                  if(buttonPressed.equals("Down"))
                     blinkY++;
                  if(buttonPressed.equals("Check"))
                     areaChecked = true;
                  if(buttonPressed.equals("Inventory"))
                     inventoryOpened = true;  
               }
            }
         }
         else if(inCombat)
         {
         
         }
      }
      
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.setColor(Color.WHITE);
      bufferGraphics.drawRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
      drawToolbar();
      
      if(inCombat)
      {
         drawCombatScene();
      }
      else if(inMap)
      {
         if(inWorld1)
            drawWorldMap1();
         else if(inWorld2)
            drawWorldMap2();
         else if(inWorld3)
            drawWorldMap3();
         else if(inWorld4)
            drawWorldMap4();
         else if(inTown1)
            drawTownMap1();
         else if(inTown2)
            drawTownMap2();
         else if(inTown3)
            drawTownMap3();
         else if(inTown4)
            drawTownMap4();
         else if(inDungeon1)
            drawDungeonMap1();
         else if(inDungeon2)
            drawDungeonMap2();
         else if(inDungeon3)
            drawDungeonMap3();
         else if(inDungeon4)
            drawDungeonMap4();
         else if(inMerchant)
            drawMerchantMap();
         else if(inBlacksmith)
            drawBlacksmithMap();
      }
      
      if(areaChecked)
      {
         drawAreaText();
      }
      else
      {
         if(inMap)
            drawMapButtons();
         else if(inCombat)
            drawCombatButtons();
         else if(inMerchant)
            drawMerchantButtons();
         else if(inBlacksmith)
            drawBlacksmithButtons();
      }
      
      if(inMap)
         drawBlinkInMap();
      else if(inCombat)
         drawBlinkInCombat();
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      
   }

   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public void drawWorldMap1()
   {
      bufferGraphics.drawImage(worldMaps[0],0,(int)(toolbarHeight*scaleVertical),observer);
   }
   public void drawWorldMap2()
   {
   }
   public void drawWorldMap3()
   {
   }
   public void drawWorldMap4()
   {
   }
   public void drawTownMap1()
   {
   }
   public void drawTownMap2()
   {
   }
   public void drawTownMap3()
   {
   }
   public void drawTownMap4()
   {
   }
   public void drawDungeonMap1()
   {
   }
   public void drawDungeonMap2()
   {
   }
   public void drawDungeonMap3()
   {
   }
   public void drawDungeonMap4()
   {
   }
   public void drawMerchantMap()
   {
   }
   public void drawBlacksmithMap()
   {
   }
   public void drawMapButtons()
   {
   }
   public void drawCombatButtons()
   {
   }
   public void drawMerchantButtons()
   {
   }
   public void drawBlacksmithButtons()
   {
   }
   public void drawToolbar()
   {
      drawText("h "+hp,(int)(1*scaleHorizontal),(int)(1*scaleVertical));
      drawText("m "+mp,(int)(1*scaleHorizontal),(int)(11*scaleVertical));
      drawText("g "+g,WINDOWWIDTH/2,(int)(1*scaleVertical));
      drawText("e "+exp,WINDOWWIDTH/2,(int)(11*scaleVertical));
   }
   public void drawCombatScene()
   {
   }
   public void drawBlinkInMap()
   {
   }
   public void drawBlinkInCombat()
   {
   }
   public void drawText(String message, int x, int y) //requires message to be lowercase
   {
      int newX = x;
      char awesome;
      int wicked;
      //System.out.println(message);
      for(int n = 0; n < message.length(); n++)
      {
         awesome = message.charAt(n);
         wicked = (int)awesome;
         //System.out.println(wicked+"");
         if(wicked >= 48 && wicked <= 57)
         {
            bufferGraphics.drawImage(numbers[wicked-48],newX,y,observer);
         }
         else if(wicked >= 97 && wicked <= 122)
         {
            bufferGraphics.drawImage(alphabet[wicked-97],newX,y,observer);
         }
         newX+=(int)(6*scaleHorizontal);
      }
   }   
   public String whichButtonWasClickedInMap()
   {
      if(areaChecked)
      {
         if(mouseX <= WINDOWHEIGHT*(22/99) && mouseX >= WINDOWHEIGHT*(44/99))
         {
            if(mouseY >= WINDOWHEIGHT*(120/176))
               return "Text";
         }
      }
      else
      {
         if(mouseX <= WINDOWHEIGHT*(22/99) && mouseX >= WINDOWHEIGHT*(44/99))
         {
            if(mouseY <= WINDOWHEIGHT*(120/176) && mouseX >= WINDOWHEIGHT*(139/176))
               return "Up";
         }
         else if(mouseX <= WINDOWHEIGHT*(0/99) && mouseX >= WINDOWHEIGHT*(22/99))
         {
            if(mouseY <= WINDOWHEIGHT*(139/176) && mouseX >= WINDOWHEIGHT*(158/176))
               return "Left";
         }
         else if(mouseX <= WINDOWHEIGHT*(44/99) && mouseX >= WINDOWHEIGHT*(66/99))
         {
            if(mouseY <= WINDOWHEIGHT*(139/176) && mouseX >= WINDOWHEIGHT*(158/176))
               return "Right";
         }
         else if(mouseX <= WINDOWHEIGHT*(22/99) && mouseX >= WINDOWHEIGHT*(44/99))
         {
            if(mouseY <= WINDOWHEIGHT*(158/176) && mouseX >= WINDOWHEIGHT*(176/176))
               return "Down";
         }
         
         else if(mouseX <= WINDOWHEIGHT*(66/99) && mouseX >= WINDOWHEIGHT*(99/99))
         {
            if(mouseY <= WINDOWHEIGHT*(120/176) && mouseX >= WINDOWHEIGHT*(148/176))
               return "Check";
         }
         else if(mouseX <= WINDOWHEIGHT*(66/99) && mouseX >= WINDOWHEIGHT*(99/99))
         {
            if(mouseY <= WINDOWHEIGHT*(148/176) && mouseX >= WINDOWHEIGHT*(176/176))
               return "Inventory";
         }
      }
      return "None";
   }   
   public void whichButtonWasClickedInCombat()
   {
   }
   
   public void drawAreaText()
   {
   }
   
   public void instantiateImages()
   {
      try {
         alphabet[0] = ImageIO.read(new File("A.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[1] = ImageIO.read(new File("B.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[2] = ImageIO.read(new File("C.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[3] = ImageIO.read(new File("D.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[4] = ImageIO.read(new File("E.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[5] = ImageIO.read(new File("F.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[6] = ImageIO.read(new File("G.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[7] = ImageIO.read(new File("H.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[8] = ImageIO.read(new File("I.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[9] = ImageIO.read(new File("J.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[10] = ImageIO.read(new File("K.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[11] = ImageIO.read(new File("L.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[12] = ImageIO.read(new File("M.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[13] = ImageIO.read(new File("N.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[14] = ImageIO.read(new File("O.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[15] = ImageIO.read(new File("P.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[16] = ImageIO.read(new File("Q.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[17] = ImageIO.read(new File("R.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[18] = ImageIO.read(new File("S.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[19] = ImageIO.read(new File("T.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[20] = ImageIO.read(new File("U.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[21] = ImageIO.read(new File("V.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[22] = ImageIO.read(new File("W.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[23] = ImageIO.read(new File("X.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[24] = ImageIO.read(new File("Y.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         alphabet[25] = ImageIO.read(new File("Z.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[0] = ImageIO.read(new File("0.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[1] = ImageIO.read(new File("1.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[2] = ImageIO.read(new File("2.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[3] = ImageIO.read(new File("3.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[4] = ImageIO.read(new File("4.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[5] = ImageIO.read(new File("5.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[6] = ImageIO.read(new File("6.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[7] = ImageIO.read(new File("7.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[8] = ImageIO.read(new File("8.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         numbers[9] = ImageIO.read(new File("9.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         worldMaps[0] = ImageIO.read(new File("blinkWorldMap1.png")).getScaledInstance((int)(scaleHorizontal*99),(int)(scaleVertical*99),Image.SCALE_SMOOTH);    
      } 
      catch (IOException e) {
      }
   }
}