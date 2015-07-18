package cum.edmund.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.core.Configuration;
import cum.edmund.helpers.EnemiesHelper;
import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;

@SuppressWarnings("serial")
public class UI extends JFrame {

  private static final Logger LOGGER;

  private Set<Integer> keysPressed;
  private WorldMap worldMap;
  private Hero fucker;
  private DefaultTableModel model;
  private Object[] columnNames;

  static {
    LOGGER = LoggerFactory.getLogger(UI.class);
  }

  public UI() {
    super("Return to AssFuck Castle");
    setSize(200, 200);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    int size = Configuration.UI_GRID_SIZE;

    List<String> columnNames = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      columnNames.add(String.valueOf(i));
    }
    this.columnNames = columnNames.toArray();
    
    Object[][] data = new Object[size][size];

    model = new DefaultTableModel(data, this.columnNames);

    JTable table = new AssFuckGrid(model);
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setTableHeader(null);
    getContentPane().add(table);

    // Set table resizer
    table.getParent().addComponentListener(new AssFuckTableResizer(table));

    keysPressed = ConcurrentHashMap.newKeySet();
    fucker = new Hero("fucker", 0, 0);
    worldMap = new WorldMap(fucker);
//    model.setDataVector(worldMap.toArray(), columnNames.toArray());

    KeyListener listener = new MyKeyListener();
    addKeyListener(listener);
    setFocusable(true);
    table.setEnabled(false);

    // TODO REMOVE THESE TEST OBJECTS
    worldMap.put(new House("Fuckers house", new Coord(4, 2)));
    worldMap.put(EnemiesHelper.createButtasaurusAss(4, new Coord(-2, -2)));

  }

  public static void main(String[] args) {
    new UI();
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
      model.setDataVector(worldMap.createView(), columnNames);
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

      LOGGER.debug(worldMap.toString());

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
