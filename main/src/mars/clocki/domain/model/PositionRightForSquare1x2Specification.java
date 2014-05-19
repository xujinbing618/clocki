package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionRightForSquare1x2Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionRightForSquare1x2Specification(final Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(Position right) {
    return right.row == position.row && right.column == position.column + 1
        || right.row == position.row + 1 && right.column == position.column + 1;
  }

}