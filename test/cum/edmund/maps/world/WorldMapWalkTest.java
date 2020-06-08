package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import cum.edmund.helpers.EnemiesHelper;
import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;

public class WorldMapWalkTest {
  @Test
  public void houseBlocksPathTest() {

    // Create player world map
    WorldMap playersMap = new WorldMap();
    Hero fucker = new Hero("fucker");
    playersMap.put(0, 0, fucker);

    // Create blocker world map
    WorldMap blockersMap = new WorldMap();
    House house = new House("fucker's house");
    blockersMap.put(new Coord(-2, 0), house);

    // this should work
    WalkOutcome outcome = WalkHelper.walk(fucker, Direction.WEST, playersMap, blockersMap);
    assertTrue(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(-1, 0), outcome.getNewPosition());
    assertEquals(new Coord(-1, 0), playersMap.get(fucker));

    // The house is (-2, 0) fine which is (-10, 0) coarse
    while (playersMap.get(fucker).getX() > -5) {
      outcome = WalkHelper.walk(fucker, Direction.WEST, playersMap, blockersMap);
    }

    // this should fail (house in way)
    outcome = WalkHelper.walk(fucker, Direction.WEST, playersMap, blockersMap);
    assertFalse(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(-5, 0), outcome.getNewPosition());
    assertEquals(new Coord(-5, 0), playersMap.get(fucker));
  }

  @Test
  public void enemyCausesFight() {

    // Create players world map
    WorldMap playersMap = new WorldMap();
    Hero fucker = new Hero("fucker");
    playersMap.put(0, 0, fucker);

    WorldMap blockersMap = new WorldMap();
    Enemies enemies = EnemiesHelper.createButtasaurusAss(2);
    blockersMap.put(new Coord(4, 0), enemies);

    // This should work
    for (int i = 0; i < 15; i++) {
      WalkOutcome outcome = WalkHelper.walk(fucker, Direction.EAST, playersMap, blockersMap);
      assertTrue(outcome.isSuccess());
      assertFalse(outcome.isFight());
      assertEquals(new Coord(1 + i, 0), outcome.getNewPosition());
      assertEquals(new Coord(1 + i, 0), playersMap.get(fucker));
      assertNull(outcome.getEnemies());
    }

    // This should work, results in fight!
    WalkOutcome outcome = WalkHelper.walk(fucker, Direction.EAST, playersMap, blockersMap);
    assertFalse(outcome.isSuccess());
    assertTrue(outcome.isFight());
    assertEquals(new Coord(15, 0), outcome.getNewPosition());
    assertEquals(new Coord(15, 0), playersMap.get(fucker));
    assertEquals(2, outcome.getEnemies().totalCount());
  }
}
