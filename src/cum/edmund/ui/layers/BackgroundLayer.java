package cum.edmund.ui.layers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import cum.edmund.models.maps.world.StretchIcon;

public class BackgroundLayer extends JPanel {
  /**
   * The layer for background tiles. I will possibly create a second background layer so that tiles
   * overlap but this is TBD
   * 
   * @author Ed
   */
  public BackgroundLayer() {
    setLayout(new GridBagLayout());

    StretchIcon grass = new StretchIcon(ClassLoader.getSystemResource("grass.png"), false);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    gbc.weightx = 1;
    gbc.weighty = 1;

    for (int x = 0; x < 11; x++) {
      for (int y = 0; y < 11; y++) {
        gbc.gridx = x;
        gbc.gridy = y;
        add(new JLabel(grass), gbc);
      }
    }
  }
}
