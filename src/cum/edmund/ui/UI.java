package cum.edmund.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cum.edmund.core.Engine;
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

    worldMapView.grabFocus();
  }

  private void setupWorldMapView() {

    worldMapView = new WorldMapView(this, engine.getWorldMap());
    worldMapView.setVisible(true);

    getContentPane().add(worldMapView);

    // Set table resizer. Must be done after adding to Content Pane
    worldMapView.getParent().addComponentListener(new AssFuckTableResizer(worldMapView));

  }

  private void setupFrame() {
    setSize(200, 200);
    setVisible(true);
    setFocusable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void main(String[] args) {
    new UI();
  }

  public void showFightView() {
    JPanel fightPanel = new FightView();
    getContentPane().add(fightPanel);
    worldMapView.setVisible(false);
    fightPanel.grabFocus();
  }

}
