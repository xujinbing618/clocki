package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionLeftForSquare2x2Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionLeftForSquare2x2Specification(Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(final Position left) {
    return left.row == position.row && left.column == position.column - 1
        || left.row == position.row + 1 && left.column == position.column - 1;
  }

}