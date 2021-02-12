package cum.edmund.ui.fight;

import java.awt.Component;
import java.awt.event.KeyEvent;
import cum.edmund.ui.input.Controllable;

/**
 * Processes keyboard events, eg makes the player walk when a directional button is pressed
 * 
 * @author Ed
 *
 */
public class FightViewKeyboardEventListener implements Controllable {

  private static boolean enabled = true;
  
  private FightView fightView;

  public FightViewKeyboardEventListener(FightView fightView) {
    this.fightView = fightView;
  }

  @Override
  public void handleKeyPress(int key) {
    if (!enabled) {
      return;
    }
    
    if (key == KeyEvent.VK_UP) {
      fightView.getActionsPanel().upPressed();
    } else if (key == KeyEvent.VK_DOWN) {
      fightView.getActionsPanel().downPressed();
    } else if (key == KeyEvent.VK_ENTER) {
      fightView.getActionsPanel().enterPressed();
    }
  }

  @Override
  public Component listenComponent() {
    return fightView;
  }

  public void enable() {
    enabled = true;
  }
  
  public void disable() {
    enabled = false;
  }
}
