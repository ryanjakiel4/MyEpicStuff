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

public class Ork {
    private BufferedImage[] orkShootRight = new BufferedImage[4];
    private BufferedImage[] orkShootLeft = new BufferedImage[4];
    private BufferedImage missle;
    private int missleSpeed;
    private int myX;
    private int myY;
    private int platformLeftEdge;
    private int platformRightEdge;
    private String enemyDir;
    private ArrayList<Integer> missleX = new ArrayList<Integer>();
    private ArrayList<String> missleDir = new ArrayList<String>();
    private int shootingCount = 0;
    private boolean pause = false;
    private int generalCount = 0, pauseBegin;

    public Ork(int x, int y, int s, int le, int re) {
        try {
            orkShootRight[0] = ImageIO.read(new File("oRight1.png"));
            orkShootRight[1] = ImageIO.read(new File("oRight2.png"));
            orkShootRight[2] = ImageIO.read(new File("oRight3.png"));
            orkShootRight[3] = ImageIO.read(new File("oRight4.png"));
            orkShootLeft[0] = ImageIO.read(new File("oLeft1.png"));
            orkShootLeft[1] = ImageIO.read(new File("oLeft2.png"));
            orkShootLeft[2] = ImageIO.read(new File("oLeft3.png"));
            orkShootLeft[3] = ImageIO.read(new File("oLeft4.png"));
            missle = ImageIO.read(new File("oMissle.png"));
        } catch (IOException e) {
        }
        myX = x;
        myY = y - orkShootRight[1].getHeight() * 3 - 10;
        missleSpeed = s;
        platformLeftEdge = le;
        platformRightEdge = re;
    }

    public int makeMove(int hx, int hy, int platformSpeed, int heroLength, boolean swingingSword) {
        myY += platformSpeed;
        if ((hx + heroLength / 2) > (myX + 10)) {
            enemyDir = "e";
        } else {
            enemyDir = "w";
        }
        if (!pause) {
            if (shootingCount < 3) {
                if (generalCount % 6 == 0)
                    shootingCount++;
            } else {
                pause = true;
                shootingCount = 0;
                pauseBegin = generalCount;
            }
        }
        if (generalCount - pauseBegin > 250)
            pause = false;
        generalCount++;

        if (shootingCount == 3) {
            if (enemyDir.equals("e")) {
                missleX.add(myX + orkShootRight[3].getWidth());
                missleDir.add("e");
            } else {
                missleX.add(myX);
                missleDir.add("w");
            }
        }
        if (enemyDir.equals("e")) {
            if (Math.abs(hx + heroLength / 2 - (myX + orkShootRight[shootingCount % 3 + 1].getWidth() / 2)) < 40
                    && Math.abs(myY - hy) < 15 && swingingSword)
                return 2;
            else if (Math.abs(hx + heroLength / 2 - (myX + orkShootRight[shootingCount % 3 + 1].getWidth() / 2)) < 7
                    && Math.abs(myY - hy) < 15)
                return 1;
        } else if (Math.abs(myX + orkShootRight[shootingCount % 3 + 1].getWidth() / 2 - (hx + heroLength / 2)) < 40
                && Math.abs(myY - hy) < 15 && swingingSword)
            return 2;
        else if (Math.abs(myX + orkShootRight[shootingCount % 3 + 1].getWidth() / 2 - (hx + heroLength / 2)) < 7
                && Math.abs(myY - hy) < 15)
            return 1;
        for (int n = 0; n < missleX.size(); n++) {
            if (missleDir.get(n).equals("e")) {
                if (Math.abs(hx - (missleX.get(n) + missle.getWidth())) < missleSpeed + 5 && Math.abs(hy - myY) < 15) {
                    missleX.remove(n);
                    missleDir.remove(n);
                    return 3;
                } else if (missleX.get(n) > 539) {
                    missleX.remove(n);
                    missleDir.remove(n);
                } else {
                    missleX.set(n, missleX.get(n) + missleSpeed);
                }
            } else {
                if (Math.abs(missleX.get(n) - (hx + heroLength)) < missleSpeed + 5 && Math.abs(hy - myY) < 15) {
                    missleX.remove(n);
                    missleDir.remove(n);
                    return 3;
                } else if (missleX.get(n) < -9) {
                    missleX.remove(n);
                    missleDir.remove(n);
                } else {
                    missleX.set(n, missleX.get(n) - missleSpeed);
                }
            }
        }
        return 0;
    }

    public void drawMove(Graphics g, ImageObserver observer) {
        if (enemyDir.equals("e")) {
            g.drawImage(orkShootRight[shootingCount % 3 + 1], myX, myY, observer);
        } else {
            g.drawImage(orkShootLeft[shootingCount % 3 + 1], myX, myY, observer);
        }
        for (int n = 0; n < missleX.size(); n++) {
            g.drawImage(missle, missleX.get(n), myY, observer);
        }
    }

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }
}