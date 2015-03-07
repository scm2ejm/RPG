package cum.edmund.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;

@SuppressWarnings("serial")
public class UI extends JPanel {

  private static final Logger LOGGER;

  private Set<Integer> keysPressed;
  private WorldMap worldMap;
  private Hero fucker;

  static {
    LOGGER = LoggerFactory.getLogger(UI.class);
  }

  public UI() {
    keysPressed = ConcurrentHashMap.newKeySet();
    worldMap = new WorldMap();
    fucker = new Hero("fucker", 0, 0);

    KeyListener listener = new MyKeyListener();
    addKeyListener(listener);
    setFocusable(true);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Return to AssFuck Castle");
    UI ui = new UI();
    frame.add(ui);
    frame.setSize(200, 200);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public class MyKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
      // Record that this key has been pressed
      keysPressed.add(e.getKeyCode());

      // Process all keys
      processKeys();
    }

    private void processKeys() {

      processWalkKeys();

      processOtherKeys();
    }
    
    private void processOtherKeys() {
      // TODO Auto-generated method stub
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

      WalkOutcome outcome = WalkHelper.walk(fucker, direction, worldMap);

      if (outcome.isFight()) {
        LOGGER.debug("Starting fight!");
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
}
