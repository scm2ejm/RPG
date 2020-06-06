package cum.edmund.ui.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

/**
 * Captures input from user and hands off to another component
 * 
 * @author Ed
 *
 */
public abstract class AbstractInputListener implements ControllerListener, KeyListener {

  @Override
  public void connected(Controller controller) {
    // Na
  }

  @Override
  public void disconnected(Controller controller) {
    // Na
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    // Na
    return false;
  }

  @Override
  public boolean buttonUp(Controller controller, int buttonCode) {
    // Na
    return false;
  }

  @Override
  public boolean axisMoved(Controller controller, int axisCode, float value) {
    // Na
    return false;
  }

  @Override
  public boolean povMoved(Controller controller, int povCode, PovDirection value) {
    // Na
    return false;
  }

  @Override
  public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
    // Na
    return false;
  }

  @Override
  public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
    // Na
    return false;
  }

  @Override
  public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
    // Na
    return false;
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    // Na
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // Na
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // Na
  }

}
