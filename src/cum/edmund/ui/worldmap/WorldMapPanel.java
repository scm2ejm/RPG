package cum.edmund.ui.worldmap;

import cum.edmund.core.Engine;
import cum.edmund.ui.MultilayeredPanel;
import cum.edmund.ui.UI;
import cum.edmund.ui.View;
import cum.edmund.ui.input.Controllable;
import cum.edmund.ui.layers.BackgroundLayer;
import cum.edmund.ui.layers.BlockerLayer;
import cum.edmund.ui.layers.PlayerLayer;

/**
 * Component which allows multiple layers to be rendered with transparency
 * 
 * @author Ed
 *
 */
public class WorldMapPanel extends MultilayeredPanel implements Controllable {

  private final View view;
  private final PlayerLayer playerLayer;

  public WorldMapPanel(UI ui, Engine engine) {
    this.view = new View();

    playerLayer = new PlayerLayer(view, ui, engine);

    // Top layer
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
    // Hand off the action to player layer
    playerLayer.handleKeyPress(key);
  }

  @SuppressWarnings("exports")
  @Override
  public PlayerLayer listenComponent() {
    return playerLayer;
  }

}
