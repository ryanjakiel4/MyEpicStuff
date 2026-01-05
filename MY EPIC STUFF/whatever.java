public class whatever
{
   public static void main (String[] args)
   {
      int[] num = new int[]{0,1,1,0,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
      long decimalValue = 0;
      long powerOfTwo = 1;
      for(int x = 0; x < num.length; x++)
      {
      decimalValue += num[x]*powerOfTwo;
      powerOfTwo *= 2;
      }
      System.out.println(decimalValue);
   }
}