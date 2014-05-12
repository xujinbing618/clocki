package mars.clocki.domain.shared;

/**
 * OR specification, used to create a new specification that is the OR of
 * two other specifications.
 */
public class OrSpecification<T> extends AbstractSpecification<T> {

  private Specification<T> spec1;
  private Specification<T> spec2;

  /**
   * Create a new OR specification based on two other spec.
   *
   * @param spec
   */
  public OrSpecification(final Specification<T> spec1, final Specification<T> spec2) {
    this.spec1 = spec1;
    this.spec2 = spec2;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isSatisfiedBy(T t) {
    return spec1.isSatisfiedBy(t) || spec2.isSatisfiedBy(t);
  }

}