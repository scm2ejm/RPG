package cum.edmund.ui.fight;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.ui.UI;
import cum.edmund.ui.fight.components.ActionsPanel;

public class FightView extends JPanel {

  private final UI ui;
  private final List<FightableCharacter> players;
  private final Enemies enemies;

  private final List<FightableCharacter> allCharacters;
  private int currentCharacter;

  private ActionsPanel actionsPanel;
  private JPanel playerStatsPanel;

  public FightView(UI ui, List<FightableCharacter> players, Enemies enemies) {
    super(new GridBagLayout());
    this.ui = ui;
    this.players = players;
    this.enemies = enemies;

    allCharacters = new ArrayList<>();
    allCharacters.addAll(players);
    allCharacters.addAll(enemies.getFrontRow());
    allCharacters.addAll(enemies.getBackRow());
    Collections.shuffle(allCharacters);

    currentCharacter = 0;

    setBackground(Color.GREEN);
    setVisible(true);
  }

  public void showFightPanel() {

    setupEnemyStatsText();

    setupPlayerStatsPanel();

    setupActionsPanel();

    setupPlayerImagePanel();

    setupEnemyImagePanel();

    addKeyListener(new FightViewKeyboardEventListener(this));
  }

  private void setupEnemyStatsText() {
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 1;
    c.gridy = 0;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.5;
    c.weighty = 1;
    c.anchor = GridBagConstraints.FIRST_LINE_END;

    JTextPane enemyStatsText = new JTextPane();
    enemyStatsText.setForeground(Color.WHITE);
    enemyStatsText.setBackground(Color.BLACK);
    Font font = new Font(Font.MONOSPACED, 1, 25);
    enemyStatsText.setFont(font);
    enemyStatsText.setText("BUTTASAURUS ASS\t69HP\t69MP");

    FontMetrics fontMetrics = enemyStatsText.getFontMetrics(font);

    int width = fontMetrics.stringWidth(enemyStatsText.getText());

    add(enemyStatsText, c);
  }

  private void setupPlayerImagePanel() {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.anchor = GridBagConstraints.NORTHWEST;

    ImageIcon icon = TileLoader.getNewPlayerTile();
    JLabel label = new JLabel(icon);
    icon.setImageObserver(ui);
    label.setVisible(true);

    add(label, c);

  }

  private void setupEnemyImagePanel() {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.anchor = GridBagConstraints.NORTHEAST;

    ImageIcon icon = TileLoader.getNewEnemyTile();
    JLabel label = new JLabel(icon);
    icon.setImageObserver(ui);
    label.setVisible(true);

    add(label, c);

  }

  private void setupPlayerStatsPanel() {
    GridBagConstraints overallC = new GridBagConstraints();
    overallC.fill = GridBagConstraints.BOTH;
    overallC.gridx = 0;
    overallC.gridy = 3;
    overallC.weightx = 0.1;
    overallC.weighty = 0.5;
    overallC.anchor = GridBagConstraints.LAST_LINE_START;

    Border border = BorderFactory.createLineBorder(Color.WHITE, 5, true);
    Border margin = new EmptyBorder(5, 5, 5, 5);
    Border withOuterMargin = new CompoundBorder(margin, border);
    Border withInnerMargin = new CompoundBorder(withOuterMargin, margin);

    playerStatsPanel = new JPanel();
    playerStatsPanel.setBackground(Color.BLUE);
    playerStatsPanel.setBorder(withInnerMargin);
    playerStatsPanel.setVisible(true);
    playerStatsPanel.setLayout(new GridBagLayout());
    add(playerStatsPanel, overallC);

    // Players
    for (int i = 0; i < players.size(); i++) {
      FightableCharacter player = players.get(i);

      // Name
      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 0;
      c.gridy = i + 1;
      c.anchor = GridBagConstraints.CENTER;
      c.weightx = 1;
      c.weighty = 1;

      JLabel name = new JLabel(player.getName());
      name.setForeground(Color.WHITE);
      name.setAlignmentX(Component.CENTER_ALIGNMENT);
      name.setAlignmentY(Component.TOP_ALIGNMENT);
      playerStatsPanel.add(name, c);

      // HP
      c = new GridBagConstraints();
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 1;
      c.gridy = i + 1;
      c.anchor = GridBagConstraints.CENTER;
      c.weightx = 1;
      c.weighty = 1;

      JLabel hp = new JLabel(player.getCharacterAttributes().getHp() + "/"
          + player.getCharacterAttributes().getMaxHp() + " HP");
      hp.setForeground(Color.WHITE);
      hp.setAlignmentX(Component.CENTER_ALIGNMENT);
      hp.setAlignmentY(Component.TOP_ALIGNMENT);
      playerStatsPanel.add(hp, c);

      // MP
      c = new GridBagConstraints();
      c.fill = GridBagConstraints.BOTH;
      c.gridx = 2;
      c.gridy = i + 1;
      c.anchor = GridBagConstraints.CENTER;
      c.weightx = 1;
      c.weighty = 1;

      JLabel mp = new JLabel(player.getCharacterAttributes().getMp() + "/"
          + player.getCharacterAttributes().getMaxMp() + " MP");
      mp.setForeground(Color.WHITE);
      mp.setAlignmentX(Component.CENTER_ALIGNMENT);
      mp.setAlignmentY(Component.TOP_ALIGNMENT);
      playerStatsPanel.add(mp, c);
    }
  }

  private void setupActionsPanel() {
    actionsPanel = new ActionsPanel(ui);

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 3;
    c.weightx = 0.9;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.LAST_LINE_END;

    add(actionsPanel, c);
  }

  public ActionsPanel getActionsPanel() {
    return actionsPanel;
  }

  public JPanel getPlayerStatsPanel() {
    return playerStatsPanel;
  }

}
