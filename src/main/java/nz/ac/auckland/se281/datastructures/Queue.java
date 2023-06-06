package nz.ac.auckland.se281.datastructures;

/**
 * A queue data structure.
 *
 * @param <T> The type of each item.
 */
public interface Queue<T> {
  public void enqueue(T item);

  public T dequeue();

  public abstract boolean isEmpty();

  public abstract int size();
}
