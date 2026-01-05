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

   public class LevelEditor extends Canvas
   implements KeyListener, Runnable
   {
      private int amountOfMissles = 4, missleSpeed = 5;
      private int missleSize = 7;
      private ArrayList<Missle> missleArray = new ArrayList<Missle>();
      private int realLL = 150, realLW = 2;
      private boolean boss = false;
      public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
      private int level = 1;
      private int waldo = 0, deltaW = 1, deltaL = 2;
      private int laserW = 1, laserL = 150, windL = 60, windW = 30, tempLW, tempLL, tempMS, tempWW=windW, tempWL=windL;
      private static int x = 900, y = 400,size = 40;
      private int windX, windY, windXa, windYa, windXb, windYb;
      private ArrayList<Integer> keys = new ArrayList<Integer>();
      private static Graphics bufferGraphics = null;
      private BufferStrategy bufferStrategy = null;
      private boolean running,n=true,s=true,e=true,w=true,windR = false, w1 = false , w2 = false , w3 = false , w4 = false,windRa = false, w1a = false , w2a = false , w3a = false , w4a = false,windRb = false, w1b = false , w2b = false , w3b = false , w4b = false, laserR = false;
      private Thread thread;
      private boolean wind,laser, missle, missleR = false;
      private String direction="n";
      private double start;
      private int r=0, count = 90;
      private int speed = 5;
      private int laserTime = 0;
      private static Rectangle hero = new Rectangle (x,y,size,size);
      private static Rectangle a = new Rectangle(200,650,200,40);
      private static Rectangle b =new Rectangle(400,650,40,240);
      private static Rectangle c = new Rectangle(200,850,200,40);
      private static Rectangle d = new Rectangle(375, 275, 40, 200);
      private static Rectangle g = new Rectangle(650,490,100,40);
      private static Rectangle f = new Rectangle(850,490,100,40);
      private static Rectangle h = new Rectangle(1400, 250,200,40);
      private static Rectangle i = new Rectangle(1250, 650, 40, 200);
      private static Rectangle j = new Rectangle(1450,650, 40, 200);
      private static Rectangle windRect = null, windRect1 = null, windRect2 = null;
      private static ArrayList<Rectangle> laserRect = new ArrayList<Rectangle>();
   	
      public LevelEditor(Dimension size)
      {
         this.setPreferredSize(size);
         addKeyListener(this);
      	
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
         
         for(int p = 0; p < level; p++)
         {
            if (!boss)
            {
               if(Math.random() < .0005)
               {
                  if(Math.random() < .5)
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(20,3,0,(int)(Math.random()*1080)-20,8*level, 4*level));
                     else
                        enemies.add(new Enemy(20,3,1850-20,(int)(Math.random()*1080)-20,8*level, 4*level));
                  }
                  else
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(20,3,(int)(Math.random() * 1850)-20,0,8*level, 4*level));
                     else
                        enemies.add(new Enemy(20,3,(int)(Math.random() * 1850)-20,1080-20,8*level, 4*level));
                  }
               }
               if(Math.random() < .0005)
               {
                  if(Math.random() < .5)
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(30,3,0,(int)(Math.random()*1080)-30,10*level, 5*level));
                     else
                        enemies.add(new Enemy(30,3,1850-30,(int)(Math.random()*1080)-30, 10*level, 5*level));
                  }
                  else
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(30,3,(int)(Math.random() * 1850)-30,0,10*level, 5*level));
                     else
                        enemies.add(new Enemy(30,3,(int)(Math.random() * 1850)-30,1080-30,10*level, 5*level));
                  }
               }
               if(Math.random() < .0005)
               {
                  if(Math.random() < .5)
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(10,4,0,(int)(Math.random()*1080)-10,3*level,2*level));
                     else
                        enemies.add(new Enemy(10,4,1850-20,(int)(Math.random()*1080)-10,3*level,2*level));
                  }
                  else
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(10,4,(int)(Math.random() * 1850)-10,0,3*level,2*level));
                     else
                        enemies.add(new Enemy(10,4,(int)(Math.random() * 1850)-10,1080-10,3*level,2*level));
                  }
               }
               if(Math.random() < .0005)
               {
                  if(Math.random() < .5)
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(50,2,0,(int)(Math.random()*1080)-50,30*level,7*level));
                     else
                        enemies.add(new Enemy(50,2,1850-50,(int)(Math.random()*1080)-50,30*level,7*level));
                  }
                  else
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(50,2,(int)(Math.random() * 1850)-50,0,30*level,5*level));
                     else
                        enemies.add(new Enemy(50,2,(int)(Math.random() * 1850)-50,1080-50,30*level,5*level));
                  }
               }
            
               if(Math.random() < .00005)
               {
                  boss = true;
                  if(Math.random() < .5)
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(120,2,0,(int)(Math.random()*1080)-120,50*level,20*level));
                     else
                        enemies.add(new Enemy(120,2,1850-120,(int)(Math.random()*1080)-120,50*level,20*level));
                  }
                  else
                  {
                     if(Math.random() < .5)
                        enemies.add(new Enemy(120,2,(int)(Math.random() * 1850)-120,0,50*level,20*level));
                     else
                        enemies.add(new Enemy(120,2,(int)(Math.random() * 1850)-120,1080-120,50*level,20*level));
                  }
               }      
            }
         }
         if(keys.contains(new Integer(KeyEvent.VK_UP)))
         {
            if(collision(x,y-speed,size))
               n =false;
            else
               n=true;
            if(n)
            {
               if(y-speed >= 0 )
                  y-=speed;
               else
                  y=0;
               
            }
            direction = "n";
            
         }
         if(keys.contains(new Integer(KeyEvent.VK_DOWN)))
         {
            if(collision(x,y+speed,size))
               s=false;
            else
               s=true;
            if(s)
            {
               if(y+speed+size <= this.getSize().height)
                  y+=speed;
               else
                  y=this.getSize().height-size;
               
            }
            direction = "s";
            
         }
         if(keys.contains(new Integer(KeyEvent.VK_LEFT)))
         {
            if(collision(x-speed,y,size))
               w=false;
            else
               w=true;
            if(w)
            {
               if(x - speed >= 0)
                  x-=speed;
               else
                  x =0;
               
            }
            direction = "w";
            
         }
         if(keys.contains(new Integer(KeyEvent.VK_RIGHT)))
         {
            if(collision(x+speed,y,size))
               e=false;
            else
               e=true;
            if(e)
            {
               if(x + speed + size< this.getSize().width)
                  x+=speed;
               else
                  x = this.getSize().width-size;
               
            }
            direction = "e";
            
         }
         if(keys.contains(new Integer(KeyEvent.VK_X)))
         {
            if(laserW < 200)
               if(waldo % 5 ==0)
                  laserW+=deltaW;
            if(laserL < 3000)
               if(waldo%2 == 0)
                  laserL+=deltaL;
            waldo++;
         }
         bufferGraphics = (Graphics)bufferGraphics2D;
      }
      public void Draw()
      {
         bufferGraphics = bufferStrategy.getDrawGraphics();
         try
         {
            bufferGraphics.clearRect(0,0,this.getSize().width, this.getSize().height);
            Graphics2D bufferGraphics2D = (Graphics2D)bufferGraphics;
            hero.setLocation(x,y);
            for(int me = 0; me < enemies.size(); me++)
               enemies.get(me).setLocation(hero);
            bufferGraphics2D.fill(a);
            bufferGraphics2D.fill(b);
            bufferGraphics2D.fill(c);
            bufferGraphics2D.fill(d);
            bufferGraphics2D.fill(g);
            bufferGraphics2D.fill(f);
            bufferGraphics2D.fill(h);
            bufferGraphics2D.fill(i);
            bufferGraphics2D.fill(j);
            bufferGraphics2D.setColor(Color.YELLOW.darker());
            bufferGraphics2D.fill(hero);
            bufferGraphics2D.setColor(Color.RED.darker());
            for(int you = 0; you < enemies.size(); you++)
               bufferGraphics2D.fill(enemies.get(you).toCirc());
            bufferGraphics2D.setColor(Color.GREEN.darker().darker());
            if(wind)
            {
               drawWind();
            }
            bufferGraphics2D.setColor(Color.BLUE);
            if(laser)
            {
               drawLaser();  
            }      
            bufferGraphics2D.setColor(Color.MAGENTA.darker());
            if(missle)
            {
               drawMissle();
            }	
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
      public void keyPressed(KeyEvent e)
      {
         int keyCode = e.getKeyCode();
      
         if(!keys.contains(keyCode))
            keys.add(new Integer(keyCode));
         if(keyCode == KeyEvent.VK_Z)
         {
            wind = true;
         }
         if(keyCode == KeyEvent.VK_X)
            laser = true;
         if(keyCode == KeyEvent.VK_C)
            missle = true;
      }
      public void keyReleased(KeyEvent e)
      {
         keys.remove(new Integer(e.getKeyCode()));
         if(e.getKeyCode() == KeyEvent.VK_Z)
         {
            if((!w1b && !w2b &&!w3b&&!w4b))
               windRb = true;
            else if((!w1a && !w2a &&!w3a&&!w4a))
               windRa = true;
            else if(!w1 && !w2 &&!w3&&!w4)
               windR = true;
            
         }
         if(e.getKeyCode() == KeyEvent.VK_X)
            laserR = true;
         if(e.getKeyCode() == KeyEvent.VK_C)
            addMissle();
      }
      public void keyTyped(KeyEvent e)
      {
      }
      public static boolean collision(int ab, int bc, int mm)
      {
         Graphics2D bufferGraphics2D = (Graphics2D) bufferGraphics;
         return bufferGraphics2D.hit(new Rectangle(ab, bc,mm,mm), a, true)||bufferGraphics2D.hit(new Rectangle(ab, bc,mm,mm), c, true)||bufferGraphics2D.hit(new Rectangle(ab,bc,mm,mm), b, true)||bufferGraphics2D.hit(new Rectangle(ab,bc,mm,mm), d, true)||bufferGraphics2D.hit(new Rectangle(ab,bc,mm,mm), f, true)||bufferGraphics2D.hit(new Rectangle(ab,bc,mm,mm), g, true)||bufferGraphics2D.hit(new Rectangle(ab,bc,mm,mm), h, true)||bufferGraphics2D.hit(new Rectangle(ab,bc,mm,mm), i, true)||bufferGraphics2D.hit(new Rectangle(ab,bc,mm,mm), j, true);
      }
      public void drawWind()
      {
         Graphics2D bufferGraphics2D = (Graphics2D) bufferGraphics;
         if(windR)
         {
            if(direction.equals("n"))
            {
               windX = x+20;
               windY = y-5;
               w1 = true;
               windR=false;
            }
            else if(direction.equals("s"))
            { 
               windX = x+20;
               windY = y+45;
               w2 = true;
               windR = false;
            }
            else if(direction.equals("e"))
            {
               windX = x+45;
               windY = y+20;
               w3 = true;
               windR = false;
            }
            else if(direction.equals("w"))
            {
               windX = x-5;
               windY = y+20;
               w4 = true;
               windR = false;
            }
         	
         }
         if(w1)
         {
            bufferGraphics2D.drawLine(windX-tempWL/2,windY+tempWW,windX,windY);
            bufferGraphics2D.drawLine(windX,windY,windX+tempWL/2,windY+tempWW);
            windY-=7;
            if(windY < 0)
               w1 = false;
            windRect = new Rectangle(windX-tempWL/2,windY,tempWL,tempWW);
         }
         if(w2)
         {
            bufferGraphics2D.drawLine(windX-tempWL/2,windY-tempWW,windX,windY);
            bufferGraphics2D.drawLine(windX+tempWL/2,windY-tempWW,windX,windY);
            windY+=7;
            if(windY > 1080)
               w2 = false;
            windRect = new Rectangle(windX-tempWW,windY-tempWL/2,tempWL,tempWW);
         }
         if(w3)
         {
            bufferGraphics2D.drawLine(windX-tempWW,windY-tempWL/2,windX,windY);
            bufferGraphics2D.drawLine(windX,windY,windX-tempWW,windY+tempWL/2);
            windX+=7;
            if(windX > 1980)
               w3 = false;
            windRect = new Rectangle(windX-tempWW,windY-tempWL/2,tempWW,tempWL);
         }
         if(w4)
         {
            bufferGraphics2D.drawLine(windX+tempWW,windY-tempWL/2,windX,windY);
            bufferGraphics2D.drawLine(windX+tempWW,windY+tempWL/2,windX,windY);
            windX-=7;
            if(windX < 0)
               w4= false;
            windRect = new Rectangle(windX,windY-tempWL/2,tempWW,tempWL);
         }
            
         	
         if(windRa)
         {
            if(direction.equals("n"))
            {
               windXa = x+20;
               windYa = y-5;
               w1a = true;
               windRa=false;
            }
            else if(direction.equals("s"))
            { 
               windXa = x+20;
               windYa = y+45;
               w2a = true;
               windRa = false;
            }
            else if(direction.equals("e"))
            {
               windXa = x+45;
               windYa = y+20;
               w3a = true;
               windRa = false;
            }
            else if(direction.equals("w"))
            {
               windXa = x-5;
               windYa = y+20;
               w4a = true;
               windRa = false;
            }
         }
         if(w1a)
         {
            bufferGraphics2D.drawLine(windXa-tempWL/2,windYa+tempWW,windXa,windYa);
            bufferGraphics2D.drawLine(windXa,windYa,windXa+tempWL/2,windYa+tempWW);
            windYa-=7;
            if(windYa < 0)
               w1a = false;
            windRect1 = new Rectangle(windXa-tempWL/2,windYa,tempWL,tempWW);
         }
         if(w2a)
         {
            bufferGraphics2D.drawLine(windXa-tempWL/2,windYa-tempWW,windXa,windYa);
            bufferGraphics2D.drawLine(windXa+tempWL/2,windYa-tempWW,windXa,windYa);
            windYa+=7;
            if(windYa > 1080)
               w2a = false;
            windRect1 = new Rectangle(windXa-tempWW,windYa-tempWL/2,tempWL,tempWW);
         }
         if(w3a)
         {
            bufferGraphics2D.drawLine(windXa-tempWW,windYa-tempWL/2,windXa,windYa);
            bufferGraphics2D.drawLine(windXa,windYa,windXa-tempWW,windYa+tempWL/2);
            windXa+=7;
            if(windXa > 1980)
               w3a = false;
            windRect1 = new Rectangle(windXa-tempWW,windYa-tempWL/2,tempWW,tempWL);
         }
         if(w4a)
         {
            bufferGraphics2D.drawLine(windXa+tempWW,windYa-tempWL/2,windXa,windYa);
            bufferGraphics2D.drawLine(windXa+tempWW,windYa+tempWL/2,windXa,windYa);
            windXa-=7;
            if(windXa < 0)
               w4a= false;
            windRect1 = new Rectangle(windXa,windYa-tempWL/2,tempWW,tempWL);
         }
            
         	
         if(windRb)
         {
            if(direction.equals("n"))
            {
               windXb = x+20;
               windYb = y-5;
               w1b = true;
               windRb=false;
            }
            else if(direction.equals("s"))
            { 
               windXb = x+20;
               windYb= y+45;
               w2b = true;
               windRb = false;
            }
            else if(direction.equals("e"))
            {
               windXb = x+45;
               windYb = y+20;
               w3b = true;
               windRb = false;
            }
            else if(direction.equals("w"))
            {
               windXb = x-5;
               windYb = y+20;
               w4b = true;
               windRb = false;
            }
         }
         if(w1b)
         {
            bufferGraphics2D.drawLine(windXb-tempWL/2,windYb+tempWW,windXb,windYb);
            bufferGraphics2D.drawLine(windXb,windYb,windXb+tempWL/2,windYb+tempWW);
            windYb-=7;
            if(windYb < 0)
               w1b = false;
            windRect2 = new Rectangle(windXb-tempWL/2,windYb,tempWL,tempWW);
         }
         if(w2b)
         {
            bufferGraphics2D.drawLine(windXb-tempWL/2,windYb-tempWW,windXb,windYb);
            bufferGraphics2D.drawLine(windXb+tempWL/2,windYb-tempWW,windXb,windYb);
            windYb+=7;
            if(windYb > 1080)
               w2b = false;
            windRect2 = new Rectangle(windXb-tempWW,windYb-tempWL/2,tempWL,tempWW);
         }
         if(w3b)
         {
            bufferGraphics2D.drawLine(windXb-tempWW,windYb-tempWL/2,windXb,windYb);
            bufferGraphics2D.drawLine(windXb,windYb,windXb-tempWW,windYb+tempWL/2);
            windXb+=7;
            if(windXb > 1980)
               w3b = false;
            windRect2 = new Rectangle(windXb-tempWW,windYb-tempWL/2,tempWW,tempWL);
         }
         if(w4b)
         {
            bufferGraphics2D.drawLine(windXb+tempWW,windYb-tempWL/2,windXb,windYb);
            bufferGraphics2D.drawLine(windXb+tempWW,windYb+tempWL/2,windXb,windYb);
            windXb-=7;
            if(windXb < 0)
               w4b= false;
            windRect2 = new Rectangle(windXb,windYb-tempWL/2,tempWW,tempWL);
         }
      }
      public void drawLaser()
      {
         Graphics2D bufferGraphics2D = (Graphics2D) bufferGraphics;
         if(laserR)
         {
            if(direction.equals("n"))
            {
               for(int help = 0; help < laserW; help++)
                  laserRect.add(new Rectangle(x+(size/2)-(laserW/2)+help, y-laserL,1,laserL));
               
               for(int help = 0; help < laserRect.size(); help++)
                  bufferGraphics2D.fill(laserRect.remove(help));
            	//laserRect = new Rectangle(x+(size/2)-(laserW/2), y-laserL,laserW,laserL);
               //bufferGraphics2D.fillRect(x+(size/2)-(laserW/2), y-laserL,laserW,laserL);
            }
            else if(direction.equals("s"))
            { 
               for(int help = 0; help < laserW; help++)
                  laserRect.add(new Rectangle(x+(size/2)-(laserW/2)+help, y+size,1,laserL));
               for(int help = 0; help < laserRect.size(); help++)
                  bufferGraphics2D.fill(laserRect.remove(help));
               //laserRect = new Rectangle(x+(size/2)-(laserW/2),y+size,laserW,laserL);
               //bufferGraphics2D.fillRect(x+(size/2)-(laserW/2),y+size,laserW,laserL);
            }
            else if(direction.equals("e"))
            {
               for(int help = 0; help < laserW; help++)
                  laserRect.add(new Rectangle(x+size, y+(size/2)-(laserW/2)+help,laserL,1));
               for(int help = 0; help < laserRect.size(); help++)
                  bufferGraphics2D.fill(laserRect.remove(help));
               //laserRect = new Rectangle(x+size,y+(size/2)-(laserW/2),laserL,laserW);
               //bufferGraphics2D.fillRect(x+size,y+(size/2)-(laserW/2),laserL,laserW);
            }
            else if(direction.equals("w"))
            {
               for(int help = 0; help < laserW; help++)
                  laserRect.add(new Rectangle(x-laserL, y+(size/2)-(laserW/2)+help,laserL,1));
               for(int help = 0; help < laserRect.size(); help++)
                  bufferGraphics2D.fill(laserRect.remove(help));
               //laserRect = new Rectangle(x-laserL, y+(size/2)-(laserW/2), laserL,laserW);
               //bufferGraphics2D.fillRect(x-laserL, y+(size/2)-(laserW/2), laserL,laserW);
            }   
            laserTime++;
         }
         if(laserTime > 5)
         {
            laserR = false;
            laserTime = 0;
            laserW = realLW;
            laserL = realLL;
            laserRect = new ArrayList<Rectangle>();
         }
      }
      public void addMissle()
      {
         if(direction.equals("n"))
         {
            if(missleArray.size() < amountOfMissles)
               missleArray.add(new Missle(missleSize, missleSpeed,x+size/2,y,"n"));
         }
         if(direction.equals("s"))
         {
            if(missleArray.size() < amountOfMissles)
               missleArray.add(new Missle(missleSize, missleSpeed,x+size/2,y+size,"s"));
         }
         if(direction.equals("e"))
         {
            if(missleArray.size() < amountOfMissles)
               missleArray.add(new Missle(missleSize, missleSpeed,x+size,y+size/2,"e"));
         }
         if(direction.equals("w"))
         {
            if(missleArray.size() < amountOfMissles)
               missleArray.add(new Missle(missleSize, missleSpeed, x,y+size/2,"w"));
         }
      }
      public void drawMissle()
      {
         Graphics2D bufferGraphics2D = (Graphics2D) bufferGraphics;
      
         for(int penguin = 0; penguin < missleArray.size(); penguin++)
         {
            System.out.println(missleArray);
            missleArray.get(penguin).setLocation();
            bufferGraphics2D.fill(missleArray.get(penguin).toCirc());
            if ((missleArray.get(penguin).x == missleArray.get(penguin).getEnemy().x) && (missleArray.get(penguin).y == missleArray.get(penguin).getEnemy().y))
            {
               missleArray.remove(penguin);
            }
            	
         }
         
      }
      public Enemy getClosestEnemy(Ellipse2D.Double eli)
      {
         Enemy closest = null;
         for(int frog = 0; frog < enemies.size(); frog++)
         {
            if(closest == null || (Math.abs(enemies.get(frog).x - eli.x) < Math.abs(closest.x - eli.x) && Math.abs(enemies.get(frog).y - eli.y) < Math.abs(closest.y - eli.y)))
            {
               closest = enemies.get(frog);
            }
         }
         return closest;
      }
   }