package cum.edmund.newui.layers;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import cum.edmund.newui.View;

/**
 * This is the UI layer that contains anything that blocks the player's path, e.g. houses
 * 
 * @author Ed
 *
 */
public class BlockerLayer extends JPanel {

  private static final ImageIcon VANILLA =
      new ImageIcon(ClassLoader.getSystemResource("house.png"));

  private final View view;

  private ImageIcon house;
  private int tileX = 3;
  private int tileY = 5;
  private int totalHeight = -1;
  private int totalWidth = -1;

  public BlockerLayer(View view) {
    this.view = view;

    // This is used for redrawing a scaled version from a good version to avoid distortion
    house = VANILLA;

  }

  /**
   * Paints the player at given x and y coords as well as the JPanel itself
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Convert screen percent into number of pixels
    float x = ((tileX - view.getViewX()) / (float) View.COARSE_GRID_SIZE) * getWidth();
    float y = ((tileY - view.getViewY()) / (float) View.COARSE_GRID_SIZE) * getHeight();

    if (totalHeight != getHeight() || totalWidth != getWidth()) {
      // Screen dimensions have changed
      totalHeight = getHeight();
      totalWidth = getWidth();
      house = new ImageIcon(VANILLA.getImage().getScaledInstance(totalWidth / View.COARSE_GRID_SIZE,
          totalHeight / View.COARSE_GRID_SIZE, Image.SCALE_DEFAULT));
    }

    house.paintIcon(this, g, (int) x, (int) y);
  }

}
