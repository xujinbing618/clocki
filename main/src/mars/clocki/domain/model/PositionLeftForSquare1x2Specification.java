package mars.clocki.domain.model;

import mars.clocki.domain.shared.AbstractSpecification;

public class PositionLeftForSquare1x2Specification extends AbstractSpecification<Position> {

  private final Position position;

  public PositionLeftForSquare1x2Specification(Position position) {
    this.position = position;
  }

  @Override
  public boolean isSatisfiedBy(Position t) {
    return new PositionLeftForSquare2x2Specification(position).isSatisfiedBy(t);
  }

}