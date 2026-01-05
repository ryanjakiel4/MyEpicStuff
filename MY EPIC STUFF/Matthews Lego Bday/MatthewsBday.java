   import java.util.*;
   import java.io.*;
   public class MatthewsBday
   {
      private static String location = "bed";
      private static Command outBed,inBed,outHouse,inHouse,car,car1,couch,keys,couch1,tv,road,mailbox,letter,letter1,
      					road1,chains,inpyramid,inpyramid1,outpyramid,flowers,garden,garden1,ball,awkFlower,torch,wall,
      					wall1,secretRoom,chains1,secretRoom1,ax,roadA,outcar, window, window1, dogWindow,shed,soccerBall,
      					pHouse,pHouse1,pHouse2,pHouseExit,lightTorch,goNorth,goNorth1,goEast,goSouth,goSouth1,rock,present,
      					cannonRock,forest,forest1,goEastH,goEastH1,flipSwitch,chains2,grabObj,secretRoom0,useObj,cannonTake,
      					cannonTake2,cannonForest,torch7,shedWindow,shedWindow1,shedWindow3,sword,sword1,goEast1,goWest2,
      					southToNorth,northToSouth,goEast5,hole,goWest3,takeShip,goWest4;
      private static String carLocation = "outHouse";
      private static int hints = 2;
      public static boolean hasLetter, locateKeys, locateLetter, openMailbox, hasKeys, hasCar, toPyrimid, hasChains,
      					hasax, secretRoomOpen, searchedFlowers, hasTorch, seenBall, hasBall,seenShip, dogs,
      					hasHouseKeys, doorUnlocked, torchLit, spaceShip, torchWall, hasCannon, rockShot, foundHouseKeys,
      					flippedSwitch, chainsTied, hasObj, locateCannon, chatWithAlienb, openedPresent = false;
      public static boolean shedWindow2 = true;
      public static void main (String[] args) throws FileNotFoundException
      {
         Scanner awesome = new Scanner(System.in);
         Scanner sc = new Scanner(new File("begin.txt"));
         while(sc.hasNextLine())
            System.out.println(sc.nextLine());
         instantiateCommands();
         System.out.println();
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
               if(hasax)
               {
                  if(wall1.check(temp))
                     secretRoomOpen = true;
               }
               if(wall.check(temp))
               {
               }
               if(secretRoomOpen)
               {
                  if(toPyrimid)
                  {
                     if(secretRoom0.check(temp))
                        location = "secret room";
                  }
                  else if(secretRoom.check(temp))
                     location = "secret room";
               }
               if(dogs)
               {
                  if(window.check(temp))
                  {
                     location = "main";
                  }
               }
               else if(dogWindow.check(temp))
               {
               }
               if(soccerBall.check(temp))
                  dogs = true;      
            }
            else if(location.equals("outHouse"))
            {
               if(!hasCar)
               {
                  if(inHouse.check(temp))
                     location = "house";
               }
               if(present.check(temp))
               {
                  if(openPresent())
                  {
                     openPresent2();
                  }
               }
               if(mailbox.check(temp))
                  openMailbox = true;
               else if(openMailbox)
               {
                  if(letter.check(temp))
                     hasLetter = true;
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
               if(shed.check(temp))
               {
               }
               if(!hasCar)
                  if(garden.check(temp))
                  {
                     location = "garden";
                  }
               if(flippedSwitch)
               {
                  if(goEastH1.check(temp))
                  {
                     location = "main";
                  }
               }
               else if(goEastH.check(temp))
               {
               }
            }
            else if(location.equals("west"))
            {
               if(hasCar)
               {
                  if(roadA.check(temp))
                     location = "outHouse";
               }
               if(!hasCar)
               {
                  if(hasChains)
                  {
                     if(chainsTied)
                     {
                        if(chains.check(temp))
                           toPyrimid = true;
                     }
                  }
                  if(toPyrimid)
                  {
                     if(inpyramid1.check(temp))
                        location = "pyramid";
                  }
                  else if(inpyramid.check(temp))
                  {
                  }
                  if(flowers.check(temp))
                     searchedFlowers = true;
                  if(searchedFlowers)
                  {
                     if(ax.check(temp))
                        hasax = true;
                  }
                  if(hasHouseKeys)
                     if(pHouse1.check(temp))
                        doorUnlocked = true;
                  if(doorUnlocked)
                  {
                     if(pHouse2.check(temp))
                        location = "pHouse";
                  }
                  else if(pHouse.check(temp))
                  {
                  }
               }
            }
            else if(location.equals("pyramid"))
            {
               if(outpyramid.check(temp))
                  location = "west";
               if(torch.check(temp))
                  hasTorch=true;
            }
            else if(location.equals("secret room"))
            {
               if(secretRoom1.check(temp))
                  location = "house";
               if(chains1.check(temp))
                  hasChains = true;	
               if(hasObj)
                  if(useObj.check(temp))
                     if(chatWithAlien())
                        spaceShip = true;
            }
            else if(location.equals("main"))
            {
               if(!hasCar)
               {
                  if(window1.check(temp))
                     location = "house";
               }
               if(spaceShip)
               {
                  if(goNorth.check(temp))
                     location = "north";
               }
               else if(goNorth1.check(temp))
               {
                  seenShip = true;
               }
               if(seenShip)
            	{
               if(takeShip.check(temp))
               {
               }
               }
               if(flippedSwitch)
               {
                  if(goWest3.check(temp))
                     location = "outHouse";
               }
               else if(goWest4.check(temp))
               {
               }
               if(torchWall)
               {
                  if(goSouth.check(temp))
                     location = "south";
               }
               else if(goSouth1.check(temp))
               {
               }
               if(goEast5.check(temp))
                  location = "outHouse";
               if(rock.check(temp))
               {
               }
               if(hasCannon)
                  if(cannonRock.check(temp))
                     rockShot = true;
               if(forest.check(temp))
                  foundHouseKeys = true;
               if(foundHouseKeys)
                  if(forest1.check(temp))
                     hasHouseKeys = true;
               if(rockShot)
               {
                  if(grabObj.check(temp))
                     hasObj = true;	
               }	
               if(torchLit)
               {
                  if(torch7.check(temp))
                     torchWall = true;
               }
            }
            else if(location.equals("garden"))
            {
               if(awkFlower.check(temp))
               {
                  seenBall = true;
               }
               if(seenBall)
               {
                  if(ball.check(temp))
                     hasBall = true;
               }
               if(garden1.check(temp))
                  location = "outHouse";
            }
            else if(location.equals("pHouse"))
            {
               if(hasTorch)
                  if(lightTorch.check(temp))
                     torchLit = true;
               if(pHouseExit.check(temp))
                  location = "west";
            }
            else if(location.equals("south"))
            {
               if(southToNorth.check(temp))
                  location = "main";
               if(flipSwitch.check(temp))
               {
                  if(flippedSwitch)
                  {
                     flipSwitch.setMessage("Switch has been flipped to the \"OFF\" position");
                     flippedSwitch = false;
                     shedWindow2 = true;
                  }
                  else
                  {
                     flipSwitch.setMessage("Switch has been flipped to the \"ON\" position");
                     flippedSwitch = true;
                     shedWindow2 = false;
                  }
               }
               if(cannonForest.check(temp))
                  locateCannon = true;
               if(locateCannon)
               {
                  if(hasCar)
                  {
                     if(cannonTake2.check(temp))
                        hasCannon = true;
                  }
                  else if(cannonTake.check(temp))
                  {
                  }
               }
            }
            else if(location.equals("north"))
            {
               if(northToSouth.check(temp))
                  location = "main";
               if(shedWindow2)
               {
                  if(shedWindow.check(temp))
                     location = "shed";
               }
               else
                  if(shedWindow1.check(temp))
                  {
                  }
            }
            else if(location.equals("shed"))
            {
               if(sword.check(temp)||hole.check(temp))
               {
                  location = "?";
                  shedWindow.setMessage("You go through the window into your tool shed, which now is apparently a portal to another planet, all you have to do is enter the hole.");
               }
               if(shedWindow3.check(temp))
                  location = "north";
            }
            else if(location.equals("?"))
            {
               if(goEast1.check(temp))
               {
                  if(chatWithAlienb)
                  {
                     if(openedPresent)
                     {
                        chatWithAlien3();
                     }
                     else
                     {
                        System.out.println("You havent opened your present yet? Having trouble finding the numbers?\nWell I cant help you... Just remember...\n\"This is definitely better than your gift last year\""); 
                     }
                  }
                  else
                  {
                     chatWithAlien2();
                     chatWithAlienb = true;
                  }
                  location = "??";
               }
               if(sword1.check(temp))
               {
                  location = "house";
               }	
            }
            else if(location.equals("??"))
            {
               if(goWest2.check(temp))
                  location = "?";
            }
            if(hasKeys)
            {
               if(carLocation.equals(location))
               {
                  if(car1.check(temp))
                     hasCar = true;
               }
            }
            else if(car.check(temp))
            {
            }
            if(hasCar)
            {
               if(outcar.check(temp))
               {
                  carLocation = location;
                  hasCar = false;
               }
            }
            if(chains2.check(temp))
               chainsTied = true;
            if(temp.equals("ryanisawesome"))
            {
               torchWall = true;
               hasax = true;
               hasObj = true;
               toPyrimid= true;
            }
            if(temp.equals("hint"))
               hint();
            if(temp.equals("car location"))
               System.out.println(carLocation);
            if(hasLetter)
               if(letter1.check(temp))
               {
               }
            if(seenShip)
               window.setMessage("You leave your house through the window and find a large open area with an odd structure in the south,  and a spaceship in the north... this area also has a strange rock structure and a little forest");
            if(spaceShip)
               window.setMessage("You leave your house through the window and find a large open area with an odd structure in the south,  and another empty area in the north... this area also has a strange rock structure and a little forest");
            System.out.println();
            System.out.println("What do ya wanna do?");
            System.out.println();
         }
      }
      public static void instantiateCommands()
      {
         soccerBall = new Command("use ball on dogs", "You throw the soccer ball and the dogs chase after it, leaving you alone.  Now you can exit.");
         dogWindow = new Command("exit window", "Some mean looking dogs are guarding the window, and you can't make them go away...");
         outBed = new Command("exit bed", "You got out of bed... you live in a nice cozy house, with a solid oak door, nice couch, tv, bed, and sunlight coming in through your window");
         inBed = new Command("enter bed", "You got into your awesome fluffy bed, you could probably even place a wine glass on here and bounce a bowling ball on it without spilling the wine");
         outHouse = new Command("exit door", "You left your house, you see your garden with many trees and flowers, your car, your tool shed, your mailbox, and a giant present");
         inHouse = new Command("enter house", "You have arrived in your cozy house, with a solid oak door, nice couch, tv, bed, and sunlight coming in through your window");
         car = new Command ("enter car", "You want to get in your car but, alas, you have forgot your keys in your house.");
         car1 = new Command("enter car", "You unlocked your car with your keys, turned it on, and your ready to drive... remember... you can't drive everywhere.");
         outcar = new Command("exit car", "You parked and locked your car.");
         couch = new Command("check couch", "You located your keys!");
         keys = new Command("take keys", "Keys taken.");
         couch1 = new Command("couch", "This looks like the couch in Steve's apartement, and you sit down on it, and it is sooooo fluffy.  You should just sit here all day and forget about your bday present.");
         tv = new Command("tv", "Its an awesome tv, but it wont help you find your bday present.");
         road = new Command("go west", "A busy road blocks your way, you're gonna need to be in a car to cross it");
         road1 = new Command("go west", "You come across a large open area with a a pyramid looking structure that is on top of two red supporters.  There is also a pretty flower bed and a mysterious looking house.");
         mailbox = new Command("check mailbox", "You open the mailbox and you discover a letter.");
         letter = new Command("take letter", "Letter taken.");
         letter1 = new Command("read letter", "You read the letter. It says...\nMatthew, Great Job! Youre finally getting the hang of it, now you must go as far west as you can to find something that will aid you in your quest.");
         chains2 = new Command("use chain on chain", "You tie the chains together in the shape of a lasso... Hmmm this should be more useful");
         chains = new Command("use chain on pyramid", "You throw the chain up at the entrance to the pyramid, and it holds!  You should be able to climb it now.");		
         inpyramid = new Command("enter pyramid","Your kidding yourself, aint no way you can jump that high, you're going to have to use something to get in");
         inpyramid1 = new Command("enter pyramid","You climb the clain to gain enterance to the pyramid.  You see a skeleton of a pharaoh holding a torch, but the torch has been unlit for a long time...");
         outpyramid = new Command("exit pyramid", "You climb down the chain and make it back down to solid ground.");   
         flowers = new Command("check flowers", "You look through the flowers and discover a ax!");
         wall = new Command("check wall", "Its a sturdy wall, but there might be something behind it... but you won't be able to break it with your hands.");
         wall1 = new Command("use ax on wall", "You chip away at the wall brick by brick, and you uncover a secret door!");
         secretRoom0 = new Command("enter secret door", "You go throught the secret door see the hidden room... with a alien in the middle of it... You still can't believe you've lived in this house for 10 years and have never discovered this before...");
         secretRoom = new Command("enter secret door", "You go throught the secret door and discover a hidden room... with a alien held back by chains in the middle of it... You can't believe you've lived in this house for 10 years and have never discovered this before...");
         secretRoom1 = new Command("exit secret door", "You enter back into your cozy house with a solid oak door, nice couch, tv, bed, and sunlight coming in through your window");
         ax = new Command("take ax","ax taken.");
         chains1 = new Command("take chains", "Chains taken.");
         roadA = new Command("go east", "You cross the road and see your garden with many trees and flowers your mailbox, your tool shed, and a giant present.");
         window = new Command("exit window", "You leave your house through the window and find a large open area with an odd structure in the south,  and... WHATS THAT!! in the north... this area also has a strange rock structure and a little forest");
         window1 = new Command("enter window", "You go through your window and enter your house, you live in a nice cozy house, with a solid oak door, nice couch, tv, bed, and sunlight coming in through your window");
         shed = new Command("enter shed","You try pushing the door, but something is blocking it from the other side.");
         torch = new Command("take torch", "You pry the torch from the skeleton's hands and retrieve the torch.");
         garden = new Command("enter garden","You go to your garden and see all of your beautiful trees and flowers.");
         awkFlower = new Command("check grass","You look more closely at the grass and discover a ball!");
         ball = new Command("take ball", "Ball taken.");
         garden1 = new Command("exit garden","you leave your garden and you see your house, your car, your tool shed, your mailbox, and a giant present");
         pHouse = new Command("enter house", "you attempt to enter the mysterious house, but the door is locked.");
         pHouse1 = new Command("use key on door", "You unlock the door using the key.");
         pHouse2  =new Command("enter house", "You enter the house and see an open fireplace... and thats it. Pretty cozy.");
         pHouseExit = new Command("exit house", "You leave through the door and see a flower bed, a pyramid looking structure, and a road to the east.");
         lightTorch = new Command("use torch on fireplace", "You put the torch in the fireplace and the torch is lit!");
         goNorth = new Command("go north", "you come across a very empty space, next to your tool shed.");
         goNorth1 = new Command("go north", "A spaceship??? Really??? Well yeah there's no way youre gonna get past this.");
         goEast1=new Command("go east","On this foreign land you see none other than that alien and his spaceship... despite the land looking somewhat random... it looks pretty cool. All of a sudden, the alien starts talking to you...");      
         goSouth = new Command("go south", "you head south and discover a semi-dense forest, and what appears to be a switch that controls... something.");
         goSouth1 = new Command("go south", "you try to head south, but a wall blocks your way... this wall totally reminds you of something...");
         rock = new Command("check rock", "you look closer at the rock... it seems hollow, as if something is inside it.  Its gonna take something really powerfull to break this rock.");
         cannonRock = new Command("use cannon on rock","The rock breaks in half, and reveals a mysterious object... this cannot be from earth...");
         forest = new Command ("check forest", "you look around the forest and find a key!");
         forest1 = new Command("take key" , "Taken.");
         goEastH = new Command("go east","You attempt to move east, but a large wall blocks your way.");
         goEastH1 = new Command("go east","You move east into a large open area that has a strange rock structure and a little forest");
         flipSwitch = new Command("check switch","Switch has been flipped to the \"ON\" position");
         grabObj = new Command("take object", "You grab the mysterious object... a small jolt of electricity surprises you... what could this be used for?");
         useObj = new Command("use object on alien", "You let the object come in contact with the alien's skin... the body stirs, and the alien wakes up!");
         cannonForest = new Command("check forest","You look around the forest and see A CANNON???");
         cannonTake = new Command("take cannon", "Even with wheels, theres no way you can push this cannon yourself.");
         goWest3 = new Command("go west","You go west and see your garden with many trees and flowers, your car, your tool shed, your mailbox, and a giant present");     
         cannonTake2 = new Command("take cannon","You attach the cannon to your car, and your ready to drive!");
         torch7 = new Command("use torch on wall","You throw the lit torch at the wall and the wall immediately falls down.");
         shedWindow = new Command("enter window", "You go through the window into your tool shed... wow... the only tool in here is a very nice-looking sword...");
         shedWindow1 = new Command("enter window", "A wall is blocking your entry");
         shedWindow3 = new Command("exit window","You leave through the window, back into a very empty space...");
         sword = new Command("take sword","You take the sword in your hand and feel the power from the sword moving through your entire body, but then you are violently sucked into the hole you pulled the sword from and end up... I dont even know...");
         goWest2 = new Command("go west","You head west and see a teleporter... hopefully this can take you back home...");
         sword1 = new Command("use sword on teleporter","You insert your sword into a special looking slot and the teleporter comes to life!  You take your sword back before stepping into the teleporter... You end up in your nice cozy house, with a solid oak door, nice couch, tv, bed, and sunlight coming in through your window.");
         present = new Command("check present", "A code is required to open the present, enter the code below:");
         southToNorth = new Command("go north","You move into a large open area that has a strange rock structure and a little forest");
         northToSouth = new Command("go south","You move into a large open area that has a strange rock structure and a little forest");
         goEast5 = new Command("go east","You go east and you see your house, your car, your tool shed, your mailbox, and a giant present");
         hole = new Command("enter hole","You are transported back to that teleporter...");
         takeShip = new Command("take spaceship","You cant do that! You dont have your driver's license yet...");
         goWest4 = new Command("go west", "You want to go west, but a large wall blocks your way...");
      }
      public static boolean chatWithAlien()
      {
         Scanner rush = new Scanner(System.in);
         while(true)
         {
            System.out.println();
            System.out.println("Choose 1, 2, or 3 for the following.\n");
            System.out.println();
            System.out.println("I'm alive...IM ALIVE???... You.  Who are you?\n\n--Uh hi im matthew... so you're really an alien huh?\n--ITS ALIVEEEE!\n--I'm matthew and you've go about 3 seconds to tell me what you're doing here\n  or I will have to kill you.");
            String x = rush.nextLine();
            if(x.equals("1")||x.equals("2"))
            {
               System.out.println("Well... yeah... have you seen my ship anywhere, i need to get home.\nAnd dont lie to me, you'll regret it.\n--No i havent seen your ship around anywhere...\n--Yeah, leave this room, go out the window and\nitll be on your left, you cant miss it.");
               x = rush.nextLine();
               if(x.equals("1"))
               {
                  if(seenShip)
                  {
                     System.out.println("LIAR");
                     System.exit(0);
                  }
                  else
                  {
                     System.out.println("Well let me know when you do ASAP");
                     return false;
                  }
               }
               if(x.equals("2"))
               {
                  if(!seenShip)
                  {
                     System.out.println("LIAR");
                     System.exit(0);
                  }
                  else
                  {
                     System.out.println("SWEET! Peace out.");
                     return true;
                  }
               }
            }
            else if(x.equals("3"))
            {
               System.out.println("Well considering you have no real weapon YOU have\n5 seconds to tell me where my ship is.\nAnd don't even think about lying.\n--Its out the window to the left.\n--I honestly have no idea");
               x = rush.nextLine();
               if(x.equals("1"))
               {
                  if(!seenShip)
                  {
                     System.out.println("LIAR");
                     System.exit(0);
                  }
                  else
                  {
                     System.out.println("SWEET! Peace out.");
                     return true;
                  }
               }
               if(x.equals("2"))
               {
                  if(seenShip)
                  {
                     System.out.println("LIAR");
                     System.exit(0);
                  }
                  else
                  {
                     System.out.println("Well you know what you have to do. Now GO!");
                     return false;
                  }	
               }
            }
         }
      }
      public static void chatWithAlien2() throws FileNotFoundException
      {
         Scanner ryan = new Scanner(new File("alien.txt"));
         while (ryan.hasNextLine())
            System.out.println(ryan.nextLine());
      }
      public static void chatWithAlien3() throws FileNotFoundException
      {
         Scanner awesome = new Scanner(new File("alien2.txt"));
         while(awesome.hasNextLine())
            System.out.println(awesome.nextLine());
      }
      public static boolean openPresent()
      {
         Scanner wicked = new Scanner(System.in);
         if(wicked.nextLine().equals("4,6,This is definitely better than your gift last year"))
         {
            return true;
         }
         else
         {
            System.out.println("Wrong code.\n\nYou see your garden with many trees and flowers, your tool shed, your mailbox, and a giant present.");
            return false;
         }
      }
      public static void openPresent2() throws FileNotFoundException
      {
         Scanner possum = new Scanner(new File("present.txt"));
         while(possum.hasNextLine())
            System.out.println(possum.nextLine());
         openedPresent = true;
      }
      public static void hint() throws FileNotFoundException
      {
         Scanner a = new Scanner(new File ("a.txt"));
         Scanner b = new Scanner(System.in);
         System.out.println("Hint about what?");
         String awesome = b.nextLine();
         if(hints > 0)
         {
            while (a.hasNextLine())
            {
               String wicked = a.nextLine();
               if(wicked.indexOf(awesome) > -1)
               {
                  System.out.println(wicked);
                  System.out.println("Is this what you were looking for?");
                  String x = b.nextLine();
                  if(x.equals("yes"))
                  {
                     hints--;
                     return;
                  }
               }
            }
            System.out.println("Sorry there was no result, but always remember that\nyou cant do everything while in your car\nand try different key words if your really stuck.");
         }
      }
   }