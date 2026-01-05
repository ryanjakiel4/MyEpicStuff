public class Command
   {
      private String myCommand;
      private String myCommand1;
      private String myMessage = "";
      public Command(String a, String b)
      {
         if(a.indexOf(" ") > -1)
         {
            String[] temp = a.split(" ");
            if(temp[0].equals("use") && temp[2].equals("on"))
            {
               myCommand = temp[1];
               myCommand1 = temp[3];
            }
            else
            {
               myCommand = temp[0];
               myCommand1 = temp[1];
            }
         }
         else
            myCommand = a;
         myMessage=b;
      }
      public boolean check(String s)
      {
         String[] a = s.split(" ");
         if(a.length == 1)
         {
            if(myCommand1 == null)
               if(a[0].equals(myCommand))
               {
                  printMessage();
                  return true;
               }
         }
         else if(a.length < 4)
         {
            if(myCommand1 != null)
            {
               if(a[0].equals(myCommand) && a[1].equals(myCommand1))
               {
                  printMessage();
                  return true;
               }
            }
            else
               if(a[0].equals(myCommand)||a[1].equals(myCommand))
               {
                  printMessage();
                  return true;
               }
         }
         else
         {
            if(a[1].equals(myCommand) && a[3].equals(myCommand1))
            {
               printMessage();
               return true;
            }
         }
         return false;
      }
      private void printMessage()
      {
         String temp = myMessage;
         while(temp.length() > 80)
         {
            for(int x = 52; x < temp.length(); x++)
               if(temp.substring(x,x+1).equals(" "))
               {
                  System.out.println(temp.substring(0,x));
                  temp = temp.substring(x+1, temp.length());
                  x = temp.length();
               }
           	
         }
         System.out.println(temp);	
      }
   }