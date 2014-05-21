package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionLeftForSquare2x1Specification extends AbstractSpecification<Position> {

  @Override
  public boolean isSatisfiedBy(Position left) {
    return left.row == position.row && left.column == position.column - 1
        || left.row == position.row && left.column == position.column - 2;
  }

  private final Position position;

  public PositionLeftForSquare2x1Specification(Position position) {
    this.position = position;
  }

}