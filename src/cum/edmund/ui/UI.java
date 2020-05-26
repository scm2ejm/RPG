package cum.edmund.ui;

import javax.swing.JFrame;
import cum.edmund.core.Engine;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.map.Coord;
import cum.edmund.ui.fight.FightView;
import cum.edmund.ui.worldmap.WorldMapView;

/**
 * Basic tile-based UI for Return To Assfuck Castle
 * 
 * @author Ed
 *
 */
@SuppressWarnings("serial")
public class UI extends JFrame {

  private Engine engine;
  private WorldMapView worldMapView;
  private FightView fightView;

  public UI() {
    super("Return to AssFuck Castle");
    setupFrame();

    engine = new Engine();

    setupWorldMapView();

    // Draw stuff
    validate();


  }

  private void setupWorldMapView() {

    worldMapView = new WorldMapView(this, engine.getWorldMap());

    getContentPane().add(worldMapView);

    // Set table resizer. Must be done after adding to Content Pane
    worldMapView.getParent().addComponentListener(new AssFuckTableResizer(worldMapView));

    worldMapView.setVisible(true);
    worldMapView.grabFocus();
    worldMapView.drawThings();
    worldMapView.getParent().setSize(200, 200);

  }

  private void setupFrame() {
    setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
    // setSize(200, 200);
    setVisible(true);
    setFocusable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }

  public static void main(String[] args) {
    new UI();
  }

  public void showFightView() {
    fightView = new FightView(this, engine.entourage(), new Enemies(new Coord(1, 1)));
    getContentPane().removeAll();
    getContentPane().add(fightView);

    fightView.showFightPanel();

    fightView.revalidate();
    fightView.grabFocus();
  }

  public void showWorldMapView() {
    getContentPane().removeAll();
    getContentPane().add(worldMapView);

    worldMapView.revalidate();
    worldMapView.grabFocus();
    worldMapView.resetKeyboardActions();
  }

}
