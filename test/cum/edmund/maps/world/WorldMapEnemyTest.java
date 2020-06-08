package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import cum.edmund.helpers.EnemiesHelper;
import cum.edmund.models.WorldObject;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WorldMap;

public class WorldMapEnemyTest {

  @Test
  public void basicTest() {

    // Create world map
    WorldMap map = new WorldMap();
    new Hero("Fucker");

    Enemies enemiesOne = EnemiesHelper.createButtasaurusAss(1);
    map.put(new Coord(10, 10), enemiesOne);

    Enemies enemiesTwo = EnemiesHelper.createButtasaurusAss(2);
    map.put(new Coord(5, 5), enemiesTwo);

    WorldObject element = map.get(0, 0);
    assertNull(element);

    element = map.get(5, 5);
    Enemies foundEnemies = (Enemies) element;
    assertEquals(2, foundEnemies.allEnemies().size());

    element = map.get(10, 10);
    foundEnemies = (Enemies) element;
    assertNotNull(foundEnemies);
    assertEquals(1, foundEnemies.allEnemies().size());
  }
}
