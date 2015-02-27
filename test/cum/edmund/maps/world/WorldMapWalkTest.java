package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cum.edmund.models.characters.Direction;
import cum.edmund.models.characters.enemies.ButtasaurusAss;
import cum.edmund.models.characters.enemies.FightableNPC;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.house.House;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WalkOutcome;
import cum.edmund.models.maps.world.WorldMap;

public class WorldMapWalkTest {
  @Test
  public void houseBlocksPathTest() {

    // Create world map
    WorldMap map = new WorldMap();

    House house = new House("fucker's house", new Coord(-2, 0));
    map.putHouse(house);

    Hero fucker = new Hero("fucker", 0, 0);

    // this should work
    WalkOutcome outcome = map.walk(fucker, Direction.WEST);
    assertTrue(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(-1, 0), outcome.getNewPosition());
    assertEquals(new Coord(-1, 0), fucker.getPosition());

    // this should fail (house in way)
    outcome = map.walk(fucker, Direction.WEST);
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
    WalkOutcome outcome = map.walk(fucker, Direction.EAST);
    assertTrue(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(1, 0), outcome.getNewPosition());
    assertEquals(new Coord(1, 0), fucker.getPosition());

    // This should work
    outcome = map.walk(fucker, Direction.EAST);
    assertTrue(outcome.isSuccess());
    assertFalse(outcome.isFight());
    assertEquals(new Coord(2, 0), outcome.getNewPosition());
    assertEquals(new Coord(2, 0), fucker.getPosition());

    // This should work, results in fight!
    outcome = map.walk(fucker, Direction.EAST);
    assertTrue(outcome.isSuccess());
    assertTrue(outcome.isFight());
    assertEquals(new Coord(3, 0), outcome.getNewPosition());
    assertEquals(new Coord(3, 0), fucker.getPosition());
    assertEquals(2, outcome.getElement().getEnemies().size());
  }
}
