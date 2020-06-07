package cum.edmund.ui;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.Timer;
import cum.edmund.core.Engine;

/**
 * Component which allows multiple layers to be rendered with transparency
 * 
 * @author Ed
 *
 */
public class MultilayeredPanel extends JPanel {

  public MultilayeredPanel(Engine engine) {
    // This will make the screen update every 100 ms
    new Timer(RedrawTimerTask.ANIMATION_DELAY, new RedrawTimerTask(this, engine)).start();

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
