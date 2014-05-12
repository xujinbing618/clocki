package mars.clocki.domain.model;

import mars.clocki.domain.shared.ValueObject;

final class Position implements ValueObject<Position> {

  final int row;
  final int column;

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public static Position point(int row, int column) {
    return new Position(row, column);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (o == null || o.getClass() != getClass()) return false;

    Position other = (Position) o;
    return sameValueAs(other);
  }

  @Override
  public boolean sameValueAs(Position other) {
    return other != null &&
           other.row == row &&
           other.column == column;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + column;
    result = prime * result + row;
    return result;
  }

  @Override
  public String toString() {
    return String.format("[%s, %s]", row, column);
  }

  private static final long serialVersionUID = 1L;

}