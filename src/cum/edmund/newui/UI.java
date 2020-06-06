package cum.edmund.newui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import cum.edmund.newui.layers.BackgroundLayer;
import cum.edmund.newui.layers.BlockerLayer;
import cum.edmund.newui.layers.PlayerLayer;

/**
 * Main executable for starting game running
 * 
 * @author Ed
 *
 */
public class UI extends JFrame {

  private View view;
  
  public static void main(String[] args) {
    new UI();
  }

  private UI() {
    view = new View();
    
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(multilayeredContainer());
    setVisible(true);
  }

  private JPanel multilayeredContainer() {
    MultilayeredPanel multiLayered = new MultilayeredPanel();

    // Top layer
    multiLayered.add(new PlayerLayer(view));
    multiLayered.add(new BlockerLayer(view));
    multiLayered.add(new BackgroundLayer());
    // Bottom layer

    return multiLayered;
  }

}
