package cum.edmund.newui;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;

/**
 * Component which allows multiple layers to be rendered with transparency
 * 
 * @author Ed
 *
 */
public class MultilayeredPanel extends JPanel {

  public MultilayeredPanel() {
    // This will make the screen update every 100 ms
    new Timer(RedrawTimerTask.ANIMATION_DELAY, new RedrawTimerTask(this)).start();

    setLayout(new OverlayLayout(this));
  }

  @Override
  public Component add(Component c) {
    if (c instanceof JComponent) {
      ((JComponent) c).setOpaque(false);
    }

    return super.add(c);
  }
}
