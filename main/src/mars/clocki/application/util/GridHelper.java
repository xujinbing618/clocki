package mars.clocki.application.util;


public class GridHelper {

  /**
   * Returns row number for given cell id.
   *
   * Note: Following id format is expected:
   * rXcY
   * where r is fixed string
   * where X is dynamic number from 0 to 7
   * where c is fixed string
   * where Y is dynamic number from 0 to 3
   */
  public static int row(final String cellId) {
    try {
      return new Integer(cellId.substring(1, 2));
    } catch (Exception e) {
      return -10;
    }
  }

  /**
   * Returns column number for given cell id.
   *
   * See {@link #rowNumber(String)}
   */
  public static int column(final String cellId) {
    try {
      return new Integer(cellId.substring(cellId.length() -1));
    } catch (Exception e) {
      return -10;
    }
  }

  /**
   * Returns another three cells(in one square) for given cell id.
   *
   * For example if given id is:
   * r0c0
   * Then result would be:
   * r0c1,r1c0,r1c1
   */
  public static String findOtherThreeCell(final String id) {

    Integer rowPlusOne = row(id) + 1;                   // -X--
    Integer columnPlusOne = column(id) + 1;             // ---Y

    if (columnPlusOne < 0 || rowPlusOne < 0 ||
        columnPlusOne > 3 || rowPlusOne > 7) {
      return null;
    }
    return  "r" + row(id)    + "c" + columnPlusOne + "," +
            "r" + rowPlusOne + "c" + column(id) + "," +
            "r" + rowPlusOne + "c" + columnPlusOne;
  }


}