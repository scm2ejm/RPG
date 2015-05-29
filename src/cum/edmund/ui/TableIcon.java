package cum.edmund.ui;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TableIcon extends JFrame {

  public TableIcon() {
    URL catUrl = ClassLoader.getSystemResource("c2.jpg");

    ImageIcon aboutIcon = new ImageIcon(catUrl);
    ImageIcon addIcon = new ImageIcon("c2.jpg");
    ImageIcon copyIcon = new ImageIcon("cat.gif");

    String[] columnNames = {"a", "b"};
    Object[][] data = { {aboutIcon, "About"}, {addIcon, "Add"}, {copyIcon, "Copy"},};

    DefaultTableModel model = new DefaultTableModel(data, columnNames);

    JTable table = new JTable(model) {
      // Returning the Class of each column will allow different
      // renderers to be used based on Class
      public Class<?> getColumnClass(int column) {
        return getValueAt(0, column).getClass();
      }
    };
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setTableHeader(null);

    getContentPane().add(table);

    table.getParent().addComponentListener(new AssFuckTableResizer(table));

  }

  public static void main(String[] args) {
    TableIcon frame = new TableIcon();
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

}
