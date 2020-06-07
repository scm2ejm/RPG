package cum.edmund.ui;

import javax.swing.JFrame;
import cum.edmund.core.Engine;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.ui.fight.FightView;
import cum.edmund.ui.fight.FightViewKeyboardEventListener;
import cum.edmund.ui.input.Controllable;
import cum.edmund.ui.worldmap.WorldMapPanel;

/**
 * Main executable for starting game running
 * 
 * @author Ed
 *
 */
public class UI extends JFrame {

  private final Engine engine;
  private WorldMapPanel worldMap;

  public static void main(String[] args) {
    new UI();
  }

  private UI() {
    super("Return to AssFuck Castle");

    engine = new Engine();
    worldMap = new WorldMapPanel(this, engine);
    createWorldMapView();

    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void createFightView(Enemies enemies) {
    FightView fightView = new FightView(this, engine, enemies);

    getContentPane().removeAll();
    getContentPane().add(fightView);

    fightView.showFightPanel();
    fightView.grabFocus();
    setVisible(true);

    directInputEventsTo(new FightViewKeyboardEventListener(fightView));
  }

  public void createWorldMapView() {
    getContentPane().removeAll();
    getContentPane().add(worldMap);
    worldMap.listenComponent().grabFocus();

    directInputEventsTo(worldMap);
  }

  private void directInputEventsTo(Controllable controllable) {
    engine.getGamePad().setControllable(controllable);
  }

}
