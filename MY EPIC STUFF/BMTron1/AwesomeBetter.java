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
public class AwesomeBetter extends Canvas
implements KeyListener
{
   private int WINDOWWIDTH, WINDOWHEIGHT, REALWINDOWHEIGHT;
   public Thread thready;
   public Thread thready1;
   private int rows;
   private int cols;
   private int players;
   private double gameTime;
   private int gameLength;
   private int gameLengthSoFar = 0;
   private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running = true;
   private int generalCount = 0;
   private boolean countdown = true;
   private int colorCount = 0;
   private boolean colorUp = true;
   private int winner = 0;
   private boolean justOnce = true;
   
   private boolean choosingHorizontal = true;
   private boolean choosingVertical = false;
   private boolean choosingPlayers = false;
   private boolean choosingGameSpeed = false;
   private boolean choosingGameTime = false;
   private boolean playing = false;
   private String horizontalNum = "";
   private String verticalNum = "";
   private String playersNum = "";
   private String gameSpeedNum = "";
   private String gameTimeNum = "";
  
   private boolean player1Up = false;
   private boolean player1Left = false;
   private boolean player1Down = false;
   private boolean player1Right = false;
   private boolean player2Up = false;
   private boolean player2Left = false;
   private boolean player2Down = false;
   private boolean player2Right = false;
   private boolean player3Up = false;
   private boolean player3Left = false;
   private boolean player3Down = false;
   private boolean player3Right = false;
   private boolean player4Up = false;
   private boolean player4Left = false;
   private boolean player4Down = false;
   private boolean player4Right = false;
   
   private int player1Row;
   private int player2Row;
   private int player3Row;
   private int player4Row;
   private int player1Col;
   private int player2Col;
   private int player3Col;
   private int player4Col;
   
   private int player1Squares;
   private int player2Squares;
   private int player3Squares;
   private int player4Squares;
   
   private int player1Wins = 0;
   private int player2Wins = 0;
   private int player3Wins = 0;
   private int player4Wins = 0;
   
   private int[][] grid;
   
   // 0's are blank
   // 1's are player 1's trail
   // 2's are player 2's trail
   // 3's are player 3's trail
   // 4's are player 4's trail
   // 11's is player 1's position
   // 22's is player 1's position
   // 33's is player 1's position
   // 44's is player 1's position
   private int speedOfPlay;
   private boolean gameover = false;
   private String[] playerControls = new String[]{
   "Player 1's controls are:\nW     = Up\nA     = Left\nS     = Down\nD     = Right\n",
   "Player 2's controls are:\nUp    = Up\nLeft  = Left\nDown  = Down\nRight = Right\n",
   "Player 3's controls are:\nI     = Up\nJ     = Left\nK     = Down\nL     = Right\n",
   "Player 4's controls are:\nT     = Up\nF     = Left\nG     = Down\nH     = Right\n"};
   private Color[] colors = new Color[]{
   new Color(61,40,247),// blue
   new Color(37,234,21), //green
   new Color(255,0,0),//red
   new Color(255,255,0), // yellow
   new Color(255-61,255-6,255-155), // dark blue
   new Color(255-20,255-124,255-12), // dark green
   new Color(255-164,255-0,255-0), // dark red
   new Color(255-255, 255-128, 255-0), //orange
   };
   public AwesomeBetter(Dimension size)
   {
      this.thready = new Thread(
            new Runnable() {
               public void run()
               {
                  while(running)
                  {
                     if(!gameover)
                     {
                        DoLogic();
                        Draw();
                        DrawBackBufferToScreen();
                        Thread.currentThread();
                        try
                        {
                           thready.sleep(speedOfPlay);
                           if(!countdown)
                              gameLengthSoFar += speedOfPlay;
                           if(gameLengthSoFar > gameLength*100)
                           {
                              if(justOnce)
                              {
                                 winner = whoWon();
                                 justOnce = false;
                              }
                              gameover = true;
                           }
                           if(playing)
                              generalCount++;
                        }
                        catch(Exception e)
                        {
                           e.printStackTrace();
                        } 
                     }
                     else
                     {
                        displayEndGame();
                        DrawBackBufferToScreen();
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
      this.thready1 = new Thread(
            new Runnable() {
               public void run()
               {
               
               }
            });  
      this.setPreferredSize(size);
      addKeyListener(this);
      WINDOWWIDTH = size.width - 1;
      WINDOWHEIGHT = size.height - 100;
      REALWINDOWHEIGHT = size.height;
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
      if(playing)
      {
         if(countdown)
         {
            if(generalCount > 160)
               countdown = false;
         }
         else
         {
            if(player1Left)
            {
               if(player1Row > 0)
               {
                  grid[player1Row][player1Col] = 11;
                  grid[player1Row-1][player1Col] = 1;
                  player1Row--;
               }
            }
            if(player2Left)
            {
               if(player2Row > 0)
               {
                  grid[player2Row][player2Col] = 22;
                  grid[player2Row-1][player2Col] = 2;
                  player2Row--;
               }
            }
            if(player3Left)
            {
               if(player3Row > 0)
               {
                  grid[player3Row][player3Col] = 33;
                  grid[player3Row-1][player3Col] = 3;
                  player3Row--;
               }
            }
            if(player4Left)
            {
               if(player4Row > 0)
               {
                  grid[player4Row][player4Col] = 44;
                  grid[player4Row-1][player4Col] = 4;
                  player4Row--;
               }
            }
            if(player1Up)
            {
               if(player1Col > 0)
               {
                  grid[player1Row][player1Col] = 11;
                  grid[player1Row][player1Col-1] = 1;
                  player1Col--;
               }
            }
            if(player2Up)
            {
               if(player2Col > 0)
               {
                  grid[player2Row][player2Col] = 22;
                  grid[player2Row][player2Col-1] = 2;
                  player2Col--;
               }
            }
            if(player3Up)
            {
               if(player3Col > 0)
               {
                  grid[player3Row][player3Col] = 33;
                  grid[player3Row][player3Col-1] = 3;
                  player3Col--;
               }
            }
            if(player4Up)
            {
               if(player4Col > 0)
               {
                  grid[player4Row][player4Col] = 44;
                  grid[player4Row][player4Col-1] = 4;
                  player4Col--;
               }
            }
            if(player1Right)
            {
               if(player1Row < rows-1)
               {
                  grid[player1Row][player1Col] = 11;
                  grid[player1Row+1][player1Col] = 1;
                  player1Row++;
               }
            }
            if(player2Right)
            {
               if(player2Row < rows-1)
               {
                  grid[player2Row][player2Col] = 22;
                  grid[player2Row+1][player2Col] = 2;
                  player2Row++;
               }
            }
            if(player3Right)
            {
               if(player3Row < rows-1)
               {
                  grid[player3Row][player3Col] = 33;
                  grid[player3Row+1][player3Col] = 3;
                  player3Row++;
               }
            }
            if(player4Right)
            {
               if(player4Row < rows-1)
               {
                  grid[player4Row][player4Col] = 44;
                  grid[player4Row+1][player4Col] = 4;
                  player4Row++;
               }
            }
            if(player1Down)
            {
               if(player1Col < cols-1)
               {
                  grid[player1Row][player1Col] = 11;
                  grid[player1Row][player1Col+1] = 1;
                  player1Col++;
               }
            }
            if(player2Down)
            {
               if(player2Col < cols-1)
               {
                  grid[player2Row][player2Col] = 22;
                  grid[player2Row][player2Col+1] = 2;
                  player2Col++;
               }
            }
            if(player3Down)
            {
               if(player3Col < cols-1)
               {
                  grid[player3Row][player3Col] = 33;
                  grid[player3Row][player3Col+1] = 3;
                  player3Col++;
               }
            }
            if(player4Down)
            {
               if(player4Col < cols-1)
               {
                  grid[player4Row][player4Col] = 44;
                  grid[player4Row][player4Col+1] = 4;
                  player4Col++;
               }
            }
            countSquares();
         }
      }
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
      bufferGraphics.setColor(Color.BLACK);
      bufferGraphics.fillRect(0,0,WINDOWWIDTH, REALWINDOWHEIGHT);   //draw contents of grid
      if(!playing)
      {
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.setFont(new Font("SansSeriff",Font.PLAIN,30));
         if(choosingHorizontal)
         {
            bufferGraphics.drawString("Number of Cols?  "+horizontalNum,0,WINDOWHEIGHT/8);
         }
         if(choosingVertical)
         {
            bufferGraphics.drawString("Number of Cols?  "+horizontalNum,0,WINDOWHEIGHT/8);
            bufferGraphics.drawString("Number of Rows?  "+verticalNum,0,WINDOWHEIGHT*2/8);
         }
         if(choosingPlayers)
         {
            bufferGraphics.drawString("Number of Cols?  "+horizontalNum,0,WINDOWHEIGHT/8);
            bufferGraphics.drawString("Number of Rows?  "+verticalNum,0,WINDOWHEIGHT*2/8);
            bufferGraphics.drawString("Number of Players (2-4)?  "+playersNum,0,WINDOWHEIGHT*3/8);
         }
         if(choosingGameSpeed)
         {
            bufferGraphics.drawString("Number of Cols?  "+horizontalNum,0,WINDOWHEIGHT/8);
            bufferGraphics.drawString("Number of Rows?  "+verticalNum,0,WINDOWHEIGHT*2/8);
            bufferGraphics.drawString("Number of Players (2-4)?  "+playersNum,0,WINDOWHEIGHT*3/8);
            bufferGraphics.drawString("Game Speed (Normal = 10)?  "+gameSpeedNum,0,WINDOWHEIGHT*4/8);
         }
         if(choosingGameTime)
         {
            bufferGraphics.drawString("Number of Cols?  "+horizontalNum,0,WINDOWHEIGHT/8);
            bufferGraphics.drawString("Number of Rows?  "+verticalNum,0,WINDOWHEIGHT*2/8);
            bufferGraphics.drawString("Number of Players (2-4)?  "+playersNum,0,WINDOWHEIGHT*3/8);
            bufferGraphics.drawString("Game Speed (Normal = 10)?  "+gameSpeedNum,0,WINDOWHEIGHT*4/8);
            bufferGraphics.drawString("Game Length?  "+gameTimeNum,0,WINDOWHEIGHT*5/8);
            bufferGraphics.drawString("Once you press enter, the game will start.",0,WINDOWHEIGHT*6/8);
         }
         
      }
      else
      {
         if(countdown)
         {
            bufferGraphics.setColor(Color.WHITE);
            bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, REALWINDOWHEIGHT * 2 / 3));
            if(generalCount < 50)
               bufferGraphics.drawString("3", WINDOWWIDTH/3,WINDOWHEIGHT*3/4);
            else if(generalCount < 100)
               bufferGraphics.drawString("2", WINDOWWIDTH/3,WINDOWHEIGHT*3/4);
            else if(generalCount < 150)
               bufferGraphics.drawString("1", WINDOWWIDTH/3,WINDOWHEIGHT*3/4);
            else
               bufferGraphics.drawString("GO", WINDOWWIDTH/5,WINDOWHEIGHT*3/4);
         }
         for(int r = 0; r < grid.length; r++)
         {
            for(int c = 0; c < grid[0].length; c++)
            {
               if(grid[r][c] != 0)
               {
                  if(grid[r][c] < 10)
                  {
                     bufferGraphics.setColor(colors[grid[r][c]+3]);
                     bufferGraphics.fillOval(r*(WINDOWWIDTH/rows),c*(WINDOWHEIGHT/cols),WINDOWWIDTH/rows,WINDOWHEIGHT/cols);
                  }
                  else if(grid[r][c] > 10)
                  {
                     bufferGraphics.setColor(colors[(grid[r][c]-11)/10]);
                     bufferGraphics.fillRect(r*(WINDOWWIDTH/rows),c*(WINDOWHEIGHT/cols),WINDOWWIDTH/rows,WINDOWHEIGHT/cols);
                  }  
               }    
            }
         }
      
      //draw grid lines
         bufferGraphics.setColor(Color.WHITE);
         for(int r = 0; r < rows+1; r++)
            bufferGraphics.drawLine(r*(WINDOWWIDTH/rows),0,r*(WINDOWWIDTH/rows),WINDOWHEIGHT - (WINDOWHEIGHT%cols));
         for(int c = 0; c < cols+1; c++)
            bufferGraphics.drawLine(0,c*(WINDOWHEIGHT/cols),WINDOWWIDTH - (WINDOWWIDTH%rows),c*(WINDOWHEIGHT/cols));
      
         bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, 30));
         if(players == 2)
         {
            bufferGraphics.drawString("Player 1: " + player1Squares + "      Player 2: " + player2Squares, 10, REALWINDOWHEIGHT - 70); 
            bufferGraphics.drawString("Player 1: " + player1Wins + "      Player 2: " + player2Wins, WINDOWWIDTH/2, REALWINDOWHEIGHT - 70); 
         }
         else if(players == 3)
         {
            bufferGraphics.drawString("Player 1: " + player1Squares + "      Player 2: " + player2Squares + "      Player 3: " + player3Squares, 10, REALWINDOWHEIGHT - 70);
            bufferGraphics.drawString("Player 1: " + player1Wins + "      Player 2: " + player2Wins + "      Player 3: " + player3Wins, WINDOWWIDTH/2, REALWINDOWHEIGHT - 70);
         }
         else if(players == 4)
         {
            bufferGraphics.drawString("Player 1: " + player1Squares + "      Player 2: " + player2Squares + "      Player 3: " + player3Squares + "      Player 4: " + player4Squares, 10, REALWINDOWHEIGHT - 70);
            bufferGraphics.drawString("Player 1: " + player1Wins + "      Player 2: " + player2Wins + "      Player 3: " + player3Wins + "      Player 4: " + player4Wins, WINDOWWIDTH/2, REALWINDOWHEIGHT - 70);
         }
         bufferGraphics.drawString("Time: " + (gameLength*100 - gameLengthSoFar)/speedOfPlay, WINDOWWIDTH-200, REALWINDOWHEIGHT - 70);
      }
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      
   }

   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public void countSquares()
   {
      player1Squares = 0;
      player2Squares = 0;
      player3Squares = 0;
      player4Squares = 0;
      for(int x = 0; x < rows; x++)
      {
         for(int y = 0; y < cols; y++)
         {
            if(grid[x][y] == 1 || grid[x][y] == 11)
               player1Squares++;
            else if(grid[x][y] == 2 || grid[x][y] == 22)
               player2Squares++;
            else if(grid[x][y] == 3 || grid[x][y] == 33)
               player3Squares++;
            else if(grid[x][y] == 4 || grid[x][y] == 44)
               player4Squares++;
         }
      }
   }
   public void resetGame()
   {
      justOnce = true;
      winner = 0;
      countdown = true;
      generalCount = 0;
      grid = new int[rows][cols];
      for(int x = 0; x < rows; x++)
         for(int y = 0; y < cols; y++)
            grid[x][y] = 0; 
       
      if(players == 2)
      {
         player1Row = rows/3;
         player1Col = cols/2;
         grid[rows/3][cols/2] = 1;
         player2Row = 2*rows/3;
         player2Col = cols/2;
         grid[2*rows/3][cols/2] = 2;
      }
      else if(players == 3)
      {
         player1Row = rows/3;
         player1Col = cols/3;
         grid[rows/3][cols/3] = 1;
         player2Row = 2*rows/3;
         player2Col = cols/3;
         grid[2*rows/3][cols/3] = 2;
         player3Row = rows/2;
         player3Col = 2*cols/3;
         grid[rows/2][2*cols/3] = 3;
      }
      else if(players == 4)
      {
         player1Row = rows/3;
         player1Col = cols/3;
         grid[rows/3][cols/3] = 1;
         player2Row = 2*rows/3;
         player2Col = cols/3;
         grid[2*rows/3][cols/3] = 2;
         player3Row = rows/3;
         player3Col = 2*cols/3;
         grid[rows/3][2*cols/3] = 3;
         player4Row = 2*rows/3;
         player4Col = 2*cols/3;
         grid[2*rows/3][2*cols/3] = 4;
      }
      gameLengthSoFar = 0;
   }
   public void displayEndGame()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.setColor(new Color(colorCount,colorCount,colorCount));
      if(colorCount > 250)
         colorUp = false;
      if(colorCount < 1)
         colorUp = true;
      if(colorUp)
         colorCount++;
      else
         colorCount--;
      bufferGraphics.setFont(new Font("SansSerif", Font.PLAIN, 60));
      bufferGraphics.drawString("Time!", WINDOWWIDTH/2 - 75, WINDOWHEIGHT/8);
      if(players >= 2)
      {
         bufferGraphics.drawString("Player 1: " + player1Squares, WINDOWWIDTH/8, WINDOWHEIGHT*2/8);
         bufferGraphics.drawString("Player 2: " + player2Squares, WINDOWWIDTH/8, WINDOWHEIGHT*3/8);
      }
      if(players >= 3)
         bufferGraphics.drawString("Player 3: " + player3Squares, WINDOWWIDTH/8, WINDOWHEIGHT*4/8);
      if(players >= 4)
         bufferGraphics.drawString("Player 4: " + player4Squares, WINDOWWIDTH/8, WINDOWHEIGHT*5/8);
      bufferGraphics.drawString("Player " + winner + " Wins!", WINDOWWIDTH/8, WINDOWHEIGHT*6/8);
      bufferGraphics.drawString("Press R to play again, Press C to edit settings.", WINDOWWIDTH/8, WINDOWHEIGHT*7/8);
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
   }
   public int whoWon()
   {
      if(player1Squares > player2Squares)
      {
         if(player1Squares > player3Squares)
         {
            if(player1Squares > player4Squares)
            {
               player1Wins++;
               return 1;
            }
            else
            {
               player4Wins++;
               return 4;
            }
         }
         else
         {
            if(player3Squares > player4Squares)
            {
               player3Wins++;
               return 3;
            }
            else
            {
               player4Wins++;
               return 4;
            }
         }
      }
      else if(player2Squares > player3Squares)
      {
         if(player2Squares > player4Squares)
         {
            player2Wins++;
            return 2;
         }
         else
         {
            player4Wins++;
            return 4;
         }
      }
      else if(player3Squares > player4Squares)
      {
         player3Wins++;
         return 3;
      }
      else
      {
         player4Wins++;
         return 4;
      }
   }
   
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      
      if(choosingHorizontal)
      {
         if(keyCode == KeyEvent.VK_ENTER)
         {
            choosingHorizontal = false;
            choosingVertical = true;
            cols = Integer.parseInt(horizontalNum);
         }
         else if(keyCode == KeyEvent.VK_BACK_SPACE && horizontalNum.length() >= 1)
         {
            horizontalNum = horizontalNum.substring(0,horizontalNum.length()-1);
         }   
         else if("0123456789".contains(Character.toString((char)keyCode)))
         {
            char c = (char)keyCode;
            horizontalNum += Character.toString(c);
         }
      }
      else if(choosingVertical)
      {
         if(keyCode == KeyEvent.VK_ENTER)
         {
            choosingVertical = false;
            choosingPlayers  = true;
            rows = Integer.parseInt(verticalNum);
            grid = new int[rows][cols];
            for(int x = 0; x < rows; x++)
               for(int y = 0; y < cols; y++)
                  grid[x][y] = 0; 
         }
         else if(keyCode == KeyEvent.VK_BACK_SPACE)
         {
            if(verticalNum.length() <= 0)
            {
               choosingHorizontal = true;
               choosingVertical = false;
            }
            else
            {
               verticalNum = verticalNum.substring(0,verticalNum.length()-1);
            }
         }
         else if("0123456789".contains(Character.toString((char)keyCode)))
         {
            String s = Character.toString((char)keyCode);
            verticalNum += s;
         }
      }
      else if(choosingPlayers)
      {
         if(keyCode == KeyEvent.VK_ENTER)
         {
            choosingPlayers = false;
            choosingGameSpeed = true;
            players = Integer.parseInt(playersNum);
            if(players == 2)
            {
               player1Row = rows/3;
               player1Col = cols/2;
               grid[rows/3][cols/2] = 1;
               player2Row = 2*rows/3;
               player2Col = cols/2;
               grid[2*rows/3][cols/2] = 2;
            }
            else if(players == 3)
            {
               player1Row = rows/3;
               player1Col = cols/3;
               grid[rows/3][cols/3] = 1;
               player2Row = 2*rows/3;
               player2Col = cols/3;
               grid[2*rows/3][cols/3] = 2;
               player3Row = rows/2;
               player3Col = 2*cols/3;
               grid[rows/2][2*cols/3] = 3;
            }
            else if(players == 4)
            {
               player1Row = rows/3;
               player1Col = cols/3;
               grid[rows/3][cols/3] = 1;
               player2Row = 2*rows/3;
               player2Col = cols/3;
               grid[2*rows/3][cols/3] = 2;
               player3Row = rows/3;
               player3Col = 2*cols/3;
               grid[rows/3][2*cols/3] = 3;
               player4Row = 2*rows/3;
               player4Col = 2*cols/3;
               grid[2*rows/3][2*cols/3] = 4;
            }
            else
            {
               System.exit(1);
            }
         }
         else if(keyCode == KeyEvent.VK_BACK_SPACE)
         {
            if(playersNum.length() <= 0)
            {
               choosingVertical = true;
               choosingPlayers = false;
            }
            else
            {
               playersNum = playersNum.substring(0,playersNum.length()-1);
            }
         }
         else if("0123456789".contains(Character.toString((char)keyCode)))
         {
            String s = Character.toString((char)keyCode);
            playersNum+=s;
         }
      }
      else if(choosingGameSpeed)
      {
         if(keyCode == KeyEvent.VK_ENTER)
         {
            choosingGameSpeed = false;
            choosingGameTime = true;
            speedOfPlay = Integer.parseInt(gameSpeedNum);
         }
         else if(keyCode == KeyEvent.VK_BACK_SPACE)
         {
            if(gameSpeedNum.length() <= 0)
            {
               choosingGameSpeed = false;
               choosingPlayers = true;
            }
            else
            {
               gameSpeedNum = gameSpeedNum.substring(0,gameSpeedNum.length()-1);
            }
         }
         else if("0123456789".contains(Character.toString((char)keyCode)))
         {
            String s = Character.toString((char)keyCode);
            gameSpeedNum+=s;
         }
      }
      else if(choosingGameTime)
      {
         if(keyCode == KeyEvent.VK_ENTER)
         {
            choosingGameTime = false;
            playing = true;
            gameTime = Double.parseDouble(gameTimeNum);
            gameLength = (int)(gameTime);
         }
         else if(keyCode == KeyEvent.VK_BACK_SPACE)
         {
            if(gameTimeNum.length() <= 0)
            {
               choosingGameSpeed = true;
               choosingGameTime = false;
            }
            else
            {
               gameTimeNum = gameTimeNum.substring(0,gameTimeNum.length()-1);
            }
         }
         else if("0123456789.".contains(Character.toString((char)keyCode)))
         {
            String s = Character.toString((char)keyCode);
            gameTimeNum+=s;
         }
      }
      else if(playing)
      {
         if(gameover)
         {
            if(keyCode == KeyEvent.VK_R)
            {
               gameover = false;
               resetGame();
            }
            else if(keyCode == KeyEvent.VK_C)
            {
               gameover = false;
               resetGame();
               playing = false; 
               choosingGameTime = true;  
            }
         }
         else
         {
            if(players >= 2)
            {
               if(keyCode == KeyEvent.VK_W)
               {
                  player1Up = true;
                  player1Down = false;
                  player1Left = false;
                  player1Right = false;
               }
               if(keyCode == KeyEvent.VK_A)
               {
                  player1Up = false;
                  player1Down = false;
                  player1Left = true;
                  player1Right = false;
               }
               if(keyCode == KeyEvent.VK_S)
               {
                  player1Up = false;
                  player1Down = true;
                  player1Left = false;
                  player1Right = false;
               }
               if(keyCode == KeyEvent.VK_D)
               {
                  player1Up = false;
                  player1Down = false;
                  player1Left = false;
                  player1Right = true;
               }
               if(keyCode == KeyEvent.VK_UP)
               {
                  player2Up = true;
                  player2Down = false;
                  player2Left = false;
                  player2Right = false;
               }
               if(keyCode == KeyEvent.VK_LEFT)
               {
                  player2Up = false;
                  player2Down = false;
                  player2Left = true;
                  player2Right = false;
               }
               if(keyCode == KeyEvent.VK_DOWN)
               {
                  player2Up = false;
                  player2Down = true;
                  player2Left = false;
                  player2Right = false;
               }
               if(keyCode == KeyEvent.VK_RIGHT)
               {
                  player2Up = false;
                  player2Down = false;
                  player2Left = false;
                  player2Right = true;
               }
            }
            if(players >= 3)
            {
               if(keyCode == KeyEvent.VK_I)
               {
                  player3Up = true;
                  player3Down = false;
                  player3Left = false;
                  player3Right = false;
               }
               if(keyCode == KeyEvent.VK_J)
               {
                  player3Up = false;
                  player3Down = false;
                  player3Left = true;
                  player3Right = false;
               }
               if(keyCode == KeyEvent.VK_K)
               {
                  player3Up = false;
                  player3Down = true;
                  player3Left = false;
                  player3Right = false;
               }
               if(keyCode == KeyEvent.VK_L)
               {
                  player3Up = false;
                  player3Down = false;
                  player3Left = false;
                  player3Right = true;
               }
            }
            if(players >= 4)
            {
               if(keyCode == KeyEvent.VK_T)
               {
                  player4Up = true;
                  player4Down = false;
                  player4Left = false;
                  player4Right = false;
               }
               if(keyCode == KeyEvent.VK_F)
               {
                  player4Up = false;
                  player4Down = false;
                  player4Left = true;
                  player4Right = false;
               }
               if(keyCode == KeyEvent.VK_G)
               {
                  player4Up = false;
                  player4Down = true;
                  player4Left = false;
                  player4Right = false;
               }
               if(keyCode == KeyEvent.VK_H)
               {
                  player4Up = false;
                  player4Down = false;
                  player4Left = false;
                  player4Right = true;
               }
            }
         }
      }
   }
   public void keyReleased(KeyEvent e)
   {
      if(playing)
      {
         int keyCode = e.getKeyCode();
         if(keyCode == KeyEvent.VK_W)
            player1Up = false;
         if(keyCode == KeyEvent.VK_A)
            player1Left = false;
         if(keyCode == KeyEvent.VK_S)
            player1Down = false;
         if(keyCode == KeyEvent.VK_D)
            player1Right = false;
         if(keyCode == KeyEvent.VK_UP)
            player2Up = false;
         if(keyCode == KeyEvent.VK_LEFT)
            player2Left = false;
         if(keyCode == KeyEvent.VK_DOWN)
            player2Down = false;
         if(keyCode == KeyEvent.VK_RIGHT)
            player2Right = false;
         if(keyCode == KeyEvent.VK_I)
            player3Up = false;
         if(keyCode == KeyEvent.VK_J)
            player3Left = false;
         if(keyCode == KeyEvent.VK_K)
            player3Down = false;
         if(keyCode == KeyEvent.VK_L)
            player3Right = false;
         if(keyCode == KeyEvent.VK_T)
            player4Up = false;
         if(keyCode == KeyEvent.VK_F)
            player4Left = false;
         if(keyCode == KeyEvent.VK_G)
            player4Down = false;
         if(keyCode == KeyEvent.VK_H)
            player4Right = false;
      }
   }
   public void keyTyped(KeyEvent e)
   {
   }
}