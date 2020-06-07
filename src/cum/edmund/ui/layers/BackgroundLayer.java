package cum.edmund.ui.layers;

import cum.edmund.core.Engine;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.View;
import cum.edmund.ui.layers.core.AbstractLayer;
import cum.edmund.ui.layers.core.Granularity;

/**
 * Returns the main background layer (e.g. grass, path, etc)
 * 
 * @author Ed
 *
 */
public class BackgroundLayer extends AbstractLayer {

  private final Engine engine;

  public BackgroundLayer(View view, Engine engine) {
    super(view, Granularity.COARSE);
    this.engine = engine;
  }

  @Override
  protected WorldMap worldMap() {
    return engine.getTopBackgroundWorldMap();
  }
}
