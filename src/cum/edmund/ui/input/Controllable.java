package cum.edmund.ui.input;

import java.awt.Component;

/**
 * Implementing classes will do something when a keyboard or gamepad button is pressed
 * 
 * @author Ed
 *
 */
public interface Controllable {

  public void handleKeyPress(int key);

  /**
   * This is the thing that will register the key listener
   */
  public Component listenComponent();

}
