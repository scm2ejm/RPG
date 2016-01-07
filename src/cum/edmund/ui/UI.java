package cum.edmund.ui;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cum.edmund.core.Configuration;
import cum.edmund.core.Engine;

@SuppressWarnings("serial")
public class UI extends JFrame {

  private DefaultTableModel model;
  private Object[] columnNames;
  private Engine engine;

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

    engine = new Engine();
    
    JTable table = new AssFuckGrid(model);
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setTableHeader(null);
    getContentPane().add(table);

    // Set table resizer
    table.getParent().addComponentListener(new AssFuckTableResizer(table));


    KeyListener listener = new KeyboardEventListener(this, engine);
    addKeyListener(listener);
    setFocusable(true);
    table.setEnabled(false);

  }

  public static void main(String[] args) {
    new UI();
  }

  public void drawThings() {
    model.setDataVector(engine.getWorldMap().createView(), columnNames);
  }
}
