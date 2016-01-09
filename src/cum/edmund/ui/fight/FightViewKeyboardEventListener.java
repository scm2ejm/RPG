package cum.edmund.ui.fight;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Processes keyboard events, eg makes the player walk when a directional button is pressed
 * 
 * @author Ed
 *
 */
public class FightViewKeyboardEventListener implements KeyListener {

  private FightView fightView;

  public FightViewKeyboardEventListener(FightView fightView) {
    this.fightView = fightView;
  }

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      fightView.getActionsPanel().upPressed();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      fightView.getActionsPanel().downPressed();
    }
  }


  @Override
  public void keyReleased(KeyEvent e) {}
}
