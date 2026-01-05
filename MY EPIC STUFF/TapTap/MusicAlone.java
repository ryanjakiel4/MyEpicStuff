import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
public class MusicAlone extends Application
{
   public static void main(String[] args)
   {
      Application.launch();
   }
   public void init()
   {
   }
   public void start(Stage arg0) throws Exception 
   {
      String bip = new File("WreckItRalph.mp3").toURI().toString();
      Media hit = new Media(bip);
      MediaPlayer mediaPlayer = new MediaPlayer(hit);
      mediaPlayer.play();
      mediaPlayer.setOnEndOfMedia(
            new Runnable() {
               public void run()
               {
                  Platform.exit();
               }
            });
      //Platform.exit();
   }
   public void stop()
   {
   }
}