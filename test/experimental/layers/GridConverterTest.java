package experimental.layers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import cum.edmund.newui.utils.GridConverter;

public class GridConverterTest {

  @Test
  public void test() {
    assertEquals(0, GridConverter.coarseToFine(0));
    assertEquals(0, GridConverter.fineToCoarse(0));
    assertEquals(-1, GridConverter.fineToCoarse(-5));
    assertEquals(-5, GridConverter.fineToCoarse(-16));
  }

}
