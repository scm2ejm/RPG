package cum.edmund.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import cum.edmund.core.Engine;
import cum.edmund.models.characters.Direction;

/**
 * Processes keyboard events, eg makes the player walk when a directional button is pressed
 * 
 * @author Ed
 *
 */
public class KeyboardEventListener implements KeyListener {

  private Set<Integer> keysPressed;
  private final UI ui;
  private final Engine engine;

  public KeyboardEventListener(UI ui, Engine engine) {
    this.ui = ui;
    this.engine = engine;
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

    ui.drawThings();
  }

  private void processKeys() {

    processWalkKeys();

    processOtherKeys();
  }

  private void processOtherKeys() {
    // I guess fight menus etc would go here
    
    // TODO: Remove this!
    if (isPressed(KeyEvent.VK_A)) {
      ui.showFightPanel();
    }
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
      engine.walk(direction);
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
}
