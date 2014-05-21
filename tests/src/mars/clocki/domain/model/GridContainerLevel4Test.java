package mars.clocki.domain.model;

import static mars.clocki.domain.model.Square.SquareType.*;

/**
 * GridContainerLevel4Test class goes through different possible moves
 * in level 4. We will test only new added logic(square type 2x1) here.
 * see {@link GridContainerLevel2Test#testIsValidMoveForLevel2()} for
 * later tests.
 */
public class GridContainerLevel4Test extends GridContainerTestCase {

  @Override
  protected void setUp() throws Exception {
    grid = GridContainer.initLevel4();
  }

  public void testIsValidMoveForLevel4() {
    // Asserts after grid level 4 is initialized. // see end of this file.
    assertMoveIsValid(0, 0, false, 1, 0, "");
    assertMoveIsValid(4, 0, true,  4, 1, "");

    moveSquare(3, 1,  4, 1);
    moveSquare(3, 2,  4, 2);

    // Asserts after square type 2x1 is able to move down
    assertMoveIsValid(2, 2, false, 3, 2, "Covered cell should not move");
    assertMoveIsValid(2, 1, true,  3, 1, "");

    moveSquare(2, 1,  3, 1);

    // Asserts after square type 2x1 in [2, 1] moves down to [3, 1]
    assertCellIsEmpty(2, 1);
    assertCellIsEmpty(2, 2);
    assertSquareTypeIs(COVERED, 3, 2);
    assertMoveIsValid(3, 1, true,  2, 1, "");

    moveSquare(3, 1,  2, 1);

    // Asserts after square type 2x1 in [3, 1] move back(up) to [2, 1]
    assertCellIsEmpty(3, 1);
    assertCellIsEmpty(3, 2);
    assertSquareTypeIs(COVERED, 2, 2);
    assertMoveIsValid(2, 1, true,  3, 1, "");

    moveSquare(2, 1,  3, 1);
    moveSquare(2, 3,  2, 1);
    moveSquare(3, 3,  2, 2);
    moveSquare(3, 1,  3, 3);

    // Asserts after square type 2x1 in [3, 1] moves right in [3, 3] ([3,2] actually)
    assertCellIsEmpty(3, 1);
    assertSquareTypeIs(s2x1, 3, 2);
    assertSquareTypeIs(COVERED, 3, 3);

    moveSquare(3, 2,  3, 1);

    // Asserts after square type 2x1 in [3, 2] moves left in [3, 1]
    assertCellIsEmpty(3, 3);
    assertSquareTypeIs(s2x1, 3, 1);
    assertSquareTypeIs(COVERED, 3, 2);

    moveSquare(2, 2,  2, 3);
    moveSquare(2, 1,  2, 2);
    moveSquare(2, 0,  2, 1);
    moveSquare(3, 0,  2, 0);
    moveSquare(3, 1,  3, 0);    // type 2x1
    moveSquare(3, 0,  3, 3);    // type 2x1 moves two block to right

    // Asserts after square type 2x1 in [3, 0] moves right in [3, 3] ([3, 2] actually)
    assertCellIsEmpty(3, 0);
    assertCellIsEmpty(3, 1);
    assertSquareTypeIs(s2x1, 3, 2);
    assertSquareTypeIs(COVERED, 3, 3);

    moveSquare(3, 2,  3, 0);

    // Asserts after square type 2x1 in [3, 2] moves left in [3, 0]
    assertCellIsEmpty(3, 3);
    assertCellIsEmpty(3, 2);
    assertSquareTypeIs(s2x1, 3, 0);
    assertSquareTypeIs(COVERED, 3, 1);
  }

}
/*
 * Visual initialization for Level 4
 *   _   _ _ _   _
 *  | | |     | | |
 *  | | |     | | |
 *   _   _ _ _   _
 *  | | | 2x1 | | |
 *   _   _   _   _
 *  | | | | | | | |
 *   _           _
 *  | |         | |
 */