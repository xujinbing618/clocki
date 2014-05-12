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
      default:
        break;
      }
    }
  }

  private void moveSquareType1x1(Square square, CellContainer targetContainer) {
    CellContainer currentContainer = cells.get(square.position());
    currentContainer.setSquare(null);
    square.setPosition(targetContainer.position());
    targetContainer.setSquare(square);
  }

  private void moveSquareType2x2(Square square, CellContainer target) {
    CellContainer currentContainer = cells.get(square.position());
    currentContainer.setSquare(null);
    switch (moveDirectionForSquareType2x2(square, target)) {
    case RIGHT:
      square.setPosition(Position.point(
          square.position().row,
          square.position().column + 1
      ));
      cell(square.position().row, square.position().column).setSquare(square);
      cell(square.position().row, square.position().column - 1).setSquare(null);
      cell(square.position().row - 1, square.position().column - 1).setSquare(null);
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
    case TOP:
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
      break;
    }
  }

  private void makeCellCovered(int row, int column) {
    cell(row, column).setSquare(
        new Square(SquareType.COVERED, Position.point(row, column))
    );
  }

  private Direction moveDirectionForSquareType2x2(Square s2x2,
                                                  CellContainer target) {
    if (new PositionRightForSquare2x2Specification(s2x2.position()).
        isSatisfiedBy(target.position())) {
      return Direction.RIGHT;
    }
    if (new PositionLeftForSquare2x2Specification(s2x2.position()).
        isSatisfiedBy(target.position())) {
      return Direction.LEFT;
    }
    if (new PositionTopForSquare2x2Specification(s2x2.position()).
        isSatisfiedBy(target.position())){
      return Direction.TOP;
    }
    if (new PositionDownForSquare2x2Specification(s2x2.position()).
        isSatisfiedBy(target.position())) {
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
      return possibleMovesForSquareType2x2(square.position(), Direction.NONE).contains(to);
    default:
      return false;
    }
  }

  private Set<Position> possibleMovesForSquareType2x2(Position p, Direction direction) {
    Set<Position> result = new HashSet<Position>();
    Position right  = Position.point(p.row,     p.column + 2);
    Position right2 = Position.point(p.row + 1, p.column + 2);
    Position left   = Position.point(p.row,     p.column - 1);
    Position left2  = Position.point(p.row - 1, p.column - 1);
    Position top    = Position.point(p.row - 1, p.column);
    Position top2   = Position.point(p.row - 1, p.column + 1);
    Position down   = Position.point(p.row + 2, p.column);
    Position down2  = Position.point(p.row + 2, p.column + 1);

    if (isEmpty(right)  &&
        isEmpty(right2) && !direction.equals(Direction.LEFT)) {
      result.add(right);
      result.addAll(possibleMovesForSquareType1x1(right, Direction.RIGHT));
    }
    if (isEmpty(left)  &&
        isEmpty(left2) && !direction.equals(Direction.RIGHT)) {
      result.add(left);
      result.addAll(possibleMovesForSquareType1x1(left, Direction.LEFT));
    }
    if (isEmpty(top)  &&
        isEmpty(top2) && !direction.equals(Direction.DOWN)) {
      result.add(top);
      result.addAll(possibleMovesForSquareType1x1(top, Direction.TOP));
    }
    if (isEmpty(down)  &&
        isEmpty(down2) && !direction.equals(Direction.TOP)) {
      result.add(down);
      result.addAll(possibleMovesForSquareType1x1(down, Direction.DOWN));
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
      result.addAll(possibleMovesForSquareType1x1(top, Direction.TOP));
    }
    if (isEmpty(down) && !direction.equals(Direction.TOP)) {
      result.add(down);
      result.addAll(possibleMovesForSquareType1x1(down, Direction.DOWN));
    }
    return result;
  }

  enum Direction {
    RIGHT, LEFT, TOP, DOWN, NONE;
  }

  private boolean isEmpty(Position position) {
    CellContainer cell = cells.get(position);
    return cell != null && !cell.hasSquare();
  }

  public static GridContainer initLevel1() {
    Map<Position, CellContainer> cells = new HashMap<Position, CellContainer>();
    cells.put(Position.point(0, 0), CellContainer.newCell(0, 0, SquareType.s1x1));
    cells.put(Position.point(0, 1), CellContainer.newCell(0, 1, SquareType.s2x2));
    cells.put(Position.point(0, 2), CellContainer.newCell(0, 2, SquareType.COVERED));
    cells.put(Position.point(0, 3), CellContainer.newCell(0, 3, SquareType.s1x1));

    cells.put(Position.point(1, 0), CellContainer.newCell(1, 0, SquareType.s1x1));
    cells.put(Position.point(1, 1), CellContainer.newCell(1, 1, SquareType.COVERED));
    cells.put(Position.point(1, 2), CellContainer.newCell(1, 2, SquareType.COVERED));
    cells.put(Position.point(1, 3), CellContainer.newCell(1, 3, SquareType.s1x1));

    cells.put(Position.point(2, 0), CellContainer.newCell(2, 0, SquareType.s1x1));
    cells.put(Position.point(2, 1), CellContainer.newCell(2, 1, SquareType.s1x1));
    cells.put(Position.point(2, 2), CellContainer.newCell(2, 2, SquareType.s1x1));
    cells.put(Position.point(2, 3), CellContainer.newCell(2, 3, SquareType.s1x1));

    cells.put(Position.point(3, 0), CellContainer.newCell(3, 0, SquareType.s1x1));
    cells.put(Position.point(3, 1), CellContainer.newCell(3, 1, SquareType.s1x1));
    cells.put(Position.point(3, 2), CellContainer.newCell(3, 2, SquareType.s1x1));
    cells.put(Position.point(3, 3), CellContainer.newCell(3, 3, SquareType.s1x1));

    cells.put(Position.point(4, 0), CellContainer.newCell(4, 0, SquareType.s1x1));
    cells.put(Position.point(4, 1), CellContainer.newCell(4, 1));
    cells.put(Position.point(4, 2), CellContainer.newCell(4, 2));
    cells.put(Position.point(4, 3), CellContainer.newCell(4, 3, SquareType.s1x1));
    return new GridContainer(cells);
  }

  public CellContainer cell(int row, int column) {
    return cells.get(Position.point(row, column));
  }

}