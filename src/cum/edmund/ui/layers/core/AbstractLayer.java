package cum.edmund.ui.layers.core;

import java.awt.Graphics;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import cum.edmund.models.WorldObject;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.View;
import cum.edmund.ui.utils.GridConverter;

/**
 * This is the UI layer that contains anything that blocks the player's path, e.g. houses
 * 
 * @author Ed
 *
 */
public abstract class AbstractLayer extends JPanel {

  private final View view;
  private final Granularity granularity;
  private final Map<String, ImageIcon> imageCache;
  private boolean flipImage;

  /**
   * Height of the panel. We use this to check whether the panel has been resized so we can redraw
   * things to the new scale
   */
  private int totalHeight = -1;

  /**
   * Width of the panel. We use this to check whether the panel has been resized so we can redraw
   * things to the new scale
   */

  private int totalWidth = -1;

  public AbstractLayer(View view, Granularity granularity) {
    this.view = view;
    this.granularity = granularity;
    this.imageCache = new HashMap<>();
    this.flipImage = false;
//    this.setOpaque(false);
  }

  /**
   * Paints the player at given x and y coords as well as the JPanel itself
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (totalHeight != getHeight() || totalWidth != getWidth()) {
      // Screen dimensions have changed
      totalHeight = getHeight();
      totalWidth = getWidth();
      imageCache.clear();
    }

    int gridSize = granularity == Granularity.COARSE ? View.COARSE_GRID_SIZE : View.FINE_GRID_SIZE;
    int scaledGridX = granularity == Granularity.COARSE ? view.getViewX()
        : GridConverter.coarseToFine(view.getViewX());
    int scaledGridY = granularity == Granularity.COARSE ? view.getViewY()
        : GridConverter.coarseToFine(view.getViewY());

    for (int renderX = 0; renderX < gridSize; renderX++) {
      for (int renderY = 0; renderY < gridSize; renderY++) {

        int worldX = renderX + scaledGridX;
        int worldY = renderY + scaledGridY;

        WorldObject worldObject = worldMap().get(worldX, worldY);

        if (worldObject == null) {
          // There is nothing at this coordinate
          continue;
        }

        // Convert screen percent into number of pixels
        float x = (renderX * getWidth()) / (float) gridSize;
        float y = (renderY * getHeight()) / (float) gridSize;

        // If the container has been resized then we need to scale image. If not then use cached
        // version
        ImageIcon icon = imageCache.computeIfAbsent(worldObject.getType() + "-" + flipImage,
            (type) -> scaleImage(worldObject.getUnscaledImage()));

        // Paint it
        icon.paintIcon(this, g, (int) x, (int) y);
      }
    }

  }

  /**
   * Creates a new image scaled according to the dimensions of parent container
   */
  private ImageIcon scaleImage(ImageIcon unscaledImage) {
    BigDecimal gridSize = BigDecimal.valueOf(View.COARSE_GRID_SIZE);
    BigDecimal newWidth = BigDecimal.valueOf(totalWidth).divide(gridSize, RoundingMode.UP);
    BigDecimal newHeight = BigDecimal.valueOf(totalHeight).divide(gridSize, RoundingMode.UP);
    if (flipImage) {
      return new MirrorImageIcon(unscaledImage.getImage().getScaledInstance(newWidth.intValue(),
          newHeight.intValue(), Image.SCALE_DEFAULT));
    } else {
      return new ImageIcon(unscaledImage.getImage().getScaledInstance(newWidth.intValue(),
          newHeight.intValue(), Image.SCALE_DEFAULT));
    }

  }

  public boolean isFlipImage() {
    return flipImage;
  }

  public void setFlipImage(boolean flipImage) {
    this.flipImage = flipImage;
  }

  /**
   * Returns the world map containing objects on this layer
   * 
   */
  protected abstract WorldMap worldMap();
}
