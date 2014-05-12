package mars.clocki.domain.model;

import mars.clocki.domain.shared.ValueObject;

public class Square implements ValueObject<Square> {

  private final SquareType type;
  private Position position;

  public Square(SquareType type, Position position) {
    this.type = type;
    this.position = position;
  }

  public Position position() {
    return position;
  }

  public SquareType type() {
    return type;
  }

  public static Square square(int row, int column, SquareType type) {
    return new Square(type, new Position(row, column));
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  /**
   * Square types which are recognizable by their names.
   * For example see below names with their equivalent shapes:
   *        _
   * s1x1  | |
   *        _ _
   * s2x2  |   |
   *       |   |
   */
  public enum SquareType {
    s1x1, s2x2, s1x2, s2x1, COVERED;
  }

  @Override
  public boolean sameValueAs(Square other) {
    return other !=null &&
           other.type.equals(type) &&
           other.position.equals(position);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (null == o || o.getClass() != getClass()) return false;

    Square other = (Square) o;
    return sameValueAs(other);
  }

  @Override
  public int hashCode() {
    return type.hashCode() +
        position.hashCode();
  }

  @Override
  public String toString() {
    return String.format("%s %s", type, position);
  }

  private static final long serialVersionUID = 1L;

}