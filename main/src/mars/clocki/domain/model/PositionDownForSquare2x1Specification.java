package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionDownForSquare2x1Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionDownForSquare2x1Specification(Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(final Position down) {
    return down.row == position.row + 1 && down.column == position.column
        || down.row == position.row + 1 && down.column == position.column + 1;
  }

}