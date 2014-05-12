package mars.clocki.domain.shared;

/**
 * NOT decorator, used to create a new specification that is the inverse (NOT)
 * of given spec.
 * @param <T>
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

  private Specification<T> spec1;

  /**
   * Create a new NOT specification based on another spec.
   *
   * @param spec1 Specification instance to not.
   */
  public NotSpecification(final Specification<T> spec1) {
    this.spec1 = spec1;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isSatisfiedBy(T t) {
    return !spec1.isSatisfiedBy(t);
  }
}