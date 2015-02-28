package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.WorldObjectType;
import cum.edmund.models.blocks.House;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WorldMap;
import cum.edmund.models.maps.world.WorldMapElement;

public class WorldMapHouseTest {

  private static final Logger LOGGER;

  static {
    LOGGER = LoggerFactory.getLogger(WorldMapHouseTest.class);
  }

  @Test
  public void startTest() {

    // Create world map
    WorldMap map = new WorldMap();

    // Create a test house
    House house = new House("fucker's house", new Coord(5, 0));

    // Place the house on the map
    map.putBarrier(house);

    // Create a test house
    House crackDen = new House("fucker's crack den", new Coord(69, -69));

    // Place the crack den on the map
    map.putBarrier(crackDen);

    // Scan map looking for house and crack den
    boolean foundHouse = false;
    boolean foundCrackDen = false;
    for (int x = -100; x <= 100; x++) {
      for (int y = -100; y <= 100; y++) {

        WorldMapElement element = map.get(x, y);

        String houseName = verifyAndIdentifyHouse(x, y, element);

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

  private String verifyAndIdentifyHouse(int x, int y, WorldMapElement element) {
    if (x == 5 && y == 0) {

      // There should be fucker's house here

      House house = element.getHouse();

      assertNotNull(element);
      assertEquals("fucker's house", house.getName());
      assertEquals(WorldObjectType.HOUSE, house.getType());
      LOGGER.debug("({},{}) - Found {}", x, y, house.getName());
      return house.getName();

    } else if (x == 69 && y == -69) {

      // There should be fucker's crack den here

      House house = element.getHouse();

      assertNotNull(element);
      assertEquals("fucker's crack den", house.getName());
      assertEquals(WorldObjectType.HOUSE, house.getType());
      LOGGER.debug("({},{}) - Found {}", x, y, house.getName());
      return house.getName();

    } else {

      // Anything else should be null

      assertNull(element);
      return null;

    }
  }
}
