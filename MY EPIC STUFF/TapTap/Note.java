import java.awt.Color;
public class Note
{
   private int x;
   private int y;
   private Color color;
   private int count = 0;
   public Note(int x1, int y1, Color c)
   {
      x = x1;
      y = y1;
      color = c;
   }
   public int getX()
   {
      return x;
   }
   public int getY()
   {
      return y;
   }
   public Color getColor()
   {
      return color;
   }
   public void increment(int inc)
   {
      y-=inc;
      count++;
   }
   public boolean isAboveTrack(int offTrack)
   {
      if(y<offTrack)
         return true;
      return false;
   }
   public boolean isBelowTrack(int offTrack)
   {
      if(y>offTrack)
         return true;
      return false;
   }
   public int getCount()
   {
   return count;
   }
}
