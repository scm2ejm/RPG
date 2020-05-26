package cum.edmund.ui.worldmap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableColumnModel;
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
    setIntercellSpacing(new Dimension(0,0));
    setShowGrid(false);
    setShowVerticalLines(false);
    setShowHorizontalLines(false);
    setRowMargin(0);
    setColumnModel(new CustomDefaultTableColumnModel());
    setCellSelectionEnabled(false);
    setBackground(Color.LIGHT_GRAY);
    setColumnSelectionAllowed(false);
    setRowSelectionAllowed(false);
    setColumnSelectionAllowed(false);
    setBorder(new EmptyBorder(0, 0, 0, 0));

  }

  @Override
  public boolean editCellAt(int row, int column, java.util.EventObject e) {
    return false;
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
  
  private class CustomDefaultTableColumnModel extends DefaultTableColumnModel{

    @Override
    public void setColumnMargin(int newMargin) {
       //Always set ColumnMargin to zero.
       //Because after the column data binding its internally set one as ColumnMargin.
      //That course to paint white color grid.
      //To stop we override the setColumnMargin and pass zero to ColumnMargin.
     super.setColumnMargin(0);
     }
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
