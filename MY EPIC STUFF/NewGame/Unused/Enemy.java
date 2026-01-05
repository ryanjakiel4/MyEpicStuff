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
import javax.swing.border.EmptyBorder;
public abstract class Enemy
{
public abstract boolean makeMove(int hx, int hy, int platformSpeed, int heroLength);
public abstract void drawMove(Graphics g, boolean collision, ImageObserver observer);
public abstract int getX();
public abstract int getY();
}