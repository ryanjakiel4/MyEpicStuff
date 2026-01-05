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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;

public class TapTap extends Canvas
implements KeyListener//, Runnable
{
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
   private Color[] colorsOfButtons = new Color[]{
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
   private boolean startSong = false;
   private boolean songFinished = false;
   private int WINDOWWIDTH, WINDOWHEIGHT;
   private ArrayList<Integer> keys = new ArrayList<Integer>();
   private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running;
   public Thread thready;
   public Thread thready1;
   private String songName = "";
   private boolean choosingPlayStyle = true, choosingSong = false, loadingSong = false, editingSong = false, playingSong = false, startedSong;
   private boolean choosingRails = false, choosingKeys = false;
   private boolean upperCase = false;
   private Music song = new Music();
   private String workingDir = System.getProperty("user.dir");
   private String txtFileName;
   private File directory = new File(workingDir);
   private ArrayList<String> txtFiles = new ArrayList<String>();
   private ArrayList<String> songFiles = new ArrayList<String>();
   private int txtDisplayPlace = 0;
   private int FONTSIZE = 30;
   private String numberOfRails = "";
   private int rails;
   private ArrayList<String> railKeys = new ArrayList<String>();
   private int scorebarHeight = 50,keysbarHeight = 100,dividerWidth = 20;
   private boolean songComplete = false;
   private String bip;
   private Media hit;
   private MediaPlayer mediaPlayer;
   private ArrayList<Integer> keysTapped = new ArrayList<Integer>();
   private ArrayList<Integer> keysHeld = new ArrayList<Integer>();
   private PrintWriter noteWriter;
   private Scanner noteReader;
   private String lineOfNotes = "";
   private ArrayList<Note> notesToDrawEditing = new ArrayList<Note>();
   private ArrayList<Note> notesToDrawPlaying = new ArrayList<Note>();
   private int noteThickness = 10;
   private int OFFSCREEN = 0;
   private int noteSpeed;
   //private int delayNotes = WINDOWHEIGHT / noteSpeed;
   private int topOfTrack = 100;
   private int bottomOfTrack;
   private int generalCount = 0;
   private boolean ifTrueEditingSong = true;
   private int difficulty = 160;
   public TapTap(Dimension size)
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
            });
      this.thready1 = new Thread(
            new Runnable() {
               public void run()
               {
                  while(running)
                  {
                     System.out.print(""); //NECESSARY FOR MUSIC TO WORK FOR SOME UNKNOWN REASON
                     if((startSong && editingSong))
                     {
                        mediaPlayer.play();
                     }
                     else if((startSong && playingSong) && generalCount >= 160)
                     {
                        mediaPlayer.play();
                     }
                     else if(songFinished)
                     {
                        mediaPlayer.stop();
                     }
                  }
               }
            });  
      this.setPreferredSize(size);
      addKeyListener(this);
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      System.out.println(WINDOWWIDTH + " x " + WINDOWHEIGHT); 
      running =true;
      getTxtFiles();
      getSongFiles();
   }

   public void paint(Graphics g)
   {
      if(bufferStrategy == null)
      {
         this.createBufferStrategy(2);
         bufferStrategy = this.getBufferStrategy();
         bufferGraphics = bufferStrategy.getDrawGraphics();
         //this.t.start();
         thready.start();
         thready1.start();
         
      }
   }
   public void DoLogic()
   {
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      //System.out.println(editingSong + "  " + startSong);
      if(choosingPlayStyle)
      {
      }
      else if(choosingSong)
      {
      //add selection from list of txt files w/curser, and as you nagivate through with the arrow keys
      //changes pages when you reach the top and bottom and press enter to select
      //can select option of typing in a new file name if it does not exist
      if(playingSong)
      songName = txtFiles.get(txtDisplayPlace);
      else
      songName = songFiles.get(txtDisplayPlace);
      }
      else if(loadingSong)
      {
         //song.setFileName(songName);
         new JFXPanel();
         bip = new File(songName).toURI().toString();
         hit = new Media(bip);
         mediaPlayer = new MediaPlayer(hit);
         mediaPlayer.setOnEndOfMedia(
               new Runnable() {
                  public void run()
                  {
                     songFinished = true;
                  }
               });
         loadingSong = false;
         choosingRails = true;
      }
      else if(choosingRails || choosingKeys)
      {
         if(!choosingRails)
         {
            
         }  
      }
      else if(!startSong)
      {
         startSong = true;  
         txtFileName = songName.substring(0,songName.length()-4) + ".txt";
         if(editingSong)
         {
            try
            {
               noteWriter = new PrintWriter(new File(txtFileName));
            }
            catch(FileNotFoundException e)
            {
            }
         }
         else if(playingSong)
         {
            try
            {
               noteReader = new Scanner(new File(txtFileName));
            }
            catch(FileNotFoundException e)
            {
            }
         }
      }
      else if(editingSong && !songFinished)
      {
         for(int n = 0; n < rails; n++)
         {
            if(keysTapped.contains(n))
            {
               noteWriter.print("2");
               keysTapped.remove(new Integer(n));
               notesToDrawEditing.add(new Note(n*((WINDOWWIDTH - dividerWidth)/rails)+dividerWidth, bottomOfTrack, colors[n]));
            }
            else if(keysHeld.contains(n))
            {
               noteWriter.print("1");
               notesToDrawEditing.add(new Note(n*((WINDOWWIDTH - dividerWidth)/rails)+dividerWidth, bottomOfTrack, Color.GRAY));
            }
            else
            {
               noteWriter.print("0");
            }
         } 
         noteWriter.println();
         for(int n = 0; n < notesToDrawEditing.size(); n++)
         {
            notesToDrawEditing.get(n).increment(noteSpeed);
            if(notesToDrawEditing.get(n).isAboveTrack(topOfTrack))
            {
               notesToDrawEditing.remove(n);
            }
         }
      }
      else if(playingSong && !songFinished)
      {
      // Need to adjust text file to allow for user error in taps
      // look back at the test txt file to see how long taps and holds last
         lineOfNotes = noteReader.nextLine();
         for(int n = 0; n < rails; n++)
         {
            if(keysTapped.contains(n))
            {
               if(lineOfNotes.substring(n,n+1).equals("2"))
               {
                  notesToDrawPlaying.add(new Note(n*((WINDOWWIDTH - dividerWidth)/rails)+dividerWidth, topOfTrack, colors[n]))  ;     
               }
            }
            else if(keysHeld.contains(n))
            {
               if(lineOfNotes.substring(n,n+1).equals("1"))
               {
                  notesToDrawPlaying.add(new Note(n*((WINDOWWIDTH - dividerWidth)/rails)+dividerWidth, topOfTrack, Color.GRAY));
               }
            }
         }
         for(int n = 0; n < notesToDrawPlaying.size(); n++)
         {
            notesToDrawPlaying.get(n).increment(noteSpeed);
            if(notesToDrawPlaying.get(n).isBelowTrack(bottomOfTrack))
            {
               notesToDrawPlaying.remove(n);
            }
         }
         
      
      
      }
      else if(songFinished)
      {
         System.out.println("Song Finished.");
         if(editingSong)
         {
            noteWriter.close();
            modifySongFile(txtFileName);
         }
         else if(playingSong)
         {
            noteReader.close();
         }
      }
      bufferGraphics = (Graphics)bufferGraphics2D;
   }
   public void Draw()
   {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
      bufferGraphics.setColor(Color.BLACK);
      bufferGraphics.fillRect(0,0,WINDOWWIDTH,WINDOWHEIGHT);
      bufferGraphics.setColor(Color.WHITE);
      bufferGraphics.setFont(new Font("SansSeriff",Font.PLAIN,FONTSIZE));
      if(choosingPlayStyle)
      {
         if(ifTrueEditingSong)
            bufferGraphics.setColor(Color.WHITE);
         else
            bufferGraphics.setColor(Color.GRAY);
         bufferGraphics.drawString("Make Tap", WINDOWWIDTH/8,WINDOWHEIGHT/2);
         if(ifTrueEditingSong)
            bufferGraphics.setColor(Color.GRAY);
         else
            bufferGraphics.setColor(Color.WHITE);
         bufferGraphics.drawString("Play Tap", WINDOWWIDTH*6/8,WINDOWHEIGHT/2);
      }
      else if(choosingSong)
      {
         bufferGraphics.drawString("Choose A Song (Use the right and left arrow keys to navigate songs in your directory): " + songName.substring(0,songName.length()-4),0,WINDOWHEIGHT/4);
         
         // if(txtFiles.size() > txtDisplayPlace)
      //          {
      //             for(int n = 0; n < (3*WINDOWHEIGHT/4)/FONTSIZE; n++)
      //             {
      //                bufferGraphics.drawString(txtFiles.get(n+txtDisplayPlace),WINDOWWIDTH/2,WINDOWHEIGHT/4 + FONTSIZE*n);
      //             }
      //          }
      }
      else if(!startedSong)
      {
         bufferGraphics.drawString("Number of Rails? (12 Max)  "+numberOfRails,0,WINDOWHEIGHT/8);
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
      else
      {
         bufferGraphics.fillRect(0,topOfTrack, dividerWidth, WINDOWHEIGHT-topOfTrack);
         for(int n = 1; n < rails+1; n++)
         {
            bufferGraphics.fillRect(n*((WINDOWWIDTH - dividerWidth)/rails),topOfTrack,  dividerWidth, WINDOWHEIGHT-topOfTrack);
         }
         for(int n = 0; n < rails; n++)
         {
            bufferGraphics.setColor(colorsOfButtons[n]);
            bufferGraphics.fillRect((n*((WINDOWWIDTH - dividerWidth)/rails)) + dividerWidth, bottomOfTrack, ((WINDOWWIDTH - dividerWidth)/rails)-dividerWidth, WINDOWHEIGHT-bottomOfTrack);
         }
         // for(int n = 0; n < railKeys.size(); n++)
      //          {
      //             bufferGraphics.setFont(new Font("Arial",Font.PLAIN,40));
      //             bufferGraphics.drawString(railKeys.get(n),0,0);
      //          }
      
      }
      if(editingSong)
      {
         for(int n = 0; n < notesToDrawEditing.size(); n++)
         {
            bufferGraphics.setColor(notesToDrawEditing.get(n).getColor());
            bufferGraphics.fillRect(notesToDrawEditing.get(n).getX(),notesToDrawEditing.get(n).getY(),((WINDOWWIDTH - dividerWidth)/rails)-dividerWidth,noteThickness);
         }
      }
      else if(playingSong)
      {
         for(int n = 0; n < notesToDrawPlaying.size(); n++)
         {
            bufferGraphics.setColor(notesToDrawPlaying.get(n).getColor());
            bufferGraphics.fillRect(notesToDrawPlaying.get(n).getX(),notesToDrawPlaying.get(n).getY(),((WINDOWWIDTH - dividerWidth)/rails)-dividerWidth,noteThickness);
         }
      }
      else if(songFinished)
      {
      }        
            
      Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
      
   }

   public void DrawBackBufferToScreen()
   {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }
   public void getTxtFiles()
   {
      for(File f : directory.listFiles())
      {
         if(f.isFile() && f.getName().endsWith(".txt"))
            txtFiles.add(f.getName());
      }
   }
   public void getSongFiles()
   {
      for(File f : directory.listFiles())
         if(f.isFile() && (f.getName().endsWith(".mp3") || f.getName().endsWith(".m4a") || f.getName().endsWith(".wmv")))
            songFiles.add(f.getName());
   }
   public void modifySongFile(String sn)
   {
   
   }
   public String toString()
   {
      return ""+FONTSIZE;
   }
   public void keyPressed(KeyEvent e)
   {
      int keyCode = e.getKeyCode();
      if(choosingPlayStyle)
      {
         if(keyCode == KeyEvent.VK_LEFT)
         {
            ifTrueEditingSong = true;
         }
         if(keyCode == KeyEvent.VK_RIGHT)
         {
            ifTrueEditingSong = false;
         }
         else if(keyCode == KeyEvent.VK_ENTER)
         {
            if(ifTrueEditingSong)
               editingSong = true;
            else
               playingSong = true;
            choosingSong = true;
            choosingPlayStyle = false;
         }
      }
      else if(choosingSong)
      {
      //TO TYPE IN SONG NAME
      
         // if(keyCode == KeyEvent.VK_SHIFT)
         // {
            // upperCase = true;
         // }
         // else if(keyCode == KeyEvent.VK_ENTER)
         // {
                  // choosingSong = false;
                  // loadingSong = true;
         // }
         // else if(keyCode == KeyEvent.VK_BACK_SPACE)
         // {
            // if(songName.length() >= 1)
               // songName = songName.substring(0,songName.length()-1);
            // else
            // {
               // choosingSong = false;
               // choosingPlayStyle = true;
            // }
         // } 
         // else
         // {
            // char c = (char)keyCode;
            // if(upperCase)
            // {
               // songName += Character.toString(c);
            // }
            // else
            // {
               // songName += Character.toString(c).toLowerCase();
            // }
         // }  
         if(keyCode == KeyEvent.VK_ENTER)
         {
            choosingSong = false;
            loadingSong = true;
         }
         else if(keyCode == KeyEvent.VK_RIGHT)
         {
            if(txtDisplayPlace < txtFiles.size()-1)
               txtDisplayPlace++;
            else
               txtDisplayPlace = 0;
            if(playingSong)
               songName = txtFiles.get(txtDisplayPlace);
            else
               songName = songFiles.get(txtDisplayPlace);
         }
         else if(keyCode == KeyEvent.VK_LEFT)
         {
            if(txtDisplayPlace != 0)
               txtDisplayPlace--;
            else
               txtDisplayPlace = txtFiles.size()-1;
            if(playingSong)
               songName = txtFiles.get(txtDisplayPlace);
            else
               songName = songFiles.get(txtDisplayPlace);
         }
         else if(keyCode == KeyEvent.VK_BACK_SPACE)
         {
            choosingSong = false;
            choosingPlayStyle = true;
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
               rails = Integer.parseInt(numberOfRails);
               bottomOfTrack = WINDOWHEIGHT -((WINDOWWIDTH - 10*dividerWidth)/10); // "10"'s were previously "numberOfRails"
               noteSpeed = (bottomOfTrack - topOfTrack) / difficulty; // 160 frames until notes hit bottom
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
      else if(editingSong)
      {
         String s = Character.toString((char)keyCode);
         Integer railTemp = railKeys.indexOf(s);
         if(railKeys.contains(s))
         {
            if(!keysTapped.contains(railTemp) && !keysHeld.contains(railTemp))
            {
               keysTapped.add(railTemp);
               colorsOfButtons[railTemp] = colorsOfButtons[railTemp].brighter();
            }
            if(!keysHeld.contains(railTemp))
            {
               keysHeld.add(railTemp);
            }
         }
      }
      else if(playingSong)
      {
         String s = Character.toString((char)keyCode);
         Integer railTemp = railKeys.indexOf(s);
         if(railKeys.contains(s))
         {
         
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
      if(choosingSong)
      {
         if(keyCode == KeyEvent.VK_SHIFT)
         {
            upperCase = false;
         }
      //keys.remove(new Integer(e.getKeyCode()));
      }
      else if(editingSong)
      {
         Integer railTemp = (railKeys.indexOf(Character.toString((char)keyCode)));
         if(railTemp != -1)
         {
            keysHeld.remove(railTemp);
            colorsOfButtons[railTemp] = colorsOfButtons[railTemp].darker();
         }
      }
   }
   public void keyTyped(KeyEvent e)
   {
   }
}