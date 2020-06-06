package cum.edmund.ui.layers;

import cum.edmund.core.Engine;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.View;
import cum.edmund.ui.layers.core.AbstractLayer;
import cum.edmund.ui.layers.core.Granularity;

/**
 * This is the UI layer that contains anything that blocks the player's path, e.g. houses
 * 
 * @author Ed
 *
 */
public class BlockerLayer extends AbstractLayer {

  private final Engine engine;

  public BlockerLayer(View view, Engine engine) {
    super(view, Granularity.COARSE);

    this.engine = engine;
  }

  @Override
  protected WorldMap worldMap() {
    return engine.getBlockersWorldMap();
  }

}
