package cum.edmund.audio;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used for playing sound effects or backgroundMusic
 * 
 * @author Ed
 *
 */
public class AudioEngine {

  private static final Logger LOGGER = LoggerFactory.getLogger(AudioEngine.class);

  private static final String FIGHT_BACKGROUND_FILE = "fight.wav";

  private static Clip backgroundMusic;

  private static void playBackgroundMusic(String filename) {

    // Stop previous track from playing
    if (backgroundMusic != null) {
      backgroundMusic.stop();
    }

    // Play new track
    try {
      URL url = ClassLoader.getSystemResource(filename);
      File yourFile = new File(url.toURI());

      AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
      AudioFormat format = stream.getFormat();
      Info info = new DataLine.Info(Clip.class, format);
      backgroundMusic = (Clip) AudioSystem.getLine(info);
      backgroundMusic.open(stream);
      backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
      backgroundMusic.start();
    } catch (Exception e) {
      LOGGER.error("Error playing audio", e);
    }
  }
  
  public static void startFightBackgroundMusic() {
    playBackgroundMusic(FIGHT_BACKGROUND_FILE);
  }
}
