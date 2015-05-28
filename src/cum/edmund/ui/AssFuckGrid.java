package cum.edmund.ui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This will be used for the world map
 * 
 * @author Ed
 *
 */
@SuppressWarnings("serial")
public class AssFuckGrid extends JTable {

  public AssFuckGrid(DefaultTableModel model) {
    super(model);
    setTableHeader(null);
  }

  @Override
  public Class<?> getColumnClass(int column) {
    Object value = getValueAt(0, column);
    return value == null ? Object.class : value.getClass();
  }

}
