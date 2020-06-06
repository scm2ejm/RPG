package cum.edmund.newui.layers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import cum.edmund.newui.View;
import cum.edmund.newui.input.Controllable;
import cum.edmund.newui.input.DefaultInputListener;

/**
 * This is the UI layer that contains any characters
 * 
 * @author Ed
 *
 */
public class PlayerLayer extends JPanel implements Controllable {

  private static final ImageIcon VANILLA = new ImageIcon(ClassLoader.getSystemResource("walk.gif"));

  private final View view;
  
  private final DefaultInputListener gamePad;

  private ImageIcon character;
  private int cellX = 0;
  private int cellY = 0;
  private int totalHeight = -1;
  private int totalWidth = -1;

  public PlayerLayer(View view) {
    this.view = view;
    this.gamePad = new DefaultInputListener(this::handleKeyPress);
    
    character = VANILLA;

    addKeyListener(gamePad);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }
  
  

  public DefaultInputListener getGamePad() {
    return gamePad;
  }



  /**
   * Paints the player at given x and y coords as well as the JPanel itself
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    

    // Work out where the player is considering view position
    int coarseTopLeftViewX = view.getViewX();
    int coarseTopLeftViewY = view.getViewY();
    int fineTopLeftViewX = coarseTopLeftViewX * View.MULTIPLIER;
    int fineTopLeftViewY = coarseTopLeftViewY * View.MULTIPLIER;

    int playerX = cellX - fineTopLeftViewX;
    int playerY = cellY - fineTopLeftViewY;

    // Convert screen percent into number of pixels. Display co-ordinates are based on fine grid
    // size
    float x = (playerX / (float) View.FINE_GRID_SIZE) * getWidth();
    float y = (playerY / (float) View.FINE_GRID_SIZE) * getHeight();

    

    if (totalHeight != getHeight() || totalWidth != getWidth()) {
      // Screen dimensions have changed so we have to recreated icons with correct dimensions
      totalHeight = getHeight();
      totalWidth = getWidth();
      // Scaling is based on course grid size
      int scaledWidth = totalWidth / View.COARSE_GRID_SIZE;
      int scaledHeight = totalHeight / View.COARSE_GRID_SIZE;
      character = new ImageIcon(
          VANILLA.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_DEFAULT));
    }

    character.paintIcon(this, g, (int) x, (int) y);
  }

  @Override
  public void handleKeyPress(int key) {
    float xDelta = 0;
    float yDelta = 0;

    if (key == KeyEvent.VK_LEFT) {
      xDelta = -1;
      yDelta = 0;
    }
    if (key == KeyEvent.VK_UP) {
      xDelta = 0;
      yDelta = -1;
    }
    if (key == KeyEvent.VK_RIGHT) {
      xDelta = 1;
      yDelta = 0;
    }
    if (key == KeyEvent.VK_DOWN) {
      xDelta = 0;
      yDelta = 1;
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

    cellX += xDelta;
    cellY += yDelta;

  }
}
