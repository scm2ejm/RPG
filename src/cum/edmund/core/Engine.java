package cum.edmund.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.helpers.EnemiesHelper;
import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.UI;

public class Engine {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(UI.class);
  
  private WorldMap worldMap;
  private Hero fucker;
  
  public Engine() {
    fucker = new Hero("fucker", 0, 0);
    setWorldMap(new WorldMap(fucker));
    
    // TODO REMOVE THESE TEST OBJECTS
    getWorldMap().put(new House("Fuckers house", new Coord(4, 2)));
    getWorldMap().put(EnemiesHelper.createButtasaurusAss(4, new Coord(-2, -2)));
  }

  public void walk(Direction direction) {
    WalkOutcome outcome = WalkHelper.walk(fucker, direction, getWorldMap());

    if (outcome.isFight()) {
      LOGGER.debug("Starting fight!");
    }
  }

  public WorldMap getWorldMap() {
    return worldMap;
  }

  public void setWorldMap(WorldMap worldMap) {
    this.worldMap = worldMap;
  }
}