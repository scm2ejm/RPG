package cum.edmund.ui.fight;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class FightPanel extends JPanel {

  public FightPanel() {
    super(new GridBagLayout());
    setBackground(Color.RED);

    showFightPanel();

    setVisible(true);
  }

  public void showFightPanel() {

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

    c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 0;
    c.gridy = 3;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.LAST_LINE_START;

    JPanel playerStatsPanel = new JPanel();
    playerStatsPanel.setBackground(Color.BLACK);
    playerStatsPanel.setVisible(true);
    add(playerStatsPanel, c);

    c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    c.gridx = 1;
    c.gridy = 3;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.LAST_LINE_END;
    add(new ActionsPanel(), c);
  }


}
