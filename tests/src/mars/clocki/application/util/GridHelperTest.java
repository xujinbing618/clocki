package mars.clocki.application.util;

import junit.framework.TestCase;
import mars.clocki.application.util.GridHelper;

public class GridHelperTest extends TestCase {

  public void testFindRowNumberFromGivenId() {
    assertEquals(0, GridHelper.row("r0c1"));
    assertEquals(1, GridHelper.row("r1c1"));
  }

  public void testRemoveOtherThreeCell() {
    assertEquals("r0c1,r1c0,r1c1", GridHelper.findOtherThreeCell("r0c0"));
    assertEquals("r0c2,r1c1,r1c2", GridHelper.findOtherThreeCell("r0c1"));
    assertEquals("r0c3,r1c2,r1c3", GridHelper.findOtherThreeCell("r0c2"));
    assertNull(GridHelper.findOtherThreeCell("r0c3"));
    assertEquals("r1c1,r2c0,r2c1", GridHelper.findOtherThreeCell("r1c0"));
    assertEquals("r1c2,r2c1,r2c2", GridHelper.findOtherThreeCell("r1c1"));
    assertEquals("r1c3,r2c2,r2c3", GridHelper.findOtherThreeCell("r1c2"));
    assertNull(GridHelper.findOtherThreeCell("r1c3"));
    assertEquals("r2c1,r3c0,r3c1", GridHelper.findOtherThreeCell("r2c0"));
    assertEquals("r2c2,r3c1,r3c2", GridHelper.findOtherThreeCell("r2c1"));
    assertEquals("r2c3,r3c2,r3c3", GridHelper.findOtherThreeCell("r2c2"));
    assertNull(GridHelper.findOtherThreeCell("r2c3"));
    assertEquals("r3c1,r4c0,r4c1", GridHelper.findOtherThreeCell("r3c0"));
    assertEquals("r3c2,r4c1,r4c2", GridHelper.findOtherThreeCell("r3c1"));
    assertEquals("r3c3,r4c2,r4c3", GridHelper.findOtherThreeCell("r3c2"));
    assertNull(GridHelper.findOtherThreeCell("r3c3"));
    assertEquals("r4c1,r5c0,r5c1", GridHelper.findOtherThreeCell("r4c0"));
    assertEquals("r4c2,r5c1,r5c2", GridHelper.findOtherThreeCell("r4c1"));
    assertEquals("r4c3,r5c2,r5c3", GridHelper.findOtherThreeCell("r4c2"));
    assertNull(GridHelper.findOtherThreeCell("r4c3"));
  }

}