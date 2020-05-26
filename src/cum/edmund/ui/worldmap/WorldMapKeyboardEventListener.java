package cum.edmund.ui.worldmap;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import cum.edmund.audio.AudioEngine;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.maps.world.WalkOutcome;

/**
 * Processes keyboard events, eg makes the player walk when a directional button is pressed
 * 
 * @author Ed
 *
 */
public class WorldMapKeyboardEventListener implements KeyListener {

  private Set<Integer> keysPressed;

  private WorldMapView worldMapView;

  public WorldMapKeyboardEventListener(WorldMapView worldMapView) {

    this.worldMapView = worldMapView;
    keysPressed = ConcurrentHashMap.newKeySet();
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    // Record that this key has been pressed
    keysPressed.add(e.getKeyCode());

    // Process all keys
    processKeys();

    worldMapView.drawThings();
  }

  private void processKeys() {

    processWalkKeys();

    processOtherKeys();
  }

  private void processOtherKeys() {
    // Esc should open menu etc
  }

  private void processWalkKeys() {
    Direction direction = null;

    if (isPressed(KeyEvent.VK_UP) && isPressed(KeyEvent.VK_RIGHT)) {
      direction = Direction.NORTH_EAST;
    } else if (isPressed(KeyEvent.VK_RIGHT) && isPressed(KeyEvent.VK_DOWN)) {
      direction = Direction.SOUTH_EAST;
    } else if (isPressed(KeyEvent.VK_DOWN) && isPressed(KeyEvent.VK_LEFT)) {
      direction = Direction.SOUTH_WEST;
    } else if (isPressed(KeyEvent.VK_LEFT) && isPressed(KeyEvent.VK_UP)) {
      direction = Direction.NORTH_WEST;
    } else if (isPressed(KeyEvent.VK_UP)) {
      direction = Direction.NORTH;
    } else if (isPressed(KeyEvent.VK_DOWN)) {
      direction = Direction.SOUTH;
    } else if (isPressed(KeyEvent.VK_LEFT)) {
      direction = Direction.WEST;
    } else if (isPressed(KeyEvent.VK_RIGHT)) {
      direction = Direction.EAST;
    }

    if (direction != null) {
      WalkOutcome outcome = worldMapView.getWorldMap().walk(direction);

      if (outcome.isFight()) {
        AudioEngine.startFightBackgroundMusic();
        
        worldMapView.getUi().showFightView();
      }
    }

  }

  private boolean isPressed(int key) {
    return keysPressed.contains(key);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // Record that this key has been released
    keysPressed.remove(e.getKeyCode());
  }
  
  public void reset() {
    keysPressed.clear();
  }
}
