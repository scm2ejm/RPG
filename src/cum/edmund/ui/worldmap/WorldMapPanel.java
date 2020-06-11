package cum.edmund.ui.worldmap;

import java.awt.event.KeyEvent;
import cum.edmund.core.Engine;
import cum.edmund.ui.MultilayeredPanel;
import cum.edmund.ui.UI;
import cum.edmund.ui.View;
import cum.edmund.ui.input.Controllable;
import cum.edmund.ui.layers.BackgroundLayer;
import cum.edmund.ui.layers.BlockerLayer;
import cum.edmund.ui.layers.ForegroundLayer;
import cum.edmund.ui.layers.PlayerLayer;

/**
 * Component which allows multiple layers to be rendered with transparency
 * 
 * @author Ed
 *
 */
public class WorldMapPanel extends MultilayeredPanel implements Controllable {

  private final View view;
  private final Engine engine;
  private final PlayerLayer playerLayer;
  private final ForegroundLayer foregroundLayer;

  public WorldMapPanel(UI ui, Engine engine, View view) {
    super(engine);
    this.view = view;
    this.engine = engine;

    playerLayer = new PlayerLayer(view, ui, engine);
    foregroundLayer = new ForegroundLayer(view, engine);

    // Top layer
    add(foregroundLayer);
    add(playerLayer);
    add(new BlockerLayer(view, engine));
    add(new BackgroundLayer(view, engine));
    // Bottom layer
  }

  public View getView() {
    return view;
  }

  @Override
  public void handleKeyPress(int key) {
    if (key == KeyEvent.VK_T) {
      engine.getForegroundWorldMap().handleKeyPress(key);
    }
    // Hand off the action to player layer
    playerLayer.handleKeyPress(key);
  }

  @SuppressWarnings("exports")
  @Override
  public PlayerLayer listenComponent() {
    return playerLayer;
  }

}
