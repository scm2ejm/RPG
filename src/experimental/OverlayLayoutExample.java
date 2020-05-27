package experimental;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.LineBorder;
import cum.edmund.models.maps.world.StretchIcon;

public class OverlayLayoutExample {
  private JPanel randomCell;

  public static void main(String[] args) {
    new OverlayLayoutExample().init();
  }

  private void init() {
    JPanel panel = createPanel();
    JFrame frame = createFrame();
    frame.add(panel);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private JPanel createPanel() {
    JPanel multiLayered = new JPanel();
    multiLayered.setLayout(new OverlayLayout(multiLayered));

    JButton button = new JButton("Show Message");
    button.setAlignmentX(0.5f);
    button.setAlignmentY(0.5f);

    JPanel popupPanel = createPopupPanel(button);
    popupPanel.setAlignmentX(0.1f);
    popupPanel.setAlignmentY(0.1f);

    button.addActionListener(e -> {
      button.setEnabled(false);
      popupPanel.setVisible(true);
    });


    
    PlayerLayer ship = new PlayerLayer();
    ship.setOpaque(false);
    
    multiLayered.add(ship);
    multiLayered.add(icon(multiLayered));
    multiLayered.add(button);
    multiLayered.add(popupPanel);
    multiLayered.add(background());
    ship.startAnimation();
    
    return multiLayered;
  }

  private JPanel background() {
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
        randomCell = new JPanel();
        if (x % 2 == 0) {
          randomCell.setBackground(y % 2 == 0 ? Color.BLACK : Color.RED);
        } else {
          randomCell.setBackground(y % 2 != 0 ? Color.BLACK : Color.RED);
        }

        panel.add(randomCell, gbc);
      }
    }

    return panel;
  }

  private static Component icon(JPanel multiLayeredparent) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.anchor = GridBagConstraints.NORTHWEST;

    JPanel panel = new JPanel();
//    panel.setLayout(null);
    panel.setOpaque(false);

    URL url = ClassLoader.getSystemResource("house.png");
    StretchIcon stretchIcon = new StretchIcon(url);
    
    
//    ImageIcon imageIcon = new ImageIcon("./img/imageName.png"); // load the image to a imageIcon
    Image image = stretchIcon.getImage(); // transform it 
    Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
    ImageIcon thing = new ImageIcon(newimg);  // transform it back
    
    JLabel label = new JLabel(thing);
    
    panel.add(label);
    panel.setAlignmentX(2f);
    panel.setAlignmentY(0.5f);
    label.setSize(100, 100);
    label.setText(" ");
    return panel;
  }

  private static JPanel createPopupPanel(JComponent overlapComponent) {
    JPanel popupPanel = new JPanel(new BorderLayout());
    popupPanel.setOpaque(false);
    popupPanel.setMaximumSize(new Dimension(150, 70));
    popupPanel.setBorder(new LineBorder(Color.gray));
    popupPanel.setVisible(false);

    JLabel label = new JLabel("HI there!");
    popupPanel.add(wrapInPanel(label), BorderLayout.CENTER);

    JButton popupCloseButton = new JButton("Close");
    popupPanel.add(wrapInPanel(popupCloseButton), BorderLayout.SOUTH);

    popupCloseButton.addActionListener(e -> {
      overlapComponent.setEnabled(true);
      popupPanel.setVisible(false);
    });

    return popupPanel;
  }

  private static JPanel wrapInPanel(JComponent component) {
    JPanel jPanel = new JPanel();
    jPanel.setBackground(new Color(50, 210, 250, 150));
    jPanel.add(component);
    return jPanel;
  }


  private static JFrame createFrame() {
    JFrame frame = new JFrame("OverlayLayout Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(400, 300));
    return frame;
  }
}
