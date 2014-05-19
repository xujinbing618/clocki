package mars.clocki.domain.model;

import mars.clocki.domain.model.Square.SquareType;
import junit.framework.TestCase;

public class GridContainerTestCase extends TestCase {
  protected GridContainer grid;

  void assertMoveIsValid(int row, int column, boolean isValid, int toRow, int toColumn, String msg) {
    if (isValid) {
      assertTrue(msg, grid.isValidMove(
                   grid.cell(row, column).square(),
                   grid.cell(toRow, toColumn)));
    }
    else {
      assertFalse(msg, grid.isValidMove(
                    grid.cell(row, column).square(),
                    grid.cell(toRow, toColumn)));
    }
  }

  void moveSquare(int row, int column, int toRow, int toColumn) {
    grid.move(
        grid.cell(row, column).square(),
        grid.cell(toRow, toColumn));
  }

  void assertCellIsEmpty(int row, int column) {
    assertTrue(String.format("Position[%s, %s] supposed to be empty", row, column),
        grid.isEmpty(Position.point(row, column)));
  }

  void assertSquareTypeIs(SquareType type, int row, int column) {
    assertEquals(type, grid.cell(row, column).square().type());
  }

}