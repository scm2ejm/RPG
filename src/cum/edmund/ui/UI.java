package cum.edmund.ui;

import java.awt.Dimension;

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
public class UI extends JFrame {

  private static final UI INSTANCE = new UI();
  
  private Engine engine;
  private WorldMapView worldMapView;
  private JPanel fightPanel;


  private UI() {
    super("Return to AssFuck Castle");
    setupFrame();
    
    engine = new Engine();
    fightPanel = new FightView();

    setupWorldMapView();

    // Draw stuff
    validate();

    getContentPane().add(fightPanel);
    worldMapView.grabFocus();
  }

  private void setupWorldMapView() {

    worldMapView = new WorldMapView(this, engine.getWorldMap());
    worldMapView.setVisible(true);

    getContentPane().add(worldMapView);

    // Set table resizer. Must be done after adding to Content Pane
    worldMapView.getParent().addComponentListener(new AssFuckTableResizer(worldMapView));
    
    // Set size again to trigger redraw
    worldMapView.getParent().setSize(new Dimension(800,600));
  }

  private void setupFrame() {
    setSize(800, 600);
    setVisible(true);
    setFocusable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static UI getInstance() {
    return INSTANCE;
  }

  public static void main(String[] args) {
    UI.getInstance();
  }

  public void showFightView() {
    worldMapView.setVisible(false);
    fightPanel.grabFocus();
  }

  public void showWorldMap() {
    worldMapView.setVisible(true);
    worldMapView.grabFocus();
  }
}
