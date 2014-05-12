package mars.clocki.domain.shared;

/**
 * An entity, as explained in the DDD book(written by Eric Evans).
 *
 */
public interface Entity<T> {

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return <code>true</code> if the identities are the same,
   * regardless of other attributes.
   */
  boolean sameIdentityAs(T other);
}