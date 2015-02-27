package cum.edmund.maps.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cum.edmund.map.Coord;
import cum.edmund.map.MapFeature;
import cum.edmund.map.MapFeatureType;

public class WorldMapTest {

  private static final Logger LOGGER;

  static {
    LOGGER = LoggerFactory.getLogger(WorldMapTest.class);
  }

  @Test
  public void startTest() {

    // Create world map
    WorldMap map = new WorldMap();

    // Create a test house
    MapFeature house = new MapFeature("fucker's house", MapFeatureType.HOUSE, new Coord(5, 0));

    // Place the house on the map
    map.putFeature(house);

    // Create a test house
    MapFeature crackDen =
        new MapFeature("fucker's crack den", MapFeatureType.HOUSE, new Coord(69, -69));

    // Place the crack den on the map
    map.putFeature(crackDen);

    // Scan map looking for house and crack den
    boolean foundHouse = false;
    boolean foundCrackDen = false;
    for (int x = -100; x <= 100; x++) {
      for (int y = -100; y <= 100; y++) {

        MapFeature feature = map.get(x, y);

        String foundFeature = verifyFeature(x, y, feature);

        if ("fucker's house".equals(foundFeature)) {
          foundHouse = true;
        } else if ("fucker's crack den".equals(foundFeature)) {
          foundCrackDen = true;
        }
      }
    }

    assertTrue(foundHouse);
    assertTrue(foundCrackDen);

  }

  private String verifyFeature(int x, int y, MapFeature feature) {
    if (x == 5 && y == 0) {

      // There should be fucker's house here

      assertNotNull(feature);
      assertEquals("fucker's house", feature.getName());
      assertEquals(MapFeatureType.HOUSE, feature.getType());
      LOGGER.debug("({},{}) - Found {}", x, y, feature.getName());
      return feature.getName();

    } else if (x == 69 && y == -69) {

      // There should be fucker's crack den here

      assertNotNull(feature);
      assertEquals("fucker's crack den", feature.getName());
      assertEquals(MapFeatureType.HOUSE, feature.getType());
      LOGGER.debug("({},{}) - Found {}", x, y, feature.getName());
      return feature.getName();

    } else {

      // Anything else should be null

      assertNull(feature);
      return null;

    }
  }
}
