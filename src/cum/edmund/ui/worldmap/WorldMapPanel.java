package cum.edmund.ui.worldmap;

import cum.edmund.core.Engine;
import cum.edmund.ui.MultilayeredPanel;
import cum.edmund.ui.UI;
import cum.edmund.ui.View;
import cum.edmund.ui.layers.BackgroundLayer;
import cum.edmund.ui.layers.BlockerLayer;
import cum.edmund.ui.layers.PlayerLayer;

/**
 * Component which allows multiple layers to be rendered with transparency
 * 
 * @author Ed
 *
 */
public class WorldMapPanel extends MultilayeredPanel {

  private final View view;

  public WorldMapPanel(UI ui, Engine engine) {
    this.view = new View();

    // Top layer
    add(new PlayerLayer(view, ui, engine));
    add(new BlockerLayer(view, engine));
    add(new BackgroundLayer());
    // Bottom layer
  }

  public View getView() {
    return view;
  }

}
