package cum.edmund.core;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cum.edmund.helpers.EnemiesHelper;
import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.blocks.House;
import cum.edmund.models.blocks.Mountain;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.PlayableCharacter;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.BackgroundWorldMap;
import cum.edmund.models.maps.world.ForegroundWorldMap;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.ui.View;
import cum.edmund.ui.input.DefaultInputListener;

public class Engine {

  private static final Logger LOGGER = LoggerFactory.getLogger(Engine.class);

  private final Hero fucker;
  private final List<PlayableCharacter> entourage;

  private final ForegroundWorldMap foregroundWorldMap;
  private final WorldMap characterWorldMap;
  private final WorldMap blockersWorldMap;
  private final WorldMap topBackgroundWorldMap;
  private final WorldMap bottomBackgroundWorldMap;

  private final DefaultInputListener gamePad;

  public Engine(View view) {
    fucker = new Hero("fucker");
    entourage = new ArrayList<>();

    foregroundWorldMap = new ForegroundWorldMap(view);
    characterWorldMap = new WorldMap();
    characterWorldMap.put(new Coord(20, 15), fucker);
    blockersWorldMap = new WorldMap();
    topBackgroundWorldMap = new BackgroundWorldMap();
    bottomBackgroundWorldMap = new WorldMap();

    this.gamePad = new DefaultInputListener(this);

    // TODO REMOVE THESE TEST OBJECTS
    for (int i = -3; i <= 5; i++) {
      blockersWorldMap.put(new Coord(i, 5), new Mountain());
      blockersWorldMap.put(new Coord(i, -5), new Mountain());
      blockersWorldMap.put(new Coord(i, 6), new Mountain());
      blockersWorldMap.put(new Coord(i, -6), new Mountain());
    }

    for (int i = -5; i <= 5; i++) {
      blockersWorldMap.put(new Coord(5, i), new Mountain());
      if (i != 0) {
        blockersWorldMap.put(new Coord(-3, i), new Mountain());
      }
    }

    blockersWorldMap.put(new Coord(-5, 1), new Mountain());
    blockersWorldMap.put(new Coord(-5, -1), new Mountain());

    blockersWorldMap.put(new Coord(-4, 1), new Mountain());
    blockersWorldMap.put(new Coord(-4, -1), new Mountain());

    blockersWorldMap.put(new Coord(3, -4), new Mountain());
    blockersWorldMap.put(new Coord(4, -4), new Mountain());
    blockersWorldMap.put(new Coord(4, -3), new Mountain());
    blockersWorldMap.put(new Coord(4, 4), new Mountain());
    blockersWorldMap.put(new Coord(4, 2), new House("Fuckers house"));
    blockersWorldMap.put(new Coord(-2, 0), EnemiesHelper.createButtasaurusAss(1));
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

  public ForegroundWorldMap getForegroundWorldMap() {
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

  @SuppressWarnings("exports")
  public DefaultInputListener getGamePad() {
    return gamePad;
  }
}
