import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import java.util.*;
public class Music extends Application
{
   private String fn = "";
   //private boolean done = false;
   public Music()
   {
   }
   public void setFileName(String filename)
   {
      fn = new String(filename);
   }
   public void playSong(Music m)
   {
      if(fn.length() > 0)
         m.launch(new String[]{fn});
   }
   public void init()
   {
      List<String> s = this.getParameters().getUnnamed();
      this.fn = s.get(0);
   }
   public void start(Stage arg0)
   {
      String bip = new File(this.fn).toURI().toString();
      Media hit = new Media(bip);
      MediaPlayer mediaPlayer = new MediaPlayer(hit);
      mediaPlayer.play();
      /*
      mediaPlayer.setOnEndOfMedia(
            new Runnable() {
               public void run()
               {
                  Platform.exit();
                  done = true;
               }
            });
            */      
   }
}