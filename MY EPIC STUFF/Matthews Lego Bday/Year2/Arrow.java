public class Arrow
{
   private String type;
   private int ww, wh;
   private int s;
   private int x;
   private int y;
   private int dx;
   private int dy;
   private int xConstant;
   private int yConstant;
   public Arrow(String t, int speed, int w, int h, int xc, int yc, int scale)
   {
      type = t;
      ww = w;
      wh = h;
      s = scale;
      xConstant = xc;
      yConstant = yc;
      if(type.equals("up"))
      {
         x = ww/2 - ww/(2*s) + xc;
         y = 0;
         dx = 0;
         dy = speed;
      }
      else if(type.equals("down"))
      {
         x = ww/2 - ww/(2*s) + xc;
         y = wh + yc;
         dx = 0;
         dy = -1*speed;
      }
      else if(type.equals("left"))
      {
         x = 0;
         y = wh/2 - wh/(2*s) + yc;
         dx = speed;
         dy = 0;
      }
      else if(type.equals("right"))
      {
         x = ww + xc;
         y = wh/2 - wh/(2*s) + yc;
         dx = -1*speed;
         dy = 0;
      }
   }
   public int getX(){
      return x;}
   public int getY(){
      return y;}
   public boolean increment()
   {
      x+=dx;
      y+=dy; 
      if(type.equals("up"))
      {
         if(y > (wh/2 - wh/s - wh/(2*s)+yConstant))
         {
            y=(wh/2 - wh/s - wh/(2*s)+yConstant)-4;
            dy=1;
         }
      }
      if(type.equals("down"))
      {
         if(y < (wh/2 + wh/(2*s)+yConstant))
         {
            y = (wh/2 + wh/(2*s)+yConstant) + 4;
            dy= -1;
         }
      }
      if(type.equals("left"))
      {
         if(x > (ww/2 - ww/s - ww/(2*s)+xConstant))
         {
            x=(ww/2 - ww/s - ww/(2*s)+xConstant)-4; 
            dx=1;
         }
      }
      if(type.equals("right"))
      {
         if(x < (ww/2 + ww/(2*s)+xConstant))
         {
            x = (ww/2 + ww/(2*s)+xConstant)+4;
            dx= -1;
         }
      }
      return isOut();
   }
   public boolean isOut()
   {
      if(type.equals("up"))
      {
         if(y == (wh/2 - wh/s - wh/(2*s)+yConstant))
            return true;
         return false;
      }
      if(type.equals("down"))
      {
         if(y == (wh/2 + wh/(2*s)+yConstant))
            return true;
         return false;
      }
      if(type.equals("left"))
      {
         if(x == (ww/2 - ww/s - ww/(2*s)+xConstant))
            return true;
         return false;
      }
      if(type.equals("right"))
      {
         if(x == (ww/2 + ww/(2*s)+xConstant))
            return true;
         return false;
      }
      return false;
   }
   public void recalibrate(String t, int speed, int w, int h, int xc, int yc, int scale)
   {
      type = t;
      ww = w;
      wh = h;
      s = scale;
      xConstant = xc;
      yConstant = yc;
      if(type.equals("up"))
      {
         x = ww/2 - ww/(2*s) + xc;
         y = 0;
         dx = 0;
         dy = speed;
      }
      else if(type.equals("down"))
      {
         x = ww/2 - ww/(2*s) + xc;
         y = wh + yc;
         dx = 0;
         dy = -1*speed;
      }
      else if(type.equals("left"))
      {
         x = 0;
         y = wh/2 - wh/(2*s) + yc;
         dx = speed;
         dy = 0;
      }
      else if(type.equals("right"))
      {
         x = ww + xc;
         y = wh/2 - wh/(2*s);
         dx = -1*speed;
         dy = 0;
      }
   }
}
