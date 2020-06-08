package cum.edmund.maps.world;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import cum.edmund.models.WorldObject;
import cum.edmund.models.blocks.House;
import cum.edmund.models.characters.hero.Hero;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WorldMap;

public class WorldMapHouseTest {

  @Test
  public void startTest() {

    // Create world map
    WorldMap map = new WorldMap();
    map.put(0, 0, new Hero("Fucker"));

    // Create a test house
    House house = new House("fucker's house");

    // Place the house on the map
    map.put(new Coord(5, 0), house);

    // Create a test house
    House crackDen = new House("fucker's crack den");

    // Place the crack den on the map
    map.put(new Coord(69, -69), crackDen);

    // Scan map looking for house and crack den
    boolean foundHouse = false;
    boolean foundCrackDen = false;
    for (int x = -100; x <= 100; x++) {
      for (int y = -100; y <= 100; y++) {

        WorldObject element = map.get(x, y);

        String houseName = element == null ? "" : element.getName();

        if ("fucker's house".equals(houseName)) {
          foundHouse = true;
        } else if ("fucker's crack den".equals(houseName)) {
          foundCrackDen = true;
        }
      }
    }

    assertTrue(foundHouse);
    assertTrue(foundCrackDen);

  }


}
