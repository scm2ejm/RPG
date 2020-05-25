package cum.edmund.core;

import java.util.ArrayList;
import java.util.List;
import cum.edmund.helpers.EnemiesHelper;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.PlayableCharacter;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WorldMap;

public class Engine {

  private WorldMap worldMap;
  private Hero fucker;
  private List<PlayableCharacter> entourage;

  public Engine() {
    fucker = new Hero("fucker", 0, 0);
    entourage = new ArrayList<>();
    setWorldMap(new WorldMap(fucker));

    // TODO REMOVE THESE TEST OBJECTS
    getWorldMap().put(new House("Fuckers house", new Coord(4, 2)));
    getWorldMap().put(EnemiesHelper.createButtasaurusAss(4, new Coord(-2, -2)));
  }

  public WorldMap getWorldMap() {
    return worldMap;
  }

  public void setWorldMap(WorldMap worldMap) {
    this.worldMap = worldMap;
  }

  public List<FightableCharacter> entourage() {
    List<FightableCharacter> fighters = new ArrayList<>();
    fighters.add(fucker);

    // TODO: Replace instanceof with a field
    entourage.stream().filter(o -> o instanceof FightableCharacter)
        .map(FightableCharacter.class::cast).forEach(fighters::add);

    return fighters;
  }
}
