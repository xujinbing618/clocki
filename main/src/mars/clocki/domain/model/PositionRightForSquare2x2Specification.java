package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionRightForSquare2x2Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionRightForSquare2x2Specification(Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(final Position right) {
    return right.row == position.row && right.column == position.column + 2
        || right.row == position.row + 1 && right.column == position.column + 2;
  }

}