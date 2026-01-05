
//gold brick taken can be displayed multiple times
import java.awt.*;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.applet.Applet;
import java.awt.geom.*;
import java.io.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javax.imageio.ImageIO;

public class MatthewsBdayPart2 extends Canvas
      implements KeyListener// , Runnable
{
   // station0
   private Command2 crash = new Command2(fileToArrayList("intro.txt"));
   private Command2 checkStation0 = new Command2(fileToArrayList("checkStation0.txt"));
   private Command2 checkStation0WithoutWall = new Command2(fileToArrayList("checkStation0WithoutWall.txt"));
   private Command2 checkStation0WithoutWallTrain = new Command2(fileToArrayList("checkStation0WithoutWallTrain.txt"));
   private Command2 checkStation0WithoutWallTrainPyramid = new Command2(
         fileToArrayList("checkStation0WithoutWallTrainPyrimid.txt"));
   private Command2 checkStation0WithoutWallTrainPyramidStorage = new Command2(
         fileToArrayList("checkStation0WithoutWallTrainPyrimidStorage.txt"));
   private Command2 checkStation0WithoutWallTrainPyramidStorageBricks = new Command2(
         fileToArrayList("checkStation0WithoutWallTrainPyrimidStorageBric.txt"));

   private Command2 checkSpaceshipTorch = new Command2(fileToArrayList("checkSpaceshipTorch.txt"));
   private Command2 checkSpaceshipCoin = new Command2(fileToArrayList("checkSpaceshipCoin.txt"));
   private Command2 checkSpaceshipWithHoseThrusterDomeLeverPower = new Command2(
         fileToArrayList("checkSpaceshipWithHoseThrusterDomeLeverPower.txt"));
   private Command2 checkSpaceshipWithHoseThrusterDomeLever = new Command2(
         fileToArrayList("checkSpaceshipWithHoseThrusterDomeLever.txt"));
   private Command2 checkSpaceshipWithHoseThrusterDome = new Command2(
         fileToArrayList("checkSpaceshipWithHoseThrusterDome.txt"));
   private Command2 checkSpaceshipWithHoseThruster = new Command2(
         fileToArrayList("checkSpaceshipWithHoseThruster.txt"));
   private Command2 checkSpaceshipWithHose = new Command2(fileToArrayList("checkSpaceshipWithHose.txt"));
   private Command2 checkSpaceship = new Command2(fileToArrayList("checkSpaceship.txt"));

   private Command2 checkForest = new Command2(fileToArrayList("checkForest.txt"));
   private Command2 checkWall = new Command2(fileToArrayList("checkWall.txt"));
   private Command2 checkPyramid = new Command2(fileToArrayList("checkPyramid.txt"));
   private Command2 checkTrain = new Command2(fileToArrayList("checkTrain.txt"));
   private Command2 takeTorch = new Command2(fileToArrayList("takeTorch.txt"));
   private Command2 useTorchOnSpaceship = new Command2(fileToArrayList("useTorchOnSpaceship.txt"));
   private Command2 useTorchOnWall = new Command2(fileToArrayList("useTorchOnWall.txt"));
   private Command2 takeTrain = new Command2(fileToArrayList("takeTrain.txt"));
   private Command2 takeCoin = new Command2(fileToArrayList("takeCoin.txt"));
   private Command2 takeGoldBrick = new Command2(fileToArrayList("takeGoldBrick.txt"));
   private Command2 useShovelOnPyramid = new Command2(fileToArrayList("useShovelOnPyramid.txt"));
   private Command2 takeStorageCar = new Command2(fileToArrayList("takeStorageCar.txt"));
   private Command2 takeStorageCarNoMagnet = new Command2(fileToArrayList("takeStorageCarNoMagnet.txt"));
   private Command2 useShovelOnBricksStation0 = new Command2(fileToArrayList("useShovelOnBricksStation0.txt"));
   private Command2 useShovelOnBricksWithoutStorageStation0 = new Command2(
         fileToArrayList("useShovelOnBricksWithoutStorageStation0.txt"));

   private Command2 useHoseOnSpaceship = new Command2(fileToArrayList("useHoseOnSpaceship.txt"));
   private Command2 useThrusterOnSpaceship = new Command2(fileToArrayList("useThrusterOnSpaceship.txt"));
   private Command2 useDomeOnSpaceship = new Command2(fileToArrayList("useDomeOnSpaceship.txt"));
   private Command2 useLeverOnSpaceship = new Command2(fileToArrayList("useLeverOnSpaceship.txt"));
   private Command2 usePowerOnSpaceship = new Command2(fileToArrayList("usePowerOnSpaceship.txt"));

   private Command2 checkPyramidRemoved = new Command2(fileToArrayList("checkPyramidAfterRemoved.txt"));
   private Command2 takeOff = new Command2(fileToArrayList("takeoff.txt"));
   private Command2 takeOffNoWin = new Command2(fileToArrayList("takeOffNoWin.txt"));

   // station1

   private Command2 checkStation1 = new Command2(fileToArrayList("checkStation1.txt"));
   private Command2 checkStation1WithoutBricks = new Command2(fileToArrayList("checkStation1WithoutBricks.txt"));
   private Command2 checkGuyStation1 = new Command2(fileToArrayList("checkGuyStation1.txt"));
   private Command2 checkGuyStation1WithoutExtinguisher = new Command2(
         fileToArrayList("checkGuyStation1WithoutExtinguisher.txt"));
   private Command2 checkGuyStation1WithoutExtinguisherMagnet = new Command2(
         fileToArrayList("checkGuyStation1WithoutExtinguisherMagnet.txt"));
   private Command2 checkHouse = new Command2(fileToArrayList("checkHouse.txt"));
   private Command2 checkHouseLocked = new Command2(fileToArrayList("checkHouseLocked.txt"));
   private Command2 useKeyOnHouse = new Command2(fileToArrayList("useKeyOnHouse.txt"));
   private Command2 useShovelOnBricksStation1 = new Command2(fileToArrayList("useShovelOnBricksStation1.txt"));
   private Command2 useShovelOnBricksWithoutStorageStation1 = new Command2(
         fileToArrayList("useShovelOnBricksWithoutStorageStation1.txt"));
   private Command2 talkToGuyStation1 = new Command2(fileToArrayList("talkToGuyStation1.txt"));
   private Command2 talkToGuyStation1WithExtinguisherMagnet = new Command2(
         fileToArrayList("talkToGuyStation1WithExtinguisherMagnet.txt"));
   private Command2 talkToGuyStation1WithExtinguisher = new Command2(
         fileToArrayList("talkToGuyStation1WithMagnet.txt"));
   private Command2 takeExtinguisher = new Command2(fileToArrayList("takeExtinguisher.txt"));
   private Command2 takeMagnet = new Command2(fileToArrayList("takeMagnet.txt"));
   private Command2 takeHose = new Command2(fileToArrayList("takeHose.txt"));
   private Command2 checkRoof = new Command2(fileToArrayList("checkRoof.txt"));
   private Command2 checkTv = new Command2(fileToArrayList("checkTv.txt"));
   private Command2 useCartridge1OnTv = new Command2(fileToArrayList("useCartridge1OnTv.txt"));
   private Command2 useCartridge2OnTv = new Command2(fileToArrayList("useCartridge2OnTv.txt"));
   private Command2 useCartridge3OnTv = new Command2(fileToArrayList("useCartridge3OnTv.txt"));
   private Command2 takePrize1 = new Command2(fileToArrayList("takePrize1.txt"));
   private Command2 takePrize2 = new Command2(fileToArrayList("takePrize2.txt"));
   private Command2 takePrize3 = new Command2(fileToArrayList("takePrize3.txt"));

   // station 2
   private Command2 checkStation2NoBricks = new Command2(fileToArrayList("checkStation2NoBricks.txt"));
   private Command2 checkStation2 = new Command2(fileToArrayList("checkStation2.txt"));
   private Command2 checkStation2DoorUnlockedBoxOpened = new Command2(
         fileToArrayList("checkStation2DoorUnlockedBoxOpened.txt"));
   private Command2 checkStation2DoorUnlocked = new Command2(fileToArrayList("checkStation2DoorUnlocked.txt"));
   private Command2 checkFlowers = new Command2(fileToArrayList("checkFlowers.txt"));
   private Command2 takeAxe = new Command2(fileToArrayList("takeAxe.txt"));
   private Command2 talkToGuyStation2 = new Command2(fileToArrayList("talkToGuyStation2.txt"));
   private Command2 talkToGuyStation2DoorOpen = new Command2(fileToArrayList("talkToGuyStation2DoorOpen.txt"));
   private Command2 checkDoor = new Command2(fileToArrayList("checkDoor.txt"));
   private Command2 checkDoorNoGoldBricks = new Command2(fileToArrayList("checkDoorNoGoldBricks.txt"));
   private Command2 useShovelOnBricksStation2 = new Command2(fileToArrayList("useShovelOnBricksStation2.txt"));
   private Command2 takeKey = new Command2(fileToArrayList("takeKey.txt"));
   private Command2 checkBox = new Command2(fileToArrayList("checkBox.txt"));
   private Command2 statue52 = new Command2(fileToArrayList("statue52.txt"));
   private Command2 checkShack = new Command2(fileToArrayList("checkShack.txt"));

   // station3
   private Command2 checkStation3 = new Command2(fileToArrayList("checkStation3.txt"));
   private Command2 checkStation3Wall = new Command2(fileToArrayList("checkStation3Wall.txt"));
   private Command2 checkStation3CannonWall = new Command2(fileToArrayList("checkStation3CannonWall.txt"));
   private Command2 checkStation3ForestCannonWall = new Command2(fileToArrayList("checkStation3ForestCannonWall.txt"));
   private Command2 checkStation3BricksForestCannonWall = new Command2(
         fileToArrayList("checkStation3BricksForestCannonWall.txt"));
   private Command2 checkForestStation3HasCannon = new Command2(fileToArrayList("checkForestStation3HasCannon.txt"));
   private Command2 checkForestStation3 = new Command2(fileToArrayList("checkForestStation3.txt"));
   private Command2 checkCannon = new Command2(fileToArrayList("checkCannon.txt"));
   private Command2 checkWallStation3 = new Command2(fileToArrayList("checkWallStation3.txt"));
   private Command2 takeRemote = new Command2(fileToArrayList("takeRemote.txt"));
   private Command2 useShovelOnBricksStation3 = new Command2(fileToArrayList("useShovelOnBricksStation3.txt"));
   private Command2 useDynamiteOnWall = new Command2(fileToArrayList("useDynamiteOnWall.txt"));
   private Command2 useAxeOnForest = new Command2(fileToArrayList("useAxOnForest.txt"));
   private Command2 takeCannonCar = new Command2(fileToArrayList("takeCannon.txt"));
   private Command2 talkToGuyStation3WallRemoved = new Command2(fileToArrayList("talkToGuyStation3WallRemoved.txt"));
   private Command2 talkToGuyStation3 = new Command2(fileToArrayList("talkToGuyStation3.txt"));
   private Command2 takeStatue = new Command2(fileToArrayList("takeStatue.txt"));
   private Command2 checkIce = new Command2(fileToArrayList("checkIce.txt"));
   private Command2 useHeatGunOnIce = new Command2(fileToArrayList("useHeatGunOnIce.txt"));
   private Command2 checkLever = new Command2(fileToArrayList("checkLever.txt"));
   private Command2 takeDomeNoCar = new Command2(fileToArrayList("takeDomeNoCar.txt"));
   private Command2 takeDome = new Command2(fileToArrayList("takeDome.txt"));
   private Command2 takeCartridge1 = new Command2(fileToArrayList("takeCartridge1.txt"));
   private Command2 checkMailbox = new Command2(fileToArrayList("checkMailbox.txt"));
   private Command2 checkRoadStation5 = new Command2(fileToArrayList("checkRoadStation5.txt"));

   // station 4
   private Command2 checkDoorStation4 = new Command2(fileToArrayList("checkDoorStation4.txt"));
   private Command2 checkHouseStation4 = new Command2(fileToArrayList("checkHouseStation4.txt"));
   private Command2 takeThruster = new Command2(fileToArrayList("takeThruster.txt"));
   private Command2 takeBall = new Command2(fileToArrayList("takeBall.txt"));

   // station 5
   private Command2 checkStation5MissilePyramid = new Command2(fileToArrayList("checkStation5MisslePyramid.txt"));
   private Command2 checkStation5Missile = new Command2(fileToArrayList("checkStation5Missle.txt"));
   private Command2 checkStation5 = new Command2(fileToArrayList("checkStation5.txt"));
   private Command2 checkStation5NoBridge = new Command2(fileToArrayList("checkStation5NoBridge.txt"));
   private Command2 useBricksOnRoad = new Command2(fileToArrayList("useBricksOnRoad.txt"));
   private Command2 useBricksOnRoadNotEnough = new Command2(fileToArrayList("useBricksOnRoadNotEnough.txt"));
   private Command2 useShovelOnBricksStation5 = new Command2(fileToArrayList("useShovelOnBricksStation5.txt"));
   private Command2 talkToGuyStation5 = new Command2(fileToArrayList("talkToGuyStation5.txt"));
   private Command2 takeCartridge2 = new Command2(fileToArrayList("takeCartridge2.txt"));
   private Command2 useRemoteOnPyramid = new Command2(fileToArrayList("useRemoteOnPyramid.txt"));
   private Command2 talkToGuyStation5Pyramid = new Command2(fileToArrayList("talkToGuyStation5Pyramid.txt"));
   private Command2 takeDynamite = new Command2(fileToArrayList("takeDynamite.txt"));
   private Command2 checkPyramidOpen = new Command2(fileToArrayList("checkPyramidOpen.txt"));
   private Command2 checkPyramidRoof = new Command2(fileToArrayList("checkPyramidRoof.txt"));
   private Command2 takeMissileCar = new Command2(fileToArrayList("takeMissileCar.txt"));
   private Command2 checkPyramidStation5 = new Command2(fileToArrayList("checkPyramidStation5.txt"));
   private Command2 checkRoad = new Command2(fileToArrayList("checkRoad.txt"));
   private Command2 checkRoadAfterBridge = new Command2(fileToArrayList("checkRoadAfterBridge.txt"));
   // station 6
   private Command2 checkStation6WithPower = new Command2(fileToArrayList("checkStation6WithPower.txt"));
   private Command2 checkStation6 = new Command2(fileToArrayList("checkStation6.txt"));
   private Command2 talkToSpider = new Command2(fileToArrayList("talkToSpider.txt"));
   private Command2 takeCartridge3 = new Command2(fileToArrayList("takeCartridge3.txt"));
   private Command2 takePower = new Command2(fileToArrayList("takePower.txt"));
   private Command2 takePowerWithoutWinning = new Command2(fileToArrayList("takePowerWithoutWinning.txt"));

   // station 8

   private Command2 checkStation8 = new Command2(fileToArrayList("checkStation8.txt"));
   private Command2 talkToGuyStation8WithMissileCar = new Command2(
         fileToArrayList("talkToGuyStation8WithMissleCar.txt"));
   private Command2 talkToGuyStation8 = new Command2(fileToArrayList("talkToGuyStation8.txt"));
   private Command2 talkToGuyStation8Nothing = new Command2(fileToArrayList("talkToGuyStation8Nothing.txt"));
   private Command2 talkToGuyStation8WithoutMissileCar = new Command2(
         fileToArrayList("talkToGuyStation8WithoutMissleCar.txt"));
   private Command2 takeShovel = new Command2(fileToArrayList("takeShovel.txt"));
   private Command2 takeMissile = new Command2(fileToArrayList("takeMissle.txt"));
   private Command2 useShovelOnBricksStation8 = new Command2(fileToArrayList("useShovelOnBricksStation8.txt"));
   private Command2 useShovelOnBricksStation8NoGoldBricks = new Command2(
         fileToArrayList("useShovelOnBricksStation8NoGoldBricks.txt"));

   // all
   private Command2 moveToStation0 = new Command2(fileToArrayList("moveToStation0.txt"));
   private Command2 moveToStation1 = new Command2(fileToArrayList("moveToStation1.txt"));
   private Command2 moveToStation2 = new Command2(fileToArrayList("moveToStation2.txt"));
   private Command2 moveToStation2CrabFire = new Command2(fileToArrayList("moveToStation2CrabFire.txt"));
   private Command2 moveToStation2CrabRock = new Command2(fileToArrayList("moveToStation2CrabRock.txt"));
   private Command2 moveToStation3 = new Command2(fileToArrayList("moveToStation3.txt"));
   private Command2 moveToStation3CrabFire = new Command2(fileToArrayList("moveToStation3CrabFire.txt"));
   private Command2 moveToStation3CrabRock = new Command2(fileToArrayList("moveToStation3CrabRock.txt"));
   private Command2 moveToStation4 = new Command2(fileToArrayList("moveToStation4.txt"));
   private Command2 moveToStation4WallRock = new Command2(fileToArrayList("moveToStation4WallRock.txt"));
   private Command2 moveToStation5 = new Command2(fileToArrayList("moveToStation5.txt"));
   private Command2 moveToStation5CrabFire = new Command2(fileToArrayList("moveToStation5CrabFire.txt"));
   private Command2 moveToStation6 = new Command2(fileToArrayList("moveToStation6.txt"));
   private Command2 moveToStation6LeverDamaged = new Command2(fileToArrayList("moveToStation6LeverDamaged.txt"));
   private Command2 moveToStation7 = new Command2(fileToArrayList("moveToStation7.txt"));
   private Command2 moveToStation7CrabFire = new Command2(fileToArrayList("moveToStation7CrabFire.txt"));

   private Command2 useExtinguisherOnFire = new Command2(fileToArrayList("useExtinguisherOnFire.txt"));
   private Command2 useMissileOnCrab = new Command2(fileToArrayList("useMissleOnCrab.txt"));
   private Command2 useLeversOnTrack = new Command2(fileToArrayList("useLeversOnTrack.txt"));
   private Command2 useCannonOnRock = new Command2(fileToArrayList("useCannonOnRock.txt"));

   private boolean wallRemoved = false, hasTrain = false, pyramidRemoved = false, hasStorageCar = false,
         dugBricksStation0 = false, hasTorch = false, lookingForCoin = false, checkedSpaceshipForCoin = false,
         domeAdded = false, leverAdded = false, hoseAdded = false, thrusterAdded = false, powerAdded = false,
         torchLit = false, hasShovel = false, hasStation0Bricks = false, foundGoldBrick = false, hasCoin = false,
         dugBricksStation1 = false, houseUnlocked = false, hasExtinguisher = false,
         hasMagnet = false, hasMissileCar = false, hasMissile = false, dugBricksStation8 = false, crabRemoved = false,
         fireRemoved = false, wallOnTrackRemoved = false, leverRepaired = false, hasHose = false, hasThruster = false,
         hasDome = false, hasLever = false, hasPower = false, hasCartridge1 = false, hasCartridge2 = false,
         hasCartridge3 = false, hasCannonball = false, hasHeatGun = false, doorLocked = true, dugBricksStation2 = false,
         dugBricksStation3 = false, dugBricksStation5 = false, boxOpened = false, axeFound = false,
         hasAxe = false, hasKey = false, hasStatue = false, hasLevers = false, forestRemoved = false,
         hasCannonCar = false, station3WallRemoved = false, hasCannon = false, hasRemote = false, hasDynamite = false,
         doorChecked = false, canTakeStatue = false, iceRemoved = false, hasCar = false,
         hasSoccerBall = false, pyramidOpen = false, bridgeBuilt = false, canTakeCartridge3 = false,
         mailboxChecked = false, hasGoldBrickStation0 = false, hasGoldBrickInBluePyramid = false,
         hasGoldBrickFromPyramid = false, hasGoldBrickInHouse = false;

   public int minigame1Score = 0, minigame2Score = -100, minigame3Score = 0;
   public boolean isPlayingMinigame1 = false, isPlayingMinigame2 = false, isPlayingMinigame3 = false,
         isPlayingMinigame4 = false, hasWon = false;
   private boolean beatMinigame1 = false, beatMinigame2 = false, beatMinigame3 = false;
   public boolean hasHeWonMinigame4 = false;
   private int WINDOWWIDTH, WINDOWHEIGHT;
   private Graphics bufferGraphics = null;
   private BufferStrategy bufferStrategy = null;
   private boolean running;
   public Thread thready;
   public Thread thready1;
   public int generalCount = 0;
   private int goldBricks = 0;

   private String bip;
   private Media hit;
   private MediaPlayer mediaPlayer;
   private String[] songNames = new String[] { "WreckItRalph.mp3", "System Override.mp3", "Spontaneous Me.mp3",
         "Song of Storms.mp3", "Song of Healing.mp3", "NGNL.mp3", "01. Half Remembered Dream.mp3",
         "02. We Built Our Own World.mp3", "03. Dream Is Collapsing.mp3", "04. Radical Notion.mp3", "05. Old Souls.mp3",
         "06. 528491.mp3", "07. Mombassa.mp3", "08. One Simple Idea.mp3", "09. Dream Within A Dream.mp3",
         "10. Waiting For A Train.mp3", "11. Paradox.mp3", "12. Time.mp3", "01 Three Flights Up.m4a",
         "The Sophomore Attempt - The Tides that Bind - 01 A Deluge Of Combination.mp3", "01 Three Flights Up.m4a",
         "15 S.T.A.Y..m4a", "14 Detach.m4a", "13 Coward.m4a", "08 Mountains.m4a", "02 Cornfield Chase.m4a",
         "06 Fountain of Dreams.m4a",
         "07 Pokemon Medley.m4a", "13 Fire Emblem.m4a", "21 Imperfect Lock.m4a" };
   private int songCounter = 0;
   private boolean playSong = true;

   private ImageObserver observer;
   private Image[] alphabet = new Image[26];
   private Image[] numbers = new Image[10];
   private Image background;
   private Image backgroundWithTextBox;
   private Image period;
   private Image colon;
   private Image underscore;
   private Image leftPeren;
   private Image rightPeren;
   private Image rightQuotes;
   private Image leftQuotes;
   private Image question;
   private Image exclam;
   private Image comma;
   private Image apostrophe;

   private double scaleHorizontal, scaleVertical;

   private String typedInput = "";

   private boolean checkTypedInput = false;

   private boolean displayingText = false;
   private int displayingTextCount = 0;
   private Command2 currentCommand;
   private ArrayList<ArrayList<String>> textToBeDisplayed;

   private int commandsTyped = 0;

   private int location = 0;

   private boolean gameStarted = false;

   public MatthewsBdayPart2(Dimension size) {
      this.thready = new Thread(
            new Runnable() {
               public void run() {
                  while (running) {
                     generalCount++;
                     DoLogic();
                     Draw();
                     DrawBackBufferToScreen();
                     try {
                        thready.sleep(10);
                     } catch (Exception e) {
                        e.printStackTrace();
                     }
                  }
               }
            });
      this.thready1 = new Thread(
            new Runnable() {
               public void run() {
                  playASong();
               }

               public void playASong() {
                  new JFXPanel();
                  bip = new File(songNames[songCounter]).toURI().toString();
                  hit = new Media(bip);
                  mediaPlayer = new MediaPlayer(hit);
                  mediaPlayer.setOnEndOfMedia(
                        new Runnable() {
                           public void run() {
                              mediaPlayer.stop();
                              if (songCounter < songNames.length - 1)
                                 songCounter++;
                              else
                                 songCounter = 0;
                              playASong();
                           }
                        });
                  // System.out.print(""); //NECESSARY FOR MUSIC TO WORK FOR SOME UNKNOWN REASON
                  mediaPlayer.play();
               }
            });
      this.addComponentListener(new ResizeListener());
      addKeyListener(this);
      this.setPreferredSize(size);
      this.setFocusable(true);
      WINDOWWIDTH = size.width;
      WINDOWHEIGHT = size.height;
      // System.out.println(WINDOWWIDTH + " x " + WINDOWHEIGHT);
      scaleHorizontal = WINDOWWIDTH * 1.0 / 224;
      scaleVertical = WINDOWHEIGHT * 1.0 / 126;
      // System.out.println(scaleHorizontal + " x " + scaleVertical);
      instantiateImages();
      running = true;
   }

   public void paint(Graphics g) {
      if (bufferStrategy == null) {
         this.createBufferStrategy(2);
         bufferStrategy = this.getBufferStrategy();
         bufferGraphics = bufferStrategy.getDrawGraphics();
         thready.start();
         thready1.start();
         // Request focus so keyboard input works
         this.requestFocus();
      }
   }

   public void DoLogic() {
      Graphics2D bufferGraphics2D = (Graphics2D) bufferGraphics;
      if (!isPlayingMinigame1 && !isPlayingMinigame2 && !isPlayingMinigame3) {
         if (checkTypedInput) {
            if (location == 0) {
               if (!gameStarted) {
                  if (checkInput(crash)) {
                     gameStarted = true;
                  }
               } else {
                  // check
                  if (wallRemoved) {
                     if (hasTrain) {
                        if (pyramidRemoved) {
                           if (hasStorageCar) {
                              if (dugBricksStation0) {
                                 if (checkInput(checkStation0WithoutWallTrainPyramidStorageBricks)) {
                                 }
                              } else if (checkInput(checkStation0WithoutWallTrainPyramidStorage)) {
                              }
                           } else if (checkInput(checkStation0WithoutWallTrainPyramid)) {
                           }
                        } else if (checkInput(checkStation0WithoutWallTrain)) {
                        }
                     } else if (checkInput(checkStation0WithoutWall)) {
                     }
                  } else if (checkInput(checkStation0)) {
                  }

                  // check spaceship

                  if (hasTorch && !hasTrain) {
                     if (checkInput(checkSpaceshipTorch)) {
                     }
                  } else if (lookingForCoin && !checkedSpaceshipForCoin) {
                     if (checkInput(checkSpaceshipCoin)) {
                        checkedSpaceshipForCoin = true;
                     }
                  } else {
                     if (hoseAdded) {
                        if (thrusterAdded) {
                           if (domeAdded) {
                              if (leverAdded) {
                                 if (powerAdded)
                                    if (checkInput(checkSpaceshipWithHoseThrusterDomeLeverPower)) {
                                    } else if (checkInput(checkSpaceshipWithHoseThrusterDomeLever)) {
                                    }
                              } else if (checkInput(checkSpaceshipWithHoseThrusterDome)) {
                              }
                           } else if (checkInput(checkSpaceshipWithHoseThruster)) {
                           }
                        } else if (checkInput(checkSpaceshipWithHose)) {
                        }
                     } else if (checkInput(checkSpaceship)) {
                     }
                  }

                  // check forest
                  if (checkInput(checkForest)) {
                  }
                  // check wall
                  if (!wallRemoved)
                     if (checkInput(checkWall)) {
                     }
                  // check train
                  if (!hasTrain)
                     if (checkInput(checkTrain)) {
                     }
                  // check pyramid
                  if (!pyramidRemoved) {
                     if (checkInput(checkPyramid)) {
                     }
                  } else {
                     if (!hasGoldBrickInBluePyramid)
                        if (checkInput(checkPyramidRemoved)) {
                           goldBricks++;
                           hasGoldBrickInBluePyramid = true;
                        }
                  }

                  // take torch
                  if (!hasTorch)
                     if (checkInput(takeTorch)) {
                        hasTorch = true;
                     }
                  // use torch on spaceship
                  if (hasTorch && !torchLit)
                     if (checkInput(useTorchOnSpaceship)) {
                        torchLit = true;
                     }
                  // use torch on wall
                  if (torchLit && !wallRemoved)
                     if (checkInput(useTorchOnWall)) {
                        wallRemoved = true;
                     }
                  // take train
                  if (wallRemoved && !hasTrain)
                     if (checkInput(takeTrain)) {
                        hasTrain = true;
                     }
                  // use shovel on pyramid
                  if (hasShovel && !pyramidRemoved)
                     if (checkInput(useShovelOnPyramid)) {
                        pyramidRemoved = true;
                     }
                  // take storage car
                  if (pyramidRemoved && !hasStorageCar) {
                     if (hasMagnet) {
                        if (checkInput(takeStorageCar)) {
                           hasStorageCar = true;
                        }
                     } else if (checkInput(takeStorageCarNoMagnet)) {
                     }

                  }

                  // use shovel on bricks
                  if (hasShovel && !dugBricksStation0) {
                     if (hasStorageCar) {
                        if (checkInput(useShovelOnBricksStation0)) {
                           dugBricksStation0 = true;
                        }
                     } else if (!foundGoldBrick)
                        if (checkInput(useShovelOnBricksWithoutStorageStation0)) {
                           foundGoldBrick = true;
                        }
                  }
                  if (lookingForCoin && checkedSpaceshipForCoin && !hasCoin) {
                     if (checkInput(takeCoin))
                        hasCoin = true;
                  }
                  if (foundGoldBrick) {
                     if (!hasGoldBrickStation0)
                        if (checkInput(takeGoldBrick)) {
                           goldBricks++;
                           hasGoldBrickStation0 = true;
                        }
                  }
                  if (hasHose && !hoseAdded)
                     if (checkInput(useHoseOnSpaceship))
                        hoseAdded = true;
                  if (hasThruster && !thrusterAdded)
                     if (checkInput(useThrusterOnSpaceship))
                        thrusterAdded = true;
                  if (hasDome && !domeAdded)
                     if (checkInput(useDomeOnSpaceship))
                        domeAdded = true;
                  if (hasLever && !leverAdded)
                     if (checkInput(useLeverOnSpaceship))
                        leverAdded = true;
                  if (hasPower && !powerAdded)
                     if (checkInput(usePowerOnSpaceship))
                        powerAdded = true;
                  if (hoseAdded && thrusterAdded && domeAdded && leverAdded && powerAdded) {
                     if (hasHeWonMinigame4) {
                        if (checkInput(takeOff)) {
                           hasWon = true;
                        }
                     } else {
                        if (checkInput(takeOffNoWin)) {
                           isPlayingMinigame4 = true;
                        }
                     }
                  }

               }

            } else if (location == 1) {
               // check
               if (dugBricksStation1) {
                  if (checkInput(checkStation1WithoutBricks)) {
                  }
               } else if (checkInput(checkStation1)) {
               }

               // check guy
               if (hasMagnet) {
                  if (checkInput(checkGuyStation1WithoutExtinguisherMagnet)) {
                  }
               } else if (hasExtinguisher) {
                  if (checkInput(checkGuyStation1WithoutExtinguisher)) {
                  }
               } else {
                  if (checkInput(checkGuyStation1)) {
                  }
               }
               // check house
               if (houseUnlocked) {
                  if (checkInput(checkHouse)) {
                  }
               } else if (checkInput(checkHouseLocked)) {
               }

               if (houseUnlocked) {
                  if (!hasHose && checkInput(takeHose)) {
                     hasHose = true;
                  }
                  if (!hasGoldBrickInHouse) {
                     if (checkInput(checkRoof)) {
                        goldBricks++;
                        hasGoldBrickInHouse = true;
                     }
                  }
               } else {
                  if (!houseUnlocked && hasKey) {
                     if (checkInput(useKeyOnHouse)) {
                        houseUnlocked = true;
                     }
                  }
               }
               // use shovel on bricks
               if (hasShovel && !dugBricksStation1) {
                  if (hasStorageCar) {
                     if (checkInput(useShovelOnBricksStation1)) {
                        dugBricksStation1 = true;
                     }
                  } else {
                     if (checkInput(useShovelOnBricksWithoutStorageStation1)) {
                     }
                  }
               }

               // talk to guy
               if (hasMagnet) {
                  if (checkInput(talkToGuyStation1)) {
                  }
               } else if (hasExtinguisher) {
                  if (checkInput(talkToGuyStation1WithExtinguisher)) {
                  }
               } else {
                  if (checkInput(talkToGuyStation1WithExtinguisherMagnet)) {
                     lookingForCoin = true;
                  }
               }

               // take extinguisher
               if (hasCoin && !hasExtinguisher)
                  if (checkInput(takeExtinguisher)) {
                     hasExtinguisher = true;
                  }

               // take magnet
               if (goldBricks >= 1 && !hasMagnet)
                  if (checkInput(takeMagnet)) {
                     hasMagnet = true;
                     goldBricks--;
                  }

               if (checkInput(checkTv)) {
               }
               if (hasCartridge1) {
                  if (checkInput(useCartridge1OnTv)) {
                     isPlayingMinigame1 = true;
                  }
               }
               if (hasCartridge2) {
                  if (checkInput(useCartridge2OnTv)) {
                     isPlayingMinigame2 = true;
                  }
               }
               if (hasCartridge3) {
                  if (checkInput(useCartridge3OnTv)) {
                     isPlayingMinigame3 = true;
                  }
               }
               if (beatMinigame1 && !hasHeatGun) {
                  if (checkInput(takePrize1)) {
                     hasHeatGun = true;
                  }
               }
               if (beatMinigame2 && !hasCannonball) {
                  if (checkInput(takePrize2)) {
                     hasCannonball = true;
                  }
               }
               if (beatMinigame3 && !hasLever) {
                  if (checkInput(takePrize3)) {
                     hasLever = true;
                  }
               }

            } else if (location == 2) {
               if (doorLocked) {
                  if (dugBricksStation2) {
                     if (checkInput(checkStation2)) {
                     }
                  } else {
                     if (checkInput(checkStation2NoBricks)) {
                     }
                  }
               } else {
                  if (boxOpened) {
                     if (checkInput(checkStation2DoorUnlockedBoxOpened)) {
                     }
                  } else {
                     if (checkInput(checkStation2DoorUnlocked)) {
                     }
                  }
               }
               if (!axeFound)
                  if (checkInput(checkFlowers)) {
                     axeFound = true;
                  }
               if (axeFound && !hasAxe)
                  if (checkInput(takeAxe)) {
                     hasAxe = true;
                  }
               if (doorLocked) {
                  if (checkInput(talkToGuyStation2)) {
                  } // crime scene need to bribe with gold brick
                  if (checkInput(checkShack)) {
                  }
               } else {
                  if (checkInput(talkToGuyStation2DoorOpen)) {
                  }
               }

               if (doorLocked && goldBricks > 0) {
                  if (checkInput(checkDoor)) {
                     doorLocked = false;
                     goldBricks--;
                  }
               } else if (checkInput(checkDoorNoGoldBricks)) {
               }
               if (!dugBricksStation2)
                  if (checkInput(useShovelOnBricksStation2)) {
                     dugBricksStation2 = true;
                  }
               if (dugBricksStation2)
                  if (checkInput(takeKey)) {
                     hasKey = true;
                  }
               if (!doorLocked) {
                  if (checkInput(checkBox)) {
                  }
                  if (hasStatue)
                     if (checkInput(statue52)) {
                        hasLevers = true;
                     }
               }
            } else if (location == 3) {
               if (dugBricksStation3) {
                  if (forestRemoved) {
                     if (hasCannonCar) {
                        if (station3WallRemoved) {
                           if (checkInput(checkStation3)) {
                           }
                        } else if (checkInput(checkStation3Wall)) {
                        }
                     } else if (checkInput(checkStation3CannonWall)) {
                     }
                  } else if (checkInput(checkStation3ForestCannonWall)) {
                  }
               } else if (checkInput(checkStation3BricksForestCannonWall)) {
               }
               if (hasCannon) {
                  if (checkInput(checkForestStation3HasCannon)) {
                  }
               } else {
                  if (checkInput(checkForestStation3)) {
                  }
               }
               if (!hasCannon)
                  if (checkInput(checkCannon)) {
                  }
               if (!station3WallRemoved)
                  if (checkInput(checkWallStation3)) {
                  }
               if (dugBricksStation3 && !hasRemote) {
                  if (checkInput(takeRemote)) {
                     hasRemote = true;
                  }
               } else if (checkInput(useShovelOnBricksStation3)) {
                  dugBricksStation3 = true;
               }
               if (hasDynamite && !station3WallRemoved)
                  if (checkInput(useDynamiteOnWall)) {
                     station3WallRemoved = true;
                  }
               if (hasAxe && !forestRemoved)
                  if (checkInput(useAxeOnForest)) {
                     forestRemoved = true;
                  }
               if (forestRemoved && !hasCannonCar)
                  if (checkInput(takeCannonCar)) {
                     hasCannonCar = true;
                  }
               if (doorChecked) {
                  if (checkInput(talkToGuyStation3WallRemoved)) {
                     canTakeStatue = true;
                  }
               } else if (checkInput(talkToGuyStation3)) {
               }
               if (canTakeStatue && !hasStatue)
                  if (checkInput(takeStatue)) {
                     hasStatue = true;
                  }
               if (station3WallRemoved) {
                  if (checkInput(checkIce)) {
                  }
                  if (hasHeatGun && !iceRemoved)
                     if (checkInput(useHeatGunOnIce)) {
                        iceRemoved = true;
                     }
                  if (iceRemoved && !wallOnTrackRemoved)
                     if (checkInput(checkLever)) {
                        wallOnTrackRemoved = true;
                     }
               }
               if (hasCar) {
                  if (checkInput(takeDome)) {
                     hasDome = true;
                  }
               } else if (checkInput(takeDomeNoCar)) {
               }

               if (!mailboxChecked)
                  if (checkInput(checkMailbox)) {
                     mailboxChecked = true;
                  }
               if (mailboxChecked)
                  if (checkInput(takeCartridge1)) {
                     hasCartridge1 = true;
                  }
               if (!hasDome) {
                  if (checkInput(checkRoadStation5)) {
                  }
               }
            } else if (location == 4) {
               if (checkInput(checkDoorStation4)) {
                  doorChecked = true;
               }
               if (checkInput(checkHouseStation4)) {
                  doorChecked = true;
               }
               if (doorChecked) {
                  if (checkInput(takeThruster)) {
                     hasThruster = true;
                  }
                  if (checkInput(takeBall)) {
                     hasSoccerBall = true;
                  }
               }
            } else if (location == 5) {
               if (bridgeBuilt) {
                  if (hasMissileCar) {
                     if (pyramidOpen) {
                        if (checkInput(checkStation5MissilePyramid)) {
                        }
                     } else {
                        if (checkInput(checkStation5Missile)) {
                        }
                     }
                  } else {
                     if (checkInput(checkStation5)) {
                     }
                  }
               } else {
                  if (checkInput(checkStation5NoBridge)) {
                  }
               }
               if (dugBricksStation0 && dugBricksStation1 && dugBricksStation5) {
                  if (checkInput(useBricksOnRoad) && !bridgeBuilt) {
                     bridgeBuilt = true;
                  }
               } else {
                  if (checkInput(useBricksOnRoadNotEnough)) {
                  }
               }
               if (!dugBricksStation5)
                  if (checkInput(useShovelOnBricksStation5)) {
                     dugBricksStation5 = true;
                  }
               if (!bridgeBuilt) {
                  if (checkInput(checkRoad)) {
                  }
               }
               if (bridgeBuilt) {
                  if (checkInput(checkRoadAfterBridge)) {
                  }
                  if (!pyramidOpen)
                     if (checkInput(checkPyramidStation5)) {
                     }
                  if (checkInput(talkToGuyStation5)) {
                  }
                  if (hasSoccerBall) {
                     if (checkInput(takeCartridge2)) {
                        hasCartridge2 = true;
                     }
                  }
                  if (hasRemote) {
                     if (checkInput(useRemoteOnPyramid)) {
                        pyramidOpen = true;
                     }
                  }
                  if (pyramidOpen) {
                     if (checkInput(talkToGuyStation5Pyramid)) {
                     }
                     if (goldBricks > 0 && !hasDynamite)
                        if (checkInput(takeDynamite)) {
                           hasDynamite = true;
                           goldBricks--;
                        }
                     if (checkInput(checkPyramidOpen)) {
                     }
                     if (!hasGoldBrickFromPyramid) {
                        if (checkInput(checkPyramidRoof)) {
                           goldBricks++;
                           hasGoldBrickFromPyramid = true;
                        }
                     }
                  }
                  if (checkInput(takeMissileCar) && !hasMissileCar) {
                     hasMissileCar = true;
                  }
               }
            } else if (location == 6) {
               if (hasPower) {
                  if (checkInput(checkStation6WithPower)) {
                  }
               } else {
                  if (checkInput(checkStation6)) {
                  }
               }
               if (checkInput(talkToSpider)) {
                  canTakeCartridge3 = true;
               }
               if (canTakeCartridge3)
                  if (checkInput(takeCartridge3)) {
                     hasCartridge3 = true;
                  }
               if (minigame1Score >= 8 && minigame2Score >= 0 && minigame3Score >= 8) {
                  if (checkInput(takePower)) {
                     hasPower = true;
                  }
               } else if (checkInput(takePowerWithoutWinning)) {
               }
            } else if (location == 7) {
               if (checkInput(checkStation8)) {
               }
               // talk to guy
               if (hasShovel) {
                  if (hasMissileCar) {
                     if (hasMissile) {
                        if (checkInput(talkToGuyStation8Nothing)) {
                        }
                     } else if (checkInput(talkToGuyStation8WithMissileCar)) {
                     }
                  } else if (checkInput(talkToGuyStation8WithoutMissileCar)) {
                  }
               } else if (checkInput(talkToGuyStation8)) {
               }
               // take shovel
               if (checkInput(takeShovel)) {
                  hasShovel = true;
               }
               // take missle
               if (dugBricksStation8)
                  if (checkInput(takeMissile)) {
                     hasMissile = true;
                  }

               // use shovel on bricks
               if (hasShovel) {
                  if (hasMissileCar && goldBricks >= 1) {
                     if (checkInput(useShovelOnBricksStation8) && !dugBricksStation8) {
                        hasMissile = true;
                        goldBricks--;
                        dugBricksStation8 = true;
                     }
                  } else if (checkInput(useShovelOnBricksStation8NoGoldBricks)) {
                  }
               }

            }
            if (hasTrain) {
               // move to station 0
               if (checkInput(moveToStation0)) {
                  location = 0;
               }
               // move to station 1
               if (checkInput(moveToStation1)) {
                  location = 1;
               }
               // move to station 2
               if (crabRemoved) {
                  if (checkInput(moveToStation2)) {
                     location = 2;
                  } else if (checkInput(moveToStation3)) {
                     location = 3;
                  }
               } else {
                  if (fireRemoved) {
                     if (checkInput(moveToStation2CrabRock)) {
                     }
                     if (checkInput(moveToStation3CrabRock)) {
                     }
                  } else {
                     if (checkInput(moveToStation2CrabFire)) {
                     }
                     if (checkInput(moveToStation3CrabFire)) {
                     }
                  }
               }
               // move to station 4
               if (wallOnTrackRemoved) {

                  if (checkInput(moveToStation4)) {
                     location = 4;
                  }
               } else {
                  if (checkInput(moveToStation4WallRock)) {
                  }
               }

               // move to station 5
               // move to station 6
               if (fireRemoved) {
                  if (checkInput(moveToStation5)) {
                     location = 5;
                  }
               } else {
                  if (checkInput(moveToStation5CrabFire)) {
                  }
               }
               // move to station 7
               if (leverRepaired) {
                  if (checkInput(moveToStation6)) {
                     location = 6;
                  }
               } else {
                  if (checkInput(moveToStation6LeverDamaged)) {
                  }
               }

               // move to station 8
               if (fireRemoved) {
                  if (checkInput(moveToStation7)) {
                     location = 7;
                  }
               } else {
                  if (checkInput(moveToStation7CrabFire)) {
                  }
               }
               if (hasExtinguisher)
                  if (checkInput(useExtinguisherOnFire))
                     fireRemoved = true;
               if (hasMissile)
                  if (checkInput(useMissileOnCrab))
                     crabRemoved = true;
               if (hasLevers)
                  if (checkInput(useLeversOnTrack))
                     leverRepaired = true;
               if (hasCannonball && hasCannonCar)
                  if (checkInput(useCannonOnRock)) {
                     hasCar = true;
                  }
            }
         }
         checkTypedInput = false;
         if (!beatMinigame1 && minigame1Score >= 5)
            beatMinigame1 = true;
         if (!beatMinigame2 && minigame2Score >= -10)
            beatMinigame2 = true;
         if (!beatMinigame3 && minigame3Score >= 5)
            beatMinigame3 = true;
      }
      bufferGraphics = (Graphics) bufferGraphics2D;
   }

   public void Draw() {
      bufferGraphics = bufferStrategy.getDrawGraphics();
      bufferGraphics.setColor(Color.BLACK);
      bufferGraphics.fillRect(0, 0, WINDOWWIDTH, WINDOWHEIGHT);
      bufferGraphics.setColor(Color.WHITE);
      if (displayingText)
         bufferGraphics.drawImage(backgroundWithTextBox, 0, 0, observer);
      else
         bufferGraphics.drawImage(background, 0, 0, observer);
      drawText("tec  ver: 3.6.97", (int) scaleHorizontal, (int) scaleVertical);
      drawText("location  : " + location, (int) (scaleHorizontal), (int) (scaleVertical * 19));
      if (hasGoldBrickStation0) {
         drawText("gld bricks: " + goldBricks, (int) (scaleHorizontal), (int) (scaleVertical * 32));
      }
      drawText("cmds typed: " + commandsTyped, (int) (scaleHorizontal), (int) (scaleVertical * 45));
      drawText("play time : " + (generalCount / 100), (int) (scaleHorizontal), (int) (scaleVertical * 58));
      if (!displayingText) {
         if (minigame1Score > 0) {
            drawText("high scores", (int) (scaleHorizontal), (int) (scaleVertical * 81));
            drawText("minigame1: " + minigame1Score, (int) (scaleHorizontal), (int) (scaleVertical * 95));
         }
         if (minigame2Score > -100) {
            drawText("minigame2: " + minigame2Score, (int) (scaleHorizontal), (int) (scaleVertical * 105));
         }
         if (minigame2Score > 0) {
            drawText("minigame3: " + minigame3Score, (int) (scaleHorizontal), (int) (scaleVertical * 115));
         }
      }
      Graphics2D bufferGraphics2D = (Graphics2D) bufferGraphics;
      if (displayingText) {
         drawText(textToBeDisplayed.get(displayingTextCount).get(0), (int) (scaleHorizontal * 3),
               (int) (scaleVertical * 85));
         drawText(textToBeDisplayed.get(displayingTextCount).get(1), (int) (scaleHorizontal * 3),
               (int) (scaleVertical * 96));
         drawText(textToBeDisplayed.get(displayingTextCount).get(2), (int) (scaleHorizontal * 3),
               (int) (scaleVertical * 107));
         drawText(currentCommand.getTalker(displayingTextCount), (int) (scaleHorizontal * 3),
               (int) (scaleVertical * 72));

      } else {
         if (minigame1Score > 0) {
            drawText(typedInput, (int) (scaleHorizontal * 92), (int) (scaleVertical * 100));
            if (generalCount % 50 < 25)
               drawText("_", (int) ((92 + (typedInput.length()) * 6) * scaleHorizontal), (int) (scaleVertical * 100));
         } else {
            drawText(typedInput, (int) (scaleHorizontal * 5), (int) (scaleVertical * 100));
            if (generalCount % 50 < 25)
               drawText("_", (int) ((5 + (typedInput.length()) * 6) * scaleHorizontal), (int) (scaleVertical * 100));
         }
      }
   }

   public void DrawBackBufferToScreen() {
      bufferStrategy.show();
      Toolkit.getDefaultToolkit().sync();
   }

   public boolean checkInput(Command2 c) {
      if (c.checkCommand(typedInput)) {
         typedInput = "";
         displayingText = true;
         displayingTextCount = 0;
         currentCommand = c;
         textToBeDisplayed = currentCommand.getMessage();
         commandsTyped++;
         return true;
      }
      return false;
   }

   public void drawText(String message, int x, int y) {
      int newX = x;
      char awesome;
      int wicked;
      for (int n = 0; n < message.length(); n++) {
         awesome = message.charAt(n);
         wicked = (int) awesome;
         if (wicked >= 48 && wicked <= 57)
            bufferGraphics.drawImage(numbers[wicked - 48], newX, y, observer);
         else if (wicked >= 97 && wicked <= 122)
            bufferGraphics.drawImage(alphabet[wicked - 97], newX, y, observer);
         else if (wicked == 46) {
            bufferGraphics.drawImage(period, newX, y, observer);
         } else if (wicked == 58) {
            bufferGraphics.drawImage(colon, newX, y, observer);
         } else if (wicked == 95) {
            bufferGraphics.drawImage(underscore, newX, y, observer);
         } else if (wicked == 33) {
            bufferGraphics.drawImage(exclam, newX, y, observer);
         } else if (wicked == 8220) {
            bufferGraphics.drawImage(leftQuotes, newX, y, observer);
         } else if (wicked == 8221) {
            bufferGraphics.drawImage(rightQuotes, newX, y, observer);
         } else if (wicked == 40) {
            bufferGraphics.drawImage(leftPeren, newX, y, observer);
         } else if (wicked == 41) {
            bufferGraphics.drawImage(rightPeren, newX, y, observer);
         } else if (wicked == 63) {
            bufferGraphics.drawImage(question, newX, y, observer);
         } else if (wicked == 44) {
            bufferGraphics.drawImage(comma, newX, y, observer);
         } else if (wicked == 8217 || wicked == 39) {
            bufferGraphics.drawImage(apostrophe, newX, y, observer);
         }
         newX += (int) (6 * scaleHorizontal);
      }
   }

   // public boolean playingMinigame1()
   // {
   // return isPlayingMinigame1;
   // }
   // public boolean playingMinigame2()
   // {
   // return isPlayingMinigame2;
   // }
   // public boolean playingMinigame3()
   // {
   // return isPlayingMinigame3;
   // }
   // public boolean hasHeWon()
   // {
   // return hasWon;
   // }
   public void instantiateImages() {
      try {
         alphabet[0] = ImageIO.read(new File("A.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[1] = ImageIO.read(new File("B.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[2] = ImageIO.read(new File("C.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[3] = ImageIO.read(new File("D.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[4] = ImageIO.read(new File("E.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[5] = ImageIO.read(new File("F.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[6] = ImageIO.read(new File("G.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[7] = ImageIO.read(new File("H.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[8] = ImageIO.read(new File("I.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[9] = ImageIO.read(new File("J.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[10] = ImageIO.read(new File("K.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[11] = ImageIO.read(new File("L.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[12] = ImageIO.read(new File("M.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[13] = ImageIO.read(new File("N.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[14] = ImageIO.read(new File("O.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[15] = ImageIO.read(new File("P.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[16] = ImageIO.read(new File("Q.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[17] = ImageIO.read(new File("R.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[18] = ImageIO.read(new File("S.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[19] = ImageIO.read(new File("T.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[20] = ImageIO.read(new File("U.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[21] = ImageIO.read(new File("V.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[22] = ImageIO.read(new File("W.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[23] = ImageIO.read(new File("X.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[24] = ImageIO.read(new File("Y.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         alphabet[25] = ImageIO.read(new File("Z.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[0] = ImageIO.read(new File("0.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[1] = ImageIO.read(new File("1.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[2] = ImageIO.read(new File("2.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[3] = ImageIO.read(new File("3.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[4] = ImageIO.read(new File("4.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[5] = ImageIO.read(new File("5.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[6] = ImageIO.read(new File("6.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[7] = ImageIO.read(new File("7.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[8] = ImageIO.read(new File("8.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         numbers[9] = ImageIO.read(new File("9.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         background = ImageIO.read(new File("MatthewsBdayMap.png")).getScaledInstance((int) (scaleHorizontal * 224),
               (int) (scaleVertical * 126), Image.SCALE_SMOOTH);
         backgroundWithTextBox = ImageIO.read(new File("MatthewsBdayMapWithTextBox.png"))
               .getScaledInstance((int) (scaleHorizontal * 224), (int) (scaleVertical * 126), Image.SCALE_SMOOTH);
         period = ImageIO.read(new File("period.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         colon = ImageIO.read(new File("colon.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         underscore = ImageIO.read(new File("underscore.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         leftPeren = ImageIO.read(new File("leftPeren.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         rightPeren = ImageIO.read(new File("rightPeren.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         question = ImageIO.read(new File("question.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         rightQuotes = ImageIO.read(new File("rightQuotes.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         leftQuotes = ImageIO.read(new File("leftQuotes.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         exclam = ImageIO.read(new File("exclam.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         comma = ImageIO.read(new File("comma.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
         apostrophe = ImageIO.read(new File("apostrophe.png")).getScaledInstance((int) (scaleHorizontal * 5),
               (int) (scaleVertical * 9), Image.SCALE_SMOOTH);
      } catch (IOException e) {
      }
   }

   public void keyPressed(KeyEvent e) {
      int keyCode = e.getKeyCode();
      if (!isPlayingMinigame1 && !isPlayingMinigame2 && !isPlayingMinigame3) {
         if (keyCode == KeyEvent.VK_ENTER) {
            if (displayingText) {
               if (displayingTextCount < textToBeDisplayed.size() - 1)
                  displayingTextCount++;
               else
                  displayingText = false;
            } else {
               checkTypedInput = true;
            }
            // System.out.println(typedInput);

         } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
            if (typedInput.length() > 0)
               typedInput = typedInput.substring(0, typedInput.length() - 1);
         }
         // Character input is handled in keyTyped() method
      }
   }

   public ArrayList<String> fileToArrayList(String filename) {
      try {
         File file = new File(filename);
         String currentDir = System.getProperty("user.dir");

         // Try current directory first
         if (!file.exists() || !file.isFile()) {
            file = new File(currentDir, filename);
         }

         // If still not found, try Year2 subdirectory
         if (!file.exists() || !file.isFile()) {
            file = new File(currentDir + "/Year2", filename);
         }

         // If still not found, try parent/Year2
         if (!file.exists() || !file.isFile()) {
            file = new File(currentDir + "/Matthews Lego Bday/Year2", filename);
         }

         if (!file.exists() || !file.isFile()) {
            System.out.println("ERROR: Cannot find file: " + filename);
            System.out.println("  Looked in: " + new File(filename).getAbsolutePath());
            System.out.println("  Looked in: " + new File(currentDir, filename).getAbsolutePath());
            return new ArrayList<String>();
         }

         // Read file with proper encoding handling for CRLF/CR/LF line endings
         // Use BufferedReader which handles line endings better than Scanner
         BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
         ArrayList<String> speech = new ArrayList<String>();
         String line;
         while ((line = reader.readLine()) != null) {
            // readLine() automatically handles CRLF, CR, and LF
            speech.add(line.toLowerCase());
         }
         reader.close();

         if (speech.isEmpty()) {
            System.out.println("WARNING: " + filename + " appears to be empty");
            System.out.println("  File exists: " + file.exists());
            System.out.println("  File path: " + file.getAbsolutePath());
            System.out.println("  File size: " + file.length() + " bytes");
         }
         return speech;
      } catch (Exception e) {
         System.out.println("ERROR reading file: " + filename);
         System.out.println("  Current directory: " + System.getProperty("user.dir"));
         e.printStackTrace();
         return new ArrayList<String>();
      }
   }

   public void keyReleased(KeyEvent e) {
   }

   public void keyTyped(KeyEvent e) {
      char c = e.getKeyChar();
      if (!isPlayingMinigame1 && !isPlayingMinigame2 && !isPlayingMinigame3) {
         // Handle character input (ignore Enter, Backspace, and undefined chars)
         if (c != KeyEvent.CHAR_UNDEFINED && c != '\n' && c != '\b' && c != '\r') {
            String s = Character.toString(c).toLowerCase();
            if ("abcdefghijklmnopqrstuvwxyz1234567890 ".indexOf(s) != -1) {
               typedInput += s;
            }
         }
      }
   }

   class ResizeListener implements ComponentListener {
      public void componentHidden(ComponentEvent e) {
      }

      public void componentMoved(ComponentEvent e) {
      }

      public void componentShown(ComponentEvent e) {
      }

      public void componentResized(ComponentEvent e) {
         Dimension size = e.getComponent().getBounds().getSize();
         // this.setPreferredSize(size);
         WINDOWWIDTH = size.width;
         WINDOWHEIGHT = size.height;
         scaleHorizontal = WINDOWWIDTH * 1.0 / 224;
         scaleVertical = WINDOWHEIGHT * 1.0 / 126;
         instantiateImages();
      }
   }
}