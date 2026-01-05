import java.util.*;
   import java.io.*;
   public class MatthewsBday
   {
      private static String location = "bed";
      private static Command outBed,inBed,outHouse,inHouse,car,car1,couch,keys,couch1,tv,road,mailbox,letter,letter1,road1,chains,inpyrimid,inpyrimid1,outpyrimid,flowers,wall,wall1,secretRoom,chains1,secretRoom1;
      private static String carLocation = "house";
      public static boolean hasLetter, locateKeys, locateLetter, openMailbox, hasKeys, hasCar, toPyrimid, hasChains, hasPickaxe, secretRoomOpen = false;
      public static void main (String[] args) throws FileNotFoundException
      {
         Scanner awesome = new Scanner(System.in);
         Scanner sc = new Scanner(new File("begin.txt"));
         while(sc.hasNextLine())
            System.out.println(sc.nextLine());
         instantiateCommands();
         System.out.println("What do ya wanna do?");
         while(true)
         {
            String temp = awesome.nextLine();
            if(location.equals("bed"))
            {
               if(outBed.check(temp))
                  location = "house";
            }
            else if(location.equals("house"))
            {
               if(inBed.check(temp))
                  location = "bed";
               if(outHouse.check(temp))
                  location = "outHouse";
               if(couch.check(temp))
                  locateKeys=true;
               else if(couch1.check(temp))
               {
               }
               if(locateKeys)
               {
                  if(keys.check(temp))
                  {
                     hasKeys = true;
                  }
               }
               if(tv.check(temp))
               {
               }
               if(hasPickaxe)
               {
                  if(wall1.check(temp))
                     secretRoomOpen = true;
               }
               if(wall.check(temp))
               {
               }
               if(secretRoomOpen)
               {
                  if(secretRoom.check(temp))
                     location = "secret room";
               }
            }
            else if(location.equals("outHouse"))
            {
               if(inHouse.check(temp))
                  location = "house";
               if(hasKeys)
               {
                  if(car1.check(temp))
                     hasCar = true;
               }
               else if(car.check(temp))
               {
               }
               if(mailbox.check(temp))
                  openMailbox = true;
               else if(openMailbox)
               {
                  if(letter.check(temp))
                     hasLetter = true;
                  else if(hasLetter)
                     if(letter1.check(temp))
                     {
                     }
               }
               if(hasCar)
               {
                  if(road1.check(temp))
                  {
                     location = "west";
                  }
               }
               else if (road.check(temp))
               {
               }
            		
            }
            else if(location.equals("west"))
            {
               if(hasChains)
               {
                  if(chains.check(temp))
                     toPyrimid = true;
               }
               if(inpyrimid.check(temp))
               {
               }
               if(toPyrimid)
               {
                  if(inpyrimid1.check(temp))
                     location = "pyrimid";
               }
               if(flowers.check(temp))
                  hasPickaxe = true;
            }
            else if(location.equals("pyrimid"))
            {
               if(outpyrimid.check(temp))
                  location = "west";
            }
            else if(location.equals("secret room"))
            {
               if(secretRoom.check(temp))
                  location = "house";
               if(chains1.check(temp))
                  hasChains = true;	
            }
            System.out.println("What do ya wanna do?");
         }
      		
      	
      }
      public static void instantiateCommands()
      {
         outBed = new Command("exit bed", "You got out of bed... you live in a nice cozy house, with a nice couch tv, and bed");
         inBed = new Command("enter bed", "You got into your awesome fluffy bed, you could probably even place a wine glass on here and bounce a bowling ball on it without spilling the wine");
         outHouse = new Command("exit house", "You left your house, you see your garden with many trees and flowers, your car, and your mailbox");
         inHouse = new Command("enter house", "You have arrived in your cozy house, with a nice couch, tv, and bed");
         car = new Command ("enter car", "You want to get in your car but, alas, you have forgot your keys in your house.");
         car1 = new Command("enter car", "You unlocked your car with your keys, turned it on, and your ready to drive.");
         couch = new Command("check couch", "You located your keys!");
         keys = new Command("take keys", "Keys taken.");
         couch1 = new Command("couch", "This looks like the couch in Steve's apartement, and you sit down on it, and it is sooooo fluffy.  You should just sit here all day and forget about your bday present.");
         tv = new Command("tv", "Its an awesome tv, but it wont help you find your bday present.");
         road = new Command("go west", "A busy road blocks your way, you're gonna need to be in a car to cross it");
         road1 = new Command("go west", "You come across a large open area with a a pyrimid looking structure that is on top of two red supporters.  There is also a pretty flower bed.");
         mailbox = new Command("check mailbox", "You open the mailbox and you discover a letter.");
         letter = new Command("take letter", "Letter taken.");
         letter1 = new Command("read letter", "You read the letter. It says...\nMatthew,\nGreat Job! Youre finally getting the hang of it, now your going to need to go as far west as you can to find your next clue.");
         chains = new Command("use chains on pyrimid", "You tie the chains together in the shape of a lasso, throw it up to the entrance, and it holds!  You should be able to climb it now.");		
         inpyrimid = new Command("enter pyrimid","Your kidding yourself, aint no way you can jump that high, you're going to have to use something to get in");
         inpyrimid1 = new Command("enter pyrimid","You climb the clain to gain enterance to the pyrimid.  You see a skeleton of a pharaoh holding a pickaxe, but the rest is just empty space.");
         outpyrimid = new Command("exit pyrimid", "You climb down the chain and make it back down to solid ground.");   
         flowers = new Command("check flowers", "You look through the flowers and discover a pickaxe!");
         wall = new Command("check wall", "Its a sturdy wall, but there might be something behind it... but you won't be able to break it with your hands.");
         wall1 = new Command("use pickaxe on wall", "You chip away at the wall brick by brick, and you uncover a secret door!");
         secretRoom = new Command("enter door", "You go throught the secret door and discover a hidden room... with a skeleton held back by chains in the middle of it... You can't believe you've lived in this house for 10 years and have never discovered this before...");
         secretRoom1 = new Command("exit door", "You enter back into your cozy house with a nice couch, tv, and bed");
         chains1 = new Command("take chains", "Chains taken.");
      	
      }
   
   }