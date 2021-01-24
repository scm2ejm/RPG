package cum.edmund.ui.fight;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import cum.edmund.core.Engine;
import cum.edmund.helpers.CombatHelper;
import cum.edmund.helpers.CombatHelper.CombatOutcome;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.maps.world.tiles.TileLoader;
import cum.edmund.models.maps.world.tiles.TileLoader.DrawType;
import cum.edmund.ui.UI;
import cum.edmund.ui.fight.components.ActionsPanel;

/**
 * This is the screen displayed when fighting, consisting of the player and enemy images and the
 * panel of actions
 * 
 * @author Ed
 *
 */
public class FightView extends JPanel {

  private final UI ui;
  private final Engine engine;
  private final List<FightableCharacter> players;
  private final Enemies enemies;

  private final List<FightableCharacter> allCharacters;
  private int currentCharacter;

  private ActionsPanel actionsPanel;
  private JPanel playerStatsPanel;
  private JLabel playerImageLabel;
  private JTextPane notificationsPane;
  private JTextPane enemyStatsText;
  private JLabel hp;
  private Timer timer;
  private JPanel holdUpPanel;

  public FightView(UI ui, Engine engine, Enemies enemies) {
    super(new GridBagLayout());
    this.ui = ui;
    this.engine = engine;
    this.players = engine.entourageFighters();
    this.enemies = enemies;
    this.timer = new Timer();

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

    setupHoldUpPanel();

    setupActionsPanel();

    setupNotificationsPanel();

    setupPlayerImagePanel();

    setupEnemyImagePanel();
  }

  private void setupNotificationsPanel() {
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1;
    c.weighty = 0.1;
    c.anchor = GridBagConstraints.SOUTH;

    notificationsPane = new JTextPane();
    notificationsPane.setForeground(Color.WHITE);
    notificationsPane.setBackground(Color.BLACK);
    Font font = new Font(Font.MONOSPACED, 1, 15);
    notificationsPane.setFont(font);
    notificationsPane.setText("Waiting for action...");

    add(notificationsPane, c);
  }

  private void setupEnemyStatsText() {
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 1;
    c.gridy = 0;
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.5;
    c.weighty = 1;
    c.anchor = GridBagConstraints.FIRST_LINE_END;

    enemyStatsText = new JTextPane();
    enemyStatsText.setForeground(Color.WHITE);
    enemyStatsText.setBackground(Color.BLACK);
    Font font = new Font(Font.MONOSPACED, 1, 25);
    enemyStatsText.setFont(font);
    FightableNPC leadEnemy = enemies.getFrontRow().get(0);
    updateEnemyStats(leadEnemy);



    FontMetrics fontMetrics = enemyStatsText.getFontMetrics(font);

    int width = fontMetrics.stringWidth(enemyStatsText.getText());

    add(enemyStatsText, c);
  }

  /**
   * Display something like "BUTTASAURUS ASS\t69HP\t69MP"
   * 
   * @param enemy Enemy to display stats of
   */
  private void updateEnemyStats(FightableNPC enemy) {
    StringJoiner stats = new StringJoiner("\t");
    stats.add(enemy.getName().toUpperCase());
    stats.add(String.valueOf(enemy.getCharacterAttributes().getHp()) + "HP");
    stats.add(String.valueOf(enemy.getCharacterAttributes().getMp()) + "MP");
    enemyStatsText.setText(stats.toString());
  }

  public void setupPlayerImagePanel() {
    setupPlayerImagePanel(TileLoader.getNewPlayerTile());
  }

  public void changePlayerIcon(String iconFileName) {
    setupPlayerImagePanel(TileLoader.loadTile(iconFileName, DrawType.STRETCH));
  }

  private void setupPlayerImagePanel(ImageIcon icon) {
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 1;
    c.weighty = 1;
    c.anchor = GridBagConstraints.NORTHWEST;

    if (playerImageLabel != null) {
      playerImageLabel.removeAll();
      playerImageLabel.setIcon(icon);
    } else {
      playerImageLabel = new JLabel(icon);
    }
    icon.setImageObserver(ui);
    playerImageLabel.setVisible(true);

    add(playerImageLabel, c);

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

      hp = new JLabel();
      hp.setForeground(Color.WHITE);
      hp.setAlignmentX(Component.CENTER_ALIGNMENT);
      hp.setAlignmentY(Component.TOP_ALIGNMENT);
      populatePlayerHp();
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

  private void populatePlayerHp() {
    FightableCharacter player = players.get(0);
    hp.setText(player.getCharacterAttributes().getHp() + "/"
        + player.getCharacterAttributes().getMaxHp() + " HP");
  }

  private void setupActionsPanel() {
    actionsPanel = new ActionsPanel(ui, engine, this);

    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 3;
    c.weightx = 0.9;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.LAST_LINE_END;

    add(actionsPanel, c);

    actionsPanel.setVisible(false);
  }

  private void setupHoldUpPanel() {
    Border border = BorderFactory.createLineBorder(Color.WHITE, 5, true);
    Border margin = new EmptyBorder(5, 5, 5, 5);
    Border withOuterMargin = new CompoundBorder(margin, border);
    Border withInnerMargin = new CompoundBorder(withOuterMargin, margin);

    holdUpPanel = new JPanel();
    holdUpPanel.setBackground(Color.BLUE);
    holdUpPanel.setBorder(withInnerMargin);
    holdUpPanel.setLayout(new GridBagLayout());

    JLabel label = new JLabel("One moment please!");
    label.setForeground(Color.RED);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setAlignmentY(Component.CENTER_ALIGNMENT);

    GridBagConstraints centre = new GridBagConstraints();
    centre.fill = GridBagConstraints.BOTH;
    centre.anchor = GridBagConstraints.CENTER;

    holdUpPanel.add(label, centre);


    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 3;
    c.weightx = 0.9;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.LAST_LINE_END;

    add(holdUpPanel, c);
  }

  public ActionsPanel getActionsPanel() {
    return actionsPanel;
  }

  public JPanel getPlayerStatsPanel() {
    return playerStatsPanel;
  }

  public void showText(String text) {
    notificationsPane.setText(text);
  }

  public void scheduleNextTurn() {
    currentCharacter++;
    actionsPanel.setVisible(false);
    holdUpPanel.setVisible(true);
    
    if (currentCharacter >= allCharacters.size()) {
      currentCharacter=0;
    }
    
    timer.schedule(new TimerTask() {

      @Override
      public void run() {
        takeTurn();
      }
    }, 3000L);
  }

  public void takeTurn() {
    FightableCharacter character = allCharacters.get(currentCharacter);
    if (character instanceof FightableNPC) {
      // Perform action
      CombatOutcome outcome = CombatHelper.physicalAttack(character, players.get(0));
      
      // Update notification pane
      notificationsPane.setText(outcome.toString());
      
      // Move to next turn automatically
      scheduleNextTurn();
    } else {
      actionsPanel.setVisible(true);
      holdUpPanel.setVisible(false);
    }

    // Update enemy HP
    FightableNPC leadEnemy = enemies.getFrontRow().get(0);
    updateEnemyStats(leadEnemy);

    // Update player HP
    populatePlayerHp();

    
  }

}
