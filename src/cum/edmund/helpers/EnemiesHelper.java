package cum.edmund.helpers;

import cum.edmund.models.characters.enemies.ButtasaurusAss;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.map.Coord;

/**
 * Helper class for creating new enemies
 * 
 * @author Ed
 *
 */
public class EnemiesHelper {
  public static Enemies createButtasaurusAss(int enemiesToCreate, Coord position) {
    Enemies enemies = new Enemies(position);

    for (int i = 0; i < enemiesToCreate; i++) {
      enemies.getEntourage().add(new ButtasaurusAss(position));
    }

    return enemies;
  }
}
