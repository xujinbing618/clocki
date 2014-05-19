package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionDownForSquare1x2Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionDownForSquare1x2Specification(Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(Position down) {
    return down.row == position.row + 2 && down.column == position.column
        || down.row == position.row + 3 && down.column == position.column;
  }

}