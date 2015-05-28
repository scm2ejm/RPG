package cum.edmund.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JTable;

/**
 * Resizes table when parent is resized
 * 
 * @author Ed
 *
 */
public class AssFuckTableResizer extends ComponentAdapter {

  private JTable table;

  public AssFuckTableResizer(JTable table) {
    this.table = table;
  }

  @Override
  public void componentResized(final ComponentEvent e) {
    if (table.getPreferredSize().width < table.getParent().getWidth()) {
      table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    } else {
      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    table.setRowHeight(table.getParent().getHeight() / table.getRowCount());

  }

}
