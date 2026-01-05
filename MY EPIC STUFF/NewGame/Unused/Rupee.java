public class Rupee
{
   private int value;
   private int x;
   private int y;
   public Rupee(int tx, int ty, int level)
   {
      x = tx;
      y = ty;
      if(Math.random()*level > 1)
      {
         value = 2;
      }
      if(Math.random()*level > .6)
      {
         value = 1;
      }
      else
      {
         value = 0;
      }
   }
   public int getKind()
   {
   return value;
   }
   public int getValue()
   {
      if(value == 2)
         return 20;
      else if(value == 1)
         return 10;
      else
         return 5;
   }
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public void setX(int newX)
   {
      x = newX;
   }
   public void setY(int newY)
   {
      y = newY;
   }
}