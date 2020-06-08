package cum.edmund.ui.layers.core;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Flipped version of an image. See
 * https://stackoverflow.com/questions/1708011/create-a-imageicon-that-is-the-mirror-of-another-one
 * 
 * @author Ed
 *
 */
public class MirrorImageIcon extends ImageIcon {

  private Image image;

  public MirrorImageIcon(Image image) {
    super(image);
    this.image = image;
  }

  @Override
  public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
    // Graphics2D g2 = (Graphics2D) g;
    // g2.rotate(Math.toRadians(180), x + (image.getHeight(getImageObserver()) / 2),
    // y + image.getWidth(getImageObserver()) / 2);
    // g2.drawImage(image, x, y, getImageObserver());


    BufferedImage bufferedImage = new BufferedImage(image.getWidth(getImageObserver()),
        image.getHeight(getImageObserver()), BufferedImage.TYPE_INT_ARGB );
    Graphics2D g2d = (Graphics2D) g;

    Graphics gb = bufferedImage.getGraphics();
    gb.drawImage(image, 0, 0, getImageObserver());
    gb.dispose();

    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
    tx.translate(-image.getWidth(getImageObserver()), 0);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    bufferedImage = op.filter(bufferedImage, null);

    g2d.drawImage(bufferedImage, null, x, y);

  }

}
