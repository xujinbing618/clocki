package mars.clocki.domain.model;

import mars.clocki.domain.model.Square.SquareType;
import mars.clocki.domain.shared.ValueObject;

/**
 * Cell container could contain one Square or nothing.
 */
public class CellContainer implements ValueObject<CellContainer> {

  private Square square;
  private final Position position;

  public CellContainer(Position position) {
    this.position = position;
  }

  public CellContainer(Position position, Square square) {
    this.position = position;
    this.square = square;
  }

  public Square square() {
    return square;
  }

  public static CellContainer newCell(int row, int column) {
    return new CellContainer(new Position(row, column));
  }

  public static CellContainer newCell(int row, int column, SquareType squareType) {
    return new CellContainer(new Position(row, column), Square.square(row, column, squareType));
  }

  public Position position() {
    return position;
  }

  public void setSquare(Square square) {
    this.square = square;
  }

  /**
   * Returns <code>true</code> if cell contains square.
   */
  public boolean hasSquare() {
    return square != null;
  }

  @Override
  public boolean sameValueAs(CellContainer other) {
    return other != null &&
        other.position.sameValueAs(position);
  }

  @Override
  public String toString() {
    return String.format("%s (%s)", position.toString(), square == null ? "null" : square.toString());
  }

  private static final long serialVersionUID = 1L;

}