package cum.edmund.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import cum.edmund.core.Engine;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.ui.fight.FightView;
import cum.edmund.ui.worldmap.WorldMapPanel;

/**
 * Main executable for starting game running
 * 
 * @author Ed
 *
 */
public class UI extends JFrame {

  private Engine engine;
  private JPanel worldMap;

  public static void main(String[] args) {
    new UI();
  }

  private UI() {
    super("Return to AssFuck Castle");

    engine = new Engine();
    worldMap = new WorldMapPanel(this, engine);
    showWorldMapView();

    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setVisible(true);
  }

  public void createFightView() {
    FightView fightView = new FightView(this, engine.entourageFighters(), new Enemies());
    getContentPane().removeAll();
    getContentPane().add(fightView);

    fightView.showFightPanel();

    directInputEventsTo(worldMap);
  }

  public void showWorldMapView() {
    getContentPane().removeAll();
    getContentPane().add(worldMap);

    directInputEventsTo(worldMap);
  }

  private void directInputEventsTo(JPanel worldMap2) {
    // TODO Auto-generated method stub

  }

}
