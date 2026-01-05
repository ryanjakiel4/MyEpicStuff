import java.util.Scanner;
public class consoleStuff
{
   private static Thread thready;
   private static int newNum = 1000;
   public static void main(String[] args) throws InterruptedException 
   {
      thready = new Thread(
            new Runnable(){
               public void run()
               {
                  Scanner scanny = new Scanner(System.in);
                  while(true)
                  {
                     newNum = scanny.nextInt();
                  }
               }  
            });
      while(newNum < 10000)
      {
         System.out.print(newNum);
         Thread.sleep(100);
         for(int y = 0; y < (newNum % 10) + 1; y++)
         {
            System.out.print("\b");
         }
         newNum++;
      }
   }
   
}