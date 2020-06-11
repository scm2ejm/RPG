package cum.edmund.ui.layers;

import java.awt.Component;
import java.awt.event.KeyEvent;
import cum.edmund.audio.AudioEngine;
import cum.edmund.core.Engine;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.UI;
import cum.edmund.ui.View;
import cum.edmund.ui.input.Controllable;
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

  public PlayerLayer(View view, UI ui, Engine engine) {
    super(view, Granularity.FINE);

    this.view = view;
    this.ui = ui;
    this.engine = engine;

    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }

  @Override
  public void handleKeyPress(int key) {

    WalkOutcome outcome = null;
    if (key == KeyEvent.VK_F) {
      AudioEngine.playFart();
    }
    
    if (key == KeyEvent.VK_B) {
      AudioEngine.playBurp();
    }

    if (key == KeyEvent.VK_LEFT) {
      outcome = engine.walk(Direction.WEST);
      setFlipImage(true);
    }
    if (key == KeyEvent.VK_UP) {
      outcome = engine.walk(Direction.NORTH);
    }
    if (key == KeyEvent.VK_RIGHT) {
      outcome = engine.walk(Direction.EAST);
      setFlipImage(false);
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

      ui.createFightView(outcome.getEnemies());
    }
  }

  @Override
  protected WorldMap worldMap() {
    return engine.getCharacterWorldMap();
  }

  @Override
  public Component listenComponent() {
    return this;
  }
}
