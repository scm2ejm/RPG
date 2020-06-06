package cum.edmund.ui.layers;

import java.awt.event.KeyEvent;
import cum.edmund.audio.AudioEngine;
import cum.edmund.core.Engine;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.UI;
import cum.edmund.ui.View;
import cum.edmund.ui.input.Controllable;
import cum.edmund.ui.input.DefaultInputListener;
import cum.edmund.ui.layers.core.AbstractLayer;
import cum.edmund.ui.layers.core.Granularity;

/**
 * This is the UI layer that contains any characters
 * 
 * @author Ed
 *
 */
public class PlayerLayer extends AbstractLayer implements Controllable {

  private final View view;

  private final UI ui;

  private final Engine engine;

  private final DefaultInputListener gamePad;

  public PlayerLayer(View view, UI ui, Engine engine) {
    super(view, Granularity.FINE);

    this.view = view;
    this.ui = ui;
    this.engine = engine;
    this.gamePad = new DefaultInputListener(this::handleKeyPress);

    addKeyListener(gamePad);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }



  public DefaultInputListener getGamePad() {
    return gamePad;
  }

  @Override
  public void handleKeyPress(int key) {

    WalkOutcome outcome = null;

    if (key == KeyEvent.VK_LEFT) {
      outcome = engine.walk(Direction.WEST);
    }
    if (key == KeyEvent.VK_UP) {
      outcome = engine.walk(Direction.NORTH);
    }
    if (key == KeyEvent.VK_RIGHT) {
      outcome = engine.walk(Direction.EAST);
    }
    if (key == KeyEvent.VK_DOWN) {
      outcome = engine.walk(Direction.SOUTH);
    }

    if (key == KeyEvent.VK_NUMPAD4) {
      view.setViewX(view.getViewX() - 1);
      view.setViewY(view.getViewY());
    }
    if (key == KeyEvent.VK_NUMPAD8) {
      view.setViewX(view.getViewX());
      view.setViewY(view.getViewY() - 1);
    }
    if (key == KeyEvent.VK_NUMPAD6) {
      view.setViewX(view.getViewX() + 1);
      view.setViewY(view.getViewY());
    }
    if (key == KeyEvent.VK_NUMPAD2) {
      view.setViewX(view.getViewX());
      view.setViewY(view.getViewY() + 1);
    }

    if (outcome != null && outcome.isFight()) {
      AudioEngine.startFightBackgroundMusic();

      ui.createFightView();
    }
  }

  @Override
  protected WorldMap worldMap() {
    return engine.getCharacterWorldMap();
  }
}
