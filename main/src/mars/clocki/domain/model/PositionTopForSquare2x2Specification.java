package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionTopForSquare2x2Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionTopForSquare2x2Specification(Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(final Position top) {
    return top.row == position.row - 1 && top.column == position.column
        || top.row == position.row - 1 && top.column == position.column + 1;
  }

}