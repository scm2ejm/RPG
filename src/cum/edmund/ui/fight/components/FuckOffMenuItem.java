package cum.edmund.ui.fight.components;

import cum.edmund.audio.AudioEngine;
import cum.edmund.ui.UI;
import cum.edmund.ui.fight.FightView;

/**
 * Menu Item that triggers a player to flee from battle
 * 
 * @author Ed
 *
 */
public class FuckOffMenuItem extends AssFuckMenu {
  public FuckOffMenuItem(UI ui, FightView fightView) {
    super("Fuck Off", () -> {
      ui.createWorldMapView();
      AudioEngine.stopBackgroundMusic();
    });

    setDuration(0);
  }

}
