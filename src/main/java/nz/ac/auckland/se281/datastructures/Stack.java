package nz.ac.auckland.se281.datastructures;

/**
 * A stack data structure.
 *
 * @param <T> The type of each item.
 */
public interface Stack<T> {
  public void append(T item);

  public T pop();

  public abstract boolean isEmpty();

  public abstract int size();
}
