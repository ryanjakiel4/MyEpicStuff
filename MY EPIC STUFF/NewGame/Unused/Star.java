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
public class Star
{
private int myX, myY, mySize;
public Star(int x, int y, int size)
{
myX = x;
myY = y;
mySize = size;
}
public void setY(int y)
{
int newY = y/2 + 1;
myY += newY;
}
public void drawStar(Graphics g)
{
g.fillOval(myX,myY,mySize,mySize);
}
}