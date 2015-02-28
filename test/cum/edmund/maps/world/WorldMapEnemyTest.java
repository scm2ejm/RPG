package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import cum.edmund.models.characters.enemies.ButtasaurusAss;
import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.models.maps.world.WorldMapElement;

public class WorldMapEnemyTest {

  // private static final Logger LOGGER;
  //
  // static {
  // LOGGER = LoggerFactory.getLogger(WorldMapEnemyTest.class);
  // }

  @Test
  public void basicTest() {

    // Create world map
    WorldMap map = new WorldMap();

    FightableNPC enemyOne = new ButtasaurusAss(10, 10);
    map.putEnemy(enemyOne);

    FightableNPC enemyTwo = new ButtasaurusAss(5, 5);
    map.putEnemy(enemyTwo);

    FightableNPC enemyThree = new ButtasaurusAss(5, 5);
    map.putEnemy(enemyThree);

    WorldMapElement element = map.get(0, 0);
    assertNull(element);

    element = map.get(5, 5);
    assertEquals(2, element.getEnemies().size());
    assertNull(element.getBarrier());

    element = map.get(10, 10);
    assertEquals(1, element.getEnemies().size());
    assertNull(element.getBarrier());

  }

  /**
   * This test that we cannot have more than 6 enemies in a map element
   */
  @Test(expected = RuntimeException.class)
  public void tooManyEnemiesTest() {

    // Create world map
    WorldMap map = new WorldMap();

    // Count to make sure we do not go over six enemies
    int count = 0;

    while (true) {

      count++;

      FightableNPC enemy = new ButtasaurusAss(10, 10);
      map.putEnemy(enemy);

      if (count > 6) {
        fail();
      }

    }
  }
}
