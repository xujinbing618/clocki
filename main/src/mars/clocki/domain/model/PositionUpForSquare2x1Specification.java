package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionUpForSquare2x1Specification extends AbstractSpecification<Position> {

  @Override
  public boolean isSatisfiedBy(Position up) {
    return up.row == position.row - 1 && up.column == position.column
        || up.row == position.row - 1 && up.column == position.column + 1;
  }

  private final Position position;

  public PositionUpForSquare2x1Specification(Position position) {
    this.position = position;
  }

}