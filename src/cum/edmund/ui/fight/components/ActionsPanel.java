package cum.edmund.ui.fight.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.models.maps.world.tiles.TileLoader.TileType;
import cum.edmund.ui.UI;

/**
 * Represents the menu in a fight. Eg Fight, Item, etc
 *
 * @author Ed
 *
 */
public class ActionsPanel extends JPanel {

  private static final Logger LOGGER = LoggerFactory.getLogger(ActionsPanel.class);

  private int selectedItem;
  private List<JLabel> arrows;
  private List<AssFuckMenu> menuItems;

  public ActionsPanel() {
    super(new GridBagLayout());

    Border border = BorderFactory.createLineBorder(Color.WHITE, 5, true);
    Border margin = new EmptyBorder(5, 5, 5, 5);
    Border withOuterMargin = new CompoundBorder(margin, border);
    Border withInnerMargin = new CompoundBorder(withOuterMargin, margin);

    setBackground(Color.BLUE);
    setBorder(withInnerMargin);
    setVisible(true);

    selectedItem = 0;
    arrows = new ArrayList<>();
    menuItems = new ArrayList<>();

    topLevel();
  }

  private void topLevel() {
    clearMenu();
    addMenuItem("Attack", this::attack);
    addMenuItem("Magic", () -> LOGGER.error("Magic"));
    addMenuItem("Item", () -> LOGGER.error("Item"));
    addMenuItem("Fuck Off", () -> UI.getInstance().showWorldMap());
  }

  private void clearMenu() {
    // Remove all components
    removeAll();

    // Reset our vars
    selectedItem = 0;
    arrows.clear();
    menuItems.clear();
  }

  private void attack() {
    clearMenu();
    addMenuItem("Attack 1", () -> LOGGER.error("Attack"));
    addMenuItem("Cancel", this::topLevel);
  }

  private void addArrow(int y) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = y;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.1;
    c.weighty = 1;

    ImageIcon hand = TileLoader.getTile(TileType.HAND);
    JLabel selected = new JLabel(hand);
    selected.setVisible(y == 0 ? true : false);
    add(selected, c);
    arrows.add(selected);
  }

  private void addMenuItem(String text, Runnable task) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = arrows.size();
    c.weightx = 0.9;
    c.weighty = 1;
    c.anchor = GridBagConstraints.LINE_START;

    JLabel label = new JLabel(text);
    label.setForeground(Color.WHITE);
    add(label, c);

    menuItems.add(new AssFuckMenu(text, task));

    addArrow(arrows.size());
    
    validate();
  }

  public void upPressed() {
    if (selectedItem > 0) {
      selectedItem--;
    }
    drawArrows();
  }

  public void downPressed() {
    if (selectedItem < arrows.size() - 1) {
      selectedItem++;
    }
    drawArrows();
  }

  public void enterPressed() {
    menuItems.get(selectedItem).select();
  }

  private void drawArrows() {
    for (int i = 0; i < arrows.size(); i++) {
      JLabel arrow = arrows.get(i);
      if (i == selectedItem) {
        arrow.setVisible(true);
      } else {
        arrow.setVisible(false);
      }
    }
  }
}
