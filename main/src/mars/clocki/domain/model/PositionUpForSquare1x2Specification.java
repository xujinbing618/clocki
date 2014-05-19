package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionUpForSquare1x2Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionUpForSquare1x2Specification(Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(Position up) {
    return up.row == position.row - 1 && up.column == position.column
        || up.row == position.row - 2 && up.column == position.column;
  }

}