   import java.awt.geom.*;
   import java.awt.Rectangle;
   public class Missle
   {
      private String direction;
      private int size, speed;
      public int x,y;
      private Enemy enemy;
      public Missle(int s1, int s2, int xx, int yy, String d)
      {
         size=s1;
         speed = s2;
         x = xx;
         y=yy;
         direction = d;
      }
      public Ellipse2D.Double toCirc()
      {
         return new Ellipse2D.Double(x,y,size,size);
      }
      public Rectangle toRect()
      {
         return new Rectangle(x,y,size,size);
      }
      public void setLocation()
      {
         boolean ryan = true;
         enemy = getClosestEnemy(toCirc());
         Rectangle hero = enemy.toRect();
         int steve = 1;
         int xx = (hero.x+(hero.height/2)-size/2) - x;
         int yy = (hero.y+(hero.height/2)-size/2) - y;
         while(ryan)
         {
            if(xx / steve < speed && xx/steve > speed*-1 && yy/steve < speed && yy/steve > speed*-1)
            {
               if(direction.equals("n"))
                  y += yy/steve;
               else if(direction.equals("s"))
                  y += yy/steve;
               else
                  y += yy/steve;
               if(direction.equals("e"))
                  x += xx/steve;
               else if(direction.equals("w"))
                  x += xx/steve;
               else
                  x += xx/steve;
               if(LevelEditor.collision(x,y,size))
               {
                  if(LevelEditor.collision(x,y-(yy/steve),size))
                     x-=xx/steve;
                  if(LevelEditor.collision(x-(xx/steve),y,size))
                     y-=yy/steve;
               }
               ryan = false;
            }
            steve++;
         }
      }
      public Enemy getClosestEnemy(Ellipse2D.Double eli)
      {
         Enemy closest = null;
         for(int frog = 0; frog < LevelEditor.enemies.size(); frog++)
         {
            if(closest == null || (Math.abs(LevelEditor.enemies.get(frog).x - eli.x) < Math.abs(closest.x - eli.x) && Math.abs(LevelEditor.enemies.get(frog).y - eli.y) < Math.abs(closest.y - eli.y)))
            {
               closest = LevelEditor.enemies.get(frog);
            }
         }
         return closest;
      }
   }