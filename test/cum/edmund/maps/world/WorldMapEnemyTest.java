package cum.edmund.maps.world;

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
    new Hero("Fucker", 0, 0);

    Enemies enemiesOne = EnemiesHelper.createButtasaurusAss(1, new Coord(10, 10));
    map.put(enemiesOne);

    Enemies enemiesTwo = EnemiesHelper.createButtasaurusAss(2, new Coord(5, 5));
    map.put(enemiesTwo);

    WorldObject element = map.get(0, 0);
    assertNull(element);

    // TODO - fix me!!
    //
    // element = map.get(5, 5);
    // Enemies foundEnemies = (WorldObject) element;
    // House foundHouse = element.getHouse();
    // assertNotNull(foundEnemies);
    // assertEquals(2, foundEnemies.allEnemies().size());
    // assertNull(foundHouse);
    //
    // element = map.get(10, 10);
    // foundEnemies = element.getEnemies();
    // foundHouse = element.getHouse();
    // assertNotNull(foundEnemies);
    // assertEquals(1, foundEnemies.allEnemies().size());
    // assertNull(foundHouse);

  }
}
