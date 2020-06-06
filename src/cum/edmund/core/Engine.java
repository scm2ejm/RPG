package cum.edmund.core;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.PlayableCharacter;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;

public class Engine {

  private static final Logger LOGGER = LoggerFactory.getLogger(Engine.class);

  private final Hero fucker;
  private final List<PlayableCharacter> entourage;

  private final WorldMap foregroundWorldMap;
  private final WorldMap characterWorldMap;
  private final WorldMap blockersWorldMap;
  private final WorldMap topBackgroundWorldMap;
  private final WorldMap bottomBackgroundWorldMap;

  public Engine() {
    fucker = new Hero("fucker");
    entourage = new ArrayList<>();

    foregroundWorldMap = new WorldMap();
    characterWorldMap = new WorldMap();
    characterWorldMap.put(new Coord(0, 0), fucker);

    blockersWorldMap = new WorldMap();
    blockersWorldMap.put(new Coord(0, 3), new House());

    topBackgroundWorldMap = new WorldMap();
    bottomBackgroundWorldMap = new WorldMap();

    // TODO REMOVE THESE TEST OBJECTS
    // for (int i = 0; i < 10; i++) {
    // blockersWorldMap.put(new Mountian(new Coord(i, 3)));
    // }


    // blockersWorldMap.put(new Mountian(new Coord(5, 3)));
    // blockersWorldMap.put(new Mountian(new Coord(5, 3)));
    // blockersWorldMap.put(new Mountian(new Coord(5, 2)));
    // blockersWorldMap.put(new Mountian(new Coord(5, 1)));
    // blockersWorldMap.put(new Mountian(new Coord(5, 0)));
    // blockersWorldMap.put(new Mountian(new Coord(5, -1)));
    // blockersWorldMap.put(new Mountian(new Coord(5, -3)));
    //
    // blockersWorldMap.put(new House("Fuckers house", new Coord(4, 2)));
    // characterWorldMap.put(EnemiesHelper.createButtasaurusAss(4, new Coord(-2, -2)));
  }

  /**
   * Subset of entourage who can fight, along with the main character
   */
  public List<FightableCharacter> entourageFighters() {
    List<FightableCharacter> fighters = new ArrayList<>();
    fighters.add(fucker);

    // TODO: Replace instanceof with a field
    entourage.stream().filter(o -> o instanceof FightableCharacter)
        .map(FightableCharacter.class::cast).forEach(fighters::add);

    return fighters;
  }

  public WalkOutcome walk(Direction direction) {
    WalkOutcome outcome = WalkHelper.walk(fucker, direction, characterWorldMap, blockersWorldMap);

    if (outcome.isFight()) {
      LOGGER.debug("Starting fight!");
    }

    return outcome;
  }

  public Hero getFucker() {
    return fucker;
  }

  public List<PlayableCharacter> getEntourage() {
    return entourage;
  }

  public WorldMap getForegroundWorldMap() {
    return foregroundWorldMap;
  }

  public WorldMap getCharacterWorldMap() {
    return characterWorldMap;
  }

  public WorldMap getBlockersWorldMap() {
    return blockersWorldMap;
  }

  public WorldMap getTopBackgroundWorldMap() {
    return topBackgroundWorldMap;
  }

  public WorldMap getBottomBackgroundWorldMap() {
    return bottomBackgroundWorldMap;
  }
}
