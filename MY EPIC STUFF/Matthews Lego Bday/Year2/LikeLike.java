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
public class LikeLike
{
   private BufferedImage like;
   private BufferedImage linkInLike;
   private int speed;
   private int myX;
   private int myY;
   private int platformLeftEdge;
   private int platformRightEdge;
   private String enemyDir = "e";
   public LikeLike(int x, int  y, int s, int le, int re)
   {
      try
      {
         like = ImageIO.read(getClass().getResourceAsStream("LikeLike.png"));
         linkInLike = ImageIO.read(getClass().getResourceAsStream("linkInLikeLike.png"));
      }
      catch (IOException e) {
      }
      myX = x;
      myY = y - like.getHeight();
      speed = s;
      platformLeftEdge = le;
      platformRightEdge = re;
   }
   public boolean makeMove(int hx, int hy, int platformSpeed, int heroLength) // int[] is [?,?,?,?] where the first is the Y of the platform, then the next two are the left and right X endpoints, and the last is whether the hero touched the platform or not
   {
      if((myX + like.getWidth()) > (hx + heroLength/2))
      {
         if((myX - speed) > platformLeftEdge)
         {
            myX -= speed;
         }
         else
         {
            myX = platformLeftEdge;
         }
         enemyDir = "w";
      }
      else
      {
         if((myX + speed + like.getWidth()) < platformRightEdge)
         {
            myX += speed;
         }
         else
         {
            myX = platformRightEdge - like.getWidth();
         }
         enemyDir = "e";
      }
      myY += platformSpeed;
      boolean collision;
      if(enemyDir == "e")
      {
         if((myX + like.getWidth() - hx) >= 0 && (Math.abs((myY-linkInLike.getHeight()-10) - hy) < 7))
         {
            collision = true;
         }
         else
         {
            collision = false;
         }
      }
      else
      {
         if((myX - hx - heroLength) <= 0 && (Math.abs((myY-linkInLike.getHeight()-10) - hy) < 7))
         {
            collision = true;
         }
         else
         {
            collision = false;
         }
      }
      return collision;
   }
   public void drawMove(Graphics g, boolean collision, ImageObserver observer)
   {
      if(collision)
      {
         g.drawImage(linkInLike, myX, myY-linkInLike.getHeight()-10, observer);
      }
      else
      {
         g.drawImage(like, myX, myY-like.getHeight()-10, observer);
      }
   }
   public int getX()
   {
      return myX;
   }
   public int getY()
   {
      return myY;
   }
}