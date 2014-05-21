package mars.clocki.domain.model;

import static mars.clocki.domain.model.Square.SquareType.*;

/**
 * This test class goes through different possible moves in level 2.
 * We will test only new added logic(square type 1x2) here.
 * see {@link GridContainerTest#testIsValidMoveForLevel1()} for later tests.
 */
public class GridContainerLevel2Test extends GridContainerTestCase {

  @Override
  protected void setUp() throws Exception {
    grid = GridContainer.initLevel2();
  }

  public void testIsValidMoveForLevel2() {
    // Asserts after grid level 2 is initialized. // see end of this file.
    assertMoveIsValid(0, 0, false, 1, 0, "");
    assertMoveIsValid(0, 3, false, 2, 3, "");
    assertMoveIsValid(4, 0, true,  4, 2, "");
    assertMoveIsValid(4, 3, true,  4, 2, "");

    moveSquare(4, 0,  4, 2);
    moveSquare(3, 0,  4, 1);
    moveSquare(2, 0,  4, 0);

    // Asserts after square type 1x2 is able to move down
    assertMoveIsValid(1, 0, false, 2, 0, "");
    assertMoveIsValid(0, 0, true,  2, 0, "");
    assertMoveIsValid(0, 0, true,  3, 0, "");

    moveSquare(0, 0,  2, 0);

    // Asserts after square type 1x2 in [0, 0] moves down to [2, 0]([1, 0] actually)
    assertCellIsEmpty(0, 0);
    assertCellIsEmpty(3, 0);
    assertSquareTypeIs(COVERED, 2, 0);

    moveSquare(1, 0,  0, 0);

    // Asserts after square type 1x2 in [1, 0] moves back up to [0, 0]
    assertCellIsEmpty(2, 0);
    assertCellIsEmpty(3, 0);
    assertSquareTypeIs(COVERED, 1, 0);

    moveSquare(0, 0,  3, 0);

    // Asserts after square type 1x2 in [0, 0] moves down to [3, 0]([2,0] actually)
    assertCellIsEmpty(0, 0);
    assertCellIsEmpty(1, 0);
    assertSquareTypeIs(COVERED, 3, 0);

    moveSquare(2, 0, 0, 0);
    assertCellIsEmpty(2, 0);
    assertCellIsEmpty(3, 0);
    moveSquare(0, 0,  3, 0);
    moveSquare(0, 1,  0, 0);
    assertCellIsEmpty(0, 2);
    assertCellIsEmpty(1, 2);
    moveSquare(2, 2,  0, 2);
    moveSquare(3, 2,  1, 2);
    moveSquare(2, 1,  2, 2);
    moveSquare(3, 1,  3, 2);

    // Asserts after square type 1x2 in [2, 0] is able to go to right
    assertCellIsEmpty(2, 1);
    assertCellIsEmpty(3, 1);

    moveSquare(2, 0,  2, 1);    // Square 1x2 moves to right
    assertCellIsEmpty(2, 0);
    assertCellIsEmpty(3, 0);
    assertSquareTypeIs(COVERED, 3, 1);

    moveSquare(2, 1,  2, 0);    // Square 1x2 moves back to left
    assertCellIsEmpty(2, 1);
    assertCellIsEmpty(3, 1);
    assertSquareTypeIs(COVERED, 3, 0);
  }

}
/*
 * Visual initialization for Level 2
 *   _   _ _ _   _
 *  | | |     | | |
 *  | | |     | | |
 *   _   _   _   _
 *  | | | | | | | |
 *   _   _   _   _
 *  | | | | | | | |
 *   _           _
 *  | |         | |
 */