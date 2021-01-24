package cum.edmund.audio;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
  
  private static final String WIN_FIGHT_FILE = "win-fight.wav";

  private static final List<String> BURP_FILES =
      Arrays.asList("burp1.wav", "burp2.wav", "burp3.wav");

  private static final List<String> FART_FILES = Arrays.asList("fart1.wav", "fart2.wav");

  private static final Random RANDOM = new Random();

  private static Clip backgroundMusic;

  public static void playFart() {
    playSoundEffect(FART_FILES.get(RANDOM.nextInt(FART_FILES.size())));
  }

  public static void playBurp() {
    playSoundEffect(BURP_FILES.get(RANDOM.nextInt(BURP_FILES.size())));
  }

  public static void playSoundEffect(String filename) {
    try {
      Clip soundEffect = stream(filename);
      soundEffect.start();
    } catch (Exception e) {
      LOGGER.error("Error playing audio", e);
    }
  }

  private static void playBackgroundMusic(String filename) {

    // Stop previous track from playing
    if (backgroundMusic != null) {
      backgroundMusic.stop();
    }

    // Play new track
    try {
      backgroundMusic = stream(filename);
      backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
      backgroundMusic.start();
    } catch (Exception e) {
      LOGGER.error("Error playing audio", e);
    }
  }

  private static Clip stream(String filename) throws Exception {
    URL url = ClassLoader.getSystemResource(filename);
    File yourFile = new File(url.toURI());
    AudioInputStream stream = AudioSystem.getAudioInputStream(yourFile);
    AudioFormat format = stream.getFormat();
    Info info = new DataLine.Info(Clip.class, format);
    Clip clip = (Clip) AudioSystem.getLine(info);
    clip.open(stream);
    return clip;
  }

  public static void startFightBackgroundMusic() {
    playBackgroundMusic(FIGHT_BACKGROUND_FILE);
  }

  /**
   * Stops background track from playing
   */
  public static void stopBackgroundMusic() {
    if (backgroundMusic == null) {
      return;
    }

    backgroundMusic.stop();
  }

  public static void playWinFightMusic() {
    playSoundEffect(WIN_FIGHT_FILE);
  }

}
