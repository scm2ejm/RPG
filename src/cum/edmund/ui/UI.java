package cum.edmund.ui;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cum.edmund.core.Configuration;
import cum.edmund.core.Engine;

/**
 * Basic tile-based UI for Return To Assfuck Castle
 * 
 * @author Ed
 *
 */
@SuppressWarnings("serial")
public class UI extends JFrame {

  private DefaultTableModel model;
  private Object[] columnNames;
  private Engine engine;

  public UI() {
    super("Return to AssFuck Castle");

    columnNames = columnNames();
    model = buildTable();
    engine = new Engine();

    setupFrame();
    setupTable();
    setupKeyboardListener();
    
  }

  private void setupKeyboardListener() {
    KeyListener listener = new KeyboardEventListener(this, engine);
    addKeyListener(listener);
  }

  private void setupTable() {
    JTable table = new AssFuckGrid(model);
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setTableHeader(null);
    getContentPane().add(table);

    // Set table resizer
    table.getParent().addComponentListener(new AssFuckTableResizer(table));
    table.setEnabled(false);
  }

  private void setupFrame() {
    setSize(200, 200);
    setVisible(true);
    setFocusable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private DefaultTableModel buildTable() {
    int size = Configuration.UI_GRID_SIZE;

    Object[][] data = new Object[size][size];

    return new DefaultTableModel(data, this.columnNames);
  }

  private Object[] columnNames() {
    List<String> columnNames = new ArrayList<>();

    for (int i = 0; i < Configuration.UI_GRID_SIZE; i++) {
      columnNames.add(String.valueOf(i));
    }

    return columnNames.toArray();
  }

  public static void main(String[] args) {
    new UI();
  }

  public void drawThings() {
    model.setDataVector(engine.getWorldMap().createView(), columnNames);
  }
}
