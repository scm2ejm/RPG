package experimental;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import cum.edmund.models.maps.world.StretchIcon;

public class ExperimentalUI {

  public static void main(String... args) {

    JPanel layers = new JPanel();
    layers.setLayout(new OverlayLayout(layers));
    
    JFrame frame = new JFrame("Things");
    frame.setVisible(true);
    frame.add(layers);
//    frame.add(icon());
//    layers.add(background());
    layers.add(icon());
    frame.validate();
    

  }

  private static Component icon() {
    URL url = ClassLoader.getSystemResource("house.png");
    StretchIcon stretchIcon = new StretchIcon(url);
    JLabel label = new JLabel(stretchIcon);
//    label.setIcon();
    label.setVisible(true);
    return label;
  }

  private static JPanel background() {
    JPanel panel = new JPanel();
    GridBagLayout layout = new GridBagLayout();
    panel.setLayout(layout);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;


    // Put constraints on different buttons
    for (int x = 0; x < 11; x++) {
      for (int y = 0; y < 11; y++) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JPanel thing = new JPanel();
        if (x % 2 == 0) {
          thing.setBackground(y % 2 == 0 ? Color.BLACK : Color.RED);
        } else {
          thing.setBackground(y % 2 != 0 ? Color.BLACK : Color.RED);
        }

        panel.add(thing, gbc);
      }
    }

    return panel;
  }

}
