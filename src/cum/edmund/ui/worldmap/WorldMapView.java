package cum.edmund.ui.worldmap;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cum.edmund.core.Configuration;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.UI;

/**
 * This will be used for the world map
 * 
 * @author Ed
 *
 */
@SuppressWarnings("serial")
public class WorldMapView extends JTable {

  private UI ui;
  private Object[] columnNames;
  private WorldMap worldMap;
  private DefaultTableModel model;

  public WorldMapView(UI ui, WorldMap worldMap) {
    super();
    this.ui = ui;
    this.worldMap = worldMap;
    
    columnNames = columnNames();
    model = buildTable(columnNames);
    
    setModel(model);
    setTableHeader(null);
    setEnabled(true);
    setPreferredScrollableViewportSize(getPreferredSize());
    setupKeyboardListener();
  }

  @Override
  public Class<?> getColumnClass(int column) {
    Object value = getValueAt(0, column);
    return value == null ? Object.class : value.getClass();
  }

  private DefaultTableModel buildTable(Object[] columnNames) {
    int size = Configuration.UI_GRID_SIZE;

    Object[][] data = new Object[size][size];

    return new DefaultTableModel(data, columnNames);
  }

  private Object[] columnNames() {
    List<String> columnNames = new ArrayList<>();

    for (int i = 0; i < Configuration.UI_GRID_SIZE; i++) {
      columnNames.add(String.valueOf(i));
    }

    return columnNames.toArray();
  }
  
  public void drawThings() {
    model.setDataVector(worldMap.createView(this), columnNames);
  }

  private void setupKeyboardListener() {
    KeyListener listener = new WorldMapKeyboardEventListener(this);
    addKeyListener(listener);
  }

  public WorldMap getWorldMap() {
    return worldMap;
  }

  public UI getUi() {
    return ui;
  }
}
