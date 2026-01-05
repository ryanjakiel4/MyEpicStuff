import java.awt.geom.*;
	import java.awt.Rectangle;
   public class Enemy
   {
      private int size, speed, health, exp;
      public int x,y;
      public Enemy(int s1, int s2, int xx, int yy, int h,int e)
      {
         size=s1;
         speed = s2;
         x = xx;
         y=yy;
			health = h;
			exp = e;
      }
      public Ellipse2D.Double toCirc()
      {
         return new Ellipse2D.Double(x,y,size,size);
      }
		public Rectangle toRect()
		{
		return new Rectangle(x,y,size,size);
		}
      public void setLocation(Rectangle hero)
      {
         int steve = 1;
         boolean ryan = true;
         int xx = (hero.x+(hero.height/2)-size/2) - x;
         int yy = (hero.y+(hero.height/2)-size/2) - y;
         while(ryan)
         {
            if(xx / steve < speed && xx/steve > speed*-1 && yy/steve < speed && yy/steve > speed*-1)
            {
               x += xx/steve;
               y += yy/steve;
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
   }