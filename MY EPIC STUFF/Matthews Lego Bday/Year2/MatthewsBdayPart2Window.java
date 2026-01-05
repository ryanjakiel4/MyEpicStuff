
//check station 3... wall removed/not removed
// check station 4... non existant
// check door/house station 4 "?"
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class MatthewsBdayPart2Window extends JFrame {
   public MatthewsBdayPart2Window(String name) {
      super(name);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Dimension windowSize = new Dimension(99+3,176+27); // add 10 to the y
      // component to make up for toolbar size
      Dimension windowSize = new Dimension(224, 126);
      // Dimension windowSize = new Dimension(224,126);
      // Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.setSize(windowSize);
   }

   public static void main(String[] args) {
      MatthewsBdayPart2Window window = new MatthewsBdayPart2Window("type crash.");

      window.setResizable(true);

      Container contentPane = window.getContentPane();

      contentPane.setLayout(new GridLayout(1, 1));
      MatthewsBdayPart2 mainProgram = new MatthewsBdayPart2(window.getSize());
      WordMini minigame1 = new WordMini(window.getSize());
      ArrowMini minigame2 = new ArrowMini(window.getSize());
      MemoryMini minigame3 = new MemoryMini(window.getSize());
      NewGameEndlessMatthew minigame4 = new NewGameEndlessMatthew(window.getSize());
      contentPane.add(mainProgram);
      window.setVisible(true);
      // Request focus for keyboard input
      mainProgram.requestFocus();
      boolean hasNotWon = true;
      int whatIsOnScreen = 0; // 0 is main program, 1 is minigame 1, 2 is minigame 2
      while (hasNotWon) {
         // mainProgram.isPlayingMinigame1= mainProgram.isPlayingMinigame1;
         // mainProgram.isPlayingMinigame2= mainProgram.isPlayingMinigame2;
         // mainProgram.isPlayingMinigame3= mainProgram.isPlayingMinigame3;
         System.out.print("");
         // System.out.println(mainProgram.isPlayingMinigame1+""+mainProgram.isPlayingMinigame2+""+mainProgram.isPlayingMinigame3+""+whatIsOnScreen);
         if (mainProgram.isPlayingMinigame1 && whatIsOnScreen != 1) {
            // contentPane.remove(mainProgram); //comment for split screen
            minigame1 = new WordMini(window.getSize());
            minigame1.isPlaying = true;
            contentPane.add(minigame1);
            window.setVisible(true);
            whatIsOnScreen = 1;
         } else if (mainProgram.isPlayingMinigame2 && whatIsOnScreen != 2) {
            // contentPane.remove(mainProgram); //comment for split screen
            minigame2 = new ArrowMini(window.getSize());
            minigame2.isPlaying = true;
            contentPane.add(minigame2);
            window.setVisible(true);
            whatIsOnScreen = 2;
         } else if (mainProgram.isPlayingMinigame3 && whatIsOnScreen != 3) {
            // contentPane.remove(mainProgram); //comment for split screen
            minigame3 = new MemoryMini(window.getSize());
            minigame3.isPlaying = true;
            contentPane.add(minigame3);
            window.setVisible(true);
            whatIsOnScreen = 3;
         } else if (mainProgram.isPlayingMinigame4 && whatIsOnScreen != 4) {
            // contentPane.remove(mainProgram); //comment for split screen
            minigame4 = new NewGameEndlessMatthew(window.getSize());
            // minigame4.isPlaying = true;
            contentPane.add(minigame4);
            window.setVisible(true);
            whatIsOnScreen = 4;
         } else if (!mainProgram.isPlayingMinigame1 && !mainProgram.isPlayingMinigame2
               && !mainProgram.isPlayingMinigame3 && !mainProgram.isPlayingMinigame4 && whatIsOnScreen != 0) {
            if (whatIsOnScreen == 1) {
               // System.out.println("HERE1");
               minigame1.isPlaying = false;
               mainProgram.isPlayingMinigame1 = false;
               contentPane.remove(minigame1);

            } else if (whatIsOnScreen == 2) {
               if (minigame2.score > mainProgram.minigame2Score)
                  mainProgram.minigame2Score = minigame2.score;
               minigame2.isPlaying = false;
               contentPane.remove(minigame2);
            } else if (whatIsOnScreen == 3) {
               minigame3.isPlaying = false;
               contentPane.remove(minigame3);
            } else if (whatIsOnScreen == 4) {
               // minigame4.isPlaying = false;
               contentPane.remove(minigame4);
            }
            // contentPane.add(mainProgram); //comment for split screen
            // System.out.println("HERE2" + mainProgram.isPlayingMinigame1 + "" +
            // mainProgram.isPlayingMinigame2 + "" + mainProgram.isPlayingMinigame3);
            window.setVisible(true);
            whatIsOnScreen = 0;
         }
         hasNotWon = !mainProgram.hasWon;
         if (whatIsOnScreen == 1) {
            mainProgram.isPlayingMinigame1 = !minigame1.hasWon();
            if (minigame1.stringsTyped > mainProgram.minigame1Score)
               mainProgram.minigame1Score = minigame1.stringsTyped;
         } else if (whatIsOnScreen == 2) {
            mainProgram.isPlayingMinigame2 = !minigame2.hasWon();
         } else if (whatIsOnScreen == 3) {
            mainProgram.isPlayingMinigame3 = !minigame3.hasWon();
            if (minigame3.arrows - 2 > mainProgram.minigame3Score)
               mainProgram.minigame3Score = minigame3.arrows - 2;
         } else if (whatIsOnScreen == 4) {
            mainProgram.isPlayingMinigame4 = !minigame4.hasHeQuit;
            mainProgram.hasHeWonMinigame4 = minigame4.hasHeWon;
         }
      }

   }
}
