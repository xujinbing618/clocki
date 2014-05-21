package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionRightForSquare2x1Specification extends AbstractSpecification<Position> {

  @Override
  public boolean isSatisfiedBy(Position right) {
    return right.row == position.row && right.column == position.column + 2
        || right.row == position.row && right.column == position.column + 3;
  }

  private final Position position;

  public PositionRightForSquare2x1Specification(Position position) {
    this.position = position;
  }

}