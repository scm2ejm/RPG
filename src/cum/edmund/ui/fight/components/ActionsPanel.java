package cum.edmund.ui.fight.components;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.core.Engine;
import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.models.maps.world.tiles.TileLoader.TileType;
import cum.edmund.ui.UI;
import cum.edmund.ui.fight.FightView;

/**
 * Represents the menu in a fight. Eg Fight, Item, etc
 *
 * @author Ed
 *
 */
public class ActionsPanel extends JPanel {

  private static final Logger LOGGER = LoggerFactory.getLogger(ActionsPanel.class);

  private final UI ui;
  private final FightView fightView;
  private int selectedItem;
  private List<JLabel> arrows;
  private List<AssFuckMenu> menuItems;
  private Timer timer;


  public ActionsPanel(UI ui, Engine engine, FightView fightView) {
    super(new GridBagLayout());

    this.ui = ui;
    this.fightView = fightView;
    this.timer = new Timer();

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

  private List<AssFuckMenu> topMenu() {
    List<AssFuckMenu> menu = new ArrayList<>();
    menu.add(new UnEndingAssFuckMenu("Attack", () -> renderMenu(attackMenu()), fightView));
    menu.add(new UnEndingAssFuckMenu("Magic", () -> renderMenu(magicMenu()), fightView));
    menu.add(new UnEndingAssFuckMenu("Item", () -> renderMenu(itemMenu()), fightView));
    menu.add(new FuckOffMenuItem(ui, fightView));
    return menu;
  }

  private List<AssFuckMenu> attackMenu() {
    List<AssFuckMenu> menu = new ArrayList<>();
    menu.add(new NuggetMenuItem(fightView));
    menu.add(new AssFuckMenu("Wiener Attack", () -> LOGGER.error("Wiener Attack"), fightView));
    menu.add(new AssFuckMenu("Heart Attack", () -> LOGGER.error("Heart Attack"), fightView));
    menu.add(new UnEndingAssFuckMenu("back", () -> renderMenu(topMenu()), fightView));
    return menu;
  }

  private List<AssFuckMenu> magicMenu() {
    List<AssFuckMenu> menu = new ArrayList<>();
    menu.add(new AssFuckMenu("Plasma Jizz", () -> LOGGER.error("Plasma Jizz"), fightView));
    menu.add(new AssFuckMenu("Yellow Snow", () -> LOGGER.error("Yellow Snow"), fightView));
    menu.add(new CockRocketMenuItem(fightView));
    menu.add(new ExplosivoDiarrheaMenuItem(fightView));
    menu.add(new UnEndingAssFuckMenu("back", () -> renderMenu(topMenu()), fightView));
    return menu;
  }

  private List<AssFuckMenu> itemMenu() {
    List<AssFuckMenu> menu = new ArrayList<>();
    menu.add(new AssFuckMenu("Mints", () -> LOGGER.error("Mints"), fightView));
    menu.add(new AssFuckMenu("TP", () -> LOGGER.error("TP"), fightView));
    menu.add(new UnEndingAssFuckMenu("back", () -> renderMenu(topMenu()), fightView));
    return menu;
  }

  private void renderMenu(List<AssFuckMenu> menu) {
    // Clear all previous components
    this.removeAll();
    selectedItem = 0;
    arrows.clear();
    menuItems.clear();

    // Add new components
    menu.forEach(this::addMenuItem);
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

  private void addMenuItem(AssFuckMenu menuItem) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = arrows.size();
    c.weightx = 0.9;
    c.weighty = 1;
    c.anchor = GridBagConstraints.LINE_START;

    JLabel label = new JLabel(menuItem.getName());
    label.setForeground(Color.WHITE);
    add(label, c);

    menuItems.add(menuItem);

    addArrow(arrows.size());
  }

  public void upPressed() {
    if (selectedItem > 0) {
      selectedItem--;
    }
    drawArrows();

    AssFuckMenu menu = menuItems.get(selectedItem);
    fightView.showText(menu.couldPerform());
  }

  public void downPressed() {
    if (selectedItem < arrows.size() - 1) {
      selectedItem++;
    }
    drawArrows();

    AssFuckMenu menu = menuItems.get(selectedItem);
    fightView.showText(menu.couldPerform());

  }

  public void enterPressed() {

    // Cancel any future timer actions
    timer.cancel();

    // Get the menu item
    AssFuckMenu thisMenu = menuItems.get(selectedItem);

    fightView.showText(thisMenu.isPerforming());

    if (thisMenu.getIconFilename() != null) {
      fightView.changePlayerIcon(thisMenu.getIconFilename());
    }

    // Do it's thang
    thisMenu.select();

    // Schedule the reset when it's complete
    if (thisMenu.getDuration() > 0) {
      timer = new Timer();
      timer.schedule(new TimerTask() {
        @Override
        public void run() {
          // The action is complete so restore original player image
          fightView.setupPlayerImagePanel();

          fightView.showText(thisMenu.hasPerformed());
        }
      }, thisMenu.getDuration());

      // Redraw stuff
      drawArrows();
    }

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
