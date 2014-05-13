package mars.clocki.domain.model;

import mars.clocki.domain.model.Square.SquareType;
import junit.framework.TestCase;

public class GridContainerTest extends TestCase {
  GridContainer grid;

  @Override
  protected void setUp() throws Exception {
    grid = GridContainer.initLevel1();
  }

  /**
   * This test goes through different possible moves in level 1.
   * Generally we want to make sure squares are only allowed to move
   * in Horizontal or Vertical directions when there are empty cells
   * next to them. In addition no square is allowed to jump.
   */
  public void testIsValidMoveForLevel1() {
    // Asserts after grid level 1 is initialized.
    assertMoveIsValid(0, 0, false, 0, 1, "");
    assertMoveIsValid(0, 1, false, 0, 2, "");
    assertMoveIsValid(0, 2, false, 0, 3, "");
    assertMoveIsValid(4, 0, true,  4, 1, "");
    assertMoveIsValid(4, 0, true,  4, 2, "");
    assertMoveIsValid(4, 0, false, 4, 3, "");

    moveSquare(4, 0,  4, 1);

    // Asserts after square in [4, 0] moves to cell [4, 1]
    assertMoveIsValid(4, 0, false, 4, 0, "");
    assertMoveIsValid(4, 0, false, 4, 1, "");
    assertMoveIsValid(4, 0, false, 4, 2, "");
    assertMoveIsValid(4, 0, false, 4, 3, "");
    assertMoveIsValid(4, 1, false, 4, 1, "");
    assertMoveIsValid(4, 1, false, 4, 3, "");
    assertMoveIsValid(4, 1, true,  4, 0, "");
    assertMoveIsValid(4, 1, true,  4, 2, "");

    moveSquare(3, 0,  4, 0);

    // Asserts after square in [3, 0] moves to cell [4, 0]
    assertMoveIsValid(4, 1, false, 3, 0, "");
    assertMoveIsValid(4, 2, false, 3, 0, "");
    assertMoveIsValid(4, 3, false, 3, 0, "");
    assertMoveIsValid(3, 0, false, 3, 0, "");
    assertMoveIsValid(3, 2, false, 3, 0, "");
    assertMoveIsValid(3, 3, false, 3, 0, "");
    assertMoveIsValid(2, 1, false, 3, 0, "");
    assertMoveIsValid(2, 2, false, 3, 0, "");
    assertMoveIsValid(2, 3, false, 3, 0, "");
    assertMoveIsValid(1, 0, false, 3, 0, "");
    assertMoveIsValid(1, 1, false, 3, 0, "");
    assertMoveIsValid(1, 2, false, 3, 0, "");
    assertMoveIsValid(1, 3, false, 3, 0, "");
    assertMoveIsValid(2, 0, true,  3, 0, "");
    assertMoveIsValid(4, 0, true,  3, 0, "");
    assertMoveIsValid(3, 1, true,  3, 0, "");

    moveSquare(3, 1,  3, 0);

    // Asserts after square in [3, 1] moves to cell [3, 0]
    assertMoveIsValid(2, 0, false, 3, 1, "");
    assertMoveIsValid(2, 2, false, 3, 1, "");
    assertMoveIsValid(4, 0, false, 3, 1, "");
    assertMoveIsValid(4, 2, false, 3, 1, "");
    assertMoveIsValid(2, 1, true,  3, 1, "");
    assertMoveIsValid(3, 0, true,  3, 1, "");
    assertMoveIsValid(3, 2, true,  3, 1, "");
    assertMoveIsValid(4, 1, true,  3, 1, "");

    moveSquare(3, 2,  4, 2);
    moveSquare(2, 2,  3, 2);
    moveSquare(2, 1,  3, 1);

    // Asserts after an square type 2x2 is able to move
    assertMoveIsValid(0, 1, false, 0, 0, "");
    assertMoveIsValid(0, 1, false, 0, 1, "");
    assertMoveIsValid(0, 1, false, 0, 2, "");
    assertMoveIsValid(0, 1, false, 0, 3, "");
    assertMoveIsValid(0, 1, false, 1, 0, "");
    assertMoveIsValid(0, 1, false, 1, 1, "");
    assertMoveIsValid(0, 1, false, 1, 2, "");
    assertMoveIsValid(0, 1, false, 1, 3, "");
    assertMoveIsValid(0, 1, false, 2, 0, "");
    assertMoveIsValid(0, 1, false, 2, 3, "");
    assertMoveIsValid(0, 1, false, 3, 0, "");
    assertMoveIsValid(0, 1, false, 3, 1, "");
    assertMoveIsValid(0, 1, false, 3, 2, "");
    assertMoveIsValid(0, 1, false, 3, 3, "");
    assertMoveIsValid(0, 1, true,  2, 1, "Big square dropped to down-left");
    assertMoveIsValid(0, 1, true,  2, 2, "Big square dropped to down-right");

    moveSquare(0, 1,  2, 1);
    assertTrue("Square2x2 type updated?", grid.cell(1, 1).square().type().equals(SquareType.s2x2));
    assertTrue("Square2x2 down cell covered?", grid.cell(2, 1).square().type().equals(SquareType.COVERED));
    assertTrue("Square2x2 position updated?", grid.cell(1, 1).square().position().equals(Position.point(1, 1)));

    assertMoveIsValid(1, 1, true, 0, 1, "Big square could back up");
    moveSquare(1, 1, 0, 1);
    moveSquare(0, 1, 2, 1);

    // Asserts after square(2x2) in [0, 1] moves to cell [2, 1](actually [1, 1])
    assertMoveIsValid(0, 0, true,  0, 1, "");
    assertMoveIsValid(0, 3, true,  0, 1, "");
    assertMoveIsValid(1, 1, true,  0, 1, "");
    assertMoveIsValid(1, 0, false, 1, 1, "");
    assertMoveIsValid(1, 3, false, 1, 2, "");
    assertMoveIsValid(2, 0, false, 2, 1, "");
    assertMoveIsValid(2, 3, false, 2, 2, "");
    assertMoveIsValid(3, 1, false, 2, 1, "");
    assertMoveIsValid(3, 2, false, 2, 2, "");
    assertMoveIsValid(4, 1, false, 0, 1, "No jumping");

    moveSquare(0, 3,  0, 1);
    moveSquare(1, 3,  0, 2);
    moveSquare(2, 3,  0, 3);

    assertMoveIsValid(1, 1, true, 1, 3, "Big square could go right");
    moveSquare(1, 1,  1, 3);

    assertMoveIsValid(1, 2, true, 1, 1, "Big square back to left");
    moveSquare(1, 2,  1, 1);

    moveSquare(3, 3,  1, 3);
    moveSquare(3, 2,  2, 3);
    moveSquare(3, 1,  3, 3);
    moveSquare(1, 1,  3, 1);
    // Note: Currently square2x2 is in cell [2, 1]
    // which could be one of finish points in the level 1.

    // Asserts after square(2x2) in [1, 1] moves to cell [3, 1](actually [2, 1])
    assertMoveIsValid(2, 1, true, 1, 1, "");
    assertMoveIsValid(0, 1, true, 1, 1, "");
    assertMoveIsValid(0, 2, true, 1, 2, "");

    moveSquare(1, 3,  1, 1);
    moveSquare(2, 3,  1, 2);
    moveSquare(3, 3,  1, 3);
    moveSquare(4, 3,  2, 3);
    moveSquare(4, 2,  3, 3);
    moveSquare(4, 1,  4, 3);

    // Note: square2x2 is still in cell [2, 1]
    // But this time there is no square1x1 in front of finish line.
    moveSquare(2, 1, 4, 1);
  }

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

}