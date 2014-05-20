package mars.clocki.domain.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import mars.clocki.domain.model.Square.SquareType;

/**
 * Grid container contain set of cells.
 */
public class GridContainer {

  private Map<Position, CellContainer> cells;

  public GridContainer(Map<Position, CellContainer> cells) {
    this.cells = cells;
  }

  public void move(Square square, CellContainer targetContainer) {
    if (isValidMove(square, targetContainer)) {
      switch (square.type()) {
      case s1x1:
        moveSquareType1x1(square, targetContainer);
        break;
      case s2x2:
        moveSquareType2x2(square, targetContainer);
        break;
      case s1x2:
        moveSquareType1x2(square, targetContainer);
        break;
      default:
        throw new RuntimeException("Square type not found! (nothing moved)");
      }
    }
  }

  public void move(int rowFrom, int columnFrom, int rowTo, int columnTo) {
    Square square = cell(rowFrom, columnFrom).square();
    if (square != null) {
      move(square, cell(rowTo, columnTo));
    }
  }

  private void moveSquareType1x1(Square square, CellContainer targetContainer) {
    CellContainer currentContainer = cells.get(square.position());
    currentContainer.setSquare(null);
    square.setPosition(targetContainer.position());
    targetContainer.setSquare(square);
  }

  private void moveSquareType1x2(Square square, CellContainer target) {
    CellContainer currentCell = cells.get(square.position());
    currentCell.setSquare(null);
    switch(moveDirectionForSquareType1x2(square.position(), target.position())) {
    case RIGHT:
      square.setPosition(Position.point(
          square.position().row,
          square.position().column + 1
      ));
      cell(square.position().row, square.position().column).setSquare(square);
      cell(square.position().row, square.position().column - 1).setSquare(null);
      cell(square.position().row + 1, square.position().column - 1).setSquare(null);
      makeCellCovered(square.position().row + 1, square.position().column);
      break;
    case LEFT:
      square.setPosition(Position.point(
          square.position().row,
          square.position().column - 1
      ));
      cell(square.position().row, square.position().column).setSquare(square);
      cell(square.position().row, square.position().column + 1).setSquare(null);
      cell(square.position().row + 1, square.position().column + 1).setSquare(null);
      makeCellCovered(square.position().row + 1, square.position().column);
      break;
    case UP:
      if (square.position().row - target.position().row > 1) {
        square.setPosition(Position.point(
            square.position().row - 2,
            square.position().column
        ));
        cell(square.position().row + 2, square.position().column).setSquare(null);
        cell(square.position().row + 3, square.position().column).setSquare(null);
      }
      else {
        square.setPosition(Position.point(
            square.position().row - 1,
            square.position().column
        ));
        cell(square.position().row + 2, square.position().column).setSquare(null);
      }
      cell(square.position().row, square.position().column).setSquare(square);
      makeCellCovered(square.position().row + 1, square.position().column);
      break;
    case DOWN:
      if (target.position().row - square.position().row > 2) {
        square.setPosition(Position.point(
            square.position().row + 2,
            square.position().column
        ));
        cell(square.position().row - 1, square.position().column).setSquare(null);
        cell(square.position().row - 2, square.position().column).setSquare(null);
      }
      else {
        square.setPosition(Position.point(
            square.position().row + 1,
            square.position().column
        ));
        cell(square.position().row - 1, square.position().column).setSquare(null);
      }
      cell(square.position().row, square.position().column).setSquare(square);
      makeCellCovered(square.position().row + 1, square.position().column);
      break;
    default:
      throw new RuntimeException("Square 1x2 didn't moved!");
    }
  }

  private void moveSquareType2x2(Square square, CellContainer target) {
    CellContainer currentCell = cells.get(square.position());
    currentCell.setSquare(null);
    switch (moveDirectionForSquareType2x2(square.position(), target.position())) {
    case RIGHT:
      square.setPosition(Position.point(
          square.position().row,
          square.position().column + 1
      ));
      cell(square.position().row, square.position().column).setSquare(square);
      cell(square.position().row, square.position().column - 1).setSquare(null);
      cell(square.position().row + 1, square.position().column - 1).setSquare(null);
      makeCellCovered(square.position().row, square.position().column + 1);
      makeCellCovered(square.position().row + 1, square.position().column);
      makeCellCovered(square.position().row + 1, square.position().column + 1);
      break;
    case LEFT:
      square.setPosition(Position.point(
          square.position().row, square.position().column - 1
      ));
      cell(square.position().row, square.position().column).setSquare(square);
      cell(square.position().row, square.position().column + 2).setSquare(null);
      cell(square.position().row + 1, square.position().column + 2).setSquare(null);
      makeCellCovered(square.position().row, square.position().column + 1);
      makeCellCovered(square.position().row + 1, square.position().column);
      makeCellCovered(square.position().row + 1, square.position().column + 1);
      break;
    case UP:
      square.setPosition(Position.point(
          square.position().row - 1, square.position().column
          ));
      cell(square.position().row, square.position().column).setSquare(square);
      cell(square.position().row + 2, square.position().column).setSquare(null);
      cell(square.position().row + 2, square.position().column + 1).setSquare(null);
      makeCellCovered(square.position().row, square.position().column + 1);
      makeCellCovered(square.position().row + 1, square.position().column);
      makeCellCovered(square.position().row + 1, square.position().column + 1);
      break;
    case DOWN:
      square.setPosition(Position.point(
          square.position().row + 1, square.position().column
          ));
      cell(square.position().row, square.position().column).setSquare(square);
      cell(square.position().row - 1, square.position().column).setSquare(null);
      cell(square.position().row - 1, square.position().column + 1).setSquare(null);
      makeCellCovered(square.position().row, square.position().column + 1);
      makeCellCovered(square.position().row + 1, square.position().column);
      makeCellCovered(square.position().row + 1, square.position().column + 1);
      break;
    default:
      throw new RuntimeException("Square 2x2 didn't moved!");
    }
  }

  private void makeCellCovered(int row, int column) {
    cell(row, column).setSquare(
        new Square(SquareType.COVERED, Position.point(row, column))
    );
  }

  public Direction moveDirectionForSquareType2x2(Position current,
                                                 Position target) {
    if (new PositionRightForSquare2x2Specification(current).
        isSatisfiedBy(target)) {
      return Direction.RIGHT;
    }
    if (new PositionLeftForSquare2x2Specification(current).
        isSatisfiedBy(target)) {
      return Direction.LEFT;
    }
    if (new PositionTopForSquare2x2Specification(current).
        isSatisfiedBy(target)){
      return Direction.UP;
    }
    if (new PositionDownForSquare2x2Specification(current).
        isSatisfiedBy(target)) {
      return Direction.DOWN;
    }
    return Direction.NONE;
  }

  public Direction moveDirectionForSquareType1x2(Position current,
                                                 Position target) {
    if (new PositionRightForSquare1x2Specification(current).isSatisfiedBy(target)) {
      return Direction.RIGHT;
    }
    if (new PositionLeftForSquare1x2Specification(current).isSatisfiedBy(target)) {
      return Direction.LEFT;
    }
    if (new PositionUpForSquare1x2Specification(current).isSatisfiedBy(target)) {
      return Direction.UP;
    }
    if (new PositionDownForSquare1x2Specification(current).isSatisfiedBy(target)) {
      return Direction.DOWN;
    }
    return Direction.NONE;
  }

  public boolean isValidMove(Square square, CellContainer cellContainer) {
    if (square == null || cellContainer.hasSquare()) {
      return false;
    }
    return isThereAnyValidPath(square, cellContainer.position());
  }

  private boolean isThereAnyValidPath(Square square, Position to) {
    switch (square.type()) {
    case s1x1:
      return possibleMovesForSquareType1x1(square.position(), Direction.NONE).contains(to);
    case s2x2:
      return possibleMovesForSquareType2x2(square.position()).contains(to);
    case s1x2:
      return possibleMovesForSquareType1x2(square.position()).contains(to);
    default:
      return false;
    }
  }

  private Set<Position> possibleMovesForSquareType1x2(final Position p) {
    Set<Position> result = new HashSet<Position>();
    Position right  = Position.point(p.row,     p.column + 1);
    Position right2 = Position.point(p.row + 1, p.column + 1);
    Position left   = Position.point(p.row,     p.column - 1);
    Position left2  = Position.point(p.row + 1, p.column - 1);
    Position top    = Position.point(p.row - 1, p.column);
    Position top2   = Position.point(p.row - 2, p.column);
    Position down   = Position.point(p.row + 2, p.column);
    Position down2  = Position.point(p.row + 3, p.column);

    if (isEmpty(right) && isEmpty(right2)) {
      result.add(right);
      result.add(right2);
    }
    if (isEmpty(left) && isEmpty(left2)) {
      result.add(left);
      result.add(left2);
    }
    if (isEmpty(top)) {
      result.add(top);
      if (isEmpty(top2)) {
        result.add(top2);
      }
    }
    if (isEmpty(down)) {
      result.add(down);
      if (isEmpty(down2)) {
        result.add(down2);
      }
    }
    return result;
  }

  private Set<Position> possibleMovesForSquareType2x2(final Position p) {
    Set<Position> result = new HashSet<Position>();
    Position right  = Position.point(p.row,     p.column + 2);
    Position right2 = Position.point(p.row + 1, p.column + 2);
    Position left   = Position.point(p.row,     p.column - 1);
    Position left2  = Position.point(p.row + 1, p.column - 1);
    Position top    = Position.point(p.row - 1, p.column);
    Position top2   = Position.point(p.row - 1, p.column + 1);
    Position down   = Position.point(p.row + 2, p.column);
    Position down2  = Position.point(p.row + 2, p.column + 1);

    if (isEmpty(right)  && isEmpty(right2)) {
      result.add(right);
      result.add(right2);
    }
    if (isEmpty(left)  && isEmpty(left2)) {
      result.add(left);
      result.add(left2);
    }
    if (isEmpty(top)  && isEmpty(top2)) {
      result.add(top);
      result.add(top2);
    }
    if (isEmpty(down)  && isEmpty(down2)) {
      result.add(down);
      result.add(down2);
    }
    return result;
  }

  private Set<Position> possibleMovesForSquareType1x1(Position p, Direction direction) {
    Set<Position> result = new HashSet<Position>();
    Position right = Position.point(p.row, p.column+1);
    Position left  = Position.point(p.row, p.column-1);
    Position top   = Position.point(p.row-1, p.column);
    Position down  = Position.point(p.row+1, p.column);

    if (isEmpty(right) && !direction.equals(Direction.LEFT)) {
      result.add(right);
      result.addAll(possibleMovesForSquareType1x1(right, Direction.RIGHT));
    }
    if (isEmpty(left) && !direction.equals(Direction.RIGHT)) {
      result.add(left);
      result.addAll(possibleMovesForSquareType1x1(left, Direction.LEFT));
    }
    if (isEmpty(top) && !direction.equals(Direction.DOWN)) {
      result.add(top);
      result.addAll(possibleMovesForSquareType1x1(top, Direction.UP));
    }
    if (isEmpty(down) && !direction.equals(Direction.UP)) {
      result.add(down);
      result.addAll(possibleMovesForSquareType1x1(down, Direction.DOWN));
    }
    return result;
  }

  public enum Direction {
    RIGHT, LEFT, UP, DOWN, NONE;
  }

  public boolean isEmpty(Position position) {
    CellContainer cell = cells.get(position);
    return cell != null && !cell.hasSquare();
  }

  public CellContainer cell(int row, int column) {
    return cells.get(Position.point(row, column));
  }

  public boolean isAllowedToMoveFrom(int row, int column) {
    if (cell(row, column) != null &&
        cell(row, column).square() != null) {
      switch(cell(row, column).square().type()) {
      case s1x1:
        return possibleMovesForSquareType1x1(Position.point(row, column), Direction.NONE).size() > 0;
      case s2x2:
        return possibleMovesForSquareType2x2(Position.point(row, column)).size() > 0;
      case s1x2:
        return possibleMovesForSquareType1x2(Position.point(row, column)).size() > 0;
      default:
        return false;
      }
    }
    return false;
  }

  public boolean isAllowedToMoveTo(int row, int column, int rowTo, int columnTo) {
    CellContainer cell = cell(row, column);
    if (cell != null && cell.square() != null) {
      return isThereAnyValidPath(cell.square(), Position.point(rowTo, columnTo));
    }
    return false;
  }

  private static void putNewCell(Map<Position, CellContainer> cells,
                   int row,
                   int column,
                   SquareType squareType) {
    if (squareType != null) {
      cells.put(Position.point(row, column),
                CellContainer.newCell(row, column, squareType)
      );
    }
    else {
      cells.put(Position.point(row, column),
                CellContainer.newCell(row, column)
      );
    }
  }

  public static GridContainer initLevel1() {
    Map<Position, CellContainer> cells = new HashMap<Position, CellContainer>();
    putNewCell(cells, 0, 0, SquareType.s1x1);
    putNewCell(cells, 0, 1, SquareType.s2x2);
    putNewCell(cells, 0, 2, SquareType.COVERED);
    putNewCell(cells, 0, 3, SquareType.s1x1);
    putNewCell(cells, 1, 0, SquareType.s1x1);
    putNewCell(cells, 1, 1, SquareType.COVERED);
    putNewCell(cells, 1, 2, SquareType.COVERED);
    putNewCell(cells, 1, 3, SquareType.s1x1);
    putNewCell(cells, 2, 0, SquareType.s1x1);
    putNewCell(cells, 2, 1, SquareType.s1x1);
    putNewCell(cells, 2, 2, SquareType.s1x1);
    putNewCell(cells, 2, 3, SquareType.s1x1);
    putNewCell(cells, 3, 0, SquareType.s1x1);
    putNewCell(cells, 3, 1, SquareType.s1x1);
    putNewCell(cells, 3, 2, SquareType.s1x1);
    putNewCell(cells, 3, 3, SquareType.s1x1);
    putNewCell(cells, 4, 0, SquareType.s1x1);
    putNewCell(cells, 4, 1, null);
    putNewCell(cells, 4, 2, null);
    putNewCell(cells, 4, 3, SquareType.s1x1);
    return new GridContainer(cells);
  }

  public static GridContainer initLevel2() {
    Map<Position, CellContainer> cells = new HashMap<Position, CellContainer>();
    putNewCell(cells, 0, 0, SquareType.s1x2);
    putNewCell(cells, 0, 1, SquareType.s2x2);
    putNewCell(cells, 0, 2, SquareType.COVERED);
    putNewCell(cells, 0, 3, SquareType.s1x2);
    putNewCell(cells, 1, 0, SquareType.COVERED);
    putNewCell(cells, 1, 1, SquareType.COVERED);
    putNewCell(cells, 1, 2, SquareType.COVERED);
    putNewCell(cells, 1, 3, SquareType.COVERED);
    putNewCell(cells, 2, 0, SquareType.s1x1);
    putNewCell(cells, 2, 1, SquareType.s1x1);
    putNewCell(cells, 2, 2, SquareType.s1x1);
    putNewCell(cells, 2, 3, SquareType.s1x1);
    putNewCell(cells, 3, 0, SquareType.s1x1);
    putNewCell(cells, 3, 1, SquareType.s1x1);
    putNewCell(cells, 3, 2, SquareType.s1x1);
    putNewCell(cells, 3, 3, SquareType.s1x1);
    putNewCell(cells, 4, 0, SquareType.s1x1);
    putNewCell(cells, 4, 1, null);
    putNewCell(cells, 4, 2, null);
    putNewCell(cells, 4, 3, SquareType.s1x1);
    return new GridContainer(cells);
  }

  public static GridContainer initLevel3() {
    Map<Position, CellContainer> cells = new HashMap<Position, CellContainer>();
    putNewCell(cells, 0, 0, SquareType.s1x2);
    putNewCell(cells, 0, 1, SquareType.s2x2);
    putNewCell(cells, 0, 2, SquareType.COVERED);
    putNewCell(cells, 0, 3, SquareType.s1x2);
    putNewCell(cells, 1, 0, SquareType.COVERED);
    putNewCell(cells, 1, 1, SquareType.COVERED);
    putNewCell(cells, 1, 2, SquareType.COVERED);
    putNewCell(cells, 1, 3, SquareType.COVERED);
    putNewCell(cells, 2, 0, SquareType.s1x2);
    putNewCell(cells, 2, 1, SquareType.s1x1);
    putNewCell(cells, 2, 2, SquareType.s1x1);
    putNewCell(cells, 2, 3, SquareType.s1x1);
    putNewCell(cells, 3, 0, SquareType.COVERED);
    putNewCell(cells, 3, 1, SquareType.s1x1);
    putNewCell(cells, 3, 2, SquareType.s1x1);
    putNewCell(cells, 3, 3, SquareType.s1x1);
    putNewCell(cells, 4, 0, SquareType.s1x1);
    putNewCell(cells, 4, 1, null);
    putNewCell(cells, 4, 2, null);
    putNewCell(cells, 4, 3, SquareType.s1x1);
    return new GridContainer(cells);
  }

}