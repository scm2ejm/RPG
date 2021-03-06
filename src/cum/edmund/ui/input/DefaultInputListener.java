package cum.edmund.ui.input;

import java.awt.event.KeyEvent;
import org.libsdl.SDL;
import org.libsdl.SDL_Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.badlogic.gdx.controllers.Controller;
import cum.edmund.core.Engine;
import uk.co.electronstudio.sdl2gdx.SDL2ControllerManager;

/**
 * Captures input from user and hands off to another component
 * 
 * @author Ed
 *
 */
public class DefaultInputListener extends AbstractInputListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultInputListener.class);

  private final Engine engine;

  /**
   * This is the thing that will process the event. E.g. move the character or whatever
   */
  private Controllable controllable;

  /**
   * This is the thing that captures gamepad input
   */
  private SDL2ControllerManager manager;

  public DefaultInputListener(Engine engine) {

    this.engine = engine;

    // I have no idea what magic this does
    SDL.SDL_SetHint("SDL_XINPUT_ENABLED", "0");

    // Start listening for controller input
    manager = new SDL2ControllerManager();
    manager.addListenerAndRunForConnectedControllers(this);
  }

  public void captureInput() {
    try {

      // If there are no controllers then abort early
      if (manager.getControllers().isEmpty()) {
        return;
      }

      // Check for controller input
      manager.pollState();

      // Assume there is only one controller for now
      Controller controller = manager.getControllers().get(0);

      // Left stick horizontal and vertical
      handleInput(0, controller.getAxis(0));
      handleInput(1, controller.getAxis(1));

      // Right stick horizontal and vertical
      handleInput(2, controller.getAxis(2));
      handleInput(3, controller.getAxis(3));

      // Left and Right triggers
      handleInput(5, controller.getAxis(5));
      handleInput(4, controller.getAxis(4));

      
    } catch (SDL_Error e) {
      LOGGER.error("Error capturing gamepad state", e);
    }
  }

  /**
   * This handles gamepad input
   */
  private void handleInput(int axisCode, float value) {
    if (controllable == null) {
      // No hook yet so aborting
      return;
    }

    if (Math.abs(value) < 0.5f) {
      // Input is small enough to fall in dead zone so ignoring it
      return;
    }

    LOGGER.debug("Axis: {}, direction: {}", axisCode, value > 0);

    if (axisCode == 0) {
      // Left stick - Horizontal
      controllable.handleKeyPress(value > 0 ? KeyEvent.VK_RIGHT : KeyEvent.VK_LEFT);
    }

    if (axisCode == 1) {
      // Left stick - Vertical
      controllable.handleKeyPress(value > 0 ? KeyEvent.VK_DOWN : KeyEvent.VK_UP);
    }

    if (axisCode == 2) {
      // Right stick - Horizontal
      controllable.handleKeyPress(value > 0 ? KeyEvent.VK_NUMPAD6 : KeyEvent.VK_NUMPAD4);
    }

    if (axisCode == 3) {
      // Right stick - Vertical
      controllable.handleKeyPress(value > 0 ? KeyEvent.VK_NUMPAD2 : KeyEvent.VK_NUMPAD8);
    }
    
    if (axisCode == 4) {
      // Left trigger
      controllable.handleKeyPress(KeyEvent.VK_F);
    }
    
    if (axisCode == 5) {
      // Right trigger
      controllable.handleKeyPress(KeyEvent.VK_B);
    }
    

  }

  /**
   * This handles keyboard input
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (controllable == null) {
      // No hook yet so aborting
      return;
    }

    controllable.handleKeyPress(e.getKeyCode());
  }

  public Controllable getControllable() {
    return controllable;
  }

  public void setControllable(Controllable controllable) {
    // Remove listener from old component
    if (this.controllable != null) {
      this.controllable.listenComponent().removeKeyListener(engine.getGamePad());
    }

    // Set the new controllable
    this.controllable = controllable;

    // Add listener to new component
    if (this.controllable != null) {
      this.controllable.listenComponent().addKeyListener(engine.getGamePad());
    }
  }

  @Override
  public boolean buttonDown(Controller controller, int buttonCode) {
    if (controllable != null && buttonCode == 0) {
      controllable.handleKeyPress(KeyEvent.VK_ENTER);
      return true;
    }
    return false;
  }
}
