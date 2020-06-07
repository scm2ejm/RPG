package cum.edmund.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import cum.edmund.ui.layers.PlayerLayer;

/**
 * This thing triggers a redraw of the UI periodically (e.g. every 100 ms). Without this it would
 * only draw about once a second... lagggg
 * 
 * @author Ed
 *
 */
public class RedrawTimerTask implements ActionListener {

  public static final int ANIMATION_DELAY = 100;

  private Component component;

  public RedrawTimerTask(Component component) {
    this.component = component;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    component.repaint();
  }
}