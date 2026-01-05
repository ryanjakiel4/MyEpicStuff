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

public class WordMiniCustom extends Canvas implements KeyListener
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
   private int stringsTyped = 0;
   
   private ImageObserver observer;
   private Image[] alphabet = new Image[26];
   private Image[] numbers = new Image[10];
   private Image background;
   private Image backgroundWithTextBox;
   private Image period;
   private Image colon;
   private Image underscore;
   private Image leftPeren;
   private Image rightPeren;
   private Image rightQuotes;
   private Image leftQuotes;
   private Image question;
   private Image exclam;
   private Image comma;
   private Image apostrophe;
   
   private double scaleHorizontal, scaleVertical;
   
   public WordMiniCustom(Dimension size)
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
   
      addKeyListener(this);
      this.addComponentListener(new ResizeListener()); 
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      FONTSIZE = WINDOWHEIGHT/20;
      scaleVertical = FONTSIZE/9;
      scaleHorizontal = scaleVertical;
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
      
      if(!startGame && !gameFinished)
      {
      
      }
      else if(startGame)
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
      else if(gameFinished)
      {
      }
      
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
         drawText("Press the letters displayed as fast as you can!", leftMargin, firstLine);
         drawText("Press S to start game",leftMargin,secondLine);
      }
      else if(startGame)
      {
         drawText("Timer: " + (gameTimeAllowed - ((int)(System.currentTimeMillis()/1000)-startTime)), leftMargin, firstLine);
         drawText(goalString, leftMargin,secondLine);
         bufferGraphics.setColor(Color.GREEN);
         drawText(typedInput, leftMargin,secondLine);
         bufferGraphics.setColor(Color.WHITE);
         drawText("Strings Typed: "+stringsTyped, leftMargin, thirdLine);
      }
      else if(gameFinished)
      {
         drawText("Game Over.", leftMargin, firstLine);
         drawText(goalString,leftMargin,secondLine);
         if(!typedInput.equals(""))
         {
            bufferGraphics.setColor(Color.RED);
            drawText(typedInput.substring(0,typedInput.length()),leftMargin,secondSubLine);
            bufferGraphics.setColor(Color.GREEN);
            drawText(typedInput.substring(0,typedInput.length()-1),leftMargin,secondSubLine);
         }
         bufferGraphics.setColor(Color.WHITE);
         drawText("Strings Typed: "+stringsTyped, leftMargin, thirdLine);
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
   
   public void keyPressed(KeyEvent e)
   {
      
   }
   public void keyReleased(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      String letter = Character.toString((char)keyCode).toLowerCase();
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
   public void drawText(String message, int x, int y)
   {
      int newX = x;
      char awesome;
      int wicked;
      message = message.toLowerCase();
      for(int n = 0; n < message.length(); n++)
      {
         awesome = message.charAt(n);
         wicked = (int)awesome;
         if(wicked >= 48 && wicked <= 57)
            bufferGraphics.drawImage(numbers[wicked-48],newX,y,observer);
         else if(wicked >= 97 && wicked <= 122)
            bufferGraphics.drawImage(alphabet[wicked-97],newX,y,observer);
         else if(wicked == 46)
            bufferGraphics.drawImage(period,newX,y,observer);
         else if(wicked == 58)
            bufferGraphics.drawImage(colon,newX,y,observer);
         else if(wicked == 95)
            bufferGraphics.drawImage(underscore,newX,y,observer);
         else if(wicked == 33)
            bufferGraphics.drawImage(exclam,newX,y,observer);
         else if(wicked == 8220)
            bufferGraphics.drawImage(leftQuotes,newX,y,observer);
         else if(wicked == 8221)
            bufferGraphics.drawImage(rightQuotes,newX,y,observer);
         else if(wicked == 40)
            bufferGraphics.drawImage(leftPeren,newX,y,observer);
         else if(wicked == 41)
            bufferGraphics.drawImage(rightPeren,newX,y,observer);
         else if(wicked == 63)
            bufferGraphics.drawImage(question,newX,y,observer);
         else if(wicked == 44)
            bufferGraphics.drawImage(comma,newX,y,observer);    
         else if(wicked == 8217)
            bufferGraphics.drawImage(apostrophe,newX,y,observer);
         newX+=(int)(6*scaleHorizontal);
      }
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
         background = ImageIO.read(new File("MatthewsBdayMap.png")).getScaledInstance((int)(scaleHorizontal*224),(int)(scaleVertical*126),Image.SCALE_SMOOTH);    
         backgroundWithTextBox = ImageIO.read(new File("MatthewsBdayMapWithTextBox.png")).getScaledInstance((int)(scaleHorizontal*224),(int)(scaleVertical*126),Image.SCALE_SMOOTH);    
         period = ImageIO.read(new File("period.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         colon = ImageIO.read(new File("colon.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         underscore = ImageIO.read(new File("underscore.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         leftPeren = ImageIO.read(new File("leftPeren.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         rightPeren = ImageIO.read(new File("rightPeren.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         question = ImageIO.read(new File("question.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         rightQuotes = ImageIO.read(new File("rightQuotes.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         leftQuotes = ImageIO.read(new File("leftQuotes.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         exclam = ImageIO.read(new File("exclam.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         comma = ImageIO.read(new File("comma.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
         apostrophe = ImageIO.read(new File("apostrophe.png")).getScaledInstance((int)(scaleHorizontal*5),(int)(scaleVertical*9),Image.SCALE_SMOOTH);
      } 
      catch (IOException e) {
      }
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
            FONTSIZE = WINDOWHEIGHT/20;
         scaleVertical = FONTSIZE/9;
         scaleHorizontal = scaleVertical;
         instantiateImages();
      }
   }
}