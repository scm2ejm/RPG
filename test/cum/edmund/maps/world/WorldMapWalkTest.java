package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cum.edmund.helpers.WalkHelper;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.enemies.ButtasaurusAss;
import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;

public class WorldMapWalkTest {
  @Test
  public void houseBlocksPathTest() {

    // Create world map
    WorldMap map = new WorldMap();

    House house = new House("fucker's house", new Coord(-2, 0));
    map.putBarrier(house);

    Hero fucker = new Hero("fucker", 0, 0);

    // this should work
    WalkOutcome outcome = WalkHelper.walk(fucker, Direction.WEST, map);
    assertTrue(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(-1, 0), outcome.getNewPosition());
    assertEquals(new Coord(-1, 0), fucker.getPosition());

    // this should fail (house in way)
    outcome = WalkHelper.walk(fucker, Direction.WEST, map);
    assertFalse(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(-1, 0), outcome.getNewPosition());
    assertEquals(new Coord(-1, 0), fucker.getPosition());
  }

  @Test
  public void enemyCausesFight() {

    // Create world map
    WorldMap map = new WorldMap();

    FightableNPC enemyOne = new ButtasaurusAss(3, 0);
    map.putEnemy(enemyOne);

    FightableNPC enemyTwo = new ButtasaurusAss(3, 0);
    map.putEnemy(enemyTwo);

    Hero fucker = new Hero("fucker", 0, 0);

    // This should work
    WalkOutcome outcome = WalkHelper.walk(fucker, Direction.EAST, map);
    assertTrue(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(1, 0), outcome.getNewPosition());
    assertEquals(new Coord(1, 0), fucker.getPosition());

    // This should work
    outcome = WalkHelper.walk(fucker, Direction.EAST, map);
    assertTrue(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(2, 0), outcome.getNewPosition());
    assertEquals(new Coord(2, 0), fucker.getPosition());

    // This should work, results in fight!
    outcome = WalkHelper.walk(fucker, Direction.EAST, map);
    assertTrue(outcome.isSuccess());
    assertTrue(outcome.isFight());
    assertEquals(new Coord(3, 0), outcome.getNewPosition());
    assertEquals(new Coord(3, 0), fucker.getPosition());
    assertEquals(2, outcome.getElement().getEnemies().size());
  }
}
