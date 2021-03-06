package cum.edmund.helpers;

import cum.edmund.models.characters.enemies.ButtasaurusAss;
import cum.edmund.models.characters.enemies.Enemies;

/**
 * Helper class for creating new enemies
 * 
 * @author Ed
 *
 */
public class EnemiesHelper {
  public static Enemies createButtasaurusAss(int enemiesToCreate) {
    Enemies enemies = new Enemies();

    for (int i = 0; i < enemiesToCreate; i++) {
      enemies.add(new ButtasaurusAss());
    }

    return enemies;
  }
}
