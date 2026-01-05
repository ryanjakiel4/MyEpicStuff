import sun.audio.*;
import java.io.*;

    public class Sound {

        public void music() {

            AudioStream backgroundMusic;
            AudioData musicData;
            AudioPlayer musicPlayer = AudioPlayer.player;
            ContinuousAudioDataStream loop = null;
            try {
                backgroundMusic = new AudioStream(new FileInputStream("WreckItRalph.mp3"));
                musicData = backgroundMusic.getData();
                loop = new ContinuousAudioDataStream(musicData);
                musicPlayer.start(loop);
            } catch (IOException error) { System.out.println(error);
            }
        }
    }