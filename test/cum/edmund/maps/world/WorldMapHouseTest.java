package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.models.WorldObject;
import cum.edmund.models.WorldObjectType;
import cum.edmund.models.house.House;
import cum.edmund.models.map.Coord;
import cum.edmund.models.maps.world.WorldMap;

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
    WorldObject house = new House("fucker's house", new Coord(5, 0));

    // Place the house on the map
    map.put(house);

    // Create a test house
    WorldObject crackDen = new House("fucker's crack den", new Coord(69, -69));

    // Place the crack den on the map
    map.put(crackDen);

    // Scan map looking for house and crack den
    boolean foundHouse = false;
    boolean foundCrackDen = false;
    for (int x = -100; x <= 100; x++) {
      for (int y = -100; y <= 100; y++) {

        WorldObject object = map.get(x, y);

        String identifiedObject = verifyAndIdentifyFeature(x, y, object);

        if ("fucker's house".equals(identifiedObject)) {
          foundHouse = true;
        } else if ("fucker's crack den".equals(identifiedObject)) {
          foundCrackDen = true;
        }
      }
    }

    assertTrue(foundHouse);
    assertTrue(foundCrackDen);

  }

  private String verifyAndIdentifyFeature(int x, int y, WorldObject object) {
    if (x == 5 && y == 0) {

      // There should be fucker's house here

      assertNotNull(object);
      assertEquals("fucker's house", object.getName());
      assertEquals(WorldObjectType.HOUSE, object.getType());
      LOGGER.debug("({},{}) - Found {}", x, y, object.getName());
      return object.getName();

    } else if (x == 69 && y == -69) {

      // There should be fucker's crack den here

      assertNotNull(object);
      assertEquals("fucker's crack den", object.getName());
      assertEquals(WorldObjectType.HOUSE, object.getType());
      LOGGER.debug("({},{}) - Found {}", x, y, object.getName());
      return object.getName();

    } else {

      // Anything else should be null

      assertNull(object);
      return null;

    }
  }
}
