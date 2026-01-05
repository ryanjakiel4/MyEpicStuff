public class Coordinate
   {
      public int myX;
      public int myY;
      public Coordinate(int x, int y)
      {
         myX = x;
         myY = y;
      }
      public String toString()
      {
         return myX + ", "+ myY;
      }
      public void setX(int x)
   	{
   	myX = x;
   	}
   	public void setY(int y)
   	{
   	myY = y;
   	}
   }