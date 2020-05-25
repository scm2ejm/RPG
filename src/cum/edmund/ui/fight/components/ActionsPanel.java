package cum.edmund.ui.fight.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

  private final UI ui;
  private int selectedItem;
  private List<JLabel> arrows;
  private List<AssFuckMenu> menuItems;


  public ActionsPanel(UI ui) {
    super(new GridBagLayout());

    this.ui = ui;
    
    Border border = BorderFactory.createLineBorder(Color.WHITE, 5, true);
    Border margin = new EmptyBorder(5, 5, 5, 5);
    Border withOuterMargin = new CompoundBorder(margin, border);
    Border withInnerMargin = new CompoundBorder(withOuterMargin, margin);

    setBackground(Color.BLUE);
    setBorder(withInnerMargin);
    setVisible(true);

    arrows = new ArrayList<>();
    menuItems = new ArrayList<>();

    renderMenu(topMenu());



  }

  private Map<String, Runnable> topMenu() {
    Map<String, Runnable> menu = new LinkedHashMap<>();
    menu.put("Attack", () -> renderMenu(attackMenu()));
    menu.put("Magic", () -> renderMenu(magicMenu()));
    menu.put("Item", () -> renderMenu(itemMenu()));
    menu.put("Fuck Off", () -> ui.showWorldMapView());
    return menu;
  }

  private Map<String, Runnable> attackMenu() {
    Map<String, Runnable> menu = new LinkedHashMap<>();
    menu.put("Butt Attack", () -> LOGGER.error("Butt attack"));
    menu.put("Wiener Attack", () -> LOGGER.error("Wiener Attack"));
    menu.put("back", () -> renderMenu(topMenu()));
    return menu;
  }
  
  private Map<String, Runnable> magicMenu() {
    Map<String, Runnable> menu = new LinkedHashMap<>();
    menu.put("Jizz", () -> LOGGER.error("Jizz attack"));
    menu.put("Explosivo Diarrhea", () -> LOGGER.error("Explosivo Diarrhea Attack"));
    menu.put("back", () -> renderMenu(topMenu()));
    return menu;
  }
  
  private Map<String, Runnable> itemMenu() {
    Map<String, Runnable> menu = new LinkedHashMap<>();
    menu.put("Mints", () -> LOGGER.error("Mints"));
    menu.put("TP", () -> LOGGER.error("TP"));
    menu.put("back", () -> renderMenu(topMenu()));
    return menu;
  }

  private void renderMenu(Map<String, Runnable> menu) {
    // Clear all previous components
    this.removeAll();
    selectedItem = 0;
    arrows.clear();
    menuItems.clear();

    // Add new components
    menu.forEach((k, v) -> addMenuItem(k, v));
    arrows.get(0).setVisible(true);

    // Redraw
    this.validate();
  }

  private void addArrow(int y) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = y;
    c.anchor = GridBagConstraints.LINE_END;
    c.weightx = 0.1;
    c.weighty = 1;

    JLabel selected = new JLabel();
    ImageIcon hand = TileLoader.getTile(TileType.HAND, selected);
    selected.setIcon(hand);
    selected.setVisible(false);
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
    drawArrows();
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
