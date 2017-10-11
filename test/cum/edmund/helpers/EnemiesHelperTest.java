package cum.edmund.helpers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import cum.edmund.models.characters.FightableCharacter;
import cum.edmund.models.characters.enemies.Enemies;
import cum.edmund.models.map.Coord;

public class EnemiesHelperTest {

  @Test(expected = RuntimeException.class)
  public void testCreateTooMany() {
    // This should throw an exception
    EnemiesHelper.createButtasaurusAss(10, new Coord(0, 0));
  }

  @Test
  public void testSixTotalSixAlive() {
    Enemies enemies = EnemiesHelper.createButtasaurusAss(6, new Coord(0, 0));
    assertEquals(6, enemies.getEntourage().aliveCount());
    assertEquals(6, enemies.getEntourage().totalCount());
  }

  @Test
  public void testThreeTotalZeroAlive() {
    Enemies enemies = EnemiesHelper.createButtasaurusAss(3, new Coord(0, 0));

    int dead = 0;
    for (FightableCharacter enemy : enemies.getEntourage().allCharacters()) {
      if (dead++ == 3) {
        break;
      }
      enemy.kill();
    }

    assertEquals(0, enemies.getEntourage().aliveCount());
    assertEquals(3, enemies.getEntourage().totalCount());
  }
}
