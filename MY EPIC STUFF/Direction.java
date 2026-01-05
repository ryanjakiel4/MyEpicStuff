 public class Direction
   {
   //   3   2   1 
   //    \  |  /
   //     \ | /
   // 4 ----- ----- 0
   //     / | \
   //    /  |  \
   //   5   6   7 
      private int dx;
      private int dy;
      public Direction(int x)
      {
         if(x==0)
         {
            dx=1;
            dy=0;
         }
         else if(x==1)
         {
            dx=1;
            dy=-1;
         }
         else if(x==2)
         {
            dx=0;
            dy=-1;
         }
         else if(x==3)
         {
            dx=-1;
            dy=-1;
         }
         else if(x==4)
         {
            dx=-1;
            dy=0;
         }
         else if(x==5)
         {
            dx=-1;
            dy=1;
         }
         else if(x==6)
         {
            dx=0;
            dy=1;
         }
         else if(x==7)
         {
            dx=1;
            dy=1;
         }	
      }
      public int incX(int x)
      {
         return x + dx;
      }
      public int incY(int y)
      {
         return y + dy;
      }
		public String toString()
		{
		return dx + ", "+ dy;
		}
   }