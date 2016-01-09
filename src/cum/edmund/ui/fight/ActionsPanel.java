package cum.edmund.ui.fight;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.models.maps.world.tiles.TileLoader.TileType;

public class ActionsPanel extends JPanel {

  private int selectedItem;
  private List<JLabel> arrows;

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

    Arrays.asList("Attack", "Magic", "Item", "Fuck Off").forEach(this::addMenuItem);
    
    arrows.get(0).setVisible(true);
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
    selected.setVisible(false);
    add(selected, c);
    arrows.add(selected);
  }

  private void addMenuItem(String text) {
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

    addArrow(arrows.size());
  }

  public void upPressed() {
    if (selectedItem > 0) {
      selectedItem--;
    }
  }

  public void downPressed() {
    if (selectedItem < arrows.size()) {
      selectedItem++;
    }
  }
}
