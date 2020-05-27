package experimental;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This is the UI layer that contains any characters
 * 
 * @author Ed
 *
 */
public class PlayerLayer extends JPanel implements ActionListener, KeyListener {

  protected ImageIcon ship1[];
  private final int TOTAL_IMAGES = 16;
  private int currentIMAGE = 0;
  private final int ANIMATION_DELAY = 100;
  private int width;
  private int height;
  private int thingX = 0;
  private int thingY = 0;
  private int velX = 0;
  private int velY = 0;

  private Timer animationTimer;

  public static void main(String... args) {
    JFrame frame = new JFrame("things");
    PlayerLayer ship = new PlayerLayer();
    frame.add(ship);
    frame.setVisible(true);
    frame.addKeyListener(ship);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ship.startAnimation();

  }

  public PlayerLayer() {

    ship1 = new ImageIcon[TOTAL_IMAGES];

    for (int count = 0; count < ship1.length; count++) {
      ship1[count] = new ImageIcon(ClassLoader.getSystemResource("walk.gif"));

      width = ship1[count].getIconWidth();
      height = ship1[count].getIconHeight();
    }

    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    ship1[currentIMAGE].paintIcon(this, g, thingX, thingY);

    if (animationTimer != null && animationTimer.isRunning()) {
      currentIMAGE = (currentIMAGE + 1) % TOTAL_IMAGES;
    }
  }

  public void startAnimation() {
    if (animationTimer == null) {
      currentIMAGE = 0;
      animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());

      animationTimer.start();
    } else {
      if (!animationTimer.isRunning()) {
        animationTimer.restart();
      }
    }
  }

  public void stopAnimation() {
    animationTimer.stop();
  }

  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  private class TimerHandler implements ActionListener {

    public void actionPerformed(ActionEvent actionEvent) {
      repaint();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (thingX < 0) {
      velX = 0;
      thingX = 0;
    }
    if (thingX > 850) {
      velX = 0;
      thingX = 850;
    }
    if (thingY < 0) {
      velY = 0;
      thingY = 0;
    }
    if (thingY > 650) {
      velY = 0;
      thingY = 650;
    }

    this.thingX = this.thingX + velX;
    this.thingY = this.thingY + velY;

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int c = e.getKeyCode();

    if (c == KeyEvent.VK_LEFT) {
      velX = -10;
      velY = 0;
    }
    if (c == KeyEvent.VK_UP) {
      velX = 0;
      velY = -10;
    }
    if (c == KeyEvent.VK_RIGHT) {
      velX = 10;
      velY = 0;
    }
    if (c == KeyEvent.VK_DOWN) {
      velX = 0;
      velY = 10;
    }
    setX(thingX + velX);
    this.thingY += velY;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {
    velX = 0;
    velY = 0;
  }

  public int getX() {
    return thingX;
  }

  public void setX(int thingX) {
    this.thingX = thingX;
  }

  public int getY() {
    return thingY;
  }

  public void setY(int thingY) {
    this.thingY = thingY;
  }

}
