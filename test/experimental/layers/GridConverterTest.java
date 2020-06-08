package experimental.layers;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.Test;
import cum.edmund.models.map.Coord;
import cum.edmund.ui.utils.GridConverter;

public class GridConverterTest {

  @Test
  public void test() {
    assertEquals(0, GridConverter.coarseToFine(0));
    assertEquals(0, GridConverter.fineToCoarse(0));
    assertEquals(-1, GridConverter.fineToCoarse(-5));
    assertEquals(-3, GridConverter.fineToCoarse(-15));

    // TODO: Check this one, it doesn't seem right
    assertEquals(-3, GridConverter.fineToCoarse(-16));

    assertEquals(new HashSet<>(Arrays.asList(new Coord(-3, 0), new Coord(-4, 0))),
        GridConverter.fineToCoarseExactThing(new Coord(-16, 0)));


  }

}

